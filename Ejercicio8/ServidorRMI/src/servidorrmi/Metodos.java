/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorrmi;

import interfazrmi.MetodosRemotos;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import modelo.SQLConexion;

/**
 *
 * @author esedecks
 */
public class Metodos extends UnicastRemoteObject  implements MetodosRemotos {
    SQLConexion conexionBD ; 
    public Metodos() throws RemoteException{
        super();
        
    }

    @Override
    public boolean autenticarUsuario(String usuario, String password) throws RemoteException {
        conexionBD = new SQLConexion(); 
        String consulta = "select idUsuario from usuario where usuario = '"+usuario+"' and password ='"+password+"'"; 
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        //System.err.println("El resultado de la consulta es: "+resultado); 
        if(resultado.equals("noDatos"))return false; 
        else return true; 

    }

    @Override
    public boolean insertarProducto(String descripcion, String existencias, String precio) throws RemoteException {
        conexionBD = new SQLConexion();
        String consulta = "insert into articulo values(null,'"+descripcion+"','"+precio+"','"+existencias+"')"; 
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        if(resultado.equals("0"))return false; 
        else return true; 
    }

    @Override
    public String leerArticulos() throws RemoteException {
        conexionBD = new SQLConexion();
        String consulta = "select descripcion from articulo"; 
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        return resultado; 
    }

    @Override
    public boolean eliminarArticulo(String nombre) throws RemoteException {
        conexionBD = new SQLConexion();
        String consulta = "delete from articulo\n" +
                           "where descripcion = '"+nombre+"'"; 
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        if(resultado.equals("0"))return false; 
        else return true; 
    }

    @Override
    public String leerInfoArticulo(String nombre) throws RemoteException {
        conexionBD = new SQLConexion();
        String consulta = "select * from articulo where descripcion='"
                            +nombre +"'"; 
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        //System.err.println("El resultado es: "+resultado ); 
        return resultado;     
    }

    @Override
    public boolean actualizarArticulo(String descripcion, String existencias, String precio, String nombreAnterior) throws RemoteException {
        String consulta = "update articulo "
                        + "set descripcion ='"+descripcion+"', precio='"+precio+"', existencias = '"+existencias+"'"
                        +" where descripcion = '"+nombreAnterior+"'";
        System.err.println("La consulta queda como "+consulta); 
        conexionBD = new SQLConexion();
    
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        if(resultado.equals("0"))return false; 
        else return true; 
    }

    @Override
    public boolean realizarMovimiento(String nombreArticulo, String tipoMovimiento, String cantidad) throws RemoteException {
        //hacer la inserción en la tabla movArticulo, tener en cuenta que afecta 
        //la tabla articulo
        java.util.Date myDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        String consulta = "insert into movArticulo values"
                        + "(null,'"+sqlDate+"','"+tipoMovimiento+"','"+cantidad+"',(select idArticulo from articulo where descripcion='"+nombreArticulo+"'))";
        System.err.println("La consulta queda como "+consulta); 
        
        conexionBD = new SQLConexion();
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        if(resultado.equals("0"))return false;
        //hasta aquí se hace la inserción de movimiento falta la inserción de para modificar los articulo
        consulta = "update articulo\n" +
                    "set existencias = existencias"+ (tipoMovimiento.contains("entrada")?" + ": " - ")+ cantidad+
                    " where descripcion = '"+nombreArticulo+"'"; 
        System.err.println("La consulta de actiañiozaciaon como "+consulta); 
      
        conexionBD = new SQLConexion();
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        resultado = conexionBD.getResultado(); 
        if(resultado.equals("0"))return false;
        else return true; 
    }

    @Override
    public String leerInfoForChart() throws RemoteException {
        conexionBD = new SQLConexion();
        String consulta = "SELECT a.descripcion,count(ma.idArticulo)\n" +
                          " FROM movArticulo ma, articulo a \n" +
                          " where a.idArticulo = ma.idArticulo group by a.descripcion; "; 
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        System.err.println("El resultado es: "+resultado ); 
        return resultado;     
       
    }

    @Override
    public String getCorreoUsuario(String usuario) throws RemoteException {
        conexionBD = new SQLConexion();
        String consulta = "select correo\n" +
                          " from usuario where usuario ='"+usuario+"'"; 
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        System.err.println("El resultado es: "+resultado ); 
        return resultado;   
    }

    @Override
    public String leerNoExistencias(String descripcion) throws RemoteException {
        conexionBD = new SQLConexion();
        String consulta = "select existencias from articulo where descripcion = '"+descripcion+ "'";
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        System.err.println("El resultado es: "+resultado ); 
        return resultado;   
    }

    @Override
    public String getReporte() throws RemoteException {
        String consulta; 
        consulta = "select a.descripcion, mv.fechaMovimiento,\n" +
                            " mv.tipo, mv.cantidad  \n" +
                            " from articulo a, movArticulo mv\n" +
                            " where a.idArticulo = mv.idArticulo\n" +
                            " order by a.descripcion; ";
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
       String res =  conexionBD.getResultado();
        System.err.println("El resultado es: "+res ); 
        return res;   
    
    }
    
}
