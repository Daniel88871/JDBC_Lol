package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CampeonesController {

	Scanner scanner = new Scanner(System.in);

	private Connection connection;

	public CampeonesController(Connection connection) {
		this.connection = connection;
	}

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