package main;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.StatsDAO;
import INPUT.Input;

public class GestionStatsUI {
	
	// SUB MENÚ ESTADÍSTICAS
	public static void mostrarMenuEstadisticas() {
		System.out.println("********************************");
		System.out.println("*         ESTADÍSTICAS         *");
		System.out.println("********************************");
		System.out.println();
		System.out.println("1. Producto más vendido (5 primeros).");
		System.out.println("2. Empleados que más venden (5 primeros).");
		System.out.println("3. Datos de las ventas del último mes.");
		System.out.println("4. Datos de las ventas del último mes por un empleado.");
		System.out.println("5. Datos de los cambios.");
		System.out.println("6. Salir.");
	}
	
	public void listarVentasEnMes(Scanner entrada, StatsDAO daoEST) {
		boolean valido = false;
		String mesS = "";
		do {
			System.out.println("Introduzca el mes: ");
			mesS = entrada.nextLine();
			valido = Input.ComprobarStringRegex(mesS, "^(1[0-2]|[1-9])$");
			if (!valido) {
				System.err.println("Introduzca un mes válido.");
			}
		}while(!valido);
		int mes = Integer.parseInt(mesS);
		try {
			daoEST.listarVentasEnRealizadasEnUnMes(mes);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void listarVentasEnMesPorEmpleado(Scanner entrada, StatsDAO daoEST) {
		boolean valido = false;
		String mesS = "";
		do {
			System.out.println("Introduzca el mes: ");
			mesS = entrada.nextLine();
			valido = Input.ComprobarStringRegex(mesS, "^(1[0-2]|[1-9])$");
			if (!valido) {
				System.err.println("Introduzca un mes válido.");
			}
		}while(!valido);
		int mes = Integer.parseInt(mesS);
		int idEmpleado = Input.pedirInt(entrada, "Introduzca el Id del empleado:");
		try {
			daoEST.listarVentasEnRealizadasEnUnMesPorEmpleado(mes, idEmpleado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void menuOpcionesEstadisticas(Scanner entrada, StatsDAO daoEST) {
		
		int opcionSecun = 0;

		while (opcionSecun != 6) { // bucle del sub-menú
			mostrarMenuEstadisticas();
			System.out.print("Introduzca una opción: ");
			String opcion = entrada.nextLine();

			if (!Input.ComprobarStringRegex(opcion, "^[1-6]$")) {
				System.err.println("Opción no válida.");
			} else {
				opcionSecun = Integer.parseInt(opcion);

				switch (opcionSecun) {
				case 1:
					System.out.println();
					System.out.println();
					try {
						daoEST.masVendidosCinco();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println();
					System.out.println();
					break;
				case 2:
					System.out.println();
					System.out.println();
					try {
						daoEST.empleadosMasVentas();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println();
					System.out.println();
					break;
				case 3:
					System.out.println();
					System.out.println();
					listarVentasEnMes(entrada, daoEST);
					System.out.println();
					System.out.println();
					break;
				case 4:
					System.out.println();
					System.out.println();
					listarVentasEnMesPorEmpleado(entrada, daoEST);
					System.out.println();
					System.out.println();
					break;
				case 5:
					System.out.println();
					System.out.println();
					try {
						daoEST.listarCambios();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println();
					System.out.println();
					break;
				case 6:
					System.out.println();
					System.out.println("Saliendo al menú principal...");
					System.out.println();
					System.out.println();
					break;
					default:
						System.out.println();
						System.out.println("ERROR");
						System.out.println();
						System.out.println();
						break;
				}
			}
		}
	}
	
}
