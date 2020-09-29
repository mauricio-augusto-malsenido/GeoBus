package com.mym.geobus.ServicioExterno.Retrofit;

public class Utilidades {
    private static final String BASE_URL = "https://geobus-88ca7.appspot.com";

    public static ApiRest obtenerCliente()
    {
        return ClienteRetrofit.getCliente(BASE_URL).create(ApiRest.class);
    }
}
