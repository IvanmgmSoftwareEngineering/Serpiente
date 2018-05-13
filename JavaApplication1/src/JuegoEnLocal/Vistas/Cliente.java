/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoEnLocal.Vistas;

import javax.swing.JTextField;


public class Cliente {
    
    private int idCliente;
    private String nombre;
    private String Color;
    private JTextField textArea;
    private int puntuacion;

    public Cliente(int idCliente, String nombre, String Color, JTextField textArea) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.Color = Color;
        this.textArea = textArea;
        this.puntuacion = 0;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getColor() {
        return Color;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
    
    

    public JTextField getTextArea() {
        return textArea;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public void setTextArea(JTextField textArea) {
        this.textArea = textArea;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    
    
    public boolean equals(Object obj) {
      if(obj instanceof Cliente){
            Cliente cliente = (Cliente) obj;
            return this.idCliente == cliente.idCliente;
        }
        else{
            return false;
        }
    }
    
    
    
}
