/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Cliente.ControladorCliente;
import Cliente.VentanaPrincipal;
import Cliente.VentanaPuntuacion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainCliente {
    
    private static final int ALTURA_TABLERO = 40;
    private static final int ANCHURA_TABLERO = 50;
    private static final int VELOCIDAD_SERPIENTE =  1;
    
    public static void main(String[] args) {
        try {
            ControladorCliente controladorCliente = new ControladorCliente();
            VentanaPuntuacion ventanaPuntuacion = new VentanaPuntuacion(1);
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(1,controladorCliente, ALTURA_TABLERO, ANCHURA_TABLERO,VELOCIDAD_SERPIENTE);
            ventanaPrincipal.setVisible(true);
            ventanaPuntuacion.setVisible(true);
            controladorCliente.addObserver(ventanaPrincipal);
            controladorCliente.addObserver(ventanaPuntuacion);
            // Y AQUI YA SE ESPERA A QUE EL CLIENTE PULSE ALGUN BOTON DE LA VISTA
        } catch (IOException ex) {
            Logger.getLogger(MainCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
        
    }
    
}
