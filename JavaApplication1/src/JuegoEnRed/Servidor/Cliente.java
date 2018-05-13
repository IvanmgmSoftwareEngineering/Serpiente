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
    private String nombre;
    private int puntuacion;
    private int idCliente;
    private Socket socketCliente;
    //private DataOutputStream flujoParaEnviarAlCliente;
    private InputStreamReader flujoParaRecibirDatosCliente;
    private BufferedReader bufferParaRecibirDatosDelCliente;
    private String color;

    
    
    public Cliente(int idCliente, String nombre, String color) {
        this.idCliente = idCliente;
        this.color = color;
        this.puntuacion = 0;
        this.nombre = nombre;
    }

    public Cliente(int idCliente, Socket socketCliente) {
        try {
            this.idCliente = idCliente;
            this.color = "";
            this.socketCliente = socketCliente;
            this.socketCliente.setSoTimeout(1000);
            //this.flujoParaEnviarAlCliente = new DataOutputStream(socketCliente.getOutputStream());
            this.flujoParaRecibirDatosCliente = new InputStreamReader(this.socketCliente.getInputStream());
            this.bufferParaRecibirDatosDelCliente = new BufferedReader(this.flujoParaRecibirDatosCliente);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getColor() {
        return color;
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

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
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

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
      if(obj instanceof Cliente){
            Cliente cliente = (Cliente) obj;
            return this.idCliente == cliente.idCliente;
        }
        else{
            return false;
        }
    }
    
        
    
    
   
}
