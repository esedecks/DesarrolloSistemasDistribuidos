/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea8producto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esedecks
 */
public class ProductoDAO {
    public static synchronized boolean insertarProducto(Producto p ){
        Connection cn = null; 
        CallableStatement cs = null; 
        boolean respuesta = false; 
        try{
            String call = "{CALL insertarProducto(?,?,?)}";
            System.err.println("1");
            cn = Conexion.getConnextion(); 
            cn.setAutoCommit(false);
            cs = cn.prepareCall(call); 
            cs.setInt(1,p.getIdProducto());
            cs.setString(2, p.getNombre());
            cs.setDouble(3, p.getPrecio());
            System.err.println("2");
            respuesta =cs.executeUpdate() == 1?true: false;
            System.err.println("3");
            if(respuesta){
                 
                cn.commit();
                System.err.println("Commit after"); 
            }else{
                Conexion.deshacerCambios(cn); 
            }
            Conexion.cerrar(cn);
            Conexion.cerrar(cs);
        }catch(Exception e ){
            e.printStackTrace();
        }
        finally{
            return respuesta; 
        }
    }
    
    public static synchronized boolean actualizarProducto(Producto p ){
        Connection cn = null; 
        CallableStatement cs = null; 
        boolean respuesta = false; 
        try{
            String call = "{CALL actualizarProducto(?,?,?)}";
            cn = Conexion.getConnextion(); 
            cn.setAutoCommit(false);
            cs = cn.prepareCall(call); 
            cs.setInt(1,p.getIdProducto());
            cs.setString(2, p.getNombre());
            cs.setDouble(3, p.getPrecio());
            respuesta = ((cs.executeUpdate() == 1)?true: false);
            if(respuesta){
                cn.commit();
                System.err.println("se hizo la modificación");
            }else{
                Conexion.deshacerCambios(cn); 
                System.err.println("no se hizo la modificación");
            }
            Conexion.cerrar(cn);
            Conexion.cerrar(cs);
            
        }catch(Exception e ){
            e.printStackTrace();
        }
        finally{
            return respuesta; 
        }
    }
    public static synchronized boolean borrarProducto(Producto p ){
        Connection cn = null; 
        CallableStatement cs = null; 
        boolean respuesta = false; 
        try{
            String call = "{CALL borrarProducto(?)}";
            cn = Conexion.getConnextion(); 
            cn.setAutoCommit(false);
            cs = cn.prepareCall(call); 
            cs.setInt(1,p.getIdProducto());
            respuesta = ((cs.executeUpdate() == 1)?true: false);
            if(respuesta){
                cn.commit();
            }else{
                Conexion.deshacerCambios(cn); 
            }
            Conexion.cerrar(cn);
            Conexion.cerrar(cs);
        }catch(Exception e ){
            e.printStackTrace();
        }
        finally{
            return respuesta; 
        }
    }
    public static synchronized Producto mostrarProducto(Producto ptemp){
        Producto p = new Producto(); 
        Connection cn = null; 
        CallableStatement cs = null; 
        ResultSet rs = null; 
        try{
            String call = "{CALL mostrarProducto(?)}"; 
            cn = Conexion.getConnextion(); 
            cs = cn.prepareCall(call); 
            cn.setAutoCommit(false);
            cs = cn.prepareCall(call); 
            cs.setInt(1,ptemp.getIdProducto());
            rs = cs.executeQuery(); 
            while(rs.next()){
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
            }
            Conexion.cerrar(cs);
            Conexion.cerrar(cn);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            return p; 
        }
    }
    
    public static synchronized ArrayList<Producto> mostrarProductos(){
        ArrayList<Producto> lista = new ArrayList<Producto>(); 
        Connection cn = null; 
        CallableStatement cs = null; 
        ResultSet rs = null; 
        try{
            cn = Conexion.getConnextion(); 
            cs = cn.prepareCall("{CALL mostrarProductos()}"); 
            rs = cs.executeQuery(); 
            while(rs.next()){
                Producto p = new Producto(); 
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                lista.add(p); 
            }
            Conexion.cerrar(cs);
            Conexion.cerrar(cn);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            
            return lista; 
        }
    }
}
