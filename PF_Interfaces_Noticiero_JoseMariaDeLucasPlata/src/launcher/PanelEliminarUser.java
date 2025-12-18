package launcher;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fileWritter.FileWritter;
import model.Paginas;
import model.User;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

import fileLoader.FileLoader;

import java.awt.ComponentOrientation;

public class PanelEliminarUser extends JPanel {
	private FileLoader carga;
	private FileWritter escritor;
	private Ventana ventana;
	private JTable table;
	private JTextField textField;
	private ArrayList<Paginas> listaDePaginas;
	private ArrayList<User> listaUsuarios;

	public PanelEliminarUser(Ventana ventana, ArrayList<User> listaUsuarios, ArrayList<Paginas> listaDePaginas) {
		this.ventana = ventana;
		this.carga = new FileLoader();
		this.escritor = new FileWritter();
		this.listaDePaginas = listaDePaginas;
		this.listaUsuarios = listaUsuarios;

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

		table = new JTable();
		table.setBorder(UIManager.getBorder("SplitPane.border"));
		listaUsuarios = carga.cargarUsuariosConConfiguracion();
		String[] columnas = { "ID", "Nickname" };
		int tamano = listaUsuarios.size();
		String[][] datos = new String[tamano][2];
		for (int i = 0; i < listaUsuarios.size(); i++) {
			User u = listaUsuarios.get(i);
			if(u.getIdUser() != 1) {
				datos[i][0] = Integer.toString(u.getIdUser());
				datos[i][1] = u.getNickName();
			}
		}
		// setModel es necesario para que la tabla sepa que datos tiene que mostrar
		table.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(259, 121, 360, 218);
		add(scrollPane);

		textField = new JTextField();
		textField.setBounds(259, 350, 360, 29);
		add(textField);
		textField.setColumns(10);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idAborrar = textField.getText();
				eliminarUsuario(idAborrar);
				actualizarTabla();
			}
		});
		btnBorrar.setBounds(403, 390, 89, 23);
		add(btnBorrar);

		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.mostrarPAdmin(ventana, listaUsuarios, listaDePaginas);
			}
		});
		btnAtras.setBounds(10, 483, 89, 23);
		add(btnAtras);

		JLabel lblNewLabel = new JLabel("INTRODUCE EL ID DEL USUARIO QUE QUIERES ELIMINAR");
		lblNewLabel.setForeground(new Color(89, 89, 89));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 79, 880, 31);
		add(lblNewLabel);
	}

	private void actualizarTabla() {
		String[] columnas = { "ID", "Nickname" };
		int tamano = listaUsuarios.size();
		String[][] datos = new String[tamano][2];
		for (int i = 0; i < listaUsuarios.size(); i++) {
			User u = listaUsuarios.get(i);
			if(u.getIdUser() != 1) {
				datos[i][0] = Integer.toString(u.getIdUser());
				datos[i][1] = u.getNickName();
			}
		}
		// setModel es necesario para que la tabla sepa que datos tiene que mostrar
		table.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
	}

	private void eliminarUsuario(String idAborrar) {
		if (idAborrar.matches("^[0-9]{1,9}$")) {
			int id;
			try {
				id = Integer.parseInt(idAborrar);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Debe introducir un ID válido (número)", "ERROR", 0);
				return;
			}
			if(id > 1) {

				if (listaUsuarios.size() <= 4) {
					JOptionPane.showMessageDialog(null, "No puede haber menos de 4 usuarios contando con el administrador",
							"ERROR", 0);
					return;
				}
	
				boolean removed = listaUsuarios.removeIf(u -> u.getIdUser() == id);
				if (removed) {
					escritor.reescribirUsu(listaUsuarios); // guarda cambios en el archivo
					JOptionPane.showMessageDialog(null, "Usuario eliminado satisfactoriamente", "Bien", 3);
					actualizarTabla(); // actualiza la JTable
				} else {
					JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con ese ID", "INFO", 1);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Usuario seleccionado inválido", "INFO", 1);
			}
		}else {
			JOptionPane.showMessageDialog(null, "No se admite nada mas que números", "INFO", 1);
		}
		
	}

}
