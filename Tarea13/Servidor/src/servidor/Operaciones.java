/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import interfazrmi.OperacionesRemota;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author esedecks
 */
public class Operaciones extends UnicastRemoteObject  implements OperacionesRemota {
   
    private double promedio ; 
    public Operaciones ()throws RemoteException{
        super(); 
    }
    @Override
    public double suma(double a, double b) throws RemoteException {
        return a +b ; 
    }

    @Override
    public double resta(double a, double b) throws RemoteException {
        return a -b; 
    }

    @Override
    public double multiplica(double a, double b) throws RemoteException {
        return a *b; 
    }

    @Override
    public double divide(double a, double b) throws RemoteException {
        return b == 0 ? 0 : a/b; 
    }

    @Override
    public double promedio(double[] numeros) throws RemoteException {
        this.promedio = 0; 
        for(double numero : numeros){
            this.promedio = this.promedio +numero; 
        }
        return this.promedio / numeros.length; 
    }

    @Override
    public double desviacion(double[] numeros) throws RemoteException {
        double temp = 0; 
        this.promedio  = promedio(numeros); 
         
        for(double numero: numeros){
           temp  = temp + (Math.pow((numero-promedio),2));  
        }
        double mtemp = (double) ((double)1/(double)numeros.length); 
         System.err.println("mtemp "+mtemp);

        return Math.sqrt(mtemp*temp); 
    }
    
}
