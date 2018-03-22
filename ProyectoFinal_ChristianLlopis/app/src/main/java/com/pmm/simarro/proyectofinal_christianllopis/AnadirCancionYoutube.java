package com.pmm.simarro.proyectofinal_christianllopis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.pmm.simarro.proyectofinal_christianllopis.API.MiCancionOperacional;
import com.pmm.simarro.proyectofinal_christianllopis.pojo.CancionYoutube;

import org.apache.commons.validator.routines.UrlValidator;

public class AnadirCancionYoutube extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private ImageButton botonBack;
    private ImageButton botonAnadir;

    private EditText nombreTxt;
    private EditText artistaTxt;
    private EditText generoTxt;
    private EditText urlTxt;

    private Spinner spinnerGenero;

    private static String[] generos = {"-Genero-", "Rock", "Rap", "Pop", "Otro"};

    private boolean correcto = false;
    private int caso = -1;
    private int pos = -1;
    private String genero = "";
    private String nombre = "";
    private String artista = "";
    private  String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_cancion_youtube);

        botonBack = (ImageButton) findViewById(R.id.botonBack);
        botonAnadir = (ImageButton) findViewById(R.id.botonAnadir);

        nombreTxt = (EditText) findViewById(R.id.nombreTxt);
        artistaTxt = (EditText) findViewById(R.id.artistaTxt);
        generoTxt = (EditText) findViewById(R.id.generoTxt);
        urlTxt = (EditText) findViewById(R.id.urlTxt);

        spinnerGenero = (Spinner) findViewById(R.id.spinnerGenero);

        adaptarSpinner();

        botonBack.setOnClickListener(this);
        botonAnadir.setOnClickListener(this);
    }

    private void adaptarSpinner() {
        ArrayAdapter<String> adaptadorGenero = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, generos);

        spinnerGenero.setAdapter(adaptadorGenero);
        spinnerGenero.setOnItemSelectedListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.botonBack:
                onBackPressed();
                break;
            case R.id.botonAnadir:
                anadir();
                break;
        }
    }

    private void anadir() {
        if (!correcto) {
            Toast.makeText(this, R.string.selectGenero, Toast.LENGTH_SHORT).show();
        }else {
            if (caso == 1)
                genero = generoTxt.getText().toString();

            if (caso == 2)
                genero = generos[pos];

            nombre = nombreTxt.getText().toString();
            artista = artistaTxt.getText().toString();
            url = urlTxt.getText().toString();

            UrlValidator validar = new UrlValidator();

            if (genero.length() == 0) {
                Toast.makeText(this, R.string.indicaGenero, Toast.LENGTH_SHORT).show();
            }else if (nombre.length() == 0) {
                Toast.makeText(this, R.string.indicaNombre, Toast.LENGTH_SHORT).show();
            }else if (artista.length() == 0) {
                Toast.makeText(this, R.string.indicaArtista, Toast.LENGTH_SHORT).show();
            }else if (url.length() == 0) {
                Toast.makeText(this, R.string.indicaUrl, Toast.LENGTH_SHORT).show();
            }else if (!validar.isValid(url)) {
                Toast.makeText(this, R.string.noUrl, Toast.LENGTH_SHORT).show();
            }else {
                final CancionYoutube cancionYoutube = new CancionYoutube(nombre, artista, genero, url);

                String mensaje = getResources().getString(R.string.nombre) + cancionYoutube.getNombre() + "\n" + getResources().getString(R.string.artista) + cancionYoutube.getGenero() + "\n" + getResources().getString(R.string.genero) + cancionYoutube.getGenero();

                AlertDialog.Builder alerta =
                        new AlertDialog.Builder(this);
                alerta.setMessage( getResources().getString(R.string.anadir) + "\n" + mensaje)
                        .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MiCancionOperacional mco = MiCancionOperacional.getInstance(getApplicationContext());
                                mco.insertarCancion(cancionYoutube);

                                clean();
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                alerta.show();
            }
        }


    }

    private void clean() {
        caso = -1;
        pos = -1;
        correcto = false;

        nombreTxt.setText("");
        artistaTxt.setText("");
        generoTxt.setText("");
        urlTxt.setText("");

        adaptarSpinner();


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0) {
            correcto = false;
            generoTxt.setVisibility(View.GONE);
        }else if (i == 4) {
            caso = 1;
            correcto = true;
            generoTxt.setVisibility(View.VISIBLE);
        }else {
            caso = 2;
            pos = i;
            correcto = true;
            generoTxt.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
