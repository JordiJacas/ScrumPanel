package ventanas;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import config.ConnnectDBDao;
import config.fileOffline;
import daoImpl.UsuarioDAOImpl;
import daoImpl.UsuarioDAOImplEmbebded;
import enumClass.userTypeEnum;
import iDao.IUsuario;
import modelo.Usuario;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaUsuarios extends JPanel implements FocusListener{
	private JTextField tfNombre;
	private JTextField tfLogin;
	private JPasswordField tfPassword;
	private JPasswordField tfPassword2;
	private JTextField tfEmail;
	private JComboBox comboBox;
	private JLabel lblRepetidas;
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
		tfNombre.addFocusListener(this);
		
		tfLogin = new JTextField();
		tfLogin.setEditable(false);
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
				String text = new BigInteger(28, random).toString(32);
				JOptionPane.showMessageDialog(null, text);
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
				boolean valid = true;
				valid = !validatePasswords() ? false : valid;
				valid = !formNotEmpty() ? false : valid;
				valid = !validateEmail() ? false : valid;
				if (valid) {
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
					
					
					ConnnectDBDao con = new ConnnectDBDao();
					if(con.getState()){
						IUsuario gestorUsuarios = new UsuarioDAOImpl();
						try {
							gestorUsuarios.crearUsuario(new Usuario(login,nombre,password,email,rol,null));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else {
						fileOffline file = new fileOffline();
						file.addQuery(
								"INSERT INTO `usuario`(`contraseña`, `email`, `nombre`, `nombre_usuario`, `rol_usuario`)" + 
								"VALUES  (" + password + ","+ email +","+ nombre + "," + login + ","+rol.ordinal()+");");
					}
					
					IUsuario gestorUsuarios = new UsuarioDAOImplEmbebded();
					try {
						gestorUsuarios.crearUsuario(new Usuario(login,nombre,password,email,rol,null));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
					JOptionPane.showMessageDialog(null,  "Usuario creado");
					
					System.out.println("[INFO] - Usuario creado");
				}
				else {
					JOptionPane.showMessageDialog(null,  "El Usuario no se ha podido crear");
				}
			}
		});
		
		lblRepetidas = new JLabel("Las contrase\u00F1as no coinciden");
		lblRepetidas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRepetidas.setForeground(Color.RED);
		lblRepetidas.setVisible(false);
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
						.addComponent(tfLogin, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(tfEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(tfPassword, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
									.addComponent(tfPassword2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnGenerarPassword)
										.addGap(30))
									.addComponent(lblRepetidas, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(28, Short.MAX_VALUE))
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
								.addComponent(tfPassword2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRepetidas))
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
					.addContainerGap(15, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	
	public boolean validatePasswords() {
		if(!tfPassword.getText().equals(tfPassword2.getText())) {
			lblRepetidas.setVisible(true);
			return false;
		}
		lblRepetidas.setVisible(false);
		return true;
	}
	
	public boolean formNotEmpty() {
		if (tfNombre.getText().isEmpty() || tfLogin.getText().isEmpty() 
				|| tfPassword.getText().isEmpty() || tfPassword2.getText().isEmpty()
				|| tfEmail.getText().isEmpty()
				|| comboBox.getSelectedItem().toString().isEmpty())
			return false;
		return true;
	}
	
	public boolean validateEmail() {
		String email = tfEmail.getText();
		String EMAIL_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		Pattern pattern;
		Matcher matcher;
		
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public void generateLogin() {
		String login, 
				completeName[] = tfNombre.getText().split(" ");
		login = completeName[completeName.length - 2];
		for (int i = 0; i < completeName.length - 2 ; i++) {
			login = completeName[i].charAt(0) + login;
		}
		tfLogin.setText(login.toLowerCase());
	}

	public void focusGained(FocusEvent e) {
	}

	public void focusLost(FocusEvent e) {
		generateLogin();
	}
	
}
