package daoTest;

import config.ConnnectDBDao;
import ventanas.VentanaGeneral;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnnectDBDao connect = new ConnnectDBDao();
		VentanaGeneral vGeneral = new VentanaGeneral(connect.getStateString());
		vGeneral.setVisible(true);
	}
}
