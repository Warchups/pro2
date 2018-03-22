package com.pmm.simarro.proyectofinal_christianllopis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.pmm.simarro.proyectofinal_christianllopis.pojo.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String GET = "http://welivescore.xyz/Christian/login_usuario.php";

    private EditText nick;
    private EditText pass;

    private ImageButton botonLogin;
    private ImageButton botonRegistro;

    private ImageView imagenLogin;

    ShimmerFrameLayout container;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("preferenciasmusicteca", Context.MODE_PRIVATE);

        imagenLogin = (ImageView) findViewById(R.id.imageLogin);

        String logo = prefs.getString("logo", "");

        if (logo.equals("spoty")) {
            imagenLogin.setImageResource(R.drawable.spoty);
        }else if (logo.equals("logoApp")) {
            imagenLogin.setImageResource(R.drawable.logo2);
        }else if (logo.equals("spotyC")) {
            imagenLogin.setImageResource(R.drawable.spoty2);
        }else if (logo.equals("basic")) {
            imagenLogin.setImageResource(R.drawable.user_icon);
        }else {
            imagenLogin.setImageResource(R.drawable.spoty);
        }

        nick = (EditText) findViewById(R.id.nick);
        pass = (EditText) findViewById(R.id.pass);

        container = (ShimmerFrameLayout) findViewById(R.id.containerShimmer);

        botonLogin = (ImageButton) findViewById(R.id.botonLogin);
        botonRegistro = (ImageButton) findViewById(R.id.botonRegistro);

        botonLogin.setOnClickListener(this);
        botonRegistro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.botonLogin:
                String n = nick.getText().toString();
                String p = pass.getText().toString();

                if (n.length() == 0 || p.length() == 0) {
                    Toast.makeText(this, "Introduce un usuario y una contraseña", Toast.LENGTH_SHORT).show();
                }else {
                    IniciarAsyncTask iat = new IniciarAsyncTask();

                    iat.execute(GET + "?nick=" + n.trim() + "&pass=" + p.trim());
                    /*Intent i = new Intent(MainActivity.this, PantallaInicio.class);

                    Usuario u = new Usuario();
                    u.setNick(n);

                    i.putExtra("USUARIO", u);

                    startActivity(i);*/
                }

                break;
            case R.id.botonRegistro:

                Intent i = new Intent(this, Registro.class);

                startActivity(i);

                break;
        }
    }

    class IniciarAsyncTask extends AsyncTask<String, Integer, Usuario> {
        @Override
        protected void onPreExecute() {
            container.startShimmerAnimation();
            botonLogin.setImageResource(R.drawable.login_pulsado);
        }

        @Override
        protected Usuario doInBackground(String... values) {

            Usuario usuario = null;

            try {
                URL url = new URL(values[0]);

                HttpURLConnection connection = (HttpURLConnection)
                        url.openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                        " (Linux; Android 1.5; es-ES) Ejemplo HTTP");

                int respuesta = connection.getResponseCode();
                StringBuilder result = new StringBuilder();

                if (respuesta == HttpURLConnection.HTTP_OK){
                    InputStream in = new BufferedInputStream(connection.getInputStream());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    JSONObject respuestaJSON = new JSONObject(result.toString());
                    String resultJSON = respuestaJSON.getString("estado");

                    if (resultJSON.equals("1")) {

                        int id = respuestaJSON.getJSONObject("usuario").getInt("id");
                        String nick = respuestaJSON.getJSONObject("usuario").getString("nick");
                        String pass = respuestaJSON.getJSONObject("usuario").getString("pass");
                        String correo = respuestaJSON.getJSONObject("usuario").getString("correo");

                        usuario = new Usuario(id, nick, pass, correo);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return usuario;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            container.stopShimmerAnimation();
            botonLogin.setImageResource(R.drawable.login);

            if (usuario != null) {

                Intent i = new Intent(MainActivity.this, PantallaInicio.class);

                i.putExtra("USUARIO", usuario);

                startActivity(i);
            }else {
                Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, PantallaInicio.class);

                Usuario u = new Usuario();
                u.setNick("ANONIMO");

                i.putExtra("USUARIO", u);

                startActivity(i);

            }

        }
    }
}
