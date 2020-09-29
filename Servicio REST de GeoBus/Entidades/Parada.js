var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var parada = new Schema({
    idParada: Number,
    latitud: Number,
    longitud: Number,
    idRecorrido: {type: Number, ref: 'Recorrido'},
    idLineaColectivo: {type: Number, ref: 'LineaColectivo'}
});

module.exports = mongoose.model('Parada',parada);