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
        FINALIZAR_JUEGO, 
        GIRAR_ARRIBA, 
        GIRAR_ABAJO, 
        GIRAR_IZQUIERDA, 
        GIRAR_DERECHA, 
        COMER_FRUTA,
        APARECE_FRUTA, 
        CRECE_SERPIENTE, 
        
        
    }
    
   
    private Object datos1;
    private Object datos2;
    private EventType evento;
    
    
    
    public GameEvent(EventType evento, Object datos1, Object datos2){
        this.evento = evento;
        this.datos1 = datos1;
        this.datos2 = datos2;
        
    }

    public Object getDatos1() {
        return datos1;
    }
    
    public Object getDatos2() {
        return datos2;
    }
    
   
    public EventType getEvento() {
        return evento;
    }
    
    

    
}
