package Launcher;

import java.time.LocalTime;
import java.util.ArrayList;

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
	private Configuracion configuracion;

	public HiloEnviarEmail() {
		this.carga = new FileLoader();
		this.escritor = new FileWritter();
		this.listaUsuarios = new ArrayList<>();
		this.listaPaginas = new ArrayList<>();

		// Cargar configuración desde el TXT aquí
		this.configuracion = carga.cargarConfiguracion();
	}

	public String getHora() {
		return hora;
	}

	@Override
	public void run() {

		listaUsuarios = carga.cargarUsuariosConConfiguracion();
		listaPaginas = carga.cargarConfigPagina();

		ArrayList<Noticia> listaNoticias = new ArrayList<>();

		boolean continuar = true;

		while (continuar) {
			// Hora actual
			LocalTime ahora = LocalTime.now();
			// Hora configurada parseada para poder comparar
			LocalTime horaConfigurada = LocalTime.parse(configuracion.getHoraEnvio());

			System.out.println("Hora actual: " + ahora + " | Hora de envío: " + horaConfigurada);

			// Si coincide la hora se envia
			if (ahora.equals(horaConfigurada)) {

				Email email = new Email();
				email.empezarEmail(listaNoticias, listaUsuarios, listaPaginas, carga, escritor);

				continuar = false;
			}
			if (!continuar) {
				try {
					Thread.sleep(86280000); // Si se han enviado las noticias el hilo duerme 23h y 58 min
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(60000); // Si no va preguntando cada minuto
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
