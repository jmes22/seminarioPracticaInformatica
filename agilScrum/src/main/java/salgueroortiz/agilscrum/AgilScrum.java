package salgueroortiz.agilscrum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgilScrum {
    private static final List<Estado> estados = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Proyecto> proyectos = new ArrayList<>();
    private static final List<Sprint> sprints = new ArrayList<>();
    
    public static void main(String[] args) {
        crearObjetosAuxiliares();
        
        int opcion;
        
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                // Lógica para registrar un proyecto
                case 1 -> registrarProyecto();
                
                 // Lógica para listar los proyectos
                case 2 -> listarProyectos();
                
                 // Lógica para registrar un sprint
                case 3 -> registrarSprint();
                
                // Lógica para listar los sprints
                case 4 -> listarSprints();
                
                // Lógica para asignar sprints al proyecto
                case 5 -> asignarSprintAlProyecto();
                 
                  // Lógica para registrar tareas
                case 6 -> System.out.println("Registrar tareas: opcion en desarrollo.");
                
                // Lógica para asignar tareas
                case 7 -> System.out.println("Asignar tareas: opcion en desarrollo.");
                
                 // Lógica para generar un reporte de desvío
                case 8 -> System.out.println("Generar reporte de desvio: opcion en desarrollo.");
              
                case 9 -> System.out.println("Ejecucion de programa finalizada");
                default -> System.out.println("Opción no valida");
            }
        } while (opcion != 9);
    }

    private static void mostrarMenu() {
        System.out.println("=== Menu de Opciones ===");
        System.out.println("1. Registrar proyecto");
        System.out.println("2. Listar proyectos");
        System.out.println("3. Registrar Sprint");
        System.out.println("4. Listar Sprints");
        System.out.println("5. Asignar sprint a proyecto");
        System.out.println("6. Registrar tareas");
        System.out.println("7. Asignar tareas");
        System.out.println("8. Generar reporte de desvio");
        System.out.println("9. Salir");
        System.out.print("Seleccione una opcion: ");
    }
    
    private static void crearObjetosAuxiliares(){
        estados.add(new Estado("Pendiente", "TAREA"));
        estados.add(new Estado("En Progreso", "TAREA"));
        estados.add(new Estado("Completada", "TAREA"));
        estados.add(new Estado("Bloqueada", "TAREA"));
        
        estados.add(new Estado("Planificado", "SPRINT"));
        estados.add(new Estado("En Progreso", "SPRINT"));
        estados.add(new Estado("Completado", "SPRINT"));
        
        estados.add(new Estado("Iniciado", "PROYECTO"));
        estados.add(new Estado("En Progreso", "PROYECTO"));
        estados.add(new Estado("Completado", "PROYECTO"));
        estados.add(new Estado("Cancelado", "PROYECTO"));
    }
    
    private static void registrarProyecto(){
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
        
        listarEstados("PROYECTO");
        
        System.out.print("Seleccione un estado: ");
        int opcionEstado = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        if (opcionEstado >= 1 && opcionEstado <= estados.size()) {
            proyecto.setEstado(estados.get(opcionEstado - 1));
        } else {
            System.out.println("Opción inválida. Se establecerá un estado por defecto.");
            proyecto.setEstado(getEstadoPorDefecto("PROYECTO"));
        }

        proyectos.add(proyecto);
        System.out.println("Proyecto registrado con exito!");
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void registrarSprint(){
        Sprint sprint = new Sprint();
        System.out.print("Numero del proyecto: ");
        sprint.setNumero(Integer.parseInt(scanner.nextLine()));
        
        System.out.print("Fecha de inicio (YYYY-MM-DD): ");
        sprint.setFechaInicio(LocalDate.parse(scanner.nextLine()));

        System.out.println("Estados disponibles:");
        
        listarEstados("SPRINT");
        
        System.out.print("Seleccione un estado: ");
        int opcionEstado = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        if (opcionEstado >= 1 && opcionEstado <= estados.size()) {
            sprint.setEstado(estados.get(opcionEstado - 1));
        } else {
            System.out.println("Opción inválida. Se establecerá un estado por defecto.");
            sprint.setEstado(getEstadoPorDefecto("SPRINT"));
        }
        
        sprints.add(sprint);
        
        System.out.println("Sprint registrado con exito!");
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void listarProyectos(){
        System.out.println("\nProyectos:");
        for (Proyecto proyecto : proyectos) {
            System.out.println(proyecto.toString());
        }
        
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void listarSprints(){
        System.out.println("\nSprints:");
        for (Sprint sprint : sprints) {
            System.out.println(sprint.toString());
        }
        
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void listarEstados(String codigo) {
        for (int i = 0; i < estados.size(); i++) {
            if (estados.get(i).getCodigo().equals(codigo)) {
                System.out.println((i + 1) + ". " + estados.get(i).getNombre());
            }
        }
    }
    
    private static Estado getEstadoPorDefecto(String codigo) {
        for (Estado estado : estados) {
            if (estado.getCodigo().equals(codigo) && estado.getNombre().equals("Iniciado")) {
                return estado;
            }
        }
        return null; // Si no se encuentra el estado por defecto
    }
    
    private static void asignarSprintAlProyecto()
    {
        // Listar sprints sin proyecto asignado
       List<Sprint> sprintsSinProyecto = new ArrayList<>();
       for (Sprint sprint : sprints) {
           boolean tieneProyecto = false;
           for (Proyecto proyecto : proyectos) {
               if (proyecto.getSprints().contains(sprint)) {
                   tieneProyecto = true;
                   break;
               }
           }
           
           if (!tieneProyecto) {
               sprintsSinProyecto.add(sprint);
           }
       }

        // Mostrar los sprints sin proyecto asignado
        System.out.println("Sprints sin proyecto asignado:");
        for (int i = 0; i < sprintsSinProyecto.size(); i++) {
            System.out.println((i + 1) + ". " + sprintsSinProyecto.get(i));
        }

        //Pedir al usuario que elija un sprint
        System.out.print("Seleccione un sprint: ");
        int opcionSprint = scanner.nextInt();
        Sprint sprintSeleccionado = sprintsSinProyecto.get(opcionSprint - 1);

        // Mostrar los proyectos disponibles
        System.out.println("Proyectos disponibles:");
        for (int i = 0; i < proyectos.size(); i++) {
            System.out.println((i + 1) + ". " + proyectos.get(i));
        }  
        
        System.out.print("Seleccione un proyecto para asignar el sprint: ");
        int opcionProyecto = scanner.nextInt();
        Proyecto proyectoSeleccionado = proyectos.get(opcionProyecto - 1);

        // Asociar el sprint seleccionado al proyecto
        proyectoSeleccionado.getSprints().add(sprintSeleccionado);

        System.out.println("El sprint ha sido asignado al proyecto exitosamente.");
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}