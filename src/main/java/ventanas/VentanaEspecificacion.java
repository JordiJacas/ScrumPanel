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
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import config.ConnnectDBDao;
import daoImpl.EspecifiacionDAOImplEmbebded;
import daoImpl.EspecificacionDAOImpl;
import iDao.iEspecificacion;
import modelo.Especificacion;
import modelo.Proyecto;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEspecificacion extends JPanel {

	private JButton btnGuardar;
	private JButton btnInsertar;
	private JButton btnEliminar;
	private JPanel panelNorte;
	private JPanel panelEsp;
	private JScrollPane scrollPaneEsp;
	private iEspecificacion gestorEspecificacion;
	private ConnnectDBDao con;
	private List<Especificacion> especificaciones = new ArrayList<Especificacion>();
	private PanelEspecificacion pe;
	private PanelNuevaTarea pnt;
	
	/**
	 * Create the panel.
	 */
	public VentanaEspecificacion(Proyecto proyecto) {
		con = new ConnnectDBDao();
		if(con.getState()) {
			gestorEspecificacion = new EspecificacionDAOImpl();
		} else {
			gestorEspecificacion = new EspecifiacionDAOImplEmbebded();
		}
		
		
<<<<<<< HEAD
		especificaciones = gestorEspecificacion.getAllEspecifiacionByProyecto(proyecto);*/
=======
		List<Especificacion> especificaciones = gestorEspecificacion.getAllEspecifiacionByProyecto(proyecto);
>>>>>>> branch 'sprint3' of https://github.com/JordiJacas/ScrumPanel.git
		
		System.out.println(especificaciones);
		
		//en el panel del norte decimos que lo que a�adamos va estar en el centro
		panelNorte = new JPanel();
		panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Creaciond de los botones que van al panel del norte
		btnGuardar = new JButton("Guardar");
		btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pe = new PanelEspecificacion(" ");
				panelEsp.add(pe);
			}
		});
<<<<<<< HEAD
		btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				pnt = new PanelNuevaTarea();
				panelEsp.add(pnt);
			}
		});
=======
>>>>>>> branch 'sprint3' of https://github.com/JordiJacas/ScrumPanel.git
		btnEliminar = new JButton("Eliminar");
		
		//insertamos los botones que van al panel del norte
		panelNorte.add(btnGuardar);
		panelNorte.add(btnInsertar);
		panelNorte.add(btnEliminar);
		
		//Aqui insertamos los paneles en este orden ya que aunque pongas border layout North o Center,
		// se pondran en el orden escrito
		
		//Creacion del panel donde va a ir la cantidad de especificaciones en filas
		panelEsp = new JPanel();
		this.add(panelNorte, BorderLayout.NORTH);
		scrollPaneEsp = new JScrollPane();
		panelEsp.setLayout(new BoxLayout(panelEsp, BoxLayout.Y_AXIS));
		JPanel internalPanel = new JPanel();
		
		pe = new PanelEspecificacion("TEST");
		internalPanel.add(pe);
		for(Especificacion especificacion: especificaciones) {
		}
		scrollPaneEsp.getViewport().add(internalPanel);
		this.add(scrollPaneEsp, BorderLayout.CENTER);

		
		//Esto es para tener el fondo del container de color rojo
		scrollPaneEsp.getViewport().setBackground(Color.RED);
		
		//el new PanelEspecificacion() tiene que estar dentro del for, si no, no se multiplica
		/*for (int i = 0; i < especifiaciones.size(); i++) {
			pe = new PanelEspecificacion(" ");
			panelEsp.add(pe);
		}*/
		
		

	}
	
	//Ejemplo main para improvisar
/*	public static void main(String[] args) {
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
	}*/
}
