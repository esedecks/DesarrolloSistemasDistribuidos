
package ejercicio2cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente  {
    public static void main(String[] args) throws Exception{
        String host = "127.0.0.1"; 
        int PORT = 6666; 
        Socket s = new Socket(host, PORT); 
        System.err.println("Se conectó al servidor remoto"); 
        //Asociar flujos
        PrintWriter salida = new PrintWriter(new OutputStreamWriter(s.getOutputStream())); 
        BufferedReader entrada = new BufferedReader(new InputStreamReader(s.getInputStream())); 
     
        String [] prueba1 = {"select * from banco",
                            "insert into banco values(12, 'Mexico banco')", 
                            "create table alumno(nombre varchar(30) not null)", 
                            "dumb thing no sense"
                
                           }; 
        salida.println(prueba1[3]);
        salida.flush();
        String linea ; 
        while((linea = entrada.readLine())!=null){
            System.err.println(linea);
        }
    
    }
           
}
