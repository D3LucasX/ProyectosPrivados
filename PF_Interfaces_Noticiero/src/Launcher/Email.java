package Launcher;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

public class Email {
	
	private String correoDestino;
	private String mensaje;
	
	public Email(String correoDestino, String mensaje) {
		this.correoDestino = correoDestino;
		this.mensaje = mensaje;
	}
	
	public void enviarEmail() {
		final String fromEmail = "jose.delucas.dosa@gmail.com"; // EMAIL DE SALIDA
		final String password = "emcw qyvk nprd yygr"; // CONTRASEÑA DEL EMAIL DE SALIDA (aplicaciones de 3ros)
														// Contraseñas de aplicación -- Verificación en 2 pasos
														// https://,yaccount.google.com/appaswords
		final String toEmail = correoDestino; // EMAIL DESTINATARIO

		System.out.println("Configurando datos conexión SSL");

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
		
		try{
		      MimeMessage msg = new MimeMessage(session);
		      //Configurar Cabeceras
		      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		      msg.addHeader("format", "flowed");
		      msg.addHeader("Content-Transfer-Encoding", "8bit");
		      msg.setFrom(new InternetAddress("no_reply@example.com", "Amanece Informado"));//Datos de ejemplo	      	      
		      msg.setReplyTo(InternetAddress.parse("no_reply_DOSA@DAM.com", false));	      
		      msg.setSubject("Noticiero DAM DOSA", "UTF-8");
		      msg.setText(mensaje, "UTF-8");
		      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));	   
		      System.out.println("MENSAJE CREADO");  	  
		      Transport.send(msg);
		      System.out.println("¡EMAIL ENVIADO!");//SI NO DA ERROR
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		    }
	}
}
