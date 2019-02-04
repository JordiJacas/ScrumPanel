package ventanas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

public class PanelEspecificacion extends JPanel {
	private JButton btnGuardarCambios;
	private JCheckBox chckbxMarcar;
	private JTextArea taEspecificacion;
	
	
	/**
	 * Create the panel.
	 */
	public PanelEspecificacion(String descripcion) {
		
		btnGuardarCambios = new JButton("Guardar cambios");
		
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
