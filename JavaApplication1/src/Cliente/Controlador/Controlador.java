/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Controlador;

import Cliente.Modelo.GameModel;

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
    
}
