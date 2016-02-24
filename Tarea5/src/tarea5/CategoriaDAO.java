package tarea5;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tarea5.Categoria;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author esedecks
 */
public class CategoriaDAO {
    private static final String SQL_INSERT = "insert into categoria(nombre,rango) values(?,?)"; 
    private static final String SQL_SELECT = "select * from categoria where idCategoria=?"; 
    private static final String SQL_UPDATE = "update categoria set nombre=?,rango=? where idCategoria=?"; 
    private static final String SQL_SELECT_ALL = "select * from categoria"; 
     private static final String SQL_DELETE = "delete from categoria where idCategoria=?"; 
    java.sql.Connection con; 
    Statement st ; 
    String driver = "com.mysql.jdbc.Driver"; 
    String usuario = "root"; 
    String clave ="1234";
    String URLBD = "jdbc:mysql://localhost:3306/Categoria"; 
    
    public void create(Categoria c ){
        PreparedStatement ps = null; 
        obtenerConexion(); 
        try{
            ps  = con.prepareStatement(SQL_INSERT); 
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getRango());
            ps.executeUpdate(); 
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void update(Categoria c){
        PreparedStatement ps = null; 
        obtenerConexion(); 
        try{
            ps  = con.prepareStatement(SQL_UPDATE); 
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getRango());
            ps.setInt(3, c.getCategoria());
            ps.executeUpdate(); 
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
     public void delete(Categoria c){
        PreparedStatement ps = null; 
        obtenerConexion(); 
        try{
            ps  = con.prepareStatement(SQL_DELETE); 
            ps.setInt(1, c.getCategoria()); 
            ps.executeUpdate(); 
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public Categoria read(Categoria c){
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        obtenerConexion();
        try{
            ps = con.prepareStatement(SQL_SELECT); 
            ps.setInt(1, c.getCategoria()); 
            rs = ps.executeQuery(); 
            List resultado = obtenerRersultados(rs); 
            if(resultado.size()>0){
                return (Categoria)resultado.get(0); 
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
            Categoria c = new Categoria(); 
            c.setCategoria(rs.getInt("idCategoria"));
            c.setNombre(rs.getString("nombre"));
            c.setRango(rs.getString("rango"));
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
