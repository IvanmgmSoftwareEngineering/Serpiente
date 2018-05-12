/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Cliente.Conexion.Conexion; 
import Cliente.Observable.Observer;
import java.io.BufferedReader;
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
public class ControladorServidor extends Conexion implements ObserverServidor, InterfazParaControlarAlServidor, InterfazParaEscucharNuevosClientes {
    
    private String cabeceraCodificada;
    private  boolean conexionFinalizada;
    private GameModel modelo;
    private int alturaTablero;
    private int ancho_tablero;
    private int contadorClientes;
    private List<String> cabecerasCodificadasParaEnviar;
    private List<String> cabecerasCodificadasRecibidas;
    private HebraParaControlarAlServidor hebra;
    private List<Cliente> clientes;
    private HebraParaEscucharNuevosClientes escuchaNuevosClientes;
    

    public ControladorServidor(GameModel modelo) throws IOException{ //Constructor
     
       super("servidor");
       this.conexionFinalizada = false;
       this.modelo = modelo;
       this.contadorClientes = 0;
       this.cabecerasCodificadasParaEnviar = new ArrayList<String>();
       this.cabecerasCodificadasRecibidas = new ArrayList<String>();
       this.hebra = new HebraParaControlarAlServidor(this,this.ss);
       hebra.start();
       this.clientes = new ArrayList<Cliente>();
       this.escuchaNuevosClientes = new HebraParaEscucharNuevosClientes(this,this.cs,this.ss);
       this.cabeceraCodificada = "";
       
        
    }
    
    public void iniciaServidor(){
        this.escuchaANuevosClientes();
        //this.escuchaNuevosClientes.start();
        
    }

    public synchronized boolean isConexionFinalizada() {
        return conexionFinalizada;
    }
    
    

    // RECIBE EVENTOS DESDE EL MODELO Y DEBE CODIFICARLOS Y ENVIARLOS A LOS CLIENTES
    @Override
    public void notifyEventControladorServidor(GameEvent evento) {
        SwingUtilities.invokeLater(new Runnable() {
            //SwingUtilities clase que contiene el metodod static 'invokeLater'
            //Runnable: interfaz que solo tiene el método run
            // el objetivo de lo anterior es crear una cola de eventos para ir almacenando los eventos
            @Override
            public void run() {
                manejarEvento(evento);
            } 
        });
    }
    
    // GENRERA LA CABECERA CODIFICADA QUE SE ENVIARA AL CONTROLADOR CLIENTE
    public void manejarEvento (GameEvent evento) {
        switch (evento.getEvento()){
                
            case START: 
                
                    System.out.println("El evento procedente del modelo se codifica en el controlador del servidor");

                    Posicion posicionCabeza = (Posicion)evento.getDatos1();
                    Posicion posicionCola = (Posicion)evento.getDatos2();
                    Posicion posicionFruta = (Posicion)evento.getDatos5();
                    int posicionCabezaX = posicionCabeza.getFila();
                    int posicionCabezaY = posicionCabeza.getColumna();
                    int posicionColaX = posicionCola.getFila();
                    int posicionColaY = posicionCola.getColumna();
                    String color = (String)evento.getDatos3();
                    String nombreCliente = (String) evento.getDatos4();
                    int posicionFrutaX = posicionFruta.getFila();
                    int posicionFrutaY = posicionFruta.getColumna();
                    int idVentana = (int)evento.getDatos6();            
                    
                    this.cabecerasCodificadasParaEnviar.add("START;"+posicionCabezaX+";"+posicionCabezaY+";"+posicionColaX+";"+posicionColaY+";"+color+";"+nombreCliente+";"+posicionFrutaX+";"+posicionFrutaY+";"+idVentana +"\n");
                
                    break; 
  
            case PAUSE:
                    this.cabecerasCodificadasParaEnviar.add("PAUSE;"+"\n");
                 
                    break;
                
            case REANUDAR:
                    this.cabecerasCodificadasParaEnviar.add("REANUDAR;"+"\n");
                
                    break;
                
            case REINICIAR:
                    this.cabecerasCodificadasParaEnviar.add("REINICIAR;"+"\n");
                
                    break;
                    
            case MOVER_SERPIENTE: 
                    Posicion posicionCabeza1 = (Posicion)evento.getDatos1();
                    Posicion posicionCola1 = (Posicion)evento.getDatos2();
                    int posicionCabezaX1 = posicionCabeza1.getFila();
                    int posicionCabezaY1 = posicionCabeza1.getColumna();
                    int posicionColaX1 = posicionCola1.getFila();
                    int posicionColaY1 = posicionCola1.getColumna();
                    String color1 = (String)evento.getDatos3();
                    int idVentana1 = (int) evento.getDatos4();
                    
                    this.cabecerasCodificadasParaEnviar.add("MOVER_SERPIENTE;"+posicionCabezaX1+";"+posicionCabezaY1+";"+posicionColaX1+";"+posicionColaY1+";"+color1+";"+idVentana1+"\n"); 
                
                    break; 
                    
            case NUEVA_FRUTA: 
                    Posicion posicionFruta2 = (Posicion)evento.getDatos1();
                    int posicionFrutaX2 = posicionFruta2.getFila();
                    int posicionFrutaY2 = posicionFruta2.getColumna();
                    int idVentana5 = (int)evento.getDatos2();
                    
                    this.cabecerasCodificadasParaEnviar.add("NUEVA_FRUTA;"+posicionFrutaX2+";"+posicionFrutaY2+";"+idVentana5+"\n");

                    
                    break;         
                   
            case FINALIZAR_JUEGO:
                    this.cabecerasCodificadasParaEnviar.add("FINALIZAR_JUEGO;"+"\n");
                    //this.conexionFinalizada = true;
                    
                    break;
             
            case NOMBRE_NO_VALIDO:
                    String nombreCliente3 = (String) evento.getDatos1();
                    String idVentana3 = (String) evento.getDatos2();
                    this.cabecerasCodificadasParaEnviar.add("NOMBRE_NO_VALIDO;"+nombreCliente3+";"+idVentana3+"\n");
                    
                    break; 
                    
            case COLOR_NO_VALIDO: 
                    String color4 = (String) evento.getDatos1();
                    String nombreCliente4 = (String) evento.getDatos2();
                    int idVentana4 = (int) evento.getDatos3();
                    
                    this.cabecerasCodificadasParaEnviar.add("COLOR_NO_VALIDO;"+color4+";"+nombreCliente4+";"+idVentana4+"\n");
                    
                    break;             
        } 
        }
    
    // RECIBE INFO CODIFICADA DESDE EL CONTROLADOR CLIENTE Y DEBE DECODIFICARLA Y LLAMAR AL METODO ADECUADO DEL MODELO
    
        // DECODIFICA LA INFO RECIBIDA DEL CONTROLADOR CLIENTE
    
        // CON LA INFO DECODIFICADA SELECCIONA ALGUN METODO DEL MODELO
    
      
    public void escuchaANuevosClientes(){
        try {
            System.out.println("Escuchando..."); //Esperando conexión
            //ss.setSoTimeout(1000);
            cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente
            cs.setSoTimeout(1000);
            this.contadorClientes = this.contadorClientes + 1;
            this.clientes.add(new Cliente(this.contadorClientes,cs));
            System.out.println("Nuevo Cliente conectado al servidor. Id Cliente: " + contadorClientes );
        } catch (IOException ex) {
            Logger.getLogger(ControladorServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    
    public synchronized void  servidorEnviaRecibe(){
        //System.out.println("He entrado en el metodo servidorEnviaRecibe del controlador del servidor");
        System.out.println("....");
             
        //ENVIAMOS TODOS LOS MENSAJES GENERADOS POR EL MODELO HACIA LOS CLIENTES QUE HEMOS IDO ALMACENANDO EN EL ARRAY cabecerasCodificadas
        if(this.contadorClientes>0){
        if(!cabecerasCodificadasParaEnviar.isEmpty()){
            System.out.println("Hay "+cabecerasCodificadasParaEnviar.size() +" datos listos para enviarse desde el controlador del servidor al controlador del cliente");

            try {
                
                //ENVIAMOS A CADA UNO DE LOS CLIENTES TODOS LOS DATOS PENDIENTES DE ENVIAR DESDE EL SERVIDOR
                synchronized(this.hebra){
                for(int h=0; h<this.clientes.size();h++){
                    salidaCliente = clientes.get(h).creaNuevoFlujoParaEnviarDatosDesdeElServidor();
                    System.out.println("-----Enviando datos al cliente: "+ clientes.get(h).getIdCliente());
                    //Se le envía un mensaje al cliente usando su flujo de salida
                    int i=1;
                    
                    for(int d=0; d<this.cabecerasCodificadasParaEnviar.size();d++){
                        try{ 
                            salidaCliente.writeUTF(cabecerasCodificadasParaEnviar.get(d));
                            System.out.println("----------"+i+" de "+cabecerasCodificadasParaEnviar.size()+"...Enviando dato "+cabecerasCodificadasParaEnviar.get(d));
                            i = i+1;
                            
                        }
                        catch (IOException ex) {
                            Logger.getLogger(ControladorServidor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.out.println("-----Todos los datos enviados al cliente: "+ clientes.get(h).getIdCliente());
                    System.out.println("");
                   
                }
                this.cabecerasCodificadasParaEnviar.clear();
                }

            }
            catch (Exception ex) {
                Logger.getLogger(ControladorServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Iterator iterador2 = this.clientes.iterator();
        while(iterador2.hasNext()){
            Cliente cliente = (Cliente) iterador2.next();
            BufferedReader entrada = cliente.getBufferParaRecibirDatosDelCliente();

            try {
                System.out.println("Escuchando al cliente desde el servidor...");
                
                if((mensajeServidor = entrada.readLine())!=null ){
                    //if(!(mensajeServidor.length()<=2)){
                        System.out.println("-----Recibiendo datos en el servidor...");

                        System.out.println("----------El dato recibido en el servidor es: " + mensajeServidor);                   
                        cabecerasCodificadasRecibidas.add(mensajeServidor);
                        System.out.println("----------Dato almacenado en el servidor y esta pendiente de ser procesado y enviado al modelo");
                        //mensajeServidor = null;
                }
            
                //else{
                    //System.out.println("-----Recibiendo datos en el servidor...");
                    //System.out.println("-----Deshechando dato por no ser un dato valido...");

            
                //}
            } catch (IOException ex) {
                System.out.println("-----El cliente no ha enviado ningun dato");
                //Logger.getLogger(ControladorServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Se almacena en  la lista de mensajes recibidos
        if(!cabecerasCodificadasRecibidas.isEmpty()){
            System.out.println("Procesando datos recibidos en el servidor...");
            System.out.println("Hay "+ this.cabecerasCodificadasRecibidas.size() + " datos pendientes de procesar");
            Iterator iterador4 = cabecerasCodificadasRecibidas.iterator();
            int k=1;
            while(iterador4.hasNext()){
                String mensajeRecibidoCodificado = (String) iterador4.next();
                System.out.println("--"+k+"de"+cabecerasCodificadasRecibidas.size()+"...Decodificando dato "+mensajeRecibidoCodificado);
                k=k+1;
                String[] mensajeRecibidoDecodificado = mensajeRecibidoCodificado.split(";");
                String cabecera = mensajeRecibidoDecodificado[0];
                String cabeceraArreglada ="";
                for(int j = 0; j<cabecera.length()-1;j++){
                    cabeceraArreglada +=cabecera.charAt(j+1);
                }
                try{
                String cabeceraArregladaAux = cabeceraArreglada.substring(1);
                mensajeRecibidoDecodificado[0] = cabeceraArregladaAux;
                }
                catch (Exception e){
                    System.out.println("El mensaje recibido solo tiene un espacio: " + mensajeRecibidoCodificado);
                    mensajeRecibidoDecodificado[0] = "REANUDAR";
                }
                
                System.out.println("----------La info decodificada es: ");
                for (int i =0; i < mensajeRecibidoDecodificado.length; i++){
                    System.out.println("--------------" + "Mostrando dato decodificado: " + mensajeRecibidoDecodificado[i]);
                }
                synchronized(this.hebra){
                if(mensajeRecibidoDecodificado[0].equals("GIRAR_DERECHA")  ||mensajeRecibidoDecodificado[0].equals("GIRAR_IZQUIERDA") ||mensajeRecibidoDecodificado[0].equals("GIRAR_ABAJO")||mensajeRecibidoDecodificado[0].equals("GIRAR_ARRIBA")  ){
                    System.out.println("Como el dato recibido es de GIRO_, entonces hay que hacer dos cosas:");
                    
                    System.out.println("-----1º Bloqueo la hebra de la serpiente del jugador que quiere girar para que deje de moverse hasta que se le notificca al modelo el cambio de direcion");
                    Iterator iterador5 = this.modelo.getJugadores().iterator();
                    boolean encontrado = false;
                    while(iterador5.hasNext()&&!encontrado){
                        Jugador jugador = (Jugador) iterador5.next();
                        if(jugador.getIdVentana()== Integer.parseInt(mensajeRecibidoDecodificado[1])){
                            encontrado = true;
                            this.modelo.pauseJugador(jugador.getIdVentana());
                            this.modelo.setPuedesEntrar(true);
                        }
                    }
                
                    System.out.println("-----2º Tenemos que borrar en la lista de cabecerasCodificadasParaEnviar todos los datos asociados a MOVER_SERPIENTE que estan listos para enviar a los clientes desde el servidor. Esto se debe a que estos movimientos no se corresponden con los deseod del cliente de cambiar de direccion, es decir, son datos desfasados en el tiempo");
                    ArrayList<String> lista_aux = new ArrayList<String>();
                    Iterator iterador6 = this.cabecerasCodificadasParaEnviar.iterator();
                        while(iterador6.hasNext()){
                            String dato = (String) iterador6.next();
                            String[] datos = dato.split(";");
                            if(datos[0]!= "MOVER_SERPIENTE"){
                                lista_aux.add(dato);
                            }
                            else{
                                if (datos[6]!=mensajeRecibidoDecodificado[1]){
                                    lista_aux.add(dato);
                                }
                            }
                        }
                        this.cabecerasCodificadasParaEnviar = lista_aux;
                }
                
                else if(mensajeRecibidoDecodificado[0].equals("PAUSE")){
                System.out.println("Como el dato recibido es de PAUSE, entonces hay que hacer dos cosas:");
                
                System.out.println("-----1º Bloquear todas las hebras de las serpientes de los jugadores");
                this.modelo.pause();
                System.out.println("-----2º Tenemos que borrar en la lista de cabecerasCodificadasParaEnviar todos los datos asociados a MOVER_SERPIENTE que estan listos para enviar a los clientes desde el servidor. Esto se debe a que estos movimientos no se corresponden con los deseod del cliente de cambiar de direccion, es decir, son datos desfasados en el tiempo");
                ArrayList<String> lista_aux = new ArrayList<String>();
                    Iterator iterador7 = this.cabecerasCodificadasParaEnviar.iterator();
                        while(iterador7.hasNext()){
                            String dato = (String) iterador7.next();
                            String[] datos = dato.split(";");
                            if(datos[0]!= "MOVER_SERPIENTE"){
                                lista_aux.add(dato);
                            }
                        }
                        this.cabecerasCodificadasParaEnviar = lista_aux;
                }

                    
                }
                    
                        
                
                
                switch (mensajeRecibidoDecodificado[0]){//AQUI DEBO PONER EL STRING CON LA CABECERA QUE HE RECIBIDO
                    case "START":
                        System.out.println("LLevando datos al modelo...");
                        modelo.start(Integer.parseInt(mensajeRecibidoDecodificado[1])/*Idventana*/, mensajeRecibidoDecodificado[2]/*Color serpiente*/,Integer.parseInt(mensajeRecibidoDecodificado[3]) /*Velocidad serpiente*/, mensajeRecibidoDecodificado[4   ]/*Nombre Cliente*/);
                        
                        
                        break;
                        
                    case "REANUDAR":
                        System.out.println("LLevando datos al modelo...");
                        
                        modelo.reanudar();
                        
                        break;
                        
                    case "REINICIAR":
                        System.out.println("LLevando datos al modelo...");
                       
                        modelo.reiniciar();
                        
                        break;
                        
                    case "GIRAR_DERECHA":
                        System.out.println("LLevando datos al modelo...");
                        
                        modelo.girarDerecha(Integer.parseInt(mensajeRecibidoDecodificado[1]));
                        
                        break;
                        
                    case "GIRAR_IZQUIERDA":
                        System.out.println("LLevando datos al modelo...");
                        
                        modelo.girarIzquierda(Integer.parseInt(mensajeRecibidoDecodificado[1]));
                        
                        break;
                        
                    case "GIRAR_ARRIBA":
                        System.out.println("LLevando datos al modelo...");
                        
                        modelo.girarArriba(Integer.parseInt(mensajeRecibidoDecodificado[1]));
                        
                        break;
                        
                    case "GIRAR_ABAJO":
                        System.out.println("LLevando datos al modelo...");
                        
                        modelo.girarAbajo(Integer.parseInt(mensajeRecibidoDecodificado[1]));
                        
                        break;
                        
                    case "FINALIZAR_JUEGO":
                        System.out.println("LLevando datos al modelo...");
                        
                        modelo.finalizarJuego();
                        
                        break;
                }
                
            }
            cabecerasCodificadasRecibidas.clear();
        } 
    }
    }

    
                
}

    

            
              
             

    

    
 

        
        
      

    
    

