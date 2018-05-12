/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Cliente.Conexion.Conexion;
import Cliente.Observable.Observable;
import Cliente.Observable.Observer;
import Servidor.GameEvent;
import Servidor.Posicion;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
/**
 *
 * @author img
 */
public class ControladorCliente extends Conexion implements ObservableCliente, InterfazParaControlarAlCliente {
    private boolean conexionFinalizada;
    private List<ObserverCliente> observadores;
    private List<String> cabecerasCodificadasParaEnviar;
    private List<String> cabecerasCodificadasRecibidas;
    private HebraParaControlarAlCliente hebra;
    private String mensajeCliente;
    private boolean nuncaEnviadoNada;
    private BufferedReader datosEntrantes;
    private DataOutputStream datosSalientes;
    


    public ControladorCliente() throws IOException {
        super("cliente");
        this.observadores = new ArrayList<ObserverCliente>();
        this.cabecerasCodificadasParaEnviar = new ArrayList<String>();
        this.cabecerasCodificadasRecibidas = new ArrayList<String>();
        this.conexionFinalizada = false;
        this.hebra = new HebraParaControlarAlCliente(this.cs,this);
        this.hebra.start();
        this.mensajeCliente ="";
        this.nuncaEnviadoNada = true;
        this.datosEntrantes = new BufferedReader(new InputStreamReader(cs.getInputStream()));
        this.datosSalientes = new DataOutputStream(cs.getOutputStream());
    }

    public boolean isConexionFinalizada() {
        return conexionFinalizada;
    }
    
    
   
    @Override
    public void addObserver(ObserverCliente observador) {
        this.observadores.add(observador);
        
    }

    @Override
    public void removeObserver(ObserverCliente observador) {
        this.observadores.remove(observador);
    }
    
    private void notifyObserversControladorCliente (GameEvent evento){
        for(ObserverCliente observer : observadores){ // para cada obervador en observadores
            observer.notifyEventVistasCliente(evento);
        }
        
    }

    
    //AQUI CODIFICO LA INFO 
    
    public void start(int idVentana,String colorSerpiente, int velocidadSerpiente, String nombreCliente) {
        this.cabecerasCodificadasParaEnviar.add("START;"+idVentana+";"+colorSerpiente+";"+velocidadSerpiente+";"+nombreCliente+"\n" );
        System.out.println("Llamado desde la vista el metodo START del controlador cliente");
        System.out.println("Codificando cabecera... y almacenando para enviar cuadno se pueda");
        System.out.println("");
        this.nuncaEnviadoNada = false;
    }
    
    public void pause() {
        this.cabecerasCodificadasParaEnviar.add("PAUSE;" + "\n");
        System.out.println("Llamado desde la vista el metodo PAUSE del controlador cliente");
        System.out.println("Codificando cabecera... y almacenando para enviar cuadno se pueda");
        System.out.println("");
    }
    
   public void reanudar() {
       this.cabecerasCodificadasParaEnviar.add("REANUDAR;" + "\n");
       System.out.println("Llamado desde la vista el metodo REANUDAR del controlador cliente");
        System.out.println("Codificando cabecera... y almacenando para enviar cuando se pueda");
        System.out.println("");
    }
   
    public void reiniciar() {
        this.cabecerasCodificadasParaEnviar.add("REINICIAR;" + "\n");
        System.out.println("Llamado desde la vista el metodo REINICIAR del controlador cliente");
        System.out.println("Codificando cabecera... y almacenando para enviar cuando se pueda");
        System.out.println("");
    }
    
    public void finalizarJuego() {
        this.cabecerasCodificadasParaEnviar.add("FINALIZAR_JUEGO;" + "\n");
        System.out.println("Llamado desde la vista el metodo FINALIZAR_JUEGO del controlador cliente");
        System.out.println("Codificando cabecera... y almacenando para enviar cuando se pueda");
        System.out.println("");
    }
    
    public void girarDerecha(int idVentana) {
        this.cabecerasCodificadasParaEnviar.add("GIRAR_DERECHA;"+idVentana + "\n");
        System.out.println("Llamado desde la vista el metodo GIRAR_DERECHA del controlador cliente");
        System.out.println("Codificando cabecera... y almacenando para enviar cuando se pueda");
        System.out.println("");
    }

    public void girarArriba(int idVentana) {
        this.cabecerasCodificadasParaEnviar.add("GIRAR_ARRIBA;"+idVentana + "\n");
        System.out.println("Llamado desde la vista el metodo GIRAR_ARRIBA del controlador cliente");
        System.out.println("Codificando cabecera... y almacenando para enviar cuando se pueda");
        System.out.println("");
    }

    public void girarAbajo(int idVentana) {
        this.cabecerasCodificadasParaEnviar.add("GIRAR_ABAJO;"+idVentana + "\n");
        System.out.println("Llamado desde la vista el metodo GIRAR_ABAJO del controlador cliente");
        System.out.println("Codificando cabecera... y almacenando para enviar cuando se pueda");
        System.out.println("");
    }

    public void girarIzquierda(int idVentana) {
        this.cabecerasCodificadasParaEnviar.add("GIRAR_IZQUIERDA;"+idVentana + "\n");
        System.out.println("Llamado desde la vista el metodo GIRAR_IZQUIERDA del controlador cliente");
        System.out.println("Codificando cabecera... y almacenando para enviar cuando se pueda");
    }
    
    public  void  clienteEnviaRecibe(){
                System.out.println("....");
               if(!nuncaEnviadoNada){
                if(cabecerasCodificadasParaEnviar.size() > 0){
                    //Flujo de datos hacia el servidor
                    System.out.println("Hay "+cabecerasCodificadasParaEnviar.size() +" datos listos para enviarse desde el controlador del CLIENTE al controlador del SERVIDOR");
                    System.out.println("");
                    salidaServidor = this.datosSalientes;
                    int i=1;
                    Iterator iterador = cabecerasCodificadasParaEnviar.iterator();
                    System.out.println("-----Enviando datos al Servidor...");
                    while(iterador.hasNext()){
                        try {
                            String cadenaCodificada = (String) iterador.next();
                            salidaServidor.writeUTF(cadenaCodificada);
                            System.out.println("----------"+i+" de "+cabecerasCodificadasParaEnviar.size()+"...Enviando dato "+cadenaCodificada);
                            i=i+1;
                        } catch (IOException ex) {
                            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.out.println("-----Todos los datos enviados al Servidor: ");
                    System.out.println("-----Borrando el buffer de salida del servidor... ");
                    System.out.println("");
                    
                    cabecerasCodificadasParaEnviar.clear();
                    System.out.println("-----Borrado completado");

                }
                
                
                
                //Flujo de datos desde el servidor
                
                
                //AQUI TENGO QUE RECIBIR LAS CADENAS CODIFICADES DEL SERVIDOR, DECODIFICARLAS Y GENERAR EL EVENTO ADECUADO EN FUNCIÓN DE LA CABECERA
                
                try {
                    System.out.println("Euchando al servidor desde el cliente...");
                    System.out.println("");
                    
                   
                    if((mensajeCliente = datosEntrantes.readLine())!=null){
                    System.out.println("-----Recibiendo datos en el cliente...");

                    System.out.println("----------El dato recibido en el cliente es: " + mensajeCliente);  
                    
                    cabecerasCodificadasRecibidas.add(mensajeCliente);
                    System.out.println("----------Dato almacenado en el cliente pendiente de ser procesado y enviado a las vistas");
                    //DataInputStream entrada1 = new DataInputStream(cs.getInputStream());
                    //mensajeCliente= entrada1.readUTF();
                    }
                    
                } catch (IOException ex) {
                    System.out.println("El servidor no ha enviado datos");
                    System.out.println("");
                    //Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                if(!cabecerasCodificadasRecibidas.isEmpty()){
                    System.out.println("Procesando datos recibidos en el Cliente...");
                    System.out.println("Hay "+ this.cabecerasCodificadasRecibidas.size() + " datos pendientes de procesar");
                    
                    for(int j=0;j<this.cabecerasCodificadasRecibidas.size();j++){
                        System.out.println("--"+j+"de"+cabecerasCodificadasRecibidas.size()+"...Decodificando dato "+this.cabecerasCodificadasRecibidas.get(j));

                        String[] mensajeRecibidoDecodificado = this.cabecerasCodificadasRecibidas.get(j).split(";");
                        String cabecera = mensajeRecibidoDecodificado[0];
                        try{
                        String cabeceraArregladaAux = cabecera.substring(2);
                        mensajeRecibidoDecodificado[0] = cabeceraArregladaAux;
                        }
                        catch(Exception e){
                            System.out.println("El mensaje recibido solo tiene un espacio: " + mensajeRecibidoDecodificado[0]);
                            mensajeRecibidoDecodificado[0] = "REANUDAR";
                        }
                    
                    switch (mensajeRecibidoDecodificado[0]){
                        
                        case "START":
                            Posicion posicionCabeza = new Posicion(Integer.parseInt(mensajeRecibidoDecodificado[1]),Integer.parseInt(mensajeRecibidoDecodificado[2]));
                            Posicion posicionCola = new Posicion(Integer.parseInt(mensajeRecibidoDecodificado[3]),Integer.parseInt(mensajeRecibidoDecodificado[4]));
                            String color = mensajeRecibidoDecodificado[5];
                            String nombreCliente = mensajeRecibidoDecodificado[6];
                            Posicion posicionFruta = new Posicion (Integer.parseInt(mensajeRecibidoDecodificado[7]),Integer.parseInt(mensajeRecibidoDecodificado[8]));
                            int idVentana = Integer.parseInt(mensajeRecibidoDecodificado[9]);
                            
                            System.out.println("-----Decodificando...");
                            System.out.println("----------Cabecera: " +mensajeRecibidoDecodificado[0]);
                            System.out.println("----------X Cabeza: " +mensajeRecibidoDecodificado[1]);
                            System.out.println("----------Y Cabeza: " +mensajeRecibidoDecodificado[2]);
                            System.out.println("----------X Cola: " +mensajeRecibidoDecodificado[3]);
                            System.out.println("----------Y Cola: " +mensajeRecibidoDecodificado[4]);
                            System.out.println("----------Color: " +mensajeRecibidoDecodificado[5]);
                            System.out.println("----------Nombre Cliente: " +mensajeRecibidoDecodificado[6]);
                            System.out.println("----------X fruta: " +mensajeRecibidoDecodificado[7]);
                            System.out.println("----------Y fruta: " +mensajeRecibidoDecodificado[8]);
                            System.out.println("----------Id Ventana: " +mensajeRecibidoDecodificado[9]);
                            
                            System.out.println("");
                            System.out.println("--Generando evento... para notificarselo a las vistas(ventanas)");
                            
                            notifyObserversControladorCliente(new GameEvent(GameEvent.EventType.START, posicionCabeza,posicionCola,color, nombreCliente, posicionFruta, idVentana));
                            
                            break;
                            
                        case "PAUSE":
                            System.out.println("-----Decodificando...");
                            System.out.println("----------Cabecera: " +mensajeRecibidoDecodificado[0]);
                            System.out.println("");
                            System.out.println("--Generando evento... para notificarselo a las vistas(ventanas)");
                            
                            notifyObserversControladorCliente(new GameEvent(GameEvent.EventType.PAUSE, null,null,null,null,null,null));
                            
                            break;
                            
                        case "REANUDAR":
                            System.out.println("-----Decodificando...");
                            System.out.println("----------Cabecera: " +mensajeRecibidoDecodificado[0]);
                            System.out.println("");
                            System.out.println("--Generando evento... para notificarselo a las vistas(ventanas)");
                            
                            notifyObserversControladorCliente(new GameEvent(GameEvent.EventType.REANUDAR, null,null,null,null,null,null));
                            
                            break;
                            
                        case "REINICIAR":
                            System.out.println("-----Decodificando...");
                            System.out.println("----------Cabecera: " +mensajeRecibidoDecodificado[0]);
                            System.out.println("");
                            System.out.println("--Generando evento... para notificarselo a las vistas(ventanas)");
                            
                            notifyObserversControladorCliente(new GameEvent(GameEvent.EventType.REINICIAR, null,null,null,null,null,null));
                                  
                            break;
                            
                        case "MOVER_SERPIENTE":
                            Posicion posicionCabeza1 = new Posicion(Integer.parseInt(mensajeRecibidoDecodificado[1]),Integer.parseInt(mensajeRecibidoDecodificado[2]));
                            Posicion posicionCola1 = new Posicion(Integer.parseInt(mensajeRecibidoDecodificado[3]),Integer.parseInt(mensajeRecibidoDecodificado[4]));
                            String color1 = mensajeRecibidoDecodificado[5];
                            int idVentana1 = Integer.parseInt(mensajeRecibidoDecodificado[6]);
                            
                            System.out.println("-----Decodificando...");
                            System.out.println("----------Cabecera: " +mensajeRecibidoDecodificado[0]);
                            System.out.println("----------X Cabeza: " +mensajeRecibidoDecodificado[1]);
                            System.out.println("----------Y Cabeza: " +mensajeRecibidoDecodificado[2]);
                            System.out.println("----------X Cola: " +mensajeRecibidoDecodificado[3]);
                            System.out.println("----------Y Cola: " +mensajeRecibidoDecodificado[4]);
                            
                            System.out.println("");
                            System.out.println("--Generando evento... para notificarselo a las vistas(ventanas)");
                          
                            
                            notifyObserversControladorCliente(new GameEvent(GameEvent.EventType.MOVER_SERPIENTE, posicionCabeza1, posicionCola1,color1,idVentana1,null,null));
                            
                            break;
                            
                        case "NUEVA_FRUTA":
                            
                            Posicion posicionFruta5 = new Posicion (Integer.parseInt(mensajeRecibidoDecodificado[1]),Integer.parseInt(mensajeRecibidoDecodificado[2]));
                            int idVentana4 = Integer.parseInt(mensajeRecibidoDecodificado[3]);
                            System.out.println("-----Decodificando...");
                            System.out.println("----------Cabecera: " +mensajeRecibidoDecodificado[0]);
                            System.out.println("----------X fruta: " +mensajeRecibidoDecodificado[1]);
                            System.out.println("----------Y fruta: " +mensajeRecibidoDecodificado[2]);
                            System.out.println("----------Id Ventana: " +mensajeRecibidoDecodificado[3]);
                            System.out.println("");
                            System.out.println("--Generando evento... para notificarselo a las vistas(ventanas)");
                            
                            notifyObserversControladorCliente(new GameEvent(GameEvent.EventType.NUEVA_FRUTA,posicionFruta5,idVentana4,null,null,null,null));
                            
                            break;
                            
                        case "FINALIZAR_JUEGO":
                            
                            System.out.println("-----Decodificando...");
                            System.out.println("----------Cabecera: " +mensajeRecibidoDecodificado[0]);
                            System.out.println("");
                            System.out.println("--Generando evento... para notificarselo a las vistas(ventanas)");
                           
                            notifyObserversControladorCliente(new GameEvent(GameEvent.EventType.FINALIZAR_JUEGO, null,null,null,null,null,null));
                            //conexionFinalizada = true;
                            break;
                            
                        case "NOMBRE_NO_VALIDO":
                            
                            
                            String nombreCliente2 = mensajeRecibidoDecodificado[1];
                            int idVentana2 = Integer.parseInt(mensajeRecibidoDecodificado[2]);
                            
                            System.out.println("-----Decodificando...");
                            System.out.println("----------Cabecera: " +mensajeRecibidoDecodificado[0]);
                            System.out.println("----------Nombre Cliente: " +mensajeRecibidoDecodificado[1]);                            
                            System.out.println("----------Id Ventana: " +mensajeRecibidoDecodificado[2]);
                            System.out.println("");
                            System.out.println("--Generando evento... para notificarselo a las vistas(ventanas)");
                            
                            
                            notifyObserversControladorCliente(new GameEvent(GameEvent.EventType.NOMBRE_NO_VALIDO,nombreCliente2,idVentana2,null,null,null,null));
                            
                            break;
                            
                        case "COLOR_NO_VALIDO":
                            
                           
                            String color3 = mensajeRecibidoDecodificado[1];
                            String nombreCliente3 = mensajeRecibidoDecodificado[2];
                            int idVentana3 = Integer.parseInt(mensajeRecibidoDecodificado[3]);
                            
                            System.out.println("-----Decodificando...");
                            System.out.println("----------Cabecera: " +mensajeRecibidoDecodificado[0]);
                            System.out.println("----------Color: " +mensajeRecibidoDecodificado[1]);                            
                            System.out.println("----------Nombre Cliente: " +mensajeRecibidoDecodificado[2]);                            
                            System.out.println("----------Id Ventana: " +mensajeRecibidoDecodificado[3]);
                            System.out.println("");
                            System.out.println("--Generando evento... para notificarselo a las vistas(ventanas)");
                            
                            notifyObserversControladorCliente(new GameEvent(GameEvent.EventType.COLOR_NO_VALIDO,color3,nombreCliente3,idVentana3,null,null,null));
                            
                            break;
                    }
                }
                    this.cabecerasCodificadasRecibidas.clear();
                }
               }
            
            
    }
}
            //this.cs.close();
        
       
    
    


