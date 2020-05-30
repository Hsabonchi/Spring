var jwt = require('jsonwebtoken')
require('dotenv').config()
var secret = process.env.SECRET
function verifyToken (req, res, next) {
  let authHeader = req.header('Authorization')
  if (authHeader != null) {
    var token = authHeader.split(' ')[1]
  }
  if (!token)
    return res.status(403).send({ auth: false, message: 'No token provided.' })
  jwt.verify(token, secret, function (err, decoded) {
    if (err)
      return res
        .status(500)
        .send({ auth: false, message: 'Failed to authenticate token.' })
    req.userId = decoded.id
    next()
  })
}
module.exports = verifyToken
