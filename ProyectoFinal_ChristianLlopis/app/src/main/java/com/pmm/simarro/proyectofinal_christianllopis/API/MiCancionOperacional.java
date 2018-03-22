package com.pmm.simarro.proyectofinal_christianllopis.API;

import android.content.Context;

import com.pmm.simarro.proyectofinal_christianllopis.bd.MiBD;
import com.pmm.simarro.proyectofinal_christianllopis.pojo.CancionYoutube;

import java.util.ArrayList;

public class MiCancionOperacional {

    private MiBD miBD;

    protected MiCancionOperacional(Context context) {
        miBD = MiBD.getInstance(context);
    }

    private static MiCancionOperacional instance = null;

    public static MiCancionOperacional getInstance(Context context) {
        if(instance == null) {
            instance = new MiCancionOperacional(context);
        }
        return instance;
    }

    public void insertarCancion(CancionYoutube c) {
        miBD.insercionCancion(c);
    }

    public ArrayList<CancionYoutube> getAll(){
        return miBD.getCancionYoutubeDAO().getAll();
    }

    public ArrayList getCancionesGenero(int genero) {
        switch (genero) {
            case 0:
                return miBD.getCancionYoutubeDAO().getCancionesGenero("Rock");
            case 1:
                return miBD.getCancionYoutubeDAO().getCancionesGenero("Rap");
            case 2:
                return miBD.getCancionYoutubeDAO().getCancionesGenero("Pop");
            default:
                return miBD.getCancionYoutubeDAO().getCancionesGeneroOtro();
        }

    }

    public void delete(CancionYoutube c) {
        miBD.getCancionYoutubeDAO().delete(c);
    }
}
