package ventanas;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EnumType;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import config.ConnnectDBDao;
import config.fileOffline;
import daoImpl.ProyectoDAOImpl;
import daoImpl.ProyectoDAOImplEmbeded;
import daoImpl.UsuarioDAOImpl;
import daoImpl.UsuarioDAOImplEmbebded;
import enumClass.userTypeEnum;
import iDao.IProyecto;
import iDao.IUsuario;
import modelo.Proyecto;
import modelo.Usuario;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;

public class VentanaProyecto extends JPanel {
	private JTextField textProyecto;
	private JTextArea textDescripcion;
	private IUsuario user;
	private List<Usuario> scrumMasterList;
	private ArrayList<String> sMNames;
	private JComboBox scrumMasterCB;
	private ComboBoxModel smModel;

	//Aqui esta el segundo objeto para el Product Owner
	private IUsuario user2;
	private List<Usuario> productOwnerList;
	private ArrayList<String> pONames;
	private JComboBox productOwnerCB;
	private ComboBoxModel poModel;
	private JButton btnAdd;
	private JLabel lblExiste;
	private IProyecto gestorProyecto;
	private IProyecto gestorProyectoEmbebed;

	/**
	 * Create the panel.
	 */
	public VentanaProyecto() {

		JLabel lblProyecto = new JLabel("Nombre Proyecto:");

		textProyecto = new JTextField();
		textProyecto.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripcion:");

		textDescripcion = new JTextArea();

		JLabel lblScrumMaster = new JLabel("Scrum Master:");
		ConnnectDBDao con = new ConnnectDBDao();
		if(con.getState()){
			//Aqui cogemos los usuario que son Scrum Master que encuentra en la base de datos remota
			user = new UsuarioDAOImpl();
			
			//Aqui cogemos los usuario que son Scrum Master que encuentra en la base de datos remota
			user2 = new UsuarioDAOImpl();
			
		}
		else {
			user = new UsuarioDAOImplEmbebded();
			user2 = new UsuarioDAOImplEmbebded();
		}
		
		scrumMasterList = user.getUsuariosByRol(userTypeEnum.SCRUM_MASTER);
		productOwnerList = user2.getUsuariosByRol(userTypeEnum.PRODUCT_OWNER);

		sMNames = new ArrayList<String>();
		System.out.println("scrum " + scrumMasterList);
		System.out.println("product " + productOwnerList);
		for (Usuario usuario: scrumMasterList) {
			sMNames.add(usuario.getNombre());
			System.out.println(usuario.getNombre());
		}	

		scrumMasterCB = new JComboBox();

		smModel = new DefaultComboBoxModel(sMNames.toArray());

		scrumMasterCB.setModel(smModel);

		JLabel lblProductOwner = new JLabel("Product Owner:");


		pONames = new ArrayList<String>();
		for (Usuario usuario: productOwnerList) {
			pONames.add(usuario.getNombre());
		}		
		productOwnerCB = new JComboBox();

		poModel = new DefaultComboBoxModel(pONames.toArray());

		productOwnerCB.setModel(poModel);

		//Aqui tenemos el boton que guarda el proyecto en la BD
		btnAdd = new JButton("Crear");

		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ConnnectDBDao con = new ConnnectDBDao();
				if(con.getState()){
					//Insertamos el proyecto en la BBDD
					gestorProyecto = new ProyectoDAOImpl();
				}
				boolean valid = true;
				valid = !validateNomProyecto(textProyecto.getText()) ? false : valid;

				if (valid) {
					String nombreProyecto = textProyecto.getText();
					String descripcion = textDescripcion.getText();
					Usuario scrumMasterNom = scrumMasterList.get(scrumMasterCB.getSelectedIndex());
					Usuario productOwnerNom = productOwnerList.get(productOwnerCB.getSelectedIndex());
					
					System.out.println("[INFO] - Creando proyecto: ");
					System.out.println("[INFO] - Nombre del proyecto: " + nombreProyecto);
					System.out.println("[INFO] - Decripcion: " + descripcion);
					System.out.println("[INFO] - Nombre del Scrum Master: " + scrumMasterNom.getNombre());
					System.out.println("[INFO] - Nombre del Product Owner: " + productOwnerNom.getNombre());

					//Creamos objecto proyecto
					Proyecto proyecto = new Proyecto(nombreProyecto, descripcion, scrumMasterNom, productOwnerNom);

					if(con.getState()){
						//Insertamos el proyecto en la BBDD
						gestorProyecto = new ProyectoDAOImpl();
						gestorProyecto.crearProyecto(proyecto);

						//Actualizamos los usuarios responsables del proyecto
						/*IUsuario gestorUsuario = new UsuarioDAOImpl();
						gestorUsuario.updateUsuario(scrumMasterNom, proyecto);
						gestorUsuario.updateUsuario(productOwnerNom, proyecto);*/
					}
					else {
						fileOffline file = new fileOffline();
						file.addQuery(
								"INSERT INTO `proyecto`(`descripcion`, `nombre_proyecto`, `productOwner_usuario_id`, `scrumMaster_usuario_id`) " +
								"VALUES ('" + descripcion + "','"+nombreProyecto+"',"+productOwnerNom.getUsuario_id()+","+scrumMasterNom.getUsuario_id()+");");
					}
					
					gestorProyectoEmbebed = new ProyectoDAOImplEmbeded();
					gestorProyectoEmbebed.crearProyecto(proyecto);
					
					JOptionPane.showMessageDialog(null,  "Proyecto creado");

					System.out.println("[INFO] - Proyecto creado");
				}
				else {
					JOptionPane.showMessageDialog(null,  "El Proyecto no se ha podido crear");
				} 
			}
		});
			
		lblExiste = new JLabel("Nombre ya existente");
		lblExiste.setForeground(Color.RED);
		lblExiste.setVisible(false);


		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDescripcion)
								.addComponent(lblScrumMaster))
							.addGap(37)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textDescripcion, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
								.addComponent(scrumMasterCB, 0, 298, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblProyecto)
							.addGap(19)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblExiste)
								.addComponent(textProyecto, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblProductOwner)
							.addGap(32)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAdd)
								.addComponent(productOwnerCB, 0, 298, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textProyecto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProyecto))
					.addGap(7)
					.addComponent(lblExiste)
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textDescripcion, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescripcion))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrumMasterCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblScrumMaster))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(productOwnerCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProductOwner))
					.addGap(18)
					.addComponent(btnAdd)
					.addGap(25))
		);
		setLayout(groupLayout);

	}

	//Aqui hacemos que si el nombre de ya existe en la base de datos, no se podra escoger
	public boolean validateNomProyecto(String proyectoNombre) {
		Proyecto proyecto;
		try {
			ConnnectDBDao con = new ConnnectDBDao();
			if(con.getState()){
				proyecto = gestorProyecto.getProyectoByName(proyectoNombre);
				System.out.println("prueba");
			}else {
				proyecto = gestorProyectoEmbebed.getProyectoByName(proyectoNombre);
			}
		} catch (Exception e) {
			System.out.println("no funciona" + e);
			proyecto = null;
		}
		
		if(proyecto != null) {
			lblExiste.setVisible(true);
			return false;
		}
		return true;
	}
}


