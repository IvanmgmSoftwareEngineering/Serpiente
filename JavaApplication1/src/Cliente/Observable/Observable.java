/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Observable;

/**
 *
 * @author img
 */
public interface Observable {
    
    void addObserver(Observer observador);
    void removeObserver(Observer observador);
    
    
}
