var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var ubicacionAnteriorUsuario = new Schema({
    idUbicacionAnteriorUsuario: Number,
    fecha: String,
    hora: String,
    latitudAnterior: Number,
    longitudAnterior: Number,
    idUsuario: {type: Number, ref: 'Usuario'}
});

module.exports = mongoose.model('UbicacionAnteriorUsuario',ubicacionAnteriorUsuario);