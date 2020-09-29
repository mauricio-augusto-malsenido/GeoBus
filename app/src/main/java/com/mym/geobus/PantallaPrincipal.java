package com.mym.geobus;

import android.Manifest;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.mym.geobus.Autenticacion.AdministradorDeSesion;
import com.mym.geobus.BaseDeDatosLocal.ColectivoDAO;
import com.mym.geobus.BaseDeDatosLocal.LineaColectivoDAO;
import com.mym.geobus.BaseDeDatosLocal.ParadaDAO;
import com.mym.geobus.BaseDeDatosLocal.PuntoGeograficoDAO;
import com.mym.geobus.BaseDeDatosLocal.RecorridoDAO;
import com.mym.geobus.BaseDeDatosLocal.TransmisionDAO;
import com.mym.geobus.BaseDeDatosLocal.UsuarioDAO;
import com.mym.geobus.BaseDeDatosLocal.UsuarioParadaDAO;
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
import com.mym.geobus.ServicioExterno.Response.Colectivo.GetAllColectivosResponse;
import com.mym.geobus.ServicioExterno.Response.Colectivo.PostColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.Colectivo.PutColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.LineaColectivo.GetAllLineasColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.Parada.GetAllParadasResponse;
import com.mym.geobus.ServicioExterno.Response.PuntoGeografico.GetAllPuntosGeograficosResponse;
import com.mym.geobus.ServicioExterno.Response.Recorrido.GetAllRecorridosResponse;
import com.mym.geobus.ServicioExterno.Response.Transmision.GetAllTransmisionesResponse;
import com.mym.geobus.ServicioExterno.Response.Transmision.PostTransmisionResponse;
import com.mym.geobus.ServicioExterno.Response.Transmision.PutTransmisionResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorColectivo.GetAllUbicacionesAnterioresColectivosResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorColectivo.PostUbicacionAnteriorColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorColectivo.PutUbicacionAnteriorColectivoResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorUsuario.GetAllUbicacionesAnterioresUsuariosResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorUsuario.PostUbicacionAnteriorUsuarioResponse;
import com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorUsuario.PutUbicacionAnteriorUsuarioResponse;
import com.mym.geobus.ServicioExterno.Response.Usuario.PutUsuarioResponse;
import com.mym.geobus.ServicioExterno.Response.UsuarioParada.GetAllUsuariosParadasResponse;
import com.mym.geobus.ServicioExterno.Response.UsuarioParada.PostUsuarioParadaResponse;
import com.mym.geobus.ServicioExterno.Response.UsuarioParada.PutUsuarioParadaResponse;
import com.mym.geobus.ServicioExterno.Retrofit.ApiRest;
import com.mym.geobus.ServicioExterno.Retrofit.Utilidades;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PantallaPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback{

    // Variables relacionadas con lo mostrado en el mapa
    GoogleMap map;
    LatLng miPosicion;
    float miVelocidad;
    NavigationView navigationView;
    ArrayList<Circle> circles;
    Bitmap iconoParada, iconoColectivo, iconoMiPosicion;
    Marker marcadorMiPosicion;
    ArrayList<Marker> marcadoresColectivos;
    Polyline recorridoMapa;
    FloatingActionButton btnMiUbicacion;
    boolean Lineas = false;
    boolean L5 = false;
    boolean L18 = false;
    boolean miUbicacionActivada = false;
    boolean estuveEnLaParada = false;
    boolean estoyFueraDeLaParada = false;
    boolean estoyEnElColectivo = false;

    // Variables relacionadas con la cuenta de usuario o la sesión
    AdministradorDeSesion adminSesion;
    Usuario usuario;
    TextView txtNombreUsuario;
    TextView txtEmailUsuario;

    // Variables para el manejo de los fragments
    FragmentManager manager;
    SupportMapFragment mapFragment;
    PantallaRanking pantallaRanking;
    AlertDialog.Builder seleccionarLinea;
    AlertDialog.Builder seleccionarRecorrido;

    // Variables relacionadas con el manejo y la obtención de información de la bases de datos local
    UsuarioDAO usuarioDAO;
    LineaColectivoDAO lineaColectivoDAO;
    RecorridoDAO recorridoDAO;
    PuntoGeograficoDAO puntoGeograficoDAO;
    ParadaDAO paradaDAO;
    UsuarioParadaDAO usuarioParadaDAO;
    ColectivoDAO colectivoDAO;
    TransmisionDAO transmisionDAO;

    // Variables relacionadas con el manejo y la obtención de información del servicio nodejs
    ApiRest rest;

    // Variables utilizadas para el almacenamiento temporal de información u otras utilidades
    List<Parada> paradasPorRecorrido;
    List<Parada> todasLasParadas;
    List<Recorrido> recorridosPorLinea;
    List<Recorrido> todosLosRecorridos;
    List<LineaColectivo> lineas;
    List<PuntoGeografico> puntosGeograficosPorRecorrido;
    List<Colectivo> todosLosColectivos;
    List<Colectivo> todosLosColectivosPorRecorrido;
    List<Transmision> transmisiones;
    Colectivo colectivo = null;
    LineaColectivo linea = null;
    Recorrido recorrido = null;
    Transmision transmision = null;

    // Variables para dar formatos a fechas y horas
    DateFormat formatoFecha;
    DateFormat formatoHora;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cargando la vista de la pantalla principal
        setContentView(R.layout.pantalla_principal);

        // Creación del menú en la barra
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Creación del menú principal
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        manager = getSupportFragmentManager(); // Obtenemos instancia del manejador de fragmentos

        // Cargando el mapa
        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Cargando vista inicial y forma de interacción del menú principal
        navigationView.getMenu().findItem(R.id.nav_todos).setChecked(true);
        navigationView.getMenu().findItem(R.id.nav_lineas).setCheckable(false);
        for (int i=2;i<navigationView.getMenu().size()-1;i++)
        {
            if(navigationView.getMenu().getItem(i).getTitle().toString().contains("Linea"))
            {
                navigationView.getMenu().getItem(i).setCheckable(false);
            }
        }

        // Referenciamos la variable correspondiente al botón "Mi Ubicación" de la vista
        btnMiUbicacion = findViewById(R.id.btnMiUbicacion);

        // Referenciando las variables que se asociarán con los componentes texto de la vista, los cuales son el nombre e email del usuario que se muestran en la parte superior del menu principal
        txtNombreUsuario = navigationView.getHeaderView(0).findViewById(R.id.txtNombreUsuario);
        txtEmailUsuario = navigationView.getHeaderView(0).findViewById(R.id.txtEmailUsuario);

        // Instanciamos las clases DAO para realizar operaciones con la base de datos local
        usuarioDAO = new UsuarioDAO(getApplicationContext());
        lineaColectivoDAO = new LineaColectivoDAO(getApplicationContext());
        recorridoDAO = new RecorridoDAO(getApplicationContext());
        puntoGeograficoDAO = new PuntoGeograficoDAO(getApplicationContext());
        paradaDAO = new ParadaDAO(getApplicationContext());
        usuarioParadaDAO = new UsuarioParadaDAO(getApplicationContext());
        colectivoDAO = new ColectivoDAO(getApplicationContext());
        transmisionDAO = new TransmisionDAO(getApplicationContext());

        // Instanciamos el ApiRest para realizar operaciones con la base de datos externa
        rest = Utilidades.obtenerCliente();

        cargarTodasLasLineas();
        cargarTodosLosRecorridos();

        // Indicamos los formatos de fecha y hora que se utilizaran
        formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        formatoHora = new SimpleDateFormat("HH:mm:ss");

        // Obtenemos manejador de sesión y el usuario que esta autenticado, luego cargamos su nombre e email en los textos de la parte superior del menu principal
        adminSesion = new AdministradorDeSesion(getApplicationContext());
        usuario = usuarioDAO.obtenerUsuarioPorId(adminSesion.obtenerUsuario().getIdUsuario());
        txtNombreUsuario.setText(usuario.getNombre());
        txtEmailUsuario.setText(usuario.getEmail());

        // Creamos los iconos para los diferentes marcadores que se mostrarán en pantalla
        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.bus_stop1);
        Bitmap b = bitmapdraw.getBitmap();
        iconoParada = Bitmap.createScaledBitmap(b, 100, 95, false);

        bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.bus1);
        b = bitmapdraw.getBitmap();
        iconoColectivo = Bitmap.createScaledBitmap(b, 50, 50, false);

        bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.blue_circle2);
        b = bitmapdraw.getBitmap();
        iconoMiPosicion = Bitmap.createScaledBitmap(b, 30, 30, false);
    }

    // Sobreescribir la función que se ejecuta al presionar el botón atrás
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
        }
    }

    // Función que carga el menu de opciones de la barra
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void mostrarLineas()
    {
        Lineas = true;
        for (int i=2; i<navigationView.getMenu().size()-1; i++)
        {
            for (int j=0; j<lineas.size(); j++)
            {
                if(navigationView.getMenu().getItem(i).getTitle().toString().contentEquals(lineas.get(j).getNombre()))
                {
                    navigationView.getMenu().getItem(i).setVisible(true);
                }
            }
        }
    }

    public void mostrarRecorridosDeLaLinea(int id)
    {
        navigationView.getMenu().findItem(id).setIcon(R.drawable.ic_arrow_up_black);

        String nombreLinea = navigationView.getMenu().findItem(id).getTitle().toString();
        final LineaColectivo linea = lineaColectivoDAO.obtenerLineaColectivoPorNombre(nombreLinea);
        recorridosPorLinea = recorridoDAO.obtenerTodosLosRecorridosPorLinea(linea.getIdLineaColectivo());

        for (int i=2; i<navigationView.getMenu().size()-1; i++)
        {
            for (int j=0; j<recorridosPorLinea.size(); j++)
            {
                if(navigationView.getMenu().getItem(i).getTitle().toString().contentEquals(recorridosPorLinea.get(j).getNombre()))
                {
                    navigationView.getMenu().getItem(i).setVisible(true);
                }
            }
        }
    }

    public void cerrarSubitemsPrimerNivel()
    {
        L5 = false;
        L18 = false;
        Lineas = false;
        for (int i=2; i<navigationView.getMenu().size()-1; i++)
        {
            navigationView.getMenu().getItem(i).setVisible(false);
            if(navigationView.getMenu().getItem(i).getTitle().toString().contains("Linea"))
            {
                navigationView.getMenu().getItem(i).setIcon(R.drawable.ic_arrow_down_black);
            }
        }
    }

    public void cerrarSubitemsSegundoNivel()
    {
        L5 = false;
        L18 = false;
        for (int i=2; i<navigationView.getMenu().size()-1; i++)
        {
            if(navigationView.getMenu().getItem(i).getTitle().toString().contains("Linea"))
            {
                navigationView.getMenu().getItem(i).setIcon(R.drawable.ic_arrow_down_black);
            }
            else
            {
                navigationView.getMenu().getItem(i).setVisible(false);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_todos) {
            cerrarSubitemsPrimerNivel();
            if(pantallaRanking != null) manager.beginTransaction().remove(pantallaRanking).commit(); // Si el fragment de ranking no es nulo significa que que esta activo y visible por lo que se lo remueve del contenedor
            pantallaRanking = null;
            map.clear();
            cargarTodasLasParadas();
            cargarTodosLosColectivos();
            if (marcadorMiPosicion != null) cargarMarcadorDeMiPosicion();
        } else if (id == R.id.nav_lineas) {
            if (Lineas) cerrarSubitemsPrimerNivel();
            else mostrarLineas();
        } else if (id == R.id.L5) {
            if(L18) cerrarSubitemsSegundoNivel();
            if (L5) cerrarSubitemsSegundoNivel();
            else {
                L5 = true;
                mostrarRecorridosDeLaLinea(id);
            }
        } else if (id == R.id.L18) {
            if(L5) cerrarSubitemsSegundoNivel();
            if (L18) cerrarSubitemsSegundoNivel();
            else {
                L18 = true;
                mostrarRecorridosDeLaLinea(id);
            }
        } else if (id == R.id.L5SEOC) {
            map.clear();
            cargarTodosLosColectivos();
            if (marcadorMiPosicion != null) cargarMarcadorDeMiPosicion();
            if(pantallaRanking != null) manager.beginTransaction().remove(pantallaRanking).commit(); // Si el fragment de ranking no es nulo significa que que esta activo y visible por lo que se lo remueve del contenedor
            pantallaRanking = null;
            cargarPuntosGeograficosDeUnRecorrido(id);
            cargarParadasDeUnRecorrido(id);
            cargarMarcadoresDeParadas(paradasPorRecorrido);
        } else if (id == R.id.L18H) {
            map.clear();
            cargarTodosLosColectivos();
            if (marcadorMiPosicion != null) cargarMarcadorDeMiPosicion();
            if(pantallaRanking != null) manager.beginTransaction().remove(pantallaRanking).commit(); // Si el fragment de ranking no es nulo significa que que esta activo y visible por lo que se lo remueve del contenedor
            pantallaRanking = null;
            cargarPuntosGeograficosDeUnRecorrido(id);
            cargarParadasDeUnRecorrido(id);
            cargarMarcadoresDeParadas(paradasPorRecorrido);
        } else if (id == R.id.nav_ranking) {
            cerrarSubitemsPrimerNivel();
            pantallaRanking = new PantallaRanking();
            pantallaRanking.usuario = usuario;
            manager.beginTransaction().add(R.id.contenedor,pantallaRanking).commit(); // Se coloca el fragment del ranking encima del contenedor con el mapa
        }
        if(!navigationView.getMenu().findItem(id).getTitle().toString().contains("Linea"))
        {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
                if(adminSesion.obtenerProveedor().contentEquals("Google"))
                {
                    // Cerrar sesión en Google
                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestEmail()
                            .build();
                    GoogleSignInClient client = GoogleSignIn.getClient(this, gso);
                    client.signOut();
                }
                if(adminSesion.obtenerProveedor().contentEquals("Facebook"))
                {
                    // Cerrar sesión en Facebook
                    LoginManager.getInstance().logOut();
                }
                adminSesion.cerrarSesion(); // Limpiamos los datos de la cuenta desvinculada

                Intent irPantallaAutenticacion = new Intent(getApplicationContext(),PantallaAutenticacion.class);
                startActivity(irPantallaAutenticacion); // Nos dirigimos a la pantalla de autenticación
                finish();
                return true;
            case R.id.salir:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAndRemoveTask(); // Finalizar la actividad y matar al proceso
                }
                else
                {
                    finish(); // Finalizar la actividad
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);
        map.getUiSettings().setCompassEnabled(true);

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                btnMiUbicacion.setImageResource(R.drawable.my_location_icon);
                miUbicacionActivada = false;
            }
        });

        map.setIndoorEnabled(true);
        map.setBuildingsEnabled(true);

        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_todos));

        ubicarMiPosicion();
    }

    public void ubicarMiPosicion()
    {
        // Haremos referencia a LocationManager, la cual, utilizaremos para obtener servicios de geoposicionamiento en el dispositivo.
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        // Validaremos el permiso ACCESS_FINE_LOCATION para acceder a mi ubicación
        int checkSelfPermission = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        if(checkSelfPermission != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)) {}
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1000);
            }
        }
        else
        {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            {
                // Solicitamos al GPS actualizaciones de posición
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locListener);
                // Obtenemos la última posición conocida, luego llamamos a nuestro método actualizarUbicación.
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                actualizarPosicion(location);

                if (miPosicion != null) moverCamara(miPosicion,17);
            }
        }
    }

    // Metodo para actualizar nuestra posición en el mapa
    public void actualizarPosicion(Location location)
    {
        // Comprobamos si la localización recibida es diferente de null
        if (location != null)
        {
            cargarTodosLosColectivos();
            usuario.setLatitudActual(location.getLatitude());
            usuario.setLongitudActual(location.getLongitude());
            actualizarUsuarioBaseDeDatosExterna(usuario.getIdUsuario(), usuario);
            usuarioDAO.modificarUsuario(usuario);
            registrarUbicacionAnteriorUsuario();
            if (colectivo != null) registrarUbicacionAnteriorColectivo();
            miPosicion = new LatLng(usuario.getLatitudActual(),usuario.getLongitudActual());

            float v = ((location.getSpeed()*3600)/1000);
            BigDecimal bd = new BigDecimal(Float.toString(v));
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            miVelocidad = bd.floatValue();

            if (miUbicacionActivada) moverCamara(miPosicion,17);

            if (estoyEnElColectivo)
            {
                Toast msj = Toast.makeText(getApplicationContext(),"Estoy en el colectivo",Toast.LENGTH_SHORT);
                msj.show();
                if (marcadorMiPosicion != null) marcadorMiPosicion.remove();
                if (colectivo == null) verificarLinea();
                else
                {
                    colectivo.setVelocidad(miVelocidad);
                    colectivo.setLatitudActual(miPosicion.latitude);
                    colectivo.setLongitudActual(miPosicion.longitude);
                    actualizarColectivoBaseDeDatosExterna(colectivo.getIdColectivo(),colectivo);
                    colectivoDAO.modificarColectivo(colectivo);
                    if (transmision == null) registrarTransimision();
                    else
                    {
                        transmision.setHoraFin(formatoHora.format(new Date()));
                        usuario.setPuntajeActual(usuario.getPuntajeActual()+1);
                        transmision.setPuntajeObtenido(transmision.getPuntajeObtenido()+1);
                        actualizarTransmisionBaseDeDatosExterna(transmision.getIdTransmision(),transmision);
                        transmisionDAO.modificarTransmision(transmision);
                    }
                }
            }
            else
            {
                if (marcadorMiPosicion == null) cargarMarcadorDeMiPosicion();
                else animarMarcador(location,marcadorMiPosicion);

                if (circles != null)
                {
                    verificarProximidadParada();
                    verificarSiEstoyEnElColectivo();
                }
            }
        }
    }

    public void seguirMiUbicacion(View view)
    {
        if (!miUbicacionActivada)
        {
            btnMiUbicacion.setImageResource(R.drawable.my_location_on_icon);
            miUbicacionActivada = true;
            //if (miPosicion != null) moverCamara(miPosicion,19);
            ubicarMiPosicion();
        }
        else
        {
            btnMiUbicacion.setImageResource(R.drawable.my_location_icon);
            miUbicacionActivada = false;
        }
    }

    public void moverCamara(LatLng posicion, float zoom)
    {
        CameraPosition cameraPosition = CameraPosition.builder() // Definimos nueva posición de cámara
                .target(posicion)
                .zoom(zoom)
                .build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); // Movemos la cámara a la posición definida
    }

    public void verificarSiEstoyEnElColectivo()
    {
        if (estoyFueraDeLaParada && estuveEnLaParada)
        {
            if (miVelocidad >= 0)
            {
                estoyEnElColectivo = true;
            }
            //else estuveEnLaParada = false;
        }
    }

    public void verificarProximidadParada()
    {
        float[] disResultado = new float[2];

        for(int i = 0; i<circles.size();i++)
        {
            Circle circle = circles.get(i);
            Location.distanceBetween( miPosicion.latitude, miPosicion.longitude,
                    circle.getCenter().latitude,
                    circle.getCenter().longitude,
                    disResultado);

            if(disResultado[0] <=  circle.getRadius())
            {
                Toast msj = Toast.makeText(getApplicationContext(),"Estoy en la parada",Toast.LENGTH_SHORT);
                msj.show();
                estuveEnLaParada = true;
                estoyFueraDeLaParada = false;
                double lat = circle.getCenter().latitude;
                double lng = circle.getCenter().longitude;
                Parada parada = paradaDAO.obtenerParadaPorPosicion(lat, lng);
                if (parada == null)
                {
                    for (int j=0;j<todasLasParadas.size();j++)
                    {
                        if (todasLasParadas.get(j).getLatitud() == lat && todasLasParadas.get(j).getLongitud() == lng)
                        {
                            parada = todasLasParadas.get(j);
                        }
                    }
                }
                int idUsuario = usuario.getIdUsuario();
                int idParada = 1;
                if (parada != null) idParada = parada.getIdParada();
                String fecha = formatoFecha.format(new Date());
                String hora = formatoHora.format(new Date());
                final UsuarioParada usuarioParada = new UsuarioParada(idUsuario,idParada,fecha,hora);

                registrarUsuarioParada(usuarioParada);

                break;
            }
            else
            {
                estoyFueraDeLaParada = true;
            }
        }
    }

    // Definimos un escuchador LocationListener para manejar los eventos que ocurran como el cambio de ubicación
    LocationListener locListener = new LocationListener() {

        // Este método se lanza cada vez que el GPS detecte un cambio de posición
        @Override
        public void onLocationChanged(Location location) {
            actualizarPosicion(location); // refrescamos nuestra posición en el mapa
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public void cargarTodasLasLineas()
    {
        lineas = lineaColectivoDAO.obtenerTodasLasLineasColectivos();
        if (lineas.size() == 0)
        {
            rest.obtenerLineasColectivo().enqueue(new Callback<GetAllLineasColectivoResponse>() {
                @Override
                public void onResponse(Call<GetAllLineasColectivoResponse> call, Response<GetAllLineasColectivoResponse> response) {
                    if (response.body() != null)
                    {
                        Gson gson = new Gson();
                        Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                        lineas = response.body().getLineasColectivo();
                        for (int i=0;i<lineas.size();i++)
                        {
                            Log.i("GeoBusLog",gson.toJson(response.body().getLineasColectivo().get(i)));
                            lineaColectivoDAO.crearLineaColectivo(lineas.get(i));
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetAllLineasColectivoResponse> call, Throwable t) {
                    Log.e("GeoBusLog", t.getMessage());
                }
            });
        }
    }

    public void cargarParadasDeUnRecorrido(int id)
    {
        String nombreRecorrido = navigationView.getMenu().findItem(id).getTitle().toString();
        Recorrido recorrido = recorridoDAO.obtenerRecorridoPorNombre(nombreRecorrido);
        paradasPorRecorrido = paradaDAO.obtenerTodasLasParadasPorRecorridoyLinea(recorrido.getIdRecorrido(),recorrido.getIdLineaColectivo());
        if (paradasPorRecorrido.size() == 0)
        {
            paradasPorRecorrido = new ArrayList<>();
            for (int i=0; i<todasLasParadas.size(); i++)
            {
                if (todasLasParadas.get(i).getIdRecorrido() == recorrido.getIdRecorrido() && todasLasParadas.get(i).getIdLineaColectivo() == recorrido.getIdLineaColectivo())
                {
                    paradaDAO.crearParada(todasLasParadas.get(i));
                    paradasPorRecorrido.add(todasLasParadas.get(i));
                }
            }
        }
    }

    public void cargarTodosLosRecorridos()
    {
        todosLosRecorridos = recorridoDAO.obtenerTodosLosRecorridos();
        if (todosLosRecorridos.size() == 0)
        {
            todosLosRecorridos = new ArrayList<>();
            rest.obtenerRecorridos().enqueue(new Callback<GetAllRecorridosResponse>() {
                @Override
                public void onResponse(Call<GetAllRecorridosResponse> call, Response<GetAllRecorridosResponse> response) {
                    if (response.body() != null)
                    {
                        Gson gson = new Gson();
                        Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                        todosLosRecorridos = response.body().getRecorridos();
                        for (int i=0; i<todosLosRecorridos.size(); i++)
                        {
                            Log.i("GeoBusLog",gson.toJson(response.body().getRecorridos().get(i)));
                            recorridoDAO.crearRecorrido(todosLosRecorridos.get(i));
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetAllRecorridosResponse> call, Throwable t) {
                    Log.e("GeoBusLog", t.getMessage());
                }
            });
        }
    }

    public void cargarPuntosGeograficosDeUnRecorrido(int id)
    {
        String nombreRecorrido = navigationView.getMenu().findItem(id).getTitle().toString();
        final Recorrido recorrido = recorridoDAO.obtenerRecorridoPorNombre(nombreRecorrido);
        puntosGeograficosPorRecorrido = puntoGeograficoDAO.obtenerTodosLosPuntosGeograficosPorRecorrido(recorrido.getIdRecorrido());
        if (puntosGeograficosPorRecorrido.size() == 0)
        {
            puntosGeograficosPorRecorrido = new ArrayList<>();
            rest.obtenerPuntosGeograficos().enqueue(new Callback<GetAllPuntosGeograficosResponse>() {
                @Override
                public void onResponse(Call<GetAllPuntosGeograficosResponse> call, Response<GetAllPuntosGeograficosResponse> response) {
                    if (response.body() != null)
                    {
                        Gson gson = new Gson();
                        Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                        List<PuntoGeografico> puntosGeograficos = response.body().getPuntosGeograficos();
                        Collections.sort(puntosGeograficos, new Comparator<PuntoGeografico>() {
                            @Override
                            public int compare(PuntoGeografico o1, PuntoGeografico o2) {
                                return Integer.compare(o2.getIdPuntoGeografico(), o1.getIdPuntoGeografico());
                            }
                        });

                        for (int i=0; i<puntosGeograficos.size(); i++)
                        {
                            if (puntosGeograficos.get(i).getIdRecorrido() == recorrido.getIdRecorrido())
                            {
                                Log.i("GeoBusLog",gson.toJson(response.body().getPuntosGeograficos().get(i)));
                                puntoGeograficoDAO.crearPuntoGeografico(puntosGeograficos.get(i));
                                puntosGeograficosPorRecorrido.add(puntosGeograficos.get(i));
                            }
                        }
                        marcarRecorrido();
                    }
                }

                @Override
                public void onFailure(Call<GetAllPuntosGeograficosResponse> call, Throwable t) {
                    Log.e("GeoBusLog", t.getMessage());
                }
            });
        }
        else
        {
            marcarRecorrido();
        }
    }

    public void cargarMarcadoresDeParadas(List<Parada> paradas) {

        circles = new ArrayList<>();
        for (int i=0; i<paradas.size();i++)
        {
            LatLng posicionParada = new LatLng(paradas.get(i).getLatitud(),paradas.get(i).getLongitud());
            map.addMarker(new MarkerOptions()
                    .position(posicionParada)
                    .title("Parada")
                    .icon(BitmapDescriptorFactory.fromBitmap(iconoParada)));
            Circle circle = map.addCircle(new CircleOptions()
                    .center(posicionParada)
                    .radius(40)
                    .strokeColor(Color.LTGRAY));
            circle.setVisible(false);
            circles.add(circle);
        }
    }

    public void cargarMarcadoresDeColectivos(List<Colectivo> colectivos) {

        if (marcadoresColectivos != null)
        {
            for (int i=0; i<marcadoresColectivos.size();i++)
            {
                marcadoresColectivos.get(i).remove();
            }
        }
        marcadoresColectivos = new ArrayList<>();
        for (int i=0; i<colectivos.size();i++)
        {
            LatLng posicionColectivo = new LatLng(colectivos.get(i).getLatitudActual(),colectivos.get(i).getLongitudActual());
            Marker marcadorColectivo = map.addMarker(new MarkerOptions()
                    .position(posicionColectivo)
                    .title("Colectivo")
                    .icon(BitmapDescriptorFactory.fromBitmap(iconoColectivo)));
            marcadoresColectivos.add(marcadorColectivo);
        }
    }

    public void cargarMarcadorDeMiPosicion()
    {
        marcadorMiPosicion = map.addMarker(new MarkerOptions()
                .position(miPosicion)
                .anchor(0.5f,0.5f)
                .icon(BitmapDescriptorFactory.fromBitmap(iconoMiPosicion)));
    }

    public void marcarRecorrido()
    {
        ArrayList<LatLng> cor = new ArrayList<>();
        for (int i=0; i<puntosGeograficosPorRecorrido.size(); i++)
        {
            LatLng punto = new LatLng(puntosGeograficosPorRecorrido.get(i).getLatitud(),puntosGeograficosPorRecorrido.get(i).getLongitud());
            cor.add(punto);
        }
        recorridoMapa = map.addPolyline(new PolylineOptions()
                .addAll(cor)
                .width(30)
                .color(0x6E0051FF));
    }

    public static void animarMarcador(final Location destino, final Marker marcador) {
        if (marcador != null) {
            final LatLng posicionInicial = marcador.getPosition();
            final LatLng posicionFinal = new LatLng(destino.getLatitude(), destino.getLongitude());

            final LatLngInterpolator latLngInterpolator = new LatLngInterpolator.LinearFixed();
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
            valueAnimator.setDuration(1000);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override public void onAnimationUpdate(ValueAnimator animation) {
                    try {
                        float v = animation.getAnimatedFraction();
                        LatLng nuevaPosicion = latLngInterpolator.interpolate(v, posicionInicial, posicionFinal);
                        marcador.setPosition(nuevaPosicion);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            valueAnimator.start();
        }
    }

    private interface LatLngInterpolator {
        LatLng interpolate(float fraction, LatLng a, LatLng b);

        class LinearFixed implements LatLngInterpolator {
            @Override
            public LatLng interpolate(float fraction, LatLng a, LatLng b) {
                double lat = (b.latitude - a.latitude) * fraction + a.latitude;
                double lngDelta = b.longitude - a.longitude;
                // Take the shortest path across the 180th meridian.
                if (Math.abs(lngDelta) > 180) {
                    lngDelta -= Math.signum(lngDelta) * 360;
                }
                double lng = lngDelta * fraction + a.longitude;
                return new LatLng(lat, lng);
            }
        }
    }

    public void verificarLinea()
    {
        lineas = lineaColectivoDAO.obtenerTodasLasLineasColectivos();
        final String[] items = new String[lineas.size()];
        for (int i=0;i<lineas.size();i++)
        {
            items[i] = lineas.get(i).getNombre();
        }
        if (seleccionarLinea == null)
        {
            seleccionarLinea = new AlertDialog.Builder(this);
            seleccionarLinea.setTitle("Seleccione la linea en la que viaja")
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            linea = lineaColectivoDAO.obtenerLineaColectivoPorNombre(items[i]);
                            verificarRecorrido(linea);
                        }
                    });
            seleccionarLinea.create();
            seleccionarLinea.show();
        }
    }

    public void verificarRecorrido(LineaColectivo linea)
    {
        recorridosPorLinea = recorridoDAO.obtenerTodosLosRecorridosPorLinea(linea.getIdLineaColectivo());
        final String[] items = new String[recorridosPorLinea.size()];
        for (int i=0;i<recorridosPorLinea.size();i++)
        {
            items[i] = recorridosPorLinea.get(i).getNombre();
        }
        if (seleccionarRecorrido == null)
        {
            seleccionarRecorrido = new AlertDialog.Builder(this);
            seleccionarRecorrido.setTitle("Seleccion el recorrido de la linea en la que viaja")
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            recorrido = recorridoDAO.obtenerRecorridoPorNombre(items[i]);
                            registrarColectivo();
                        }
                    });
            seleccionarRecorrido.create();
            seleccionarRecorrido.show();
        }
    }

    public void registrarColectivo()
    {
        rest.obtenerColectivos().enqueue(new Callback<GetAllColectivosResponse>() {
            @Override
            public void onResponse(Call<GetAllColectivosResponse> call, Response<GetAllColectivosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    seleccionarLinea = null;
                    seleccionarRecorrido = null;

                    todosLosColectivos = response.body().getColectivos();

                    int idColectivo;
                    if(todosLosColectivos.size() > 0)
                    {
                        idColectivo = todosLosColectivos.get(todosLosColectivos.size() - 1).getIdColectivo() + 1;
                    }
                    else
                    {
                        idColectivo = 1;
                    }
                    float velocidad = miVelocidad;
                    double latitudActual = miPosicion.latitude;
                    double longitudActual = miPosicion.longitude;
                    int idRecorrido = recorrido.getIdRecorrido();
                    int idLineaColectivo = linea.getIdLineaColectivo();
                    colectivo = new Colectivo(idColectivo,velocidad,latitudActual,longitudActual,idRecorrido,idLineaColectivo);
                    agregarColectivoBaseDeDatosExterna(colectivo);
                    colectivoDAO.crearColectivo(colectivo);
                    cargarTodosLosColectivos();
                }
            }

            @Override
            public void onFailure(Call<GetAllColectivosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void registrarTransimision()
    {
        rest.obtenerTransmisiones().enqueue(new Callback<GetAllTransmisionesResponse>() {
            @Override
            public void onResponse(Call<GetAllTransmisionesResponse> call, Response<GetAllTransmisionesResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    transmisiones = response.body().getTransmisiones();

                    int idTransmision;
                    if(transmisiones.size() > 0)
                    {
                        idTransmision = transmisiones.get(transmisiones.size() - 1).getIdTransmision() + 1;
                    }
                    else
                    {
                        idTransmision = 1;
                    }
                    String fecha = formatoFecha.format(new Date());
                    String horaInicio = formatoHora.format(new Date());
                    String horaFin = "";
                    int puntajeObtenido = 0;
                    int idUsuario = usuario.getIdUsuario();
                    int idColectivo = colectivo.getIdColectivo();
                    transmision = new Transmision(idTransmision,fecha,horaInicio,horaFin,puntajeObtenido,idUsuario,idColectivo);
                    agregarTransmisionBaseDeDatosExterna(transmision);
                    transmisionDAO.crearTransmision(transmision);
                }
            }

            @Override
            public void onFailure(Call<GetAllTransmisionesResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void registrarUbicacionAnteriorColectivo()
    {
        rest.obtenerUbicacionesAnterioresColectivos().enqueue(new Callback<GetAllUbicacionesAnterioresColectivosResponse>() {
            @Override
            public void onResponse(Call<GetAllUbicacionesAnterioresColectivosResponse> call, Response<GetAllUbicacionesAnterioresColectivosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    List<UbicacionAnteriorColectivo> ubicacionesAnterioresColectivo = response.body().getUbicacionesAnterioresColectivos();

                    int idUbicacionAnteriorColectivo;
                    if(ubicacionesAnterioresColectivo.size() > 0)
                    {
                        idUbicacionAnteriorColectivo = ubicacionesAnterioresColectivo.get(ubicacionesAnterioresColectivo.size() - 1).getIdUbicacionAnteriorColectivo() + 1;
                    }
                    else
                    {
                        idUbicacionAnteriorColectivo = 1;
                    }
                    String fecha = formatoFecha.format(new Date());
                    String hora = formatoHora.format(new Date());
                    double latitudAnterior = miPosicion.latitude;
                    double longitudAnterior = miPosicion.longitude;
                    int idColectivo = colectivo.getIdColectivo();
                    int idRecorrido = colectivo.getIdRecorrido();
                    int idLineaColectivo = colectivo.getIdLineaColectivo();
                    UbicacionAnteriorColectivo ubicacionAnteriorColectivo = new UbicacionAnteriorColectivo(idUbicacionAnteriorColectivo,fecha,hora,latitudAnterior,longitudAnterior,idColectivo,idRecorrido,idLineaColectivo);
                    UbicacionAnteriorColectivo uac = null;

                    for (int i=0;i<ubicacionesAnterioresColectivo.size();i++)
                    {
                        if (ubicacionesAnterioresColectivo.get(i).getIdColectivo() == idColectivo)
                        {
                            uac = ubicacionesAnterioresColectivo.get(i);
                        }
                    }

                    if (uac == null)
                    {
                        agregarUbicacionAnteriorColectivoBaseDeDatosExterna(ubicacionAnteriorColectivo);
                    }
                    else
                    {
                        ubicacionAnteriorColectivo.setIdUbicacionAnteriorColectivo(uac.getIdUbicacionAnteriorColectivo());
                        actualizarUbicacionAnteriorColectivoBaseDeDatosExterna(uac.getIdUbicacionAnteriorColectivo(),ubicacionAnteriorColectivo);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllUbicacionesAnterioresColectivosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void registrarUbicacionAnteriorUsuario()
    {
        rest.obtenerUbicacionesAnterioresUsuarios().enqueue(new Callback<GetAllUbicacionesAnterioresUsuariosResponse>() {
            @Override
            public void onResponse(Call<GetAllUbicacionesAnterioresUsuariosResponse> call, Response<GetAllUbicacionesAnterioresUsuariosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    List<UbicacionAnteriorUsuario> ubicacionesAnterioresUsuarios = response.body().getUbicacionesAnterioresUsuarios();

                    int idUbicacionAnteriorUsuario;
                    if(ubicacionesAnterioresUsuarios.size() > 0)
                    {
                        idUbicacionAnteriorUsuario = ubicacionesAnterioresUsuarios.get(ubicacionesAnterioresUsuarios.size() - 1).getIdUbicacionAnteriorUsuario() + 1;
                    }
                    else
                    {
                        idUbicacionAnteriorUsuario = 1;
                    }
                    String fecha = formatoFecha.format(new Date());
                    String hora = formatoHora.format(new Date());
                    double latitudAnterior = miPosicion.latitude;
                    double longitudAnterior = miPosicion.longitude;
                    int idUsuario = usuario.getIdUsuario();
                    UbicacionAnteriorUsuario ubicacionAnteriorUsuario = new UbicacionAnteriorUsuario(idUbicacionAnteriorUsuario,fecha,hora,latitudAnterior,longitudAnterior,idUsuario);
                    UbicacionAnteriorUsuario uau = null;

                    for (int i=0;i<ubicacionesAnterioresUsuarios.size();i++)
                    {
                        if (ubicacionesAnterioresUsuarios.get(i).getIdUsuario() == idUsuario)
                        {
                            uau = ubicacionesAnterioresUsuarios.get(i);
                        }
                    }

                    if (uau == null)
                    {
                        agregarUbicacionAnteriorUsuarioBaseDeDatosExterna(ubicacionAnteriorUsuario);
                    }
                    else
                    {
                        ubicacionAnteriorUsuario.setIdUbicacionAnteriorUsuario(uau.getIdUbicacionAnteriorUsuario());
                        actualizarUbicacionAnteriorUsuarioBaseDeDatosExterna(uau.getIdUbicacionAnteriorUsuario(),ubicacionAnteriorUsuario);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllUbicacionesAnterioresUsuariosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void registrarUsuarioParada(UsuarioParada usup)
    {
        final UsuarioParada usuarioParada = usup;
        if (usuarioParadaDAO.obtenerUsuarioParadaPorId(usuarioParada.getIdUsuario(),usuarioParada.getIdParada()) == null)
        {
            usuarioParadaDAO.crearUsuarioParada(usuarioParada);
        }
        else
        {
            usuarioParadaDAO.modificarUsuarioParada(usuarioParada);
        }

        rest.obtenerUsuariosParadas().enqueue(new Callback<GetAllUsuariosParadasResponse>() {
            @Override
            public void onResponse(Call<GetAllUsuariosParadasResponse> call, Response<GetAllUsuariosParadasResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    List<UsuarioParada> usuariosParadas = response.body().getUsuariosParadas();
                    UsuarioParada up = null;

                    for (int i=0;i<usuariosParadas.size();i++)
                    {
                        if (usuariosParadas.get(i).getIdUsuario() == usuarioParada.getIdUsuario() && usuariosParadas.get(i).getIdParada() == usuarioParada.getIdParada())
                        {
                            up = usuariosParadas.get(i);
                        }
                    }

                    if (up == null)
                    {
                        agregarUsuarioParadaBaseDeDatosExterna(usuarioParada);
                    }
                    else
                    {
                        actualizarUsuarioParadaBaseDeDatosExterna(up.getIdUsuario(),up.getIdParada(),up.getFecha(),up.getHora(),usuarioParada);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllUsuariosParadasResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void cargarTodosLosColectivos()
    {
        rest.obtenerColectivos().enqueue(new Callback<GetAllColectivosResponse>() {
            @Override
            public void onResponse(Call<GetAllColectivosResponse> call, Response<GetAllColectivosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    todosLosColectivos = response.body().getColectivos();
                    cargarMarcadoresDeColectivos(todosLosColectivos);
                }
            }

            @Override
            public void onFailure(Call<GetAllColectivosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void cargarTodasLasParadas()
    {
        rest.obtenerParadas().enqueue(new Callback<GetAllParadasResponse>() {
            @Override
            public void onResponse(Call<GetAllParadasResponse> call, Response<GetAllParadasResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    todasLasParadas = response.body().getParadas();
                    cargarMarcadoresDeParadas(todasLasParadas);
                }
            }

            @Override
            public void onFailure(Call<GetAllParadasResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void agregarColectivoBaseDeDatosExterna(Colectivo colectivo)
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

    public void actualizarColectivoBaseDeDatosExterna(int idColectivo, Colectivo colectivo)
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

    public void agregarTransmisionBaseDeDatosExterna(Transmision transmision)
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

    public void actualizarTransmisionBaseDeDatosExterna(int idTransmision, Transmision transmision)
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

    public void agregarUbicacionAnteriorColectivoBaseDeDatosExterna(UbicacionAnteriorColectivo ubicacionAnteriorColectivo)
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

    public void actualizarUbicacionAnteriorColectivoBaseDeDatosExterna(int idUbicacionAnteriorColectivo, UbicacionAnteriorColectivo ubicacionAnteriorColectivo)
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

    public void agregarUbicacionAnteriorUsuarioBaseDeDatosExterna(UbicacionAnteriorUsuario ubicacionAnteriorUsuario)
    {
        rest.agregarUbicacionAnteriorUsuario(ubicacionAnteriorUsuario).enqueue(new Callback<PostUbicacionAnteriorUsuarioResponse>() {
            @Override
            public void onResponse(Call<PostUbicacionAnteriorUsuarioResponse> call, Response<PostUbicacionAnteriorUsuarioResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PostUbicacionAnteriorUsuarioResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void actualizarUbicacionAnteriorUsuarioBaseDeDatosExterna(int idUbicacionAnteriorUsuario, UbicacionAnteriorUsuario ubicacionAnteriorUsuario)
    {
        rest.actualizarUbicacionAnteriorUsuario(idUbicacionAnteriorUsuario, ubicacionAnteriorUsuario).enqueue(new Callback<PutUbicacionAnteriorUsuarioResponse>() {
            @Override
            public void onResponse(Call<PutUbicacionAnteriorUsuarioResponse> call, Response<PutUbicacionAnteriorUsuarioResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PutUbicacionAnteriorUsuarioResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void actualizarUsuarioBaseDeDatosExterna(int idUsuario, Usuario usuario)
    {
        usuarioDAO.modificarUsuario(usuario);
        rest.actualizarUsuario(idUsuario, usuario).enqueue(new Callback<PutUsuarioResponse>() {
            @Override
            public void onResponse(Call<PutUsuarioResponse> call, Response<PutUsuarioResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PutUsuarioResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void agregarUsuarioParadaBaseDeDatosExterna(UsuarioParada usuarioParada)
    {
        rest.agregarUsuarioParada(usuarioParada).enqueue(new Callback<PostUsuarioParadaResponse>() {
            @Override
            public void onResponse(Call<PostUsuarioParadaResponse> call, Response<PostUsuarioParadaResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PostUsuarioParadaResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void actualizarUsuarioParadaBaseDeDatosExterna(int idUsuario, int idParada, String fecha, String hora, UsuarioParada usuarioParada)
    {
        rest.actualizarUsuarioParada(idUsuario, idParada, fecha, hora, usuarioParada).enqueue(new Callback<PutUsuarioParadaResponse>() {
            @Override
            public void onResponse(Call<PutUsuarioParadaResponse> call, Response<PutUsuarioParadaResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PutUsuarioParadaResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED && permissions[0].contentEquals(Manifest.permission.ACCESS_FINE_LOCATION))
        {
            Toast.makeText(getApplicationContext(),"Permiso de localización otorgado", Toast.LENGTH_SHORT).show();
            ubicarMiPosicion();
        }
    }
}