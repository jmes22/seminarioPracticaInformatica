package salgueroortiz.agilscrum.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import salgueroortiz.agilscrum.BE.*;

public class UsuarioDAO {
    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    
     public List<Usuario> getUsuarios() throws SQLException {
        String query = "SELECT * FROM Usuario";
        List<Usuario> usuarios = new ArrayList<>();
       
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Usuario usuario = new Usuario();
            
            usuario.setId(resultSet.getInt("id"));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setApellido(resultSet.getString("apellido"));
            usuario.setMail(resultSet.getString("mail"));
            usuario.setUsuario(resultSet.getString("usuario"));
            usuario.setContrasena(resultSet.getString("contraseña"));
            usuario.setIdRol(resultSet.getInt("rol_id"));
            
            usuarios.add(usuario);
        }
        
        return usuarios;
    }
}