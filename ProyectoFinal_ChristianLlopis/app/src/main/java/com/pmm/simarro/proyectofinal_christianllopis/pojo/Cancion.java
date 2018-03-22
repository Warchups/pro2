package com.pmm.simarro.proyectofinal_christianllopis.pojo;

import java.io.Serializable;

public class Cancion implements Serializable {
    int id;
    String nombre;

    public Cancion(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Cancion: " + nombre ;
    }
}
