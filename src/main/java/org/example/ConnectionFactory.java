package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * El ConnectionFactory establece y gestiona una conexión a una BBDD de PostgreSQL.
 *
 * @author Daniel Ruiz - Daniel88871 in GitHub
 * @version 8.0
 */
public class ConnectionFactory {
	// init database constants
	private static final String DATABASE_DRIVER = "org.postgresql.Driver";
	private static final String DATABASE_URL = "jdbc:postgresql://192.168.22.128:5432/lol";
	private static final String USERNAME = "dani";
	private static final String PASSWORD = "1234";
	private static final String MAX_POOL = "250";
	private static ConnectionFactory instance;

	private String dbname;
	private String host;
	private String port;
	private String user;
	private String password;
	private String schema;
	
	// init connection object
	private Connection connection;
	// init properties object
	private Properties properties;

	/**
	 * Inicias la conexión
	 */
	private ConnectionFactory() {
		super();
		init();
	}

	/**
	 * Este método sirve para coger el Instance
	 *
	 * @return devuelve el Instance
	 */
	public static ConnectionFactory getInstance() {
		if (instance == null) {
			instance = new ConnectionFactory();
		}
		return instance;
	}

	/**
	 * Inicializa la clase cargando el archivo de propiedades de la base de datos y asigna valores a las variables de instancia.
	 *
	 * @throws RuntimeException Salta cuando no encuentra el archivo propierties.
	 */
	public void init() {
		Properties prop = new Properties();
		InputStream propStream = this.getClass().getClassLoader().getResourceAsStream("db.properties");

		try {
			prop.load(propStream);
			this.host = prop.getProperty("host");
			this.port = prop.getProperty("port");
			this.user = prop.getProperty("user");
			this.password = prop.getProperty("password");
			this.dbname = prop.getProperty("dbname");
			this.schema = prop.getProperty("schema");
		} catch (IOException e) {
			String message = "ERROR: db.properties file could not be found";
			System.err.println(message);
			throw new RuntimeException(message, e);
		}
	}

	/**
	 * Creas los properties
	 *
	 * @return te devuelve el properties una vez ya creado
	 */
	private Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
			properties.setProperty("user", this.user);
			properties.setProperty("password", this.password);
			properties.setProperty("MaxPooledStatements", MAX_POOL);
		}
		return properties;
	}

	/**
	 * Para conectarte a la BBDD
	 */
	public Connection connect() {
		if (connection == null) {

			try {
				String url = null;

				Class.forName(DATABASE_DRIVER);
				
				// Preprara connexió a la base de dades
				StringBuffer sbUrl = new StringBuffer();
				sbUrl.append("jdbc:postgresql:");
				if (host != null && !host.equals("")) {
					sbUrl.append("//").append(host);
					if (port != null && !port.equals("")) {
						sbUrl.append(":").append(port);
					}
				}
				sbUrl.append("/").append(dbname);
				url = sbUrl.toString();
				
				System.out.println(url);
				System.out.println(getProperties());
						
				connection = DriverManager.getConnection(url, getProperties());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	/**
	 * Para desconectarte de la BBDD
	 */
	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}