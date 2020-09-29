package com.mym.geobus.Ranking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mym.geobus.Entidades.Usuario;
import com.mym.geobus.R;

import java.util.List;

public class Adaptador extends BaseAdapter {

    private Context contexto;
    private List<Usuario> listaObjetos;
    private String nombreUsuarioActual;

    static class ViewHolder{
        private TextView txtPosicion;
        private TextView txtNombreUsuario;
        private TextView txtPuntaje;

        ViewHolder(View view){
            txtPosicion = view.findViewById(R.id.txtPosicion);
            txtNombreUsuario = view.findViewById(R.id.txtNombreUsuario);
            txtPuntaje = view.findViewById(R.id.txtPuntaje);
        }
    }

    public Adaptador(Context contexto, List<Usuario> listaObjetos, String nombreUsuarioActual) {
        this.contexto = contexto;
        this.listaObjetos = listaObjetos;
        this.nombreUsuarioActual = nombreUsuarioActual;
    }

    @Override
    public int getCount() {
        return listaObjetos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaObjetos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View vista = convertView;

        if(vista == null)
        {
            LayoutInflater inflater = LayoutInflater.from(contexto);
            vista = inflater.inflate(R.layout.item_lista,null);
            holder = new ViewHolder(vista);
            vista.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) vista.getTag();
        }

        Usuario objeto = listaObjetos.get(position);

        holder.txtPosicion.setText(String.valueOf(position+1));
        holder.txtNombreUsuario.setText(objeto.getNombre());
        holder.txtPuntaje.setText(String.valueOf(objeto.getPuntajeActual()));

        if (holder.txtNombreUsuario.getText().toString().contentEquals(nombreUsuarioActual))
        {
            holder.txtPosicion.setTypeface(null,Typeface.BOLD);
            holder.txtPosicion.setTextColor(Color.BLACK);
            holder.txtNombreUsuario.setTypeface(null,Typeface.BOLD);
            holder.txtNombreUsuario.setTextColor(Color.BLACK);
            holder.txtPuntaje.setTypeface(null,Typeface.BOLD);
            holder.txtPuntaje.setTextColor(Color.BLACK);
        }
        else
        {
            holder.txtPosicion.setTypeface(null,Typeface.NORMAL);
            holder.txtPosicion.setTextColor(Color.GRAY);
            holder.txtNombreUsuario.setTypeface(null,Typeface.NORMAL);
            holder.txtNombreUsuario.setTextColor(Color.GRAY);
            holder.txtPuntaje.setTypeface(null,Typeface.NORMAL);
            holder.txtPuntaje.setTextColor(Color.GRAY);
        }

        return vista;
    }
}
