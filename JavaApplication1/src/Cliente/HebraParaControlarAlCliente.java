/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author img
 */
public class HebraParaControlarAlCliente extends Thread {
    
    private Socket sc;
    private InterfazParaControlarAlCliente controladorCliente;
    private boolean suspended = false;


    public HebraParaControlarAlCliente(Socket sc, InterfazParaControlarAlCliente controladorCliente) {
        this.sc = sc;
        this.controladorCliente = controladorCliente;
        this.suspended = false;

    }
    
    public synchronized void reanudar(){
        this.suspended = false;
        synchronized(this){
            this.notify();
        }
        
    }
    
    public void pausar(){
        this.suspended = true;
    }
    
    public void run(){
        
        try {
            while(!this.controladorCliente.isConexionFinalizada()){
                
                try {
                Thread.sleep(1000/1000000);
                /*synchronized (this){
                    while (suspended){
                        this.wait();
                    }
                }*/
                    
                }
                
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                } 
                
                
                this.controladorCliente.clienteEnviaRecibe();
               
            }
            this.sc.close();
        
        } catch (IOException ex) {
            Logger.getLogger(HebraParaControlarAlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

    

    
    
    
    
}
