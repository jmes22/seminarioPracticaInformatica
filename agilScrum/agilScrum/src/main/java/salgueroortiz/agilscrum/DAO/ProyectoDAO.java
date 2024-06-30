package salgueroortiz.agilscrum.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import salgueroortiz.agilscrum.BE.*;

public class ProyectoDAO {

    private Connection connection;

    public ProyectoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Proyecto> getProyectos() throws SQLException {
        String query = "SELECT p.id, p.nombre, p.fechaInicio, p.fechaFin, e.id AS estado_id, e.nombre AS estado_nombre, "
                + "(SELECT COUNT(*) FROM agil_scrum.sprintxproyecto WHERE proyecto_id = p.id) AS cantidad_sprints "
                + "FROM agil_scrum.proyecto p "
                + "JOIN agil_scrum.estado e ON p.estado_id = e.id";

        List<Proyecto> proyectos = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Proyecto proyecto = new Proyecto();

                proyecto.setId(resultSet.getInt("id"));
                proyecto.setNombre(resultSet.getString("nombre"));

                // Convertir java.sql.Date a LocalDate para fechaInicio
                Date fechaInicioSql = resultSet.getDate("fechaInicio");
                LocalDate fechaInicio = fechaInicioSql.toLocalDate();
                proyecto.setFechaInicio(fechaInicio);

                // Convertir java.sql.Date a LocalDate para fechaFin
                Date fechaFinSql = resultSet.getDate("fechaFin");
                LocalDate fechaFin = fechaFinSql.toLocalDate();
                proyecto.setFechaFin(fechaFin);

                // Crear y establecer el estado del proyecto
                Estado estado = new Estado();
                estado.setId(resultSet.getInt("estado_id"));
                estado.setNombre(resultSet.getString("estado_nombre"));
                proyecto.setEstado(estado);

                proyecto.setCantidadSprint(resultSet.getInt("cantidad_sprints"));

                proyectos.add(proyecto);
            }
        }

        return proyectos;
    }

    public void insertarProyecto(Proyecto proyecto) throws SQLException {
        String query = "INSERT INTO Proyecto (nombre, fechaInicio, fechaFin, estado_id) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, proyecto.getNombre());
        statement.setDate(2, Date.valueOf(proyecto.getFechaInicio()));
        statement.setDate(3, Date.valueOf(proyecto.getFechaFin()));
        statement.setInt(4, proyecto.getEstado().getId());

        int rowsInserted = statement.executeUpdate();

        if (rowsInserted > 0) {
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                proyecto.setId(id);
            }
        }
    }

}
