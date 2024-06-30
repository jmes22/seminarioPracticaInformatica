package salgueroortiz.agilscrum.DAO;

import java.sql.Connection;

public class MySQLDAOFactory extends DAOFactory {

    private Connection connection;

    public MySQLDAOFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public EstadoDAO getEstadoDAO() {
        return new EstadoDAO(connection);
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new UsuarioDAO(connection);
    }

    @Override
    public AccionDAO getAccionDAO() {
        return new AccionDAO(connection);
    }

    @Override
    public ProyectoDAO getProyectoDAO() {
        return new ProyectoDAO(connection);
    }

    @Override
    public SprintDAO getSprintDAO() {
        return new SprintDAO(connection);
    }

    @Override
    public SprintProyectoDAO getSprintProyectoDAO() {
        return new SprintProyectoDAO(connection);
    }

}
