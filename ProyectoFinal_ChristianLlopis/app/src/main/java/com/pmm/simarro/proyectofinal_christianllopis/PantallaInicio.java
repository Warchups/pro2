package com.pmm.simarro.proyectofinal_christianllopis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pmm.simarro.proyectofinal_christianllopis.pojo.Usuario;


public class PantallaInicio extends AppCompatActivity implements View.OnClickListener {

    private TextView bienvenido;
    private ImageButton botonCancionesLocales;
    private ImageButton botonCancionesYoutube;
    private ImageButton botonAnadirCancion;
    private ImageButton botonWiki;
    private ImageButton botonMaps;
    private ImageButton botonConfiguracion;

    private Usuario usuario = null;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);

        usuario = (Usuario) getIntent().getExtras().getSerializable("USUARIO");

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);////////////

        bienvenido = (TextView) findViewById(R.id.bienvenido);

        prefs = getSharedPreferences("preferenciasmusicteca", Context.MODE_PRIVATE);

        String saludo = (String) prefs.getString("saludo", "");

        if (saludo.length() > 0) {
            bienvenido.setText(saludo + " " + usuario.getNick());
        }else {
            bienvenido.setText("Bienvenido " + usuario.getNick());
        }

        botonCancionesLocales = (ImageButton) findViewById(R.id.botonCancionesLocales);
        botonCancionesYoutube = (ImageButton) findViewById(R.id.botonCancionesYoutube);
        botonAnadirCancion = (ImageButton) findViewById(R.id.botonAnadirCancion);
        botonWiki = (ImageButton) findViewById(R.id.botonWiki);
        botonMaps = (ImageButton) findViewById(R.id.botonMaps) ;
        botonConfiguracion = (ImageButton) findViewById(R.id.botonConfiguracion);

        botonCancionesLocales.setOnClickListener(this);
        botonCancionesYoutube.setOnClickListener(this);
        botonAnadirCancion.setOnClickListener(this);
        botonWiki.setOnClickListener(this);
        botonMaps.setOnClickListener(this);
        botonConfiguracion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.botonCancionesLocales:
                i = new Intent(this, CancionesLocales.class);

                startActivity(i);
                break;
            case R.id.botonCancionesYoutube:
                i = new Intent(this, CancionesYoutube.class);

                startActivity(i);
                break;
            case R.id.botonAnadirCancion:
                i = new Intent(this, AnadirCancionYoutube.class);

                startActivity(i);
                break;
            case R.id.botonWiki:
                i = new Intent(this, Wiki.class);

                startActivity(i);
                break;
            case R.id.botonMaps:
                i = new Intent(this, MapsMenu.class);

                startActivity(i);
                break;
            case R.id.botonConfiguracion:
                i = new Intent(this, Configuracion.class);

                startActivity(i);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
