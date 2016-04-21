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
@Table (name = "articulo")
public class Articulo implements Serializable {
    @Id
    private int idArticulo; 
    private String descripcion; 
    private float precio; 
    private int existencias; 

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }
    public String toString(){
        return "idArticulo "+idArticulo 
              +"\ndescipcion "+  descripcion
              +"\nprecio "+ precio 
              +"\nexistencias"+ existencias + "\n"; 
    }
    
}
