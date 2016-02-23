/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea5;

/**
 *
 * @author esedecks
 */
public class Tarea5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CategoriaDAO cat = new CategoriaDAO(); 
        Categoria categoria = new Categoria();
        
        categoria.setNombre("Mosca");
        categoria.setRango("51 - 100 kg");
        categoria.setCategoria(1);
        
        cat.delete(categoria);
    }
    
}
