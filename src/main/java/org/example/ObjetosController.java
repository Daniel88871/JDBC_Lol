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
        ResultSet rs = null;
        String sql = "SELECT * FROM objetos";

        try {
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Popularidad: " + rs.getString("popularidad") + " " +
                        "Porcentaje de victoria: " + rs.getString("porcentaje_de_victoria"));
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La tabla no existe");
        }

    }

    public void deleteObjetos() throws SQLException, IOException {
        ResultSet rs = null;
        String sql = "DELETE TABLE objetos";

        try {
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Se ha borrado la tabla objetos");
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La tabla objetos no existe, por lo tanto no se puede borrar nada");
        }
    }

    public void addObjetos() {
        try {
            Statement st = connection.createStatement();

            st.executeUpdate("CREATE TABLE objetos (" +
                    "id_objetos serial," +
                    "popularidad varchar(200)," +
                    "porcentaje_de_victoria varchar(200)," +
                    "primary key(id_objetos));");

            st.close();

        } catch (SQLException e) {
            System.out.println("Error: No se pueden crear la tabla, fijate si ya están creadas");
        }
    }
}