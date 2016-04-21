/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio10hibernateejemplo;

import interfazrmi.Articulo;
import interfazrmi.OperacionesRemota;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

/**
 *
 * @author esedecks
 */
public class Ejercicio10HibernateEjemplo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registro; 
        OperacionesRemota metodosRemotos ;
        registro = LocateRegistry.getRegistry("127.0.0.1", 1099); 
        metodosRemotos = (OperacionesRemota) registro.lookup("rmiServer");
        Articulo a = new Articulo(); 
        a.setIdArticulo(14);
        a.setDescripcion("Baraja bycicle ");
        a.setExistencias(20);
        a.setPrecio(500);
        metodosRemotos.delete(a);
       
      
 
    
    }
    
}
