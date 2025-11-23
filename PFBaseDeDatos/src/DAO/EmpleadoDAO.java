package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;

import DataBase.DataBaseConnection;
import model.Empleado;

/**
 * @author d3luc
 */

/**
 * Clase para implementar las operaciones CRUD
 */
public class EmpleadoDAO {

	// LISTA DE TODOS LOS EMPLEADOS
	public ArrayList<Empleado> listarTodos() throws SQLException {
		ArrayList<Empleado> lista = new ArrayList<>();
		String sql = "SELECT * FROM empleado";

		try (Connection conexion = DataBaseConnection.getConnection();
				Statement st = conexion.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				// Convertimos el String en un Enum porque el atributo es de tipo Enum
				Empleado.Cargo cargo = Empleado.Cargo.valueOf(rs.getString("Cargo"));

				lista.add(new Empleado(rs.getInt("idEMPLEADO"), rs.getString("Nombre"), cargo,
						rs.getDate("Fecha_ingreso")));
			}
		}
		return lista;
	}

	// INSERTAR LISTA DE SEED DATA
	public int insertarLista(ArrayList<Empleado> seedDataEmpleados) throws SQLException {
		String sql = "INSERT INTO empleado(Nombre, cargo, fecha_Ingreso) VALUES (?,?,?)";
		int totalFilas = 0;
		try (Connection conexion = DataBaseConnection.getConnection();
				PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			for (Empleado em : seedDataEmpleados) {
				ps.setString(1, em.getNombre());
				ps.setString(2, em.getCargo().name());
				ps.setDate(3, (Date) em.getFechaIngreso());

				ps.addBatch();
			}
			int[] resultados = ps.executeBatch();
			for (int r : resultados) {
				totalFilas += r;
			}

			// Obtener los IDs generados y los setea en los juguetes de la lista para tener
			// sus ids en memoria
			try (ResultSet rs = ps.getGeneratedKeys()) {
				int index = 0;
				while (rs.next() && index < seedDataEmpleados.size()) {
					seedDataEmpleados.get(index).setIdEmpleado(rs.getInt(1));
					index++;
				}
			}
		}
		return totalFilas;
	}

	// INSERTAR NUEVO EMPLEADO
	public int insertar(Empleado nuevoEmpleado) throws SQLException, SQLTimeoutException {
		String sql = "INSERT INTO empleado(Nombre, cargo, fecha_Ingreso) VALUES (?,?,?)";

		try (Connection conexion = DataBaseConnection.getConnection();
				PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, nuevoEmpleado.getNombre());
			ps.setString(2, nuevoEmpleado.getCargo().name());
			ps.setDate(3, (Date) nuevoEmpleado.getFechaIngreso());

			int filas = ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					int idGenerado = rs.getInt(1);
					nuevoEmpleado.setIdEmpleado(idGenerado);
				}
			}
			return filas;
		}
	}

}
