package ventanas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

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
		
		JComboBox ScrumMasterCB = new JComboBox();
		
		JLabel lblProductOwner = new JLabel("Product Owner:");
		
		JComboBox comboBox = new JComboBox();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblProductOwner)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProyecto)
								.addComponent(lblDescripcion)
								.addComponent(lblScrumMaster))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(ScrumMasterCB, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textDescripcion)
								.addComponent(textProyecto, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))))
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
						.addComponent(ScrumMasterCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductOwner)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(91, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
