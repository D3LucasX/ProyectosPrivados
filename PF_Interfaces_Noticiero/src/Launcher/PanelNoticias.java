package Launcher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import model.Configuracion;
import model.Noticia;
import model.Paginas;
import model.User;

public class PanelNoticias extends JPanel {

	private ArrayList<User> listaUsu;
	private ArrayList<Paginas> listaPaginas;
	private ArrayList<Noticia> listaNoticias;
	private FileLoader carga;
	private FileWritter escritor;
	private StringBuilder noticiasTexto;
	private Email email;

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

	private void inicializarComponentes() {
		listaNoticias = carga.creaarListaDeNoticias(listaPaginas);
		ArrayList<Noticia> noticiasSeleccionadas = new ArrayList<Noticia>();

		if (listaNoticias != null) {
			if (!listaNoticias.isEmpty()) {
				User usuarioLogueado = Sesion.getUsuario();
				String selecciones = usuarioLogueado.getSelecciones();
				if (selecciones == null || selecciones.equals("0") || selecciones.isEmpty()) {
					System.out.println("Este usuario no tiene selecciones");
					return;
				}

				String[] listaSelecciones = selecciones.split("\\*");
				int i = 0;
				while (i < listaSelecciones.length) {
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

				mostrarNoticiasPorCategoria(listaCategorias, noticiasSeleccionadas, noticiasSeleccionadas);
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

	private void mostrarNoticiasPorCategoria(ArrayList<String> listaCategorias, ArrayList<Noticia> listaNoticias,
			ArrayList<Noticia> noticiasSeleccionadas) {
		removeAll();
		revalidate();
		repaint();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // layout vertical
		int anchoArea = 800; // ancho del JTextArea
		int altoMax = 100; // altura visible máxima de JTextArea
		StringBuilder todasLasNoticias = new StringBuilder();

		for (String categoria : listaCategorias) {
			// Panel para cada categoría
			JPanel panelCategoria = new JPanel();
			panelCategoria.setLayout(new BoxLayout(panelCategoria, BoxLayout.Y_AXIS));

			// Label de categoría
			JLabel lblCategoria = new JLabel(categoria.toUpperCase() + ":");
			lblCategoria.setAlignmentX(CENTER_ALIGNMENT);
			panelCategoria.add(lblCategoria);

			// Recoger noticias de la categoría
			noticiasTexto = new StringBuilder();
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
			todasLasNoticias.append(noticiasTexto);

		}
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));

		JButton btnCerrarSesion = new JButton("Cerrar sesión");
		JButton btnGuardar = new JButton("Guardar");
		JButton btnEmail = new JButton("Enviar e-mail"); 
		JButton btnAtras = new JButton("Atras");

		btnAtras.setPreferredSize(new Dimension(120, 30));
		btnAtras.setMaximumSize(new Dimension(120, 30));
		btnCerrarSesion.setPreferredSize(new Dimension(120, 30));
		btnCerrarSesion.setMaximumSize(new Dimension(120, 30));
		btnGuardar.setPreferredSize(new Dimension(120, 30));
		btnGuardar.setMaximumSize(new Dimension(120, 30));

		User usuarioLogueado = Sesion.getUsuario();

		if (usuarioLogueado.getIdUser() == 1) { 
			btnCerrarSesion.setVisible(false);
			btnGuardar.setVisible(false);
			btnAtras.setVisible(true);
		} else { 
			btnCerrarSesion.setVisible(true);
			btnGuardar.setVisible(true);
			btnAtras.setVisible(false);
		}

		// Añafo los botones al panel
		panelBotones.add(btnAtras);
		panelBotones.add(btnCerrarSesion);
		panelBotones.add(Box.createHorizontalGlue());
		panelBotones.add(btnEmail);
		panelBotones.add(Box.createHorizontalGlue());
		panelBotones.add(btnGuardar);

		add(panelBotones);
		panelBotones.revalidate();
		panelBotones.repaint();

		// Boton atras
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.mostrarPAdmin(ventana, listaUsu, listaPaginas);
			}
		});
		// Boton cerrar sesión
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sesion.setUsuario(null);
				ventana.mostrarLogin(listaUsu, listaPaginas);
				JOptionPane.showMessageDialog(null, "Sesión Cerrada con exito.", "INFO", 3);
			}
		});
		// Boton de Guardar noticias
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User usuarioLogueado = Sesion.getUsuario();
				StringBuilder mensaje = crearMensaje(listaCategorias, noticiasSeleccionadas);
				boolean guardadas = escritor.guardarNoticias(mensaje, usuarioLogueado);
				if (guardadas) {
					JOptionPane.showMessageDialog(null, "Noticias guardadas con exito.", "ENHORABUENA!", 3);
				} else {
					JOptionPane.showMessageDialog(null, "No se han podido guardar las noticias", "ERROR", 0);
				}
			}
		});
		// Boton de enviar noticias por email
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Configuracion confi = carga.cargarConfiguracion();
				StringBuilder mensaje = crearMensaje(listaCategorias, noticiasSeleccionadas);
				String correoDest = Sesion.getUsuario().getMail();
				Email email = new Email(confi.getCorreoEnvio(), correoDest, mensaje.toString(), confi.getPassword(),
						confi.getHoraEnvio());
				boolean enviado = email.enviarEmail();
				if (enviado) {
					JOptionPane.showMessageDialog(null, "El mensaje fué enviado correctamente", "ENHORABUENA", 3);
				} else {
					JOptionPane.showMessageDialog(null, "No se pudo enviar el mensaje", "ERROR", 0);
				}
			}
		});
	}

	public StringBuilder crearMensaje(ArrayList<String> listaCategorias, ArrayList<Noticia> listaNoticias) {
		noticiasTexto = new StringBuilder();
		for (Noticia no : listaNoticias) {
			System.out.println(no.toString());
		}
		for (String categoria : listaCategorias) {
			noticiasTexto.append(categoria + "\n");
			for (Noticia noticia : listaNoticias) {
				if (noticia.getTitulo().equalsIgnoreCase(categoria)) {
					String noticiaTexto = buscadorNoticia(noticia.getUrl(), noticia.getFiltro());
					noticiasTexto.append("- ").append(noticiaTexto).append("\n");
				}
			}
		}
		return noticiasTexto;
	}

}
