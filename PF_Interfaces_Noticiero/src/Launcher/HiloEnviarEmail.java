package Launcher;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import FileLoader.FileLoader;
import fileWritter.FileWritter;
import model.Configuracion;
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
	private Configuracion configuracion;

	public HiloEnviarEmail() {
		this.hora = (java.time.LocalTime.now().getHour() + ":" + java.time.LocalTime.now().getMinute());
		this.listaPaginas = new ArrayList<Paginas>();
		this.listaUsuarios = new ArrayList<User>();
		this.carga = new FileLoader();
		this.escritor = new FileWritter();
		configuracion = new Configuracion();
		email = new Email();
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
			if (getHora().equals(configuracion.getHoraEnvio())) {
				Email email = new Email(configuracion.getCorreoEnvio(), null, null, configuracion.getPassword(), configuracion.getHoraEnvio());
				email.empezarEmail(listaNoticias, listaUsuarios, listaPaginas, carga, escritor);
				contador = false;
			}
			try {
				Thread.sleep(84600000);
			} catch (InterruptedException e) {
			}
		}
	}
}
