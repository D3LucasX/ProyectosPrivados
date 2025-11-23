package INPUT;

import java.util.Scanner;

import model.Empleado.Cargo;

public class Input {
	
	// COMPROBACIONES DE STRING
	public static boolean esTextoVacio(String textoAcomprobar) {
		return textoAcomprobar == null || textoAcomprobar.trim().isEmpty();
	}
	
	public static boolean validarStringLongitud(String textoAcomprobar, int minLen, int maxLen) {
		if(esTextoVacio(textoAcomprobar)) {
			return false;
		}
		int len = textoAcomprobar.trim().length();
		return len >= minLen && len <=  maxLen; // SI TAMAÑO CORRECTO == TRUE
	}
	
	public static boolean validarSoloLetras(String textoAcomprobar) {
		return esTextoVacio(textoAcomprobar) && textoAcomprobar.matches("[a-zA-Záéíóú´´ÁÉÍÓÚ ]+");
	}
	
	public static boolean ComprobarStringRegex(String textoAcomprobar, String regerx) {
		return !esTextoVacio(textoAcomprobar) && textoAcomprobar.trim().matches(regerx);
	}
	
	public static String pedirString (Scanner entrada, String mensaje, int minlen, int maxlen) {
		String textoAcomprobar = "";
		do {
			System.out.println(mensaje);
			textoAcomprobar = entrada.nextLine();
			if (!Input.validarStringLongitud(textoAcomprobar, minlen, maxlen)) {
				if (Input.esTextoVacio(textoAcomprobar)) {
					System.out.println("No puede dejar el campo vacío.");
				}else {
					System.out.println("El campo máximo puede tener " + maxlen + " caracteres.");
				}
			}
		}while(!Input.validarStringLongitud(textoAcomprobar, minlen, maxlen));
		
		return textoAcomprobar;
	}
	//-----------------------------------------------------------------------------------------------//
	
	// COMPROBACION DE NÚMEROS
	
	public static double pedirdouble(Scanner entrada, String mensaje) {
		double numeroAcomprobar = 0;
		boolean valido = false;
		do {
			System.out.println(mensaje);
			
			if (entrada.hasNextDouble()) {
				numeroAcomprobar = entrada.nextDouble();
				entrada.nextLine();
				valido = true;
			}else {
				System.out.println("ERROR, Valor inválido.");
				entrada.nextLine();
			}
		}while(!valido);
		return numeroAcomprobar;
	}
	
	public static int pedirInt(Scanner entrada, String mensaje) {
		int numeroAcomprobar = 0;
		boolean valido = false;
		do {
			System.out.println(mensaje);
			
			if (entrada.hasNextInt()) {
				numeroAcomprobar = entrada.nextInt();
				entrada.nextLine();
				valido = true;
			}else {
				System.out.println("ERROR, Valor inválido.");
				entrada.nextLine();
			}
		}while(!valido);
		return numeroAcomprobar;
	}
	
	//---------------------------------------------------------------//
	// COMOPROBACION DE CARGO DEL EMPLEADO
	public static Cargo queCargo(Scanner entrada, String mensaje) {
		Cargo cargo = null;
		boolean valido = false;
		do {
			System.out.println(mensaje);
			String cargoS = entrada.nextLine();
			if (cargoS.trim().equalsIgnoreCase("jefe")) {
				cargo = Cargo.jefe;
				valido = true;
			}else if (cargoS.trim().equalsIgnoreCase("cajero")) {
				cargo = Cargo.cajero;
				valido = true;
			}else {
				System.out.println("Entrada inválida, introduzca el cargo del empleado: (jefe/cajero)");
			}
		}while(!valido);
		return cargo;
	}
}
