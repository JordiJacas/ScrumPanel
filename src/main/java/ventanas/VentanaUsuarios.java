package ventanas;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import daoImpl.UsuarioDAOImpl;
import enumClass.userTypeEnum;
import iDao.IUsuario;
import modelo.Usuario;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.awt.event.ActionEvent;

public class VentanaUsuarios extends JPanel {
	private JTextField tfNombre;
	private JTextField tfLogin;
	private JPasswordField tfPassword;
	private JPasswordField tfPassword2;
	private JTextField tfEmail;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public VentanaUsuarios() {
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblLogin = new JLabel("Login generado:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblPassword2 = new JLabel("Repita password:");
		lblPassword2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		
		tfLogin = new JTextField();
		tfLogin.setColumns(10);
		
		tfPassword = new JPasswordField();
		tfPassword.setColumns(10);
		
		tfPassword2 = new JPasswordField();
		tfPassword2.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		
		JButton btnGenerarPassword = new JButton("Generar password");
		btnGenerarPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SecureRandom random = new SecureRandom();
				 String text = new BigInteger(32, random).toString(32);
				 tfPassword.setText(text);
				 tfPassword2.setText(text);
				//System.out.println("[INFO] - Generando Contraseña aleatoria");
				//System.out.println("[INFO] - Contraseña generada");
			}
		});
		
		comboBox = new JComboBox();
		//Cambiar el Enum del combobox para que aparezcan los roles
		comboBox.setModel(new DefaultComboBoxModel(userTypeEnum.values()));
		
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (formValid()) {
				String nombre = tfNombre.getText();
				String login = tfLogin.getText();
				String password = tfPassword.getText();
				String email = tfEmail.getText();
				userTypeEnum rol = userTypeEnum.valueOf(comboBox.getSelectedItem().toString());
				
				System.out.println("[INFO] - Creando usuario: ");
				System.out.println("[INFO] - Nombre: " + nombre);
				System.out.println("[INFO] - Login: " + login);
				System.out.println("[INFO] - password: " + password);
				System.out.println("[INFO] - Email: " + email);
				System.out.println("[INFO] - Rol: " + rol);
				
				IUsuario gestorUsuario = new UsuarioDAOImpl();
				gestorUsuario.crearUsuario(new Usuario(login,nombre,password,email,rol,null));
				
				JOptionPane.showMessageDialog(null,  "Usuario creado");
				
				System.out.println("[INFO] - Usuario creado");
				}
				else {
					JOptionPane.showMessageDialog(null,  "El Usuario no se ha podido crear");
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRol, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tfPassword2, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfLogin, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(tfEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addGap(26)
								.addComponent(btnGenerarPassword))))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnGenerarPassword, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassword2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfPassword2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRol, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnGuardar)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	
	
	public boolean formValid() {
		if (tfNombre.getText().isEmpty() || tfLogin.getText().isEmpty() 
				|| tfPassword.getText().isEmpty() || tfPassword2.getText().isEmpty()
				|| !tfPassword.getText().equals(tfPassword2.getText()) || tfEmail.getText().isEmpty()
				|| comboBox.getSelectedItem().toString().isEmpty())
			return false;
		return true;
	}
	
}
