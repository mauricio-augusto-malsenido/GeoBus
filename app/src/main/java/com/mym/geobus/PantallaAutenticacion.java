package com.mym.geobus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.facebook.FacebookSdk;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.mym.geobus.Autenticacion.AdministradorDeSesion;
import com.mym.geobus.BaseDeDatosLocal.UsuarioDAO;
import com.mym.geobus.Entidades.Usuario;
import com.mym.geobus.ServicioExterno.Response.Usuario.GetAllUsuariosResponse;
import com.mym.geobus.ServicioExterno.Response.Usuario.PostUsuarioResponse;
import com.mym.geobus.ServicioExterno.Retrofit.ApiRest;
import com.mym.geobus.ServicioExterno.Retrofit.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PantallaAutenticacion extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    SignInButton btnLoginGoogle;
    LoginButton btnLoginFacebook;
    CallbackManager callbackManager;
    GoogleSignInOptions gso;
    GoogleSignInClient googleSignInClient;
    AdministradorDeSesion adminSesion; // Definimos una variable que representara nuestra clase manejadora de la sesión
    List<Usuario> usuarios;
    UsuarioDAO usuarioDAO;
    ApiRest rest;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_autenticacion);
        getSupportActionBar().hide();

        FacebookSdk.sdkInitialize(getApplicationContext()); // Inicializamos el sdk de facebook
        callbackManager = CallbackManager.Factory.create(); // Creamos un administrador de devolución de llamadas que se encargue de las respuestas del inicio de sesión

        usuarioDAO = new UsuarioDAO(getApplicationContext());
        rest = Utilidades.obtenerCliente();

        adminSesion = new AdministradorDeSesion(getApplicationContext()); // instanciamos la clase AdministradorDeSesion
        if (adminSesion.verificarUsuarioAutenticado())
        {
            Intent irPantallaPrincipal = new Intent(getApplicationContext(),PantallaPrincipal.class);
            startActivity(irPantallaPrincipal);
            finish();
        }

        btnLoginGoogle = findViewById(R.id.btnLoginGoogle);
        ((TextView) btnLoginGoogle.getChildAt(0)).setText("Ingresar con Google"); // Defino el texto del botón del login de Google
        btnLoginGoogle.getChildAt(0).setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START); // Alinieamos el texto del botón a la izquierda

        // Evento click del botón "Ingresar con Google"
        btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicializarParametrosCuentaGoogle();
                Intent autenticarGoogle = googleSignInClient.getSignInIntent(); // Definimos la intención que nos dirigirá a la pantalla de autenticación de cuenta de Google
                startActivityForResult(autenticarGoogle,100); // Iniciamos la pantalla de autenticación de Google esperando que se devuelva el resultado de la conexión
            }
        });

        btnLoginFacebook = findViewById(R.id.btnLoginFacebook);
        btnLoginFacebook.setTextAlignment(View.TEXT_ALIGNMENT_CENTER); // Alinieamos el texto del botón al centro
        btnLoginFacebook.setReadPermissions("email"); // Le damos permiso a la aplicación para leer el correo electrónico del usuario

        // Registro de Callback correspondiente al botón "Ingreso con Facebook"
        btnLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) // Accion si la conexión es exitosa
            {
                // Si el inicio de sesión se realiza correctamente, el parámetro LoginResult tendrá el nuevo objeto AccessToken y los permisos concedidos o rechazados más recientemente.
                // A continuación, para obtener los datos del perfil definimos un objeto petición
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                // Una vez realizada la petición procesamos el objeto json que obtenemos como respuesta el cual contendra la información solicitada del perfil del usuario
                                try {
                                    String nombre = object.getString("name");
                                    String email = object.getString("email");
                                    String proveedor = "Facebook";
                                    registrarUsurio(nombre,email,proveedor);
                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle(); // Objeto que contendra una colección de datos
                parameters.putString("fields", "id,name,email,gender, birthday"); // Cargamos el objeto parameters con los campos que podriamos solicitar del perfil
                request.setParameters(parameters); // Asignamos el objeto parameters a la petición creada
                request.executeAsync(); // Ejecutamos la petición
            }

            @Override
            public void onCancel() // Accion si la conexión fue cancelada
            {
                Toast msj = Toast.makeText(getApplicationContext(),"Conexión cancelada",Toast.LENGTH_LONG);
                msj.show();
            }

            @Override
            public void onError(FacebookException exception)  // Accion si la conexión dio un error
            {
                Toast msj = Toast.makeText(getApplicationContext(),"Falló la conexión",Toast.LENGTH_LONG);
                msj.show();
            }
        });
    }

    public void inicializarParametrosCuentaGoogle()
    {
        // Definimos un objeto GoogleSignInOptions a través del builder el cual indicará como autenticarnos, proporcionamos el tipo de autenticación que queremos (en este caso sera una autenticación normal)
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("164523438894-j7f1sc3kgbgdlrq68eiqgivsa3ihp96m.apps.googleusercontent.com") // Solicita el ID de token correspondiente para poder acceder a toda la informacion de la cuenta. Se envia como parametro el ID del cliente web (Obtener del Google API Console)
                .requestEmail() // Solicita informacion sobre el correo electrónico del usuario
                .requestProfile() // Solicita informacion sobre el perfil del usuario
                .build(); // Construimos nuestro objeto GoogleSignInOptions

        // Se obtiene una nueva instancia de cliente de google a la cual se le carga los parametros de opciones definidos anteriormente a traves de esta instancia accederemos mas adelante a la pantalla de autenticación de google
        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    // Sobreescribimos el método que se encargará de gestionar cuando falle la conexión
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast msj = Toast.makeText(getApplicationContext(),"Falló la conexión",Toast.LENGTH_LONG);
        msj.show();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void registrarUsurio(String nom, String mail, String prov)
    {
        final String nombre = nom;
        final String email = mail;
        final String proveedor = prov;
        rest.obtenerUsuarios().enqueue(new Callback<GetAllUsuariosResponse>() {
            @Override
            public void onResponse(Call<GetAllUsuariosResponse> call, Response<GetAllUsuariosResponse> response) {
                if (response.body() != null)
                {
                    Gson gson = new Gson();
                    Log.i("GeoBusLog",gson.toJson(response.body().getRespuesta()));

                    usuarios = response.body().getUsuarios();
                    Usuario user = null;
                    for (int i=0; i<usuarios.size();i++)
                    {
                        if (usuarios.get(i).getNombre().contentEquals(nombre) && usuarios.get(i).getEmail().contentEquals(email))
                        {
                            user = usuarios.get(i);
                        }
                    }
                    if (user == null)
                    {
                        int idUsuario = 0;
                        if(usuarios.size() > 0)
                        {
                            idUsuario = usuarios.get(usuarios.size() - 1).getIdUsuario() + 1;
                        }
                        else
                        {
                            idUsuario = 1;
                        }
                        int puntajeActual = 0;
                        double latitudActual = 0.0;
                        double longitudActual = 0.0;
                        adminSesion.registrarCuenta(idUsuario,nombre,email,proveedor); // Registramos el usuario en la SharedPreferences
                        Usuario usuario = new Usuario(idUsuario,nombre,puntajeActual,latitudActual,longitudActual,email);
                        agregarUsuarioBaseDeDatosExterna(usuario);
                        usuarioDAO.crearUsuario(usuario);
                    }
                    else
                    {
                        if (usuarioDAO.obtenerUsuarioPorId(user.getIdUsuario()) == null)
                        {
                            usuarioDAO.crearUsuario(user);
                        }
                        adminSesion.registrarCuenta(user.getIdUsuario(),user.getNombre(),user.getEmail(),proveedor);
                    }
                    Intent irPantallaPrincipal = new Intent(getApplicationContext(),PantallaPrincipal.class); // Definimos una intencion para ir a la pantalla principal
                    startActivity(irPantallaPrincipal); // Iniciamos la pantalla principal
                    finish();
                }
            }

            @Override
            public void onFailure(Call<GetAllUsuariosResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    public void agregarUsuarioBaseDeDatosExterna(Usuario usuario)
    {
        rest.agregarUsuario(usuario).enqueue(new Callback<PostUsuarioResponse>() {
            @Override
            public void onResponse(Call<PostUsuarioResponse> call, Response<PostUsuarioResponse> response) {
                Gson gson = new Gson();
                Log.i("GeoBusLog",gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PostUsuarioResponse> call, Throwable t) {
                Log.e("GeoBusLog", t.getMessage());
            }
        });
    }

    // Metodo que gestionará el resultado devuelto al finalizar el proceso de autenticación de Google
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data); // Pasar el resultado del inicio de sesión con Facebook a LoginManager mediante callbackManager.

        // Se verifica si el codigo de inicio de activodad se corresponde con el del startActivityForResult
        if(requestCode == 100)
        {
            if (resultCode == RESULT_OK)
            {
                // Cuenta autenticada exitosamente
                try {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data); // Se define un objeto tarea que se encargara de manejar la respuesta devuelta por Google
                    GoogleSignInAccount cuenta = task.getResult(ApiException.class); // Obtenemos la cuenta autenticada y la almacenamos en un objeto de tipo GoogleSignInAccount
                    String nombre = cuenta.getDisplayName();
                    String email = cuenta.getEmail();
                    String proveedor = "Google";
                    registrarUsurio(nombre,email,proveedor);
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                // Error en la autenticación de cuenta
                Toast msj = Toast.makeText(getApplicationContext(),"Autenticación fallida o cancelada",Toast.LENGTH_LONG);
                msj.show();

                Intent irPantallaAutenticacion = new Intent(getApplicationContext(),PantallaAutenticacion.class);
                startActivity(irPantallaAutenticacion);
                finish();
            }
        }
    }
}
