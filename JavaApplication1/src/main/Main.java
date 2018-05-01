/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Cliente.Controlador.Controlador;
import Servidor.GameModel;
import Cliente.Vista.VentanaPrincipal;
import Cliente.Vista.VentanaPuntuacion;
import javax.swing.SwingUtilities;

/**
 *
 * @author img
 */
public class Main {
    
    
    private static final int ALTURA_TABLERO = 40;
    private static final int ANCHURA_TABLERO = 50;
    private static final int VELOCIDAD_SERPIENTE = 3;
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        SwingUtilities.invokeLater(new Runnable() { 
            //EDT (Event dispatch Thread: es la hebra de despacho de swin, es la hebra que se encarga de despachar la hebras
            //SwingUgilities clase que contiene el metodod static 'invokeLater'
            //Runnable: interfaz que solo tiene el método run.
            // el objetivo de lo anterior es crear una cola de eventos para ir almacenando los eventos
            public void run() {
                GameModel modelo = new GameModel(ALTURA_TABLERO,ANCHURA_TABLERO);
                Controlador controlador = new Controlador(modelo);
                VentanaPuntuacion ventanaPuntuacion1 = new VentanaPuntuacion(1);               
                VentanaPuntuacion ventanaPuntuacion2 = new VentanaPuntuacion(2);
                VentanaPrincipal ventanaPrincipal1 = new VentanaPrincipal(1,controlador, ALTURA_TABLERO, ANCHURA_TABLERO,VELOCIDAD_SERPIENTE);
                VentanaPrincipal ventanaPrincipal2 = new VentanaPrincipal(2,controlador, ALTURA_TABLERO, ANCHURA_TABLERO,VELOCIDAD_SERPIENTE);                
                modelo.addObserver(ventanaPuntuacion1);
                modelo.addObserver(ventanaPuntuacion2);               
                modelo.addObserver(ventanaPrincipal1);
                modelo.addObserver(ventanaPrincipal2);                
                ventanaPuntuacion1.setVisible(true);
                ventanaPuntuacion2.setVisible(true);
                ventanaPrincipal1.setVisible(true);
                ventanaPrincipal2.setVisible(true);
                
            }
        });
    }
    
    
    
}
