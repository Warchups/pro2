package com.pmm.simarro.proyectofinal_christianllopis;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.pmm.simarro.proyectofinal_christianllopis.pojo.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    private String GET = "http://welivescore.xyz/Christian/obtener_usuario_por_nick.php";
    private String INSERT = "http://welivescore.xyz/Christian/insertar_usuario.php";

    private EditText nickTxt;
    private EditText passTxt1;
    private EditText passTxt2;
    private EditText correoTxt;
    private Button botonRegistrarse;

    private ShimmerFrameLayout container;

    private boolean existeUsuario = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nickTxt = (EditText) findViewById(R.id.nickTxt);
        passTxt1 = (EditText) findViewById(R.id.passTxt1);
        passTxt2 = (EditText) findViewById(R.id.passTxt2);
        correoTxt = (EditText) findViewById(R.id.correoTxt);

        container = (ShimmerFrameLayout) findViewById(R.id.shimmerRegistro);

        botonRegistrarse = (Button) findViewById(R.id.botonRegistrarse);

        botonRegistrarse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.botonRegistrarse:
                String nick = nickTxt.getText().toString().trim();
                String pass1 = passTxt1.getText().toString().trim();
                String pass2 = passTxt2.getText().toString().trim();
                String correo = correoTxt.getText().toString().trim();

                if (nick.length() == 0 || pass1.length() == 0 || pass2.length() == 0 || correo.length() == 0) {
                    Toast.makeText(this, "Hay algun campo sin rellenar", Toast.LENGTH_SHORT).show();
                }else if (!comprobarContrasenas(pass1, pass2)) {
                    Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                }else {
                    RegistrarAsyncTask rat = new RegistrarAsyncTask();

                    String url = GET + "?nick=" + nick;

                    rat.execute(url, nick, pass1, correo);
                }

                break;
        }
    }

    private boolean comprobarContrasenas(String pass1, String pass2) {
        if (pass1.length() != pass2.length()) {
            return false;
        }else if (pass1.length() == 0 || pass2.length() == 0) {
            return false;
        }else {
            for (int i = 0 ; i < pass1.length() ; i++ ) {
                if (pass1.charAt(i) != pass2.charAt(i)) {
                    return false;
                }
            }
        }

        return true;
    }

    class RegistrarAsyncTask extends AsyncTask<String, Integer, Boolean> {
        String nick;
        String pass;
        String correo;

        @Override
        protected void onPreExecute() {
            container.startShimmerAnimation();
        }

        @Override
        protected Boolean doInBackground(String... values) {

            Boolean existe = true;

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

                    System.out.println(resultJSON);
                    if (resultJSON.equals("2")) {
                        existe = false;
                        nick = values[1];
                        pass = values[2];
                        correo = values[3];
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return existe;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(Boolean existe) {
            container.stopShimmerAnimation();

            if (existe) {
                Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();
            }else {
                String url = INSERT;

                InsertAsyncTask iat = new InsertAsyncTask();

                iat.execute(url, nick, pass, correo);
            }
        }
    }

    class InsertAsyncTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            container.startShimmerAnimation();
        }

        @Override
        protected Boolean doInBackground(String... values) {

            Boolean anadido = false;

            try {
                HttpURLConnection urlConn;
                DataOutputStream printout;
                DataInputStream input;
                URL url = new URL(values[0]);
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setUseCaches(false);
                urlConn.setRequestProperty("Content-Type", "application/json");
                urlConn.setRequestProperty("Accept", "application/json");
                urlConn.connect();

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("nick", values[1]);
                jsonParam.put("pass", values[2]);
                jsonParam.put("correo", values[3]);

                // Envio los parámetros post.
                OutputStream os = urlConn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(jsonParam.toString());
                writer.flush();
                writer.close();

                int respuesta = urlConn.getResponseCode();
                StringBuilder result = new StringBuilder();
                if (respuesta == HttpURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br=new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        result.append(line);
                    }

                    JSONObject respuestaJSON = new JSONObject(result.toString());

                    String resultJSON = respuestaJSON.getString("estado");
                    if (resultJSON.equals("1")) {
                        anadido = true;
                    }
                    /*else if (resultJSON.equals("2")) {
                        anadido = false;
                    }*/
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return anadido;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(Boolean anadido) {
            container.stopShimmerAnimation();

            if (anadido) {
                Toast.makeText(getApplicationContext(), "Añadido correctamente", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            }else {
                Toast.makeText(getApplicationContext(), "No se ha añadido el usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void limpiarCampos() {
        nickTxt.setText("");
        passTxt1.setText("");
        passTxt2.setText("");
        correoTxt.setText("");
    }
}
