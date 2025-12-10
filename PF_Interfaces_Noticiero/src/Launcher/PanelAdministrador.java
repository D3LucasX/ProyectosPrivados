package Launcher;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import FileLoader.FileLoader;
import fileWritter.FileWritter;
import model.Paginas;
import model.User;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class PanelAdministrador extends JPanel {

	private Ventana ventana;
	private ArrayList<User> listaUsuarios;
	private ArrayList<Paginas> listaPaginas;
	private FileLoader carga;
	private FileWritter escritor;

	private JLabel lblTitulo;
	private JButton btnEliminarUsuario;
	private JButton btnCrearUsuario;
	private JButton btnModoTest;
	private JButton btnLogOut;
	private JSeparator separator;

	public PanelAdministrador(Ventana ventana, ArrayList<User> listaUsuarios, ArrayList<Paginas> listaPaginas) {
		this.ventana = ventana;
		this.listaUsuarios = listaUsuarios;
		this.listaPaginas = listaPaginas;
		this.carga = new FileLoader();
		this.escritor = new FileWritter();

		initialize();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();

		// Colores del degradado
		Color rosa = new Color(255, 102, 178); // rosa
		Color amarillo = new Color(255, 255, 102); // amarillo

		// Degradado vertical
		GradientPaint gp = new GradientPaint(0, 0, rosa, 0, height, amarillo);

		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);
	}

	private void initialize() {
		setLayout(null);

		lblTitulo = new JLabel("ELIJA UNA OPCIÓN");
		lblTitulo.setForeground(new Color(89, 89, 89));
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(323, 171, 258, 37);
		add(lblTitulo);

		btnCrearUsuario = new JButton("Crear nuevo usuario");
		btnCrearUsuario.setForeground(new Color(89, 89, 89));
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Creamos el usuario
				User userNuevo = crearUser();
				if(userNuevo != null) {
					listaUsuarios.add(userNuevo);
					escritor.reescribirUsu(listaUsuarios);
					listaUsuarios = carga.cargarUsuarios();
				}else {
					JOptionPane.showMessageDialog(null, "Se cancelo la inscripcion del usuario", "ERROR", 0);
				}
			}
		});
		btnCrearUsuario.setBounds(384, 310, 131, 23);
		add(btnCrearUsuario);

		btnEliminarUsuario = new JButton("Eliminar Usuario");
		btnEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList <User> actualizado = eliminarUsuario();
				if (actualizado == null) {
					JOptionPane.showMessageDialog(null, "Proceso de eliminación de usuario cancelado.", "ERROR", 0);
				}
			}
		});
		btnEliminarUsuario.setForeground(new Color(89, 89, 89));
		btnEliminarUsuario.setBounds(204, 310, 131, 23);
		add(btnEliminarUsuario);

		btnModoTest = new JButton("Test de noticias");
		
		btnModoTest.setForeground(new Color(89, 89, 89));
		btnModoTest.setBounds(556, 310, 131, 23);
		add(btnModoTest);

		btnLogOut = new JButton("Cerrar Sesión");
		btnLogOut.setForeground(new Color(89, 89, 89));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sesion.setUsuario(null);
				ventana.mostrarLogin(listaUsuarios, listaPaginas);
			}
		});
		btnLogOut.setBounds(26, 481, 97, 23);
		add(btnLogOut);

		separator = new JSeparator();
		separator.setBounds(204, 344, 483, 77);
		add(separator);
	}
	
	public User crearUser() {
		// Actualizamos lista de usuarios por si acaso
		listaUsuarios = carga.cargarUsuariosConConfiguracion();
		boolean coincide = false;
		String pass = "";
		int idNuevo = listaUsuarios.size() + 1, vecesLoaded = 0;
		String nickName = JOptionPane.showInputDialog(null, "Introduce el nickname:", "Alta de usuario",
				JOptionPane.QUESTION_MESSAGE);
		 if (nickName == null) {
		        return null;
		    }
		do {
			pass = JOptionPane.showInputDialog(
			        null,
			        "Introduce la contraseña:",
			        "Alta de usuario",
			        JOptionPane.QUESTION_MESSAGE
			);
			if (pass == null) {
				return null;
			}
			String passVerif = JOptionPane.showInputDialog(
			        null,
			        "Repita la contraseña:",
			        "Alta de usuario",
			        JOptionPane.QUESTION_MESSAGE
			);
			if(passVerif == null) {
				return null;
			}
			if(pass.equals(passVerif)) {
				coincide = true;
			}else {
				JOptionPane.showMessageDialog(null, "No han coicidido las contraseñas, inténtelo de nuevo.", "ERROR", 0);
			}
			
		}while(!coincide);
		String email = JOptionPane.showInputDialog(
		        null,
		        "Introduce el email:",
		        "Alta de usuario",
		        JOptionPane.QUESTION_MESSAGE
		);
		if (email == null) {
			return null;
		}
		
		
		User usuarioAlta = new User(idNuevo, nickName, pass, vecesLoaded, email,"0");
		return usuarioAlta;
	}
	
	public ArrayList<User> eliminarUsuario() {
		listaUsuarios = carga.cargarUsuariosConConfiguracion();
		String idS = JOptionPane.showInputDialog(null, "Introdue el ID del usuario que quieres buscar:", "Baja de usuario",
				JOptionPane.QUESTION_MESSAGE);
		if(idS == null) {
			return null;
		}
		int id = Integer.parseInt(idS);
		ArrayList<User> nuevaListaDeUsuarios = new ArrayList<User>();
		for (User u : listaUsuarios) {
			if (u.getIdUser() != id) {
				nuevaListaDeUsuarios.add(u);
			}
		}
		escritor.reescribirUsu(nuevaListaDeUsuarios);
		listaUsuarios = nuevaListaDeUsuarios;
		return listaUsuarios;
	}

}
