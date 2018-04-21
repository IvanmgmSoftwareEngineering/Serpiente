/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Modelo;

import Cliente.Observable.Observable;
import Cliente.Observable.Observer;
import Cliente.Vista.Direccion;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author img
 */
public class GameModel implements Observable {
    
    private boolean juegoFinalizado;
    private boolean juegoPausado;
    private final int TAM_SERPIENTE_INICIAL = 1;
    
    private List<Observer> observadores;
    
    
    public GameModel (){
        this.observadores = new ArrayList<Observer>();
        
    }
    
    
    public void start() {
        this.juegoPausado = false;
        this.juegoFinalizado = false;
        this.notifyObservers(new GameEvent(GameEvent.EventType.START, TAM_SERPIENTE_INICIAL));
       
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
    
    public void girarDerecha() {
        this.notifyObservers(new GameEvent(GameEvent.EventType.GIRAR_DERECHA, null));
    }
    
    public void girarArriba() {
        this.notifyObservers(new GameEvent(GameEvent.EventType.GIRAR_ARRIBA, null));
    }

    public void girarAbajo() {
        this.notifyObservers(new GameEvent(GameEvent.EventType.GIRAR_ABAJO, null));
    }

    public void girarIzquierda() {
        this.notifyObservers(new GameEvent(GameEvent.EventType.GIRAR_IZQUIERDA, null));
    }
    
    public void comerFruta() {
        this.notifyObservers(new GameEvent(GameEvent.EventType.COMER_FRUTA, null));    
    }
    
    public void apareceFruta() {
        this.notifyObservers(new GameEvent(GameEvent.EventType.APARECE_FRUTA, null));    
       
    }
    
    public void creceSerpiente(Direccion direccion) {
        this.notifyObservers(new GameEvent(GameEvent.EventType.CRECE_SERPIENTE, direccion));    
    }
    
    public void finalizarJuego() {
        this.notifyObservers(new GameEvent(GameEvent.EventType.FINALIZAR_JUEGO, null));
        
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
