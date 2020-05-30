var verifyToken = require('./auth/verifyToken')
const mongoose = require('mongoose')
const Grid = require('gridfs-stream')
const fileType = require('file-type')
const Document = require('../models/documents')
const conn = mongoose.connection
module.exports = router => {
  Grid.mongo = mongoose.mongo
  let gfs
  conn.once('open', () => {
    gfs = Grid(conn.db)
    router.get('/documents/all', verifyToken, (req, res) => {
      Document.find()
        .exec().then(docs => {
          let documents = docs.map(file => ({
            file_name: file.name,
            file_type: file.type,
            versionCount: file.versions.length,
            timeStamp: file.timestamp,
            versions: file.versions.map(version => ({
              notes: version.notes,
              versionId: version._id,
              timeStamp: version.timestamp,
              link: `http://${req.headers.host}/download?document_id=${version.ver_id}&main_doc_id=${file.doc_id}`
            }))
          }))
          res.json({
            success: true,
            documents
          })
        })
        .catch(err => {
          logger.error(
            `Error, while getting all uploaded file, with error:  ${err}`
          )
          res.status(400).send({
            message: `Error, while getting all uploaded file, with error: ${err}`
          })
        })
    })
    router.get('/documents/:doc_id', verifyToken, (req, res) => {
      Document.find({ doc_id: req.params.doc_id })
        .exec()
        .then(docs => {
          let uploadedFiles = docs.map(file => ({
            file_name: file.name,
            file_type: file.type,
            versionsCount: file.versions.length,
            timestamp: file.timestamp,
            versions: file.versions.map(version => ({
              version_id: version._id,
              notes: version.notes,
              timeStamp: version.timestamp,
              link: `http://${req.headers.host}download?document_id=${version.ver_id}&main_doc_id=${req.params.doc_id}`
            }))
          }))
          res.json({
            success: true,
            uploadedFiles
          })
        })
        .catch(err => {
          logger.error(`Error, while getting all uploaded file, with error:  ${err}`)
          res.status(400).send({
            message: `Error, while getting all uploaded file, with error: ${err}`
          })
        })
    })

    router.get('/download', verifyToken, (req, res) => {
      let { document_id } = req.query
      gfs.findOne({_id: document_id},
        (err, file) => {
          if (!file) {
            return res.status(404).send({
              message: 'File was not found'
            })
          }
          let data = []
          let readstream = gfs.createReadStream({
            filename: file.filename
          })
          readstream.on('data', chunk => {
            data.push(chunk)
          })
          readstream.on('end', () => {
            data = Buffer.concat(data)
            let type = fileType(data)
            res.writeHead(200, {
              'Content-Type': type.mime,
              'Content-disposition':
                'attachment; filename=' + file.filename + '.' + type.ext,
              'Content-Length': file.length
            })
            res.end(data)
          })
          readstream.on('error', err => {
            logger.error(
              `Error, while downloading a file, with error:  ${err}`
            )
            res.status(400).send({
              message: `Error, while downloading a file, with error:  ${err}`
            })
          })
        }
      )
    })
    router.post('/documents/add', verifyToken, (req, res) => {
      let { file } = req.files
      let writeStream = gfs.createWriteStream({
        filename: `${file.name}`,
        mode: 'w',
        content_type: file.mimetype
      })
        writeStream.on('close', function (uploadedFile) {
          Document.create({
            doc_id: mongoose.Types.ObjectId(),
            name: req.body.name,
            versions: [
              {
                ver_id: uploadedFile._id,
                type: uploadedFile.contentType,
                length: uploadedFile.length,
                notes: req.body.notes
              }
            ]
          }).then(file =>
              res.json({
                success: true,
                message: 'Added'
              })
            ).catch(err => {
              logger.error(
                `Error, while uploading new files, with error: ${err}`
              )
              res.status(500).json({
                message: `Error while uploading new files, with error: ${err}`
              })
            })
        })
      writeStream.write(file.data)
      writeStream.end()
    })

    router.post('/documents/add/version/:document_id/', verifyToken, (req, res) => {
      var query = { doc_id: req.params.document_id }
      let { file } = req.files
      let writeStream = gfs.createWriteStream({
        filename: `${file.name}`,
        mode: 'w',
        content_type: file.mimetype
      })
      writeStream.on('close', function (doc) {
        Document.findOneAndUpdate(
          query,
          {
            $push: {
              versions: {
                ver_id: doc._id,
                type: doc.contentType,
                length: doc.length,
                notes: req.body.notes,
              }
            }
          },
          { upsert: false },
          function (err, doc) {
            if (err) return res.send(500, { error: err })
            return res.send('Version Added')
          }
        )
      })
      writeStream.write(file.data)
      writeStream.end()
    })
  })

}
