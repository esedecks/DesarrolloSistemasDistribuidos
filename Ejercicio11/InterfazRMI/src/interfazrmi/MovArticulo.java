/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazrmi;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author esedecks
 */
@Entity
@Table (name = "movArticulo")
public class MovArticulo implements Serializable {
    @Id
    private int idMovimiento; 
    private java.sql.Date fechaMovimiento; 
    private String tipo; 
    private int cantidad; 
    private int idArticulo; 

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }
    
    
    
    public String toString(){
        return "idMovimiento "+idMovimiento 
              +"\fechaMovimiento "+  fechaMovimiento
              +"\tipo "+ tipo 
              +"\ncantidad"+ cantidad 
              + "\nidArticulo "+idArticulo; 
    }
    
}
