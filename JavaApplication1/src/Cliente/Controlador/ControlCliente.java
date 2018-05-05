/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Controlador;

/**
 *
 * @author Santi
 */

import Cliente.Vista.ViewEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

public class ControlCliente implements Observer {
    /*  
        El controlador del cliente va a mandarle información a la vista
        a través de métodos de las ventanas, y va a recibirla desde
        eventos que observe de ellas (las ventanas serán observables y
        el controlador será un observador de las ventanas).
    
        Para comunicarse con el controlador del servidor usará sockets.
        Se conectará al serversocket y empezará a enviar y recibir comandos.
    
        Además, según el enunciado hay que usar unos mensajes determinados.
    
        Mensajes a recibir: IDC, MOV, PTS, ERR y FIN
        Mensajes a enviar: MOV y FIN
    
        Protocolos propios:
            FRT para decir al cliente que hay una fruta nueva
            MOV ha sido editado para que tenga el número de cliente al final
    */
    
    private Socket socket;
    private JFrame ventanaPrincipal;
    private JFrame ventanaPuntuacion;
    private BufferedReader entradaDatos;
    private DataOutputStream salidaDatos;
    private int idJugador;
    private final String dirServidor = "localhost";
    
    
    public ControlCliente(JFrame ventanaPrincipal, JFrame ventanaPuntuacion) throws IOException {
        this.ventanaPrincipal = ventanaPrincipal;
        this.ventanaPuntuacion = ventanaPuntuacion;
    }
    
    public int conectar() {
        try {
            socket = new Socket(dirServidor, 8000);
            InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
            entradaDatos = new BufferedReader(inputStream);
            salidaDatos = new DataOutputStream(socket.getOutputStream());
            return asignarId(entradaDatos.readLine());
        }
        catch (IOException ioe) {
            //ventanaApp.errorConexion();
        }
    }
    
    // MENSAJES DEL SOCKET A LA VISTA
    
    private int asignarId(String mensaje) {
        return 1; // Por hacer
    }
    
    // MENSAJES DE LA VISTA AL SOCKET
    
    public void update(Observable o, Object arg) {
        ViewEvent evento = (ViewEvent) arg;
        manejarEvento(evento);
    }
    
    private void manejarEvento(ViewEvent evento) {
        switch(evento.getEvento()) {
            case START:
                this.conectar();
                break;
                
            case ARRIBA:
                
                break;
                
            case ABAJO:
                
                break;
                
            case IZQUIERDA:
                
                break;
                
            case DERECHA:
                
                break;
                
            case FINALIZAR_JUEGO:
                
                break;
        }
    }
    
}
