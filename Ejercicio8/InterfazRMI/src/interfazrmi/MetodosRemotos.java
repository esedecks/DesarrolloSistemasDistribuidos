/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazrmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author esedecks
 */
public interface MetodosRemotos extends Remote {
    //implementar todos los métodos remotos
    public boolean autenticarUsuario(String usuario,String password) throws RemoteException; 
    public boolean insertarProducto(String descripcion,String existencias,String precio) throws RemoteException; 
    public String leerArticulos() throws RemoteException; 
    public boolean eliminarArticulo(String nombre ) throws RemoteException; 
    public String leerInfoArticulo(String nombre ) throws RemoteException; 
    public boolean actualizarArticulo(String descripcion,String existencias, String precio,String nombreAnterior) throws RemoteException; 
    public boolean realizarMovimiento(String nombreArticulo,String tipoMovimiento, String cantidad) throws RemoteException; 
    public String leerInfoForChart() throws RemoteException; 
    public String getCorreoUsuario(String usuario) throws RemoteException; 
    public String leerNoExistencias (String descripcion)throws RemoteException; 
}
