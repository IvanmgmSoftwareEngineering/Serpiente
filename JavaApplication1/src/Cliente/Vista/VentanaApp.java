package Cliente.Vista;

 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Cliente.Controlador.Controlador;
import Cliente.Modelo.GameEvent;
import Cliente.Modelo.GameModel;
import java.awt.Color;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import Cliente.Observable.Observer;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author i.martingo.2016
 */
public class VentanaApp  extends JFrame implements Observer, GUISerpiente {

    /**
     * Creates new form NewJFrame
     */
    
    private final int NUM_FILAS = 40; 
    private final int NUM_COLUMNAS = 50;
    private List<Serpiente> serpientes;
    private JPanel [][] matriz;
    private Controlador controlador;
    private boolean nombreIntroducido = false;
    private ArrayList<PosicionSerpiente> posicionesSerpiente;
    private Color color;
    private boolean dentroLimites = true;
    private boolean frutaComida = true;
    private int posicionFrutaFila;
    private int posicionFrutaColumna;
    
    
    
    public VentanaApp(Controlador controlador) {
        this.controlador = controlador;
        initComponents();
        serpientes = new ArrayList<Serpiente>();
        matriz = new JPanel [NUM_FILAS][NUM_COLUMNAS];
        this.jPanel2.setLayout(new GridLayout(NUM_FILAS,NUM_COLUMNAS));
        posicionesSerpiente = new ArrayList<PosicionSerpiente>();
        PosicionSerpiente posicionCabeza = new PosicionSerpiente(NUM_FILAS / 2,NUM_COLUMNAS / 2);
        posicionesSerpiente.add(posicionCabeza);
        desplegableColores.addItem("Green");
        desplegableColores.addItem("Red");
        desplegableColores.addItem("Black");
        desplegableColores.addItem("Blue");
        introducirNombre.setText("Introduzca nombre del jugador, el color y pulsar iniciar");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        botonIniciar = new javax.swing.JButton();
        introducirNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        muestraNombre = new javax.swing.JTextPane();
        muestraCoordenadaX = new javax.swing.JTextField();
        muestraCoordenadaY = new javax.swing.JTextField();
        botonGirarArriba = new javax.swing.JButton();
        botonGirarIzquierda = new javax.swing.JButton();
        botonGirarDerecha = new javax.swing.JButton();
        botonGirarAbajo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        botonPausa = new javax.swing.JButton();
        desplegableColores = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Nombre");

        jLabel2.setText("Eje Y");

        jLabel3.setText("Eje X");

        botonIniciar.setText("Iniciar");
        botonIniciar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIniciarActionPerformed(evt);
            }
        });

        introducirNombre.setText("Introduzca nombre del jugador y pulsar iniciar");
        introducirNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                introducirNombreActionPerformed(evt);
            }
        });

        muestraNombre.setEditable(false);
        jScrollPane1.setViewportView(muestraNombre);

        muestraCoordenadaX.setEditable(false);

        muestraCoordenadaY.setEditable(false);

        botonGirarArriba.setText("▲");
        botonGirarArriba.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonGirarArriba.setEnabled(false);
        botonGirarArriba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGirarArribaActionPerformed(evt);
            }
        });

        botonGirarIzquierda.setText("◀");
        botonGirarIzquierda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonGirarIzquierda.setEnabled(false);
        botonGirarIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGirarIzquierdaActionPerformed(evt);
            }
        });

        botonGirarDerecha.setText("►");
        botonGirarDerecha.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonGirarDerecha.setEnabled(false);
        botonGirarDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGirarDerechaActionPerformed(evt);
            }
        });

        botonGirarAbajo.setText("▼");
        botonGirarAbajo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonGirarAbajo.setEnabled(false);
        botonGirarAbajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGirarAbajoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 585, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );

        botonPausa.setText("Pause");
        botonPausa.setEnabled(false);
        botonPausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPausaActionPerformed(evt);
            }
        });

        desplegableColores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        desplegableColores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desplegableColoresActionPerformed(evt);
            }
        });

        jLabel4.setText("Color");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(muestraCoordenadaX, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addComponent(jLabel3)
                                        .addGap(19, 19, 19)
                                        .addComponent(muestraCoordenadaY, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonGirarIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonGirarAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonGirarDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(introducirNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonGirarArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(botonIniciar)
                                .addGap(34, 34, 34)
                                .addComponent(botonPausa))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(desplegableColores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(introducirNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(muestraCoordenadaX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(muestraCoordenadaY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(botonGirarArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonGirarIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonGirarDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonGirarAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(desplegableColores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonIniciar)
                    .addComponent(botonPausa))
                .addContainerGap(416, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIniciarActionPerformed
        if(botonIniciar.getText() == "Iniciar" && nombreIntroducido == true && desplegableColores.getSelectedItem()!= " " ) {
            apareceFruta();
            this.posicionesSerpiente.set(0, new PosicionSerpiente(NUM_FILAS / 2,NUM_COLUMNAS / 2));
            muestraCoordenadaX.setEnabled(true);
            muestraCoordenadaY.setEnabled(true);
            muestraCoordenadaX.setText(String.valueOf(posicionesSerpiente.get(0).posicionFila));
            muestraCoordenadaY.setText(String.valueOf(posicionesSerpiente.get(0).posicionColumna));
            botonIniciar.setEnabled(false);
            botonPausa.setEnabled(true);
            botonGirarDerecha.setEnabled(true);
            botonGirarIzquierda.setEnabled(true);
            botonGirarAbajo.setEnabled(false);
            botonGirarArriba.setEnabled(true);
            botonIniciar.setText("Reiniciar");
            desplegableColores.setEnabled(false);
            this.controlador.start(); 
        }
        else if (botonIniciar.getText() == "Reiniciar"){
            desplegableColores.setEnabled(true);
            desplegableColores.setSelectedItem(" ");
            botonIniciar.setText("Iniciar");
            botonGirarDerecha.setEnabled(false);
            botonGirarIzquierda.setEnabled(false);
            botonGirarAbajo.setEnabled(false);
            botonGirarArriba.setEnabled(false);
            botonPausa.setText("Pause");
            botonPausa.setEnabled(false);
            muestraNombre.setText("");
            introducirNombre.setText("Introduzca nombre del jugador y pulsar iniciar");
            muestraCoordenadaX.setText("-");
            muestraCoordenadaY.setText("-");
            nombreIntroducido = false;
            dentroLimites = true;
            this.controlador.reiniciar();
        }
    }//GEN-LAST:event_botonIniciarActionPerformed

    private void introducirNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_introducirNombreActionPerformed
        if(introducirNombre.getText().length()>0 && introducirNombre.getText().length()<10){
            muestraNombre.setText(introducirNombre.getText());
            introducirNombre.setText("");
            nombreIntroducido = true;
        }
        else{
            introducirNombre.setCaretColor(Color.red);
        }

    }//GEN-LAST:event_introducirNombreActionPerformed

    private void botonPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPausaActionPerformed
        if(botonPausa.getText() == "Pause") {
            botonIniciar.setEnabled(true);
            botonPausa.setText("Reanudar");
            botonGirarDerecha.setEnabled(false);
            botonGirarIzquierda.setEnabled(false);
            botonGirarAbajo.setEnabled(false);
            botonGirarArriba.setEnabled(false);
            this.controlador.pause();
         }
        else if (botonPausa.getText() == "Reanudar"){
            this.controlador.reanudar();
            botonPausa.setText("Pause");
            botonGirarDerecha.setEnabled(true);
            botonGirarIzquierda.setEnabled(true);
            botonGirarAbajo.setEnabled(true);
            botonGirarArriba.setEnabled(true);
        }
        
        
    }//GEN-LAST:event_botonPausaActionPerformed

    private void botonGirarDerechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGirarDerechaActionPerformed
        botonGirarIzquierda.setEnabled(false);
        botonGirarAbajo.setEnabled(true);
        botonGirarArriba.setEnabled(true);
        girarSerpiente(Direccion.DERECHA);
    }//GEN-LAST:event_botonGirarDerechaActionPerformed

    private void desplegableColoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desplegableColoresActionPerformed
        this.definirColorSerpiente((String)desplegableColores.getSelectedItem());
       
    }//GEN-LAST:event_desplegableColoresActionPerformed

    private void botonGirarIzquierdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGirarIzquierdaActionPerformed
        botonGirarDerecha.setEnabled(false);
        botonGirarAbajo.setEnabled(true);
        botonGirarArriba.setEnabled(true);
        girarSerpiente(Direccion.IZQUIERDA);
    }//GEN-LAST:event_botonGirarIzquierdaActionPerformed

    private void botonGirarArribaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGirarArribaActionPerformed
        botonGirarAbajo.setEnabled(false);
        botonGirarIzquierda.setEnabled(true);
        botonGirarDerecha.setEnabled(true);
        girarSerpiente(Direccion.ARRIBA);
    }//GEN-LAST:event_botonGirarArribaActionPerformed

    private void botonGirarAbajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGirarAbajoActionPerformed
        botonGirarArriba.setEnabled(false);
        botonGirarIzquierda.setEnabled(true);
        botonGirarDerecha.setEnabled(true);
        girarSerpiente(Direccion.ABAJO);
    }//GEN-LAST:event_botonGirarAbajoActionPerformed

    
    
    
    public int getNumFilas() {
        return NUM_FILAS;
    }

    public int getNumColumnas() {
        return NUM_COLUMNAS;
    }
    
    public JTextField getMuestraCoordenadaX() {
        return muestraCoordenadaX;
    }

    public JTextField getMuestraCoordenadaY() {
        return muestraCoordenadaY;
    }
    
    public  void rellenaPanel (){
                
                for(int i =0; i<NUM_FILAS ; i++){
                    for(int j =0; j<NUM_COLUMNAS ; j++){
                            JPanel panel = new JPanel();
                            matriz[i][j]= new JPanel();
                            matriz[i][j].setBackground(Color.white);
                            this.jPanel2.add(matriz[i][j]); 
                    }
                }
            }
     
    public void ponerBlanco () {
        for(int i =0; i<NUM_FILAS ; i++){
            for(int j =0; j<NUM_COLUMNAS ; j++){
                matriz[i][j].setBackground(Color.white); 
            }
        }
     }
    
    //public JPanel muestraPosicion (JPanel panelPrincipal){
        
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        SwingUtilities.invokeLater(new Runnable() { 
            //EDT (Event dispatch Thread: es la hebra de despacho de swin, es la hebra que se encarga de despachar la hebras
            //SwingUgilities clase que contiene el metodod static 'invokeLater'
            //Runnable: interfaz que solo tiene el método run.
            // el objetivo de lo anterior es crear una cola de eventos para ir almacenando los eventos
            public void run() {
                GameModel modelo = new GameModel();
                VentanaApp ventana = new VentanaApp(new Controlador(modelo));
                modelo.addObserver(ventana);
                ventana.setVisible(true);
                ventana.rellenaPanel();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGirarAbajo;
    private javax.swing.JButton botonGirarArriba;
    private javax.swing.JButton botonGirarDerecha;
    private javax.swing.JButton botonGirarIzquierda;
    private javax.swing.JButton botonIniciar;
    private javax.swing.JButton botonPausa;
    private javax.swing.JComboBox<String> desplegableColores;
    private javax.swing.JTextField introducirNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField muestraCoordenadaX;
    private javax.swing.JTextField muestraCoordenadaY;
    private javax.swing.JTextPane muestraNombre;
    // End of variables declaration//GEN-END:variables

    @Override
    public void notifyEvent(GameEvent evento) {
        
        SwingUtilities.invokeLater(new Runnable() {
            //SwingUgilities clase que contiene el metodod static 'invokeLater'
            //Runnable: interfaz que solo tiene el método run
            // el objetivo de lo anterior es crear una cola de eventos para ir almacenando los eventos
            @Override
            public void run() {
                manejarEvento(evento);
            } 
        });
    }
    
    public void manejarEvento (GameEvent evento) {
        switch (evento.getEvento()){
            
            case START: 
               Serpiente serpiente1 = new Serpiente("green", 10, this);
               serpientes.add(serpiente1);
               serpiente1.start();
                
               break; 
  
            case PAUSE:
                for (Serpiente serpiente : serpientes) {
                   serpiente.pausar();
                }
                
                break;
                
            case REANUDAR:
                
                for (Serpiente serpiente : serpientes) {
                    serpiente.reanudar();
                }
                
                break;
                
            case REINICIAR:
                for (Serpiente serpiente : serpientes){
                    serpiente.interrupt();
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ponerBlanco();  
                    }
                });
                                    
                 
                serpientes.clear();
              
                break;
                
            case ARRIBA:
                for (Serpiente serpiente : serpientes){
                    serpiente.girar(Direccion.ARRIBA);
                }
                break;
                
            case ABAJO:
                for (Serpiente serpiente : serpientes){
                    serpiente.girar(Direccion.ABAJO);
                }
                break;
                
            case IZQUIERDA:
                for (Serpiente serpiente : serpientes){
                    serpiente.girar(Direccion.IZQUIERDA);
                }
                break;
            
            case DERECHA:
                for (Serpiente serpiente : serpientes){
                    serpiente.girar(Direccion.DERECHA);
                }
                break;
                
             case APARECE_FRUTA:
                
                 
                break;
                
                
            
            
        }
        
    }
    
    @Override
    public boolean serpienteEstaDentroLimitesDelTablero(){
        return 
                (this.dentroLimites)
                && this.posicionesSerpiente.get(0).getPosicionFila() >= 0 
                && this.posicionesSerpiente.get(0).getPosicionColumna() >= 0 
                && this.posicionesSerpiente.get(0).getPosicionFila() <= this.NUM_FILAS-1 
                && this.posicionesSerpiente.get(0).getPosicionColumna() <= this.NUM_COLUMNAS-1;
    }
    @Override
    public void setSerpienteFueraDeLimites() {
        this.dentroLimites = false;
    }
    @Override
    public void finDelJuego() {
        this.mostrarGameOver();
        //Aquí tenemos que meter más cosas para parar la partida
    }
    
    public void mostrarGameOver (){
        
        //Muestra la G
        matriz[2][3].setBackground(Color.red);
        matriz[2][4].setBackground(Color.red);
        matriz[3][2].setBackground(Color.red);
        matriz[4][2].setBackground(Color.red);
        matriz[5][2].setBackground(Color.red);
        matriz[5][4].setBackground(Color.red);
        matriz[6][3].setBackground(Color.red);
        matriz[6][4].setBackground(Color.red);
               
        //Muestra la A
        matriz[2][7].setBackground(Color.red);
        matriz[3][6].setBackground(Color.red);
        matriz[3][8].setBackground(Color.red);
        matriz[4][6].setBackground(Color.red);
        matriz[4][7].setBackground(Color.red);
        matriz[4][8].setBackground(Color.red);
        matriz[5][6].setBackground(Color.red);
        matriz[5][8].setBackground(Color.red);
        matriz[6][6].setBackground(Color.red);
        matriz[6][8].setBackground(Color.red);
        
        //Muestra la M
        matriz[2][11].setBackground(Color.red);
        matriz[2][13].setBackground(Color.red);
        matriz[3][10].setBackground(Color.red);
        matriz[3][12].setBackground(Color.red);
        matriz[3][14].setBackground(Color.red);
        matriz[4][10].setBackground(Color.red);
        matriz[4][12].setBackground(Color.red);
        matriz[4][14].setBackground(Color.red);
        matriz[5][10].setBackground(Color.red);
        matriz[5][12].setBackground(Color.red);
        matriz[5][14].setBackground(Color.red);
        matriz[6][10].setBackground(Color.red);
        matriz[6][14].setBackground(Color.red);
        
        //Muestra la E
        matriz[2][16].setBackground(Color.red);
        matriz[2][17].setBackground(Color.red);
        matriz[2][18].setBackground(Color.red);
        matriz[3][16].setBackground(Color.red);
        matriz[4][16].setBackground(Color.red);
        matriz[4][17].setBackground(Color.red);
        matriz[5][16].setBackground(Color.red);
        matriz[6][16].setBackground(Color.red);
        matriz[6][17].setBackground(Color.red);
        matriz[6][18].setBackground(Color.red);
        
        //Muestra la O
        matriz[8][4].setBackground(Color.red);
        matriz[9][3].setBackground(Color.red);
        matriz[9][5].setBackground(Color.red);
        matriz[10][3].setBackground(Color.red);
        matriz[10][5].setBackground(Color.red);
        matriz[11][3].setBackground(Color.red);
        matriz[11][5].setBackground(Color.red);
        matriz[12][4].setBackground(Color.red);
        
        //Muestra la V
        matriz[8][7].setBackground(Color.red);
        matriz[8][9].setBackground(Color.red);
        matriz[9][7].setBackground(Color.red);
        matriz[9][9].setBackground(Color.red);
        matriz[10][7].setBackground(Color.red);
        matriz[10][9].setBackground(Color.red);
        matriz[11][7].setBackground(Color.red);
        matriz[11][9].setBackground(Color.red);
        matriz[12][8].setBackground(Color.red);
        
        //Muestra la E
        matriz[8][11].setBackground(Color.red);
        matriz[8][12].setBackground(Color.red);
        matriz[8][13].setBackground(Color.red);
        matriz[9][11].setBackground(Color.red);
        matriz[10][11].setBackground(Color.red);
        matriz[10][12].setBackground(Color.red);
        matriz[11][11].setBackground(Color.red);
        matriz[12][11].setBackground(Color.red);
        matriz[12][12].setBackground(Color.red);
        matriz[12][13].setBackground(Color.red);
        
        //Mustra la R
        matriz[8][15].setBackground(Color.red);
        matriz[8][16].setBackground(Color.red);
        matriz[9][15].setBackground(Color.red);
        matriz[9][17].setBackground(Color.red);
        matriz[10][15].setBackground(Color.red);
        matriz[10][16].setBackground(Color.red);
        matriz[11][15].setBackground(Color.red);
        matriz[11][17].setBackground(Color.red);
        matriz[12][15].setBackground(Color.red);
        matriz[12][17].setBackground(Color.red);
        
    }

    @Override
    public void moverSerpiente(Direccion direccion) {
        try {
         this.matriz[this.posicionesSerpiente.get(0).getPosicionFila()][this.posicionesSerpiente.get(0).getPosicionColumna()].setBackground(Color.white);
         this.matriz[this.posicionesSerpiente.get(0).getPosicionFila() + direccion.getVariacionFila()][this.posicionesSerpiente.get(0).getPosicionColumna() + direccion.getVariacionColumna()].setBackground(this.color);
         this.posicionesSerpiente.set(0, new PosicionSerpiente(this.posicionesSerpiente.get(0).getPosicionFila()+direccion.getVariacionFila(),this.posicionesSerpiente.get(0).getPosicionColumna()));
         this.posicionesSerpiente.set(0, new PosicionSerpiente(this.posicionesSerpiente.get(0).getPosicionFila(),this.posicionesSerpiente.get(0).getPosicionColumna()+ direccion.getVariacionColumna()));
         
         if (posicionesSerpiente.get(0).getPosicionFila() == posicionFrutaFila && posicionesSerpiente.get(0).getPosicionColumna() == posicionFrutaColumna){
             comeFruta();
         }
        }
        catch (ArrayIndexOutOfBoundsException fueraDelTablero) {
            setSerpienteFueraDeLimites();
        }
            
    }
    
    @Override
    public void girarSerpiente(Direccion direccion) {
        
        switch(direccion) {
            case ARRIBA:
                manejarEvento(new GameEvent(GameEvent.EventType.ARRIBA, null));
            break;
            case ABAJO:     
                manejarEvento(new GameEvent(GameEvent.EventType.ABAJO, null));
            break;
            case IZQUIERDA:
                manejarEvento(new GameEvent(GameEvent.EventType.IZQUIERDA, null));
            break;
            case DERECHA:
                manejarEvento(new GameEvent(GameEvent.EventType.DERECHA, null));
            break;
            
    }
        
        
    }

    @Override
    public void definirColorSerpiente(String color) {
        
        switch (color){
            
            case "Green":
                
                this.color = Color.green;
                break;
             
            case "Red":
                
                this.color = Color.red; 
                break;
                
            case "Black":
                
                this.color = Color.black; 
                break;   
                
             case "Blue":
                
                this.color = Color.blue; 
                break;  
        }
    }
    
    @Override
    public void mostrarCoordenadasCabeza(){
        muestraCoordenadaX.setText(String.valueOf(posicionesSerpiente.get(0).posicionFila));
        muestraCoordenadaY.setText(String.valueOf(posicionesSerpiente.get(0).posicionColumna));
    }
    
    @Override
    public void apareceFruta(){
        int posicionFrutaFila = (int) Math.floor(Math.random()*(NUM_FILAS));
        int posicionFrutaColumna = (int) Math.floor(Math.random()*(NUM_COLUMNAS));
        
        if(posicionesSerpiente.get(0).getPosicionFila() != posicionFrutaFila || posicionesSerpiente.get(0).getPosicionColumna() != posicionFrutaColumna){
            this.posicionFrutaFila = posicionFrutaFila;
            this.posicionFrutaColumna = posicionFrutaColumna;
            matriz[this.posicionFrutaFila][this.posicionFrutaColumna].setBackground(Color.PINK); 
            frutaComida = false;
        }
    }
    
    @Override
    public void comeFruta(){
        matriz[this.posicionFrutaFila][this.posicionFrutaColumna].setBackground(Color.white);
        apareceFruta();
    }
    
    
    
    
}
