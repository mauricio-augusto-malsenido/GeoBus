package com.mym.geobus.ServicioExterno;

import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
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
import com.mym.geobus.PantallaAutenticacion;
import com.mym.geobus.PantallaPrincipal;
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
import com.mym.geobus.ServicioExterno.Retrofit.ApiRest;
import com.mym.geobus.ServicioExterno.Retrofit.Utilidades;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicioExterno {

    private ApiRest rest;

    public ServicioExterno()
    {
        rest = Utilidades.obtenerCliente();
    }

    //Métodos para ejecutar las operaciones para la tabla Colectivo

    public void agregarColectivo(Colectivo colectivo)
    {
        rest.agregarColectivo(colectivo).enqueue(new Callback<PostColectivoResponse>() {
            @Override
            public void onResponse(Call<PostColectivoResponse> call, Response<PostColectivoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PostColectivoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void eliminarColectivo(int idColectivo)
    {
        rest.eliminarColectivo(idColectivo).enqueue(new Callback<DeleteColectivoResponse>() {
            @Override
            public void onResponse(Call<DeleteColectivoResponse> call, Response<DeleteColectivoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<DeleteColectivoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void actualizarColectivo(int idColectivo, Colectivo colectivo)
    {
        rest.actualizarColectivo(idColectivo, colectivo).enqueue(new Callback<PutColectivoResponse>() {
            @Override
            public void onResponse(Call<PutColectivoResponse> call, Response<PutColectivoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PutColectivoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public Colectivo obtenerColectivo(int idColectivo)
    {
        final List<Colectivo> colectivos = new ArrayList<>();
        rest.obtenerColectivo(idColectivo).enqueue(new Callback<GetColectivoResponse>() {
            @Override
            public void onResponse(Call<GetColectivoResponse> call, Response<GetColectivoResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body()));
                    colectivos.add(response.body().getColectivo());
                }
            }

            @Override
            public void onFailure(Call<GetColectivoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return colectivos.get(0);
    }

    public List<Colectivo> obtenerColectivosPorRecorrido(int idRecorrido)
    {
        final List<Colectivo> colectivos = new ArrayList<>();
        rest.obtenerColectivosPorRecorrido(idRecorrido).enqueue(new Callback<GetAllColectivosResponse>() {
            @Override
            public void onResponse(Call<GetAllColectivosResponse> call, Response<GetAllColectivosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getColectivos().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getColectivos().get(i)));
                        colectivos.add(response.body().getColectivos().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllColectivosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return colectivos;
    }

    public List<Colectivo> obtenerColectivosPorLinea(int idLineaColectivo)
    {
        final List<Colectivo> colectivos = new ArrayList<>();
        rest.obtenerColectivosPorLinea(idLineaColectivo).enqueue(new Callback<GetAllColectivosResponse>() {
            @Override
            public void onResponse(Call<GetAllColectivosResponse> call, Response<GetAllColectivosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getColectivos().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getColectivos().get(i)));
                        colectivos.add(response.body().getColectivos().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllColectivosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return colectivos;
    }

    public List<Colectivo> obtenerColectivos()
    {
        final List<Colectivo> colectivos = new ArrayList<>();
        rest.obtenerColectivos().enqueue(new Callback<GetAllColectivosResponse>() {
            @Override
            public void onResponse(Call<GetAllColectivosResponse> call, Response<GetAllColectivosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getColectivos().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getColectivos().get(i)));
                        colectivos.add(response.body().getColectivos().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllColectivosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return colectivos;
    }

    //Métodos para ejecutar las operaciones para la tabla LineaColectivo

    public void agregarLineaColectivo(LineaColectivo lineaColectivo)
    {
        rest.agregarLineaColectivo(lineaColectivo).enqueue(new Callback<PostLineaColectivoResponse>() {
            @Override
            public void onResponse(Call<PostLineaColectivoResponse> call, Response<PostLineaColectivoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PostLineaColectivoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void eliminarLineaColectivo(int idLineaColectivo)
    {
        rest.eliminarLineaColectivo(idLineaColectivo).enqueue(new Callback<DeleteLineaColectivoResponse>() {
            @Override
            public void onResponse(Call<DeleteLineaColectivoResponse> call, Response<DeleteLineaColectivoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<DeleteLineaColectivoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void actualizarLineaColectivo(int idLineaColectivo, LineaColectivo lineaColectivo)
    {
        rest.actualizarLineaColectivo(idLineaColectivo, lineaColectivo).enqueue(new Callback<PutLineaColectivoResponse>() {
            @Override
            public void onResponse(Call<PutLineaColectivoResponse> call, Response<PutLineaColectivoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PutLineaColectivoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public LineaColectivo obtenerLineaColectivoConId(int idLineaColectivo)
    {
        final List<LineaColectivo> lineasColectivos = new ArrayList<>();
        rest.obtenerLineaColectivoConId(idLineaColectivo).enqueue(new Callback<GetLineaColectivoResponse>() {
            @Override
            public void onResponse(Call<GetLineaColectivoResponse> call, Response<GetLineaColectivoResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body()));
                    lineasColectivos.add(response.body().getLineaColectivo());
                }
            }

            @Override
            public void onFailure(Call<GetLineaColectivoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return lineasColectivos.get(0);
    }

    public LineaColectivo obtenerLineaColectivoConNombre(String nombre)
    {
        final List<LineaColectivo> lineasColectivos = new ArrayList<>();
        rest.obtenerLineaColectivoConNombre(nombre).enqueue(new Callback<GetLineaColectivoResponse>() {
            @Override
            public void onResponse(Call<GetLineaColectivoResponse> call, Response<GetLineaColectivoResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body()));
                    lineasColectivos.add(response.body().getLineaColectivo());
                }
            }

            @Override
            public void onFailure(Call<GetLineaColectivoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return lineasColectivos.get(0);
    }

    //Métodos para ejecutar las operaciones para la tabla Parada

    public void agregarParada(Parada parada)
    {
        rest.agregarParada(parada).enqueue(new Callback<PostParadaResponse>() {
            @Override
            public void onResponse(Call<PostParadaResponse> call, Response<PostParadaResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PostParadaResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void eliminarParada(int idParada)
    {
        rest.eliminarParada(idParada).enqueue(new Callback<DeleteParadaResponse>() {
            @Override
            public void onResponse(Call<DeleteParadaResponse> call, Response<DeleteParadaResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<DeleteParadaResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void actualizarParada(int idParada, Parada parada)
    {
        rest.actualizarParada(idParada, parada).enqueue(new Callback<PutParadaResponse>() {
            @Override
            public void onResponse(Call<PutParadaResponse> call, Response<PutParadaResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PutParadaResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public Parada obtenerParada(int idParada)
    {
        final List<Parada> paradas = new ArrayList<>();
        rest.obtenerParada(idParada).enqueue(new Callback<GetParadaResponse>() {
            @Override
            public void onResponse(Call<GetParadaResponse> call, Response<GetParadaResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body()));
                    paradas.add(response.body().getParada());
                }
            }

            @Override
            public void onFailure(Call<GetParadaResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return paradas.get(0);
    }

    public List<Parada> obtenerParadasPorRecorrido(int idRecorrido)
    {
        final List<Parada> paradas = new ArrayList<>();
        rest.obtenerParadasPorRecorrido(idRecorrido).enqueue(new Callback<GetAllParadasResponse>() {
            @Override
            public void onResponse(Call<GetAllParadasResponse> call, Response<GetAllParadasResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getParadas().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getParadas().get(i)));
                        paradas.add(response.body().getParadas().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllParadasResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return paradas;
    }

    public List<Parada> obtenerParadasPorLinea(int idLineaColectivo)
    {
        final List<Parada> paradas = new ArrayList<>();
        rest.obtenerParadasPorLinea(idLineaColectivo).enqueue(new Callback<GetAllParadasResponse>() {
            @Override
            public void onResponse(Call<GetAllParadasResponse> call, Response<GetAllParadasResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getParadas().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getParadas().get(i)));
                        paradas.add(response.body().getParadas().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllParadasResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return paradas;
    }

    //Métodos para ejecutar las operaciones para la tabla PuntoGeografico

    public void agregarPuntoGeografico(PuntoGeografico puntoGeografico)
    {
        rest.agregarPuntoGeografico(puntoGeografico).enqueue(new Callback<PostPuntoGeograficoResponse>() {
            @Override
            public void onResponse(Call<PostPuntoGeograficoResponse> call, Response<PostPuntoGeograficoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PostPuntoGeograficoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void eliminarPuntoGeografico(int idPuntoGeografico)
    {
        rest.eliminarPuntoGeografico(idPuntoGeografico).enqueue(new Callback<DeletePuntoGeograficoResponse>() {
            @Override
            public void onResponse(Call<DeletePuntoGeograficoResponse> call, Response<DeletePuntoGeograficoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<DeletePuntoGeograficoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void actualizarPuntoGeografico(int idPuntoGeografico, PuntoGeografico puntoGeografico)
    {
        rest.actualizarPuntoGeografico(idPuntoGeografico, puntoGeografico).enqueue(new Callback<PutPuntoGeograficoResponse>() {
            @Override
            public void onResponse(Call<PutPuntoGeograficoResponse> call, Response<PutPuntoGeograficoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PutPuntoGeograficoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public PuntoGeografico obtenerPuntoGeograficoPorId(int idPuntoGeografico)
    {
        final List<PuntoGeografico> puntosGeograficos = new ArrayList<>();
        rest.obtenerPuntoGeograficoPorId(idPuntoGeografico).enqueue(new Callback<GetPuntoGeograficoResponse>() {
            @Override
            public void onResponse(Call<GetPuntoGeograficoResponse> call, Response<GetPuntoGeograficoResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body()));
                    puntosGeograficos.add(response.body().getPuntoGeografico());
                }
            }

            @Override
            public void onFailure(Call<GetPuntoGeograficoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return puntosGeograficos.get(0);
    }

    public PuntoGeografico obtenerPuntoGeograficoPorPosicionyRecorrido(int idRecorrido, double latitud, double longitud)
    {
        final List<PuntoGeografico> puntosGeograficos = new ArrayList<>();
        rest.obtenerPuntoGeograficoPorPosicionyRecorrido(idRecorrido, latitud, longitud).enqueue(new Callback<GetPuntoGeograficoResponse>() {
            @Override
            public void onResponse(Call<GetPuntoGeograficoResponse> call, Response<GetPuntoGeograficoResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body()));
                    puntosGeograficos.add(response.body().getPuntoGeografico());
                }
            }

            @Override
            public void onFailure(Call<GetPuntoGeograficoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return puntosGeograficos.get(0);
    }

    public List<PuntoGeografico> obtenerPuntosGeograficosPorRecorrido(int idRecorrido)
    {
        final List<PuntoGeografico> puntosGeograficos = new ArrayList<>();
        rest.obtenerPuntosGeograficosPorRecorrido(idRecorrido).enqueue(new Callback<GetAllPuntosGeograficosResponse>() {
            @Override
            public void onResponse(Call<GetAllPuntosGeograficosResponse> call, Response<GetAllPuntosGeograficosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getPuntosGeograficos().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getPuntosGeograficos().get(i)));
                        puntosGeograficos.add(response.body().getPuntosGeograficos().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllPuntosGeograficosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return puntosGeograficos;
    }

    //Métodos para ejecutar las operaciones para la tabla Recorrido

    public void agregarRecorrido(Recorrido recorrido)
    {
        rest.agregarRecorrido(recorrido).enqueue(new Callback<PostRecorridoResponse>() {
            @Override
            public void onResponse(Call<PostRecorridoResponse> call, Response<PostRecorridoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PostRecorridoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void eliminarRecorrido(int idRecorrido)
    {
        rest.eliminarRecorrido(idRecorrido).enqueue(new Callback<DeleteRecorridoResponse>() {
            @Override
            public void onResponse(Call<DeleteRecorridoResponse> call, Response<DeleteRecorridoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<DeleteRecorridoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void actualizarRecorrido(int idRecorrido, Recorrido recorrido)
    {
        rest.actualizarRecorrido(idRecorrido, recorrido).enqueue(new Callback<PutRecorridoResponse>() {
            @Override
            public void onResponse(Call<PutRecorridoResponse> call, Response<PutRecorridoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PutRecorridoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public Recorrido obtenerRecorridoPorId(int idRecorrido)
    {
        final List<Recorrido> recorridos = new ArrayList<>();
        rest.obtenerRecorridoPorId(idRecorrido).enqueue(new Callback<GetRecorridoResponse>() {
            @Override
            public void onResponse(Call<GetRecorridoResponse> call, Response<GetRecorridoResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body()));
                    recorridos.add(response.body().getRecorrido());
                }
            }

            @Override
            public void onFailure(Call<GetRecorridoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return recorridos.get(0);
    }

    public Recorrido obtenerRecorridoPorNombre(String nombre)
    {
        final List<Recorrido> recorridos = new ArrayList<>();
        rest.obtenerRecorridoPorNombre(nombre).enqueue(new Callback<GetRecorridoResponse>() {
            @Override
            public void onResponse(Call<GetRecorridoResponse> call, Response<GetRecorridoResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body()));
                    recorridos.add(response.body().getRecorrido());
                }
            }

            @Override
            public void onFailure(Call<GetRecorridoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return recorridos.get(0);
    }

    public List<Recorrido> obtenerRecorridosPorLinea(int idLineaColectivo)
    {
        final List<Recorrido> recorridos = new ArrayList<>();
        rest.obtenerRecorridosPorLinea(idLineaColectivo).enqueue(new Callback<GetAllRecorridosResponse>() {
            @Override
            public void onResponse(Call<GetAllRecorridosResponse> call, Response<GetAllRecorridosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getRecorridos().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getRecorridos().get(i)));
                        recorridos.add(response.body().getRecorridos().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllRecorridosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return recorridos;
    }

    //Métodos para ejecutar las operaciones para la tabla Transmision

    public void agregarTransmision(Transmision transmision)
    {
        rest.agregarTransmision(transmision).enqueue(new Callback<PostTransmisionResponse>() {
            @Override
            public void onResponse(Call<PostTransmisionResponse> call, Response<PostTransmisionResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PostTransmisionResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void eliminarTransmision(int idTransmision)
    {
        rest.eliminarTransmision(idTransmision).enqueue(new Callback<DeleteTransmisionResponse>() {
            @Override
            public void onResponse(Call<DeleteTransmisionResponse> call, Response<DeleteTransmisionResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<DeleteTransmisionResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void actualizarTransmision(int idTransmision, Transmision transmision)
    {
        rest.actualizarTransmision(idTransmision, transmision).enqueue(new Callback<PutTransmisionResponse>() {
            @Override
            public void onResponse(Call<PutTransmisionResponse> call, Response<PutTransmisionResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PutTransmisionResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public Transmision obtenerTransmision(int idTransmision)
    {
        final List<Transmision> transmisiones = new ArrayList<>();
        rest.obtenerTransmision(idTransmision).enqueue(new Callback<GetTransmisionResponse>() {
            @Override
            public void onResponse(Call<GetTransmisionResponse> call, Response<GetTransmisionResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body()));
                    transmisiones.add(response.body().getTransmision());
                }
            }

            @Override
            public void onFailure(Call<GetTransmisionResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return transmisiones.get(0);
    }

    public List<Transmision> obtenerTransmisionesPorUsuarioyColectivo(int idUsuario, int idColectivo)
    {
        final List<Transmision> transmisiones = new ArrayList<>();
        rest.obtenerTransmisionesPorUsuarioyColectivo(idUsuario, idColectivo).enqueue(new Callback<GetAllTransmisionesResponse>() {
            @Override
            public void onResponse(Call<GetAllTransmisionesResponse> call, Response<GetAllTransmisionesResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getTransmisiones().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getTransmisiones().get(i)));
                        transmisiones.add(response.body().getTransmisiones().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllTransmisionesResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return transmisiones;
    }

    public List<Transmision> obtenerTransmisionesPorUsuario(int idUsuario)
    {
        final List<Transmision> transmisiones = new ArrayList<>();
        rest.obtenerTransmisionesPorUsuario(idUsuario).enqueue(new Callback<GetAllTransmisionesResponse>() {
            @Override
            public void onResponse(Call<GetAllTransmisionesResponse> call, Response<GetAllTransmisionesResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getTransmisiones().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getTransmisiones().get(i)));
                        transmisiones.add(response.body().getTransmisiones().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllTransmisionesResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return transmisiones;
    }

    public List<Transmision> obtenerTransmisionesPorColectivo(int idColectivo)
    {
        final List<Transmision> transmisiones = new ArrayList<>();
        rest.obtenerTransmisionesPorColectivo(idColectivo).enqueue(new Callback<GetAllTransmisionesResponse>() {
            @Override
            public void onResponse(Call<GetAllTransmisionesResponse> call, Response<GetAllTransmisionesResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getTransmisiones().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getTransmisiones().get(i)));
                        transmisiones.add(response.body().getTransmisiones().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllTransmisionesResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return transmisiones;
    }

    public List<Transmision> obtenerTransmisiones()
    {
        final List<Transmision> transmisiones = new ArrayList<>();
        rest.obtenerTransmisiones().enqueue(new Callback<GetAllTransmisionesResponse>() {
            @Override
            public void onResponse(Call<GetAllTransmisionesResponse> call, Response<GetAllTransmisionesResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getTransmisiones().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getTransmisiones().get(i)));
                        transmisiones.add(response.body().getTransmisiones().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllTransmisionesResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return transmisiones;
    }

    //Métodos para ejecutar las operaciones para la tabla UbicacionAnteriorColectivo

    public void agregarUbicacionAnteriorColectivo(UbicacionAnteriorColectivo ubicacionAnteriorColectivo)
    {
        rest.agregarUbicacionAnteriorColectivo(ubicacionAnteriorColectivo).enqueue(new Callback<PostUbicacionAnteriorColectivoResponse>() {
            @Override
            public void onResponse(Call<PostUbicacionAnteriorColectivoResponse> call, Response<PostUbicacionAnteriorColectivoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PostUbicacionAnteriorColectivoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void eliminarUbicacionAnteriorColectivo(int idUbicacionAnteriorColectivo)
    {
        rest.eliminarUbicacionAnteriorColectivo(idUbicacionAnteriorColectivo).enqueue(new Callback<DeleteUbicacionAnteriorColectivoResponse>() {
            @Override
            public void onResponse(Call<DeleteUbicacionAnteriorColectivoResponse> call, Response<DeleteUbicacionAnteriorColectivoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<DeleteUbicacionAnteriorColectivoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void actualizarUbicacionAnteriorColectivo(int idUbicacionAnteriorColectivo, UbicacionAnteriorColectivo ubicacionAnteriorColectivo)
    {
        rest.actualizarUbicacionAnteriorColectivo(idUbicacionAnteriorColectivo, ubicacionAnteriorColectivo).enqueue(new Callback<PutUbicacionAnteriorColectivoResponse>() {
            @Override
            public void onResponse(Call<PutUbicacionAnteriorColectivoResponse> call, Response<PutUbicacionAnteriorColectivoResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PutUbicacionAnteriorColectivoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public UbicacionAnteriorColectivo obtenerUbicacionAnteriorColectivo(int idUbicacionAnteriorColectivo)
    {
        final List<UbicacionAnteriorColectivo> ubicacionesAnterioresColectivos = new ArrayList<>();
        rest.obtenerUbicacionAnteriorColectivo(idUbicacionAnteriorColectivo).enqueue(new Callback<GetUbicacionAnteriorColectivoResponse>() {
            @Override
            public void onResponse(Call<GetUbicacionAnteriorColectivoResponse> call, Response<GetUbicacionAnteriorColectivoResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body()));
                    ubicacionesAnterioresColectivos.add(response.body().getUbicacionAnteriorColectivo());
                }
            }

            @Override
            public void onFailure(Call<GetUbicacionAnteriorColectivoResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return ubicacionesAnterioresColectivos.get(0);
    }

    public List<UbicacionAnteriorColectivo> obtenerUbicacionesAnterioresColectivoPorColectivo(int idColectivo)
    {
        final List<UbicacionAnteriorColectivo> ubicacionesAnterioresColectivos = new ArrayList<>();
        rest.obtenerUbicacionesAnterioresColectivoPorColectivo(idColectivo).enqueue(new Callback<GetAllUbicacionesAnterioresColectivosResponse>() {
            @Override
            public void onResponse(Call<GetAllUbicacionesAnterioresColectivosResponse> call, Response<GetAllUbicacionesAnterioresColectivosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getUbicacionesAnterioresColectivos().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getUbicacionesAnterioresColectivos().get(i)));
                        ubicacionesAnterioresColectivos.add(response.body().getUbicacionesAnterioresColectivos().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllUbicacionesAnterioresColectivosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return ubicacionesAnterioresColectivos;
    }

    public List<UbicacionAnteriorColectivo> obtenerUbicacionesAnterioresColectivosPorRecorrido(int idRecorrido)
    {
        final List<UbicacionAnteriorColectivo> ubicacionesAnterioresColectivos = new ArrayList<>();
        rest.obtenerUbicacionesAnterioresColectivosPorRecorrido(idRecorrido).enqueue(new Callback<GetAllUbicacionesAnterioresColectivosResponse>() {
            @Override
            public void onResponse(Call<GetAllUbicacionesAnterioresColectivosResponse> call, Response<GetAllUbicacionesAnterioresColectivosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getUbicacionesAnterioresColectivos().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getUbicacionesAnterioresColectivos().get(i)));
                        ubicacionesAnterioresColectivos.add(response.body().getUbicacionesAnterioresColectivos().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllUbicacionesAnterioresColectivosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return ubicacionesAnterioresColectivos;
    }

    public List<UbicacionAnteriorColectivo> obtenerUbicacionesAnterioresColectivosPorLinea(int idLineaColectivo)
    {
        final List<UbicacionAnteriorColectivo> ubicacionesAnterioresColectivos = new ArrayList<>();
        rest.obtenerUbicacionesAnterioresColectivosPorLinea(idLineaColectivo).enqueue(new Callback<GetAllUbicacionesAnterioresColectivosResponse>() {
            @Override
            public void onResponse(Call<GetAllUbicacionesAnterioresColectivosResponse> call, Response<GetAllUbicacionesAnterioresColectivosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getUbicacionesAnterioresColectivos().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getUbicacionesAnterioresColectivos().get(i)));
                        ubicacionesAnterioresColectivos.add(response.body().getUbicacionesAnterioresColectivos().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllUbicacionesAnterioresColectivosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return ubicacionesAnterioresColectivos;
    }

    public List<UbicacionAnteriorColectivo> obtenerUbicacionesAnterioresColectivos()
    {
        final List<UbicacionAnteriorColectivo> ubicacionesAnterioresColectivos = new ArrayList<>();
        rest.obtenerUbicacionesAnterioresColectivos().enqueue(new Callback<GetAllUbicacionesAnterioresColectivosResponse>() {
            @Override
            public void onResponse(Call<GetAllUbicacionesAnterioresColectivosResponse> call, Response<GetAllUbicacionesAnterioresColectivosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getUbicacionesAnterioresColectivos().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getUbicacionesAnterioresColectivos().get(i)));
                        ubicacionesAnterioresColectivos.add(response.body().getUbicacionesAnterioresColectivos().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllUbicacionesAnterioresColectivosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return ubicacionesAnterioresColectivos;
    }

    //Métodos para ejecutar las operaciones para la tabla UbicacionAnteriorUsuario

    public void eliminarUbicacionAnteriorUsuario(int idUbicacionAnteriorUsuario)
    {
        rest.eliminarUbicacionAnteriorUsuario(idUbicacionAnteriorUsuario).enqueue(new Callback<DeleteUbicacionAnteriorUsuarioResponse>() {
            @Override
            public void onResponse(Call<DeleteUbicacionAnteriorUsuarioResponse> call, Response<DeleteUbicacionAnteriorUsuarioResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<DeleteUbicacionAnteriorUsuarioResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public UbicacionAnteriorUsuario obtenerUbicacionAnteriorUsuario(int idUbicacionAnteriorUsuario)
    {
        final List<UbicacionAnteriorUsuario> ubicacionesAnterioresUsuarios = new ArrayList<>();
        rest.obtenerUbicacionAnteriorUsuario(idUbicacionAnteriorUsuario).enqueue(new Callback<GetUbicacionAnteriorUsuarioResponse>() {
            @Override
            public void onResponse(Call<GetUbicacionAnteriorUsuarioResponse> call, Response<GetUbicacionAnteriorUsuarioResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body()));
                    ubicacionesAnterioresUsuarios.add(response.body().getUbicacionAnteriorUsuario());
                }
            }

            @Override
            public void onFailure(Call<GetUbicacionAnteriorUsuarioResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return ubicacionesAnterioresUsuarios.get(0);
    }

    public List<UbicacionAnteriorUsuario> obtenerUbicacionesAnterioresUsuariosPorUsuario(int idUsuario)
    {
        final List<UbicacionAnteriorUsuario> ubicacionesAnterioresUsuarios = new ArrayList<>();
        rest.obtenerUbicacionesAnterioresUsuariosPorUsuario(idUsuario).enqueue(new Callback<GetAllUbicacionesAnterioresUsuariosResponse>() {
            @Override
            public void onResponse(Call<GetAllUbicacionesAnterioresUsuariosResponse> call, Response<GetAllUbicacionesAnterioresUsuariosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getUbicacionesAnterioresUsuarios().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getUbicacionesAnterioresUsuarios().get(i)));
                        ubicacionesAnterioresUsuarios.add(response.body().getUbicacionesAnterioresUsuarios().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllUbicacionesAnterioresUsuariosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return ubicacionesAnterioresUsuarios;
    }

    public List<UbicacionAnteriorUsuario> obtenerUbicacionesAnterioresUsuarios()
    {
        final List<UbicacionAnteriorUsuario> ubicacionesAnterioresUsuarios = new ArrayList<>();

        return ubicacionesAnterioresUsuarios;
    }

    //Métodos para ejecutar las operaciones para la tabla Usuario

    public void eliminarUsuario(int idUsuario)
    {
        rest.eliminarUsuario(idUsuario).enqueue(new Callback<DeleteUsuarioResponse>() {
            @Override
            public void onResponse(Call<DeleteUsuarioResponse> call, Response<DeleteUsuarioResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<DeleteUsuarioResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public Usuario obtenerUsuario(int idUsuario)
    {
        final List<Usuario> usuarios = new ArrayList<>();
        rest.obtenerUsuario(idUsuario).enqueue(new Callback<GetUsuarioResponse>() {
            @Override
            public void onResponse(Call<GetUsuarioResponse> call, Response<GetUsuarioResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body()));
                    usuarios.add(response.body().getUsuario());
                }
            }

            @Override
            public void onFailure(Call<GetUsuarioResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return usuarios.get(0);
    }

    //Métodos para ejecutar las operaciones para la tabla UsuarioParada

    public void eliminarUsuarioParada(int idUsuario, int idParada, String fecha, String hora)
    {
        rest.eliminarUsuarioParada(idUsuario, idParada, fecha, hora).enqueue(new Callback<DeleteUsuarioParadaResponse>() {
            @Override
            public void onResponse(Call<DeleteUsuarioParadaResponse> call, Response<DeleteUsuarioParadaResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<DeleteUsuarioParadaResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public UsuarioParada obtenerUsuarioParada(int idUsuario, int idParada, String fecha, String hora)
    {
        final List<UsuarioParada> usuariosParadas = new ArrayList<>();
        rest.obtenerUsuarioParada(idUsuario, idParada, fecha, hora).enqueue(new Callback<GetUsuarioParadaResponse>() {
            @Override
            public void onResponse(Call<GetUsuarioParadaResponse> call, Response<GetUsuarioParadaResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body()));
                    usuariosParadas.add(response.body().getUsuarioParada());
                }
            }

            @Override
            public void onFailure(Call<GetUsuarioParadaResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return usuariosParadas.get(0);
    }

    public List<UsuarioParada> obtenerUsuariosParadasPorUsuarioyParada(int idUsuario, int idParada)
    {
        final List<UsuarioParada> usuariosParadas = new ArrayList<>();
        rest.obtenerUsuariosParadasPorUsuarioyParada(idUsuario, idParada).enqueue(new Callback<GetAllUsuariosParadasResponse>() {
            @Override
            public void onResponse(Call<GetAllUsuariosParadasResponse> call, Response<GetAllUsuariosParadasResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getUsuariosParadas().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getUsuariosParadas().get(i)));
                        usuariosParadas.add(response.body().getUsuariosParadas().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllUsuariosParadasResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return usuariosParadas;
    }

    public List<UsuarioParada> obtenerUsuariosParadasPorUsuario(int idUsuario)
    {
        final List<UsuarioParada> usuariosParadas = new ArrayList<>();
        rest.obtenerUsuariosParadasPorUsuario(idUsuario).enqueue(new Callback<GetAllUsuariosParadasResponse>() {
            @Override
            public void onResponse(Call<GetAllUsuariosParadasResponse> call, Response<GetAllUsuariosParadasResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    for (int i=0;i<response.body().getUsuariosParadas().size();i++)
                    {
                        Log.i("GeoBusLog",gson.toJson(response.body().getUsuariosParadas().get(i)));
                        usuariosParadas.add(response.body().getUsuariosParadas().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllUsuariosParadasResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
        return usuariosParadas;
    }
}
