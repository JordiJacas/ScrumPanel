package ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
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

import config.ConnnectDBDao;
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
	private JInternalFrame iMostrarProyectos;
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
	private ArrayList<JMenuItem> menuItems;
	private JButton btnSalir;
	private JPanel login;
	private JPanel nuevoUsuario;
	private JPanel mostrarProyectos;
	private VentanaGeneral that;
	
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
	    setSize(800,800);
	    setVisible(true);
	    
	    that = this;
	    
	    menuItems = new ArrayList<JMenuItem>();
	    
		dp = new JDesktopPane();
		dp.setBackground(Color.WHITE);
		getContentPane().add(dp);
		
		addVentanaLogin();
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 782, 26);
		dp.add(menuBar);
		menuBar.setVisible(false);
		
		mUsuarios = new JMenu("Usuarios");
		menuBar.add(mUsuarios);
		
		sNuevoUsuario = new JMenuItem("Nuevo Usuario");
		menuItems.add(sNuevoUsuario);
		sNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevoUsuario = new VentanaUsuarios();
				iNuevoUsuario = new JInternalFrame("Nuevo Usuario");
				 
				iNuevoUsuario.setLocation(100, 107);
				
				iNuevoUsuario.getContentPane().add(nuevoUsuario);

				iNuevoUsuario.pack();
				
				iNuevoUsuario.setResizable(true);
				iNuevoUsuario.setClosable(true);
				
				iNuevoUsuario.setVisible(true);
				
				dp.add(iNuevoUsuario);
				
			}
		});
		mUsuarios.add(sNuevoUsuario);
		
		sBuscarmodificarUsuario = new JMenuItem("Buscar/Modificar Usuario");
		menuItems.add(sBuscarmodificarUsuario);
		sBuscarmodificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mUsuarios.add(sBuscarmodificarUsuario);
		
		mProyectos = new JMenu("Proyectos");
		menuBar.add(mProyectos);
		
		sCrearProyecto = new JMenuItem("Crear Proyecto");
		menuItems.add(sCrearProyecto);
		sCrearProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JPanel nuevoProyecto = new VentanaProyecto();
				iNuevoProyecto = new JInternalFrame("Nuevo Proyecto");
				iNuevoProyecto.setLocation(89, 71);
				iNuevoProyecto.getContentPane().add(nuevoProyecto);
				iNuevoProyecto.pack();
				iNuevoProyecto.setResizable(true);
				iNuevoProyecto.setClosable(true);
				iNuevoProyecto.setVisible(true);
				dp.add(iNuevoProyecto);

			}
		});
		mProyectos.add(sCrearProyecto);
		
		sBuscarProyecto = new JMenuItem("Buscar Proyectos");
		menuItems.add(sBuscarProyecto);
		sBuscarProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				mostrarProyectos = new VentanaConsultaProyectos(that);
				iMostrarProyectos = new JInternalFrame("Mostrando proyecto");
				 
				iMostrarProyectos.setLocation(100, 107);
				
				iMostrarProyectos.getContentPane().add(mostrarProyectos);

				iMostrarProyectos.pack();
				
				iMostrarProyectos.setResizable(true);
				iMostrarProyectos.setClosable(true);
				
				iMostrarProyectos.setVisible(true);
				
				dp.add(iMostrarProyectos);
			}
		});
		mProyectos.add(sBuscarProyecto);
		
		sBuscarProyecto.setEnabled(false);
		sCrearProyecto.setEnabled(false);
		sBuscarmodificarUsuario.setEnabled(false);
		sNuevoUsuario.setEnabled(false);		
		
		pNombreSalir = new JPanel();
		pNombreSalir.setBounds(0, 26, 782, 26);
		dp.add(pNombreSalir);
		
		lblUsuario = new JLabel("");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSalir.setVisible(false);
				lblUsuario.setText("");
				menuBar.setVisible(false);
				for(JMenuItem menuItem: menuItems) {
					if(menuItem.isEnabled()) {
						menuItem.setEnabled(false);
					}
				}
				addVentanaLogin();
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
		
	}
	
	public void visible(Usuario usuario) {
		
		try {
			iLogin.setClosed(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menuBar.setVisible(true);
		btnSalir.setVisible(true);
		lblUsuario.setText("Usuario: " + UsuarioConectado.getNombreUsuario() + " (" + UsuarioConectado.getRolUsuario() + ")");
		
		if(usuario.getRol_usuario().equals(userTypeEnum.USER_ADMINISTRATOR)){
			sNuevoUsuario.setEnabled(true);
			sBuscarmodificarUsuario.setEnabled(true);
		}
		
		if(usuario.getRol_usuario().equals(userTypeEnum.DEVELOPER)) {
			sBuscarProyecto.setEnabled(true);
		}
		
		if(usuario.getRol_usuario().equals(userTypeEnum.PRODUCT_OWNER)){
			sBuscarProyecto.setEnabled(true);
		}
		
		if(usuario.getRol_usuario().equals(userTypeEnum.SCRUM_MASTER)){
			sCrearProyecto.setEnabled(true);
			sBuscarProyecto.setEnabled(true);
		}		
	}
	
	public void addVentanaLogin() {
		login = new VentanaLogin(this, iLogin);
		iLogin = new JInternalFrame("Login");
		iLogin.setSize(156, 94);
		iLogin.setLocation(133, 58);
		iLogin.getContentPane().add(login);
		iLogin.pack();
		iLogin.setResizable(true);
		iLogin.setClosable(true);
		iLogin.setVisible(true);
		iLogin.setDefaultCloseOperation(HIDE_ON_CLOSE);
		dp.add(iLogin);
		
	}
}



