package Launcher;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame{
	
	public Ventana() {
		setBounds(100, 100, 900, 900); // esto era 100 100 800 300
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		PanelCarga PCarga = new PanelCarga();
		getContentPane().add(PCarga);
	}
}
