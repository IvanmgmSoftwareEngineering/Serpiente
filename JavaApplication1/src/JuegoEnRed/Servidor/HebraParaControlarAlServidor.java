/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoEnRed.Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HebraParaControlarAlServidor extends Thread {
    
    private InterfazParaControlarAlServidor controladorServidor;
    private ServerSocket ss;

    public HebraParaControlarAlServidor(InterfazParaControlarAlServidor controladorServidor, ServerSocket ss) {
        this.controladorServidor = controladorServidor;
        this.ss = ss;
    }
    
    public void run(){
        try {
            while(!this.controladorServidor.isConexionFinalizada()){
                
                try {
                    Thread.sleep(0,000000000000001);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HebraParaControlarAlServidor.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                synchronized(this){
                
                this.controladorServidor.servidorEnviaRecibe();
                }
               
            }
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(HebraParaControlarAlServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
