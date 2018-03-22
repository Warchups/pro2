package com.pmm.simarro.proyectofinal_christianllopis.adaptador;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pmm.simarro.proyectofinal_christianllopis.R;

import java.util.ArrayList;

public class AdaptadorCancionesYoutube extends ArrayAdapter {

    Activity context;
    ArrayList<String> tracks;

    public AdaptadorCancionesYoutube(Fragment context, ArrayList<String> tracks) {
        super(context.getActivity(), R.layout.lista_canciones_elementos, tracks);
        this.context = context.getActivity();
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.lista_canciones_elementos, null);

        TextView mov = (TextView) item.findViewById(R.id.nomCancion);
        mov.setText(tracks.get(position));

        return item;
    }

}
