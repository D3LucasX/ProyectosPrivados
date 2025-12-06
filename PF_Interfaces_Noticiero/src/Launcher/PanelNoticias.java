package Launcher;

import java.util.ArrayList;

import javax.swing.JPanel;

import FileLoader.FileLoader;
import fileWritter.FileWritter;
import model.Noticia;
import model.Paginas;
import model.User;

public class PanelNoticias extends JPanel {

	private ArrayList<User> listaUsu;
	private ArrayList<Paginas> listaPaginas;
	private ArrayList<Noticia> listaNoticias;
	private FileLoader carga;
	private FileWritter escritor;

	private Ventana ventana;

	public PanelNoticias(Ventana ventana, ArrayList<User> listaUsuarios, ArrayList<Paginas> listaPaginas) {
		this.ventana = ventana;
		this.listaUsu = listaUsuarios;
		this.listaPaginas = listaPaginas;
		this.listaNoticias = new ArrayList<Noticia>();
        this.carga = new FileLoader();
        this.escritor = new FileWritter();
       
        inicializarComponentes();
	}

	private void inicializarComponentes() {
		
	}
}
