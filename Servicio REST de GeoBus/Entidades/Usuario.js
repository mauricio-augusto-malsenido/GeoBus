var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var usuario = new Schema({
    idUsuario: Number,
    nombre: String,
    puntajeActual: Number,
    latitudActual: Number,
    longitudActual: Number,
    email: String
});

module.exports = mongoose.model('Usuario',usuario);