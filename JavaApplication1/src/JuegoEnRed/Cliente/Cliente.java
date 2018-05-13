/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoEnRed.Cliente;


import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Cliente {
    private String nombre;
    private int puntuacion;
    private int idCliente;
    private String color;

    
    
    public Cliente(int idCliente, String nombre, String color) {
        this.idCliente = idCliente;
        this.color = color;
        this.puntuacion = 0;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getColor() {
        return color;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    
}
