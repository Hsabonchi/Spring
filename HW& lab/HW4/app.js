// const app = require('express')();
const app = require('express')();
require('dotenv').config()
const mongoose = require('mongoose');
mongoose.Promise = global.Promise;
const dbString = process.env.DB_STRING;
const busboyBodyParser = require('busboy-body-parser');
mongoose.connect(dbString)
  .then(() => {
      console.log(`Connected to Database`);
  })
  .catch(err => {
    console.log(`Error connecting to DB, error: ${err}`)
  });
app.use(busboyBodyParser({ limit: '50mb' }));  
let routes = require('./routes/');
app.use('/', routes);
app.listen(process.env.PORT, () => {
  console.log(`\nStarted on port ${process.env.PORT}`);
});
