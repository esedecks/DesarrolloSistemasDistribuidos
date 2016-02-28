
package serverbox.pojos;

/**
 *
 * @author esedecks
 */
public class Alumno {
    private String noBoleta ; 
    private String nombre ; 
    private String ap ; 
    private String am ; 
    
    
    public Alumno(){
    
    }

    public Alumno(String noBoleta, String nombre, String ap, String am) {
        this.noBoleta = noBoleta;
        this.nombre = nombre;
        this.ap = ap;
        this.am = am;
    }

    public String getNoBoleta() {
        return noBoleta;
    }

    public void setNoBoleta(String noBoleta) {
        this.noBoleta = noBoleta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }
    
     @Override
    public String toString(){
        return "NoBoleta: "+this.noBoleta+"\n"+
                "nombre: "+this.nombre+"\n"+
                "ap: "+this.ap+"\n"+
                "am: "+this.am; 
    }
}
