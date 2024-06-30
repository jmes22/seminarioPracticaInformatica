package salgueroortiz.agilscrum.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import salgueroortiz.agilscrum.BE.*;

public class SprintProyectoDAO {

    private Connection connection;

    public SprintProyectoDAO(Connection connection) {
        this.connection = connection;
    }

    public void asignarSprintAProyecto(int sprintId, int proyectoId) throws SQLException {
        String query = "INSERT INTO SprintXProyecto (sprint_id, proyecto_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sprintId);
            statement.setInt(2, proyectoId);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("El sprint ha sido asignado al proyecto exitosamente.");
            } else {
                System.out.println("No se pudo asignar el sprint al proyecto.");
            }
        }
    }

    public List<Sprint> getSprintsSinProyectoAsignado() throws SQLException {
        String query = "SELECT s.id, s.numero, s.fechaInicio, s.estado_id, e.nombre AS estado_nombre "
                + "FROM Sprint s "
                + "LEFT JOIN SprintXProyecto sp ON s.id = sp.sprint_id "
                + "LEFT JOIN Estado e ON s.estado_id = e.id "
                + "WHERE sp.proyecto_id IS NULL";

        List<Sprint> sprints = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Sprint sprint = new Sprint();
                sprint.setId(resultSet.getInt("id"));
                sprint.setNumero(resultSet.getInt("numero"));
                sprint.setFechaInicio(resultSet.getDate("fechaInicio").toLocalDate());

                // Crear y establecer el estado del sprint
                Estado estado = new Estado();
                estado.setId(resultSet.getInt("estado_id"));
                estado.setNombre(resultSet.getString("estado_nombre"));
                sprint.setEstado(estado);

                sprints.add(sprint);
            }
        }

        return sprints;
    }
}
