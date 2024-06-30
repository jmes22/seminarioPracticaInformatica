package salgueroortiz.agilscrum.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import salgueroortiz.agilscrum.BE.Estado;

public class EstadoDAO {
    private final Connection connection;

    public EstadoDAO(Connection connection) {
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
    
    public List<Estado> getEstadosByCodigo(String codigo) throws SQLException {
        String query = "SELECT * FROM Estado WHERE codigo = ?";
        List<Estado> estados = new ArrayList<>();
       
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, codigo);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Estado estado = new Estado();
            estado.setId(resultSet.getInt("id"));
            estado.setNombre(resultSet.getString("nombre"));
            estado.setCodigo(resultSet.getString("codigo"));
            estados.add(estado);
        }
        
        return estados;
    }

    // Otros métodos de acceso a datos (insertar, actualizar, eliminar)
}