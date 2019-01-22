package ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaGeneral extends JFrame {
	
	protected JDesktopPane dp;
	private JInternalFrame iLogin;
	private JInternalFrame iNuevoUsuario;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGeneral frame = new VentanaGeneral();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaGeneral() {		
	    setTitle("SCRUM");
	    setSize(800,500);
	    setVisible(true);
	    
		dp = new JDesktopPane();
		getContentPane().add(dp);
	    
		// Se construye el JInternalFrame
		iLogin = new JInternalFrame("Login");
		iLogin.setLocation(12, 13);
		iNuevoUsuario = new JInternalFrame("Nuevo Usuairo");
		iNuevoUsuario.setLocation(12, 13);
		
		//Creamos el panel que contendra el JInternalFrame
		JPanel login = new VentanaLogin(iLogin, iNuevoUsuario);
		JPanel nuevoUsuario = new VentanaUsuarios();
		
		//Añadimos el panel al JInternalFram
		iLogin.getContentPane().add(login);
		iNuevoUsuario.getContentPane().add(nuevoUsuario);
		
		// Es importante darle tamaño -pack()- al JInternalFrame,
		// porque si no, tendrá tamaño 0,0 y no lo veremos.
		iLogin.pack();
		iNuevoUsuario.pack();
		
		// Por defecto el JInternalFrame no es redimensionable ni
		// tiene el botón de cerrar, así que se lo ponemos.
		iLogin.setResizable(true);
		iLogin.setClosable(true);
		iNuevoUsuario.setResizable(true);
		iNuevoUsuario.setClosable(true);
		
		// Se mete el internal en el JDesktopPane
		dp.add(iLogin);
		dp.add(iNuevoUsuario);
		
		// Se visualiza el JInternalFrame 
		iLogin.setVisible(true);
		iNuevoUsuario.setVisible(false);		
	     
	}
}
