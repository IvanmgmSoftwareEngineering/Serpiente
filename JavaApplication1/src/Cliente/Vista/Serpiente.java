/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Vista;
/**
 *
 * @author img
 */
public class Serpiente extends Thread {
    
    private int velocidad; 
    private boolean suspended;
    private Direccion direccion;
    private GUISerpiente guiSerpiente;
    private String color;
    
    /**
     * 
     * @param color
     * @param velocidad Velocidad minima 1 y velocidad maxima 10
     */
    public Serpiente (String color, int velocidad, GUISerpiente guiSerpiente){
        this.guiSerpiente = guiSerpiente;
        this.color = color;
        this.velocidad = velocidad;
        this.suspended = false;
        this.direccion = Direccion.ARRIBA;
        
        
    }
    
    public synchronized void reanudar(){
        suspended = false;
        notify();
    }
    
    public void pausar(){
        suspended = true;
    }
    
    public void girar(Direccion dir) {
        this.direccion = dir;
    }

    public Direccion getDireccion() {
        return direccion;
    }
    
    
    
    
    
    public void run (){    
        guiSerpiente.definirColorSerpiente(this.color);
        while (guiSerpiente.serpienteEstaDentroLimitesDelTablero() && !Thread.currentThread().isInterrupted()) {
            
            try {
                Thread.sleep(1000/velocidad);
                synchronized (this){
                    while (suspended){
                        wait();
                    }
                }
                
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }   
            
            guiSerpiente.moverSerpiente(direccion);
            guiSerpiente.mostrarCoordenadasCabeza();
            
        }
        if(!guiSerpiente.serpienteEstaDentroLimitesDelTablero()){
            guiSerpiente.finalizarJuego();
        }
    }
    
 
}
