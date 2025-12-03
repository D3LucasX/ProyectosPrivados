package main;


import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.User;

public class VentanaAplicacion extends JFrame{
	private ArrayList<User> usuarios;
	private CardLayout cardLayout;
	public VentanaAplicacion() {
		setTitle("INFÓRMATE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);
        
        // 1-Inicializamos los paneles
        Panel splash = new Panel(this);
        LoginPanel login = new LoginPanel();
     // Añadimos los paneles al JFrame
        getContentPane().add(splash, "Splash");
        getContentPane().add(login, "Login");

        // Mostramos el Splash primero
        cardLayout.show(getContentPane(), "Splash");

        // Tamaño inicial del Splash
        setSize(800, 400); // más ancho y menos alto
        setLocationRelativeTo(null); // centra la ventana
        setVisible(true);
	}
	
	public ArrayList<User> getUsers(){
		return usuarios;
	}
	
	// Método para cambiar de panel y tamaño
    public void mostrarLogin() {
        cardLayout.show(getContentPane(), "Login");
        setSize(600, 800); // tamaño normal del login
        setLocationRelativeTo(null); // centra de nuevo
    }
}
