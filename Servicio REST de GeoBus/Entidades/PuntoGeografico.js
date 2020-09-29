var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var puntoGeografico = new Schema({
    idPuntoGeografico: Number,
    latitud: Number,
    longitud: Number,
    idRecorrido: {type: Number, ref: 'Recorrido'}
});

module.exports = mongoose.model('PuntoGeografico',puntoGeografico);