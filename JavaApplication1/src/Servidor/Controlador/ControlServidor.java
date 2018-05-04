/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor.Controlador;

/**
 *
 * @author Santi
 */

import Servidor.GameEvent;
import Servidor.GameModel;
import Servidor.Posicion;
import java.net.ServerSocket;
import java.util.Observable;
import java.util.Observer;

public class ControlServidor implements Observer {
    /*
        El controlador del servidor va a mandarle información al modelo
        a través de métodos del modelo, y va a recibirla observándolo
        (modelo será observable y esta clase, observer).
        
        En su conexión con el server socket va a recibir y enviar 
        información usando los caminos adecuados.
    
        Además, según el enunciado hay unos mensajes estándares que
        son los que hay que usar.
    
        Mensajes a recibir: DIR y FIN
        Mensajes a enviar: IDC, MOV, PTS, ERR y FIN
    */
    
    private ServerSocket socket;
    private GameModel modelo;
    private int id;
    
    
    public ControlServidor(int identificador) {
        this.id = identificador;
    }
    
    private String idC() {
        return Integer.toString(this.id);
    }
    
    
    // MENSAJES DEL SOCKET AL MODELO
    
    public void girarDerecha(int idVentana) {
        modelo.girarDerecha(idVentana);
    }

    public void girarArriba(int idVentana) {
        modelo.girarArriba(idVentana);
    }

    public void girarAbajo(int idVentana) {
        modelo.girarAbajo(idVentana);
    }

    public void girarIzquierda(int idVentana) {
        modelo.girarIzquierda(idVentana);
    }
    
    
    
    // MENSAJES DEL MODELO AL SOCKET
    
    public void update(Observable o, Object arg) {
        GameEvent evento = (GameEvent) arg;
        manejarEvento(evento);
    }
    
    private void manejarEvento (GameEvent evento) {
        switch (evento.getEvento()){
            
            case START: 
                /*if(this.idVentana == (int) evento.getDatos6()){
                    this.start();
                }
                    this.dibujaFruta((Posicion)evento.getDatos5());
                    this.dibujaSerpiente((Posicion)evento.getDatos1(),(Posicion) evento.getDatos2(),(String) evento.getDatos3(), (int) evento.getDatos6());*/
                enviarSocket("IDC;" + idC() + ":");
                
                break; 

            case MOVER_SERPIENTE: 
//                    this.dibujaSerpiente((Posicion)evento.getDatos1(),(Posicion) evento.getDatos2(),(String) evento.getDatos3(), (int)evento.getDatos4());
                Posicion posNueva = (Posicion)evento.getDatos1();
                Posicion posBlanco = (Posicion) evento.getDatos2();
                int nuevaY = posNueva.getFila();
                int nuevaX = posNueva.getColumna();
                int blancoY = posBlanco.getFila();
                int blancoX = posBlanco.getColumna();
                int idJugador = (int) evento.getDatos4();
                enviarSocket("MOV;" + idJugador + ";" + nuevaX + ";" + nuevaY + ";" + blancoX + ";" + blancoY + ":");
                break; 
                    
            case PUNTOS: 
                
                break;
                    
            case NUEVA_FRUTA: 
                // Este no sale en el enunciado pero se pueden 
                // añadir más protocolos, y este es necesario
                
//                    this.dibujaFruta((Posicion) evento.getDatos1());
                break;         
                   
            case FINALIZAR_JUEGO:
//                    this.finalizarJuego();
                break;
             
            case ERROR:
                
                break;
                    
                    
        } 
    }
    
    private void enviarSocket (String mensaje) {
        
    }
    
    
    
}
