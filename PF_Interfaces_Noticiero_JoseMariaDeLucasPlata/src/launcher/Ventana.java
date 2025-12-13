package launcher;

import java.awt.CardLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Paginas;
import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ventana extends JFrame {

	public Ventana() {
		Thread hiloEnvioEmail = new Thread(new HiloEnviarEmail());
		hiloEnvioEmail.start();
		// FRAME inicial sin decoraciones
		setBounds(100, 100, 900, 630);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setUndecorated(true); // sin marco
		this.setIconImage(Toolkit.getDefaultToolkit() // imagen de icono
				.getImage("imagenFondo.jpg"));
		setResizable(false);
		getContentPane().setLayout(new CardLayout());
	}

	public void mostrarPanel(JPanel panel, String nombre) {
		panel.setName(nombre);
		getContentPane().add(panel, nombre);
		CardLayout cl = (CardLayout) getContentPane().getLayout(); // para cambiar de paneles
		cl.show(getContentPane(), nombre);
	}

	public void mostrarLogin(ArrayList<User> listaUsuarios, ArrayList<Paginas> listaPaginas) {
		PanelLogin login = new PanelLogin(listaUsuarios, listaPaginas, this);
		mostrarPanel(login, "login");
	}

	public void mostrarPConfig(Ventana ventana, ArrayList<User> listaUsuarios, ArrayList<Paginas> listaPaginas) {
		PanelConfiguracion config = new PanelConfiguracion(this, listaUsuarios, listaPaginas);
		mostrarPanel(config, "config");
	}

	public void mostrarPNoticias(ArrayList<User> listaUsuarios, ArrayList<Paginas> listaPaginas) {
		PanelNoticias noticias = new PanelNoticias(this, listaUsuarios, listaPaginas);
		mostrarPanel(noticias, "Noticias");
	}

	public void mostrarPAdmin(Ventana ventana, ArrayList<User> listaUsuarios, ArrayList<Paginas> listaPaginas) {
		PanelAdministrador PAdmin = new PanelAdministrador(ventana, listaUsuarios, listaPaginas);
		mostrarPanel(PAdmin, "PAdmin");
	}

	public void mostrarPBorrarUsu(Ventana ventana, ArrayList<User> listaUsuarios, ArrayList<Paginas> listaPaginas) {
		PanelEliminarUser pElimUser = new PanelEliminarUser(this, listaUsuarios, listaPaginas);
		mostrarPanel(pElimUser, "PeliminarUser");
	}

	// Método para restaurar el marco de la ventana sin SwingWorker
	public void restaurarMarco() {
		if (this.isDisplayable()) {
			Rectangle bounds = getBounds(); // guardamos tamaño y posición
			dispose(); // liberamos recursos para poder cambiar undecorated
			setUndecorated(false); // activamos marco
			this.setIconImage(Toolkit.getDefaultToolkit() // imagen de icono
					.getImage("imagenFondo.jpg"));
			// Menu bar con el about
			JMenuBar menuBar = new JMenuBar();
			JMenu menuAyuda = new JMenu("Ayuda");
			JMenuItem itemAbout = new JMenuItem("Acerca de...");
			itemAbout.addActionListener(new ActionListener() {
				// Cuando pulsa sale un mensaje emergernte
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        JOptionPane.showMessageDialog(
			            null,
			            "Noticiero App v1.0\n" +
			            "Autor: Jose Maria De Lucas Plata\n" +
			            "© 2025 Todos los derechos reservados.",
			            "Acerca de",
			            JOptionPane.INFORMATION_MESSAGE
			        );
			    }
			});
			menuAyuda.add(itemAbout);
			menuBar.add(menuAyuda);
			// Finalmente, asignamos la barra al frame
			setJMenuBar(menuBar);
			setBounds(bounds);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // para que no cierre la ventana
			setVisible(true); // mostramos de nuevo la ventana al pulsar ekl sapa
			addWindowListener(new WindowAdapter() {
				// evento de pulsar el aspa, pregunta si estamos seguros de cerrar la app
				@Override
				public void windowClosing(WindowEvent e) {
					int cerrar = JOptionPane.showConfirmDialog(Ventana.this,
							"¿Seguro que quieres cerrar la aplicación?", "Cerar Aplicación", JOptionPane.YES_NO_OPTION,
							1);
					if (cerrar == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				}
			});
		}
	}
}
