/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

/**
 *
 * @author img
 */
/**
 * Clase que representa a un hilo
 * @author Santiago Faci
 * @version curso 2014-2015
 *
 */
public class Hilo2 extends Thread {

	private final static int MAXIMO = 10;
	
	/*
	 * Tarea que desempeña el hilo. En este caso de forma simbólica 
	 * pinta una serie de líneas por pantalla
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		
		try {
			for (int i = 0; i < MAXIMO; i++) {
				System.out.println("Hola, soy el hilo 2");
				Thread.sleep(500);
			}
		} catch (InterruptedException ie) {}
	}
}
