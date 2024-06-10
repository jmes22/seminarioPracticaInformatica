package salgueroortiz.agilscrum;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author juan1
 */
public class Proyecto {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Estado estado;
    private final ArrayList<Sprint> sprints;
    
    public Proyecto(){
        sprints = new ArrayList<>();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public ArrayList<Sprint> getSprints() {
        return sprints;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
     
   @Override
    public String toString() {
        return "Proyecto{" +
                "nombre ='" + nombre + '\'' +
                ", fecha de inicio =" + fechaInicio +
                ", fecha de fin =" + fechaFin +
                ", estado =" + estado.getNombre() +
                ", cantidad de Sprints = " + sprints.size() +
                '}';
    }
}
