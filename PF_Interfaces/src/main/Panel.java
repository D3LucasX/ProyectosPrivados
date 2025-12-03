package main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import FileLoader.FileLoader;
import model.User;

public class Panel extends JPanel {

    private JProgressBar progressBar;
    private JLabel infoCarga;
    private VentanaAplicacion ventana;
	private Image imagen;

	public Panel(VentanaAplicacion ventana) {
		this.ventana=ventana;
		setLayout(null);

		// 1. PONER LA IMÁGEN EN EL FONDO
		try {
			imagen = ImageIO.read(new File("imagenFondo.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 2. BARRA PROGRESO
		progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(150, 400-90, 500, 20);
        add(progressBar);
        
        // 3. INFO BAJO LA BARRA DE CARGA
        infoCarga = new JLabel("Cargando...");
        int anchoLabel = 150;
        int alturaLabel = 20;
        infoCarga.setFont(new Font("Arial", Font.PLAIN, 12));
        add(infoCarga);
        
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

                 	FileLoader cargarUsuarios = new FileLoader();
                 	listaUsuarios = cargarUsuarios.cargarUsuarios();
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
                int xMensaje = xBar + (anchoBar - anchoLabel + 90) * contador / 100;
                
                
                infoCarga.setBounds(xMensaje, yMensaje, anchoLabel, alturaLabel);

                if (contador >= 100) {
                    ((Timer) e.getSource()).stop();
                    // Cuando termine, mostrar login
                    ventana.mostrarLogin();
                }
            }
        });
        timer.start();
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imagen != null) {
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
		}
	}
}