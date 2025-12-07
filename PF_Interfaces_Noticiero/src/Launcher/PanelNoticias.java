package Launcher;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		for (Noticia n : listaNoticias) {
			System.out.println(n.toString());
		}

		if (listaNoticias != null) {
			if (!listaNoticias.isEmpty()) {
				User usuarioLogueado = Sesion.getUsuario();
				String selecciones = usuarioLogueado.getSelecciones();
				if (selecciones == null || selecciones.equals("0") || selecciones.isEmpty()) {
					System.out.println("Este usuario no tiene selecciones");
					return;
				}
				
				String[] listaSelecciones = selecciones.split("\\*");
				ArrayList<Noticia> noticiasSeleccionadas = new ArrayList<Noticia>();
				int i = 0;
				while(i < listaSelecciones.length) {
					for (Noticia noticia : listaNoticias) {
						if (noticia.getIdNoticia().equals(listaSelecciones[i])) {
							noticiasSeleccionadas.add(noticia);
						}
					}
					i++;
				}
				ArrayList<String> listaCategorias = crearListaCategorias(noticiasSeleccionadas);
				for (String cat : listaCategorias) {
					System.out.println(cat);
				}

				mostrarNoticiasPorCategoria(listaCategorias, noticiasSeleccionadas);
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
		int anchoArea = 800; // ancho del JTextArea
		int altoMax = 100; // altura visible máxima de JTextArea

		for (String categoria : listaCategorias) {
			// Panel para cada categoría
			JPanel panelCategoria = new JPanel();
			panelCategoria.setLayout(new BoxLayout(panelCategoria, BoxLayout.Y_AXIS));

			// Label de categoría
			JLabel lblCategoria = new JLabel(categoria.toUpperCase() + ":");
			lblCategoria.setAlignmentX(CENTER_ALIGNMENT);
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
			panelCategoria.add(areaNoticias);

			// Añadir panel de categoría al panel principal
			add(panelCategoria);
			add(Box.createVerticalStrut(10)); // espacio entre categorías
			
			// Al final de mostrarNoticiasPorCategoria()
			add(Box.createVerticalGlue()); // empuja todo el contenido arriba

			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));

			JButton btnCerrarSesion = new JButton("Cerrar sesión");
			JButton btnGuardar = new JButton("Guardar");

			panelBotones.add(btnCerrarSesion);
			panelBotones.add(Box.createHorizontalGlue()); // separador flexible
			panelBotones.add(btnGuardar);

			add(panelBotones);
			
			btnCerrarSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Sesion.setUsuario(null);
					ventana.mostrarLogin(listaUsu, listaPaginas);
					JOptionPane.showMessageDialog(null, "Sesión Cerrada con exito.", "INFO", 3);
				}
			});
			
		}
	}

}
