package ventanas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JTextField;

import daoImpl.ProyectoDAOImpl;
import daoImpl.ProyectoDAOImplEmbeded;
import daoImpl.UsuarioDAOImpl;
import enumClass.userTypeEnum;
import iDao.IProyecto;
import modelo.Proyecto;
import modelo.UsuarioConectado;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.event.ListSelectionListener;

import config.ConnnectDBDao;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaConsultaProyectos extends JPanel {
	
	private IProyecto gestorProyecto;
	private JTextField tfNombreProyecto;
	private JTextField tfProductOwner;
	private JTextField tfScrumMaster;
	private JTextArea taDescripcion;
	private List<Proyecto> listProyectos;
	private ArrayList<String> arrayProyectos = new ArrayList<String>();
	private Hashtable<String, Proyecto> hashProyectos = new Hashtable<String, Proyecto>();
	private VentanaGeneral vGeneral;
	private JPanel especificaciones;
	private JInternalFrame iEspecificaciones;

	/**
	 * Create the panel.
	 */
	public VentanaConsultaProyectos(final VentanaGeneral vGeneral) {
		
		this.vGeneral = vGeneral;
		
		ConnnectDBDao con = new ConnnectDBDao();
		
		if(con.getState()){
			
			gestorProyecto = new ProyectoDAOImpl();
		}else {
			
			gestorProyecto = new ProyectoDAOImplEmbeded();
		}
		
		if(UsuarioConectado.getRolUsuario().equals(userTypeEnum.SCRUM_MASTER)) {
			listProyectos = gestorProyecto.getAllProyectos();
		}
		if(UsuarioConectado.getRolUsuario().equals(userTypeEnum.DEVELOPER)) {
			listProyectos = gestorProyecto.getProyectosByUser(UsuarioConectado.getUsuario());
		}
		if(UsuarioConectado.getRolUsuario().equals(userTypeEnum.PRODUCT_OWNER)) {
			listProyectos = gestorProyecto.getProyectosByUser(UsuarioConectado.getUsuario());
		}
		
		for (Proyecto proyecto : listProyectos) {
			arrayProyectos.add(proyecto.getNombre_proyecto());
			hashProyectos.put(proyecto.getNombre_proyecto(), proyecto);
		}

		final JList<String> list = new JList(arrayProyectos.toArray());
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Proyecto proyecto = hashProyectos.get(list.getSelectedValue().toString());
				
				tfNombreProyecto.setText(proyecto.getNombre_proyecto());
				tfProductOwner.setText(proyecto.getProductOwner().getNombre());
				tfScrumMaster.setText(proyecto.getScrumMaster().getNombre());
				taDescripcion.setText(proyecto.getDescripcion());
				
			}
		});
		
		
		JLabel lblNombreProyecto = new JLabel("Nombre Proyecto:");
		lblNombreProyecto.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblProductOwner = new JLabel("Product Owner:");
		lblProductOwner.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblScrumMaster = new JLabel("Scrum Master:");
		lblScrumMaster.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		tfNombreProyecto = new JTextField();
		tfNombreProyecto.setColumns(10);
		
		tfProductOwner = new JTextField();
		tfProductOwner.setColumns(10);
		
		tfScrumMaster = new JTextField();
		tfScrumMaster.setColumns(10);
		
		taDescripcion = new JTextArea();
		taDescripcion.setLineWrap(true);
		
		JButton btnMostarEspecificaciones = new JButton("Mostar Especificaciones");
		btnMostarEspecificaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				especificaciones = new VentanaEspecificacion();
				iEspecificaciones = new JInternalFrame("Especificaciones");
				iEspecificaciones.setSize(156, 94);
				iEspecificaciones.setLocation(133, 58);
				iEspecificaciones.getContentPane().add(especificaciones);
				iEspecificaciones.pack();
				iEspecificaciones.setResizable(true);
				iEspecificaciones.setClosable(true);
				iEspecificaciones.setVisible(true);
				vGeneral.dp.add(iEspecificaciones);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(taDescripcion)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombreProyecto)
								.addComponent(lblProductOwner, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblScrumMaster, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(tfScrumMaster, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfProductOwner, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfNombreProyecto, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblDescripcion, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(43, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(170)
					.addComponent(btnMostarEspecificaciones, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(152, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombreProyecto)
								.addComponent(tfNombreProyecto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(63)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblProductOwner, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfProductOwner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(67)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblScrumMaster, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfScrumMaster, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(41)
							.addComponent(lblDescripcion, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(taDescripcion)))
					.addGap(30)
					.addComponent(btnMostarEspecificaciones)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
