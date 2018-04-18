/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Modelo;

import Cliente.Observable.Observable;
import Cliente.Observable.Observer;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author img
 */
public class GameModel implements Observable {
    
    private boolean juegoFinalizado;
    private boolean juegoPausado;
    private final int TAM_SERPIENTE = 1;
    
    private List<Observer> observadores;
    
    
    public GameModel (){
        this.observadores = new ArrayList<Observer>();
        
    }
    
    
    public void start() {
        this.juegoPausado = false;
        this.juegoFinalizado = false;
        this.notifyObservers(new GameEvent(GameEvent.EventType.START, TAM_SERPIENTE));
       
    }
    
    public void pause() {
        this.juegoPausado = true;
        this.notifyObservers(new GameEvent(GameEvent.EventType.PAUSE, null));
    }
    
    public void reanudar() {
        this.juegoPausado = false;
        this.notifyObservers(new GameEvent(GameEvent.EventType.REANUDAR, null));
    }
    
    public void reiniciar() {
        this.juegoPausado = false;
        this.juegoFinalizado = false;
        this.notifyObservers(new GameEvent(GameEvent.EventType.REINICIAR, null));
    }
     
    public void stop() {

    }
    
    public void girarDerecha() {
        this.notifyObservers(new GameEvent(GameEvent.EventType.DERECHA, null));
    }
      
    

    @Override
    public void addObserver(Observer observador) {
        this.observadores.add(observador);
        
    }

    @Override
    public void removeObserver(Observer observador) {
        this.observadores.remove(observador);
    }
    
    private void notifyObservers (GameEvent evento){
        for(Observer observer : observadores){ // para cada obervador en observadores
            observer.notifyEvent(evento);
        }
        
    }
}
