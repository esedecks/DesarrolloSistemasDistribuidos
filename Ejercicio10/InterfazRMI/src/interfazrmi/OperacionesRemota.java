/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazrmi;

/**
 *
 * @author esedecks
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author esedecks
 */
public interface OperacionesRemota extends Remote {
    public boolean create(Articulo  ar) throws RemoteException; 
    public boolean update(Articulo ar) throws RemoteException; 
    public boolean delete(Articulo ar) throws RemoteException; 
    
    public Articulo  readOne(Articulo a) throws RemoteException; 
    public List readAll() throws RemoteException; 
            
    }