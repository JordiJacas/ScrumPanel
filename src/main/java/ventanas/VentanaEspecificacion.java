package ventanas;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

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
import enumClass.userTypeEnum;
import iDao.iEspecificacion;
import modelo.Especificacion;
import modelo.Proyecto;
import modelo.UsuarioConectado;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Sean Saez Fuller
 * @version 1
 */
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
	private JPanel nuevaTarea;
	private JInternalFrame iNuevaTarea;
	private Proyecto proyecto;
	private VentanaEspecificacion that;
	
	/**
	 * Es la ventana donde podremos seleccionar si insertar o eliminar especificaciones del proyecto.
	 * Dependiendo del rol que tengas.
	 * @param proyecto
	 * @param vGeneral
	 */
	public VentanaEspecificacion(final Proyecto proyecto, final VentanaGeneral vGeneral) {
		con = new ConnnectDBDao();
		if(con.getState()) {
			gestorEspecificacion = new EspecificacionDAOImpl();
		} else {
			gestorEspecificacion = new EspecifiacionDAOImplEmbebded();
		}
		that = this;
		this.proyecto = proyecto;
		especificaciones = gestorEspecificacion.getAllEspecifiacionByProyecto(proyecto);
		
		System.out.println(especificaciones);
				
		//en el panel del norte decimos que lo que añadamos va estar en el centro
		panelNorte = new JPanel();
		panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Creaciond de los botones que van al panel del norte
		btnGuardar = new JButton("Guardar");
		btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				nuevaTarea = new PanelEspecificacion(" ", proyecto, that, false);
				nuevaTarea.setPreferredSize(new Dimension(450, 200));
				iNuevaTarea = new JInternalFrame("Nueva Tarea");
				 
				iNuevaTarea.setLocation(50, 50);
				
				iNuevaTarea.getContentPane().add(nuevaTarea);

				iNuevaTarea.pack();
				
				iNuevaTarea.setResizable(true);
				iNuevaTarea.setClosable(true);
				
				iNuevaTarea.setVisible(true);
				
				vGeneral.dp.add(iNuevaTarea);
				
				iNuevaTarea.toFront();
			}
		});

		btnEliminar = new JButton("Eliminar");
		
		if(UsuarioConectado.getRolUsuario().equals(userTypeEnum.DEVELOPER)) {
			btnEliminar.setEnabled(false);
			btnGuardar.setEnabled(false);
			btnInsertar.setEnabled(false);
		}
		
		//insertamos los botones que van al panel del norte
		panelNorte.add(btnGuardar);
		panelNorte.add(btnInsertar);
		panelNorte.add(btnEliminar);
		
		//Creacion del panel donde va a ir la cantidad de especificaciones en filas
		panelEsp = new JPanel();
		panelEsp.setLayout(new BoxLayout(panelEsp, BoxLayout.Y_AXIS));
		
		scrollPaneEsp = new JScrollPane(panelEsp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneEsp.setPreferredSize(new Dimension(550, 400));
		
		//Esto es para tener el fondo del container de color rojo
		scrollPaneEsp.getViewport().setBackground(Color.RED);
		
		
		this.setLayout(new BorderLayout());
		//Aqui insertamos los paneles en este orden ya que aunque pongas border layout North o Center,
		// se pondran en el orden escrito
		this.add(panelNorte, BorderLayout.NORTH);
		this.add(scrollPaneEsp, BorderLayout.CENTER);
		
		for (Especificacion especificacion : especificaciones) {
			pe = new PanelEspecificacion(especificacion.getDescripcion(), proyecto, that,true);
			pe.setPreferredSize(new Dimension(200, 200));
			panelEsp.add(pe);
			panelEsp.updateUI();
		}
		

	}
	
	/**
	 * Aqui insertamos una nueva tarea
	 * @param descripcion
	 */
	public void addTareaPanel(String descripcion) {
		pe = new PanelEspecificacion(descripcion, this.proyecto, that,true);
		pe.setPreferredSize(new Dimension(200, 200));
		panelEsp.add(pe);
		
		panelEsp.updateUI();
		
		iNuevaTarea.dispose();
	}
}
