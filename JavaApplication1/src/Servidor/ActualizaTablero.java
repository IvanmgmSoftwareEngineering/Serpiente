/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.JPanel;
/**
 *
 * @author img
 */
public class ActualizaTablero extends Thread {
    
    int vel = 10;
    volatile boolean ejecutar = true;

    public void run(){
        
        while (ejecutar){
           try {
              Thread.sleep((1/vel)*5000);

                    
                
           
                
              
               
           }catch (InterruptedException ex){
            System.err.print("Error en actualiza tablero");
           }  
           
            //Modificación de la posicion de un elemento movil en el tablero
            //actualizaPosicion();
               
               
               
            
            
            
        }
        
    
}
    
    public void detener (){
        ejecutar = false;
    }
    
}
