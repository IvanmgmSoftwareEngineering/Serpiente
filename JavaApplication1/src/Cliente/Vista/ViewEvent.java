/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Vista;

/**
 *
 * @author Santi
 */
public class ViewEvent {

    public enum EventType{
        START,
        ARRIBA,
        ABAJO,
        IZQUIERDA,
        DERECHA,
        FINALIZAR_JUEGO,
        FIJAR_NOMBRE // Evento entre ventanas, no pasa por socket
        
    }
    
   
    private Object datos1;
    private Object datos2;
    private EventType evento;
    
    
    
    public ViewEvent(EventType evento, Object datos1, Object datos2){
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
