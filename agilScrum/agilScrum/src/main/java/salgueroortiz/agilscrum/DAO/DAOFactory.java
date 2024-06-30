package salgueroortiz.agilscrum.DAO;

import java.sql.Connection;

public abstract class DAOFactory {

    // List of DAO creation methods
    public abstract EstadoDAO getEstadoDAO();

    public abstract UsuarioDAO getUsuarioDAO();

    public abstract AccionDAO getAccionDAO();

    public abstract ProyectoDAO getProyectoDAO();

    public abstract SprintDAO getSprintDAO();
    
     public abstract SprintProyectoDAO getSprintProyectoDAO();

    // Add more DAO creation methods here
    public static DAOFactory getDAOFactory(FactoryType factoryType, Connection connection) {
        switch (factoryType) {
            case MYSQL -> {
                return new MySQLDAOFactory(connection);
            }
            default ->
                throw new IllegalArgumentException("Unsupported factory type");
        }
        // Add cases for other databases here
    }

    public enum FactoryType {
        MYSQL,
        // Add other database types here
    }
}
