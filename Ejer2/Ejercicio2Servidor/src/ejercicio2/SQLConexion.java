
package ejercicio2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
            //verificar la consulta 
            if(isLectura()){
                //si es lectura es con select
                ejecutarLectura(st);
            }else{
                // por tanto es una operación de escritura alter, create, insert
                ejecutarEscritura(st);
            }
            con.close(); 
        }catch(Exception e){
            e.printStackTrace();
            salida.print("No fue posible ejecutar la sentencia: '"+consulta+"'"); 
            salida.flush();
        } 
    }
    private boolean isLectura(){
        boolean bandera = true;
        //SOLAMENTE SE ASUME QUE ES UN SELECT PERO NO DEPENDE 
        //PUES EN OTRO CASO EL MÉTODO EXEQUTEQUERY LO TENDRÍA QUE RECHAZAR.
        if(consulta.startsWith("s") || consulta.startsWith("S")){
            bandera = true; 
        }else 
            bandera = false; 
        return bandera ; 
    
    }
    private void ejecutarLectura(Statement st) throws SQLException{
        boolean existenMasFilas; 
        int i ; 
        ResultSet rs = st.executeQuery(consulta); 
        
        existenMasFilas = rs.next(); 
        if(!existenMasFilas){
            salida.println("No hay más filas");
            return;  
        }
        ResultSetMetaData resultadoMetadata = rs.getMetaData(); 
        
        int numeroColumnas = resultadoMetadata.getColumnCount(); 
        StringBuilder sb = new StringBuilder(); 
        for(int j = 0; j<numeroColumnas; j++){
             String columna = resultadoMetadata.getColumnName(j+1); 
             sb.append(columna);
             sb.append("\t"); 
         
        }
        sb.append('\n'); 
         
        salida.print(sb.toString()); 
        String registro = ""; 
        while(existenMasFilas){
                for(i =0; i<numeroColumnas; i++){
                    registro = registro.concat(rs.getString(i+1) + "\t"); 
                }
                registro = registro.concat("\n"); 
                existenMasFilas = rs.next(); 
            }
            salida.print(registro);
            salida.flush();
    
    }
    public void ejecutarEscritura(Statement st)throws SQLException{
        int i ; 
        int valor = st.executeUpdate(consulta); 
        salida.print(consulta + "\nCódigo respuesta :"+valor);
        salida.flush();
    }
   
           
    
}
