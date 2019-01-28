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

import daoImpl.UsuarioDAOImpl;
import enumClass.userTypeEnum;
import iDao.IUsuario;
import modelo.Usuario;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class VentanaProyecto extends JPanel {
	private JTextField textProyecto;

	/**
	 * Create the panel.
	 */
	public VentanaProyecto() {
		
		
		JLabel lblProyecto = new JLabel("Nombre Proyecto:");
		
		textProyecto = new JTextField();
		textProyecto.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		
		JTextArea textDescripcion = new JTextArea();
		
		JLabel lblScrumMaster = new JLabel("Scrum Master:");
		
		//
		UsuarioDAOImpl user = new UsuarioDAOImpl();
		List<Usuario> scrumMasterList = user.getUsuariosByRol(userTypeEnum.SCRUM_MASTER);
		ArrayList<String> sMNames = new ArrayList<String>();
		for (Usuario usuario: scrumMasterList) {
			sMNames.add(usuario.getNombre());
		}	

		//
		JComboBox scrumMasterCB = new JComboBox();
		
		ComboBoxModel smModel = new DefaultComboBoxModel(sMNames.toArray());
		
		scrumMasterCB.setModel(smModel);
		
		JLabel lblProductOwner = new JLabel("Product Owner:");
		
		//
		UsuarioDAOImpl user2 = new UsuarioDAOImpl();
		List<Usuario> productOwnerList = user2.getUsuariosByRol(userTypeEnum.PRODUCT_OWNER);
		
		ArrayList<String> pONames = new ArrayList<String>();
		for (Usuario usuario: productOwnerList) {
			pONames.add(usuario.getNombre());
		}		
		JComboBox productOwnerCB = new JComboBox();
		
		//
		ComboBoxModel poModel = new DefaultComboBoxModel(pONames.toArray());
		
		productOwnerCB.setModel(poModel);
		
		
		JButton btnAdd = new JButton("A\u00F1adir");
		
		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JLabel lblExiste = new JLabel("Nombre ya existente");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProyecto)
								.addComponent(lblDescripcion)
								.addComponent(lblScrumMaster)
								.addComponent(lblExiste))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrumMasterCB, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textProyecto, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblProductOwner)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAdd)
								.addComponent(productOwnerCB, 0, 269, Short.MAX_VALUE))))
					.addContainerGap(77, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
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
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(54, Short.MAX_VALUE)
					.addComponent(lblExiste)
					.addGap(232))
		);
		setLayout(groupLayout);

	}
}
