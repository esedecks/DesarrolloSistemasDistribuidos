/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import interfazrmi.Articulo;
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
public class ArticuloDAO {
     public boolean create(Articulo ar){
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
            return false; 
        }
        return true; 
    }
    
    public boolean update(Articulo ar,String nAnt){
        Transaction tr = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            String hql = "update Articulo set descripcion ='" +ar.getDescripcion() + "', existencias = '"+ar.getExistencias()+"', precio = "+ar.getPrecio()+"  where descripcion = '"+nAnt+"'";
            System.out.println("hql es "+hql);
            Query q = s.createQuery(hql);
            q.executeUpdate();
            //s.update(ar);
            tr.commit();
            System.err.println("Se actualizó el articulo correctamente ") ; 
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
            return false; 
            
        }
        return true; 
    }
    public boolean delete(Articulo ar){
        Transaction tr = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            Query q = s.createQuery("delete Articulo where descripcion = '"+ar.getDescripcion()+"'");
            q.executeUpdate();
            
            tr.commit();
            System.err.println("Se borró el articulo correctamente ") ; 
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
            return false; 
        }
        return true; 
    }
    
    public Articulo  readOne(Articulo a){
        Transaction tr = null;
        List lista = null ; 
        Articulo artiTemp = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            artiTemp = (Articulo)s.get(a.getClass(), a.getIdArticulo()); 
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
            Query q = s.createQuery("from Articulo"); 
            lista = q.list(); 
            tr.commit();
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
        }
        return lista; 
    }
    public List leerInfoForChart(String hql){
        Transaction tr = null;
        List lista = null ; 
        
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            lista = new ArrayList(); 
            Query q = s.createQuery(hql); 
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
