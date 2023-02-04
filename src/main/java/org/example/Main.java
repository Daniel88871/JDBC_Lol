package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws IOException, SQLException, ParseException {
		Menu menu = new Menu();
		
		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
		Connection c = connectionFactory.connect();

		HechizosController hechizosController = new HechizosController(c);
		CampeonesController campeonesController = new CampeonesController(c);
		ObjetosController objetosController = new ObjetosController(c);

		int option = menu.mainMenu();
		while (option > 0 && option < 11) {
			switch (option) {
				case 1:
					campeonesController.showCampeones();
					break;

				case 2:
					hechizosController.showHechizos();
					break;

				case 3:
					objetosController.showObjetos();
					break;

				case 4:
					campeonesController.addCampeones();
					break;

				case 5:
					campeonesController.deleteCampeones();
					break;

				case 6:
					hechizosController.addHechizos();
					break;

				case 7:
					hechizosController.deleteHechizos();
					break;

				case 8:
					objetosController.addObjetos();
					break;

				case 9:
					objetosController.deleteObjetos();
					break;

				case 10:
					System.exit(0);
			}
			option = menu.mainMenu();

		}
	}
}