/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoEnRed.Servidor;



 
public interface ObservableServidor {
    
    void addObserver(ObserverServidor observador);
    void removeObserver(ObserverServidor observador);
    
    
}
    

