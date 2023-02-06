package org.example;

/**
 * Esta clase representa la información de un usuario y su contraseña.
 * @author Daniel Ruiz - Daniel88871 in GitHub
 * @version 8.0
 */
public class Identity {
	private String user;
	private String password;

	/**
	 * Este es el constructor de la clase Identity, donde le pasaremos el usuario y la contraseña
	 * @param user recibe el nombre del usuario
	 * @param password recibe la contraseña del usuario
	 */
	public Identity(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	/**
	 * Este metodo sirve para coger el nombre
	 * @return devuelve el nombre
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Esto sirve para asignar el nombre
	 * @param user recibe el nombre que le vas a poner
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Este método sirve para coger la contraseña
	 * @return devuelve la contraseña
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Aquí le decimos que asigne la contraseña
	 * @param password recibe la contraseña que vas a introducir
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Esto sirve para modificar el formato de imprimir
	 * @return devuelve el formato
	 */
	@Override
	public String toString() {
		return "Identity [user=" + user + ", password=" + password + "]";
	}

}
