var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var colectivo = new Schema({
    idColectivo: Number,
    velocidad: Number,
    latitudActual: Number,
    longitudActual: Number,
    idRecorrido: {type: Number, ref: 'Recorrido'},
    idLineaColectivo: {type: Number, ref: 'LineaColectivo'}
});

module.exports = mongoose.model('Colectivo',colectivo);