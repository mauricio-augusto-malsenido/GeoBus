var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var lineaColectivo = new Schema({
    idLineaColectivo: Number,
    nombre: String
});

module.exports = mongoose.model('LineaColectivo',lineaColectivo);