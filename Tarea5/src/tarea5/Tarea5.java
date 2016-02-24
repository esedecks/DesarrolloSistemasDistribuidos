/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea5;

import java.util.List;

/**
 *
 * @author esedecks
 */
public class Tarea5 {
    public static void main(String[] args) {
        CategoriaDAO cat = new CategoriaDAO(); 
        
        
        Categoria categoria = new Categoria();
        categoria.setCategoria(1);
        cat.delete(categoria);
         
        //List lista = cat.readAll(); 
        //for(int i = 0; i<lista.size(); i++)
          // System.err.println(((Categoria)lista.get(i)).toString());  
        
        //Categoria c = cat.read(categoria); 
        //System.err.println(c.toString());
        
        
        //cat.update(categoria);
    }
    
}
