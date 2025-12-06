package Launcher;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import FileLoader.FileLoader;
import fileWritter.FileWritter;
import model.Paginas;
import model.User;

public class PanelConfiguracion extends JPanel {

	private JButton botonGuardarConfig;

	private ArrayList<User> listaUsu;
	private ArrayList<Paginas> listaPaginas;
	private FileLoader carga;
	private FileWritter escritor;

	private ArrayList<JCheckBox> nacionalBoxes = new ArrayList<>();
	private ArrayList<JCheckBox> internacionalBoxes = new ArrayList<>();
	private ArrayList<JCheckBox> economiaBoxes = new ArrayList<>();
	private ArrayList<JCheckBox> deportesBoxes = new ArrayList<>();
	private ArrayList<JCheckBox> videojuegosBoxes = new ArrayList<>();
	private ArrayList<JCheckBox> modaBoxes = new ArrayList<>();

	private ArrayList<String> seleccionados = new ArrayList<String>();

	private Ventana ventana;

	public PanelConfiguracion(Ventana ventana, ArrayList<User> listaUsuarios, ArrayList<Paginas> listaPaginas) {
		this.ventana = ventana;
		this.listaUsu = listaUsuarios;
		this.listaPaginas = listaPaginas;
		this.carga = new FileLoader();
		this.escritor = new FileWritter();

		inicializarComponentes();
	}

	private void inicializarComponentes() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 40, 20, 40); // espacio entre componentes

		// --- Categoría Nacional ---
		JLabel lblNacional = new JLabel("Nacional");
		JCheckBox Nacional1 = new JCheckBox("1 - El Mundo");
		JCheckBox Nacional2 = new JCheckBox("2 - El Pais");
		JCheckBox Nacional3 = new JCheckBox("3 - El Nacional");
		nacionalBoxes.add(Nacional1);
		nacionalBoxes.add(Nacional2);
		nacionalBoxes.add(Nacional3);

		JPanel panelNacional = new JPanel(new GridLayout(3, 1));
		panelNacional.add(Nacional1);
		panelNacional.add(Nacional2);
		panelNacional.add(Nacional3);

		// --- Categoría Internacional ---
		JLabel lblInternacional = new JLabel("Internacional");
		JCheckBox Internacional1 = new JCheckBox("4 - El Mundo");
		JCheckBox Internacional2 = new JCheckBox("5 - El Pais");
		JCheckBox Internacional3 = new JCheckBox("6 - BBC");
		internacionalBoxes.add(Internacional1);
		internacionalBoxes.add(Internacional2);
		internacionalBoxes.add(Internacional3);

		JPanel panelInternacional = new JPanel(new GridLayout(3, 1));
		panelInternacional.add(Internacional1);
		panelInternacional.add(Internacional2);
		panelInternacional.add(Internacional3);

		// --- Categoría Economía ---
		JLabel lblEconomia = new JLabel("Economía");
		JCheckBox Economia1 = new JCheckBox("7 - El Pais");
		JCheckBox Economia2 = new JCheckBox("8 - El Diario");
		JCheckBox Economia3 = new JCheckBox("9 - El Economista");
		economiaBoxes.add(Economia1);
		economiaBoxes.add(Economia2);
		economiaBoxes.add(Economia3);

		JPanel panelEconomia = new JPanel(new GridLayout(3, 1));
		panelEconomia.add(Economia1);
		panelEconomia.add(Economia2);
		panelEconomia.add(Economia3);

		// --- Primera fila ---
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(lblNacional, gbc);
		gbc.gridy = 1;
		add(panelNacional, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		add(lblInternacional, gbc);
		gbc.gridy = 1;
		add(panelInternacional, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		add(lblEconomia, gbc);
		gbc.gridy = 1;
		add(panelEconomia, gbc);

		// --- Categoría Deportes ---
		JLabel lblDeportes = new JLabel("Deportes");
		JCheckBox Deportes1 = new JCheckBox("10 - Marca");
		JCheckBox Deportes2 = new JCheckBox("11 - As");
		JCheckBox Deportes3 = new JCheckBox("12 - El Sport");
		deportesBoxes.add(Deportes1);
		deportesBoxes.add(Deportes2);
		deportesBoxes.add(Deportes3);

		JPanel panelDeportes = new JPanel(new GridLayout(3, 1));
		panelDeportes.add(Deportes1);
		panelDeportes.add(Deportes2);
		panelDeportes.add(Deportes3);

		// --- Categoría Videojuegos ---
		JLabel lblVideojuegos = new JLabel("Videojuegos");
		JCheckBox Videojuegos1 = new JCheckBox("13 - Hobbie Consolas");
		JCheckBox Videojuegos2 = new JCheckBox("14 - 3DJuegos");
		JCheckBox Videojuegos3 = new JCheckBox("15 - 3DJuegos Pc");
		videojuegosBoxes.add(Videojuegos1);
		videojuegosBoxes.add(Videojuegos2);
		videojuegosBoxes.add(Videojuegos3);

		JPanel panelVideojuegos = new JPanel(new GridLayout(3, 1));
		panelVideojuegos.add(Videojuegos1);
		panelVideojuegos.add(Videojuegos2);
		panelVideojuegos.add(Videojuegos3);

		// --- Categoría Moda ---
		JLabel lblModa = new JLabel("Moda");
		JCheckBox Moda1 = new JCheckBox("16 - Ocimagazine");
		JCheckBox Moda2 = new JCheckBox("17 - Revista GQ");
		JCheckBox Moda3 = new JCheckBox("18 - Vogue");
		modaBoxes.add(Moda1);
		modaBoxes.add(Moda2);
		modaBoxes.add(Moda3);

		JPanel panelModa = new JPanel(new GridLayout(3, 1));
		panelModa.add(Moda1);
		panelModa.add(Moda2);
		panelModa.add(Moda3);

		// --- Segunda fila ---
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(lblDeportes, gbc);
		gbc.gridy = 3;
		add(panelDeportes, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		add(lblVideojuegos, gbc);
		gbc.gridy = 3;
		add(panelVideojuegos, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		add(lblModa, gbc);
		gbc.gridy = 3;
		add(panelModa, gbc);

		// BOTÓN ENVIAR
		botonGuardarConfig = new JButton("GUARDAR Y MOSTRAR");
		GridBagConstraints gbcBoton = new GridBagConstraints();
		gbcBoton.gridx = 2; // columna 2 --> a la derecha
		gbcBoton.gridy = 5; // fila debajo de tus paneles
		gbcBoton.anchor = GridBagConstraints.SOUTHEAST; // abajo a la derecha
		gbcBoton.insets = new Insets(20, 0, 20, 20); // margen superior, izquierdo, inferior, derecho
		add(botonGuardarConfig, gbcBoton);

		// Acción del botón
		botonGuardarConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User usuarioLogueado = Sesion.getUsuario();
				if (usuarioLogueado != null) {
					if (usuarioLogueado.getVecesLoaded() < 1) {
						boolean actualizado = false;
						for (User u : listaUsu) {
							if (!actualizado && u.getIdUser() == usuarioLogueado.getIdUser()) {
								u.setVecesLoaded(u.getVecesLoaded() + 1);
								actualizado = true;
							}
						}
						if (actualizado) {
							escritor.reescribirUsu(listaUsu);
							listaUsu = carga.cargarUsuarios();
						}
					}
				}

				guardarConfiguracion();
				ventana.mostrarPNoticias(listaUsu, listaPaginas);
			}
		});

	}

	private void guardarConfiguracion() {
		ArrayList<String> seleccionados = new ArrayList<>();
		seleccionados.addAll(getSeleccionados(nacionalBoxes));
		seleccionados.addAll(getSeleccionados(internacionalBoxes));
		seleccionados.addAll(getSeleccionados(economiaBoxes));
		seleccionados.addAll(getSeleccionados(deportesBoxes));
		seleccionados.addAll(getSeleccionados(videojuegosBoxes));
		seleccionados.addAll(getSeleccionados(modaBoxes));

		User userLogged = Sesion.getUsuario();

		if (userLogged == null) {
			JOptionPane.showMessageDialog(this, "No hay usuario logueado");
			return;
		}

		escritor.setConfiguracion(userLogged, listaUsu, seleccionados);
		listaUsu = carga.cargarUsuarios();
	}

	public ArrayList<String> getSeleccionados(ArrayList<JCheckBox> lista) {
		ArrayList<String> seleccionados = new ArrayList<String>();
		for (JCheckBox caja : lista) {
			if (caja.isSelected()) {
				String seleccion = caja.getText().trim();
				String[] partesSelecc = seleccion.split("-");
				seleccionados.add(partesSelecc[0].trim());
			}
		}
		return seleccionados;
	}

}
