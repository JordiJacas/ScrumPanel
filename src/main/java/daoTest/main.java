package daoTest;

import config.ConnnectDBDaoRemote;
import ventanas.VentanaGeneral;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnnectDBDaoRemote connect = new ConnnectDBDaoRemote();
		VentanaGeneral vGeneral = new VentanaGeneral(connect.getState());
		vGeneral.setVisible(true);
	}
}
