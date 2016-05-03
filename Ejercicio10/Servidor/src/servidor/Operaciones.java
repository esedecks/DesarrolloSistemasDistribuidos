/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import interfazrmi.Articulo;
import interfazrmi.OperacionesRemota;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


/**
 *
 * @author esedecks
 */
public class Operaciones extends UnicastRemoteObject  implements OperacionesRemota {
    ArticuloDAO artDao;  
    public Operaciones ()throws RemoteException{
        super(); 
        artDao = new ArticuloDAO(); 
    }

    @Override
    public boolean create(Articulo ar) throws RemoteException {
        return artDao.create(ar);
    }

    @Override
    public boolean update(Articulo ar) throws RemoteException {
        return artDao.update(ar);
    }

    @Override
    public boolean delete(Articulo ar) throws RemoteException {
        return artDao.delete(ar);
    }

    @Override
    public Articulo readOne(Articulo ar) throws RemoteException {
        return artDao.readOne(ar);
    }

    @Override
    public List readAll() throws RemoteException {
        return artDao.readAll(); 
    }

    
}
