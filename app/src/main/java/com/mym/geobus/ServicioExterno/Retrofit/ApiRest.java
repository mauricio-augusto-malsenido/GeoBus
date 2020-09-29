package com.mym.geobus.ServicioExterno.Retrofit;

import com.mym.geobus.Entidades.Colectivo;
import com.mym.geobus.Entidades.LineaColectivo;
import com.mym.geobus.Entidades.Parada;
import com.mym.geobus.Entidades.PuntoGeografico;
import com.mym.geobus.Entidades.Recorrido;
import com.mym.geobus.Entidades.Transmision;
import com.mym.geobus.Entidades.UbicacionAnteriorColectivo;
import com.mym.geobus.Entidades.UbicacionAnteriorUsuario;
import com.mym.geobus.Entidades.Usuario;
import com.mym.geobus.Entidades.UsuarioParada;
import com.mym.geobus.ServicioExterno.Response.Colectivo.DeleteColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.Colectivo.GetAllColectivosResponse;
import com.mym.geobus.ServicioExterno.Response.Colectivo.GetColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.Colectivo.PostColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.Colectivo.PutColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.LineaColectivo.DeleteLineaColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.LineaColectivo.GetAllLineasColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.LineaColectivo.GetLineaColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.LineaColectivo.PostLineaColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.LineaColectivo.PutLineaColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.Parada.DeleteParadaResponse;
import com.mym.geobus.ServicioExterno.Response.Parada.GetAllParadasResponse;
import com.mym.geobus.ServicioExterno.Response.Parada.GetParadaResponse;
import com.mym.geobus.ServicioExterno.Response.Parada.PostParadaResponse;
import com.mym.geobus.ServicioExterno.Response.Parada.PutParadaResponse;
import com.mym.geobus.ServicioExterno.Response.PuntoGeografico.DeletePuntoGeograficoResponse;
import com.mym.geobus.ServicioExterno.Response.PuntoGeografico.GetAllPuntosGeograficosResponse;
import com.mym.geobus.ServicioExterno.Response.PuntoGeografico.GetPuntoGeograficoResponse;
import com.mym.geobus.ServicioExterno.Response.PuntoGeografico.PostPuntoGeograficoResponse;
import com.mym.geobus.ServicioExterno.Response.PuntoGeografico.PutPuntoGeograficoResponse;
import com.mym.geobus.ServicioExterno.Response.Recorrido.DeleteRecorridoResponse;
import com.mym.geobus.ServicioExterno.Response.Recorrido.GetAllRecorridosResponse;
import com.mym.geobus.ServicioExterno.Response.Recorrido.GetRecorridoResponse;
import com.mym.geobus.ServicioExterno.Response.Recorrido.PostRecorridoResponse;
import com.mym.geobus.ServicioExterno.Response.Recorrido.PutRecorridoResponse;
import com.mym.geobus.ServicioExterno.Response.Transmision.DeleteTransmisionResponse;
import com.mym.geobus.ServicioExterno.Response.Transmision.GetAllTransmisionesResponse;
import com.mym.geobus.ServicioExterno.Response.Transmision.GetTransmisionResponse;
import com.mym.geobus.ServicioExterno.Response.Transmision.PostTransmisionResponse;
import com.mym.geobus.ServicioExterno.Response.Transmision.PutTransmisionResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorColectivo.DeleteUbicacionAnteriorColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorColectivo.GetAllUbicacionesAnterioresColectivosResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorColectivo.GetUbicacionAnteriorColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorColectivo.PostUbicacionAnteriorColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorColectivo.PutUbicacionAnteriorColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorUsuario.DeleteUbicacionAnteriorUsuarioResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorUsuario.GetAllUbicacionesAnterioresUsuariosResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorUsuario.GetUbicacionAnteriorUsuarioResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorUsuario.PostUbicacionAnteriorUsuarioResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorUsuario.PutUbicacionAnteriorUsuarioResponse;
import com.mym.geobus.ServicioExterno.Response.Usuario.DeleteUsuarioResponse;
import com.mym.geobus.ServicioExterno.Response.Usuario.GetAllUsuariosResponse;
import com.mym.geobus.ServicioExterno.Response.Usuario.GetUsuarioResponse;
import com.mym.geobus.ServicioExterno.Response.Usuario.PostUsuarioResponse;
import com.mym.geobus.ServicioExterno.Response.Usuario.PutUsuarioResponse;
import com.mym.geobus.ServicioExterno.Response.UsuarioParada.DeleteUsuarioParadaResponse;
import com.mym.geobus.ServicioExterno.Response.UsuarioParada.GetAllUsuariosParadasResponse;
import com.mym.geobus.ServicioExterno.Response.UsuarioParada.GetUsuarioParadaResponse;
import com.mym.geobus.ServicioExterno.Response.UsuarioParada.PostUsuarioParadaResponse;
import com.mym.geobus.ServicioExterno.Response.UsuarioParada.PutUsuarioParadaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiRest {

    // Operaciones relacionadas con la tabla Colectivo

    @POST("/colectivo")
    Call<PostColectivoResponse> agregarColectivo(@Body Colectivo colectivo);

    @DELETE("/colectivo/{idColectivo}")
    Call<DeleteColectivoResponse> eliminarColectivo(@Path("idColectivo") int idColectivo);

    @PUT("/colectivo/{idColectivo}")
    Call<PutColectivoResponse> actualizarColectivo(@Path("idColectivo") int idColectivo, @Body Colectivo colectivo);

    @GET("/colectivo/{idColectivo}")
    Call<GetColectivoResponse> obtenerColectivo(@Path("idColectivo") int idColectivo);

    @GET("/colectivo/recorrido/{idRecorrido}")
    Call<GetAllColectivosResponse> obtenerColectivosPorRecorrido(@Path("idRecorrido") int idRecorrido);

    @GET("/colectivo/linea/{idLineaColectivo}")
    Call<GetAllColectivosResponse> obtenerColectivosPorLinea(@Path("idLineaColectivo") int idLineaColectivo);

    @GET("/colectivo")
    Call<GetAllColectivosResponse> obtenerColectivos();

    // Operaciones relacionadas con la tabla LineaColectivo

    @POST("/lineaColectivo")
    Call<PostLineaColectivoResponse> agregarLineaColectivo(@Body LineaColectivo lineaColectivo);

    @DELETE("/lineaColectivo/{idLineaColectivo}")
    Call<DeleteLineaColectivoResponse> eliminarLineaColectivo(@Path("idLineaColectivo") int idLineaColectivo);

    @PUT("/lineaColectivo/{idLineaColectivo}")
    Call<PutLineaColectivoResponse> actualizarLineaColectivo(@Path("idLineaColectivo") int idLineaColectivo, @Body LineaColectivo lineaColectivo);

    @GET("/lineaColectivo/id/{idLineaColectivo}")
    Call<GetLineaColectivoResponse> obtenerLineaColectivoConId(@Path("idLineaColectivo") int idLineaColectivo);

    @GET("/lineaColectivo/nombre/{nombre}")
    Call<GetLineaColectivoResponse> obtenerLineaColectivoConNombre(@Path("nombre") String nombre);

    @GET("/lineaColectivo")
    Call<GetAllLineasColectivoResponse> obtenerLineasColectivo();

    // Operaciones relacionadas con la tabla Parada

    @POST("/parada")
    Call<PostParadaResponse> agregarParada(@Body Parada parada);

    @DELETE("/parada/{idParada}")
    Call<DeleteParadaResponse> eliminarParada(@Path("idParada") int idParada);

    @PUT("/parada/{idParada}")
    Call<PutParadaResponse> actualizarParada(@Path("idParada") int idParada, @Body Parada parada);

    @GET("/parada/{idParada}")
    Call<GetParadaResponse> obtenerParada(@Path("idParada") int idParada);

    @GET("/parada/posicion/{latitud}/{longitud}")
    Call<GetParadaResponse> obtenerParadaPorPosicion(@Path("latitud") double latitud, @Path("longitud") double longitud);

    @GET("/parada/recorrido/{idRecorrido}")
    Call<GetAllParadasResponse> obtenerParadasPorRecorrido(@Path("idRecorrido") int idRecorrido);

    @GET("/parada/linea/{idLineaColectivo}")
    Call<GetAllParadasResponse> obtenerParadasPorLinea(@Path("idLineaColectivo") int idLineaColectivo);

    @GET("/parada")
    Call<GetAllParadasResponse> obtenerParadas();

    // Operaciones relacionadas con la tabla PuntoGeografico

    @POST("/puntoGeografico")
    Call<PostPuntoGeograficoResponse> agregarPuntoGeografico(@Body PuntoGeografico puntoGeografico);

    @DELETE("/puntoGeografico/{idPuntoGeografico}")
    Call<DeletePuntoGeograficoResponse> eliminarPuntoGeografico(@Path("idPuntoGeografico") int idPuntoGeografico);

    @PUT("/puntoGeografico/{idPuntoGeografico}")
    Call<PutPuntoGeograficoResponse> actualizarPuntoGeografico(@Path("idPuntoGeografico") int idPuntoGeografico, @Body PuntoGeografico puntoGeografico);

    @GET("/puntoGeografico/{idPuntoGeografico}")
    Call<GetPuntoGeograficoResponse> obtenerPuntoGeograficoPorId(@Path("idPuntoGeografico") int idPuntoGeografico);

    @GET("/puntoGeografico/recorrido_posicion/{idRecorrido}/{latitud}/{longitud}")
    Call<GetPuntoGeograficoResponse> obtenerPuntoGeograficoPorPosicionyRecorrido(@Path("idRecorrido") int idRecorrido, @Path("latitud") double latitud, @Path("longitud") double longitud);

    @GET("/puntoGeografico/recorrido/{idRecorrido}")
    Call<GetAllPuntosGeograficosResponse> obtenerPuntosGeograficosPorRecorrido(@Path("idRecorrido") int idRecorrido);

    @GET("/puntoGeografico")
    Call<GetAllPuntosGeograficosResponse> obtenerPuntosGeograficos();

    // Operaciones relacionadas con la tabla Recorrido

    @POST("/recorrido")
    Call<PostRecorridoResponse> agregarRecorrido(@Body Recorrido recorrido);

    @DELETE("/recorrido/{idRecorrido}")
    Call<DeleteRecorridoResponse> eliminarRecorrido(@Path("idRecorrido") int idRecorrido);

    @PUT("/recorrido/{idRecorrido}")
    Call<PutRecorridoResponse> actualizarRecorrido(@Path("idRecorrido") int idRecorrido, @Body Recorrido recorrido);

    @GET("/recorrido/id/{idRecorrido}")
    Call<GetRecorridoResponse> obtenerRecorridoPorId(@Path("idRecorrido") int idRecorrido);

    @GET("/recorrido/nombre/{nombre}")
    Call<GetRecorridoResponse> obtenerRecorridoPorNombre(@Path("nombre") String nombre);

    @GET("/recorrido/linea/{idLineaColectivo}")
    Call<GetAllRecorridosResponse> obtenerRecorridosPorLinea(@Path("idLineaColectivo") int idLineaColectivo);

    @GET("/recorrido")
    Call<GetAllRecorridosResponse> obtenerRecorridos();

    // Operaciones relacionadas con la tabla Transmision

    @POST("/transmision")
    Call<PostTransmisionResponse> agregarTransmision(@Body Transmision transmision);

    @DELETE("/transmision/{idTransmision}")
    Call<DeleteTransmisionResponse> eliminarTransmision(@Path("idTransmision") int idTransmision);

    @PUT("/transmision/{idTransmision}")
    Call<PutTransmisionResponse> actualizarTransmision(@Path("idTransmision") int idTransmision, @Body Transmision transmision);

    @GET("/transmision/{idTransmision}")
    Call<GetTransmisionResponse> obtenerTransmision(@Path("idTransmision") int idTransmision);

    @GET("/transmision/usuario_colectivo/{idUsuario}/{idColectivo}")
    Call<GetAllTransmisionesResponse> obtenerTransmisionesPorUsuarioyColectivo(@Path("idUsuario") int idUsuario, @Path("idColectivo") int idColectivo);

    @GET("/transmision/usuario/{idUsuario}")
    Call<GetAllTransmisionesResponse> obtenerTransmisionesPorUsuario(@Path("idUsuario") int idUsuario);

    @GET("/transmision/colectivo/{idColectivo}")
    Call<GetAllTransmisionesResponse> obtenerTransmisionesPorColectivo(@Path("idColectivo") int idColectivo);

    @GET("/transmision")
    Call<GetAllTransmisionesResponse> obtenerTransmisiones();

    // Operaciones relacionadas con la tabla UbicacionAnteriorColectivo

    @POST("/ubicacionAnteriorColectivo")
    Call<PostUbicacionAnteriorColectivoResponse> agregarUbicacionAnteriorColectivo(@Body UbicacionAnteriorColectivo ubicacionAnteriorColectivo);

    @DELETE("/ubicacionAnteriorColectivo/{idUbicacionAnteriorColectivo}")
    Call<DeleteUbicacionAnteriorColectivoResponse> eliminarUbicacionAnteriorColectivo(@Path("idUbicacionAnteriorColectivo") int idUbicacionAnteriorColectivo);

    @PUT("/ubicacionAnteriorColectivo/{idUbicacionAnteriorColectivo}")
    Call<PutUbicacionAnteriorColectivoResponse> actualizarUbicacionAnteriorColectivo(@Path("idUbicacionAnteriorColectivo") int idUbicacionAnteriorColectivo, @Body UbicacionAnteriorColectivo ubicacionAnteriorColectivo);

    @GET("/ubicacionAnteriorColectivo/{idUbicacionAnteriorColectivo}")
    Call<GetUbicacionAnteriorColectivoResponse> obtenerUbicacionAnteriorColectivo(@Path("idUbicacionAnteriorColectivo") int idUbicacionAnteriorColectivo);

    @GET("/ubicacionAnteriorColectivo/colectivo/{idColectivo}")
    Call<GetAllUbicacionesAnterioresColectivosResponse> obtenerUbicacionesAnterioresColectivoPorColectivo(@Path("idColectivo") int idColectivo);

    @GET("/ubicacionAnteriorColectivo/recorrido/{idRecorrido}")
    Call<GetAllUbicacionesAnterioresColectivosResponse> obtenerUbicacionesAnterioresColectivosPorRecorrido(@Path("idRecorrido") int idRecorrido);

    @GET("/ubicacionAnteriorColectivo/linea/{idLineaColectivo}")
    Call<GetAllUbicacionesAnterioresColectivosResponse> obtenerUbicacionesAnterioresColectivosPorLinea(@Path("idLineaColectivo") int idLineaColectivo);

    @GET("/ubicacionAnteriorColectivo")
    Call<GetAllUbicacionesAnterioresColectivosResponse> obtenerUbicacionesAnterioresColectivos();

    // Operaciones relacionadas con la tabla UbicacionAnteriorUsuario

    @POST("/ubicacionAnteriorUsuario")
    Call<PostUbicacionAnteriorUsuarioResponse> agregarUbicacionAnteriorUsuario(@Body UbicacionAnteriorUsuario ubicacionAnteriorUsuario);

    @DELETE("/ubicacionAnteriorUsuario/{idUbicacionAnteriorUsuario}")
    Call<DeleteUbicacionAnteriorUsuarioResponse> eliminarUbicacionAnteriorUsuario(@Path("idUbicacionAnteriorUsuario") int idUbicacionAnteriorUsuario);

    @PUT("/ubicacionAnteriorUsuario/{idUbicacionAnteriorUsuario}")
    Call<PutUbicacionAnteriorUsuarioResponse> actualizarUbicacionAnteriorUsuario(@Path("idUbicacionAnteriorUsuario") int idUbicacionAnteriorUsuario, @Body UbicacionAnteriorUsuario ubicacionAnteriorUsuario);

    @GET("/ubicacionAnteriorUsuario/{idUbicacionAnteriorUsuario}")
    Call<GetUbicacionAnteriorUsuarioResponse> obtenerUbicacionAnteriorUsuario(@Path("idUbicacionAnteriorUsuario") int idUbicacionAnteriorUsuario);

    @GET("/ubicacionAnteriorUsuario/usuario/{idUsuario}")
    Call<GetAllUbicacionesAnterioresUsuariosResponse> obtenerUbicacionesAnterioresUsuariosPorUsuario(@Path("idUsuario") int idUsuario);

    @GET("/ubicacionAnteriorUsuario")
    Call<GetAllUbicacionesAnterioresUsuariosResponse> obtenerUbicacionesAnterioresUsuarios();

    // Operaciones relacionadas con la tabla Usuario

    @POST("/usuario")
    Call<PostUsuarioResponse> agregarUsuario(@Body Usuario usuario);

    @DELETE("/usuario/{idUsuario}")
    Call<DeleteUsuarioResponse> eliminarUsuario(@Path("idUsuario") int idUsuario);

    @PUT("/usuario/{idUsuario}")
    Call<PutUsuarioResponse> actualizarUsuario(@Path("idUsuario") int idUsuario, @Body Usuario usuario);

    @GET("/usuario/{idUsuario}")
    Call<GetUsuarioResponse> obtenerUsuario(@Path("idUsuario") int idUsuario);

    @GET("/usuario")
    Call<GetAllUsuariosResponse> obtenerUsuarios();

    // Operaciones relacionadas con la tabla UsuarioParada

    @POST("/usuarioParada")
    Call<PostUsuarioParadaResponse> agregarUsuarioParada(@Body UsuarioParada usuarioParada);

    @DELETE("/usuarioParada/{idUsuario}/{idParada}/{fecha}/{hora}")
    Call<DeleteUsuarioParadaResponse> eliminarUsuarioParada(@Path("idUsuario") int idUsuario, @Path("idParada") int idParada, @Path("fecha") String fecha, @Path("hora") String hora);

    @PUT("/usuarioParada/{idUsuario}/{idParada}/{fecha}/{hora}")
    Call<PutUsuarioParadaResponse> actualizarUsuarioParada(@Path("idUsuario") int idUsuario, @Path("idParada") int idParada, @Path("fecha") String fecha, @Path("hora") String hora, @Body UsuarioParada usuarioParada);

    @GET("/usuarioParada/{idUsuario}/{idParada}/{fecha}/{hora}")
    Call<GetUsuarioParadaResponse> obtenerUsuarioParada(@Path("idUsuario") int idUsuario, @Path("idParada") int idParada, @Path("fecha") String fecha, @Path("hora") String hora);

    @GET("/usuarioParada/{idUsuario}/{idParada}")
    Call<GetAllUsuariosParadasResponse> obtenerUsuariosParadasPorUsuarioyParada(@Path("idUsuario") int idUsuario, @Path("idParada") int idParada);

    @GET("/usuarioParada/{idUsuario}")
    Call<GetAllUsuariosParadasResponse> obtenerUsuariosParadasPorUsuario(@Path("idUsuario") int idUsuario);

    @GET("/usuarioParada")
    Call<GetAllUsuariosParadasResponse> obtenerUsuariosParadas();
}
