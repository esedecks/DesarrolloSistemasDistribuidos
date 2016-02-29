
package ejercicio2;
import java.io.*;
import java.net.Socket;
import java.sql.*;

/**
 *
 * @author esedecks
 */
public class SQLConexion extends Thread {
    protected Socket socketCliente; 
    protected BufferedReader  entrada ; 
    protected PrintStream salida ; 
    protected String consulta; 
    java.sql.Connection con; 
    Statement st ; 
    String driver = "com.mysql.jdbc.Driver"; 
    String usuario = "root"; 
    String clave ="1234";
    String URLBD = "jdbc:mysql://localhost:3306/";
    
    
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
        //la consulta vendrá como manejador,consulta,bd
        String[] elementos = consulta.split(","); 
        try{
            switch(elementos[0]){
                case "postgreSQL":
                    System.err.println("1");
                    atiendePostgress(elementos[1],elementos[2]); 
                break; 
                case "mySQL":
                    atiendeSQL(elementos[1],elementos[2]);
                break; 
                default: 
                    salida.print("No fue posible ejecutar la sentencia: '"+consulta+"'"); 
                    salida.flush();
                break; 
            }
            //se checa si va para postgreSQL o para mysql
//            if(consulta.contains("PostgreSQL")){
//                atiendePostgress();    
//            } 
//            else
//                atiendeSQL();  
        }catch(Exception e){
            e.printStackTrace();
            salida.print("No fue posible ejecutar la sentencia: '"+consulta+"'"); 
            salida.flush();
        } 
    }
    private void atiendePostgress(String query,String bdName)throws SQLException{
        obtenerConexionPostgress(bdName); 
        System.err.println(".......---Entro en posgressSQL :"+query); 
        if(isLectura(query)){
            ejecutarLectura(st,query);
        }else {
            ejecutarEscritura(st,query);
        }
        //obtenerConexionPostgress("postgres");
        //ejecutarLectura(st);
    }
    private void atiendeSQL(String query , String bd)throws SQLException, ClassNotFoundException{
        obtenerConexion(bd);
        if(isLectura(query)){
            ejecutarLectura(st,query);
        }else {
            ejecutarEscritura(st,query);
        }

        con.close(); 
    
    }
     private void obtenerConexion(String dbName){
        try{
            Class.forName(driver); 
            con = DriverManager.getConnection(URLBD.concat(dbName), usuario, clave); 
            st = con.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void obtenerConexionPostgress(String dbName){
  
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbName,"postgres", "1234");
            st = con.createStatement(); 
        }catch(Exception e){
            e.printStackTrace();
        }
         
    
    }
    private boolean isLectura(String query){
        boolean bandera = true;
        //SOLAMENTE SE ASUME QUE ES UN SELECT PERO NO DEPENDE 
        //PUES EN OTRO CASO EL MÉTODO EXEQUTEQUERY LO TENDRÍA QUE RECHAZAR.
        String querymin = query.toLowerCase(); 
        if(querymin.startsWith("select") || querymin.startsWith("show")|| querymin.startsWith("describe")){
            bandera = true; 
        }else 
            bandera = false; 
        return bandera ; 
    
    }
    private void ejecutarLectura(Statement st,String query) throws SQLException{
        boolean existenMasFilas; 
        int i ; 
        System.err.println("La consulta es: "+query); 
        ResultSet rs = st.executeQuery(query); 
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
             sb.append("|"); 
         
        }
        sb.append("\r\n"); 
         
        salida.print(sb.toString()); 
        String registro = ""; 
        while(existenMasFilas){
                for(i =0; i<numeroColumnas; i++){
                    registro = registro.concat(rs.getString(i+1) + "|"); 
                }
                registro = registro.concat("\r\n"); 
                existenMasFilas = rs.next(); 
        }
        salida.print(registro);
        salida.flush();
            
    }
    public void ejecutarEscritura(Statement st,String query)throws SQLException{
        int i ; 
        int valor = st.executeUpdate(query); 
        salida.print(consulta + "\nCódigo respuesta :"+valor);
        salida.flush();
    }
}
