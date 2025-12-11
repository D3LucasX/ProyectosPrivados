package Launcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import FileLoader.FileLoader;
import fileWritter.FileWritter;
import model.Configuracion;
import model.Noticia;
import model.Paginas;
import model.User;

import javax.mail.PasswordAuthentication;

public class Email {
	private ArrayList<User> listaUsuarios;
	private ArrayList<Paginas> listaPaginas;
	private FileLoader carga;
	private FileWritter escritor;
	private Email email;
	private String correoDestino;
	private String mensaje;
	private String password;
	private String correoEnvio;
	private String hora;
	private Configuracion configuracion;

	public Email(String correoEnvio, String correoDestino, String mensaje, String password, String hora) {
		FileLoader loader = new FileLoader();
		this.configuracion = loader.cargarConfiguracion();

		this.correoEnvio = configuracion.getCorreoEnvio();
		this.password = configuracion.getPassword();
		this.hora = configuracion.getHoraEnvio();

		this.correoDestino = correoDestino;
		this.mensaje = mensaje;

		this.listaPaginas = new ArrayList<>();
		this.listaUsuarios = new ArrayList<>();
		this.carga = new FileLoader();
		this.escritor = new FileWritter();
	}

	public Email() {
		FileLoader loader = new FileLoader();
		this.configuracion = loader.cargarConfiguracion();

		this.correoEnvio = configuracion.getCorreoEnvio();
		this.password = configuracion.getPassword();
		this.hora = configuracion.getHoraEnvio();

		this.listaPaginas = new ArrayList<>();
		this.listaUsuarios = new ArrayList<>();
		this.carga = new FileLoader();
		this.escritor = new FileWritter();
	}

	public boolean enviarEmail() {

		final String toEmail = correoDestino; // EMAIL DESTINATARIO
		if (toEmail != null) {

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP de GMAIL en este caso
			props.put("mail.smtp.socketFactory.port", "465"); // PUERTO SSL
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
			props.put("mail.smtp.auth", "true"); // ACTIVAR SMTP AUTENTIFICACI�N
			props.put("mail.smtp.port", "465"); // SMTP Port
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(correoEnvio, password);
				}
			};
			Session session = Session.getDefaultInstance(props, auth);// CREA UNA SESIÓN CON TODAS LAS PROPIEDADES Y EL
																		// "LOGIN"
			System.out.println("Sesión Creada");

			try {
				MimeMessage msg = new MimeMessage(session);
				// Configurar Cabeceras
				msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
				msg.addHeader("format", "flowed");
				msg.addHeader("Content-Transfer-Encoding", "8bit");
				msg.setFrom(new InternetAddress("no_reply@example.com", "Amanece Informado"));// Datos de ejemplo
				msg.setReplyTo(InternetAddress.parse("no_reply_DOSA@DAM.com", false));
				msg.setSubject("Noticiero DAM DOSA", "UTF-8");
				msg.setText(mensaje, "UTF-8");
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
				Transport.send(msg);
				return true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se ha introducido el email del receptor.", "ERROR", 0);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Envio Cancelado.", "ERROR", 0);
			return false;
		}
	}

	public void empezarEmail(ArrayList<Noticia> listaNoticias, ArrayList<User> listaUsers,
			ArrayList<Paginas> listaPaginas, FileLoader carga, FileWritter escritor) {
		listaNoticias = carga.creaarListaDeNoticias(listaPaginas);
		for (User u : listaUsers) {
			if (listaNoticias != null || !listaNoticias.isEmpty()) {
				String selecciones = u.getSelecciones();
				if (selecciones != null && !selecciones.equals("0") && !selecciones.isEmpty()) {
					ArrayList<Noticia> noticiasSeleccionadas = new ArrayList<Noticia>();
					String[] listaSelecciones = selecciones.split("\\*");
					int i = 0;
					while (i < listaSelecciones.length) {
						for (Noticia noticia : listaNoticias) {
							if (noticia.getIdNoticia().equals(listaSelecciones[i])) {
								noticiasSeleccionadas.add(noticia);
							}
						}
						i++;
					}
					ArrayList<String> listaCategorias = crearListaCategorias(noticiasSeleccionadas);

					// Recoger noticias de la categoría
					StringBuilder noticiasTexto = new StringBuilder();
					StringBuilder mensaje = crearMensaje(listaCategorias, noticiasSeleccionadas, noticiasTexto);
					String correo = u.getMail();
					Email email = new Email(correoEnvio, correo, mensaje.toString(), password, hora);
					boolean enviado = email.enviarEmail();
					if (enviado) {
						JOptionPane.showMessageDialog(null, "El mensaje fué enviado correctamente", "ENHORABUENA", 3);
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo enviar el mensaje", "ERROR", 0);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"El usuario " + u.getNickName() + " no tiene configuradas las noticias.",
							"Info", 1);
				}
			}
		}
	}

	private ArrayList<String> crearListaCategorias(ArrayList<Noticia> listaDeNoticias) {
		ArrayList<String> listaCategorias = new ArrayList<String>();
		for (Noticia noticia : listaDeNoticias) {
			String categoria = noticia.getTitulo();
			if (!listaCategorias.contains(categoria)) {
				listaCategorias.add(categoria);
			}
		}
		return listaCategorias;
	}

	private String buscadorNoticia(String web, String filtro) {
		Document document = null;
		Element resultado = null;

		try {
			document = Jsoup.connect(web).get();
			resultado = document.selectFirst(filtro);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return resultado.text();

	}

	public StringBuilder crearMensaje(ArrayList<String> listaCategorias, ArrayList<Noticia> listaNoticias,
			StringBuilder noticiasTexto) {
		noticiasTexto = new StringBuilder();
		for (Noticia no : listaNoticias) {
			System.out.println(no.toString());
		}
		for (String categoria : listaCategorias) {
			noticiasTexto.append(categoria + "\n");
			for (Noticia noticia : listaNoticias) {
				if (noticia.getTitulo().equalsIgnoreCase(categoria)) {
					String noticiaTexto = buscadorNoticia(noticia.getUrl(), noticia.getFiltro());
					noticiasTexto.append("- ").append(noticiaTexto).append("\n");
				}
			}
		}
		return noticiasTexto;
	}

}
