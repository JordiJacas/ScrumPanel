package daoTest;

import ventanas.VentanaGeneral;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnnectDBDaoTest connect = new ConnnectDBDaoTest();
		VentanaGeneral vGeneral = new VentanaGeneral(connect.getState());
		vGeneral.setVisible(true);
	}
}
