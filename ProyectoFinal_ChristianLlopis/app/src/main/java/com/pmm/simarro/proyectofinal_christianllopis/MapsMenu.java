package com.pmm.simarro.proyectofinal_christianllopis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pmm.simarro.proyectofinal_christianllopis.utils.Coordenada;

import java.util.HashMap;
import java.util.Map;

public class MapsMenu extends AppCompatActivity implements View.OnClickListener {

    private Button btnExtremoduro;
    private Button btnMarea;
    private Button btnRulo;
    private Button btnSharif;
    private Button btnRayden;
    private Button btnLocoplaya;
    private Map<String, Coordenada> coordenadas = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_menu);

        coordenadas.put("Extremoduro", new Coordenada(40.0329131, -6.0998072));
        coordenadas.put("Marea", new Coordenada(42.8381798, -1.6803089));
        coordenadas.put("Rulo", new Coordenada(42.9974882, -4.157641));
        coordenadas.put("Sharif", new Coordenada(41.65167, -0.9650208));
        coordenadas.put("Rayden", new Coordenada(40.4947687, -3.4367158));
        coordenadas.put("Locoplaya", new Coordenada(27.7709231, -18.0239845));
        coordenadas.put("Simarro", new Coordenada(38.9863421, -0.5376541));

        btnExtremoduro = (Button) findViewById(R.id.buttonExtremoduro);
        btnMarea = (Button) findViewById(R.id.buttonMarea);
        btnRulo = (Button) findViewById(R.id.buttonRulo);
        btnSharif = (Button) findViewById(R.id.buttonSharif);
        btnRayden = (Button) findViewById(R.id.buttonRayden);
        btnLocoplaya = (Button) findViewById(R.id.buttonLocoplaya);

        btnExtremoduro.setOnClickListener(this);
        btnMarea.setOnClickListener(this);
        btnRulo.setOnClickListener(this);
        btnSharif.setOnClickListener(this);
        btnRayden.setOnClickListener(this);
        btnLocoplaya.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Declaramos la intencion de abrir el mapa general
        Intent i = new Intent(getApplicationContext(), MapaActivity.class);
        Coordenada coordenada = null;

        switch (v.getId()) {
            case R.id.buttonExtremoduro:
                coordenada = coordenadas.get("Extremoduro");
                break;
            case R.id.buttonMarea:
                coordenada = coordenadas.get("Marea");
                break;
            case R.id.buttonRulo:
                coordenada = coordenadas.get("Rulo");
                break;
            case R.id.buttonSharif:
                coordenada = coordenadas.get("Sharif");
                break;
            case R.id.buttonRayden:
                coordenada = coordenadas.get("Rayden");
                break;
            case R.id.buttonLocoplaya:
                coordenada = coordenadas.get("Locoplaya");
                break;
            default:
                coordenada = coordenadas.get("Simarro");
                break;
        }

        i.putExtra("COORDENADA", coordenada);

        startActivity(i);
    }
}
