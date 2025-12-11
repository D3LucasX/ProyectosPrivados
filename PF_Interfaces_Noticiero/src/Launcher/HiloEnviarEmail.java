package Launcher;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import FileLoader.FileLoader;
import fileWritter.FileWritter;
import model.Noticia;
import model.Paginas;
import model.User;

public class HiloEnviarEmail implements Runnable {
	private String hora;
	private ArrayList<User> listaUsuarios;
	private ArrayList<Paginas> listaPaginas;
	private FileLoader carga;
	private FileWritter escritor;
	private Email email;

	public HiloEnviarEmail() {
		this.hora = (java.time.LocalTime.now().getHour() + ":" + java.time.LocalTime.now().getMinute());
		this.listaPaginas = new ArrayList<Paginas>();
		this.listaUsuarios = new ArrayList<User>();
		this.carga = new FileLoader();
		this.escritor = new FileWritter();
	}
	
	public String getHora() {
		return hora;
	}

	@Override
	public void run() {
		
		ArrayList<Noticia> listaNoticias = new ArrayList<Noticia>();
		listaUsuarios = carga.cargarUsuariosConConfiguracion();
		listaPaginas = carga.cargarConfigPagina();
		User usuarioLogueado = Sesion.getUsuario();
		boolean contador = true;
		while (contador) {
			hora = (java.time.LocalTime.now().getHour() + ":" + java.time.LocalTime.now().getMinute());
			if(getHora().equals("11:10")) {
				email.empezarEmail(listaNoticias, listaUsuarios, listaPaginas, carga, escritor);
				contador = false;
			}else {
				contador = true;
			}
		}
	}
}
