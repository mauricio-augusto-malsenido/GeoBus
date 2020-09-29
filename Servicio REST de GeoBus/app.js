const express = require("express");
const bodyparser = require("body-parser");
const methodoverride = require("method-override");
const mongoose = require("mongoose");

const coleccionColectivo = require("./Entidades/Colectivo");
const coleccionLineaColectivo = require("./Entidades/LineaColectivo");
const coleccionParada = require("./Entidades/Parada");
const coleccionPuntoGeografico = require("./Entidades/PuntoGeografico");
const coleccionRecorrido = require("./Entidades/Recorrido");
const coleccionTransmision = require("./Entidades/Transmision");
const coleccionUbicacionAnteriorColectivo = require("./Entidades/UbicacionAnteriorColectivo");
const coleccionUbicacionAnteriorUsuario = require("./Entidades/UbicacionAnteriorUsuario");
const coleccionUsuario = require("./Entidades/Usuario");
const coleccionUsuarioParada = require("./Entidades/UsuarioParada");

const uri = "mongodb+srv://geobus-tgrny.gcp.mongodb.net/GeoBus?retryWrites=true&w=majority";

const app = express();
const router = express.Router();

app.use(bodyparser.urlencoded({extended:false}));
app.use(bodyparser.json());
app.use(methodoverride());
app.use(express.json({limit: '50mb'}));
app.use(router);

app.all('*', function (req,res,next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "X-Requested-With");
    next();
});

mongoose.connect(uri, {auth: {
    user: 'mauricio',
    password: 'Mm.021091'
  }}, (err,res) => {
    if(err) throw err;

    console.log('Conexión con la base de datos MongoDB exitosa');
});

app.get('/', (req, res) => {
    res.status(200).send("Servicio REST de GeoBus iniciado y en funcionamiento");
  });

app.listen(8080,() => {
    console.log("Servicio REST iniciado y en funcionamiento");
});

// Definición de las operaciones para la base de datos GeoBus

// Operaciones para la colección Colectivo

// Registrar un Colectivo
router.post("/colectivo",(req,res) => {
    console.log("Insertando un nuevo Colectivo");
    
    var colectivo = new coleccionColectivo();
    colectivo.idColectivo = req.body.idColectivo;
    colectivo.velocidad = req.body.velocidad;
    colectivo.latitudActual = req.body.latitudActual;
    colectivo.longitudActual = req.body.longitudActual;
    colectivo.idRecorrido = req.body.idRecorrido;
    colectivo.idLineaColectivo = req.body.idLineaColectivo;

    colectivo.save((err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación POST exitosa', colectivo: respuesta});
    });
});

// Eliminar un Colectivo
router.delete("/colectivo/:idColectivo",(req,res) => {
    console.log("Eliminando el Colectivo especificado");
    
    coleccionColectivo.findOne({idColectivo: req.params.idColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        retorno.remove((err, respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación DELETE exitosa', colectivo: respuesta});
        });
    });
});

// Modificar un Colectivo
router.put("/colectivo/:idColectivo",(req,res) => {
    console.log("Actualizando el Colectivo especificado");
    
    coleccionColectivo.findOne({idColectivo: req.params.idColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        var colectivo = retorno;
        colectivo.idColectivo = req.body.idColectivo;
        colectivo.velocidad = req.body.velocidad;
        colectivo.latitudActual = req.body.latitudActual;
        colectivo.longitudActual = req.body.longitudActual;
        colectivo.idRecorrido = req.body.idRecorrido;
        colectivo.idLineaColectivo = req.body.idLineaColectivo;

        colectivo.save((err,respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación PUT exitosa', colectivo: respuesta});
        });
    });
});

// Obtener un Colectivo
router.get("/colectivo/:idColectivo",(req,res) => {
    console.log("Obteniendo el Colectivo especificado");
    
    coleccionColectivo.findOne({idColectivo: req.params.idColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',colectivo: retorno});
    });
});

// Obtener todos los Colectivos por idRecorrido
router.get("/colectivo/recorrido/:idRecorrido",(req,res) => {
    console.log("Obteniendo todos los Colectivos con el idRecorrido especificado");
    
    coleccionColectivo.find({idRecorrido: req.params.idRecorrido}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',colectivos: retorno});
    });
});

// Obtener todos los Colectivos por idLineaColectivo
router.get("/colectivo/linea/:idLineaColectivo",(req,res) => {
    console.log("Obteniendo todos los Colectivos con el idLineaColectivo especificado");
    
    coleccionColectivo.find({idLineaColectivo: req.params.idLineaColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',colectivos: retorno});
    });
});

// Obtener todos los Colectivos
router.get("/colectivo",(req,res) => {
    console.log("Obteniendo todos los Colectivos");
    coleccionColectivo.find({},(err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa', colectivos: respuesta});
    });
});

// Operaciones para la colección LineaColectivo

// Registrar una LineaColectivo
router.post("/lineaColectivo",(req,res) => {
    console.log("Insertando una nueva LineaColectivo");
    
    var lineaColectivo = new coleccionLineaColectivo();
    lineaColectivo.idLineaColectivo = req.body.idLineaColectivo;
    lineaColectivo.nombre = req.body.nombre;

    lineaColectivo.save((err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación POST exitosa', lineaColectivo: respuesta});
    });
});

// Eliminar una LineaColectivo
router.delete("/lineaColectivo/:idLineaColectivo",(req,res) => {
    console.log("Eliminando la LineaColectivo especificada");
    
    coleccionLineaColectivo.findOne({idLineaColectivo: req.params.idLineaColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        retorno.remove((err, respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación DELETE exitosa', lineaColectivo: respuesta});
        });
    });
});

// Modificar una LineaColectivo
router.put("/lineaColectivo/:idLineaColectivo",(req,res) => {
    console.log("Actualizando la LineaColectivo especificada");
    
    coleccionLineaColectivo.findOne({idLineaColectivo: req.params.idLineaColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        var lineaColectivo = retorno;
        lineaColectivo.idLineaColectivo = req.body.idLineaColectivo;
        lineaColectivo.nombre = req.body.nombre;

        lineaColectivo.save((err,respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación PUT exitosa', lineaColectivo: respuesta});
        });
    });
});

// Obtener una LineaColectivo por idLineaColectivo
router.get("/lineaColectivo/id/:idLineaColectivo",(req,res) => {
    console.log("Obteniendo la LineaColectivo especificada");
    
    coleccionLineaColectivo.findOne({idLineaColectivo: req.params.idLineaColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',lineaColectivo: retorno});
    });
});

// Obtener una LineaColectivo por nombre
router.get("/lineaColectivo/nombre/:nombre",(req,res) => {
    console.log("Obteniendo la LineaColectivo especificada");
    
    coleccionLineaColectivo.findOne({nombre: req.params.nombre}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',lineaColectivo: retorno});
    });
});

// Obtener todas las LineasColectivo
router.get("/lineaColectivo",(req,res) => {
    console.log("Obteniendo todas las LineasColectivo");
    coleccionLineaColectivo.find({},(err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa', lineasColectivo: respuesta});
    });
});

// Operaciones para la colección Parada

// Registrar una Parada
router.post("/parada",(req,res) => {
    console.log("Insertando una nueva Parada");
    
    var parada = new coleccionParada();
    parada.idParada = req.body.idParada;
    parada.latitud = req.body.latitud;
    parada.longitud = req.body.longitud;
    parada.idRecorrido = req.body.idRecorrido;
    parada.idLineaColectivo = req.body.idLineaColectivo;

    parada.save((err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación POST exitosa', parada: respuesta});
    });
});

// Eliminar una Parada
router.delete("/parada/:idParada",(req,res) => {
    console.log("Eliminando la Parada especificada");
    
    coleccionParada.findOne({idParada: req.params.idParada}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        retorno.remove((err, respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación DELETE exitosa', parada: respuesta});
        });
    });
});

// Modificar una Parada
router.put("/parada/:idParada",(req,res) => {
    console.log("Actualizando la Parada especificada");
    
    coleccionParada.findOne({idParada: req.params.idParada}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        var parada = retorno;
        parada.idParada = req.body.idParada;
        parada.latitud = req.body.latitud;
        parada.longitud = req.body.longitud;
        parada.idRecorrido = req.body.idRecorrido;
        parada.idLineaColectivo = req.body.idLineaColectivo;

        parada.save((err,respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación PUT exitosa', parada: respuesta});
        });
    });
});

// Obtener una Parada por idParada
router.get("/parada/:idParada",(req,res) => {
    console.log("Obteniendo la Parada especificada");
    
    coleccionParada.findOne({idParada: req.params.idParada}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',parada: retorno});
    });
});

// Obtener una Parada por Posicion
router.get("/parada/posicion/:latitud/:longitud",(req,res) => {
    console.log("Obteniendo la Parada especificada");
    
    coleccionParada.findOne({latitud: req.params.latitud, longitud: req.params.longitud}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',parada: retorno});
    });
});

// Obtener todas las Paradas por idRecorrido
router.get("/parada/recorrido/:idRecorrido",(req,res) => {
    console.log("Obteniendo todas las Paradas con el idRecorrido especificado");
    
    coleccionParada.find({idRecorrido: req.params.idRecorrido}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',paradas: retorno});
    });
});

// Obtener todas las Paradas por idLineaColectivo
router.get("/parada/linea/:idLineaColectivo",(req,res) => {
    console.log("Obteniendo todas las Paradas con el idLineaColectivo especificado");
    
    coleccionParada.find({idLineaColectivo: req.params.idLineaColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',paradas: retorno});
    });
});

// Obtener todas las Paradas
router.get("/parada",(req,res) => {
    console.log("Obteniendo todas las Paradas");
    coleccionParada.find({},(err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa', paradas: respuesta});
    });
});

// Operaciones para la colección PuntoGeografico

// Registrar un PuntoGeografico
router.post("/puntoGeografico",(req,res) => {
    console.log("Insertando un nuevo PuntoGeografico");
    
    var puntoGeografico = new coleccionPuntoGeografico();
    puntoGeografico.idPuntoGeografico = req.body.idPuntoGeografico;
    puntoGeografico.latitud = req.body.latitud;
    puntoGeografico.longitud = req.body.longitud;
    puntoGeografico.idRecorrido = req.body.idRecorrido;

    puntoGeografico.save((err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación POST exitosa', puntoGeografico: respuesta});
    });
});

// Eliminar un PuntoGeografico
router.delete("/puntoGeografico/:idPuntoGeografico",(req,res) => {
    console.log("Eliminando el PuntoGeografico especificado");
    
    coleccionPuntoGeografico.findOne({idPuntoGeografico: req.params.idPuntoGeografico}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        retorno.remove((err, respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación DELETE exitosa', puntoGeografico: respuesta});
        });
    });
});

// Modificar un PuntoGeografico
router.put("/puntoGeografico/:idPuntoGeografico",(req,res) => {
    console.log("Actualizando el PuntoGeografico especificado");
    
    coleccionPuntoGeografico.findOne({idPuntoGeografico: req.params.idPuntoGeografico}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        var puntoGeografico = retorno;
        puntoGeografico.idPuntoGeografico = req.body.idPuntoGeografico;
        puntoGeografico.latitud = req.body.latitud;
        puntoGeografico.longitud = req.body.longitud;
        puntoGeografico.idRecorrido = req.body.idRecorrido;

        puntoGeografico.save((err,respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación PUT exitosa', puntoGeografico: respuesta});
        });
    });
});

// Obtener un PuntoGeografico por idPuntoGeografico
router.get("/puntoGeografico/:idPuntoGeografico",(req,res) => {
    console.log("Obteniendo el PuntoGeografico especificado");
    
    coleccionPuntoGeografico.findOne({idPuntoGeografico: req.params.idPuntoGeografico}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',puntoGeografico: retorno});
    });
});

// Obtener un PuntoGeografico por Posicion y idRecorrido
router.get("/puntoGeografico/recorrido_posicion/:idRecorrido/:latitud/:longitud",(req,res) => {
    console.log("Obteniendo el PuntoGeografico especificado");
    
    coleccionPuntoGeografico.findOne({idRecorrido: req.params.idRecorrido, latitud: req.params.latitud, longitud: req.params.longitud}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',puntoGeografico: retorno});
    });
});

// Obtener todos los PuntosGeograficos por idRecorrido
router.get("/puntoGeografico/recorrido/:idRecorrido",(req,res) => {
    console.log("Obteniendo todos los PuntosGeograficos con el idRecorrido especificado");
    
    coleccionPuntoGeografico.find({idRecorrido: req.params.idRecorrido}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',puntosGeograficos: retorno});
    });
});

// Obtener todos los PuntosGeograficos
router.get("/puntoGeografico",(req,res) => {
    console.log("Obteniendo todos los PuntosGeograficos");
    coleccionPuntoGeografico.find({},(err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa', puntosGeograficos: respuesta});
    });
});

// Operaciones para la colección Recorrido

// Registrar un Recorrido
router.post("/recorrido",(req,res) => {
    console.log("Insertando un nuevo Recorrido");
    
    var recorrido = new coleccionRecorrido();
    recorrido.idRecorrido = req.body.idRecorrido;
    recorrido.nombre = req.body.nombre;
    recorrido.descripcion = req.body.descripcion;
    recorrido.idLineaColectivo = req.body.idLineaColectivo;

    recorrido.save((err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación POST exitosa', recorrido: respuesta});
    });
});

// Eliminar un Recorrido
router.delete("/recorrido/:idRecorrido",(req,res) => {
    console.log("Eliminando el Recorrido especificado");
    
    coleccionRecorrido.findOne({idRecorrido: req.params.idRecorrido}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        retorno.remove((err, respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación DELETE exitosa', recorrido: respuesta});
        });
    });
});

// Modificar un Recorrido
router.put("/recorrido/:idRecorrido",(req,res) => {
    console.log("Actualizando el Recorrido especificado");
    
    coleccionRecorrido.findOne({idRecorrido: req.params.idRecorrido}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        var recorrido = retorno;
        recorrido.idRecorrido = req.body.idRecorrido;
        recorrido.nombre = req.body.nombre;
        recorrido.descripcion = req.body.descripcion;
        recorrido.idLineaColectivo = req.body.idLineaColectivo;

        recorrido.save((err,respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación PUT exitosa', recorrido: respuesta});
        });
    });
});

// Obtener un Recorrido por idRecorrido
router.get("/recorrido/id/:idRecorrido",(req,res) => {
    console.log("Obteniendo el Recorrido especificado");
    
    coleccionRecorrido.findOne({idRecorrido: req.params.idRecorrido}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',recorrido: retorno});
    });
});

// Obtener un Recorrido por nombre
router.get("/recorrido/nombre/:nombre",(req,res) => {
    console.log("Obteniendo el Recorrido especificado");
    
    coleccionRecorrido.findOne({nombre: req.params.nombre}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',recorrido: retorno});
    });
});

// Obtener todos los Recorridos por idLineaColectivo
router.get("/recorrido/linea/:idLineaColectivo",(req,res) => {
    console.log("Obteniendo todos los Recorridos con el idLineaColectivo especificado");
    
    coleccionRecorrido.find({idLineaColectivo: req.params.idLineaColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',recorridos: retorno});
    });
});

// Obtener todos los Recorridos
router.get("/recorrido",(req,res) => {
    console.log("Obteniendo todos los Recorridos");
    coleccionRecorrido.find({},(err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa', recorridos: respuesta});
    });
});

// Operaciones para la colección Transmision

// Registrar una Transmision
router.post("/transmision",(req,res) => {
    console.log("Insertando una nueva Transmision");
    
    var transmision = new coleccionTransmision();
    transmision.idTransmision = req.body.idTransmision;
    transmision.fecha = req.body.fecha;
    transmision.horaComienzo = req.body.horaComienzo;
    transmision.horaFin = req.body.horaFin;
    transmision.puntajeObtenido = req.body.puntajeObtenido;
    transmision.idUsuario = req.body.idUsuario;
    transmision.idColectivo = req.body.idColectivo;

    transmision.save((err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación POST exitosa', transmision: respuesta});
    });
});

// Eliminar una Transmision
router.delete("/transmision/:idTransmision",(req,res) => {
    console.log("Eliminando la Transmision especificada");
    
    coleccionTransmision.findOne({idTransmision: req.params.idTransmision}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        retorno.remove((err, respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación DELETE exitosa', transmision: respuesta});
        });
    });
});

// Modificar una Transmision
router.put("/transmision/:idTransmision",(req,res) => {
    console.log("Actualizando la Transmision especificada");
    
    coleccionTransmision.findOne({idTransmision: req.params.idTransmision}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        var transmision = retorno;
        transmision.idTransmision = req.body.idTransmision;
        transmision.fecha = req.body.fecha;
        transmision.horaComienzo = req.body.horaComienzo;
        transmision.horaFin = req.body.horaFin;
        transmision.puntajeObtenido = req.body.puntajeObtenido;
        transmision.idUsuario = req.body.idUsuario;
        transmision.idColectivo = req.body.idColectivo;

        transmision.save((err,respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación PUT exitosa', transmision: respuesta});
        });
    });
});

// Obtener una Transmision
router.get("/transmision/:idTransmision",(req,res) => {
    console.log("Obteniendo la Transmision especificada");
    
    coleccionTransmision.findOne({idTransmision: req.params.idTransmision}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',transmision: retorno});
    });
});

// Obtener todas las Transmisiones por idUsuario y idColectivo
router.get("/transmision/usuario_colectivo/:idUsuario/:idColectivo",(req,res) => {
    console.log("Obteniendo todas las Transmisiones con el idUsuario y idColectivo especificados");
    
    coleccionTransmision.find({idUsuario: req.params.idUsuario, idColectivo: req.params.idColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',transmisiones: retorno});
    });
});

// Obtener todas las Transmisiones por idUsuario
router.get("/transmision/usuario/:idUsuario",(req,res) => {
    console.log("Obteniendo todas las Transmisiones con el idUsuario especificado");
    
    coleccionTransmision.find({idUsuario: req.params.idUsuario}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',transmisiones: retorno});
    });
});

// Obtener todas las Transmisiones por idColectivo
router.get("/transmision/colectivo/:idColectivo",(req,res) => {
    console.log("Obteniendo todas las Transmisiones con el idColectivo especificado");
    
    coleccionTransmision.find({idColectivo: req.params.idColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',transmisiones: retorno});
    });
});

// Obtener todas las Transmisiones
router.get("/transmision",(req,res) => {
    console.log("Obteniendo todas las Transmisiones");
    coleccionTransmision.find({},(err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa', transmisiones: respuesta});
    });
});

// Operaciones para la colección UbicacionAnteriorColectivo

// Registrar una UbicacionAnteriorColectivo
router.post("/ubicacionAnteriorColectivo",(req,res) => {
    console.log("Insertando una nueva UbicacionAnteriorColectivo");
    
    var ubicacionAnteriorColectivo = new coleccionUbicacionAnteriorColectivo();
    ubicacionAnteriorColectivo.idUbicacionAnteriorColectivo = req.body.idUbicacionAnteriorColectivo;
    ubicacionAnteriorColectivo.fecha = req.body.fecha;
    ubicacionAnteriorColectivo.hora = req.body.hora;
    ubicacionAnteriorColectivo.latitudAnterior = req.body.latitudAnterior;
    ubicacionAnteriorColectivo.longitudAnterior = req.body.longitudAnterior;
    ubicacionAnteriorColectivo.idColectivo = req.body.idColectivo;
    ubicacionAnteriorColectivo.idRecorrido = req.body.idRecorrido;
    ubicacionAnteriorColectivo.idLineaColectivo = req.body.idLineaColectivo;

    ubicacionAnteriorColectivo.save((err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación POST exitosa', ubicacionAnteriorColectivo: respuesta});
    });
});

// Eliminar una UbicacionAnteriorColectivo
router.delete("/ubicacionAnteriorColectivo/:idUbicacionAnteriorColectivo",(req,res) => {
    console.log("Eliminando la UbicacionAnteriorColectivo especificada");
    
    coleccionUbicacionAnteriorColectivo.findOne({idUbicacionAnteriorColectivo: req.params.idUbicacionAnteriorColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        retorno.remove((err, respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación DELETE exitosa', ubicacionAnteriorColectivo: respuesta});
        });
    });
});

// Modificar una UbicacionAnteriorColectivo
router.put("/ubicacionAnteriorColectivo/:idUbicacionAnteriorColectivo",(req,res) => {
    console.log("Actualizando la UbicacionAnteriorColectivo especificada");
    
    coleccionUbicacionAnteriorColectivo.findOne({idUbicacionAnteriorColectivo: req.params.idUbicacionAnteriorColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        var ubicacionAnteriorColectivo = retorno;
        ubicacionAnteriorColectivo.idUbicacionAnteriorColectivo = req.body.idUbicacionAnteriorColectivo;
        ubicacionAnteriorColectivo.fecha = req.body.fecha;
        ubicacionAnteriorColectivo.hora = req.body.hora;
        ubicacionAnteriorColectivo.latitudAnterior = req.body.latitudAnterior;
        ubicacionAnteriorColectivo.longitudAnterior = req.body.longitudAnterior;
        ubicacionAnteriorColectivo.idColectivo = req.body.idColectivo;
        ubicacionAnteriorColectivo.idRecorrido = req.body.idRecorrido;
        ubicacionAnteriorColectivo.idLineaColectivo = req.body.idLineaColectivo;

        ubicacionAnteriorColectivo.save((err,respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación PUT exitosa', ubicacionAnteriorColectivo: respuesta});
        });
    });
});

// Obtener una UbicacionAnteriorColectivo
router.get("/ubicacionAnteriorColectivo/:idUbicacionAnteriorColectivo",(req,res) => {
    console.log("Obteniendo la UbicacionAnteriorColectivo especificada");
    
    coleccionUbicacionAnteriorColectivo.findOne({idUbicacionAnteriorColectivo: req.params.idUbicacionAnteriorColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',ubicacionAnteriorColectivo: retorno});
    });
});

// Obtener todas las UbicacionesAnterioresColectivos por idColectivo
router.get("/ubicacionAnteriorColectivo/colectivo/:idColectivo",(req,res) => {
    console.log("Obteniendo todas las UbicacionesAnterioresColectivos con el idColectivo especificado");
    
    coleccionUbicacionAnteriorColectivo.find({idColectivo: req.params.idColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',ubicacionesAnterioresColectivos: retorno});
    });
});

// Obtener todas las UbicacionesAnterioresColectivos por idRecorrido
router.get("/ubicacionAnteriorColectivo/recorrido/:idRecorrido",(req,res) => {
    console.log("Obteniendo todas las UbicacionesAnterioresColectivos con el idRecorrido especificado");
    
    coleccionUbicacionAnteriorColectivo.find({idRecorrido: req.params.idRecorrido}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',ubicacionesAnterioresColectivos: retorno});
    });
});

// Obtener todas las UbicacionesAnterioresColectivos por idLineaColectivo
router.get("/ubicacionAnteriorColectivo/linea/:idLineaColectivo",(req,res) => {
    console.log("Obteniendo todas las UbicacionesAnterioresColectivos con el idLineaColectivo especificado");
    
    coleccionUbicacionAnteriorColectivo.find({idLineaColectivo: req.params.idLineaColectivo}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',ubicacionesAnterioresColectivos: retorno});
    });
});

// Obtener todas las UbicacionesAnterioresColectivos
router.get("/ubicacionAnteriorColectivo",(req,res) => {
    console.log("Obteniendo todas las UbicacionesAnterioresColectivos");
    coleccionUbicacionAnteriorColectivo.find({},(err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa', ubicacionesAnterioresColectivos: respuesta});
    });
});

// Operaciones para la colección UbicacionAnteriorUsuario

// Registrar una UbicacionAnteriorUsuario
router.post("/ubicacionAnteriorUsuario",(req,res) => {
    console.log("Insertando una nueva UbicacionAnteriorUsuario");
    
    var ubicacionAnteriorUsuario = new coleccionUbicacionAnteriorUsuario();
    ubicacionAnteriorUsuario.idUbicacionAnteriorUsuario = req.body.idUbicacionAnteriorUsuario;
    ubicacionAnteriorUsuario.fecha = req.body.fecha;
    ubicacionAnteriorUsuario.hora = req.body.hora;
    ubicacionAnteriorUsuario.latitudAnterior = req.body.latitudAnterior;
    ubicacionAnteriorUsuario.longitudAnterior = req.body.longitudAnterior;
    ubicacionAnteriorUsuario.idUsuario = req.body.idUsuario;

    ubicacionAnteriorUsuario.save((err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación POST exitosa', ubicacionAnteriorUsuario: respuesta});
    });
});

// Eliminar una UbicacionAnteriorUsuario
router.delete("/ubicacionAnteriorUsuario/:idUbicacionAnteriorUsuario",(req,res) => {
    console.log("Eliminando la UbicacionAnteriorUsuario especificada");
    
    coleccionUbicacionAnteriorUsuario.findOne({idUbicacionAnteriorUsuario: req.params.idUbicacionAnteriorUsuario}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        retorno.remove((err, respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación DELETE exitosa', ubicacionAnteriorUsuario: respuesta});
        });
    });
});

// Modificar una UbicacionAnteriorUsuario
router.put("/ubicacionAnteriorUsuario/:idUbicacionAnteriorUsuario",(req,res) => {
    console.log("Actualizando la UbicacionAnteriorUsuario especificada");
    
    coleccionUbicacionAnteriorUsuario.findOne({idUbicacionAnteriorUsuario: req.params.idUbicacionAnteriorUsuario}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        var ubicacionAnteriorUsuario = retorno;
        ubicacionAnteriorUsuario.idUbicacionAnteriorUsuario = req.body.idUbicacionAnteriorUsuario;
        ubicacionAnteriorUsuario.fecha = req.body.fecha;
        ubicacionAnteriorUsuario.hora = req.body.hora;
        ubicacionAnteriorUsuario.latitudAnterior = req.body.latitudAnterior;
        ubicacionAnteriorUsuario.longitudAnterior = req.body.longitudAnterior;
        ubicacionAnteriorUsuario.idUsuario = req.body.idUsuario;

        ubicacionAnteriorUsuario.save((err,respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación PUT exitosa', ubicacionAnteriorUsuario: respuesta});
        });
    });
});

// Obtener una UbicacionAnteriorUsuario
router.get("/ubicacionAnteriorUsuario/:idUbicacionAnteriorUsuario",(req,res) => {
    console.log("Obteniendo la UbicacionAnteriorUsuario especificada");
    
    coleccionUbicacionAnteriorUsuario.findOne({idUbicacionAnteriorUsuario: req.params.idUbicacionAnteriorUsuario}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',ubicacionAnteriorUsuario: retorno});
    });
});

// Obtener todas las UbicacionesAnterioresUsuarios por idUsuario
router.get("/ubicacionAnteriorUsuario/usuario/:idUsuario",(req,res) => {
    console.log("Obteniendo todas las UbicacionesAnterioresUsuarios con el idUsuario especificado");
    
    coleccionUbicacionAnteriorUsuario.find({idUsuario: req.params.idUsuario}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',ubicacionesAnterioresUsuarios: retorno});
    });
});

// Obtener todas las UbicacionesAnterioresUsuarios
router.get("/ubicacionAnteriorUsuario",(req,res) => {
    console.log("Obteniendo todas las UbicacionesAnterioresUsuarios");
    coleccionUbicacionAnteriorUsuario.find({},(err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa', ubicacionesAnterioresUsuarios: respuesta});
    });
});

// Operaciones para la colección Usuario

// Registrar un Usuario
router.post("/usuario",(req,res) => {
    console.log("Insertando un nuevo Usuario");
    
    var usuario = new coleccionUsuario();
    usuario.idUsuario = req.body.idUsuario;
    usuario.nombre = req.body.nombre;
    usuario.puntajeActual = req.body.puntajeActual;
    usuario.latitudActual = req.body.latitudActual;
    usuario.longitudActual = req.body.longitudActual;
    usuario.email = req.body.email

    usuario.save((err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación POST exitosa', usuario: respuesta});
    });
});

// Eliminar un Usuario
router.delete("/usuario/:idUsuario",(req,res) => {
    console.log("Eliminando el Usuario especificado");
    
    coleccionUsuario.findOne({idUsuario: req.params.idUsuario}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        retorno.remove((err, respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación DELETE exitosa', usuario: respuesta});
        });
    });
});

// Modificar un Usuario
router.put("/usuario/:idUsuario",(req,res) => {
    console.log("Actualizando el Usuario especificado");
    
    coleccionUsuario.findOne({idUsuario: req.params.idUsuario}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        var usuario = retorno;
        usuario.idUsuario = req.body.idUsuario;
        usuario.nombre = req.body.nombre;
        usuario.puntajeActual = req.body.puntajeActual;
        usuario.latitudActual = req.body.latitudActual;
        usuario.longitudActual = req.body.longitudActual;
        usuario.email = req.body.email

        usuario.save((err,respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación PUT exitosa', usuario: respuesta});
        });
    });
});

// Obtener un Usuario
router.get("/usuario/:idUsuario",(req,res) => {
    console.log("Obteniendo el Usuario especificado");
    
    coleccionUsuario.findOne({idUsuario: req.params.idUsuario}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',usuario: retorno});
    });
});

// Obtener todos los Usuarios
router.get("/usuario",(req,res) => {
    console.log("Obteniendo todos los Usuarios");
    coleccionUsuario.find({},(err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa', usuarios: respuesta});
    });
});

// Operaciones para la colección UsuarioParada

// Registrar un UsuarioParada
router.post("/usuarioParada",(req,res) => {
    console.log("Insertando un nuevo UsuarioParada");
    
    var usuarioParada = new coleccionUsuarioParada();
    usuarioParada.idUsuario = req.body.idUsuario;
    usuarioParada.idParada = req.body.idParada;
    usuarioParada.fecha = req.body.fecha;
    usuarioParada.hora = req.body.hora;

    usuarioParada.save((err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación POST exitosa', usuarioParada: respuesta});
    });
});

// Eliminar un UsuarioParada
router.delete("/usuarioParada/:idUsuario/:idParada/:fecha/:hora",(req,res) => {
    console.log("Eliminando el UsuarioParada especificado");
    
    coleccionUsuarioParada.findOne({idUsuario: req.params.idUsuario, idParada: req.params.idParada, fecha: req.params.fecha, hora: req.params.hora}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        retorno.remove((err, respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación DELETE exitosa', usuarioParada: respuesta});
        });
    });
});

// Modificar un UsuarioParada
router.put("/usuarioParada/:idUsuario/:idParada/:fecha/:hora",(req,res) => {
    console.log("Actualizando el UsuarioParada especificado");
    
    coleccionUsuarioParada.findOne({idUsuario: req.params.idUsuario, idParada: req.params.idParada, fecha: req.params.fecha, hora: req.params.hora}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        var usuarioParada = retorno;
        usuarioParada.idUsuario = req.body.idUsuario;
        usuarioParada.idParada = req.body.idParada;
        usuarioParada.fecha = req.body.fecha;
        usuarioParada.hora = req.body.hora;

        usuarioParada.save((err,respuesta) => {
            if(err) res.send({respuesta: err.message});

            res.send({respuesta: 'Operación PUT exitosa', usuarioParada: respuesta});
        });
    });
});

// Obtener un UsuarioParada
router.get("/usuarioParada/:idUsuario/:idParada/:fecha/:hora",(req,res) => {
    console.log("Obteniendo el UsuarioParada especificado");
    
    coleccionUsuarioParada.findOne({idUsuario: req.params.idUsuario, idParada: req.params.idParada, fecha: req.params.fecha, hora: req.params.hora}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',usuarioParada: retorno});
    });
});

// Obtener todos los UsuarioParadas por idUsuario y idParada
router.get("/usuarioParada/:idUsuario/:idParada",(req,res) => {
    console.log("Obteniendo todos los UsuariosParadas con el idUsuario y el idParada especificados");
    
    coleccionUsuarioParada.find({idUsuario: req.params.idUsuario, idParada: req.params.idParada}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',usuariosParadas: retorno});
    });
});

// Obtener todos los UsuarioParadas por idUsuario
router.get("/usuarioParada/:idUsuario",(req,res) => {
    console.log("Obteniendo todos los UsuariosParadas con el idUsuario especificado");
    
    coleccionUsuarioParada.find({idUsuario: req.params.idUsuario}, (err,retorno) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa',usuariosParadas: retorno});
    });
});

// Obtener todos los UsuariosParadas
router.get("/usuarioParada",(req,res) => {
    console.log("Obteniendo todos los UsuariosParadas");
    coleccionUsuarioParada.find({},(err,respuesta) => {
        if(err) res.send({respuesta: err.message});

        res.send({respuesta: 'Operación GET exitosa', usuariosParadas: respuesta});
    });
});