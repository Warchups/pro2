package com.pmm.simarro.proyectofinal_christianllopis.adaptador;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pmm.simarro.proyectofinal_christianllopis.R;
import com.pmm.simarro.proyectofinal_christianllopis.pojo.Cancion;

import java.util.ArrayList;

public class AdaptadorCanciones extends ArrayAdapter {
    Activity context;
    ArrayList<Cancion> canciones;

    public AdaptadorCanciones(@NonNull Activity context, ArrayList<Cancion> canciones) {
        super(context, R.layout.lista_canciones_elementos,canciones);
        this.context = context;
        this.canciones = canciones;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.lista_canciones_elementos, null);

        TextView mov = (TextView) item.findViewById(R.id.nomCancion);
        mov.setText(canciones.get(position).getNombre());

        return item;
    }
}
