/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;


import Conexion.GameEvent;


public interface ObserverCliente {
    
    void notifyEventVistasCliente(GameEvent evento);

    
}
    

