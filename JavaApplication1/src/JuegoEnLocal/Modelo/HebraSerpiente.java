/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoEnLocal.Modelo;


public class HebraSerpiente extends Thread {
    
    private int velocidad; 
    private boolean suspended;
    private Direccion direccion;
    private Serpiente serpiente;
    private String color;
    private int idVentana;
    
    /**
     * 
     * @param color
     * @param velocidad Velocidad minima 1 y velocidad maxima 10
     */
    public HebraSerpiente (String color, int velocidad, Serpiente serpiente, int idVentana){
        this.serpiente = serpiente;
        this.color = color;
        this.velocidad = velocidad;
        this.idVentana = idVentana;
        this.suspended = false;
        this.direccion = Direccion.ARRIBA;
        
        
    }

    public int getIdVenata() {
        return idVentana;
    }

    public String getColor() {
        return color;
    }
    
    public void setDireccion (Direccion direccion){
        this.direccion = direccion;
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
    
    public void girar(Direccion dir) {
        this.direccion = dir;
    }

    public Direccion getDireccion() {
        return direccion;
    }
    
    
    
    
    
    public void run (){    
        while (!Thread.currentThread().isInterrupted()) {
            
            try {
                Thread.sleep(1000/velocidad);
                synchronized (this){
                    while (suspended){
                        this.wait();
                    }
                }
                
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }   
            
            serpiente.moverSerpiente(this.direccion,this.idVentana);            
        }
        
    }

    

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof HebraSerpiente){
            HebraSerpiente hebraSerpiente = (HebraSerpiente) obj;
            return this.idVentana == hebraSerpiente.idVentana;
        }
        else{
            return false;
        }
    }
    
    
    
    
    
 
}
