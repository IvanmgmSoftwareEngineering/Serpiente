/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Vista;

/**
 *
 * @author img
 */
public class PosicionSerpiente {
    
    private int posicionFila;
    private int posicionColumna;
    
    public PosicionSerpiente(int posicionFila, int posicionColumna){
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
    
    
    
    
    
}
