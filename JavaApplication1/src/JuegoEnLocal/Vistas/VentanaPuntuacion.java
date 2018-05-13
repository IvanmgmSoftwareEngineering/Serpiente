/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoEnLocal.Vistas;
import JuegoEnLocal.Observer.Observer;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class VentanaPuntuacion extends JFrame implements Observer {
    
    private boolean juegoInicaido;
    private int idVentana;
    private int puntuacion;
    private String nombreCliente;
    private List<Cliente> clientes;
    private List<JTextField> marcadores;

    public VentanaPuntuacion(int idVentana) {
           initComponents();
           this.juegoInicaido = false;
           this.clientes = new ArrayList<Cliente>();
           this.marcadores = new ArrayList<>();
           this.marcadores.add(jTextField1);
           this.marcadores.add(jTextField2);
           this.idVentana = idVentana;
           this.puntuacion = 1;
           this.nombreCliente = "";
           this.jTextField1.setText("");
           this.jTextField2.setText("");

           
           //this.jTextField1.setEnabled(true);
           //this.jTextField1.setEditable(true);
           //this.jTextField1.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Puntuación");

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
//@Override
    public void notifyEvent(JuegoEnLocal.Modelo.GameEvent evento) {
        
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
    
    
    
    public void manejarEvento (JuegoEnLocal.Modelo.GameEvent evento) {
        switch (evento.getEvento()){
            
            case START:
                boolean encontrado = false;
                Iterator iterador = this.clientes.iterator();
                while(iterador.hasNext()&&!encontrado){
                    Cliente cliente = (Cliente) iterador.next();
                    if(cliente.getIdCliente() == (int) evento.getDatos6()){
                         encontrado = true;
                         
                    }
                }
                if(!encontrado){
                    int idClien = (int) evento.getDatos6();
                    this.clientes.add(new Cliente(idClien,(String)evento.getDatos4(),(String) evento.getDatos3(),this.marcadores.get(this.clientes.size())));

                    this.pintaMarcador(this.clientes.get(this.clientes.size()-1));

                    //this.clientes.get(this.clientes.size()).getTextArea()
                }
                
                     
            break; 
                
            case NUEVA_FRUTA:
                
                boolean encontrado1 = false;
                Iterator iterador1 = this.clientes.iterator();
                Cliente clienteAux = new Cliente(0,null,null,null);
                while(iterador1.hasNext()&& !encontrado1){
                    Cliente cliente = (Cliente) iterador1.next();
                    if(cliente.getIdCliente() == (int) evento.getDatos2()){
                         encontrado1 = true;
                         cliente.setPuntuacion((int)evento.getDatos2());
                         clienteAux = cliente;
                    }
                }
                if(encontrado1){
                
                this.pintaMarcador(clienteAux);
                }
               
                
              break;
            
            case REINICIAR: 
                    reiniciarMarcador();
                    break; 
        }
    }
    
    

    private void reiniciarMarcador() {
        this.jLabel1.setText("Puntuación");
        //jTextField1.setText("");
        this.clientes.clear();
    }

    private void pintaMarcador(Cliente cliente) {
        //String puntuaciones = "";
        //Iterator iterador2 = this.clientes.iterator();
        //while(iterador2.hasNext()){
            //Cliente cliente = (Cliente) iterador2.next();
            cliente.getTextArea().setText("Jugador "+cliente.getIdCliente()+": "+"("+cliente.getNombre()+") "+ "tiene   "+ cliente.getPuntuacion()+" puntos."+"\n"); 
        }
        //jTextField1.setText(puntuaciones);
    }


    

    
 
  

