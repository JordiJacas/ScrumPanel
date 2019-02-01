package ventanas;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.ScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

public class VentanaEspecificacion extends JPanel {

	private JButton btnGuardar;
	private JButton btnInsertar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private GroupLayout groupLayout;
	PanelEspecificacion pe;
	
	/**
	 * Create the panel.
	 */
	public VentanaEspecificacion() {
		
		btnGuardar = new JButton("Guardar");
		
		btnInsertar = new JButton("Insertar");
		
		btnEliminar = new JButton("Eliminar");
		
		scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		
		scrollPane.getViewport().setBackground(Color.RED);

		pe = new PanelEspecificacion();
		

		Container cont = new Container();
		cont.add(pe);
		
		/*for (int i = 0; i < 3; i++) {	
			
		}*/
		
		
		groupLayout = new GroupLayout(this);
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(113)
					.addComponent(btnGuardar)
					.addGap(6)
					.addComponent(btnInsertar)
					.addGap(5)
					.addComponent(btnEliminar))
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGuardar)
						.addComponent(btnInsertar)
						.addComponent(btnEliminar))
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setSize(800,500);
					frame.setVisible(true);
					VentanaEspecificacion ve = new VentanaEspecificacion();
					frame.add(ve);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
