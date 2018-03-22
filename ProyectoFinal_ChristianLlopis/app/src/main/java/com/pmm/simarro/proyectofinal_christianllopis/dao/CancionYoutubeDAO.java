package com.pmm.simarro.proyectofinal_christianllopis.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.pmm.simarro.proyectofinal_christianllopis.bd.MiBD;
import com.pmm.simarro.proyectofinal_christianllopis.pojo.Cancion;
import com.pmm.simarro.proyectofinal_christianllopis.pojo.CancionYoutube;

import java.util.ArrayList;
import java.util.Date;

public class CancionYoutubeDAO implements PojoDAO{
    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        CancionYoutube c = (CancionYoutube) obj;
        contentValues.put("nombre", c.getNombre());
        contentValues.put("artista", c.getArtista());
        contentValues.put("genero", c.getGenero());
        contentValues.put("url", c.getUrl());
        return MiBD.getDB().insert("cancionesyoutube", null, contentValues);
    }

    @Override
    public int update(Object obj) {
        ContentValues contentValues = new ContentValues();
        CancionYoutube c = (CancionYoutube) obj;
        contentValues.put("nombre", c.getNombre());
        contentValues.put("artista", c.getArtista());
        contentValues.put("genero", c.getGenero());
        contentValues.put("url", c.getUrl());

        String condicion = "url = '" + c.getUrl() + "'";

        int resultado = MiBD.getDB().update("cancionesyoutube", contentValues, condicion, null);

        return resultado;
    }

    @Override
    public void delete(Object obj) {
        CancionYoutube c = (CancionYoutube) obj;
        String condicion = "url = '" + c.getUrl() + "'";

        MiBD.getDB().delete("cancionesyoutube", condicion, null);

    }

    @Override
    public Object search(Object obj) {
        CancionYoutube c = (CancionYoutube) obj;
        String condicion = "url = '" + c.getUrl() + "'";

        String[] columnas = {
                "nombre","artista","genero","url"
        };

        Cursor cursor = MiBD.getDB().query("cancionesyoutube", columnas, condicion, null, null, null, null);
        CancionYoutube nuevaCancion = null;
        if (cursor.moveToFirst()) {
            nuevaCancion = new CancionYoutube();
            nuevaCancion.setNombre(cursor.getString(1));
            nuevaCancion.setArtista(cursor.getString(2));
            nuevaCancion.setGenero(cursor.getString(3));
            nuevaCancion.setUrl(cursor.getString(4));
        }

        return nuevaCancion;
    }

    @Override
    public ArrayList<CancionYoutube> getAll() {
        ArrayList<CancionYoutube> listaCanciones = new ArrayList<>();
        String[] columnas = {
                "nombre","artista","genero","url"
        };

        Cursor cursor = MiBD.getDB().query("cancionesyoutube", columnas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                CancionYoutube nuevaCancion = new CancionYoutube();
                nuevaCancion.setNombre(cursor.getString(0));
                nuevaCancion.setArtista(cursor.getString(1));
                nuevaCancion.setGenero(cursor.getString(2));
                nuevaCancion.setUrl(cursor.getString(3));

                listaCanciones.add(nuevaCancion);
            }while(cursor.moveToNext());
        }

        return listaCanciones;
    }

    public ArrayList getCancionesGenero(String genero) {
        ArrayList<CancionYoutube> listaCanciones = new ArrayList<CancionYoutube>();
        String condicion = "genero = '" + genero + "'";
        String[] columnas = {
                "nombre","artista","genero","url"
        };
        Cursor cursor = MiBD.getDB().query("cancionesyoutube", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                CancionYoutube c = new CancionYoutube();
                c.setNombre(cursor.getString(0));
                c.setArtista(cursor.getString(1));
                c.setGenero(cursor.getString(2));
                c.setUrl(cursor.getString(3));

                listaCanciones.add(c);

            } while(cursor.moveToNext());
        }
        return listaCanciones;
    }

    public ArrayList getCancionesGeneroOtro() {
        ArrayList<CancionYoutube> listaCanciones = new ArrayList<CancionYoutube>();
        String condicion = "genero <> 'Rock' AND genero <> 'Rap' AND genero <> 'Pop' ";
        String[] columnas = {
                "nombre","artista","genero","url"
        };
        Cursor cursor = MiBD.getDB().query("cancionesyoutube", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                CancionYoutube c = new CancionYoutube();
                c.setNombre(cursor.getString(0));
                c.setArtista(cursor.getString(1));
                c.setGenero(cursor.getString(2));
                c.setUrl(cursor.getString(3));

                listaCanciones.add(c);

            } while(cursor.moveToNext());
        }
        return listaCanciones;
    }
}
