/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Cliente.Vista.Direccion;
import javax.swing.JPanel;

/**
 *
 * @author img
 */
public interface Serpiente {
    
    public void moverSerpiente(Direccion direccion, int idVentana);
            
    
    
    public void finalizarJuego ();    
    
}
