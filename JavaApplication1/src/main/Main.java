/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Cliente.Controlador.Controlador;
import Cliente.Modelo.GameModel;
import Cliente.Vista.VentanaPrincipal;
import Cliente.Vista.VentanaPuntuacion;
import javax.swing.SwingUtilities;

/**
 *
 * @author img
 */
public class Main {
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
                GameModel modelo = new GameModel();
                Controlador controlador = new Controlador(modelo);
                VentanaPuntuacion ventanaPuntuacion = new VentanaPuntuacion();
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(controlador);
                modelo.addObserver(ventanaPuntuacion);
                modelo.addObserver(ventanaPrincipal);
                ventanaPuntuacion.setVisible(true);
                ventanaPrincipal.setVisible(true);
            }
        });
    }
    
    
    
}
