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
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
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
    
        Protocolos propios:
            FRT para decir al cliente que hay una fruta nueva
            DIR ha sido editado para que tenga el número de cliente al final
    */
    
    private ServerSocket serverSocket;
    private GameModel modelo;
    private ArrayList<BufferedReader> entradaDatos;
    private ArrayList<DataOutputStream> salidaDatos;
    private int numJugadores = 0;
    private ArrayList<String> listaMensajes;
    
    
    public ControlServidor(GameModel modelo) throws IOException {
        this.modelo = modelo;
        serverSocket = new ServerSocket(8000);
    }
    
    public int conectar() throws IOException {
        Socket socket = serverSocket.accept();
        InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
        this.entradaDatos.add( numJugadores, new BufferedReader(inputStream) );
        this.salidaDatos.add( numJugadores, new DataOutputStream(socket.getOutputStream()) ); 
        numJugadores ++;
        return numJugadores - 1; // El identificador empieza a contar a partir del 0
    }
    
    
    
    // MENSAJES DEL SOCKET AL MODELO
    
    private void recibirMensajes() {
        Iterator iterator = entradaDatos.iterator();
        BufferedReader flujoEntrada;
        try {
            while(iterator.hasNext()) {
                flujoEntrada = (BufferedReader) iterator.next();
                while (flujoEntrada.ready()) {
                    listaMensajes.add( flujoEntrada.readLine() );
                }
            }
        }
        catch (IOException ioe) {
            //modelo.errorDeConexion();
        }
    }
    
    private void leerMensajes() {
        Iterator iterator = listaMensajes.iterator();
        String mensaje;
        while(iterator.hasNext()) {
            mensaje = (String) iterator.next();
            procesarMensaje(mensaje);
        } 
        listaMensajes.clear();
    }
    
    private void procesarMensaje(String mensaje) {
        String[] partes = mensaje.split(";");
        String cabecera = partes[0];
        switch (cabecera) {
            case "DIR":
                String dir = partes[1].trim();
                int idC = Integer.parseInt(partes[2].trim());
                switch (dir) {
                    case "ARRIBA":
                        girarArriba(idC);
                        break;
                    case "ABAJO":
                        girarAbajo(idC);
                        break;
                    case "DER":
                        girarDerecha(idC);
                        break;
                    case "IZQ":
                        girarIzquierda(idC);
                        break;
                }
                break;
                
            case "FIN":
                int idFin = Integer.parseInt(partes[1].trim());
                finalizar(idFin);
                break;
        }
    }
    
    
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
    
    public void finalizar(int id) {
        modelo.finalizarJuego();
    }
    
    
    
    // MENSAJES DEL MODELO AL SOCKET
    
    @Override
    public void update(Observable o, Object arg) {
        GameEvent evento = (GameEvent) arg;
        manejarEvento(evento);
    }
    
    private void manejarEvento (GameEvent evento) {
        switch (evento.getEvento()){
            
            case START: 
                int idStart = (int) evento.getDatos6();
                enviarSocket(idStart, "IDC; " + idStart + ":");             
                break; 

            case MOVER_SERPIENTE: 
                Posicion posNueva = (Posicion) evento.getDatos1();
                Posicion posBlanco = (Posicion) evento.getDatos2();
                int nuevaY = posNueva.getFila();
                int nuevaX = posNueva.getColumna();
                int blancoY = posBlanco.getFila();
                int blancoX = posBlanco.getColumna();
                int idJugador = (int) evento.getDatos4();
                enviarTodos("MOV; " + idJugador + ";" + nuevaX + ";" + nuevaY + ";" + blancoX + ";" + blancoY);
                break; 
                    
            case PUNTOS: 
                int jugPuntos = (int) evento.getDatos1();
                int puntos = (int) evento.getDatos2();
                enviarTodos("PTS; " + jugPuntos + "; " + puntos);
                break;
                    
            case NUEVA_FRUTA: 
                // Este no sale en el enunciado pero se pueden 
                // añadir más protocolos, y este es necesario
                Posicion posFruta = (Posicion) evento.getDatos1();
                int frutaX = posFruta.getColumna();
                int frutaY = posFruta.getFila();
                enviarTodos("FRT; " + frutaX + "; " + frutaY);
                break;         
                   
            case FINALIZAR_JUEGO:
                int idFin = (int) evento.getDatos1(); 
                // Cuando se use este evento, especificar para quién se acaba el juego
                enviarTodos("FIN; " + idFin);
                break;
             
            case ERROR:
                String textoDescriptivo = (String) evento.getDatos1();
                enviarTodos("ERR; " + textoDescriptivo);
                break;
                    
                    
        } 
    }
    
    // Envía el mensaje a un jugador en concreto, identificado por el número
    private void enviarSocket (int identificador, String mensaje) {
        DataOutputStream flujoSalida = salidaDatos.get(identificador);
        try {
            flujoSalida.writeUTF(mensaje);
        }
        catch (IOException ioe) {
            //modelo.errorDeConexion();
        }
    }
    
    //Envía el mensaje a todos los jugadores que haya conectados
    private void enviarTodos (String mensaje) {
        Iterator iterator = salidaDatos.iterator();
        DataOutputStream flujoSalida;
        try {
            while(iterator.hasNext()) {
                flujoSalida = (DataOutputStream) iterator.next();
                flujoSalida.writeUTF(mensaje);
            }
        }
        catch (IOException ioe) {
            //modelo.errorDeConexion();
        }
    }
    
    
    
}
