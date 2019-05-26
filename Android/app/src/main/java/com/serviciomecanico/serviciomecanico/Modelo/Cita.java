package com.serviciomecanico.serviciomecanico.Modelo;

public class Cita {
    public String hora;
    public String fecha;
    public String cliente;
    public String descripcion;

    public Cita() {super();
    }

    public Cita(String hora, String fecha, String cliente, String descripcion) {
        this.hora = hora;
        this.fecha = fecha;
        this.cliente = cliente;
        this.descripcion = descripcion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
