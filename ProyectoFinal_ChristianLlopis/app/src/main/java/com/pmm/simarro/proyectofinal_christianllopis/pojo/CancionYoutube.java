package com.pmm.simarro.proyectofinal_christianllopis.pojo;

import java.io.Serializable;

public class CancionYoutube implements Serializable {
    private String nombre;
    private String artista;
    private String genero;
    private String url;

    public CancionYoutube(String nombre, String artista, String genero, String url) {
        this.nombre = nombre;
        this.artista = artista;
        this.genero = genero;
        this.url = url;
    }

    public CancionYoutube() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return nombre + " - " + artista;
    }
}
