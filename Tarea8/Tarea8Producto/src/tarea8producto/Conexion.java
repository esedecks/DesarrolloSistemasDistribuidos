package tarea8producto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author esedecks
 */
public class Conexion {
    private static String url = "jdbc:mysql://localhost:3306/Tienda"; 
    private static String user = "root"; 
    private static String pwd = "1234";
    public static synchronized Connection getConnextion(){
        Connection cn = null; 
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            cn  = DriverManager.getConnection(url,user,pwd); 
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return cn ; 
        }
        
    }
    public static synchronized void cerrar(Connection cn ){
        try{
            cn.close(); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
     public static synchronized void cerrar(CallableStatement cs ){
        try{
            cs.close(); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //luiverar los demás recursos para deolverver posibles cambios hacer rolback
    public static synchronized void deshacerCambios(Connection cn ){
        try{
            cn.rollback();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
