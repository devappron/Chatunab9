package com.ingelecron.chatunab9.data.db.modelos;

public class Contacto {

    private String id;
    private String foto;
    private String nombre;
    private String especie;
//    private String correo;

    public Contacto() {
    }

    public Contacto(String id, String foto, String nombre, String especie) {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.especie = especie;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
}
