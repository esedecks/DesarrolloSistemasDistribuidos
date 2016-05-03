/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorrmi;

import DAOs.ArticuloDAO;
import DAOs.MovArticuloDAO;
import DAOs.UsuarioDAO;
import interfazrmi.Articulo;
import interfazrmi.MetodosRemotos;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import modelo.SQLConexion;

/**
 *
 * @author esedecks
 */
public class Metodos extends UnicastRemoteObject  implements MetodosRemotos {
    SQLConexion conexionBD ; 
    ArticuloDAO artDAO; 
    MovArticuloDAO mvArtDAO; 
    UsuarioDAO usDAO; 
    
    
    public Metodos() throws RemoteException{
        super();
        
    }

    @Override
    public boolean autenticarUsuario(String usuario, String password) throws RemoteException {
        usDAO = new UsuarioDAO(); 
        System.err.println("Seejecuta autenticar usuario");

        boolean res = usDAO.autenticateUser(usuario, password); 
        if(res )return true; 
        else{
            System.err.println("regreso null autenticar usuario");
            return false; 
        }
        /*conexionBD = new SQLConexion(); 
        String consulta = "select idUsuario from usuario where usuario = '"+usuario+"' and password ='"+password+"'"; 
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        //System.err.println("El resultado de la consulta es: "+resultado); 
        if(resultado.equals("noDatos"))return false; 
        else return true; */

    }

    @Override
    public boolean insertarProducto(String descripcion, String existencias, String precio) throws RemoteException {
        System.err.println("Entra en isnertar articulo");
        artDAO = new ArticuloDAO(); 
        Articulo articulo = new Articulo(); 
        articulo.setDescripcion(descripcion);
        articulo.setExistencias(Integer.parseInt(existencias));
        articulo.setPrecio(Float.parseFloat(precio));
        return artDAO.create(articulo); 
       
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
        /**conexionBD = new SQLConexion();
        String consulta = "delete from articulo\n" +
                           "where descripcion = '"+nombre+"'"; 
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        if(resultado.equals("0"))return false; 
        else return true; 
        * */
         System.err.println("Entra en elimianr articulo");
        artDAO = new ArticuloDAO(); 
        Articulo articulo = new Articulo(); 
        articulo.setDescripcion(nombre);
        return artDAO.delete(articulo); 
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
        /*String consulta = "update articulo "
                        + "set descripcion ='"+descripcion+"', precio='"+precio+"', existencias = '"+existencias+"'"
                        +" where descripcion = '"+nombreAnterior+"'";
        System.err.println("La consulta queda como "+consulta); 
        conexionBD = new SQLConexion();
    
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        if(resultado.equals("0"))return false; 
        else return true; */
         System.err.println("Entra en actualizar articulo");
        artDAO = new ArticuloDAO(); 
        Articulo articulo = new Articulo(); 
        articulo.setDescripcion(descripcion);
        articulo.setExistencias(Integer.parseInt(existencias));
        articulo.setPrecio(Float.parseFloat(precio));
       
        return artDAO.update(articulo, nombreAnterior);
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
        
        /*
        conexionBD = new SQLConexion();
        String consulta = "select correo\n" +
                          " from usuario where usuario ='"+usuario+"'"; 
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        System.err.println("El resultado es: "+resultado ); 
        
                */
        String resultado = ""; 
        usDAO = new UsuarioDAO(); 
        System.err.println("Seejecuta get correo usuario");

        List res = usDAO.getCorreoUsuario(usuario); 
        for(Object o : res){
           resultado = o.toString(); 
        }
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
