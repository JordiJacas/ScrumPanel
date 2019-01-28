package ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import config.ConnnectDBDaoRemote;
import enumClass.userTypeEnum;
import modelo.Usuario;
import modelo.UsuarioConectado;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

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
	private JPanel pNombreSalir;
	private JLabel lblUsuario;
	private ArrayList<JInternalFrame> internalFrames;
	private JButton btnSalir;
	
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
	    
	    internalFrames = new ArrayList<JInternalFrame>();
	    
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
		iLogin.setSize(156, 94);
		iLogin.setLocation(133, 58);
		internalFrames.add(iLogin);
		
		iNuevoUsuario = new JInternalFrame("Nuevo Usuairo");
		iNuevoUsuario.setLocation(28, 107);
		internalFrames.add(iNuevoUsuario);
		
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
		pNombreSalir = new JPanel();
		pNombreSalir.setBounds(0, 26, 782, 26);
		dp.add(pNombreSalir);
		
		lblUsuario = new JLabel("");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				for(JInternalFrame internalFrame: internalFrames) {
					internalFrame.setVisible(false);
				}
				
				btnSalir.setVisible(false);
				lblUsuario.setText("");
				iLogin.setVisible(true);
			}
		});
		GroupLayout gl_panel = new GroupLayout(pNombreSalir);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 681, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSalir, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
					.addGap(6))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
					.addComponent(btnSalir))
		);
		pNombreSalir.setLayout(gl_panel);
		btnSalir.setVisible(false);
		
		// Se visualiza el JInternalFrame 
		iLogin.setVisible(true);
		iNuevoUsuario.setVisible(false);
	}
	
	public void visible(Usuario usuario) {
		
		iLogin.setVisible(false);
		if(usuario.getRol_usuario().equals(userTypeEnum.USER_ADMINISTRATOR)){
			mUsuarios.setVisible(true);
		}
		
		mProyectos.setVisible(true);
		btnSalir.setVisible(true);
		pNombreSalir.updateUI();
		
		lblUsuario.setText("Usuario: " + UsuarioConectado.getNombreUsuario() + " (" + UsuarioConectado.getRolUsuario() + ")");
		
		
		
	}
}



