package com.pmm.simarro.proyectofinal_christianllopis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pmm.simarro.proyectofinal_christianllopis.Fragments.PantallaYoutubeFragment;

public class CancionesYoutube extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canciones_youtube);

        PantallaYoutubeFragment frgYoutube = (PantallaYoutubeFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentYoutube);

        frgYoutube.mostrarCanciones();
    }


}
