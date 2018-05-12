/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;


import Conexion.GameEvent;


public interface ObserverServidor {
    
    void notifyEventControladorServidor(GameEvent evento);

    
}
    

