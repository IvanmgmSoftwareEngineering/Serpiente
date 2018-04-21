/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candado;

/**
 *
 * @author img
 */
public class Candado {
    
    int x;
    int y;
    int z;
    int cociente;
    int resto;
    boolean noEncontrado;
    
    public Candado (){
        this.cociente = 29;
        this.resto = 4;
        this.noEncontrado = true;
    }
    
    public void calculaContraseña(){
            for (x=0;x<10;x++){
                for(y=0;y<10;y++){
                    for(z=0;z<10;z++){
                        if (100*x+10*y+z == (10*y+z)*cociente + resto){
                            System.out.println(100*x+10*y+z);
                        }
                    }
                }
            }
                
    }
            
        

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            new Candado().calculaContraseña();
      
        
    }
    
}
