/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Servidor.Controlador.ControlServidor;
import Servidor.GameModel;
import java.io.IOException;

/**
 *
 * @author Santi
 */
public class MainServidor {
    
    private static final int ALTURA_TABLERO = 40;
    private static final int ANCHURA_TABLERO = 50;
    private static final int VELOCIDAD_SERPIENTE = 3;
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    
        try {
            
            GameModel modelo = new GameModel(ALTURA_TABLERO,ANCHURA_TABLERO);
            ControlServidor controlServidor = new ControlServidor(modelo);
            Boolean sigueBucle = true;
            while(sigueBucle) {
                controlServidor.conectar();
                controlServidor.recibirMensajes();
                controlServidor.leerMensajes();
            }
            
        }
        catch (IOException ioe) {
            System.out.println("Excepción de IO capturada en el main");
        }
                
    }
}
