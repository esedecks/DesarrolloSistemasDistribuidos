
package ejerciciobox;

import java.io.*;
import java.net.*;


public class Cliente  {
     
    String host  ="localhost"; 
    int PORT = 6666; 
    Socket s; 
    PrintWriter salida ; 
    BufferedReader entrada; 
    String manejador = ""; 
    String[] manejadores = {"mySQL","PostgreSQL"}; 
    public Cliente(){
        
    }
    public Cliente(String manejador) {
        this.manejador = manejador; 
    }
    public void sendMessage(String msj)throws UnknownHostException, IOException{
        if(this.manejador.equals(manejadores[0])){
            System.err.println("Enviando mensaje"); 
            s = new Socket(host, PORT); 
            salida = new PrintWriter(new OutputStreamWriter(s.getOutputStream())); 
            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            salida.println(msj);
            salida.flush();
        }
        
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
    