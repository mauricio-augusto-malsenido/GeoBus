var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var usuarioParada = new Schema({
    idUsuario: {type: Number, ref: 'Usuario'},
    idParada: {type: Number, ref: 'Parada'},
    fecha: String,
    hora: String
});

module.exports = mongoose.model('UsuarioParada',usuarioParada);