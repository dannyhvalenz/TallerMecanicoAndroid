package com.serviciomecanico.serviciomecanico.Modelo;

public class Reparacion {
    public String tipo;
    public String descripcionFalla;
    public String descripcionMantenimiento;
    public String kilometraje;
    public String costo;
    public String urlImagenAReparacion;

    public Reparacion() {
        super();
    }

    public Reparacion(String tipo, String descripcionFalla, String descripcionMantenimiento, String kilometraje, String costo, String urlImagenAReparacion) {
        this.tipo = tipo;
        this.descripcionFalla = descripcionFalla;
        this.descripcionMantenimiento = descripcionMantenimiento;
        this.kilometraje = kilometraje;
        this.costo = costo;
        this.urlImagenAReparacion = urlImagenAReparacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcionFalla() {
        return descripcionFalla;
    }

    public void setDescripcionFalla(String descripcionFalla) {
        this.descripcionFalla = descripcionFalla;
    }

    public String getDescripcionMantenimiento() {
        return descripcionMantenimiento;
    }

    public void setDescripcionMantenimiento(String descripcionMantenimiento) {
        this.descripcionMantenimiento = descripcionMantenimiento;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getUrlImagenAReparacion() {
        return urlImagenAReparacion;
    }

    public void setUrlImagenAReparacion(String urlImagenAReparacion) {
        this.urlImagenAReparacion = urlImagenAReparacion;
    }
}
