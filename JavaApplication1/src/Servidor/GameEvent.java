/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

/**
 *
 * @author img
 */
public class GameEvent {
    
    public enum EventType{
        START,
        PAUSE,
        STOP,
        MOVER_SERPIENTE,
        NUEVA_FRUTA,
        REANUDAR,
        REINICIAR,
        FINALIZAR_JUEGO, 
        NOMBRE_NO_VALIDO,
        COLOR_NO_VALIDO,
        PUNTOS,
        ERROR
        
    }
    
   
    private Object datos1;
    private Object datos2;
    private Object datos3;
    private Object datos4;
    private Object datos5;
    private Object datos6;
    private EventType evento;
    
    
    
    public GameEvent(EventType evento, Object datos1, Object datos2, Object datos3, Object datos4, Object datos5, Object datos6){
        this.evento = evento;
        this.datos1 = datos1;
        this.datos2 = datos2;
        this.datos3 = datos3;
        this.datos4 = datos4;
        this.datos5 = datos5;
        this.datos6 = datos6;
        
    }

    public Object getDatos1() {
        return datos1;
    }
    
    public Object getDatos2() {
        return datos2;
    }

    public Object getDatos3() {
        return datos3;
    }

    public Object getDatos4() {
        return datos4;
    }

    public Object getDatos5() {
        return datos5;
    }

    public Object getDatos6() {
        return datos6;
    }
    
    
    
    
    
    
    
    
    
   
    public EventType getEvento() {
        return evento;
    }
    
    

    
}
