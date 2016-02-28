/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import serverbox.pojos.Alumno;


/**
 *
 * @author esedecks
 */
public class AlumnoDAO {
    private static final String SQL_INSERT = "insert into alumno(noBoleta,nombre,ap, am) values(?,?,?,?)"; 
    private static final String SQL_SELECT = "select * from alumno where noBoleta=?"; 
    private static final String SQL_UPDATE = "update alumno set nombre=?,ap=?,am=? where noBoleta=?"; 
    private static final String SQL_SELECT_ALL = "select * from alumno"; 
    private static final String SQL_DELETE = "delete from alumno where noBoleta=?"; 
    
    java.sql.Connection con; 
    Statement st ; 
    String driver = "com.mysql.jdbc.Driver"; 
    String usuario = "root"; 
    String clave ="1234";
    String URLBD = "jdbc:mysql://localhost:3306/escuelaBox"; 
    PrintStream salida = null; 

    public void setPw(PrintStream ps) {
        this.salida = ps;
    }
    public void create(Alumno a )throws Exception{
        PreparedStatement ps = null; 
        obtenerConexion(); 
            ps  = con.prepareStatement(SQL_INSERT); 
            ps.setString(1, a.getNoBoleta());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getAp());
            ps.setString(4, a.getAm());
            ps.executeUpdate(); 
    }
    
    public void update(Alumno a){
        PreparedStatement ps = null; 
        obtenerConexion(); 
        try{
            ps  = con.prepareStatement(SQL_UPDATE); 
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getAp());
            ps.setString(3, a.getAm());
            ps.setString(4, a.getNoBoleta());
            ps.executeUpdate(); 
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
     public void delete(Alumno a) throws Exception{
        PreparedStatement ps = null; 
        obtenerConexion(); 
        
        ps  = con.prepareStatement(SQL_DELETE); 
        ps.setString(1, a.getNoBoleta()); 
        int i =ps.executeUpdate(); 
        if(i == 0){
            salida.println(" \n No se puedo borrar el alumno");
            salida.flush();
        }else{
            salida.println(" \n Se borró el alumno con éxito");
            salida.flush();
        }
     
    }
    public Alumno read(Alumno a){
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        obtenerConexion();
        try{
            ps = con.prepareStatement(SQL_SELECT); 
            ps.setString(1,a.getNoBoleta()); 
            rs = ps.executeQuery(); 
            List resultado = obtenerRersultados(rs); 
            if(resultado.size()>0){
                return (Alumno)resultado.get(0); 
            }else{
                return null; 
                
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            System.err.println("Error");
        }
        return null; 
    }
    private List obtenerRersultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList(); 
        while(rs.next()){
            Alumno a = new Alumno(); 
            a.setNoBoleta(rs.getString("noBoleta"));
            a.setNombre(rs.getString("nombre"));
            a.setAp(rs.getString("ap"));
            a.setAm(rs.getString("am"));
            resultados.add(a); 
        }
        return resultados; 
    }
    public List readAll(){
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        obtenerConexion();
        List resultados = null; 
        try{
            ps = con.prepareStatement(SQL_SELECT_ALL); 
            rs = ps.executeQuery(); 
            resultados = obtenerRersultados(rs); 
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultados; 
    }
    private void obtenerConexion(){
        try{
            Class.forName(driver); 
            con = DriverManager.getConnection(URLBD, usuario, clave); 
        
        }catch(Exception e){
            e.printStackTrace();  
        }
    }    
}
