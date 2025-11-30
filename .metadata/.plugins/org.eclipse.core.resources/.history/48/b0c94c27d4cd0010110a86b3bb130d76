package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DAO.JugueteDAO;
import DataBase.DataBaseConnection;
import INPUT.Input;
import model.Juguete;
import model.Stand;
import model.Stock;
import model.Zona;
import service.JugueteService;
import service.ServiceUtils;
import service.StandService;
import service.StockService;
import service.ZonaService;

public class GestionJuguetesUI {

	//  <<<<<<<<<<< FUNCIONES RELACIONADAS CON LOS JUGUETES >>>>>>>>>>>
	
	public static void mostrarMenuJuguetes() {
		System.out.println("********************************");
		System.out.println("*           JUGUETES           *");
		System.out.println("********************************");
		System.out.println();
		System.out.println("1. Registrar nuevo Juguete.");
		System.out.println("2. Modificar juguete.");
		System.out.println("3. Listar todos los juguetes.");
		System.out.println("4. Salir.");
	}
	
	// CREAR JUGUETE
	public Juguete crearJuguete(Scanner entrada) {
		String nombre = "", descripcion = "";
		double precio = 0.0;
		int stock = 0;
		Stand stand = null;
		Zona idzona = null;

		nombre = Input.pedirString(entrada, "Introduzca el nombre del Juguete: ", 1, 45);

		descripcion = Input.pedirString(entrada, "Introduzca una descripción para el Juguete: ", 1, 150);

		precio = Input.pedirdouble(entrada, "Introduzca el precio del producto");

		stock = Input.pedirInt(entrada, "Introduzca el Stock disponible: ");
		

		Juguete nuevoJuguete = new Juguete(nombre, descripcion, precio, stock, 1, null);
		return nuevoJuguete;
	}
	
	public Stock crearStock(Scanner entrada, StandService serviceS, ZonaService serviceZ, Juguete jugueteNuevo ) {
		Stand idstand = null;
		Zona idzona = null;
		idstand = GestionUtils.validarStand(entrada, serviceS);
		idzona = GestionUtils.validarZona(entrada, serviceZ);
		Stock nuevoStock = new Stock (idstand, idzona, jugueteNuevo, jugueteNuevo.getStock());
		
		return nuevoStock;
	}
	
	// FUNCION QUE IMPRIME POR PANTALLA LA LISTA ACTUAL DE JUGUETES.
	public void listarTodosJuguetes(JugueteDAO dao) throws SQLException {
		dao.listarJuguetesActivos();
	}
	
	// SUB-MENU JUGUETES
	public void menuOpcionesJuguetes(Scanner entrada, ServiceUtils service, JugueteService serviceJug, StockService serviceStock, ZonaService serviceZ, StandService serviceStand, JugueteDAO dao) {
		int opcionSecun = 0;

		while (opcionSecun != 4) { // bucle del sub-menú
			mostrarMenuJuguetes();
			System.out.print("Introduzca una opción: ");
			String opcion = entrada.nextLine();

			if (!Input.ComprobarStringRegex(opcion, "^[1-4]$")) {
				System.err.println("Opción no válida.");
			} else {
				opcionSecun = Integer.parseInt(opcion);

				switch (opcionSecun) {
				case 1: // AÑADIR UN JUGUETE A LA BASE DE DATOS
					System.out.println();
					Juguete jugueteAdd = crearJuguete(entrada);
					Stock stockAsociado = crearStock(entrada, serviceStand, serviceZ, jugueteAdd);
					try (Connection conexion = DataBaseConnection.getConnection()) {
					    conexion.setAutoCommit(false); // inicio de la transacción

					    boolean jugueteInsertado = serviceJug.agregarJuguete(jugueteAdd, conexion);
					    boolean stockInsertado = serviceStock.insertarStock(stockAsociado, conexion);

					    if (jugueteInsertado && stockInsertado) {
					        conexion.commit(); // confirma cambios
					        System.out.println("Juguete y stock añadidos correctamente.");
					    } else {
					        conexion.rollback(); // deshace todo si algo falla
					        System.err.println("Error: no se pudo añadir el juguete y/o el stock.");
					    }

					} catch (SQLException e) {
					    e.printStackTrace();
					}
					break;
				case 2: // MODIFICAR UN CAMPO DE UNA TABLA DE LA BBDD
					System.out.println();
					GestionUtils.modificarCampo(entrada);
					break;
				case 3: // LISTAR TODOS LOS JUGUETES DE LA BBDD
					System.out.println();
					System.out.println("//-------------//");
					try {
						listarTodosJuguetes(dao);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("//-------------//");
					System.out.println();
					break;
				case 4: // SALIR DEL SUB-MENÚ
					System.out.println();
					System.out.println("Saliendo al menú principal...");
					System.out.println();
					System.out.println();
					break;
				default: // EN CASO DE ERROR.
					System.out.println();
					System.out.println("ERROR");
					System.out.println();
					System.out.println();
				}
			}
		}
	}
}
