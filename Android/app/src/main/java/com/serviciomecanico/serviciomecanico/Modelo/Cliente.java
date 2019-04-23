package com.serviciomecanico.serviciomecanico.Modelo;

public class Cliente {
    public String nombre;
    public String correo;
    public String telefono;
    public String urlImagen;
    public String latitud;
    public String longitud;

    public Cliente() {
        super();
    }

    public Cliente(String nombre, String correo, String telefono, String urlImagen, String latitud, String longitud) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.urlImagen = urlImagen;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
