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
import interfazrmi.MovArticulo;
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
  
        conexionBD = new SQLConexion();
        String consulta = "select idArticulo from articulo where descripcion='"+nombreArticulo+"'"; 
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        String resultado = conexionBD.getResultado(); 
        resultado = resultado.replace('|', '\0').trim().replace('\"', '\0');
        System.err.println("el id es : "+resultado); 
        mvArtDAO = new MovArticuloDAO(); 
        MovArticulo mvArticulo = new MovArticulo(); 
        mvArticulo.setFechaMovimiento( new java.util.Date());
        mvArticulo.setCantidad(Integer.parseInt(cantidad));
        mvArticulo.setTipo(tipoMovimiento);
        //necesito obtener el id del articulo 
        mvArticulo.setIdArticulo(Integer.parseInt(resultado));
        mvArtDAO.create(mvArticulo); 
        
        
        consulta = "update articulo\n" +
                    "set existencias = existencias"+ (tipoMovimiento.contains("entrada")?" + ": " - ")+ cantidad+
                    " where descripcion = '"+nombreArticulo+"'"; 
         
        conexionBD.setConsulta(consulta);
        conexionBD.ejecutarSQL();
        return true; 
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
