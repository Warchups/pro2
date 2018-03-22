package com.pmm.simarro.proyectofinal_christianllopis.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pmm.simarro.proyectofinal_christianllopis.API.MiCancionOperacional;
import com.pmm.simarro.proyectofinal_christianllopis.R;
import com.pmm.simarro.proyectofinal_christianllopis.ReproductorYoutube;
import com.pmm.simarro.proyectofinal_christianllopis.adaptador.AdaptadorCancionesYoutube;
import com.pmm.simarro.proyectofinal_christianllopis.bd.MiBD;
import com.pmm.simarro.proyectofinal_christianllopis.pojo.CancionYoutube;

import java.util.ArrayList;


public class CancionesYoutubeFragment extends Fragment {
    private ListView cancionesLista;
    private ArrayList<CancionYoutube> arrayYoutube;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_canciones_youtube, container, false);
        cancionesLista = (ListView)v.findViewById(R.id.cancionesLista);
        arrayYoutube = (ArrayList<CancionYoutube>) getArguments().getSerializable("TRACKS");
        mostrarCanciones();
        return v;
    }

    public void mostrarCanciones() {
        if (arrayYoutube.size() > 0){

            final String[] tracks = new String[arrayYoutube.size()];
            for(int i=0;i<arrayYoutube.size();i++){
                tracks[i] = arrayYoutube.get(i).toString();

            }

            ArrayList<String> mensajes = new ArrayList<>();

            for (int i = 0; i < tracks.length ; i++) {
                mensajes.add(tracks[i]);
            }

            cancionesLista.setAdapter(new AdaptadorCancionesYoutube(this, mensajes));

            cancionesLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final int pos = position;

                    AlertDialog.Builder alerta =
                            new AlertDialog.Builder(getContext());
                    alerta.setMessage(R.string.verBorrar)
                            .setPositiveButton(R.string.ver, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    final String [] url = arrayYoutube.get(pos).getUrl().split("v=");

                                    if (url.length > 1) {
                                        Intent intent = new Intent(getContext(), ReproductorYoutube.class);

                                        intent.putExtra("CANCION", arrayYoutube.get(pos));

                                        startActivity(intent);

                                    }else {
                                        Uri uri = Uri.parse(arrayYoutube.get(pos).getUrl());
                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                        startActivity(intent);
                                    }

                                }
                            })
                            .setNegativeButton(R.string.borrar, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MiCancionOperacional mco =  MiCancionOperacional.getInstance(getContext());
                                    mco.delete(arrayYoutube.get(pos));
                                    arrayYoutube.remove(pos);
                                    mostrarCanciones();
                                    dialog.cancel();
                                }
                            });
                    alerta.show();

                }
            });

        } else {
            ArrayList<String> vacio =  new ArrayList<>();
            vacio.add(getResources().getString(R.string.sinCanciones));
            cancionesLista.setAdapter(new AdaptadorCancionesYoutube(this, vacio));
        }

    }
}
