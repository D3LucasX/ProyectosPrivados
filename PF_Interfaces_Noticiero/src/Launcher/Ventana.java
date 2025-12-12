package Launcher;

import java.awt.CardLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Paginas;
import model.User;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ventana extends JFrame {

    public Ventana() {
    	Thread hiloEnvioEmail= new Thread(new HiloEnviarEmail());
    	hiloEnvioEmail.start();
        // FRAME inicial sin decoraciones
        setBounds(100, 100, 900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setUndecorated(true); //sin marco
        setResizable(false);
        getContentPane().setLayout(new CardLayout());
        
        addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			int cerrar = JOptionPane.showConfirmDialog(Ventana.this, "¿Seguro que quieres cerrar la aplicación?", "Cerar Aplicación", JOptionPane.YES_NO_OPTION, 1);
    			if (cerrar == JOptionPane.YES_OPTION) {
    				System.exit(1);
    			}
    		}
    	});
    }
    
    public void mostrarPanel(JPanel panel, String nombre) {
        panel.setName(nombre);
        getContentPane().add(panel, nombre);
        CardLayout cl = (CardLayout) getContentPane().getLayout();
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
            setBounds(bounds);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true); // mostramos de nuevo la ventana
        }
    }
}
