/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Observable;

import Cliente.Modelo.GameEvent;

/**
 *
 * @author img
 */
public interface Observer {
    
    void notifyEvent(GameEvent evento);
    
}
