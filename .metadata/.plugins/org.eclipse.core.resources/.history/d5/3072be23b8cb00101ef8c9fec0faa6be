package main;

import java.sql.SQLException;
import java.util.Scanner;

import INPUT.Input;
import model.Zona;
import service.ZonaService;

public class GestionZona {

	// <<<<<<<<<<<<<<< FUNCIONES RELACIONADAS CON LAS ZONAS>>>>>>>>>>>>>>>

	public static void mostrarMenuZonas() {
		System.out.println("********************************");
		System.out.println("*            ZONAS             *");
		System.out.println("********************************");
		System.out.println();
		System.out.println("1. Registrar una nueva zona.");
		System.out.println("2. Listado de zonas activas o inactivas.");
		System.out.println("3. Deshabilitar zona");
		System.out.println("4. Salir.");
	}

	// FUNCION QUE REGISTRA UNA ZONA 
	public Zona crearZona(Scanner entrada) {
		String nombre = "";
		String descripcion = "";
		
		nombre = Input.pedirString(entrada, "Introduce el nombre de la zona: ", 1, 45);
		descripcion = Input.pedirString(entrada, "Añade una pequeña descripcion sobre la zona: ", 1, 150);
		
		Zona nuevaZona = new Zona(nombre, descripcion);
		return nuevaZona;
	}
	
	public void listarZonas (boolean habilitada, Scanner entrada, ZonaService service) {
		boolean valido = false;
		String habilitadaCons = "";
			System.out.println("¿Que zonas quieres listar, las habilitadas o las inhabilitadas?");
			habilitadaCons = Input.pedirString(entrada, "Introduce: 'habilitadas' o 'inhabilitadas' : ", 1, 45);
			service.listarZonas(habilitadaCons);
	}
	
	public static void deshabilitarZona(Scanner entrada, ZonaService service) {
		String tabla = "";
		int idZona = 0;
		
		try {
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
