package launcher;

import javax.swing.*;

import fileLoader.FileLoader;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.User;
import model.Paginas;
import javax.imageio.ImageIO;

public class PanelCarga extends JPanel {

    private JProgressBar progressBar;
    private JLabel infoCarga;
    private BufferedImage fondo;

    private ArrayList<User> listaUsu;
    private ArrayList<Paginas> listaPaginas;
    private FileLoader carga;

    private Ventana ventana; // Referencia a la ventana para mostrar login después

    public PanelCarga(Ventana ventana) {
        this.ventana = ventana;

        listaUsu = new ArrayList<>();
        listaPaginas = new ArrayList<>();
        carga = new FileLoader();

        setLayout(null);
        cargarImagenFondo();
        inicializarComponentes();
    }
    // Si no encuentra la imágen en el proyecto, devuelvo null, para evitar la traza de error
    private void cargarImagenFondo() {
        File archivo = new File("imagenFondo.jpg");
        if (archivo.exists() && archivo.isFile()) {
            try {
                fondo = ImageIO.read(archivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            fondo = null; // Para que paintComponent use el fondo alternativo
        }
    }
    // Si se encuentra la imágen en el proyecto, la mostrara, si no, mostrara un fondo alternativo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        }else {
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
    }

    private void inicializarComponentes() {
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(200, 550, 500, 20);
        add(progressBar);

        infoCarga = new JLabel("Cargando...");
        infoCarga.setForeground(new Color(89, 89, 89));
        infoCarga.setFont(new Font("Arial", Font.PLAIN, 12));
        add(infoCarga);

        Timer timer = new Timer(35, new ActionListener() {
            private int contador = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                contador++;
                progressBar.setValue(contador);

                // Mensajes según progreso
                if (contador < 35) {
                    infoCarga.setText("Cargando usuarios...");
                }else if(contador == 35){
                	infoCarga.setText("Cargando configuración...");
                } else if (contador == 70) {
                    boolean listaValida = false;
                    listaPaginas = carga.cargarConfigPagina();
                    if (listaPaginas == null) {
                    	System.exit(0);
                    }else {
	                    int numPaginas = listaPaginas.size();
	                    if (numPaginas >= 18) {
	                    	listaValida = true;
	                    }else {
	                    	JOptionPane.showMessageDialog(null, "No hay el minimo de fuentes de noticias necesarias para ejecutar la aplicación", "ERROR", 0);
	                    	System.exit(0);
	                    }
                    }
                    listaUsu = carga.cargarUsuariosConConfiguracion();
                    if (listaUsu == null || !listaValida) {
                    	System.exit(0);
                    }else {
	                    int numUsu = listaUsu.size();
	                    listaValida = false;
	                    boolean usuarioClave = false;
	                    for(User u : listaUsu) {
	                    	if (u.getIdUser() == 1) {
	                    		usuarioClave = true;
	                    	}
	                    }
	                    if (numUsu >= 4 && usuarioClave) {
	                    	listaValida = true;
	                    }else {
	                    	JOptionPane.showMessageDialog(null, "No hay el minimo de usuarios para ejecutar la aplicación", "ERROR", 0);
	                    	System.exit(0);
	                    }
                    }
                    
                } else if (contador == 80) {
                    if (listaUsu == null || listaUsu.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error al cargar a los usuarios, se cerrará el programa...", "Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                    
                    if (listaPaginas == null || listaPaginas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error al cargar la configuración de las páginas, se cerrará el programa...", "Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                    
                } else if (contador == 90) {
                    infoCarga.setText("Finalizando...");
                }

                // Posicionar mensaje sobre la barra
                int xBar = progressBar.getX();
                int yBar = progressBar.getY();
                int anchoBar = progressBar.getWidth();
                int yMensaje = yBar + progressBar.getHeight() + 5;
                int anchoLabel = 150;
                int xMensaje = xBar + (anchoBar - anchoLabel) * contador / 100;
                infoCarga.setBounds(xMensaje, yMensaje, anchoLabel, 20);

                if (contador >= 100) {
                    ((Timer) e.getSource()).stop();

                    // **Restaurar el marco de la ventana**
                    ventana.restaurarMarco();

                    // Mostrar panel de login
                    ventana.mostrarLogin(listaUsu, listaPaginas);
                }
            }
        });
        
        timer.start();
    }

    // Getters por si quieres acceder a las listas desde fuera
    public ArrayList<User> getListaUsu() {
        return listaUsu;
    }

    public ArrayList<Paginas> getListaPaginas() {
        return listaPaginas;
    }
}
