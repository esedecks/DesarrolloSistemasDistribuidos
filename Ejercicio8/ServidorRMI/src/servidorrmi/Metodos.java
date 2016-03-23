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
    
}
