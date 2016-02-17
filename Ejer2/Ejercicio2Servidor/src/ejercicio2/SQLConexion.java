
package ejercicio2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 *
 * @author esedecks
 */
public class SQLConexion extends Thread {
    protected Socket socketCliente; 
    protected BufferedReader  entrada ; 
    protected PrintStream salida ; 
    protected String consulta; 
    
    public SQLConexion(Socket socketCliente){
       this.socketCliente = socketCliente; 
       try{
           entrada  = new BufferedReader (new InputStreamReader(this.socketCliente.getInputStream()));
           salida = new PrintStream(this.socketCliente.getOutputStream()); 
       }catch(Exception e){
           e.printStackTrace();
       }
       this.start();
    }

    public void run(){
        try{
            System.err.println("Esperando por consulta desde el hilo"); 
            consulta = entrada.readLine(); 
            System.out.println("Consulta a ejecutar "+consulta+" ;");
            ejecutarSQL(); 
        }catch(Exception ex){
            ex.printStackTrace(); 
            
        }finally{
            try{
                socketCliente.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    private void ejecutarSQL() {
        java.sql.Connection con; 
        Statement st ; 
        ResultSet rs; 
        ResultSetMetaData resultadoMetadata; 
        boolean existenMasFilas; 
        String driver = "com.mysql.jdbc.Driver"; 
        String usuario = "root"; 
        String clave ="1234";
        String registro; 
        String URLBD = "jdbc:mysql://localhost:3306/banco"; 
        int numeroColumnas, i; 
        try{
            Class.forName(driver); 
            con = DriverManager.getConnection(URLBD, usuario, clave); 
            st = con.createStatement(); 
            rs = st.executeQuery(consulta); 
            existenMasFilas = rs.next(); 
            if(!existenMasFilas){
                salida.println("No hay más filas");
                return;  
            }
            resultadoMetadata = rs.getMetaData(); 
            numeroColumnas = resultadoMetadata.getColumnCount(); 
            System.out.println("Existe "+numeroColumnas);
            registro = ""; 
            while(existenMasFilas){
                for(i =0; i<numeroColumnas; i++){
                    registro = registro.concat(rs.getString(i+1) + " "); 
                }
                registro = registro.concat("\n"); 
                existenMasFilas = rs.next(); 
            }
            salida.println(registro);
            salida.flush();
            con.close(); 
        }catch(Exception e){
            e.printStackTrace();
        }
            
    }
    
}
