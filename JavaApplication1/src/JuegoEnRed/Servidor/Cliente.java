/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoEnRed.Servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cliente {
    private int idCliente;
    private Socket socketCliente;
    //private DataOutputStream flujoParaEnviarAlCliente;
    private InputStreamReader flujoParaRecibirDatosCliente;
    private BufferedReader bufferParaRecibirDatosDelCliente;



    public Cliente(int idCliente, Socket socketCliente) {
        try {
            this.idCliente = idCliente;
            this.socketCliente = socketCliente;
            this.socketCliente.setSoTimeout(1000);
            //this.flujoParaEnviarAlCliente = new DataOutputStream(socketCliente.getOutputStream());
            this.flujoParaRecibirDatosCliente = new InputStreamReader(this.socketCliente.getInputStream());
            this.bufferParaRecibirDatosDelCliente = new BufferedReader(this.flujoParaRecibirDatosCliente);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getIdCliente() {
        return idCliente;
    }

    public Socket getSocketCliente() {
        return socketCliente;
    }

    public BufferedReader getBufferParaRecibirDatosDelCliente() {
        return bufferParaRecibirDatosDelCliente;
    }
    
    

    
    
    public DataOutputStream creaNuevoFlujoParaEnviarDatosDesdeElServidor(){
        try {
            return new DataOutputStream(this.socketCliente.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public BufferedReader creaNuevoFlujoParaRecibirDatosDesdeElCliente (){
        try {
            return new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
   
}
