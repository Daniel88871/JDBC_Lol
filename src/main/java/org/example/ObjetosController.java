package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ObjetosController {

    private Connection connection;

    public ObjetosController(Connection connection) {
        this.connection = connection;
    }

    public void showObjetos() throws SQLException, IOException {

        Statement st = connection.createStatement();
        ResultSet rs;

        rs = st.executeQuery("SELECT * FROM objetos");
        while (rs.next()) {
            System.out.println("Popularidad: " + rs.getString("popularidad") + " " +
                    "Porcentaje de victoria: " + rs.getString("porcentaje_de_victoria"));
        }

        rs.close();
        st.close();
    }
}