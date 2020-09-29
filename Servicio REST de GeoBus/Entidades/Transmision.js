var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var transmision = new Schema({
    idTransmision: Number,
    fecha: String,
    horaComienzo: String,
    horaFin: String,
    puntajeObtenido: Number,
    idUsuario: {type: Number, ref: 'Usuario'},
    idColectivo: {type: Number, ref: 'Colectivo'}
});

module.exports = mongoose.model('Transmision',transmision);