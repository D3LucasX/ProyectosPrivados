package main;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.DAOUtils;
import INPUT.Input;
import service.ServiceUtils;

public class GestionUtils {

	public static void mostrarMenuPrincipal() {
		System.out.println("********************************");
		System.out.println("*      JUGUETERÍA DOSA         *");
		System.out.println("********************************");
		System.out.println();
		System.out.println("1. Juguetes.");
		System.out.println("2. Empleados.");
		System.out.println("3. Ventas.");
		System.out.println("4. Obtener datos de la tienda.");
		System.out.println("5 Salir.");
	}

	// FUNCION QUE MODIFICA UN CAMPO DE UNA TABLA EN UNA FILA
	public static void modificarCampo(Scanner entrada) {
		try {
			ServiceUtils service = new ServiceUtils();
			String tabla = "";
			String columnaAmodificar = "";
			Object valor;
			String columnaID;
			int idNumero = 0;
			String reTry = "";
			boolean exito = false;
			do {
				tabla = Input.pedirString(entrada, "Introduzca la tabla que va a modificar", 1, 45);
				columnaAmodificar = Input.pedirString(entrada, "Introduzca la columna que va a modificar", 1, 45);
				valor = Input.pedirString(entrada, "Introduzca el valor modificado", 1, 45);
				columnaID = Input.pedirString(entrada, "Introduzca el nombre del campo id de la tabla " + tabla + ": ",
						1, 45);
				idNumero = Input.pedirInt(entrada, "Introduzca el numero del ID:");

				exito = service.modificarCampo(tabla, columnaAmodificar, valor, columnaID, idNumero);
				if (exito) {
					System.out.printf("Columna %s de la tabla %s modificada con éxito.\n", columnaAmodificar, tabla);
					System.out.println();
				} else {
					System.out.println("No se pudo modificar la columna. Verifique los datos.");
					System.out.println("¿Desea volver a intentarlo? si/no");
					reTry = entrada.nextLine();
					if (reTry.equalsIgnoreCase("si")) {
						exito = false;
						System.out.println("Volvera a introducir los datos.");
					} else if (reTry.equalsIgnoreCase("no")) {
						exito = true;
						System.out.println("Genial, volvera a l menú prioncipal.");
					} else {

						System.out.println("Introduzca si o no.");
					}
				}
			} while (!exito);
		} catch (SQLException e) {
			System.out.println("Error al modificar el registro: " + e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// FUNCION QUE ELIMINA O DA DE ALTA LOGICAMENTE UNA FILA DE UNA TABLA SEGÚN EL ID
	public static void eliminarFila(Scanner entrada, ServiceUtils service) {
		String tabla = "";
		String reTry = "";
		String altaBaja = "";
		int idNumero = 0;
		int activo0 = 0, activo1 = 1;
		boolean exito = false, valido = false;
		try {
			do {
				tabla = Input.pedirString(entrada, "Introduzca la tabla que va a modificar", 1, 45);
				idNumero = Input.pedirInt(entrada, "Introduzca el numero del ID de la fila que quieras modificar:");
				do {
					System.out.println("¿Que desea, dar de alta, o dar de baja? (alta/baja)");
					altaBaja = entrada.nextLine();
					if (altaBaja.equalsIgnoreCase("alta")) {
						exito = service.eliminarFila(tabla, activo1, idNumero);
						DAOUtils.modificarFechaBaja_alta(tabla, idNumero);
						valido = true;
					}else if (altaBaja.equalsIgnoreCase("baja")){
						exito = service.eliminarFila(tabla, activo0, idNumero);
						DAOUtils.modificarFechaBaja(tabla, idNumero);
						valido = true;
					}else {
						System.out.println("Escriba 'alta' para dar de alta o 'baja' para dar de baja.");
					}
				}while(!valido);
	
				if (exito && altaBaja.equalsIgnoreCase("baja")) {
					System.out.printf("Fila de la tabla %s, ha sido dada de baja con éxito.\n", tabla);
					System.out.println();
				}else if(exito && altaBaja.equalsIgnoreCase("alta")){ 
					System.out.printf("Fila de la tabla %s, ha sido dada de alta con éxito.\n", tabla);
					System.out.println();
				}else {
					System.out.println("No se pudo modificar la fila. Verifique los datos.");
					System.out.println("¿Desea volver a intentarlo? si/no");
					reTry = entrada.nextLine();
					if (reTry.equalsIgnoreCase("si")) {
						exito = false;
						System.out.println("Volvera a introducir los datos.");
					} else if (reTry.equalsIgnoreCase("no")) {
						exito = true;
						System.out.println("Genial, volvera a l menú principal.");
					} else {

						System.out.println("Introduzca si o no.");
					}
				}
			} while (!exito);
		} catch (SQLException e) {
			System.err.println("Error al modificar el borrado logico de la fila: " + e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.err.println("ERROR: " + e.getMessage());
		}
	}

}
