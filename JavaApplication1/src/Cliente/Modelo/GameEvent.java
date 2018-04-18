/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Modelo;

/**
 *
 * @author img
 */
public class GameEvent {
    
    public enum EventType{
        START,
        PAUSE,
        STOP,
        REANUDAR,
        REINICIAR,
        ARRIBA,
        ABAJO,
        IZQUIERDA,
        DERECHA
        
        
    }
    
   
    private Object datos;
    private EventType evento;
    
    
    
    public GameEvent(EventType evento, Object datos){
        this.evento = evento;
        this.datos = datos;
        
    }

    public Object getDatos() {
        return datos;
    }
    
   
    public EventType getEvento() {
        return evento;
    }
    
    

    
}
