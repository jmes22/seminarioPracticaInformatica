package salgueroortiz.agilscrum.BE;

/**
 *
 * @author juan1
 */
public enum AccionEnum {
    REGISTRAR_PROYECTO,
    LISTAR_PROYECTO,
    REGISTRAR_SPRINT,
    LISTAR_SPRINT,
    ASIGNAR_SPRINT_PROYECTO,
    REGISTRAR_TAREA,
    ASIGNAR_TAREA,
    ASIGNAR_DESARROLLADOR,
    GENERAR_REPORTE_DESVIO,
    CONSULTAR_TAREAS_ASIGNADAS,
    ASIGNAR_ESTIMACION,
    ASIGNAR_ESTADO_TAREA,
    CONSULTAR_PROYECTO,
    ACCION_NO_RECONOCIDA // Para manejar acciones no reconocidas
}