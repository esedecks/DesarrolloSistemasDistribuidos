
package serverbox.pojos;

public class Categoria {
    private int categoria; 
    private String nombre, rango; 
    public Categoria (){
    }

    public Categoria(String nombre, String rango) {
        this.nombre = nombre;
        this.rango = rango;
    }

    public Categoria(int categoria, String nombre, String rango) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.rango = rango;
    }
    
    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }
   
    @Override
    public String toString(){
        return "idCategoria: "+this.categoria+"\n"+
                "nombre: "+this.nombre+"\n"+
                "rango: "+this.rango+"\n"; 
    }
}
