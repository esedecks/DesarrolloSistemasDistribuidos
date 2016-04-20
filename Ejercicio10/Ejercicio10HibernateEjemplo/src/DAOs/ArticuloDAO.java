package DAOs;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.mapping.Array;
import pojos.Articulo;
import util.HibernateUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author esedecks
 */
public class ArticuloDAO {
    public void create(Articulo ar){
        Transaction tr = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            s.save(ar); 
            tr.commit();
            System.err.println("Se guardó el articulo correctamente ") ; 

        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
        }
        
    }
    
    public void update(Articulo ar){
        Transaction tr = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            s.update(ar);
            tr.commit();
            System.err.println("Se actualizó el articulo correctamente ") ; 
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
        }
    }
    public void delete(Articulo ar){
        Transaction tr = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            s.delete(ar);
            tr.commit();
            System.err.println("Se borró el articulo correctamente ") ; 
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
        }
    }
    
    public Articulo  readOne(Articulo a){
        Transaction tr = null;
        List lista = null ; 
        Articulo artiTemp = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            artiTemp = (Articulo)s.get(artiTemp.getClass(), artiTemp.getIdArticulo()); 
            tr.commit();
            System.err.println("Se borró el articulo correctamente ") ; 
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
        }
        return artiTemp; 
    }
    public List readAll(){
        Transaction tr = null;
        List lista = null ; 
        
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            lista = new ArrayList(); 
            Query q = s.createQuery("from articulo"); 
            lista = q.list(); 
            tr.commit();
            System.err.println("Se borró el articulo correctamente ") ; 
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
        }
        return lista; 
    }
}
