/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import interfazrmi.OperacionesRemota;
import static java.lang.System.setSecurityManager;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author esedecks
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
       // System.setProperty("java.security.policy","file:///home/esedecks/github/DesarrolloSistemasDistribuidos/Tarea13/Cliente/src/cliente/security.policy"); 
        double[] numeros = {5,5,6,4,2}; 
        Registry registro = LocateRegistry.getRegistry("127.0.0.1", 1099); 
        OperacionesRemota op = (OperacionesRemota) registro.lookup("rmiServer"); 
        System.err.println("suma : "+op.suma(numeros[0],numeros[1])); 
        System.err.println("resta : "+op.resta(numeros[0],numeros[1]));
        System.err.println("multiplica : "+op.multiplica(numeros[0],numeros[1]));
        System.err.println("divide : "+op.divide(numeros[0],numeros[1]));
        System.err.println("promedio : "+op.promedio(numeros));
        System.err.println("desviación : "+op.desviacion(numeros));
        
    }
    
}
