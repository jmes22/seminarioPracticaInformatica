package salgueroortiz.agilscrum.BE;

import java.time.LocalDate;

/**
 *
 * @author juan1
 */
public class Sprint {

    private int id;
    private int numero;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Estado estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Proyecto{"
                + "numero='" + numero + '\''
                + ", fecha de inicio=" + fechaInicio
                + ", fecha de fin=" + ((fechaFin != null) ? fechaFin.toString() : "no tiene")
                + ", estado=" + estado.getNombre()
                + '}';
    }
}
