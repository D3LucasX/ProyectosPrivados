package launcher;

import javax.swing.*;

import fileLoader.FileLoader;

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
    private JTextField textFieldPasswordVisible; 
    private JCheckBox mostrarContraseñaCheck; 

    private ArrayList<User> listaUsu;
    private ArrayList<Paginas> listaPaginas;
    private FileLoader carga;
    private FileWritter escritor;
    private PanelConfiguracion configPanel;

    private Ventana ventana;
   // private PanelConfiguracion configPanel;

    public PanelLogin(ArrayList<User> listaUsu, ArrayList<Paginas> listaPaginas, Ventana ventana) {
        this.listaUsu = listaUsu;
        this.listaPaginas = listaPaginas;
        this.ventana = ventana;
        this.carga = new FileLoader();
        this.escritor = new FileWritter();
        this.configPanel = new PanelConfiguracion(ventana, listaUsu, listaPaginas);

        inicializarComponentes();
    }
    
    @Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    Graphics2D g2d = (Graphics2D) g;
	    int width = getWidth();
	    int height = getHeight();

	    // Colores del degradado
	    Color rosa = new Color(255, 102, 178);     // rosa
	    Color amarillo = new Color(255, 255, 102); // amarillo

	    // Degradado vertical
	    GradientPaint gp = new GradientPaint(
	        0, 0, rosa,
	        0, height, amarillo
	    );

	    g2d.setPaint(gp);
	    g2d.fillRect(0, 0, width, height);
	}

    private void inicializarComponentes() {
        setLayout(null);

        // TÍTULO
        JLabel labelInicioSesion = new JLabel("INICIO DE SESIÓN");
        labelInicioSesion.setForeground(new Color(89, 89, 89));
        labelInicioSesion.setFont(new Font("Arial", Font.PLAIN, 39));
        labelInicioSesion.setHorizontalAlignment(SwingConstants.CENTER);
        labelInicioSesion.setBounds(10, 42, 385, 39);
        add(labelInicioSesion);

        // LABEL USUARIO
        JLabel nombreLBL = new JLabel("USUARIO");
        nombreLBL.setForeground(new Color(89, 89, 89));
        nombreLBL.setFont(new Font("Arial", Font.PLAIN, 12));
        nombreLBL.setHorizontalAlignment(SwingConstants.RIGHT);
        nombreLBL.setBounds(203, 249, 87, 32);
        add(nombreLBL);

        // TEXTFIELD USUARIO
        textField = new JTextField();
        textField.setBounds(300, 249, 314, 32);
        add(textField);

        // LABEL CONTRASEÑA
        JLabel contraseñaLBL = new JLabel("CONTRASEÑA");
        contraseñaLBL.setForeground(new Color(89, 89, 89));
        contraseñaLBL.setFont(new Font("Arial", Font.PLAIN, 12));
        contraseñaLBL.setHorizontalAlignment(SwingConstants.RIGHT);
        contraseñaLBL.setBounds(203, 292, 87, 32);
        add(contraseñaLBL);

        // PASSWORD FIELD
        passwordField = new JPasswordField();
        passwordField.setBounds(300, 292, 314, 32);
        add(passwordField);
        
        // TEXTFIELD MOSTRAR CONTRASEÑA
        textFieldPasswordVisible = new JTextField();
        textFieldPasswordVisible.setBounds(300, 292, 280, 32);
        textFieldPasswordVisible.setVisible(false);
        add(textFieldPasswordVisible);
        
     // CHECKBOX para mostrar/ocultar contraseña
        mostrarContraseñaCheck = new JCheckBox("Mostrar contraseña");
        mostrarContraseñaCheck.setForeground(new Color(89, 89, 89));
        mostrarContraseñaCheck.setFont(new Font("Arial", Font.PLAIN, 12));
        mostrarContraseñaCheck.setBounds(300, 325, 150, 25); // ajusta posición
        mostrarContraseñaCheck.setOpaque(false); // opcional, transparente
        add(mostrarContraseñaCheck);

        // Acción del checkbox
        mostrarContraseñaCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mostrarContraseñaCheck.isSelected()) {
                    textFieldPasswordVisible.setText(new String(passwordField.getPassword()));
                    textFieldPasswordVisible.setVisible(true);
                    passwordField.setVisible(false);
                } else {
                    passwordField.setText(textFieldPasswordVisible.getText());
                    passwordField.setVisible(true);
                    textFieldPasswordVisible.setVisible(false);
                }
            }
        });

        // BOTÓN ENVIAR
        botonEnviarLoggin = new JButton("ENVIAR");
        botonEnviarLoggin.setBounds(400, 350, 99, 32);
        add(botonEnviarLoggin);

        // BOTON ENVIAR DATOS
        botonEnviarLoggin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                procesarLogin();
            }
        });
    }

    private void procesarLogin() {
        String user = textField.getText().trim();
        String pass;
        if(mostrarContraseñaCheck.isSelected()) {
        	pass = textFieldPasswordVisible.getText().trim();
        }else {
        	pass = new String(passwordField.getPassword()).trim();
        }
        
        User usuarioLogueado = null;
        boolean encontrado = false;

        for (User u : listaUsu) {
            if (u.getNickName().trim().equals(user) && u.getPass().equals(pass)) {
                usuarioLogueado = u;
                //Seteo el usuario logueado en la clase sesion para usar ese usuario en otras clases
                Sesion.setUsuario(usuarioLogueado);
                encontrado = true;
            }
        }

        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Login correcto!", "¡Bienvenido!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Login incorrecto!", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }

        if (usuarioLogueado != null) {
        	if (usuarioLogueado.getIdUser() == 1) {
        		ventana.mostrarPAdmin(ventana, listaUsu, listaPaginas);
        	}else if (usuarioLogueado.getIdUser() != 1 && usuarioLogueado.getVecesLoaded() < 1) {
                // Mostrar panel de configuración en la misma ventana
                ventana.mostrarPanel(configPanel, "config");
            } else {
            	// Mostrar el panel noticias
                ventana.mostrarPNoticias(listaUsu, listaPaginas);
            }
        }
    }
}
