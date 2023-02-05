package org.example;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class HechizosController {

	Scanner scanner = new Scanner(System.in);

	private Connection connection;

	public HechizosController(Connection connection) {
		this.connection = connection;
	}

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