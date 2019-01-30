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
		for (Usuario usuario: scrumMasterList) {
			sMNames.add(usuario.getNombre());
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
					System.out.println("[INFO] - Nombre del proyecto: " + textProyecto);
					System.out.println("[INFO] - Decripcion: " + textDescripcion);
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
								"VALUES ('" + textDescripcion + "','"+textProyecto+"',"+productOwnerNom.getUsuario_id()+","+scrumMasterNom.getUsuario_id()+");");
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
		lblExiste.setVisible(false);


		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProyecto)
								.addComponent(lblDescripcion)
								.addComponent(lblScrumMaster)
								.addComponent(lblExiste)
								.addComponent(lblProductOwner))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnAdd)
								.addComponent(scrumMasterCB, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textProyecto, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
								.addComponent(textDescripcion)
								.addComponent(productOwnerCB, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(64, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(36)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblProyecto)
								.addComponent(textProyecto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDescripcion)
								.addComponent(textDescripcion, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblScrumMaster)
								.addComponent(scrumMasterCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(28)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblProductOwner)
								.addComponent(productOwnerCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
						.addComponent(btnAdd)
						.addGap(30))
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(54, Short.MAX_VALUE)
						.addComponent(lblExiste)
						.addGap(232))
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


