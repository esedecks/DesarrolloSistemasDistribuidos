
package serverbox;
import DAOs.AlumnoDAO;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.List;
import serverbox.pojos.Alumno;

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
        try{; 
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
        try{
            //el cliente enviará la estructura como 
            //alumno, [agregar, elimanr, actualizar, consultarUno,consultarTodos],
            if(consulta.contains("alumno")){
                String elementos[] = consulta.split(",");
                AlumnoDAO alumnoDao = new AlumnoDAO();
                Alumno a ; 
                alumnoDao.setPw(salida);
                switch(elementos[1]){
                    case "agregar":
                        a = new Alumno( elementos[2], elementos[3], elementos[4], elementos[5]); 
                        alumnoDao.create(a);
                        salida.println(consulta +" \nSe insertó con éxito");
                        salida.flush();
                    break; 
                    case "eliminar": 
                        a = new Alumno(); 
                        a.setNoBoleta(elementos[2]);
                        alumnoDao.delete(a);
                    break; 
                    case "actualizar":
                        a = new Alumno( elementos[2], elementos[3], elementos[4], elementos[5]); 
                        alumnoDao.update(a);
                        salida.println(consulta +" \nSe Actualizo con éxito");
                        salida.flush();
                    break; 
                    case "consultarUno":
                        a = new Alumno();
                        a.setNoBoleta(elementos[2]);
                        a = alumnoDao.read(a);
                        salida.println(a.toString()); 
                        salida.flush();
                    break; 
                    case "consultarTodo":
                        List lista = alumnoDao.readAll(); 
                        StringBuilder sb = new StringBuilder(); 
                        for(int i = 0; i<lista.size(); i++){
                            sb.append(lista.get(i).toString()); 
                            sb.append("\n_______________________\n"); 
                        }
                        salida.print(sb.toString());
                        salida.flush();
                    break; 
                    default:
                        System.err.println("No fue posible ejecutar el query"); 
                    break; 
                }
            }
            
            
      
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
    public void ejecutarEscritura(Statement st)throws SQLException{
        int i ; 
        int valor = st.executeUpdate(consulta); 
        salida.print(consulta + "\nCódigo respuesta :"+valor);
        salida.flush();
    }
}
