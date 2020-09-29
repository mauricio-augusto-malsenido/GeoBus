package com.mym.geobus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.mym.geobus.Entidades.Usuario;
import com.mym.geobus.Ranking.Adaptador;
import com.mym.geobus.ServicioExterno.Response.Usuario.GetAllUsuariosResponse;
import com.mym.geobus.ServicioExterno.Retrofit.ApiRest;
import com.mym.geobus.ServicioExterno.Retrofit.Utilidades;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PantallaRanking extends Fragment {

    ListView ranking;
    List<Usuario> usuarios;
    Usuario usuario;
    ApiRest rest;

    public PantallaRanking() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pantalla_ranking, container, false); // Se obtiene la vista correspondiente al fragmento añadido
        ranking = view.findViewById(R.id.ranking); // Se asocia la variable listview con el componente listview de la pantalla

        rest = Utilidades.obtenerCliente();

        cargarRanking(view);

        return view;
    }

    public void cargarRanking(View v)
    {
        final View view = v;
        rest.obtenerUsuarios().enqueue(new Callback<GetAllUsuariosResponse>() {
            @Override
            public void onResponse(Call<GetAllUsuariosResponse> call, Response<GetAllUsuariosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    usuarios = response.body().getUsuarios();

                    // Se ordena la colección de mayor a menor de acuerdo a los puntajes de los usuarios
                    Collections.sort(usuarios, new Comparator<Usuario>() {
                        @Override
                        public int compare(Usuario o1, Usuario o2) {
                            return Integer.compare(o2.getPuntajeActual(), o1.getPuntajeActual());
                        }
                    });

                    Adaptador adaptador = new Adaptador(view.getContext(),usuarios,usuario.getNombre()); // Se crea una nueva instancia de nuestro adaptador para el listview se le pasa como parametros el contexto y la colección de usuarios
                    ranking.setAdapter(adaptador); // Se asigna nuestro adaptador creado al componenete listview de la pantalla

                    for(int i=0;i<usuarios.size();i++)
                    {
                        if(usuarios.get(i).getNombre().contentEquals(usuario.getNombre()))
                        {
                            ranking.setSelection(i); // Enfocamos la vista del listview en la posición del usuario actual
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllUsuariosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }
}
