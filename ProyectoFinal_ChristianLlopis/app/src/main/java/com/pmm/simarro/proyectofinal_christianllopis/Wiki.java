package com.pmm.simarro.proyectofinal_christianllopis;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import java.io.IOException;

public class Wiki extends AppCompatActivity implements View.OnClickListener {

    private EditText busquedaWikipedia;
    private ImageButton botonSearch;
    private String url = "https://es.wikipedia.org/wiki/";
    private String searchUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki);

        busquedaWikipedia = (EditText) findViewById(R.id.busquedaWikipedia);
        botonSearch = (ImageButton) findViewById(R.id.botonSearch);

        botonSearch.setOnClickListener(this);
    }

    private void abrirEnlacee() throws IOException {
        String busqueda = busquedaWikipedia.getText().toString();

        if (busqueda.length() > 0) {
            String [] aux = busqueda.split(" ");

            if (aux.length > 1)
                searchUrl = url + formatearURL(aux);
            else
                searchUrl = url + busqueda;

            Uri uri = Uri.parse(searchUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else {
            Toast.makeText(this, R.string.introduceBusqueda, Toast.LENGTH_SHORT).show();
        }
    }

    private String formatearURL(String [] aux) {
        String busqueda = "";

        for (int i = 0 ; i < aux.length ; i++) {
            busqueda += aux[i] + "_";
        }

        return busqueda;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.botonSearch:
                try {
                    abrirEnlacee();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
