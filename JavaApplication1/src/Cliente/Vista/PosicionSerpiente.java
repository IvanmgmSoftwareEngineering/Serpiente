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
    
    public int posicionFila;
    public int posicionColumna;
    
    public PosicionSerpiente(int posicionFila, int posicionColumna){
        this.posicionFila = posicionFila;
        this.posicionColumna = posicionColumna;
    }

    public int getPosicionFila() {
        return posicionFila;
    }

    public int getPosicionColumna() {
        return posicionColumna;
    }

    public void setPosicionFila(int posicionFila) {
        this.posicionFila = posicionFila;
    }

    public void setPosicionColumna(int posicionColumna) {
        this.posicionColumna = posicionColumna;
    }
    
    
    
    
    
}
