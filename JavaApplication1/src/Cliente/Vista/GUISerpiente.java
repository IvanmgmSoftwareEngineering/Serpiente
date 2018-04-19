/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Vista;

import javax.swing.JPanel;

/**
 *
 * @author img
 */
public interface GUISerpiente {
    
    public void moverSerpiente(Direccion direccion);
    
    public void girarSerpiente(Direccion direccion);
    
    public void definirColorSerpiente(String color);
    
    public boolean serpienteEstaDentroLimitesDelTablero();
    
    public void setSerpienteFueraDeLimites();
    
    public void finDelJuego ();
    
    public void mostrarCoordenadasCabeza();
    
}
