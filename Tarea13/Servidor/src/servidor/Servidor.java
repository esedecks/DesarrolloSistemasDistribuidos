/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import interfazrmi.OperacionesRemota;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 *
 * @author esedecks
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Registry registry = LocateRegistry.createRegistry( 1099 );
            OperacionesRemota op = (OperacionesRemota) new Operaciones(); 
            registry.rebind("rmiServer", op);
            System.out.println("Servidor listo"); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
