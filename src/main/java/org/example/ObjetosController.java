package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ObjetosController se usa para controlar la información de la tabla objetos.
 * Esta clase tiene unos métodos que te muestra, borra y crea la tabla "objetos" aparte de un método que busca por ID cada objeto.
 *
 * @author Daniel Ruiz - Daniel88871 in GitHub
 * @version 8.0
 */
public class ObjetosController {

    Scanner scanner = new Scanner(System.in);

    private Connection connection;

    /**
     * Esto nos permite conectarnos a la base de datos del postgresql y con este controlador podremos buscar, borrar, añadir y buscar por ID.
     * @param connection le pasas la conexión de la BBDD para que pueda ejecutar los métodos
     *
     */
    public ObjetosController(Connection connection) {
        this.connection = connection;
    }

    /**
     * Este método recoge toda la información actual que hay en la tabla objetos y la muestra para que el usuario la pueda leer.
     * @throws SQLException Si no puede mostrar la tabla objetos, enseña un error.
     * @throws IOException Lo mismo que SQLException.
     *
     */
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
            System.out.println("La tabla objetos no existe");
        }
    }

    /**
     * Este método lo que hace es borrar la tabla objetos en el caso de que esté creada.
     * @throws SQLException Si no puede borrar la tabla objetos, enseña un error.
     * @throws IOException Lo mismo que SQLException.
     *
     */
    public void deleteObjetos() throws SQLException, IOException {
        String sql = "DROP TABLE objetos";

        try {
            Statement st = connection.createStatement();

            st.executeUpdate(sql);

            System.out.println("La tabla objetos se ha borrado");
            st.close();

        } catch (SQLException e) {
            System.out.println("La tabla objetos no existe, por lo tanto no se puede borrar nada");
        }
    }

    /**
     * Aquí creamos la tabla objetos y la llenamos con sus atributos.
     *
     */
    public void addObjetos() {
        try {
            Statement st = connection.createStatement();

            st.executeUpdate("CREATE TABLE objetos (" +
                    "id_objetos serial," +
                    "popularidad varchar(200)," +
                    "porcentaje_de_victoria varchar(200)," +
                    "primary key(id_objetos));");

            System.out.println("Se ha creado la tabla objetos");

            st.close();

        } catch (SQLException e) {
            System.out.println("Error: No se pueden crear la tabla, fijate si ya están creadas");
        }
    }

    /**
     * Aquí mostramos la información actual de la tabla objetos en base al atributo "id_objetos", y después le preguntará al usuario que ID de objeto quiere introducir,
     * y una vez se haya introducido una ID, mostrará toda la información de ese objeto en concreto.
     *
     */
    public void mostrarIDObjetos(){
        ResultSet rs = null;
        System.out.println("Inserta una ID de objeto: ");
        String ids = scanner.nextLine();

        String sql = "SELECT * FROM objetos WHERE id_objetos = '" + ids + "'";
        try {
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("-----------------------------------" +
                        "\nPopularidad: " + rs.getString("popularidad") +
                        "\nPorcentaje de victoria: " + rs.getString("porcentaje_de_victoria") +
                        "\n-----------------------------------");
            }
            rs.close();
            st.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}