package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


/**
 * La clase Main es la clase principal de nuestra práctica, se encargará de iniciar la conexión a nuestra BBDD y mostrará el
 * menu principal, con el que el usuario interactuará con las tablas.
 *
 * @author Daniel Ruiz - Daniel88871 in GitHub
 * @version 8.0
 */
public class Main {

	/**
	 * Un constructor main vacio
	 */
	public Main() {}

	/**
	 * Aquí es donde se ejecuta el programa.
	 * @param args Esto son argumentos que le pasas al main
	 * @throws IOException Si no puede ejecutar el método, saltará un error.
	 * @throws SQLException Si no puede mostrar las casillas, saltará un error.
	 * @throws ParseException Otros errores.
	 */
	public static void main(String[] args) throws IOException, SQLException, ParseException {
		Menu menu = new Menu();

		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
		Connection c = connectionFactory.connect();

		HechizosController hechizosController = new HechizosController(c);
		CampeonesController campeonesController = new CampeonesController(c);
		ObjetosController objetosController = new ObjetosController(c);

		int option = menu.mainMenu();
		while (option > 0 && option < 17) {
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
					hechizosController.addHechizos();
					break;

				case 6:
					objetosController.addObjetos();
					break;

				case 7:
					campeonesController.deleteCampeones();
					break;

				case 8:
					hechizosController.deleteHechizos();
					break;

				case 9:
					objetosController.deleteObjetos();
					break;

				case 10:
					campeonesController.mostrarIDCampeones();
					break;

				case 11:
					hechizosController.mostrarIDHechizos();
					break;

				case 12:
					objetosController.mostrarIDObjetos();
					break;

				case 13:
					CSVCampeones(c);
					break;

				case 14:
					CSVHechizos(c);
					break;

				case 15:
					CSVObjetos(c);
					break;

				case 16:
					System.exit(0);
			}
			option = menu.mainMenu();

		}
	}

	/**
	 * Este método crea una lista de la data de un archivo CSV que leeremos con BufferedReader.
	 * Una vez hecho esto, separaremos la línea haciendo un split, y añadiremos el resultado de la información en
	 * la lista que hemos creado. Una vez hecho esto, lo recorrerá con un for, y hará los INSERTS del archivo CSV.
	 * @param connection le pasas la conexión de la BBDD para que pueda ejecutar los métodos
	 *
	 */
	public static void CSVCampeones(Connection connection) {
		List < String[]>csv = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/campeones.csv"));
			String line;

			while ((line = br.readLine()) != null) {
				String[] data = line.split("\",\"");
				csv.add(data);
			}

			for (String[] data : csv) {
				try {
					Statement st = connection.createStatement();
					String nombre = data[0];
					String popularidad = data[1];
					String porcentaje_de_victoria = data[2];
					String porcentaje_de_baneo = data[3];
					String kda = data[4];
					String pentas_por_partida = data[5];

					String sql = "INSERT INTO campeones " + "(nombre,popularidad,porcentaje_de_victoria,porcentaje_de_baneo,kda,pentas_por_partida) VALUES(?,?,?,?,?,?)";

					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, nombre);
					pst.setString(2, popularidad);
					pst.setString(3, porcentaje_de_victoria);
					pst.setString(4, porcentaje_de_baneo);
					pst.setString(5, kda);
					pst.setString(6, pentas_por_partida);
					pst.executeUpdate();
					pst.close();
				} catch (SQLException e) {
					System.out.println("No se ha rellenado la tabla porque ya está rellenada");
				}
			}
			System.out.println("Se ha rellenado la tabla campeones");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este método crea una lista de la data de un archivo CSV que leeremos con BufferedReader.
	 * Una vez hecho esto, separaremos la línea haciendo un split, y añadiremos el resultado de la información en
	 * la lista que hemos creado. Una vez hecho esto, lo recorrerá con un for, y hará los INSERTS del archivo CSV.
	 * @param connection le pasas la conexión de la BBDD para que pueda ejecutar los métodos
	 *
	 */
	public static void CSVHechizos(Connection connection) {
		List<String[]> csv = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/hechizos.csv"));
			String line;

			while ((line = br.readLine()) != null) {
				String[] data = line.split("\",\"");
				csv.add(data);
			}

			for (String[] data : csv) {
				try {
					Statement st = connection.createStatement();
					String nombre = data[0];
					String popularidad = data[1];
					String porcentaje_de_victoria = data[2];

					String sql = "INSERT INTO hechizos " + "(nombre,popularidad,porcentaje_de_victoria) VALUES(?,?,?)";

					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, nombre);
					pst.setString(2, popularidad);
					pst.setString(3, porcentaje_de_victoria);
					pst.executeUpdate();
					pst.close();
				} catch (SQLException e) {
					System.out.println("No se ha rellenado la tabla porque ya está rellenada");
				}
			}
			System.out.println("Se ha rellenado la tabla hechizos");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este método crea una lista de la data de un archivo CSV que leeremos con BufferedReader.
	 * Una vez hecho esto, separaremos la línea haciendo un split, y añadiremos el resultado de la información en
	 * la lista que hemos creado. Una vez hecho esto, lo recorrerá con un for, y hará los INSERTS del archivo CSV.
	 * @param connection le pasas la conexión de la BBDD para que pueda ejecutar los métodos
	 *
	 */
	public static void CSVObjetos(Connection connection) {
		List<String[]> csv = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/objetos.csv"));
			String line;

			while ((line = br.readLine()) != null) {
				String[] data = line.split("\",\"");
				csv.add(data);
			}

			for (String[] data : csv) {
				try {
					Statement st = connection.createStatement();
					String popularidad = data[0];
					String porcentaje_de_victoria = data[1];

					String sql = "INSERT INTO objetos " + "(popularidad,porcentaje_de_victoria) VALUES(?,?)";

					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, popularidad);
					pst.setString(2, porcentaje_de_victoria);
					pst.executeUpdate();
					pst.close();
				} catch (SQLException e) {
					System.out.println("No se ha rellenado la tabla porque ya está rellenada");
				}
			}
			System.out.println("Se ha rellenado la tabla objetos");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}