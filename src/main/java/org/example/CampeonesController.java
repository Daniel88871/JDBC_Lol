package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * CampeonesController se usa para controlar la información de la tabla campeones.
 * Esta clase tiene unos métodos que te muestra, borra y crea la tabla "campeones" aparte de un método que busca por ID cada campeón.
 *
 * @author Daniel Ruiz - Daniel88871 in GitHub
 * @version 8.0
 */
public class CampeonesController {

	Scanner scanner = new Scanner(System.in);

	private Connection connection;

	/**
	 * Esto nos permite conectarnos a la base de datos del postgresql y con este controlador podremos buscar, borrar, añadir y buscar por ID.
	 */
	public CampeonesController(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Este método recoge toda la información actual que hay en la tabla campeones y la muestra para que el usuario la pueda leer.
	 */
	public void showCampeones() throws SQLException, IOException {
		ResultSet rs = null;
		String sql = "SELECT * FROM campeones";

		try {
			Statement st = connection.createStatement();

			rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Nombre: " + rs.getString("nombre") + " " +
						"Popularidad: " + rs.getString("popularidad") + " " +
						"Porcentaje de victoria: " + rs.getString("porcentaje_de_victoria") + " " +
						"Porcentaje de baneo: " + rs.getString("porcentaje_de_baneo") + " " +
						"Kda" + rs.getString("kda") + " " +
						"Pentas por partida" + rs.getString("porcentaje_de_victoria"));
			}
			rs.close();
			st.close();

		} catch (SQLException e) {
			System.out.println("La tabla campeones no existe");
		}
	}

	/**
	 * Este método lo que hace es borrar la tabla campeones en el caso de que esté creada.
	 */
	public void deleteCampeones() throws SQLException, IOException {
		String sql = "DROP TABLE campeones";

		try {
			Statement st = connection.createStatement();

			st.executeUpdate(sql);

			System.out.println("La tabla campeones se ha borrado");
			st.close();

		} catch (SQLException e) {
			System.out.println("La tabla campeones no existe, por lo tanto no se puede borrar nada");
		}
	}

	/**
	 * Aquí creamos la tabla campeones y la llenamos con sus atributos.
	 */
	public void addCampeones() {
		try {
			Statement st = connection.createStatement();

			st.executeUpdate("CREATE TABLE campeones (" +
					"id_campeones serial," +
					"nombre varchar(600)," +
					"popularidad varchar(600)," +
					"porcentaje_de_victoria varchar(600)," +
					"porcentaje_de_baneo varchar(600)," +
					"kda varchar(600)," +
					"pentas_por_partida varchar(600)," +
					"primary key(id_campeones));");

			System.out.println("Se ha creado la tabla campeones");

			st.close();

		} catch (SQLException e) {
			System.out.println("Error: No se pueden crear la tabla, fijate si ya están creadas");
		}
	}


	/**
	 * Aquí mostramos la información actual de la tabla campeones en base al atributo "id_campeones", y después le preguntará al usuario que ID de campeon quiere introducir,
	 * y una vez se haya introducido una ID, mostrará toda la información de ese campeón en concreto.
	 *
	 */
	public void mostrarIDCampeones(){
		ResultSet rs = null;
		System.out.println("Inserta una ID de campeón: ");
		String ids = scanner.nextLine();

		String sql = "SELECT * FROM campeones WHERE id_campeones = '" + ids + "'";
		try {
			Statement st = connection.createStatement();

			rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println("-----------------------------------------" +
						"\nNombre: " + rs.getString("nombre") +
						"\nPopularidad: " + rs.getString("popularidad") +
						"\nPorcentaje de victoria: " + rs.getString("porcentaje_de_victoria") +
						"\nPorcentaje de baneo: " + rs.getString("porcentaje_de_baneo") +
						"\nKda: " + rs.getString("kda") +
						"\nPentas por partida: " + rs.getString("pentas_por_partida") +
						"\n-----------------------------------------");
			}
			rs.close();
			st.close();

		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}