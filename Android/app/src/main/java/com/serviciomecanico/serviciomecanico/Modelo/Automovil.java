package com.serviciomecanico.serviciomecanico.Modelo;

public class Automovil {
    public String placa;
    public String marca;
    public String modelo;
    public String linea;
    public String color;
    public String urlImagenAutomovil;

    public Automovil() {
        super();
    }

    public Automovil(String placa, String marca, String modelo, String linea, String color, String urlImagenAutomovil) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.linea = linea;
        this.color = color;
        this.urlImagenAutomovil = urlImagenAutomovil;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUrlImagenAutomovil() {
        return urlImagenAutomovil;
    }

    public void setUrlImagenAutomovil(String urlImagenAutomovil) {
        this.urlImagenAutomovil = urlImagenAutomovil;
    }
}

