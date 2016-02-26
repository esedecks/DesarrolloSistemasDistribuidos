/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbox.pojos;

/**
 *
 * @author esedecks
 */
public class Usuario {
    private int idUsuario; 
    private String username ; 
    private String pwd; 

    public Usuario() {
    }

    public Usuario(int idUsuario, String username, String pwd) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.pwd = pwd;
    }

    public Usuario(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
     @Override
    public String toString(){
        return "idCategoria: "+this.idUsuario+"\n"+
                "nombre: "+this.username+"\n"+
                "rango: "+this.pwd+"\n"; 
    }
}
