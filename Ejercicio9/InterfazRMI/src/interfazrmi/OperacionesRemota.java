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
   public String  consultarTema(String url) throws RemoteException;
}