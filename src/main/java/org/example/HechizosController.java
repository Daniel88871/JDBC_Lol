package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HechizosController {

	private Connection connection;

	public HechizosController(Connection connection) {
		this.connection = connection;
	}

	public void showHechizos() throws SQLException, IOException {

		Statement st = connection.createStatement();
		ResultSet rs;

		rs = st.executeQuery("SELECT * FROM hechizos");
		while (rs.next()) {
			System.out.println("Nombre: " + rs.getString("nombre") + " " +
							   "Popularidad: " + rs.getString("popularidad") + " " +
							   "Porcentaje de victoria: " + rs.getString("porcentaje_de_victoria"));
		}

		rs.close();
		st.close();
	}
}