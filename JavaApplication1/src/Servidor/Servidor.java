/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author img
 */
public class Servidor {
    


/**
 * A TCP server that runs on port 9090.  When a client connects, it
 * sends the client the current date and time, then closes the
 * connection with that client.  Arguably just about the simplest
 * server you can write.
 */
    /**
     * Runs the server.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket listener1 = new ServerSocket(9190);
        ServerSocket listener2 = new ServerSocket(9290);
        ServerSocket listener3 = new ServerSocket(9390);
        ServerSocket listener4 = new ServerSocket(9490);
        ServerSocket listener5 = new ServerSocket(9590);

        try {
            while (true) {
                Socket socket1 = listener1.accept();
                Socket socket2 = listener2.accept();
                Socket socket3 = listener3.accept();
                Socket socket4 = listener4.accept();
                Socket socket5 = listener5.accept();

                try {
                    PrintWriter out1 =
                        new PrintWriter(socket1.getOutputStream(), true);
                    out1.println(new Date().toString());
                    
                    PrintWriter out2 =
                        new PrintWriter(socket1.getOutputStream(), true);
                    out2.println(new Date().toString());
                    
                    PrintWriter out3 =
                        new PrintWriter(socket1.getOutputStream(), true);
                    out3.println(new Date().toString());
                    
                    PrintWriter out4 =
                        new PrintWriter(socket1.getOutputStream(), true);
                    out4.println(new Date().toString());
                    
                    PrintWriter out5 =
                        new PrintWriter(socket1.getOutputStream(), true);
                    out5.println(new Date().toString());
                } finally {
                    socket1.close();
                    socket2.close();
                    socket3.close();
                    socket4.close();
                    socket5.close();
                }
            }
        }
        finally {
            listener1.close();
            listener2.close();
            listener3.close();
            listener4.close();
            listener5.close();
        }
    }
}
    

