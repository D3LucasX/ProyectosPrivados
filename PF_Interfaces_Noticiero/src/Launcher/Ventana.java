package Launcher;

import java.awt.CardLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Paginas;
import model.User;

public class Ventana extends JFrame {

    public Ventana() {
        // FRAME inicial sin decoraciones
        setBounds(100, 100, 900, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); //sin marco
        setResizable(false);
        getContentPane().setLayout(new CardLayout());
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
