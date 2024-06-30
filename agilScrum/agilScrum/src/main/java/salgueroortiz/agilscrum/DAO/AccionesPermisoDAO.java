package salgueroortiz.agilscrum.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import salgueroortiz.agilscrum.BE.Estado;

public class AccionesPermisoDAO {
    private Connection connection;

    public AccionesPermisoDAO(Connection connection) {
        this.connection = connection;
    }

    public Estado getEstadoById(int id) throws SQLException {
        String query = "SELECT * FROM Estado WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Estado estado = new Estado();
            estado.setId(resultSet.getInt("id"));
            estado.setNombre(resultSet.getString("nombre"));
            return estado;
        }

        return null;
    }

    // Otros métodos de acceso a datos (insertar, actualizar, eliminar)
}