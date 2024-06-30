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

public class SprintDAO {

    private Connection connection;

    public SprintDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Sprint> getSprints() throws SQLException {
        String query = "SELECT s.id, s.numero, s.fechaInicio, s.fechaFin, e.id AS estado_id, e.nombre AS estado_nombre "
                + "FROM agil_scrum.sprint s "
                + "JOIN agil_scrum.estado e ON s.estado_id = e.id";

        List<Sprint> sprints = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Sprint sprint = new Sprint();

                sprint.setId(resultSet.getInt("id"));
                sprint.setNumero(resultSet.getInt("numero"));

                // Convertir java.sql.Date a LocalDate para fechaInicio
                Date fechaInicioSql = resultSet.getDate("fechaInicio");
                LocalDate fechaInicio = fechaInicioSql.toLocalDate();
                sprint.setFechaInicio(fechaInicio);

                // Convertir java.sql.Date a LocalDate para fechaFin
                Date fechaFinSql = resultSet.getDate("fechaFin");
                if (fechaFinSql != null) {
                    LocalDate fechaFin = fechaFinSql.toLocalDate();
                    sprint.setFechaFin(fechaFin);
                } else {
                    sprint.setFechaFin(null);
                }

                // Crear y establecer el estado del proyecto
                Estado estado = new Estado();
                estado.setId(resultSet.getInt("estado_id"));
                estado.setNombre(resultSet.getString("estado_nombre"));
                sprint.setEstado(estado);

                sprints.add(sprint);
            }
        }

        return sprints;
    }

    public void insertarSprint(Sprint sprint) throws SQLException {
        String query = "INSERT INTO Sprint (numero, fechaInicio, estado_id) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, sprint.getNumero());
        statement.setDate(2, Date.valueOf(sprint.getFechaInicio()));
        statement.setInt(3, sprint.getEstado().getId());

        int rowsInserted = statement.executeUpdate();

        if (rowsInserted > 0) {
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                sprint.setId(id);
            }
        }
    }
}
