package ventanas;

import javax.swing.JPanel;

import java.awt.BorderLayout;
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
import javax.swing.BoxLayout;

public class VentanaEspecificacion extends JPanel {

	private JButton btnGuardar;
	private JButton btnInsertar;
	private JButton btnEliminar;
	private JPanel panelNorte;
	private JPanel panelEsp;
	private JScrollPane scrollPaneEsp;
	PanelEspecificacion pe;
	
	/**
	 * Create the panel.
	 */
	public VentanaEspecificacion() {
		
		//en el panel del norte decimos que lo que añadamos va estar en el centro
		panelNorte = new JPanel();
		panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Creaciond de los botones que van al panel del norte
		btnGuardar = new JButton("Guardar");
		btnInsertar = new JButton("Insertar");
		btnEliminar = new JButton("Eliminar");
		
		//insertamos los botones que van al panel del norte
		panelNorte.add(btnGuardar);
		panelNorte.add(btnInsertar);
		panelNorte.add(btnEliminar);
			
		//Creacion del panel donde va a ir la cantidad de especificaciones en filas
		panelEsp = new JPanel();
		scrollPaneEsp = new JScrollPane(panelEsp , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelEsp.setLayout(new BoxLayout(panelEsp, BoxLayout.Y_AXIS));
		
		//Esto es para tener el fondo del container de color rojo
		scrollPaneEsp.getViewport().setBackground(Color.RED);

		/*Esto sera lo que haran los botones de las especificaciones aunque por ahora probaremos un for para ver que se muestran
		pbEnviar.addActionListener(this);
		ptfLogin.addActionListener(this);
		ppfPassword.addActionListener(this);*/
		
		//el new PanelEspecificacion() tiene que estar dentro del for, si no, no se multiplica
		for (int i = 0; i < 3; i++) {
			pe = new PanelEspecificacion();
			panelEsp.add(pe);
		}
		
		//Aqui insertamos los paneles en este orden ya que aunque pongas border layout North o Center,
		// se pondran en el orden escrito
		this.add(panelNorte, BorderLayout.NORTH);
		this.add(scrollPaneEsp, BorderLayout.CENTER);
	}
	
	//Ejemplo main para improvisar
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setResizable(true);
					frame.setSize(500,800);
					frame.setVisible(true);
					VentanaEspecificacion ve = new VentanaEspecificacion();
					frame.getContentPane().add(ve);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
