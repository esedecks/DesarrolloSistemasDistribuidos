
package ejercicio2cliente;

import java.io.*;
import java.net.*;


public class Cliente  {
     
    String host  ="localhost"; 
    int PORT = 6666; 
    Socket s; 
    PrintWriter salida ; 
    BufferedReader entrada; 
    
    public Cliente() throws UnknownHostException,IOException{
       //s = new Socket(host, PORT); 
       //salida = new PrintWriter(new OutputStreamWriter(s.getOutputStream())); 
       //entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }
    public void sendMessage(String msj)throws UnknownHostException, IOException{
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
            sb.append('\n'); 
        }
             
        return sb.toString();
    }
}
    /*public static void main(String[] args) throws Exception{
        String host = "127.0.0.1"; 
        int PORT = 6666; 
        Socket s; 
        while(true){     
            s = new Socket(host, PORT); 
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(s.getOutputStream())); 
            BufferedReader entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));         
            String consulta = JOptionPane.showInputDialog(null, "Ingrese consulta o 'exit'");
            if(consulta.equals("exit"))
                System.exit(1);
            salida.println(consulta);
            salida.flush();
            String linea ; 
            while((linea = entrada.readLine())!=null)
                System.out.println(linea);
            System.err.println("\n");
        }//fin while  */ 
//    }//fin main
//}//fin clase
