package launcher;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import fileLoader.FileLoader;
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

	public boolean esHoraValida() {
		Configuracion configEmail = carga.cargarConfiguracion();
		if (configEmail != null) {
			String hora = configuracion.getHoraEnvio();
			if (hora.matches("^([01][0-9]|2[0-3]):([0-5][0-9])$")) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "La hora indicada en el archivo de configuracion es inválida",
						"ERROR", 0);
				JOptionPane.showMessageDialog(null,
						"Se cerrará la aplicación, modifíquela desde el archivo ConfiguracionDeNoticias.txt", "ERROR",
						0);
				return false;
			}
		}else return false;
	}

	@Override
	public void run() {

		listaUsuarios = carga.cargarUsuariosConConfiguracion();
		listaPaginas = carga.cargarConfigPagina();
		if (listaPaginas == null || listaUsuarios == null) {
			System.exit(0);
		}

		ArrayList<Noticia> listaNoticias = new ArrayList<>();

		boolean continuar = true;
		if (esHoraValida()) {
			while (continuar) {
				// Hora actual
				LocalTime ahora = LocalTime.now().withSecond(0).withNano(0);
				// Hora configurada parseada para poder comparar
				LocalTime horaConfigurada = null;
				horaConfigurada = LocalTime.parse(configuracion.getHoraEnvio());

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
		} else {
			JOptionPane.showMessageDialog(null, "Error cítico, falta la configuracion del email", "ERROR", 0);
			System.exit(0);
		}
	}
}
