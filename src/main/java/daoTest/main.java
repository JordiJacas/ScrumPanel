package daoTest;

import config.ConnnectDBDao;
import ventanas.VentanaGeneral;

/**
 * Clase main del proyecto, se encarga de iniciar el programa
 * @author jaimepm
 *
 */
public class main {

	/**
	 * main, se conecta a la bbdd y arranca la ventana general
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnnectDBDao connect = new ConnnectDBDao();
		VentanaGeneral vGeneral = new VentanaGeneral(connect.getStateString());
		vGeneral.setVisible(true);
	}
}
