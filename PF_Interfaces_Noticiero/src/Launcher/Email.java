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

	public Email(String correoDestino, String mensaje) {
		this.correoDestino = correoDestino;
		this.mensaje = mensaje;
	}

	public Email() {
		this.listaPaginas = new ArrayList<Paginas>();
		this.listaUsuarios = new ArrayList<User>();
		this.carga = new FileLoader();
		this.escritor = new FileWritter();
	}

	public boolean enviarEmail() {
		final String fromEmail = "jose.delucas.dosa@gmail.com"; // EMAIL DE SALIDA
		final String password = "emcw qyvk nprd yygr"; // CONTRASEÑA DEL EMAIL DE SALIDA (aplicaciones de 3ros)
														// Contraseñas de aplicación -- Verificación en 2 pasos
														// https://,yaccount.google.com/appaswords
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
					return new PasswordAuthentication(fromEmail, password);
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
		boolean valido = false;
		ArrayList<String> listaCategorias = new ArrayList<String>();
		listaNoticias = carga.creaarListaDeNoticias(listaPaginas);
		ArrayList<Noticia> noticiasSeleccionadas = new ArrayList<Noticia>();
		if (listaNoticias != null) {
			if (!listaNoticias.isEmpty()) {
				User usuarioLogueado = Sesion.getUsuario();
				String selecciones = usuarioLogueado.getSelecciones();
				if (selecciones != null || !selecciones.equals("0") || !selecciones.isEmpty()) {

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
					listaCategorias = crearListaCategorias(noticiasSeleccionadas);

					// Recoger noticias de la categoría
					StringBuilder noticiasTexto = new StringBuilder();
					StringBuilder mensaje = crearMensaje(listaCategorias, noticiasSeleccionadas, noticiasTexto);
					String correo = Sesion.getUsuario().getMail();
					Email email = new Email(correo, mensaje.toString());
					boolean enviado = email.enviarEmail();
					if (enviado) {
						JOptionPane.showMessageDialog(null, "El mensaje fué enviado correctamente", "ENHORABUENA", 3);
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo enviar el mensaje", "ERROR", 0);
					}
				} else {
					JOptionPane.showMessageDialog(null, "El usuario " + Sesion.getUsuario().getNickName() + " no tiene configuradas las noticias.", "Info", 1);
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
