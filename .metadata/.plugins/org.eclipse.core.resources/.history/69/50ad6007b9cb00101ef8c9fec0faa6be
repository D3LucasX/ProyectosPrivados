package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import DAO.ZonaDAO;
import model.Zona;

public class ZonaService {

	private ZonaDAO dao;

	public ZonaService() {
		this.dao = new ZonaDAO();
	}

	// FUNCION QUE AÑADE VARIAS ZONAS COMO SEED DATA A LA MEMORIA
	private ArrayList<Zona> crearZonasIniciales() {
		ArrayList<Zona> lista = new ArrayList<>();

		lista.add(new Zona(1, "Zona Norte", "Área ubicada en la parte norte del recinto"));
		lista.add(new Zona(2, "Zona Sur", "Sector destinado a actividades recreativas"));
		lista.add(new Zona(3, "Zona Central", "Zona principal con acceso general"));

		return lista;
	}

	// FUNCION QUE INICIALIZA LAS ZONAS SEED DATA
	public boolean inicializarSeedData() {
		int filas = 0;
		try {
			if (dao.listarTodos().isEmpty()) {
				ArrayList<Zona> lista = crearZonasIniciales();
				filas = dao.RegistrarVariasZonas(lista);
			}
		} catch (SQLException e) {
			System.err.println("ERROR al registrar las zonas iniciales.");
			e.printStackTrace();
		}
		return filas > 0;
	}

	// FUNCION QUE AÑADE UNA ZONA
	public boolean registrarZona(Zona nuevaZona) {
		int filas = 0;
		try {
			filas = dao.registrarZona(nuevaZona);
		} catch (SQLException e) {
			System.err.println("ERROR al registrar una nueva zona.");
			e.printStackTrace();
			return false;
		}
		return filas > 0;

	}

	public void listarZonas(String habilitada) {
		try {
			if (habilitada.equalsIgnoreCase("habilitadas")) {
				dao.listarZonas(true);
			}else if (habilitada.equalsIgnoreCase("inhabilitada")) {
				dao.listarZonas(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean deshabilitarZonas(String tabla, int idZona) throws SQLException {
		 if(!ServiceUtils.columnasValidas.containsKey(tabla)) {
			 throw new IllegalArgumentException("Tabla no permitida");
		 }
		 return dao.deshabilitarZonas(idZona);
	}
}
