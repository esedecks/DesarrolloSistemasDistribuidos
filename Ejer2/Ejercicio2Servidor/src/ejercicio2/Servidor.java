
package ejercicio2;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author esedecks
 */
public class Servidor extends Thread{
    public static final int PUERTO = 6666; 
    ServerSocket socketServidor;
    
    public Servidor(){
        try{
            socketServidor = new ServerSocket(PUERTO); 
            System.out.println("Server funcionando"); 
        }catch(Exception e){
            e.printStackTrace();
        }
        start();
    }
    public void run(){
        try{
            while(true){
                Socket s = socketServidor.accept(); 
                SQLConexion miConexion = new SQLConexion(s); 
                System.err.println("Atendiendo una nueva conexión"); 
            }   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new Servidor(); 
    }
}
