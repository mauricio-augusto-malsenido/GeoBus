package com.mym.geobus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class PantallaInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_inicio);
        getSupportActionBar().hide();

        Thread splashThread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                    Intent irPantallaAutenticacion = new Intent(getApplicationContext(),PantallaAutenticacion.class);
                    startActivity(irPantallaAutenticacion);
                    finish();
                }
                catch (Exception ex) {
                    Log.e("GeoBusLog",ex.getMessage(),ex);
                }
            }
        };
        splashThread.start();
    }
}
