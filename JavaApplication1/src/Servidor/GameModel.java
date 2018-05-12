/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.util.Observable;
import java.util.Observer;
import Cliente.Vista.Direccion;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
/**
 *
 * @author img
 */
public class GameModel extends Observable implements Serpiente {
    
    private boolean juegoPausado;
    private boolean dentroLimites;
    private int altura_tablero;
    private int ancho_tablero;
    private List<Jugador> jugadores;
    
    private Posicion posicionFruta;
    private Posicion posicionCabezaInicial;
    
    private List<Observer> observadores;
    
    
    public GameModel (int altura_tablero, int ancho_tablero){
        this.juegoPausado = false;
        this.observadores = new ArrayList<Observer>();
        this.jugadores = new ArrayList<Jugador>();
        this.posicionFruta = new Posicion (altura_tablero/4,ancho_tablero/4);
        this.dentroLimites = true;
        this.posicionCabezaInicial = new Posicion (altura_tablero/2,ancho_tablero/2);
        this.posicionFruta = new Posicion(altura_tablero/4,ancho_tablero/4);
        
        this.altura_tablero = altura_tablero;
        this.ancho_tablero = ancho_tablero;
        
        
    }
    
    
    public void start(int idVentana, String colorSerpiente, int velocidadSerpiente, String nombreCliente) {
        
        Iterator iterador = jugadores.iterator();// Comprobamos que no existe ningun jugador con el mismo nombre y el mismo color de la serpiente
        boolean mismoNombre = false;
        boolean mismoColor = false;
        HebraSerpiente serpienteAux = new HebraSerpiente(null,1,null,idVentana);
        Jugador jugadorAux = new Jugador(idVentana,nombreCliente,serpienteAux,new Posicion(0,0));
        while(iterador.hasNext() && !mismoNombre && !mismoColor){
            Jugador jugador = (Jugador) iterador.next();
            if(jugador.getNombreJugador().equals(jugadorAux.getNombreJugador())){
                mismoNombre = true;
            }
            if((jugador.getSerpiente().getColor().equals(colorSerpiente))){
                mismoColor = true;
            }
        }
        
        if(mismoNombre){
            this.setChanged();
            this.notifyObservers(new GameEvent(GameEvent.EventType.NOMBRE_NO_VALIDO,nombreCliente,idVentana,null,null,null,null));   
        }
        else if(mismoColor){
            this.setChanged();
            this.notifyObservers(new GameEvent(GameEvent.EventType.COLOR_NO_VALIDO,colorSerpiente,nombreCliente,idVentana,null,null,null));   
        }
        else{
           
        jugadores.add(new Jugador(idVentana,nombreCliente,new HebraSerpiente(colorSerpiente,velocidadSerpiente,this,idVentana),posicionCabezaInicial)); // Se crea un nuevo jugador que contiene una serpiente y una lista con las posiciones de la serpiente
        
        
 
        this.setChanged();
        /*this.notifyObservers(new GameEvent(GameEvent.EventType.START, 
                this.jugadores.get(jugadores.size()-1).getPosicionesSerpiente().getFirst(),
                jugadores.get(jugadores.size()-1).getPosicionesSerpiente().getLast(),
                jugadores.get(jugadores.size()-1).getSerpiente().getColor(), 
                nombreCliente, 
                this.posicionFruta, 
                idVentana));*/
        this.notifyObservers(new GameEvent(GameEvent.EventType.START, idVentana, null, null, null, null, null));
        this.setChanged();
        this.notifyObservers(new GameEvent(GameEvent.EventType.NUEVA_FRUTA, this.posicionFruta,null,null,null,null,null));
        
        this.jugadores.get(jugadores.size()-1).getSerpiente().start();
        }
    }
 
    public void pause() {
            this.juegoPausado = true;
            this.jugadores.forEach((jugador) -> {
                jugador.getSerpiente().pausar();
        });
            this.setChanged();
            this.notifyObservers(new GameEvent(GameEvent.EventType.PAUSE, null,null,null,null,null,null));   
    }
    
    public void reanudar() {
            this.juegoPausado = false;
            this.jugadores.forEach((jugador) -> {
                jugador.getSerpiente().reanudar();
        });
            this.setChanged();
            this.notifyObservers(new GameEvent(GameEvent.EventType.REANUDAR, null,null,null,null,null,null));   
    }
    
    public void reiniciar() {
        this.juegoPausado = false;
        this.jugadores.forEach((jugador) -> {
            jugador.getSerpiente().interrupt();
        });
        jugadores.clear();
        this.setChanged();
        this.notifyObservers(new GameEvent(GameEvent.EventType.REINICIAR, null,null,null,null,null,null));
    }
    
    public void girarDerecha(int idVentana) {
        if(this.juegoPausado== false){
            Iterator iterador = jugadores.iterator();
            boolean encontrado = false;
            Jugador jugadorAux = new Jugador(idVentana,"",new HebraSerpiente(null,1,null,idVentana),new Posicion(0,0));
            while(iterador.hasNext() && !encontrado){
                Jugador jugador = (Jugador) iterador.next();
                if(jugador.equals(jugadorAux)){
                    jugadorAux = jugador;
                    encontrado = true;
                }
            }
            if(Direccion.IZQUIERDA != jugadorAux.getSerpiente().getDireccion()){
                jugadorAux.getSerpiente().setDireccion(Direccion.DERECHA);
            }
        }
    }
    
    public void girarArriba(int idVentana) {
        if(this.juegoPausado== false){
            Iterator iterador = jugadores.iterator();
            boolean encontrado = false;
            Jugador jugadorAux = new Jugador(idVentana,"",new HebraSerpiente(null,1,null,idVentana),new Posicion(0,0));
            while(iterador.hasNext() && !encontrado){
                Jugador jugador = (Jugador) iterador.next();
                if(jugador.equals(jugadorAux)){
                    jugadorAux = jugador;
                    encontrado = true;
                }
            }
            if(Direccion.ABAJO != jugadorAux.getSerpiente().getDireccion()){
                jugadorAux.getSerpiente().setDireccion(Direccion.ARRIBA);
            }
        }
    }

    public void girarAbajo(int idVentana) {
        if(this.juegoPausado== false){
            Iterator iterador = jugadores.iterator();
            boolean encontrado = false;
            Jugador jugadorAux = new Jugador(idVentana,"",new HebraSerpiente(null,1,null,idVentana),new Posicion(0,0));
            while(iterador.hasNext() && !encontrado){
                Jugador jugador = (Jugador) iterador.next();
                if(jugador.equals(jugadorAux)){
                    jugadorAux = jugador;
                    encontrado = true;
                }
            }
            if(Direccion.ARRIBA != jugadorAux.getSerpiente().getDireccion()){
                jugadorAux.getSerpiente().setDireccion(Direccion.ABAJO);
            }
        }
    }

    public void girarIzquierda(int idVentana) {
        if(this.juegoPausado== false){
            Iterator iterador = jugadores.iterator();
            boolean encontrado = false;
            Jugador jugadorAux = new Jugador(idVentana,"",new HebraSerpiente(null,1,null,idVentana),new Posicion(0,0));
            while(iterador.hasNext() && !encontrado){
                Jugador jugador = (Jugador) iterador.next();
                if(jugador.equals(jugadorAux)){
                    jugadorAux = jugador;
                    encontrado = true;
                }
            }
            if(Direccion.DERECHA != jugadorAux.getSerpiente().getDireccion()){
                jugadorAux.getSerpiente().setDireccion(Direccion.IZQUIERDA);
            }
        }
    }
    @Override
    public void moverSerpiente(Direccion direccion, int idVentana) {
        try{
            //SOLO MOVEMOS LA SERPIENTE DEL JUGADOR CON nombreCliente
            Iterator iterador = jugadores.iterator();
            boolean encontrado = false;
            Jugador jugadorAux = new Jugador(idVentana,"",new HebraSerpiente(null,1,null,idVentana),new Posicion(0,0));
            while(iterador.hasNext() && !encontrado){
                Jugador jugador = (Jugador) iterador.next();
                if(jugador.equals(jugadorAux)){
                    jugadorAux = jugador;
                    encontrado = true;
                }
            }
            //COMPROBAMOS QUE LA POSICION DE LA CABEZA COINCIDE CON LA POSICION DE LA FRUTA
            boolean encontrado1 = false;
            if(jugadorAux.getPosicionesSerpiente().getFirst().equals(this.posicionFruta)){
                encontrado1 = true;
            }
            
            if(encontrado1){
                jugadorAux.getPosicionesSerpiente().addLast(new Posicion(jugadorAux.getPosicionesSerpiente().getLast().getFila()- direccion.getVariacionFila(),jugadorAux.getPosicionesSerpiente().getLast().getColumna()- direccion.getVariacionColumna()));
                this.nuevaFruta();
                this.setChanged();
                this.notifyObservers(new GameEvent(GameEvent.EventType.NUEVA_FRUTA,this.posicionFruta,idVentana,null,null,null,null));      
                
            }
            
            if( this.serpienteEstaDentroLimitesDelTablero(jugadorAux.getPosicionesSerpiente().getFirst()) && (!this.serpientesChocan(jugadorAux))){
                jugadorAux.getPosicionesSerpiente().addFirst(new Posicion(jugadorAux.getPosicionesSerpiente().getFirst().getFila()+ direccion.getVariacionFila(),jugadorAux.getPosicionesSerpiente().getFirst().getColumna()+ direccion.getVariacionColumna()));
                this.setChanged();
                this.notifyObservers(new GameEvent(GameEvent.EventType.MOVER_SERPIENTE, jugadorAux.getPosicionesSerpiente().getFirst(), jugadorAux.getPosicionesSerpiente().getLast(),jugadorAux.getSerpiente().getColor(),idVentana,null,null));      
                jugadorAux.getPosicionesSerpiente().removeLast();   
            }
            else{
                this.pause();
                //this.finalizarJuego();
            }
        }
            
        catch (ArrayIndexOutOfBoundsException fueraDelTablero) {
            setSerpienteFueraDeLimites();
            System.err.println("fuera de limites");
        }   
    }
    
    private void nuevaFruta() {
        boolean posicionFrutaNoValida = true;
        while(posicionFrutaNoValida){
            Posicion nuevaPosFruta = new Posicion((int) Math.floor(Math.random()*(this.altura_tablero)),(int) Math.floor(Math.random()*(this.ancho_tablero)));
            Iterator iterador = this.jugadores.iterator();
            boolean encontrado = false;
            while(iterador.hasNext() && !encontrado){
                Jugador jugador = (Jugador) iterador.next();
                Iterator iterador1 = jugador.getPosicionesSerpiente().iterator();
                while(iterador1.hasNext() && !encontrado){
                    Posicion posicion = (Posicion) iterador1.next();
                    if(posicion.equals(nuevaPosFruta)){
                        encontrado = true;
                    }
                }
            }
            if(!encontrado){
                //this.notifyObservers(new GameEvent(GameEvent.EventType.NUEVA_FRUTA,nuevaPosFruta,null,null,null,null));                     
                this.posicionFruta = nuevaPosFruta;
                posicionFrutaNoValida = false;
            }
        }
    }
    
    @Override
    public void finalizarJuego() {
        this.setChanged();
        this.notifyObservers(new GameEvent(GameEvent.EventType.FINALIZAR_JUEGO, null,null,null,null,null,null));
        
    }
    
    /*@Override
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
        
    }*/

    private boolean serpientesChocan(Jugador jugador) {
        Posicion posCabeza = jugador.getPosicionesSerpiente().getFirst();
        //Compruebo que la serpiente no choca contra si mismo
        Iterator iterador = jugador.getPosicionesSerpiente().iterator();
        boolean encontrado = false;
        int indice = 0;
        while(iterador.hasNext() && !encontrado){
            Posicion posicion = (Posicion) iterador.next();
            if(posicion.equals(posCabeza) && indice >0){
                encontrado = true;
            }
            indice = indice + 1;
        }
        if(encontrado){
            return true;
        }
        
        //Compruebo que la serpiente no choca contra otros
        Iterator iterador1 = jugadores.iterator();
        boolean encontrado1 = false;
        while(iterador1.hasNext() && !encontrado1){
            Jugador jugador1 = (Jugador) iterador1.next();
            if(!jugador1.equals(jugador)){
                Iterator iterador2 = jugador1.getPosicionesSerpiente().iterator();
                while(iterador2.hasNext() && !encontrado1){
                    Posicion posicion = (Posicion) iterador2.next();
                    if(posicion.equals(posCabeza)){
                        return true;
                    }
                }
            }
        }
        return false;
        
    }

    private boolean serpienteEstaDentroLimitesDelTablero(Posicion posicionCabeza) {
          return 
                (this.dentroLimites)
                && posicionCabeza.getFila() >= 0 
                && posicionCabeza.getColumna() >= 0 
                && posicionCabeza.getFila() <= altura_tablero-1 
                && posicionCabeza.getColumna() <= ancho_tablero-1;
    }
    
    
    private void setSerpienteFueraDeLimites() {
        this.dentroLimites = false;
    }

     

   

    

    

    

    

    

    

    
}
