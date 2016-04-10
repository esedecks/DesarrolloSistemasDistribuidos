/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import javax.jdo.annotations.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;

/**
 *
 * @author esedecks
 */
@Entity
@Table (name="categoria")
public class Categoria implements Serializable{
    
    public static void main(String args[]){
        Categoria c = new Categoria("categoria test","Test de categoría");
        c.insertar(c);
        System.err.println("se insertó ");
    }
    
    @Id
    private int idCategoria; 
    @Column (name ="nombre")
    private String nombreCategoria; 
    private String descripcion; 
    public void insertar (Categoria cat){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAWEBPU");
        EntityManager em = emf.createEntityManager(); 
        em.getTransaction().begin();
        em.persist(cat);
        em.getTransaction().commit();
        /*
            em.remove (cat); em.merge(cat).
                    cat = (Categoria )em.find(Categoria.class,cat.getIdCategoria()); 
            Todas las instancias de categoriaa
            Query q = em.createQuery("select c from Categoria c");
        */

         
    }

    public Categoria( String nombreCategoria, String descripcion) {
        this.nombreCategoria = nombreCategoria;
        this.descripcion = descripcion;
    }
    
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
