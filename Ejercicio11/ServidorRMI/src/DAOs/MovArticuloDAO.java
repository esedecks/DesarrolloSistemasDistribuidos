/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;


import interfazrmi.MovArticulo;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author esedecks
 */
public class MovArticuloDAO {
    public boolean create(MovArticulo ar){
        Transaction tr = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            s.save(ar); 
            tr.commit();
            System.err.println("Se guardó el movimiento correctamente ") ; 
            
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
            return false; 
        }
        return true; 
    }
    
    public boolean update(MovArticulo ar){
        Transaction tr = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            s.merge(ar);
            tr.commit();
            System.err.println("Se actualizó el movimiento correctamente ") ; 
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
            return false; 
            
        }
        return true; 
    }
    public boolean delete(MovArticulo ar){
        Transaction tr = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            s.delete(ar);
            tr.commit();
            System.err.println("Se borró el movimiento correctamente ") ; 
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
            return false; 
        }
        return true; 
    }
    
    public MovArticulo  readOne(MovArticulo a){
        Transaction tr = null;
        List lista = null ; 
        MovArticulo artiTemp = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            artiTemp = (MovArticulo)s.get(a.getClass(), a.getIdMovimiento()); 
            tr.commit();
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
            return null; 
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
            Query q = s.createQuery("from MovArticulo"); 
            lista = q.list(); 
            tr.commit();
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
        }
        return lista; 
    }
}
