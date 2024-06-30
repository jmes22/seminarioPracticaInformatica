package salgueroortiz.agilscrum.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import salgueroortiz.agilscrum.BE.*;

public class AccionDAO {

    private final Connection connection;

    public AccionDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Accion> getAccionesByIdRol(int id) throws SQLException {
        String query = "SELECT a.* FROM agil_scrum.permisosxrol pxr "
                + "JOIN agil_scrum.permiso p ON p.id = pxr.permiso_id "
                + "JOIN agil_scrum.accionesxpermiso axp ON p.id = axp.permiso_id "
                + "JOIN agil_scrum.accion a ON a.id = axp.accion_id "
                + "WHERE pxr.rol_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        List<Accion> acciones = new ArrayList<>();
        while (resultSet.next()) {
            Accion accion = new Accion();
            accion.setId(resultSet.getInt("id"));
            accion.setNombre(resultSet.getString("nombre"));
            accion.setCodigo(resultSet.getString("codigo"));
            acciones.add(accion);
        }

        return acciones;
    }
}
