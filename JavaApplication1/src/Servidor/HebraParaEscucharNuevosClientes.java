/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author img
 */
public class HebraParaEscucharNuevosClientes extends Thread {
    private InterfazParaEscucharNuevosClientes controladorServidor;
    private ServerSocket ss;
    private Socket cs;

    public HebraParaEscucharNuevosClientes(InterfazParaEscucharNuevosClientes controladorServidor,Socket cs, ServerSocket ss) {
        this.controladorServidor = controladorServidor;
        this.cs = cs;
        this.ss = ss;
    }
    
    
    
    
    public void run(){
        
        while (true){
            

            try {
                Thread.sleep(1000);
                   
                }
                
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
            } 
            
            
                    this.controladorServidor.escuchaANuevosClientes();
                }
            

          
        }
        
        
        
    }
    

