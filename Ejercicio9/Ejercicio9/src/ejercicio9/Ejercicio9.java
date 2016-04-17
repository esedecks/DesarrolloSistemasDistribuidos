/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio9;

import interfazrmi.OperacionesRemota;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author esedecks
 */
public class Ejercicio9 {

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     */
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registro = LocateRegistry.getRegistry("127.0.0.1", 1099); 
        OperacionesRemota op = (OperacionesRemota) registro.lookup("rmiServer"); 
        String resultado = op.consultarTema("https://en.wikipedia.org/wiki/Java_%28programming_language%29"); 
        System.err.println("El resultado remoto es: "); 
        System.out.println(resultado);
    }
    
}
