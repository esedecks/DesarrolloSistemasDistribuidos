/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbox;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author esedecks
 */
public class ServerBox extends Thread{
    public static final int PUERTO = 6666; 
    ServerSocket socketServidor;
    
    public ServerBox(){
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
            }   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new ServerBox(); 
    }
}
