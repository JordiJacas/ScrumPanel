package ventanas;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import config.ConnnectDBDao;
import daoImpl.UsuarioDAOImpl;
import daoImpl.UsuarioDAOImplEmbebded;
import enumClass.userTypeEnum;
import iDao.IUsuario;
import modelo.Usuario;
import modelo.UsuarioConectado;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
2  * @author Jaime Pena Martin
5  * @version 1
6  */

public class VentanaLogin extends JPanel implements KeyListener{
	private JTextField tfLogin;
	private JPasswordField tfPassword;
	private JInternalFrame iLogin;
	private VentanaGeneral VentanaGeneral;
	
	/**
	 * Ventana donde te puedes loguear
	 * @param VentanaGeneral la ventana principal
	 * @param iLogin es el Internal Frame donde todo se vera en la VentanaGeneral
	 */
	public VentanaLogin(VentanaGeneral VentanaGeneral, JInternalFrame iLogin) {
		
		this.iLogin = iLogin;
		this.VentanaGeneral = VentanaGeneral;
		
		setFocusable(true);
		addKeyListener(this);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		tfLogin = new JTextField();
		tfLogin.setColumns(10);
		tfLogin.addKeyListener(this);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		tfPassword = new JPasswordField();
		tfPassword.setColumns(10);
		tfPassword.addKeyListener(this);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(tfLogin, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addComponent(btnLogin)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	
	/**
	 * Metodo que recoge los datos del text Field y busca en la base de datos si existen los datos introducidos
	 * en la columna correcta de la base de datos.
	 */
	private void login() {
		String user = tfLogin.getText();
		String password = tfPassword.getText();
		
		
		
		/*Descomentar para hacer la prueba OFFLINE
		
		Usuario usuario = new Usuario();
		usuario.setNombre("Admin");
		usuario.setNombre_usuario("admin");
		usuario.setPassword("admin");
		usuario.setEmail("admin@gmail.com");
		usuario.setRol_usuario(userTypeEnum.USER_ADMINISTRATOR);
		
		if (user.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Introduzca el usuario y la contrasena para logearte");
		} else {
			if(usuario.getNombre_usuario().equals(user) & usuario.getContraseña().equals(password)) {
				JOptionPane.showMessageDialog(null,  "Usuario " + usuario.getNombre() + " Conectado");
				iLogin.setVisible(false);
				if(usuario.getRol_usuario().equals(userTypeEnum.USER_ADMINISTRATOR)) {
					iNuevoUsuario.setVisible(true);
				}
			}else {
				JOptionPane.showMessageDialog(null,  "Nombre de Usuario o Contraseña incorrectos");
			}
		}*/
		
		
		
		//Descomentar para hacer la prueba ONLINE
		
		if (user.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Introduzca el usuario y la contrasena para logearte");
		} else {
			System.out.println("[INFO] - Comprobando datos...");
			IUsuario gestorUsuarios;
			ConnnectDBDao con = new ConnnectDBDao();
			if(con.getState()){
				gestorUsuarios = new UsuarioDAOImpl();
			}
			else {
				gestorUsuarios = new UsuarioDAOImplEmbebded();
			}
			
			Usuario usuario = gestorUsuarios.getUsuarioByNombreUsuario(user);
			System.out.println(usuario);
			if(usuario.getNombre_usuario().equals(user) & usuario.getPassword().equals(password)) {
				UsuarioConectado.setUsuario(usuario);
				JOptionPane.showMessageDialog(null,  "Usuario " + usuario.getNombre() + " Conectado");
				
				VentanaGeneral.visible(usuario);
		
			}else {
				JOptionPane.showMessageDialog(null,  "Nombre de Usuario o Contraseña incorrectos");
				
			}
		}
	}
	
	/**
	 * funciones de keyListener para que enter ejecute login pulsando ENTER
	 * @param KeyEvent Evento que hace que pueda hacer la siguiente funcion
	 */
	public void keyPressed(KeyEvent e) {
		 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	        	login();
	        }
	}

    public void keyReleased(KeyEvent e) {}
    
    public void keyTyped(KeyEvent e) {}
}
