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

import Cliente.Vista.*;
import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

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
            DIR ha sido editado para que tenga el número de cliente al final
    */
    
    private Socket socket;
    private VentanaPrincipal ventanaPrincipal;
    private VentanaPuntuacion ventanaPuntuacion;
    private BufferedReader entradaDatos;
    private DataOutputStream salidaDatos;
    private int idJugador;
    private final String dirServidor = "localhost";
    
    
    public ControlCliente(VentanaPrincipal vPrin, VentanaPuntuacion vPun) throws IOException {
        this.ventanaPrincipal = vPrin;
        this.ventanaPuntuacion = vPun;
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
            return -1;
        }
    }
        
    private int asignarId(String mensaje) {
        String[] partes = mensaje.split(";");
        if ("IDC".equals(partes[0].trim())) {
            idJugador = Integer.parseInt(partes[1].trim());
            return idJugador;
        }
        else {
            return -1; // Te devuelven -1 si hay errores
        }
    }
    
    // MENSAJES DEL SOCKET A LA VISTA
    
    private void leerMensajes() {
        try {
            while (entradaDatos.ready()) {
                procesarMensaje(entradaDatos.readLine());
            }
        }
        catch (IOException ioe) {
            //ventanaPrincipal.errorDeConexion();
        }
    }
    
    private void procesarMensaje(String mensaje) {
        String[] partes = mensaje.split(";");
        switch (partes[0]) {
            case "MOV":
                int idSerpiente = Integer.parseInt(partes[1].trim());
                int nuevoX = Integer.parseInt(partes[2].trim());
                int nuevoY = Integer.parseInt(partes[3].trim());
                int blancoX = Integer.parseInt(partes[4].trim());
                int blancoY = Integer.parseInt(partes[5].trim());
                Posicion posNueva = new Posicion(nuevoY,nuevoX); // x es la columna; y es la fila
                Posicion posBlanco = new Posicion(blancoY,blancoX);
                ventanaPrincipal.dibujaSerpiente(posNueva,posBlanco,"black",idSerpiente);
                break;
                
            case "PTS":
                int jugPuntos = Integer.parseInt(partes[1].trim());
                int puntuacion = Integer.parseInt(partes[2].trim());
                //ventanaPuntuacion.actualizar(jugPuntos, puntuacion);
                // Hay que impementar este método en VentanaPuntuacion
                break;
                
            case "ERR":
                String textoDescriptivo = partes[1];
                //ventanaPrincipal.errorServidor(textoDescriptivo);
                break;
                
            case "FIN":
                ventanaPrincipal.finalizarJuego();
                break;
                
            case "FRT":
                int frutaX = Integer.parseInt(partes[1].trim());
                int frutaY = Integer.parseInt(partes[2].trim());
                Posicion posFruta = new Posicion(frutaX,frutaY);
                ventanaPrincipal.dibujaFruta(posFruta);
                break;
        }
    }
    
    
    // MENSAJES DE LA VISTA AL SOCKET
    
    @Override
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
                enviarSocket("DIR; ARRIBA; " + idJugador);
                break;
                
            case ABAJO:
                enviarSocket("DIR; ABAJO; " + idJugador);
                break;
                
            case IZQUIERDA:
                enviarSocket("DIR; IZQ; " + idJugador);
                break;
                
            case DERECHA:
                enviarSocket("DIR; DER; " + idJugador);
                break;
                
            case FINALIZAR_JUEGO:
                enviarSocket("FIN; " + idJugador);
                break;
        }
    }
    
    // Envía un mensaje al socket del servidor
    private void enviarSocket(String mensaje) {
        try {
            salidaDatos.writeUTF(mensaje);
        }
        catch (IOException ioe) {
            //ventana.errorDeConexion();
        }
    }
    
}