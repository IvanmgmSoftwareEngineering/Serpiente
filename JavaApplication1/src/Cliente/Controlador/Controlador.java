/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Controlador;

import Cliente.Modelo.GameModel;
import Cliente.Vista.Direccion;

/**
 *
 * @author img
 */
public class Controlador {
    
    private GameModel modelo;
    
    
    public Controlador (GameModel modelo){
        this.modelo = modelo;    
    }
    
    public void start() {
        modelo.start();
    }
    
    public void pause() {
        modelo.pause();
    }
    
   public void reanudar() {
        modelo.reanudar();
    }
   
    public void reiniciar() {
        modelo.reiniciar();
    }
    
    public void finalizarJuego() {
        modelo.finalizarJuego();
    }
    
    public void girarDerecha(Direccion direccion) {
        modelo.girarDerecha(direccion);
    }

    public void girarArriba(Direccion direccion) {
        modelo.girarArriba(direccion);
    }

    public void girarAbajo(Direccion direccion) {
        modelo.girarAbajo(direccion);
    }

    public void girarIzquierda(Direccion direccion) {
        modelo.girarIzquierda(direccion);
    }

    public void comerFruta() {
        modelo.comerFruta();
    }

    public void apareceFruta() {
        modelo.apareceFruta();
    }

    public void crecerSerpiente(Direccion direccion, int tamSerpiente) {
        modelo.creceSerpiente(direccion, tamSerpiente);
    }

    public void girarArriba() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void girarIzquierda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
