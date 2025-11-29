package main;

import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import DAO.EmpleadoDAO;
import INPUT.Input;
import model.Empleado;
import model.Empleado.Cargo;
import service.EmpleadoService;
import service.ServiceUtils;

public class GestionEmpleadosUI {
	//   <<<<<<<<<<<<<<< FUNCIONES RELACIONADAS CON LOS EMPELADOS>>>>>>>>>>>>>>>>>>>>
	
	public static void mostrarMenuEmpleados() {
		System.out.println("********************************");
		System.out.println("*           EMPLEADOS           *");
		System.out.println("********************************");
		System.out.println();
		System.out.println("1. Registrar nuevo Empleado.");
		System.out.println("2. Modificar Empleado.");
		System.out.println("3. Listar todos los Empleados.");
		System.out.println("4. Salir.");
	}
	
	// AGREGAR EMPLEADO
	public Empleado crearEmpleado (Scanner entrada) {
		String nombre = "";
		
		nombre = Input.pedirString(entrada, "Inntroduzca el nombre del empleado", 1, 45);
		Cargo cargo = Input.queCargo(entrada, "Introduzca el cargo que ocupara el Emleado: ");
		
		Empleado nuevoEmpleado = new Empleado(nombre, cargo, new Date(System.currentTimeMillis()));
		return nuevoEmpleado;
	}
	
	// FUNCION QUE IMPRIME POR PANTALLA LA LISTA ACTUAL DE EMPLEADOS
	public void listarTodosEmpleados(EmpleadoDAO daoE) throws SQLException {
		daoE.listarEmpleadosActivos();
	}
	
	//SUB-MENÚ EMPLEADOS
	public void menuOpcionesEmpleados(Scanner entrada, ServiceUtils serviceUT, EmpleadoService service, EmpleadoDAO daoE) {
		int opcionSecun = 0;
		
		while(opcionSecun != 4) {
			mostrarMenuEmpleados();
			System.out.println("Introduzca una opcion: ");
			String opcion = entrada.nextLine();
			
			if (!Input.ComprobarStringRegex(opcion, "^[1-4]$")) {
				System.out.println("Opcion no válida.");
			}else {
				opcionSecun = Integer.parseInt(opcion);
				
				switch(opcionSecun) {
				case 1:
					System.out.println();
					Empleado empleadoAdd = crearEmpleado(entrada);
					if (service.agragarEmpleado(empleadoAdd)) {
						System.out.println("Juguete " + empleadoAdd.getNombre() + " añadido correctamente.");
					}
					break;
				case 2:
					System.out.println();
					GestionUtils.modificarCampo(entrada);
					break;
				case 3:
					System.out.println();
					System.out.println("//-------------//");
					try {
						listarTodosEmpleados(daoE);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("//-------------//");
					System.out.println();
					break;
				case 4:
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
