/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoEnRed.Servidor;


import JuegoEnRed.Conexion.GameEvent;


public interface ObserverServidor {
    
    void notifyEventControladorServidor(GameEvent evento);

    
}
    

