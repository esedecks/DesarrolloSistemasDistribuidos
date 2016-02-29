
package ejercicio2cliente;

import java.io.*;
import java.net.*;


public class Cliente  {
     
    String host  ="localhost"; 
    int PORT = 6666; 
    Socket s; 
    PrintWriter salida ; 
    BufferedReader entrada; 
    
    public Cliente(){
        
    }
    public void sendMessage(String msj)throws UnknownHostException, IOException{
        System.err.println("Enviando mensaje"); 
        s = new Socket(host, PORT); 
        salida = new PrintWriter(new OutputStreamWriter(s.getOutputStream())); 
        entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
        salida.println(msj);
        salida.flush();
    }
    public String receiveMessage() throws IOException{
        String linea ; 
        StringBuilder sb = new StringBuilder(); 
        while((linea = entrada.readLine())!=null){
            sb.append(linea);
            sb.append("\r\n"); 
        }
             
        return sb.toString();
    }
}

