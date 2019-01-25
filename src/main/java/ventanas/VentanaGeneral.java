package ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daoTest.ConnnectDBDaoTest;
import enumClass.userTypeEnum;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;

public class VentanaGeneral extends JFrame {
	
	protected JDesktopPane dp;
	private JInternalFrame iLogin;
	private JInternalFrame iNuevoUsuario;
	private JInternalFrame iNuevoProyecto;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mUsuarios;
	private JMenu mProyectos;
	private JMenuItem sNuevoUsuario;
	private JMenuItem sBuscarmodificarUsuario;
	private JMenuItem sCrearProyecto;
	private JMenuItem sBuscarProyecto;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGeneral frame = new VentanaGeneral("Online");
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
	public VentanaGeneral(String state) {		
	    setTitle("SCRUM - " + state);
	    setSize(800,500);
	    setVisible(true);
	    
		dp = new JDesktopPane();
		dp.setBackground(Color.WHITE);
		getContentPane().add(dp);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 782, 26);
		dp.add(menuBar);
		
		mUsuarios = new JMenu("Usuarios");
		menuBar.add(mUsuarios);
		
		sNuevoUsuario = new JMenuItem("Nuevo Usuario");
		sNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iNuevoUsuario.setVisible(true);
			}
		});
		mUsuarios.add(sNuevoUsuario);
		
		sBuscarmodificarUsuario = new JMenuItem("Buscar/Modificar Usuario");
		sBuscarmodificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iNuevoUsuario.setVisible(true);
			}
		});
		mUsuarios.add(sBuscarmodificarUsuario);
		mProyectos = new JMenu("Proyectos");
		menuBar.add(mProyectos);
		
		sCrearProyecto = new JMenuItem("Crear Proyecto");
		sCrearProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iNuevoProyecto.setVisible(true);
			}
		});
		mProyectos.add(sCrearProyecto);
		
		JMenuItem sBuscarProyecto = new JMenuItem("Buscar Proyectos");
		sBuscarProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iNuevoProyecto.setVisible(true);
			}
		});
		mProyectos.add(sBuscarProyecto);
		
		mUsuarios.setVisible(false);
		mProyectos.setVisible(false);
	
		// Se construye el JInternalFrame
		iLogin = new JInternalFrame("Login");
		iLogin.setLocation(133, 58);
		iNuevoUsuario = new JInternalFrame("Nuevo Usuairo");
		iNuevoUsuario.setLocation(89, 71);
		
		iNuevoProyecto = new JInternalFrame("Nuevo Proyecto");
		iNuevoProyecto.setLocation(89, 71);
		
		//Creamos el panel que contendra el JInternalFrame
		JPanel login = new VentanaLogin(this, iLogin);
		JPanel nuevoUsuario = new VentanaUsuarios();
		
		JPanel nuevoProyecto = new VentanaProyecto();
		
		//Añadimos el panel al JInternalFram
		iLogin.getContentPane().add(login);
		iNuevoUsuario.getContentPane().add(nuevoUsuario);
		
		iNuevoProyecto.getContentPane().add(nuevoProyecto);
		
		// Es importante darle tamaño -pack()- al JInternalFrame,
		// porque si no, tendrá tamaño 0,0 y no lo veremos.
		iLogin.pack();
		iNuevoUsuario.pack();
		iNuevoProyecto.pack();
		
		// Por defecto el JInternalFrame no es redimensionable ni
		// tiene el botón de cerrar, así que se lo ponemos.
		iLogin.setResizable(true);
		iLogin.setClosable(true);
		iNuevoUsuario.setResizable(true);
		iNuevoUsuario.setClosable(true);
		
		iNuevoProyecto.setResizable(true);
		iNuevoProyecto.setClosable(true);
		
		// Se mete el internal en el JDesktopPane
		dp.add(iLogin);
		dp.add(iNuevoUsuario);
		
		dp.add(iNuevoProyecto);
		
		// Se visualiza el JInternalFrame 
		iLogin.setVisible(true);
		iNuevoUsuario.setVisible(false);
	}
	
	public VentanaGeneral() {}
	
	public void visible(userTypeEnum rol) {
		iLogin.setVisible(false);
		if(rol.equals(userTypeEnum.USER_ADMINISTRATOR)){
			mUsuarios.setVisible(true);
		}
		mProyectos.setVisible(true);
		
	}
}



