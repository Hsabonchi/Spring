const router = require('express').Router()
require('./api.js')(router)
require('./auth/authorizationController')(router)
module.exports = router
