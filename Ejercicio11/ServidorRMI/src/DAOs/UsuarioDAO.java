/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;


import interfazrmi.Usuario;
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
public class UsuarioDAO {
       public boolean create(Usuario ar){
        Transaction tr = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            s.save(ar); 
            tr.commit();
            System.err.println("Se guardó el usuario correctamente ") ; 
            
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
            return false; 
        }
        return true; 
    }
    
    public boolean update(Usuario ar){
        Transaction tr = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            s.merge(ar);
            tr.commit();
            System.err.println("Se actualizó el usuario correctamente ") ; 
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
            return false; 
            
        }
        return true; 
    }
    public boolean delete(Usuario ar){
        Transaction tr = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            s.delete(ar);
            tr.commit();
            System.err.println("Se borró el usuario correctamente ") ; 
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
            return false; 
        }
        return true; 
    }
    
    public Usuario  readOne(Usuario a){
        Transaction tr = null;
        List lista = null ; 
        Usuario artiTemp = null; 
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            artiTemp = (Usuario)s.get(a.getClass(), a.getIdUsuario()); 
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
            Query q = s.createQuery("from Usuario"); 
            lista = q.list(); 
            tr.commit();
        }catch(HibernateException he){
            if(tr!=null && tr.isActive())tr.rollback();
            System.err.println("Sucedió un error") ; 
            he.printStackTrace();
        }
        return lista; 
    }
    public boolean autenticateUser (String usuario, String password){
        List lista = readAll();
        for(Object  o : lista){
            Usuario u = (Usuario)o; 
            System.err.println(u.toString());
            if(u.getUsuario().equals(usuario) && u.getPassword().equals(password)){
                System.err.println("Lo encontró");
                return true; 
            }
        }
        System.err.println("No lo encontró");
        
        return false;     
    }
    public List getCorreoUsuario (String usuario){
        Transaction tr = null;
        List lista = null ; 
        
        try{
            org.hibernate.Session s = HibernateUtil.getSessionFactory().getCurrentSession(); 
            tr = s.getTransaction(); 
            tr.begin();
            lista = new ArrayList(); 
            Query q = s.createQuery("select U.correo FROM Usuario U WHERE U.usuario = '"+usuario+"'"); 
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
