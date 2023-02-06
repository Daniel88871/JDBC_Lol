package org.example;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

/**
 * HechizosController se usa para controlar la información de la tabla hechizos.
 * Esta clase tiene unos métodos que te muestra, borra y crea la tabla "hechizos" aparte de un método que busca por ID cada hechizo.
 *
 * @author Daniel Ruiz - Daniel88871 in GitHub
 * @version 8.0
 */
public class HechizosController {

	Scanner scanner = new Scanner(System.in);

	private Connection connection;

	/**
	 * Esto nos permite conectarnos a la base de datos del postgresql y con este controlador podremos buscar, borrar, añadir y buscar por ID.
	 */
	public HechizosController(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Este método recoge toda la información actual que hay en la tabla hechizos y la muestra para que el usuario la pueda leer.
	 */
	public void showHechizos() throws SQLException, IOException {
		ResultSet rs = null;
		String sql = "SELECT * FROM hechizos";

		try {
			Statement st = connection.createStatement();

			rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Nombre: " + rs.getString("nombre") + " " +
						"Popularidad: " + rs.getString("popularidad") + " " +
						"Porcentaje de victoria: " + rs.getString("porcentaje_de_victoria"));
			}
			rs.close();
			st.close();

		} catch (SQLException e) {
			System.out.println("La tabla hechizos no existe");
		}
	}

	/**
	 * Este método lo que hace es borrar la tabla hechizos en el caso de que esté creada.
	 */
	public void deleteHechizos() throws  SQLException, IOException {
		String sql = "DROP TABLE hechizos";

		try {
			Statement st = connection.createStatement();

			st.executeUpdate(sql);

			System.out.println("La tabla hechizos se ha borrado");
			st.close();

		} catch (SQLException e) {
			System.out.println("La tabla hechizos no existe, por lo tanto no se puede borrar nada");
		}
	}

	/**
	 * Aquí creamos la tabla hechizos y la llenamos con sus atributos.
	 */
	public void addHechizos() {
		try {
			Statement st = connection.createStatement();

			st.executeUpdate("CREATE TABLE hechizos (" +
					"id_hechizos serial," +
					"nombre varchar(500)," +
					"popularidad varchar(500)," +
					"porcentaje_de_victoria varchar(500)," +
					"primary key(id_hechizos));");

			System.out.println("Se ha creado la tabla hechizos");

			st.close();

		} catch (SQLException e) {
			System.out.println("Error: No se pueden crear la tabla, fijate si ya están creadas");
		}
	}

	/**
	 * Aquí mostramos la información actual de la tabla hechizos en base al atributo "id_hechizos", y después le preguntará al usuario que ID de hechizo quiere introducir,
	 * y una vez se haya introducido una ID, mostrará toda la información de ese hechizo en concreto.
	 *
	 */
	public void mostrarIDHechizos(){
		ResultSet rs = null;
		System.out.println("Inserta una ID de hechizo: ");
		String ids = scanner.nextLine();

		String sql = "SELECT * FROM hechizos WHERE id_hechizos = '" + ids + "'";
		try {
			Statement st = connection.createStatement();

			rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println("----------------------------------------" +
						"\nNombre: " + rs.getString("nombre") +
						"\nPopularidad: " + rs.getString("popularidad") +
						"\nPorcentaje de victoria: " + rs.getString("porcentaje_de_victoria") +
						"\n----------------------------------------");
			}
			rs.close();
			st.close();

		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}