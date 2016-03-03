/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.io.PrintStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import serverbox.pojos.Competidor;

/**
 *
 * @author esedecks
 */
public class CompetidorDAO {
    private static final String SQL_INSERT = "insert into competidor(nombre,peso,escuelaOrigen,idCategoria) values(?,?,?,?)"; 
    private static final String SQL_SELECT = "select * from competidor where idCompetidor=?"; 
    private static final String SQL_UPDATE = "update competidor set nombre=?,peso=?,escuelaOrigen=?,idCategoria=? where idCompetidor=?"; 
    private static final String SQL_SELECT_ALL = "select * from competidor"; 
    private static final String SQL_DELETE = "delete from competidor where idCompetidor=?"; 
    
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
    public void create(Competidor c ){
        PreparedStatement ps = null; 
        obtenerConexion(); 
        try{
            ps  = con.prepareStatement(SQL_INSERT); 
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getPeso());
            ps.setString(3, c.getEscuelaOrigen());
            ps.setInt(4, c.getIdCategoria());
            ps.executeUpdate(); 
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void update(Competidor c){
        PreparedStatement ps = null; 
        obtenerConexion(); 
        try{
            ps  = con.prepareStatement(SQL_UPDATE); 
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getPeso());
            ps.setString(3, c.getEscuelaOrigen());
            ps.setInt(4, c.getIdCategoria());
            ps.setInt(5,c.getIdCompetidor()); 
            ps.executeUpdate(); 
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
     public void delete(Competidor c){
        PreparedStatement ps = null; 
        obtenerConexion(); 
        try{
            ps  = con.prepareStatement(SQL_DELETE); 
            ps.setInt(1, c.getIdCompetidor()); 
            int i = ps.executeUpdate();
            if(i == 0){
            salida.println(" \n No se puedo borrar el competidor");
            salida.flush();
            }else{
                salida.println(" \n Se borró el competidor con éxito");
                salida.flush();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public Competidor read(Competidor c){
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        obtenerConexion();
        try{
            ps = con.prepareStatement(SQL_SELECT); 
            ps.setInt(1, c.getIdCompetidor()); 
            rs = ps.executeQuery(); 
            List resultado = obtenerRersultados(rs); 
            if(resultado.size()>0){
                return (Competidor)resultado.get(0); 
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
            Competidor c = new Competidor(); 
            c.setIdCompetidor(rs.getInt("idCompetidor"));
            c.setNombre(rs.getString("nombre"));
            c.setPeso(rs.getString("peso"));
            c.setEscuelaOrigen(rs.getString("escuelaOrigen")); 
            resultados.add(c); 
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
