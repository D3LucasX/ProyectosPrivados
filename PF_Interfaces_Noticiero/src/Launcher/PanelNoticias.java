package Launcher;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
		listaNoticias = carga.creaarListaDeNoticias(listaPaginas);

		if (listaNoticias != null) {
			if (!listaNoticias.isEmpty()) {
				ArrayList<String> listaCategorias = crearListaCategorias(listaNoticias);
				mostrarNoticiasPorCategoria(listaCategorias, listaNoticias);
			}
		} else {
			System.err.println("No existe la lista de noticias");
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

	private void mostrarNoticiasPorCategoria(ArrayList<String> listaCategorias, ArrayList<Noticia> listaNoticias) {
	    removeAll();
	    revalidate();
	    repaint();

	    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // layout vertical
	    int anchoArea = 600; // ancho del JTextArea
	    int altoMax = 100;   // altura visible máxima de JTextArea

	    for (String categoria : listaCategorias) {
	        // Panel para cada categoría
	        JPanel panelCategoria = new JPanel();
	        panelCategoria.setLayout(new BoxLayout(panelCategoria, BoxLayout.Y_AXIS));

	        // Label de categoría
	        JLabel lblCategoria = new JLabel(categoria.toUpperCase() + ":");
	        panelCategoria.add(lblCategoria);

	        // Recoger noticias de la categoría
	        StringBuilder noticiasTexto = new StringBuilder();
	        for (Noticia noticia : listaNoticias) {
	            if (noticia.getTitulo().equalsIgnoreCase(categoria)) {
	                String noticiaTexto = buscadorNoticia(noticia.getUrl(), noticia.getFiltro());
	                noticiasTexto.append("- ").append(noticiaTexto).append("\n");
	            }
	        }

	        // JTextArea con scroll
	        JTextArea areaNoticias = new JTextArea(noticiasTexto.toString());
	        areaNoticias.setEditable(false);
	        areaNoticias.setLineWrap(true);
	        areaNoticias.setWrapStyleWord(true);

	        JScrollPane scroll = new JScrollPane(areaNoticias);
	        scroll.setMaximumSize(new Dimension(anchoArea, altoMax));
	        panelCategoria.add(scroll);

	        // Añadir panel de categoría al panel principal
	        add(panelCategoria);
	        add(Box.createVerticalStrut(10)); // espacio entre categorías
	    }
	}


}
