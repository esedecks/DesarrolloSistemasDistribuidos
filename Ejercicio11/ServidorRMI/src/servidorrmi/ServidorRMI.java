/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorrmi;

import interfazrmi.MetodosRemotos;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esedecks
 */
public class ServidorRMI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry( 1099 );
            MetodosRemotos op = (MetodosRemotos) new Metodos();
            registry.rebind("rmiServer", op); 
            System.out.println("Servidor RMI listo : "+new Date());
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
    
}
