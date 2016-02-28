/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea8producto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author esedecks
 */
public class Servidor {
    public static void main(String[] args){
        try{
            ServerSocket ss = new ServerSocket(5000); 
            System.out.println("Servidor OK"); 
            Socket s = ss.accept(); 
            BufferedReader entrada = new BufferedReader(new InputStreamReader(s.getInputStream())); 
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(s.getOutputStream())); 
            String cadena = entrada.readLine(); 
            String[] elementos = cadena.split(","); 
            Producto p = new Producto (Integer.parseInt(elementos[0]),elementos[1],Double.parseDouble(elementos[2])); 
            boolean respuesta = ProductoDAO.insertarProducto(p); 
            salida.println(respuesta);
            salida.flush();  
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
