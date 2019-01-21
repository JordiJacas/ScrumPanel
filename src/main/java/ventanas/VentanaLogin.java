package ventanas;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import daoImpl.UsuarioDAOImpl;
import iDao.IUsuario;
import modelo.Usuario;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaLogin extends JPanel implements KeyListener{
	private JTextField tfLogin;
	private JPasswordField tfPassword;
	private JInternalFrame iLogin;
	
	/**
	 * Create the panel.
	 */
	public VentanaLogin(JInternalFrame iLogin) {
		this.iLogin = iLogin;
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
	
	private void login() {
		String user = tfLogin.getText();
		String password = tfPassword.getText();
		if (user.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Introduzca el usuario y la contrasena para logearte");
		} else {
			System.out.println("[INFO] - Comprobando datos...");
			
			IUsuario gestorUsuarios = new UsuarioDAOImpl();
			Usuario usuario = gestorUsuarios.getUsuarioByNombreUsuario(user);
			
			if(usuario.getNombre_usuario().equals(user) & usuario.getContraseña().equals(password)) {
				System.out.println("[INFO] - Usuario " + usuario.getNombre() + " Conectado");
			
				//iLogin.setVisible(false);
			}else {
				System.out.println("[INFO] - Nombre de Usuario o Contraseña incorrectos");
			}		
		}
	}
	
	//funciones de keyListener para que enter ejecute login
	public void keyPressed(KeyEvent e) {
		 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	        	login();
	        }
	}

    public void keyReleased(KeyEvent e) {}
    
    public void keyTyped(KeyEvent e) {}
}
