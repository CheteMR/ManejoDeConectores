package vista;

import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorPasajero;
import modelo.persistencia.SQL.DAObbdd;

public class Vista {
	private static Scanner sc;
	
	public static void main(String[] args) {
		
		
		
		
		boolean end = false;
		sc = new Scanner(System.in);
		
		DAObbdd dao = new DAObbdd ();
		
		if(!dao.connect()) {
			dao.createDatabase();
			dao.checkTable();
			
		}else {
			System.out.println("Conectando a la BBDD");
			dao.checkTable();
		}
		
		
		do {
			GestorCoche gestorCoche = new GestorCoche();
			Coche coche = new Coche();
			
			menuPrincipal();
			int opcion = sc.nextInt();
			switch (opcion) {
			
			case 1:
				alta(coche,gestorCoche);
				break;
			case 2:
				borrar(gestorCoche);
				break;
			case 3:
				obtener(gestorCoche);
				break;
			case 4:
				modificar(coche,gestorCoche);
				break;
			case 5:
				listar(gestorCoche);
				break;
				
			case 6: 
				boolean end2 = false;
				do {
					GestorPasajero gestorPasajero = new GestorPasajero();
					Pasajero pasajero = new Pasajero();
					menuSecundario();
					int opcion2 = sc.nextInt();
					
					switch(opcion2) {
					
					case 1:
						altaP(pasajero,gestorPasajero);
						break;
						
					case 2:
						borrar(gestorPasajero);
						break;
						
					case 3:
						obtener (gestorPasajero);
						break;
						
					case 4:
						listar (gestorPasajero);
						break;
						
					case 5: 
						altaPC (gestorPasajero, gestorCoche);
						break;
						
					case 6:
						borrar (gestorPasajero, gestorCoche);
						break;
						
					case 7: 
						lista(gestorPasajero, gestorCoche);
						break;
						
					case 8:
						end2 = true;
						break;
						
					}
				}while (!end2);
				break;
				
			case 7: 
			end=true;
			sc.close();
			break;
			}
		}while (!end);
			System.out.println("Fin del programa");
			
		}
	
	private static void menuPrincipal() {
		System.out.println("Elije una opción: ");
		System.out.println("1 - Añadir nuevo coche");
		System.out.println("2 - Borrar coche por ID");
		System.out.println("3 - Consultar coche por ID");
		System.out.println("4 - Modificar coche por ID");
		System.out.println("5 - Obtener todos los coches");
		System.out.println("6 - Gestión de pasajeros");
		System.out.println("7 - Terminar programa");
		System.out.println("=================");
		
	}
	
	private static void menuSecundario() {
		System.out.println("Elije una opción: ");
		System.out.println("1 - Añadir nuevo pasajero");
		System.out.println("2 - Borrar pasajero por ID");
		System.out.println("3 - Consultar pasajero por ID");
		System.out.println("4 - Obtener todos los pasajeros");
		System.out.println("5 - Añadir pasajero a un coche");
		System.out.println("6 - Eliminar pasajeros de un coche");
		System.out.println("7 - Obtener pasajeros de un coche");
		System.out.println("=================");
		
	}
						
	private static void alta (Coche coche, GestorCoche gestorCoche) {
		System.out.println("Introduce la marca del coche: ");
		String marca = sc.next();
		coche.setMarca(marca);
		
		System.out.println("Introduce el modelo del coche: ");
		String modelo = sc.next();
		coche.setModelo(modelo);
		
		System.out.println("Itroduce el año del coche: ");
		String ano = sc.next();
		coche.setAño(ano);
		
		System.out.println("Introduce los Kilometros del coche: ");
		double km = sc.nextDouble();
		coche.setKm(km);
		
		boolean alta = gestorCoche.añadir(coche);
		if(alta) {
			System.out.println("El coche ha sido añadido");
		}else {
			System.out.println("El coche no se ha añadido");
		}
	}
					
	private static void borrar (GestorCoche gestorCoche) {
		
		System.out.println("Introduce el ID del coche");
		while(!sc.hasNextInt()) {
			System.out.println("El ID tiene que ser numérico");
			sc.nextLine();
			System.out.println("Introduce el ID del coche");
		}
		int id = sc.nextInt();
		boolean borrar = gestorCoche.borrar(id);
		if(borrar) {
			System.out.println("El cohe ha sido eliminado");
			
		}else {
			System.out.println("El coche no se ha eliminado");
		}
		
	}
	
	private static void obtener (GestorCoche gestorCoche) {
		System.out.println("Introduce el ID del coche");
		while(!sc.hasNextInt()) {
			System.out.println("El ID tiene que se numérico");
			sc.nextLine();
			System.out.println("Introduce el ID del coche");
		}
		int identificador = sc.nextInt();
		System.out.println("El coche es: " +gestorCoche.obtener(identificador));
	}
				
		private static void modificar(Coche coche, GestorCoche gestorCoche) {
			
			System.out.println("Introduce el ID del coche");
			while (!sc.hasNextInt()) {
				System.out.println("El ID tiene que ser numérico");
				sc.nextLine();
				System.out.println("Introduce el ID del coche");
			
		}
			int identificador2 = sc.nextInt();
			coche.setId(identificador2);
			
			System.out.println("Introduce la marca del coche: ");
			String marca1 = sc.next();
			coche.setMarca(marca1);
			
			System.out.println("Introduce el modelo del coche: ");
			String modelo1 = sc.next();
			coche.setModelo(modelo1);
			
			System.out.println("Itroduce el año del coche: ");
			String ano1 = sc.next();
			coche.setAño(ano1);
			
			System.out.println("Introduce los Kilometros del coche: ");
			double km1 = sc.nextDouble();
			coche.setKm(km1);
			
			boolean modifica = gestorCoche.añadir(coche);
			if(modifica) {
				System.out.println("El coche ha sido modificado");
			}else {
				System.out.println("El coche no se ha modificado");
			}
		}
			
		private static void listar(GestorCoche gestorCoche) {
			System.out.println("La lista de coches es la siguiente: ");
			System.out.println(gestorCoche.lista());
		}
		
		private static void altaP (Pasajero pasajero, GestorPasajero gestorPasajero) {
			System.out.println("Introduce el nombre del pasajero: ");
			String nombre = sc.next();
			pasajero.setNombre(nombre);
			
			System.out.println("Introduce la edad del pasajero: ");
			while (!sc.hasNextInt()) {
				System.out.println("La edad tiene que ser numérica");
				sc.nextLine();
				System.out.println("Introduce la edad del pasajero");
			}
			int edad = sc.nextInt();
			pasajero.setEdad((edad));
			
			System.out.println("Introduce el peso del pasajero: ");
			while (!sc.hasNextDouble()) {
				System.out.println("El peso debe ser un valor numérico");
				sc.nextLine();
				System.out.println("Introduce el peso del pasajero");
			}
			Double peso = sc.nextDouble();
			pasajero.setPeso(peso);
			
			boolean alta = gestorPasajero.añadir(pasajero);
			if (alta) {
				System.out.println("El pasajero ha sido añadido");
			} else {
				System.out.println("El pasajero no ha sido añadido");
		}
	
	}
		private static void borrar (GestorPasajero gestorPasajero) {
			System.out.println("Introduce el ID del pasajero");
			while (!sc.hasNextInt()) {
				System.out.println("El ID tiene que ser numérico");
				sc.nextLine();
				System.out.println("Introduce el ID del pasajero");
			}
			int id = sc.nextInt();
			boolean borrar = gestorPasajero.borrar(id);
			if (borrar) {
				System.out.println("El pasajero ha sido eliminado");
			} else {
				System.out.println("El pasajero no ha sido eliminado");
			}
		}
		
		private static void obtener(GestorPasajero gestorPasajero) {
			GestorCoche gestorCoche = new GestorCoche();
			listar(gestorCoche);
			System.out.println("Introduce el ID del coche");
			while(!sc.hasNextInt()) {
				System.out.println("El ID tiene que ser numérico");
				sc.nextLine();
				System.out.println("Introduce el ID del coche");
			}
			int id = sc.nextInt();
			System.out.println("El pasajero es: " +gestorPasajero.obtener(id));
		}
		
		private static void listar(GestorPasajero gestorPasajero) {
			System.out.println("La lista de pasajeros es: ");
			System.out.println(gestorPasajero.listar());
		}
		
		
		
		private static void altaPC (GestorPasajero gestorPasajero, GestorCoche gestorCoche) {
			listar(gestorCoche);
			System.out.println("Introduce el ID del coche: ");
			while (!sc.hasNextInt()) {
				System.out.println("El ID debe ser un valor numérico");
				sc.nextLine();
				System.out.println("Introduce el ID del coche");
			}
			int idCoche = sc.nextInt();
			listar(gestorPasajero);
			System.out.println("Introduce el ID del pasajero: ");
			int idPasajero = sc.nextInt();

			boolean asignacion = gestorPasajero.añadir(idPasajero, idCoche);

			if (asignacion) {
				System.out.println("El pasajero ha sido asignado correctamente");
			} else {
				System.out.println("El pasajero no ha sido asignado");
			}
		}
		
		private static void borrar(GestorPasajero gestorPasajero, GestorCoche gestorCoche) {

			lista(gestorPasajero, gestorCoche);

			System.out.println("Introduce el ID del pasajero");
			while (!sc.hasNextInt()) {
				System.out.println("El ID tiene que ser numérico");
				sc.nextLine();
				System.out.println("Introduce el ID del pasajero");
			}
			int idPasajero = sc.nextInt();

			boolean baja = gestorPasajero.desasignar(idPasajero);

			if (baja) {
				System.out.println("El pasajero ha sido eliminado");
			} else {
				System.out.println("El pasajero no ha sido eliminado");
			}
		}

		private static void lista(GestorPasajero gestorPasajero, GestorCoche gestorCoche) {
			listar(gestorCoche);
			System.out.println("Introduce el ID del coche: ");
			int idCoche = sc.nextInt();

			System.out.println(gestorPasajero.listar(idCoche));
		}
			
	}
			
		
		



