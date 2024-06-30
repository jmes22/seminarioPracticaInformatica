package salgueroortiz.agilscrum.BE;

import java.time.LocalDate;

/**
 *
 * @author juan1
 */
public class Tarea {
    private int id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private int tiempoEstimaRealizar;
    private int tiempoEjecucion;
    private LocalDate fechaFinEstimada;
    private LocalDate fechaFinReal;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.nombre = descripcion;
    }
    
    public int getTiempoEstimaRealizar() {
        return tiempoEstimaRealizar;
    }

    public void setTiempoEstimaRealizar(int tiempoEstimaRealizar) {
        this.tiempoEstimaRealizar = tiempoEstimaRealizar;
    }
    
    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(int tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }
    
    public LocalDate getFechaFinEstimada() {
        return fechaFinEstimada;
    }

    public void setFechaFinEstimada(LocalDate fechaFinEstimada) {
        this.fechaFinEstimada = fechaFinEstimada;
    }
    
    public LocalDate getFechaFinReal() {
        return fechaFinReal;
    }

    public void setFechaFinReal(LocalDate fechaFinReal) {
        this.fechaFinReal = fechaFinReal;
    }
}
