/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio10hibernateejemplo;

import DAOs.ArticuloDAO;
import pojos.Articulo;

/**
 *
 * @author esedecks
 */
public class Ejercicio10HibernateEjemplo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Articulo a = new Articulo(); 
        a.setDescripcion("Baraja Nemónica");
        a.setPrecio(20.6f);
        a.setExistencias(100);
        
        ArticuloDAO artDao = new ArticuloDAO(); 
        artDao.create(a);
    
    }
    
}
