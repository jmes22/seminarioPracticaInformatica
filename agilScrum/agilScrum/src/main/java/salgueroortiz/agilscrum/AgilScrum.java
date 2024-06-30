package salgueroortiz.agilscrum;

import salgueroortiz.agilscrum.BE.*;
import salgueroortiz.agilscrum.DAO.DAOFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
//import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgilScrum {

    private static final Scanner scanner = new Scanner(System.in);

    // Cambiar por su puerto usuario y password.
    private static final String JDBCURL = "jdbc:mysql://localhost:3306/agil_scrum";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "JPS4lgu3r02!";
    private static Connection connection = null;
    private Usuario usuario = null;
    private static DAOFactory daoFactory = null;

    public static void main(String[] args) {
        try {
            // Registrar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBCURL, JDBC_USERNAME, JDBC_PASSWORD);
            daoFactory = DAOFactory.getDAOFactory(DAOFactory.FactoryType.MYSQL, connection);

            Usuario usuario = getUsuario(daoFactory);

            if (usuario == null) {
                System.out.println("No se selecciono ningún usuario valido.");
            } else {
                List<Accion> acciones = daoFactory.getAccionDAO().getAccionesByIdRol(usuario.getIdRol());

                int opcion;
                do {
                    mostrarMenuAcciones(acciones);
                    opcion = scanner.nextInt();
                    scanner.nextLine();

                    if (opcion >= 1 && opcion <= acciones.size()) {
                        Accion accionSeleccionada = acciones.get(opcion - 1);
                        ejecutarAccion(accionSeleccionada);
                    } else if (opcion != acciones.size() + 1) {
                        System.out.println("Opcion no valida.");
                    }
                } while (opcion != acciones.size() + 1);

                System.out.println("Ejecucion del programa finalizada.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontro el controlador JDBC. " + e.getMessage());
        }

    }

    private static Usuario getUsuario(DAOFactory daoFactory) {
        try {
            List<Usuario> usuarios = daoFactory.getUsuarioDAO().getUsuarios();
            if (usuarios != null && !usuarios.isEmpty()) {
                System.out.println("=== Ingrese un usuario ===");

                for (int i = 0; i < usuarios.size(); i++) {
                    Usuario usuario = usuarios.get(i);
                    System.out.println((i + 1) + " - " + usuario.getApellido() + ", " + usuario.getNombre());
                }

                int opcion;
                Usuario usuarioSeleccionado;
                do {
                    System.out.print("Seleccione una opcion: ");
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    if (opcion >= 1 && opcion <= usuarios.size()) {
                        usuarioSeleccionado = usuarios.get(opcion - 1);
                        System.out.println("Ha seleccionado: " + usuarioSeleccionado.getApellido() + ", " + usuarioSeleccionado.getNombre());
                        return usuarioSeleccionado; // Devuelve el usuario seleccionado
                    } else {
                        usuarioSeleccionado = null;
                        System.out.println("Opcion invalida. Por favor, intente de nuevo.");
                    }
                } while (usuarioSeleccionado == null);
            } else {
                System.out.println("Error: no hay usuarios registrados.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null; // Devuelve null si no hay usuarios o si ocurre un error
    }

    private static void mostrarMenuAcciones(List<Accion> acciones) {
        System.out.println("=== Menu de Acciones ===");
        for (int i = 0; i < acciones.size(); i++) {
            Accion accion = acciones.get(i);
            System.out.println((i + 1) + " - " + accion.getNombre());
        }
        System.out.println((acciones.size() + 1) + " - Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static void ejecutarAccion(Accion accion) {
        AccionEnum accionEnum = null;

        try {
            accionEnum = AccionEnum.valueOf(accion.getCodigo());
        } catch (IllegalArgumentException e) {
            accionEnum = AccionEnum.ACCION_NO_RECONOCIDA;
        }

        switch (accionEnum) {
            case REGISTRAR_PROYECTO ->
                registrarProyecto();
            case LISTAR_PROYECTO ->
                listarProyectos();
            case REGISTRAR_SPRINT ->
                registrarSprint();
            case LISTAR_SPRINT ->
                listarSprints();
            case ASIGNAR_SPRINT_PROYECTO ->
                asignarSprintAlProyecto();
            case REGISTRAR_TAREA ->
                System.out.println("Registrar tareas: opcion en desarrollo.");
            case ASIGNAR_TAREA ->
                System.out.println("Asignar tareas: opcion en desarrollo.");
            case ASIGNAR_DESARROLLADOR ->
                System.out.println("Asignar desarrollador: opcion en desarrollo.");
            case GENERAR_REPORTE_DESVIO ->
                System.out.println("Generar reporte de desvío: opcion en desarrollo.");
            case CONSULTAR_TAREAS_ASIGNADAS ->
                System.out.println("Consultar tareas asignadas: opcion en desarrollo.");
            case ASIGNAR_ESTIMACION ->
                System.out.println("Asignar estimacion: opcion en desarrollo.");
            case ASIGNAR_ESTADO_TAREA ->
                System.out.println("Asignar estado a tarea: opcion en desarrollo.");
            case CONSULTAR_PROYECTO ->
                System.out.println("Consultar proyecto: opcion en desarrollo.");
            case ACCION_NO_RECONOCIDA ->
                System.out.println("Accion no reconocida.");
        }
    }

    private static void registrarProyecto() {
        Proyecto proyecto = new Proyecto();

        System.out.print("Nombre del proyecto: ");
        proyecto.setNombre(scanner.nextLine());

        try {
            System.out.print("Fecha de inicio (YYYY-MM-DD): ");
            proyecto.setFechaInicio(LocalDate.parse(scanner.nextLine()));
        } catch (Exception e) {
            System.out.print("Fecha de inicio con formato incorrecto. Se cancela la ejecucion");
            return;
        }

        try {
            System.out.print("Fecha de fin (YYYY-MM-DD): ");
            proyecto.setFechaFin(LocalDate.parse(scanner.nextLine()));
        } catch (Exception e) {
            System.out.print("Fecha de fin con formato incorrecto. Se cancela la ejecucion");
            return;
        }

        System.out.println("Estados disponibles:");
        List<Estado> estados;

        try {
            estados = daoFactory.getEstadoDAO().getEstadosByCodigo("PROYECTO");
        } catch (SQLException e) {
            System.out.print("No hay estados disponibles.");
            return;
        }

        do {
            for (int i = 0; i < estados.size(); i++) {
                Estado estado = estados.get(i);
                System.out.println((i + 1) + " - " + estado.getNombre());
            }

            System.out.print("Seleccione un estado: ");
            int opcionEstado = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (opcionEstado >= 1 && opcionEstado <= estados.size()) {
                proyecto.setEstado(estados.get(opcionEstado - 1));
                break; // Sale del bucle si se selecciona un estado válido
            } else {
                System.out.println("Opción invalida. Intente de nuevo.");
            }
        } while (true);

        try {
            // Guardar proyecto en la base de datos
            daoFactory.getProyectoDAO().insertarProyecto(proyecto);
            System.out.println("Proyecto registrado con exito en la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al registrar el proyecto en la base de datos: " + e.getMessage());
        }

        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    private static void registrarSprint() {
        Sprint sprint = new Sprint();

        System.out.print("Numero del sprint: ");
        sprint.setNumero(Integer.parseInt(scanner.nextLine()));

        try {
            System.out.print("Fecha de inicio (YYYY-MM-DD): ");
            sprint.setFechaInicio(LocalDate.parse(scanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Fecha de inicio con formato incorrecto. Se cancela la ejecución.");
            return;
        }

        System.out.println("Estados disponibles:");

        // Obtener y listar estados de sprint
        List<Estado> estados;
        try {
            estados = daoFactory.getEstadoDAO().getEstadosByCodigo("SPRINT");
        } catch (SQLException e) {
            System.out.print("No hay estados disponibles.");
            return;
        }

        do {
            for (int i = 0; i < estados.size(); i++) {
                Estado estado = estados.get(i);
                System.out.println((i + 1) + " - " + estado.getNombre());
            }

            System.out.print("Seleccione un estado: ");
            int opcionEstado = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (opcionEstado >= 1 && opcionEstado <= estados.size()) {
                sprint.setEstado(estados.get(opcionEstado - 1));
                break; // Sale del bucle si se selecciona un estado válido
            } else {
                System.out.println("Opcion invalida. Intente de nuevo.");
            }
        } while (true);

        try {
            // Guardar sprint en la base de datos
            daoFactory.getSprintDAO().insertarSprint(sprint);
            System.out.println("Sprint registrado con exito en la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al registrar el sprint en la base de datos: " + e.getMessage());
        }

        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    private static void listarProyectos() {

        List<Proyecto> proyectos;

        try {
            proyectos = daoFactory.getProyectoDAO().getProyectos();
        } catch (SQLException e) {
            System.out.print("No hay proyectos disponibles.");
            System.out.print("\nPresione Enter para continuar...");
            scanner.nextLine();
            return;
        }
        System.out.println("Proyectos disponibles:");

        for (int i = 0; i < proyectos.size(); i++) {
            Proyecto proyecto = proyectos.get(i);
            System.out.println((i + 1) + " - " + proyecto.toString());
        }

        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    private static void listarSprints() {
        System.out.println("Sprints:");
        List<Sprint> sprints;

        try {
            sprints = daoFactory.getSprintDAO().getSprints();
        } catch (SQLException e) {
            System.out.print("No hay sprints.");
            System.out.print("\nPresione Enter para continuar...");
            scanner.nextLine();
            return;
        }

        for (int i = 0; i < sprints.size(); i++) {
            Sprint sprint = sprints.get(i);
            System.out.println((i + 1) + " - " + sprint.toString());
        }

        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    private static void asignarSprintAlProyecto() {
        try {
            List<Sprint> sprintsSinProyecto = daoFactory.getSprintProyectoDAO().getSprintsSinProyectoAsignado();

            if (sprintsSinProyecto.isEmpty()) {
                System.out.println("No hay sprints disponibles para asignar a proyectos.");
                System.out.print("\nPresione Enter para continuar...");
                scanner.nextLine();
                return;
            }

            System.out.println("Sprints sin proyecto asignado:");
            for (int i = 0; i < sprintsSinProyecto.size(); i++) {
                System.out.println((i + 1) + ". " + sprintsSinProyecto.get(i));
            }

            int opcionSprint;
            Sprint sprintSeleccionado = null;
            do {
                System.out.print("Seleccione un sprint: ");
                opcionSprint = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                if (opcionSprint >= 1 && opcionSprint <= sprintsSinProyecto.size()) {
                    sprintSeleccionado = sprintsSinProyecto.get(opcionSprint - 1);
                } else {
                    System.out.println("Opcion invalida. Seleccione un sprint valido.");
                }
            } while (sprintSeleccionado == null);

            List<Proyecto> proyectos = daoFactory.getProyectoDAO().getProyectos();
            if (proyectos.isEmpty()) {
                System.out.println("No hay proyectos disponibles para asignar el sprint.");
                System.out.print("\nPresione Enter para continuar...");
                scanner.nextLine();
                return;
            }

            System.out.println("Proyectos disponibles:");
            for (int i = 0; i < proyectos.size(); i++) {
                System.out.println((i + 1) + ". " + proyectos.get(i));
            }

            int opcionProyecto;
            Proyecto proyectoSeleccionado = null;
            do {
                System.out.print("Seleccione un proyecto para asignar el sprint: ");
                opcionProyecto = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                if (opcionProyecto >= 1 && opcionProyecto <= proyectos.size()) {
                    proyectoSeleccionado = proyectos.get(opcionProyecto - 1);
                } else {
                    System.out.println("Opcion invalida. Seleccione un proyecto valido.");
                }
            } while (proyectoSeleccionado == null);

            // Asociar el sprint seleccionado al proyecto en la base de datos
            daoFactory.getSprintProyectoDAO().asignarSprintAProyecto(sprintSeleccionado.getId(), proyectoSeleccionado.getId());

            System.out.println("El sprint ha sido asignado al proyecto exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al asignar el sprint al proyecto: " + e.getMessage());
        }

        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}
