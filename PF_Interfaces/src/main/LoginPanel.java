package main;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel{
	 public LoginPanel() {
	        setLayout(null);

	        JLabel usuarioLbl = new JLabel("Usuario:");
	        usuarioLbl.setBounds(50, 50, 100, 25);
	        add(usuarioLbl);

	        JTextField usuarioTxt = new JTextField();
	        usuarioTxt.setBounds(150, 50, 200, 25);
	        add(usuarioTxt);

	        JLabel passLbl = new JLabel("Contraseña:");
	        passLbl.setBounds(50, 100, 100, 25);
	        add(passLbl);

	        JPasswordField passTxt = new JPasswordField();
	        passTxt.setBounds(150, 100, 200, 25);
	        add(passTxt);

	        JButton loginBtn = new JButton("Login");
	        loginBtn.setBounds(150, 150, 100, 30);
	        add(loginBtn);
	    }
}
