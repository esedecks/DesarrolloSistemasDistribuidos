
package modelo;
import java.sql.*;

/**
 *
 * @author esedecks
 */
public class SQLConexion {
    java.sql.Connection con; 
    Statement st ; 
    String driver = "com.mysql.jdbc.Driver"; 
    String usuario = "root"; 
    String clave ="1234";
    String URLBD = "jdbc:mysql://localhost:3306/Ejercicio8"; 
    StringBuilder resultado; 
    StringBuilder metadatos; 
    protected String consulta; 
    
    
    public SQLConexion(){
     
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }
    public void ejecutarSQL() {
        try{
            Class.forName(driver); 
            con = DriverManager.getConnection(URLBD, usuario, clave); 
            st = con.createStatement(); 
            //verificar la consulta 
            if(isLectura()){
                    ejecutarLectura(st);
            }else
                ejecutarEscritura(st);
            con.close(); 
        }catch(Exception e){
            resultado = new StringBuilder(); 
            resultado.append("0"); 
            e.printStackTrace();
       
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
        resultado = new StringBuilder(); 
        metadatos = new StringBuilder(); 
        boolean existenMasFilas; 
        int i ; 
        ResultSet rs = st.executeQuery(consulta); 
        existenMasFilas = rs.next(); 
        if(!existenMasFilas){
            resultado.append("noDatos"); 
            return ;  
        }
        ResultSetMetaData resultadoMetadata = rs.getMetaData(); 
        int numeroColumnas = resultadoMetadata.getColumnCount(); 
        for(int j = 0; j<numeroColumnas; j++){
             String columna = resultadoMetadata.getColumnName(j+1); 
             metadatos.append(columna);
             metadatos.append("|"); 
        }
        metadatos.append("\r\n"); 
        String registro = ""; 
        while(existenMasFilas){
                for(i =0; i<numeroColumnas; i++){
                    registro = registro.concat(rs.getString(i+1) + "|"); 
                }
                registro = registro.concat("\r\n"); 
                existenMasFilas = rs.next(); 
        }
        resultado.append(registro);
    
    }
    public void ejecutarEscritura(Statement st)throws SQLException{
        int i ; 
        int valor = st.executeUpdate(consulta); 
        resultado = new StringBuilder(); 
        resultado.append(valor); 
    }

    public String getResultado() {
        return resultado.toString();
    }

    public String getMetadatos() {
        return metadatos.toString();
    }
    
    
    
}
