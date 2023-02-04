package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CampeonesController {

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
			e.printStackTrace();
			System.out.println("La tabla no existe");
		}
	}

	public void deleteCampeones() throws SQLException, IOException {
		ResultSet rs = null;
		String sql = "DELETE TABLE campeones";

		try {
			Statement st = connection.createStatement();

			rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Se ha eliminado la tabla campeones");
			}
			rs.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
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

			st.close();

		} catch (SQLException e) {
			System.out.println("Error: No se pueden crear la tabla, fijate si ya están creadas");
		}
	}
}