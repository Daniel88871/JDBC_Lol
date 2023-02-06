package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Esta clase llamada Menu permite interactuar con un menú en consola que permite realizar
 * diversas operaciones con una base de datos PostgreSQL.
 *
 * @author Daniel Ruiz - Daniel88871 in GitHub
 * @version 8.0
 */
public class Menu {
	private int option;

	/**
	 * Este constructor llama al super(), en el que mostraremos el menú.
	 *
	 */
	public Menu() {
		super();
	}

	/**
	 * El método mainMenu muestra las opciones disponibles para interactuar con la BBDD PostgreSQL.
	 *
	 * @return La opción elegida por el usuario en la consola.
	 */
	public int mainMenu() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do {
			System.out.println(" \n	**** MENÚ **** \n");

			System.out.println("1. Muestra campeones ");
			System.out.println("2. Muestra hechizos");
			System.out.println("3. Muestra objetos");
			System.out.println("4. Añadir tabla campeones");
			System.out.println("5. Añadir tabla hechizos");
			System.out.println("6. Añadir tabla objetos");
			System.out.println("7. Borrar tabla campeones");
			System.out.println("8. Borrar tabla hechizos");
			System.out.println("9. Borrar tabla objetos");
			System.out.println("10. Buscar ID campeones");
			System.out.println("11. Buscar ID hechizos");
			System.out.println("12. Buscar ID objetos");
			System.out.println("13. Rellenar CSV campeones");
			System.out.println("14. Rellenar CSV hechizos");
			System.out.println("15. Rellenar CSV objetos");
			System.out.println("16. Salir");
			System.out.println("Escoge una opción: ");

			try {
				option = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("valor no válido");
				e.printStackTrace();
			}

		} while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7 && option != 8 && option != 9 && option != 10 && option != 11 && option != 12 && option != 13 && option != 14 && option != 15 && option != 16);

		return option;
	}

	/**
	 * En este método lo que se hace es pedir y verificar las credenciales del usuario postgres.
	 *
	 * @return La identidad del usuario.
	 * @throws IOException Devuelve una excepción si hay problemas al leer los datos del usuario.
	 * @param tries El número de intentos que el usuario inicia sesión
	 *
	 */
	public Identity authenticate(int tries) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("============================LOL=============================");
		System.out.println("============================================================");
		System.out.println("Avíso: tienes " + (3 - tries) + " intentos para logearte");
		System.out.println("============================================================");
		System.out.println("Inserta nombre del usuario: ");
		String user = br.readLine();
		System.out.println("Inserta contraseña: ");
		String password = br.readLine();

		Identity identity = new Identity(user, password);
		return identity;
	}
}