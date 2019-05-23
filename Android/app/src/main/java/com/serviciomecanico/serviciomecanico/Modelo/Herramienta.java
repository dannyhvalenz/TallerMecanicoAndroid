package com.serviciomecanico.serviciomecanico.Modelo;

public class Herramienta {
    public String nombre;
    public String descripcion;
    public String marca;
    public String cantidad;

    public Herramienta() {
        super();
    }

    public Herramienta(String nombre, String descripcion, String marca, String cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}

