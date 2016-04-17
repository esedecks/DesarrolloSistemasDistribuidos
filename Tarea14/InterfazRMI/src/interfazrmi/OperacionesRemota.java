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

/**
 *
 * @author esedecks
 */
public interface OperacionesRemota extends Remote {
    public double suma(double a , double b ) throws RemoteException; 
    public double resta(double a , double b ) throws RemoteException; 
    public double multiplica(double a , double b ) throws RemoteException; 
    public double divide (double a , double b ) throws RemoteException; 
    public double potencia(double a , double b ) throws RemoteException; 
    public double modulo(double a , double b ) throws RemoteException; 
    public double factorial(double a  ) throws RemoteException; 
    public double maximo(double[] numeros  ) throws RemoteException; 
    public double minimo(double[] numeros) throws RemoteException; 
    public double promedio(double[]numeros) throws RemoteException; 
    public double desviacion(double[] numeros ) throws RemoteException; 
}