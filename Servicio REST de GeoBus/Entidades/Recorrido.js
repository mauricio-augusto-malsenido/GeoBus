var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var recorrido = new Schema({
    idRecorrido: Number,
    nombre: String,
    descripcion: String,
    idLineaColectivo: {type: Number, ref: 'LineaColectivo'}
});

module.exports = mongoose.model('Recorrido',recorrido);