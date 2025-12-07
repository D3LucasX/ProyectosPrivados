package fileWritter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;       // Para la fecha y hora actual
import java.time.format.DateTimeFormatter; // Para formatear la fecha en un String legible
import java.util.ArrayList;

import model.User;

public class FileWritter {
	public void reescribirUsu(ArrayList<User> listaUsu) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("ArchivoUsuario.txt"))) {

			for (User u : listaUsu) {

				String linea = u.getIdUser() + ";;" + u.getNickName() + ";;" + u.getPass() + ";;" + u.getVecesLoaded()
						+ ";;" + u.getMail();

				bw.write(linea);
				bw.newLine();
			}

		} catch (IOException e) {
			System.err.println("Error al reescribir al usuario al aumentar las veces logueado.");
			e.printStackTrace();
		}
	}

	public void setConfiguracion(User userLogged, ArrayList<User> listaUsu, ArrayList<String> selecciones) {
		if (userLogged == null)
			return;

		String seleccionesS = String.join("*", selecciones);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("ArchivoUsuario.txt"))) {

			for (User u : listaUsu) {

				String linea;
				String seleccionesFinal;

				if (u.getIdUser() == userLogged.getIdUser()) {
			        // Actualiza solo el usuario logueado
			        seleccionesFinal = (seleccionesS == null || seleccionesS.isEmpty()) ? "0" : seleccionesS;
			    } else {
			        // Mantiene la configuración existente de los demás usuarios
			        seleccionesFinal = (u.getSelecciones() == null || u.getSelecciones().isEmpty()) ? "0" : u.getSelecciones();
			    }
				 linea = u.getIdUser() + ";;" + u.getNickName() + ";;" + u.getPass() + ";;" +
		                    u.getVecesLoaded() + ";;" + u.getMail() + ";;" + seleccionesFinal;

		            bw.write(linea);
		            bw.newLine();
			}

		} catch (IOException e) {
			System.err.println("Error al reescribir al usuario al setear la configuración.");
			e.printStackTrace();
		}
	}
	
	public void guardarNoticias(StringBuilder noticiasAguardar, User usuarioLogueado) {
		LocalDate date = LocalDate.now();
		String fecha = "Fecha de registro: " + date + "\n";
		String nombreArchivo = "Historial_Usuario_ID_" + usuarioLogueado.getIdUser();
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo,true))){
			// true es para que no sobreescriba y escriba a partir de la ultima linea
			bw.write(fecha);
			bw.write(noticiasAguardar.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
