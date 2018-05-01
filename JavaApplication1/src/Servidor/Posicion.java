/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Cliente.Vista.Direccion;

/**
 *
 * @author img
 */
public class Posicion {
    
    private int posicionFila;
    private int posicionColumna;

    public Posicion() {
    
    }
    
    
    
    public Posicion(int posicionFila, int posicionColumna){
        this.posicionFila = posicionFila;
        this.posicionColumna = posicionColumna;
    }

    public int getFila() {
        return posicionFila;
    }

    public int getColumna() {
        return posicionColumna;
    }

    public void setFila(int posicionFila) {
        this.posicionFila = posicionFila;
    }

    public void setColumna(int posicionColumna) {
        this.posicionColumna = posicionColumna;
    }
    
    public void setPosicion(Direccion direccion){
        if(direccion.getVariacionFila() == 1){ 
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Posicion){
            Posicion posicion = (Posicion) obj;
            return (this.posicionFila == posicion.getFila() && this.posicionColumna == posicion.getColumna());
            
        }
        else{
        return false;
        }
    }
    
    
    
    
    
    
    
}
