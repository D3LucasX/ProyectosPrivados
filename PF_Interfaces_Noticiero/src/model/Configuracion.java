package model;

public class Configuracion {

	private String password;
	private String correoEnvio;
	private String horaEnvio;
	
	public Configuracion(String correoEvio, String password, String horaEnvio) {
		this.correoEnvio = correoEvio;
		this.password = password;
		this.horaEnvio = horaEnvio;
	}
	
	public Configuracion() {
		this.correoEnvio = correoEnvio;
		this.password = password;
		this.horaEnvio = horaEnvio;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorreoEnvio() {
		return correoEnvio;
	}

	public void setCorreoEnvio(String correoEnvio) {
		this.correoEnvio = correoEnvio;
	}

	public String getHoraEnvio() {
		return horaEnvio;
	}

	public void setHoraEnvio(String horaEnvio) {
		this.horaEnvio = horaEnvio;
	}
	
	
}
