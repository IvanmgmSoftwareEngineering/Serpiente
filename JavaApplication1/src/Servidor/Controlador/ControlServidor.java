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
    
    // MENSAJES DEL SOCKET AL MODELO
    
    
    
    // MENSAJES DEL MODELO AL SOCKET
    
    public void update(Observable o, Object arg) {
        GameEvent evento = (GameEvent) arg;
        manejarEvento(evento);
    }
    
    public void manejarEvento (GameEvent evento) {
        switch (evento.getEvento()){
            
            case START: 
                /*if(this.idVentana == (int) evento.getDatos6()){
                    this.start();
                }
                    this.dibujaFruta((Posicion)evento.getDatos5());
                    this.dibujaSerpiente((Posicion)evento.getDatos1(),(Posicion) evento.getDatos2(),(String) evento.getDatos3(), (int) evento.getDatos6());*/
                    break; 

            case MOVER_SERPIENTE: 
//                    this.dibujaSerpiente((Posicion)evento.getDatos1(),(Posicion) evento.getDatos2(),(String) evento.getDatos3(), (int)evento.getDatos4());
                    break; 
                    
            case PUNTOS: 
                
                break;
                    
            case NUEVA_FRUTA: //no hay mensaje en el enunciado para crear fruta nueva, pero debería haberlo
//                    this.dibujaFruta((Posicion) evento.getDatos1());
                    break;         
                   
            case FINALIZAR_JUEGO:
//                    this.finalizarJuego();
                    break;
             
            case ERROR:
                
                break;
                    
                    
        } 
    }
    
    
    
}
