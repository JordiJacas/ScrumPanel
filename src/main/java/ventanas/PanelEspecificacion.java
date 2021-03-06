package ventanas;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import config.ConnnectDBDao;
import daoImpl.EspecifiacionDAOImplEmbebded;
import daoImpl.EspecificacionDAOImpl;
import enumClass.userTypeEnum;
import iDao.iEspecificacion;
import modelo.Especificacion;
import modelo.Proyecto;
import modelo.UsuarioConectado;

import javax.swing.JTextArea;
/**
 * 
 * @author Sean Saez Fuller
 * @version 1
 */
public class PanelEspecificacion extends JPanel {
	private JButton btnGuardarCambios;
	private JCheckBox chckbxMarcar;
	private JTextArea taEspecificacion;
	private iEspecificacion gestorEspecificacion;
	private ConnnectDBDao con;
	private boolean isReturn;
	
	/**
	 * Aqui creamos las especificaciones donde recogeremos los siguientes parametros para guardarlos a continuacion
	 * Si esta conectado se guardara en la base de datos remota, si no en la embebida
	 * @param descripcion
	 * @param proyecto
	 * @param vEspecificacion
	 * @param isBtn
	 */
	public PanelEspecificacion(final String descripcion, final Proyecto proyecto, final VentanaEspecificacion vEspecificacion, boolean isBtn) {
		con = new ConnnectDBDao();
		
		btnGuardarCambios = new JButton("Guardar cambios");
		if(UsuarioConectado.getRolUsuario().equals(userTypeEnum.DEVELOPER) || isBtn) {
			btnGuardarCambios.setEnabled(false);
		}
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int resp=JOptionPane.showConfirmDialog(null,"Abandonar pantalla para crear tarea?");
				if (JOptionPane.OK_OPTION == resp){
					System.out.println("Selecciona opci�n Afirmativa");					
					
					Especificacion especificacion = new Especificacion(taEspecificacion.getText(), proyecto);
					
					if(con.getState()) {
						gestorEspecificacion = new EspecificacionDAOImpl();
						gestorEspecificacion.createEspecificacion(especificacion);
					}
					else {
						
					}
					
					gestorEspecificacion = new EspecifiacionDAOImplEmbebded();
					gestorEspecificacion.createEspecificacion(especificacion);
					
					vEspecificacion.addTareaPanel(taEspecificacion.getText());
				}
				else{
				    System.out.println("No selecciona una opci�n afirmativa");
				}
				
				
				
			}
		});
		
		chckbxMarcar = new JCheckBox("Marcar");
		
		taEspecificacion = new JTextArea();
		taEspecificacion.setText(descripcion);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(231, Short.MAX_VALUE)
					.addComponent(btnGuardarCambios)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxMarcar)
					.addGap(45))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(taEspecificacion, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardarCambios)
						.addComponent(chckbxMarcar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(taEspecificacion, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
}
