package com.serviciomecanico.serviciomecanico.Modelo;

public class Inventario {
    public String nombre;
    public String descripcion;
    public String costo;
    public String cantidad;

    public Inventario() {
        super();
    }

    public Inventario(String nombre, String descripcion, String costo, String cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
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

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
