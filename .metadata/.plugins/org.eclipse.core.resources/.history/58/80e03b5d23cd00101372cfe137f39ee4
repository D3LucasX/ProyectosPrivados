package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import DAO.EmpleadoDAO;
import model.Empleado;
import model.Empleado.Cargo;

public class EmpleadoService {
	private EmpleadoDAO dao;

	public EmpleadoService() {
		this.dao = new EmpleadoDAO();
	}

	private ArrayList<Empleado> crearEmpleadosIniciales() {
	    ArrayList<Empleado> lista = new ArrayList<>();
	    
	    lista.add(new Empleado("Ana", Cargo.jefe, new Date(System.currentTimeMillis())));
	    lista.add(new Empleado("Luis", Cargo.cajero, new Date(System.currentTimeMillis())));
	    lista.add(new Empleado("Marta", Cargo.cajero, new Date(System.currentTimeMillis())));
	    lista.add(new Empleado("Carlos", Cargo.jefe, new Date(System.currentTimeMillis())));
	    lista.add(new Empleado("Sofía", Cargo.cajero, new Date(System.currentTimeMillis())));
	    lista.add(new Empleado("Pedro", Cargo.cajero, new Date(System.currentTimeMillis())));
	    lista.add(new Empleado("Lucía", Cargo.jefe, new Date(System.currentTimeMillis())));
	    lista.add(new Empleado("Jorge", Cargo.cajero, new Date(System.currentTimeMillis())));
	    lista.add(new Empleado("María", Cargo.cajero, new Date(System.currentTimeMillis())));
	    lista.add(new Empleado("Andrés", Cargo.jefe, new Date(System.currentTimeMillis())));
	    
	    return lista;
	}
	// SEED DATA PARA SI LA BBDD ESTÁ VACÍA
	public void inicializarSeedDataEmp() throws SQLException{
		if (dao.listarTodos().isEmpty()) {
			ArrayList<Empleado> iniciales = crearEmpleadosIniciales();
			int filas = dao.insertarLista(iniciales);
			System.out.println("Se insertaron " + filas + " empleados de ejemplo.");
		}
	}

	
	// FUNCION PARA INSERTAR UN NUEVO EMPLEADO
	public boolean agragarEmpleado(Empleado em) {
		int filas = 0;
		try {
			filas = dao.insertar(em);
		} catch (SQLException e) {
			System.err.println("Error al agregar juguete: " + e.getMessage());
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas > 0;
	}
	
	// LISTAR TODOS LOS EMPLEADOS DEVUELVE UNA LISTA PARA PODER GUARDARLA Y PODER TENERLOS 
	// EN MEMORIA
	public ArrayList<Empleado> obtenerTodos(){
		try {
			return dao.listarTodos();
		}catch (SQLException e) {
			System.err.println("Error, no se ha podido acceder a los empleados: " + e.getMessage());
	        e.printStackTrace();
	        return new ArrayList<>();
		}
	}
	
	

}
