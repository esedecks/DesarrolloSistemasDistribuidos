package servidor;


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
    
    public boolean update(Articulo ar){
        Transaction tr = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            s.merge(ar);
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
            s.delete(ar);
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
}
