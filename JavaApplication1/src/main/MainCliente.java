/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Cliente.Controlador.ControlCliente;
import Cliente.Vista.VentanaPrincipal;
import Cliente.Vista.VentanaPuntuacion;
import java.io.IOException;

/**
 *
 * @author Santi
 */
public class MainCliente {
    // La dirección del servidor al que conectarse desde el cliente se
    // puede cambiar en ControlCliente en el campo dirServidor
    private static final int ALTURA_TABLERO = 40;
    private static final int ANCHURA_TABLERO = 50;
    private static final int VELOCIDAD_SERPIENTE = 3;
    
    
    public static void main(String[] args) {
       
        try {
            VentanaPrincipal vPrin = new VentanaPrincipal(0,ALTURA_TABLERO, ANCHURA_TABLERO,VELOCIDAD_SERPIENTE);
            VentanaPuntuacion vPunt = new VentanaPuntuacion();
            ControlCliente controlCliente = new ControlCliente(vPrin,vPunt);
            vPrin.getObservable().addObserver(controlCliente);
            vPunt.getObservable().addObserver(controlCliente);
            vPrin.setVisible(true);
            vPunt.setVisible(true);
            //int idJugador = controlCliente.conectar();
            //vPrin.setIdVentana(idJugador);
            //vPunt.setIdVentana(idJugador);
//            Boolean sigueBucle = true;
//            while(sigueBucle) {
//                controlCliente.leerMensajes();
//            }
            
        }
        catch (IOException ioe) {
            System.out.println("Excepción de IO capturada en el main");
        }
    }
}
