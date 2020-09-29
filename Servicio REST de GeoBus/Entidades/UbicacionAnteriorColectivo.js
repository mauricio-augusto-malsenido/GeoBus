var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var ubicacionAnteriorColectivo = new Schema({
    idUbicacionAnteriorColectivo: Number,
    fecha: String,
    hora: String,
    latitudAnterior: Number,
    longitudAnterior: Number,
    idColectivo: {type: Number, ref: 'Colectivo'},
    idRecorrido: {type: Number, ref: 'Recorrido'},
    idLineaColectivo: {type: Number, ref: 'LineaColectivo'}
});

module.exports = mongoose.model('UbicacionAnteriorColectivo',ubicacionAnteriorColectivo);