package FileLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.User;

public class FileLoader {

	public ArrayList<User> cargarUsuarios(){
		ArrayList<User> listaUsuarios = new ArrayList<User>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("ArchivoUsuario.txt"))){
			while(br.readLine() != null) {
				String usuario = br.readLine();
				String[] camposUsu = usuario.split(";;");
				int idUser = Integer.parseInt(camposUsu[0]);
				int vecesLoaded = Integer.parseInt(camposUsu[3]);
				User user = new User(idUser, camposUsu[1], camposUsu[2], vecesLoaded, camposUsu[4]);
				listaUsuarios.add(user);
			}
			
			
		}catch(IOException e) {
			System.err.println("No se ha podido cargar a los usuarios.");
			e.printStackTrace();
			return null;
		}
		return listaUsuarios;
	}
	
	public ArrayList
}
