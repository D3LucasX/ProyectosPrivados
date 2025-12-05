package Launcher;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import model.Paginas;
import model.User;


public class PanelCarga extends JPanel {
	private JPanel panelConFondo;
	private JProgressBar progressBar;
	private JLabel infoCarga ;
	private JPanel panelLoggin;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel labelInicioSesion;
	private JLabel nombreLBL;
	private JLabel contraseñaLBL;
	private JButton botonEnviarLoggin;
	private ArrayList<User> listaUsu;
	private ArrayList<Paginas> listaPaginas;
	public PanelCarga () {
		// PANEL LOGGIn
				setBackground(new Color(51, 255, 153));
				setBounds(new Rectangle(100, 100, 900, 900));
				setLayout(null);
	}
	public void panelCarga() {
		// TITULO DEL PANEL
				labelInicioSesion = new JLabel("INICIO DE SESIÓN");
				labelInicioSesion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 39));
				labelInicioSesion.setHorizontalAlignment(SwingConstants.CENTER);
				labelInicioSesion.setBounds(10, 42, 385, 39);
				panelLoggin.add(labelInicioSesion);
				
				// LABEL DEL USER
				nombreLBL = new JLabel("USSER");
				nombreLBL.setHorizontalAlignment(SwingConstants.RIGHT);
				nombreLBL.setBounds(203, 249, 87, 32);
				panelLoggin.add(nombreLBL);
				
				//TEXTFIELD DONDE ESCRIBE EL NICKNAME
				textField = new JTextField();
				textField.setBounds(300, 249, 314, 32);
				panelLoggin.add(textField);
				textField.setColumns(10);
				
				//LABEL DE LA CONTRASEÑA
				contraseñaLBL = new JLabel("CONTRASEÑA");
				contraseñaLBL.setHorizontalAlignment(SwingConstants.RIGHT);
				contraseñaLBL.setBounds(203, 292, 87, 32);
				panelLoggin.add(contraseñaLBL);
				
				// PASSWD FIELD PARA ESCRIBIR LA CONTRASEÑA
				passwordField = new JPasswordField();
				passwordField.setBounds(300, 292, 314, 32);
				panelLoggin.add(passwordField);
				
				// BOTÓN DE ENVIAR PARA COMPROBAR LOS DATOS DEL LOGGIN
				botonEnviarLoggin = new JButton("ENVIAR");
				botonEnviarLoggin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						User usuarioLogueado = null;
						boolean encontrado = false;
						String user = textField.getText().trim();
						String pass = new String(passwordField.getPassword()).trim();
						for (User usu : listaUsu) {
							if (usu.getNickName().trim().equals(user) && usu.getPass().equals(pass)) {
								usuarioLogueado = usu;
								encontrado = true;
							}
						}
						if(encontrado) {
							JOptionPane.showMessageDialog(null, "Login correcto!", "¡Bienvenido!", 3);
						}else {
							JOptionPane.showMessageDialog(null, "Login incorrecto!", "¡ERROR!", 0);
						}
						// SI EL USUARIO ES LA PRIMERA VEZ QUE SE LOGGUEA, AUMENTO EL ATRIBUTO VECESLOADED PARA QUE A LA SIGUIENTE NO LE MANDE AL MENU DE CONFIG
						
						if(usuarioLogueado != null) {
							if (usuarioLogueado.getVecesLoaded() < 1) {
								usuarioLogueado.setVecesLoaded(usuarioLogueado.getVecesLoaded() + 1);
								escritor.reescribirUsu(listaUsu);
								//VUELVO A CARGAR LOS USUARIOS YA MODIFICADOS
								listaUsu = carga.cargarUsuarios();
								panelLoggin.setVisible(false);
								configPanel.panelConfiguracion.setVisible(true);
							}else {
								// Le mando a la pantalla donde le muestran las noticias
							}
						}
					}
				});
	}
	private Component splashPanel() {
		BufferedImage fondo = null;
		
		// CARGAR IMAGEN
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
                contador++;
                progressBar.setValue(contador);
                
                // Actualizar mensaje según el progreso
                if (contador <= 35) {
                	infoCarga.setText("Cargando usuarios...");
                	if (contador == 5) {
	                 	listaUsu = carga.cargarUsuarios();
	                 	for (User usu:listaUsu) {
	                 		System.out.println(usu.toString());
	                 	}
                	}
                 }else if (contador <= 70) {
                 	infoCarga.setText("Cargando configuración...");
                 	listaPaginas = carga.cargarConfigPagina();
                 }else if(contador == 80) {
                 	if ( listaUsu == null || listaUsu.isEmpty()) {
                 		JOptionPane.showMessageDialog(null, "Error al cargar a los usuarios, se cerrara el programa...", "Error", 0);;
                 		System.exit(0);
                 	}
                 	if (listaPaginas == null || listaPaginas.isEmpty()) {
                 		JOptionPane.showMessageDialog(null, "Error al cargar la configuracion de las páginas, se cerrara el programa...", "Error", 0);;
                 		System.exit(0);
                 	}
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
                	liberarPanel();
                	panelConFondo.setVisible(false);
            		panelLoggin.setVisible(true);    
                }
            }
        });
        timer.start();

		return panelConFondo;
	}
	
	public void liberarPanel() {
		try {
			if (isDisplayable()) {
				this.getParent().dis; // --> Liberamos el espacio
				Ventana.setUndecorated(false); // --> y cambiamos lo de undecorated
				
				// Lo volvemos a crear
				frame.setBounds(100, 100, 800, 800);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
