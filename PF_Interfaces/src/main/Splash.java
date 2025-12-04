package main;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import FileLoader.FileLoader;
import model.User;
import java.awt.CardLayout;
import java.awt.Rectangle;

public class Splash {

	private JFrame frame;
	private JPanel panelConFondo;
	private JProgressBar progressBar;
	private JLabel infoCarga ;
	private JPanel panelLoggin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash window = new Splash();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Splash() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 900); // esto era 100 100 800 300
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		frame.getContentPane().add(buscarImagen(), "name_5951013624600");
		
		panelLoggin = new JPanel();
		panelLoggin.setBounds(new Rectangle(100, 100, 900, 900));
		frame.getContentPane().add(panelLoggin, "name_5951028023100");
		panelLoggin.setLayout(null);
	}
	
	private Component buscarImagen() {
		BufferedImage fondo = null;
		
		// cargar la imagen
		try {
			 fondo = ImageIO.read(new File("imagenFondo.jpg")); // pilla la imagen como ruta
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image foto = fondo;
		panelConFondo = new JPanel() {

			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(foto, 0, 0, 860, 600, null);
			}
		};
		panelConFondo.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(150, 250, 500, 20);
		panelConFondo.add(progressBar);
		
		// 3. INFO BAJO LA BARRA DE CARGA
        infoCarga = new JLabel("Cargando...");
        int anchoLabel = 150;
        int alturaLabel = 20;
        infoCarga.setFont(new Font("Arial", Font.PLAIN, 12));
        panelConFondo.add(infoCarga);
        
        // Simular carga con Timer
        Timer timer = new Timer(50, new ActionListener() {
            private int contador = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
            	ArrayList<User> listaUsuarios = null;
                contador++;
                progressBar.setValue(contador);
                
                // Actualizar mensaje según el progreso
                if (contador <= 35) {
                	infoCarga.setText("Cargando usuarios...");
                	if (contador == 5) {
	                 	FileLoader cargarUsuarios = new FileLoader();
	                 	listaUsuarios = cargarUsuarios.cargarUsuarios();
                	}
                } else if (contador <= 70) {
                	infoCarga.setText("Cargando configuración...");
                } else if (contador <= 100){
                	infoCarga.setText("Finalizando...");
                }
                
                // Mover el JLabel horizontalmente con la barra
                int xBar = progressBar.getX();
                int yBar = progressBar.getY();
                int anchoBar = progressBar.getWidth();
                int yMensaje = yBar + progressBar.getHeight() + 5;
                // LO POSICIONAMOS PARA QUE EMPIECE JUSTO AL INICIO DE LA BARRA DE CARGA Y NO SE SALGA DE LA BARRA Y
                // PARA QUE VISUALMENTE NO SE VEA QUE SE SALE DE LA BARRA
                int xMensaje = xBar + (anchoBar - anchoLabel) * contador / 100;
                
                
                infoCarga.setBounds(xMensaje, yMensaje, anchoLabel, alturaLabel);
                
                if (contador == 100) {
                	// si esta displayable
                	if (frame.isDisplayable()) {
                		frame.dispose(); // --> Liberamos el espacio
                		frame.setUndecorated(false); // --> y cambiamos lo de undecorated
                		
                		// Lo volvemos a crear
                		frame.setBounds(100, 100, 800, 800);
                		frame.setLocationRelativeTo(null);
                		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                		frame.setVisible(true);
                		panelConFondo.setVisible(false);
                		panelLoggin.setVisible(true);
                		
                		FileLoader carga = new FileLoader();
                		ArrayList<User> listaUsu = carga.cargarUsuarios();
                		
                	}
                }
            }
        });
        timer.start();

		return panelConFondo;
	}
}
