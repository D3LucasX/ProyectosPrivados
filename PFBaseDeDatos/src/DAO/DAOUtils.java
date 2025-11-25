package DAO;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataBase.DataBaseConnection;

public class DAOUtils {

	// Funcion para modificar un juguete en la base de datos
	public boolean modificarCampo(String tabla, String columnaAmodificar, Object valor, String columnaId, int idJuguete)
			throws SQLException {
		String sql = "UPDATE " + tabla + " SET " + columnaAmodificar + " = ? WHERE " + columnaId + " = ?";

		try (Connection conexion = DataBaseConnection.getConnection()) {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setObject(1, valor);
			ps.setInt(2, idJuguete);
			return ps.executeUpdate() > 0;
		}
	}

	// Funcion para obtener el nombre de la columna de la clave primaria para evitar
	// tener que
	// introducir el nombre como string en la consulta para evitar posibles
	// inyecciones SQL.
	public static String obtenerColumnaID(String tabla) throws SQLException {
		try (Connection conexion = DataBaseConnection.getConnection()) {
			DatabaseMetaData meta = conexion.getMetaData();
			ResultSet rs = meta.getPrimaryKeys(null, null, tabla);

			if (rs.next()) {
				return rs.getString("COLUMN_NAME");
			} else {
				throw new SQLException("No se encontró columna ID en la tabla " + tabla);
			}
		}
	}
	
	// FUNCION QUE AL DAR DE ALTA LA FECHA LA PONGA A NULL
	public static void modificarFechaBaja_alta(String tabla, int id) throws SQLException{
		String columnaID = obtenerColumnaID(tabla);
		String sql = "UPDATE juguete SET fecha_baja = NULL WHERE id = ?";
		try (Connection conexion = DataBaseConnection.getConnection();
				PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, id);
		}
	}

	// FUNCION QUE AL DAR DE BAJA LOGICAMENTE UNA FILA, MODIFICA EL CAMPO FECHA_BAJA
	public static void modificarFechaBaja(String tabla, int id) throws SQLException {
		String columnaID = obtenerColumnaID(tabla);
		String sql = "UPDATE " + tabla + " SET fecha_baja = now() WHERE " + columnaID + " = ?";

		try (Connection conexion = DataBaseConnection.getConnection();
				PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, id);
		}
	}

	// Funcion para dar de baja a una fila
	public boolean eliminarFila(String tabla, int activo, int idJuguete) throws SQLException {
		String columnaID = obtenerColumnaID(tabla);
		String sql = "UPDATE " + tabla + " SET activo = ? WHERE " + columnaID + " = ?";

		try (Connection conexion = DataBaseConnection.getConnection();
				PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, activo);
			ps.setInt(2, idJuguete);
			return ps.executeUpdate() > 0;
		}
	}
}
