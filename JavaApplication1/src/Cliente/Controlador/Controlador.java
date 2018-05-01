/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Controlador;

import Servidor.GameModel;
import Cliente.Vista.Direccion;
import Servidor.Posicion;
import java.util.Deque;

/**
 *
 * @author img
 */
public class Controlador {
    
    private GameModel modelo;
    
    
    public Controlador (GameModel modelo){
        this.modelo = modelo;    
    }
    
    public void start(int idVentana,String colorSerpiente, int velocidadSerpiente, String nombreCliente) {
        modelo.start(idVentana,colorSerpiente, velocidadSerpiente, nombreCliente);
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
    
    public void girarDerecha(int idVentana) {
        modelo.girarDerecha(idVentana);
    }

    public void girarArriba(int idVentana) {
        modelo.girarArriba(idVentana);
    }

    public void girarAbajo(int idVentana) {
        modelo.girarAbajo(idVentana);
    }

    public void girarIzquierda(int idVentana) {
        modelo.girarIzquierda(idVentana);
    }


    //public void crecerSerpiente(Direccion direccion, int tamSerpiente) {
       // modelo.creceSerpiente(direccion, tamSerpiente);
    //}

    public void girarArriba() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void girarIzquierda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
