package com.pmm.simarro.proyectofinal_christianllopis.pojo;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;
    private String nick, pass, correo;

    public Usuario(int id, String nick, String pass, String correo) {
        this.id = id;
        this.nick = nick;
        this.pass = pass;
        this.correo = correo;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Id: " + id +
                "\nNick: " + nick +
                "\nCorreo: " + correo ;
    }
}
