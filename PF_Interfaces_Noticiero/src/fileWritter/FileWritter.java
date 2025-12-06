package fileWritter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

				if (u.getIdUser() == userLogged.getIdUser()) {
					linea = u.getIdUser() + ";;" + u.getNickName() + ";;" + u.getPass() + ";;" + u.getVecesLoaded()
							+ ";;" + u.getMail() + ";;" + seleccionesS;
				} else {
					linea = u.getIdUser() + ";;" + u.getNickName() + ";;" + u.getPass() + ";;" + u.getVecesLoaded()
							+ ";;" + u.getMail();
				}

				bw.write(linea);
				bw.newLine();
			}

		} catch (IOException e) {
			System.err.println("Error al reescribir al usuario al setear la configuración.");
			e.printStackTrace();
		}
	}

}
