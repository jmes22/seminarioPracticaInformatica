package salgueroortiz.agilscrum.BE;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author juan1
 */
public class Proyecto {

    private int id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Estado estado;
    private int cantidadSprint;
    private final ArrayList<Sprint> sprints;

    public Proyecto() {
        sprints = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidadSprint() {
        return cantidadSprint;
    }

    public void setCantidadSprint(int cantidadSprint) {
        this.cantidadSprint = cantidadSprint;
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

    public Estado getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Proyecto: "
                + "Nombre ='" + nombre + '\''
                + ", Fecha de inicio =" + (fechaInicio != null ? fechaInicio.toString() : "null")
                + ", Fecha de fin =" + (fechaFin != null ? fechaFin.toString() : "null")
                + ", Estado =" + (estado != null ? estado.getNombre() : "null")
                + ", Cantidad de Sprints = " + cantidadSprint
                + '.';
    }

}
