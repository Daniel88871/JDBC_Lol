package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
	private int option;

	public Menu() {
		super();
	}

	public int mainMenu() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do {
			System.out.println(" \n	**** MENU **** \n");

			System.out.println("1. Muestra campeones ");
			System.out.println("2. Muestra hechizos");
			System.out.println("3. Muestra objetos");
			System.out.println("4. Salir");
			System.out.println("Escoge una opción: ");
			try {
				option = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("valor no válido");
				e.printStackTrace();
			}

		} while (option != 1 && option != 2 && option != 3 && option != 4);

		return option;
	}

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