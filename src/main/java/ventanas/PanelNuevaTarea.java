package ventanas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class PanelNuevaTarea extends JPanel {

	private JLabel lblDescripcion;
	private JTextArea textDescripcion;
	private JButton btnInsertar;
	
	/**
	 * Create the panel.
	 */
	public PanelNuevaTarea() {
		
		lblDescripcion = new JLabel("Descripcion");
		
		textDescripcion = new JTextArea();
		
		btnInsertar = new JButton("Insertar");
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDescripcion)
					.addGap(18)
					.addComponent(textDescripcion, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(333, Short.MAX_VALUE)
					.addComponent(btnInsertar)
					.addGap(46))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescripcion)
						.addComponent(textDescripcion, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnInsertar)
					.addContainerGap(112, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
