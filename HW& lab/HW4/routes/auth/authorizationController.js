require('dotenv').config()
var express = require('express')
var router = express.Router()
const User = require('../../models/users')
var jwt = require('jsonwebtoken')
var bcrypt = require('bcryptjs')
var secret = process.env.SECRET
module.exports = router => {
  router.post('/register', function (req, res) {
    var hashedPassword = bcrypt.hashSync(req.body.password, 8)
    User.create(
      {
        username: req.body.name,
        email: req.body.email,
        password: hashedPassword
      },
      function (err, user) {
        if (err)
          return res
            .status(500)
            .send('There was a problem registering the user.')
        var token = jwt.sign({ id: user._id }, secret, {
          expiresIn: 86400
        })
        res.status(200).send({ auth: true, token: token })
      }
    )
  })
    router.post('/login', function (req, res) {
    User.findOne({ email: req.body.email }, function (err, user) {
      if (err) return res.status(500).send('Error on the server.')
      if (!user) return res.status(404).send('No user found.')
      var passwordIsValid = bcrypt.compareSync(req.body.password, user.password)
      if (!passwordIsValid)
        return res.status(401).send({ auth: false, token: null })
      var token = jwt.sign({ id: user._id }, secret, {
        expiresIn: 86400 
      })
      res.status(200).send({ auth: true, token: token })
    })
  })

}
