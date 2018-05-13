/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoEnRed.Cliente;

import JuegoEnRed.Conexion.GameEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class VentanaPuntuacion extends JFrame implements ObserverCliente {
    
    private boolean juegoInicaido;
    private int idVentana;
    private int puntuacion;
    private String nombreCliente;
    private List<Cliente> clientes;

    public VentanaPuntuacion(int idVentana) {
           initComponents();
           this.juegoInicaido = false;
           this.clientes = new ArrayList<Cliente>();
           this.idVentana = idVentana;
           this.puntuacion = 1;
           this.nombreCliente = "";
           this.puntuacionTextField.setEnabled(true);
           this.puntuacionTextField.setEditable(true);
           this.puntuacionTextField.setText("");

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
        puntuacionTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        puntuacionTextField.setText("jTextField1");
        puntuacionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puntuacionTextFieldActionPerformed(evt);
            }
        });

        jLabel1.setText("Puntuación");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(puntuacionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(puntuacionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void puntuacionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puntuacionTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_puntuacionTextFieldActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField puntuacionTextField;
    // End of variables declaration//GEN-END:variables

    //@Override
    public void notifyEventVistasCliente(GameEvent evento) {
        
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
    
    
    
    public void manejarEvento (GameEvent evento) {
        switch (evento.getEvento()){
            case IDC:
                if(!this.juegoInicaido){
                    this.juegoInicaido = true;
                    this.idVentana = (int) evento.getDatos1();
                }

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
                    this.clientes.add(new Cliente((int) evento.getDatos6(),(String)evento.getDatos4(),(String) evento.getDatos3()));
                }
                
                this.pintaMarcador();
                     
            break; 
                
            case PTS:
                
                boolean encontrado1 = false;
                Iterator iterador1 = this.clientes.iterator();
                while(iterador1.hasNext()&& !encontrado1){
                    Cliente cliente = (Cliente) iterador1.next();
                    if(cliente.getIdCliente() == (int) evento.getDatos1()){
                         encontrado1 = true;
                         cliente.setPuntuacion((int)evento.getDatos2());
                    }
                }
                
                this.pintaMarcador();
               
                
              break;
            
            case REINICIAR: 
                    reiniciarMarcador();
                    break; 
        }
    }
    
    

    private void reiniciarMarcador() {
        this.jLabel1.setText("Puntuación");
        puntuacionTextField.setText("");
        this.clientes.clear();
    }

    private void pintaMarcador() {
        String puntuaciones = "";
        Iterator iterador2 = this.clientes.iterator();
        while(iterador2.hasNext()){
            Cliente cliente = (Cliente) iterador2.next();
            puntuaciones = "Jugador "+cliente.getIdCliente()+": "+"("+cliente.getNombre()+") "+ "tiene   "+ cliente.getPuntuacion()+" puntos."+"\n";
        }
        puntuacionTextField.setText(puntuaciones);
    }
 
}