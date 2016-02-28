/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea8producto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author esedecks
 */
public class Cliente {
    public static void main(String[] args)throws Exception{
        Socket s = new Socket("localhost", 5000); 
        BufferedReader entrada = new BufferedReader(new InputStreamReader(s.getInputStream())); 
        PrintWriter salida = new PrintWriter(new OutputStreamWriter(s.getOutputStream())); 
        salida.print("1,Axe desodorante,35.9");
        salida.flush();
    }
}
