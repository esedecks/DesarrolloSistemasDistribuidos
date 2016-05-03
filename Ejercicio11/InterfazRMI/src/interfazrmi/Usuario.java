/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazrmi;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author esedecks
 */
@Entity
@Table (name = "usuario")
public class Usuario implements Serializable {
    @Id
    private int idUsuario; 
    private String usuario; 
    private String password ; 
    private String correo; 

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    public String toString(){
        return "idUsuario "+idUsuario 
              +"\nusuario "+  usuario
              +"\npassword "+ password 
              +"\ncorreo"+ correo + "\n"; 
    }
    
}