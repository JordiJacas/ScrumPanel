import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaGeneral extends JFrame {
	protected JDesktopPane dp;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGeneral frame = new VentanaGeneral();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaGeneral() {		
	    setTitle("SCRUM");
	    setSize(300,500);
	    setVisible(true);
	    
		dp = new JDesktopPane();
		getContentPane().add(dp);
	    
	    JPanel p = new VentanaLogin();
	    
		// Se construye el JInternalFrame
		JInternalFrame internal = new JInternalFrame("Login");
		internal.add(p);
	 
		// Es importante darle tamaño -pack()- al JInternalFrame,
		// porque si no, tendrá tamaño 0,0 y no lo veremos.
		internal.pack();
		
		// Por defecto el JInternalFrame no es redimensionable ni
		// tiene el botón de cerrar, así que se lo ponemos.
		internal.setResizable(true);
		internal.setClosable(true);
		
		// Se mete el internal en el JDesktopPane
		dp.add(internal);
		
		// Se visualiza el JInternalFrame 
		internal.setVisible(true);
		
		internal.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ev)
			{
				JOptionPane.showInternalMessageDialog(dp, "keyPressed!", "information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
	     
	}
}
