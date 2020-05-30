const mongoose = require('mongoose')
const documentSchema = new mongoose.Schema({
  name: {
    type: String
  },
  versions: [
    {
      ver_id: {
        type: String
      },
      length: {
        type: Number
      },
      versionName: {
        type: String
      },
      timestamp: {
        type: Date,
        default: new Date()
      },
      type: {
        type: String
      },
      notes: {
        type: String
      }
    }
  ],
  length: {
    type: Number
  },
  doc_id: {
    type: String
  },
  notes: {
    type: String
  },
  timestamp: {
    type: Date,
    default: new Date()
  },
  type: {
    type: String
  }
  
})

module.exports = mongoose.model('Documents', documentSchema)
