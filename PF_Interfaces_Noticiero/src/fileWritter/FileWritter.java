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
			e.printStackTrace();
		}
	}
	
	public void setConfiguracion() {
		
	}
}
