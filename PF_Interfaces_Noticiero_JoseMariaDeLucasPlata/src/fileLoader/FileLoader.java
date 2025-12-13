package fileLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import launcher.Sesion;
import model.Configuracion;
import model.Noticia;
import model.Paginas;
import model.User;

public class FileLoader {
	// Para cargar a los usuarios con sus configuraciones
	public ArrayList<User> cargarUsuariosConConfiguracion() {

		File archivo = new File("ArchivoUsuario.txt");
		if (archivo.exists() && archivo.isFile()) {
			ArrayList<User> listaUsuarios = new ArrayList<User>();
			String usuario;
			try (BufferedReader br = new BufferedReader(new FileReader("ArchivoUsuario.txt"))) {
				while ((usuario = br.readLine()) != null) {
					String[] camposUsu = usuario.split(";;");
					if (camposUsu.length == 6) {
						int idUser = Integer.parseInt(camposUsu[0]);

						int vecesLoaded = Integer.parseInt(camposUsu[3]);
						User user = new User(idUser, camposUsu[1], camposUsu[2], vecesLoaded, camposUsu[4],
								camposUsu[5]);
						listaUsuarios.add(user);
					}
				}

			} catch (IOException e) {
				System.err.println("No se ha podido cargar a los usuarios.");
				e.printStackTrace();
				return null;
			}
			return listaUsuarios;
		} else {
			JOptionPane.showMessageDialog(null, "Falta de archivos de configuración, la aplicacion se cerrará",
					"ERROR GRAVE", 3);
		}
		return null;
	}

	// Cargar las paginas al inicio
	public ArrayList<Paginas> cargarConfigPagina() {
		File archivo = new File("ConfiguracionDeNoticias.txt");
		if (archivo.exists() && archivo.isFile()) {
			ArrayList<Paginas> listaPaginas = new ArrayList<Paginas>();
			String linea;
			try (BufferedReader br = new BufferedReader(new FileReader("ConfiguracionDeNoticias.txt"))) {
				while ((linea = br.readLine()) != null) {
					if (!linea.startsWith("#")) {
						String[] camposPag = linea.split(";;");
						if(camposPag.length == 3) {
							String idNoticia = camposPag[0];
							String url = camposPag[1];
							String filtro = camposPag[2];
							Paginas pagina = new Paginas(idNoticia, url, filtro);
							listaPaginas.add(pagina);
						}
					}
				}
			} catch (IOException e) {
				System.err.println("No se ha podido cargar las paginas con los filtros.");
				e.printStackTrace();
				return null;
			}
			return listaPaginas;
		} else {
			JOptionPane.showMessageDialog(null, "Falta de archivos de configuración, la aplicacion se cerrará",
					"ERROR GRAVE", 3);
		}
		return null;
	}

	// Cargar configuracion de envio de emails.
	public Configuracion cargarConfiguracion() {
		Configuracion nueaconfig = null;
		String linea;
		File archivo = new File("ConfiguracionDeNoticias.txt");
		if (archivo.exists() && archivo.isFile()) {
			try (BufferedReader br = new BufferedReader(new FileReader("ConfiguracionDeNoticias.txt"))) {
				while ((linea = br.readLine()) != null) {
					if (linea.contains("###")) {
						linea = linea.replaceAll("^#+|#+$", "");
						String[] configuracion = linea.split("--");
						nueaconfig = new Configuracion(configuracion[0], configuracion[1], configuracion[2]);
					}
				}
			} catch (IOException e) {

				e.printStackTrace();
				return null;
			}
			return nueaconfig;
		} else {
			JOptionPane.showMessageDialog(null, "Falta de archivos de configuración, la aplicacion se cerrará",
					"ERROR GRAVE", 3);
		}
		return null;
	}

	public ArrayList<Noticia> creaarListaDeNoticias(ArrayList<Paginas> listaPaginas) {

		ArrayList<Noticia> listaNoticias = new ArrayList<Noticia>();
		for (Paginas pagina : listaPaginas) {
			int idNoticia = Integer.parseInt(pagina.getIdNoticia());
			if (idNoticia > 0 && idNoticia <= 3) {
				// creas una noticia con el titulo Economia
				Noticia noticia = new Noticia(pagina.getIdNoticia(), "ECONOMIA", pagina.getUrl(), pagina.getFiltro());
				listaNoticias.add(noticia);
			} else if (idNoticia > 3 && idNoticia <= 6) {
				// Creas una noticia sobre videojuegos
				Noticia noticia = new Noticia(pagina.getIdNoticia(), "VIDEOJUEGOS", pagina.getUrl(),
						pagina.getFiltro());
				listaNoticias.add(noticia);
			} else if (idNoticia > 6 && idNoticia <= 9) {
				// creas una noticia sobre internacional
				Noticia noticia = new Noticia(pagina.getIdNoticia(), "INTERNACIONAL", pagina.getUrl(),
						pagina.getFiltro());
				listaNoticias.add(noticia);
			} else if (idNoticia > 9 && idNoticia <= 12) {
				// creas una noticia sobre nacional
				Noticia noticia = new Noticia(pagina.getIdNoticia(), "NACIONAL", pagina.getUrl(), pagina.getFiltro());
				listaNoticias.add(noticia);
			} else if (idNoticia > 12 && idNoticia <= 15) {
				// creas una noticia sobre deportes
				Noticia noticia = new Noticia(pagina.getIdNoticia(), "DEPORTES", pagina.getUrl(), pagina.getFiltro());
				listaNoticias.add(noticia);
			} else if (idNoticia > 15 && idNoticia <= 18) {
				// creas una noticia sobre moda
				Noticia noticia = new Noticia(pagina.getIdNoticia(), "MODA", pagina.getUrl(), pagina.getFiltro());
				listaNoticias.add(noticia);
			}
		}
		return listaNoticias;
	}
}
