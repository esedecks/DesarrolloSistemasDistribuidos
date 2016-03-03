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
public class Competidor {
    private int idCompetidor ; 
    private String nombre; 
    private String peso ; 
    private String escuelaOrigen; 
    private int idCategoria; 
    
    public Competidor() {
    }

    public Competidor(String nombre, String peso, String escuelaOrigen, int idCategoria) {
        this.nombre = nombre;
        this.peso = peso;
        this.escuelaOrigen = escuelaOrigen;
        this.idCategoria = idCategoria;
    }

    public Competidor(int idCompetidor, String nombre, String peso, String escuelaOrigen, int idCategoria) {
        this.idCompetidor = idCompetidor;
        this.nombre = nombre;
        this.peso = peso;
        this.escuelaOrigen = escuelaOrigen;
        this.idCategoria = idCategoria;
    }

    public Competidor(String nombre, String peso, String escuelaOrigen) {
        this.nombre = nombre;
        this.peso = peso;
        this.escuelaOrigen = escuelaOrigen;
    }

    public int getIdCompetidor() {
        return idCompetidor;
    }

    public void setIdCompetidor(int idCompetidor) {
        this.idCompetidor = idCompetidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEscuelaOrigen() {
        return escuelaOrigen;
    }

    public void setEscuelaOrigen(String escuelaOrigen) {
        this.escuelaOrigen = escuelaOrigen;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    @Override
    public String toString(){
        return "idCompetidor: "+this.idCompetidor+"\n"+
                "nombre: "+this.nombre+"\n"+
                "peso: "+this.peso+"\n"+"\n"+
                "idCategoria: "+this.idCategoria+"\n"+
                "Escuela de origen: "+this.escuelaOrigen; 
    }
}
