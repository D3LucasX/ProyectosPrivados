package Launcher;

import model.User;

public class Sesion {
	
	private static User usuarioLogueado;

	public static void setUsuario(User usuario) {
        usuarioLogueado = usuario;
    }
	
	public static User getUsuario() {
		return usuarioLogueado;
	}
	
	public static void cerrarSesion() {
        usuarioLogueado = null;
    }

}
