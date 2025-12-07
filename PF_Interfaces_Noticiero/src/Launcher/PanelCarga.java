package Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import FileLoader.FileLoader;
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

    private void cargarImagenFondo() {
        try {
            fondo = ImageIO.read(new File("imagenFondo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        }
    }

    private void inicializarComponentes() {
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(200, 550, 500, 20);
        add(progressBar);

        infoCarga = new JLabel("Cargando...");
        infoCarga.setFont(new Font("Arial", Font.PLAIN, 12));
        add(infoCarga);

        Timer timer = new Timer(50, new ActionListener() {
            private int contador = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                contador++;
                progressBar.setValue(contador);

                // Mensajes según progreso
                if (contador <= 35) {
                    infoCarga.setText("Cargando usuarios...");
                    if (contador == 5) {
                        listaUsu = carga.cargarUsuariosConConfiguracion();
                        for (User u : listaUsu) {
                            System.out.println(u);
                        }
                    }
                } else if (contador <= 70) {
                    infoCarga.setText("Cargando configuración...");
                    listaPaginas = carga.cargarConfigPagina();
                } else if (contador == 80) {
                    if (listaUsu == null || listaUsu.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error al cargar a los usuarios, se cerrará el programa...", "Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                    if (listaPaginas == null || listaPaginas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error al cargar la configuración de las páginas, se cerrará el programa...", "Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                } else if (contador <= 100) {
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
