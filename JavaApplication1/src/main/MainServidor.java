/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Servidor.ControladorServidor;
import Servidor.GameModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author img
 */
public class MainServidor {
    private static final int ALTURA_TABLERO = 40;
    private static final int ANCHURA_TABLERO = 50;

    
    public static void main(String[] args) {
        try {
            GameModel modelo = new GameModel(ALTURA_TABLERO,ANCHURA_TABLERO);
            ControladorServidor controladorServidor = new ControladorServidor(modelo);
            modelo.addObserver(controladorServidor);
            controladorServidor.iniciaServidor();
        } catch (IOException ex) {
            Logger.getLogger(MainServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
}
