/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.net.Socket;

/**
 *
 * @author img
 */
public interface InterfazParaControlarAlCliente {
    
    boolean isConexionFinalizada();
    
    void  clienteEnviaRecibe();
    
    
    
}
