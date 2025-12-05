package Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import fileWritter.FileWritter;
import model.User;
import model.Paginas;

public class PanelLogin extends JPanel {

    private JTextField textField;
    private JPasswordField passwordField;
    private JButton botonEnviarLoggin;

    private ArrayList<User> listaUsu;
    private ArrayList<Paginas> listaPaginas;
    private FileWritter escritor;
    private PanelConfiguracion configPanel;

    private Ventana ventana;
   // private PanelConfiguracion configPanel;

    public PanelLogin(ArrayList<User> listaUsu, ArrayList<Paginas> listaPaginas, Ventana ventana) {
        this.listaUsu = listaUsu;
        this.listaPaginas = listaPaginas;
        this.ventana = ventana;
        this.escritor = new FileWritter();
        this.configPanel = new PanelConfiguracion(ventana, listaUsu, listaPaginas);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(null);
        setBackground(new Color(51, 255, 153));

        // TÍTULO
        JLabel labelInicioSesion = new JLabel("INICIO DE SESIÓN");
        labelInicioSesion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 39));
        labelInicioSesion.setHorizontalAlignment(SwingConstants.CENTER);
        labelInicioSesion.setBounds(10, 42, 385, 39);
        add(labelInicioSesion);

        // LABEL USUARIO
        JLabel nombreLBL = new JLabel("USUARIO");
        nombreLBL.setHorizontalAlignment(SwingConstants.RIGHT);
        nombreLBL.setBounds(203, 249, 87, 32);
        add(nombreLBL);

        // TEXTFIELD USUARIO
        textField = new JTextField();
        textField.setBounds(300, 249, 314, 32);
        add(textField);

        // LABEL CONTRASEÑA
        JLabel contraseñaLBL = new JLabel("CONTRASEÑA");
        contraseñaLBL.setHorizontalAlignment(SwingConstants.RIGHT);
        contraseñaLBL.setBounds(203, 292, 87, 32);
        add(contraseñaLBL);

        // PASSWORD FIELD
        passwordField = new JPasswordField();
        passwordField.setBounds(300, 292, 314, 32);
        add(passwordField);

        // BOTÓN ENVIAR
        botonEnviarLoggin = new JButton("ENVIAR");
        botonEnviarLoggin.setBounds(400, 335, 99, 32);
        add(botonEnviarLoggin);

        // Acción del botón
        botonEnviarLoggin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                procesarLogin();
            }
        });
    }

    private void procesarLogin() {
        String user = textField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();
        User usuarioLogueado = null;
        boolean encontrado = false;

        for (User u : listaUsu) {
            if (u.getNickName().trim().equals(user) && u.getPass().equals(pass)) {
                usuarioLogueado = u;
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Login correcto!", "¡Bienvenido!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Login incorrecto!", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }

        if (usuarioLogueado != null) {
            if (usuarioLogueado.getVecesLoaded() < 1) {
                usuarioLogueado.setVecesLoaded(usuarioLogueado.getVecesLoaded() + 1);
                escritor.reescribirUsu(listaUsu);

                // Mostrar panel de configuración en la misma ventana
                ventana.mostrarPanel(configPanel, "config");
            } else {
                // Aquí puedes mostrar el panel de noticias
            }
        }
    }
}
