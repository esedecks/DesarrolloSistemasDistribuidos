
package serverbox;
import DAOs.AlumnoDAO;
import DAOs.CategoriaDAO;
import DAOs.CompetidorDAO;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.List;
import serverbox.pojos.Alumno;
import serverbox.pojos.Categoria;
import serverbox.pojos.Competidor;

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
    String URLBD = "jdbc:mysql://localhost:3306/escuelaBox";
    
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
            if(isLectura()){
                ejecutarLectura();
            }else if(consulta.contains("alumno")){
                atienteAlumno();
            }else if(consulta.contains("categoria")){
                atienteCategoria();
            }else if(consulta.contains("competidor")){
                atienteCompetidor(); 
            }
        }catch(Exception e){
            e.printStackTrace();
            salida.print("No fue posible ejecutar la sentencia: '"+consulta+"'"); 
            salida.flush();
        } 
    }
    
    private void atienteAlumno() throws Exception{
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
     private void atienteCategoria() throws Exception{
        String elementos[] = consulta.split(",");
        CategoriaDAO categoriaDao = new CategoriaDAO();
        Categoria c ; 
        categoriaDao.setPw(salida);
        switch(elementos[1]){
            case "agregar":
                //no es necesario el idCategoria porque es autoincrement
                c = new Categoria( elementos[2],elementos[3]); 
                categoriaDao.create(c);
                salida.println(consulta +" \nSe insertó con éxito");
                salida.flush();
            break; 
            case "eliminar": 
                c = new Categoria(); 
                c.setCategoria(Integer.parseInt(elementos[2]));
                categoriaDao.delete(c);
            break; 
            case "actualizar":
                c = new Categoria( Integer.parseInt(elementos[2]), elementos[3],elementos[4]);
                categoriaDao.update(c);
                salida.println(consulta +" \nSe Actualizo con éxito");
                salida.flush();
            break; 
            case "consultarUno":
                c = new Categoria();
                c.setCategoria(Integer.parseInt(elementos[2]));
                c = categoriaDao.read(c);
                salida.println(c.toString()); 
                salida.flush();
            break; 
            case "consultarTodo":
                List lista = categoriaDao.readAll(); 
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
     private void atienteCompetidor() throws Exception{
        String elementos[] = consulta.split(",");
        CompetidorDAO competidorDAO = new CompetidorDAO();
        Competidor c ; 
        competidorDAO.setPw(salida);
        switch(elementos[1]){
            case "agregar":
                c = new Competidor( elementos[2], elementos[3], elementos[4],Integer.parseInt(elementos[5])); 
                competidorDAO.create(c);
                salida.println(consulta +" \nSe insertó con éxito");
                salida.flush();
            break; 
            case "eliminar": 
                c = new Competidor(); 
                c.setIdCompetidor(Integer.parseInt(elementos[2]));
                competidorDAO.delete(c);
            break; 
            case "actualizar":
                c = new Competidor( Integer.parseInt(elementos[2]), elementos[3], elementos[4], elementos[5],Integer.parseInt(elementos[6])); 
                competidorDAO.update(c);
                salida.println(consulta +" \nSe Actualizo con éxito");
                salida.flush();
            break; 
            case "consultarUno":
                c = new Competidor();
                c.setIdCompetidor(Integer.parseInt(elementos[2]));
                c = competidorDAO.read(c);
                salida.println(c.toString()); 
                salida.flush();
            break; 
            case "consultarTodo":
                List lista = competidorDAO.readAll(); 
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
   
    public void ejecutarEscritura(Statement st)throws SQLException{
        int i ; 
        int valor = st.executeUpdate(consulta); 
        salida.print(consulta + "\nCódigo respuesta :"+valor);
        salida.flush();
    }
     private void obtenerConexion(){
        try{
            Class.forName(driver); 
            con = DriverManager.getConnection(URLBD, usuario, clave); 
            st = con.createStatement(); 
        }catch(Exception e){
            e.printStackTrace();  
        }
    }
    private boolean isLectura(){
        boolean bandera = true;
        //SOLAMENTE SE ASUME QUE ES UN SELECT PERO NO DEPENDE 
        //PUES EN OTRO CASO EL MÉTODO EXEQUTEQUERY LO TENDRÍA QUE RECHAZAR.
        if(consulta.startsWith("select") || consulta.startsWith("SELECT")){
            bandera = true; 
        }else 
            bandera = false; 
        return bandera ; 
    
    }
    private void ejecutarLectura() throws SQLException{
        obtenerConexion();
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
}
