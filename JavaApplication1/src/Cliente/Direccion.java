/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

/**
 *
 * @author img
 */
 public enum Direccion{
        
        DERECHA(0,1),
        IZQUIERDA(0,-1),
        ARRIBA(-1,0),
        ABAJO(1,0);
        
        private int variacionFila;
        private int variacionColumna;
        
        private Direccion (int variacionFila, int variacionColumna) {
            this.variacionFila = variacionFila;
            this.variacionColumna = variacionColumna;
        }

    public int getVariacionFila() {
        return variacionFila;
    }

    public int getVariacionColumna() {
        return variacionColumna;
    }

    public static Direccion getDERECHA() {
        return DERECHA;
    }

    public static Direccion getIZQUIERDA() {
        return IZQUIERDA;
    }

    public static Direccion getARRIBA() {
        return ARRIBA;
    }

    public static Direccion getABAJO() {
        return ABAJO;
    }
    
    
        
        
    }
