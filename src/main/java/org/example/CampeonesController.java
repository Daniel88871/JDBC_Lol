package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CampeonesController {

	private Connection connection;

	public CampeonesController(Connection c) {
		this.connection = connection;
	}

	public void showCampeones() throws SQLException, IOException {

		Statement st = connection.createStatement();
		ResultSet rs;

		rs = st.executeQuery("SELECT * FROM campeones");
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
	}
}