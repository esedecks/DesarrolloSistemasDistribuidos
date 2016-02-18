
package ejercicio2cliente;

import java.io.*;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente  {
    public static void main(String[] args) throws Exception{
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
        }//fin while   
    }//fin main
}//fin clase
