package Biblioteca;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import enums.Periodicita;

public class Archivio {
	private static final Logger logger = LoggerFactory.getLogger(Archivio.class);

	static String fileName = "text.txt";
	static File fileInfo = new File(fileName);
	private static final String ENCODING = "UTF-8";
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		boolean inside = true;
		while (inside) {
			ArrayList<String> archivio = new ArrayList<String>(FileUtils.readLines(fileInfo, ENCODING));

			System.out.println("[ Interfaccia Utente ]");
			System.out.println("|1. Aggiungi libro o rivista");
			System.out.println("|2. Rimuovi con codice ISBN");
			System.out.println("|3. Ricerca per ISBN");
			System.out.println("|4. Ricerca per Anno di pubblicazione");
			System.out.println("|5. Ricerca per Autore");
			System.out.println("|6. Carica Dati");
			System.out.println("|0. Logout");

			try {
				String scelta = in.nextLine();
				switch (scelta) {
				case "1":
					aggiungi();
					break;
				case "2":
					System.out.println("Inserisci il codice ISBN");
					String ISBN = in.nextLine();
					archivio.removeIf(obj -> obj.contains(ISBN));
					FileUtils.writeLines(fileInfo, archivio);
					System.out.println("L'elemento " + ISBN + " è stato rimosso");
					attesa(3);
					break;
				case "3":
					System.out.println("Inserisci il codice ISBN");
					ISBN = in.nextLine();
					archivio.stream().filter((obj) -> obj.contains(ISBN)).forEach((e) -> System.out.println(e));
					attesa(3);
					break;
				case "4":
					System.out.println("Inserisci l'anno di pubblicazione per completo. 'es. 2022'");
					String anno = in.nextLine();
					archivio.stream().filter((obj) -> obj.contains(anno)).forEach((e) -> System.out.println(e));
					attesa(3);
					break;
				case "5":
					System.out.println("Inserisci l'autore");
					String autore = in.nextLine();
					archivio.stream().filter((obj) -> obj.contains(autore)).forEach((e) -> System.out.println(e));
					attesa(3);
					break;
				case "6":
					System.out.println( FileUtils.readFileToString(fileInfo, ENCODING) );
					attesa(3);
					break;
				case "0":
					System.out.println("Sei uscito.");
					System.exit(0);
					break;
				default:
					throw new IllegalArgumentException("\033[31mScelta non valida. \033[0m");
				}
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage());
			}
		}

	}

	public static void aggiungi() {
		while (true) {
			try {
				System.out.println("Scegli tra le seguenti: ");
				System.out.println("1. Aggiungi libro");
				System.out.println("2. Aggiungi rivista");
				int scelta = Integer.parseInt(in.nextLine());
				if (scelta == 1) {
					creaLibro();
					break;
				} else if (scelta == 2) {
					creaRivista();
					break;
				} else {
					throw new IllegalArgumentException();
				}
			} catch (InputMismatchException e) {
				logger.error("\033[31mScelta non valida, si prega di inserire il numero dell'indice. \033[0m");
				in.nextLine();
			} catch (IllegalArgumentException e) {
				logger.error("\033[31mScelta non valida.\033[0m ");
			}
		}
	}

	// creazione Libro/Rivista
	public static void creaLibro() {
		System.out.print("Inserisci ISBN: ");
		String ISBN = in.nextLine();
		System.out.print("Inserisci titolo: ");
		String titolo = in.nextLine();
		System.out.print("Inserisci anno di pubblicazione: ");
		String anno = in.nextLine();
		System.out.print("Inserisci numero di pagine: ");
		int pagine = Integer.parseInt(in.nextLine());
		System.out.print("Inserisci autore: ");
		String autore = in.nextLine();
		System.out.print("Inserisci genere: ");
		String genere = in.nextLine();

		Libro libro = new Libro(ISBN, titolo, anno, pagine, autore, genere);
		System.out.println("Creando il libro....");
		attesa(2);
		try {
			salvataggio(libro.getInfo());
			System.out.println("Libro Creato!");
			attesa(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void creaRivista() {
		System.out.print("Inserisci ISBN: ");
		String ISBN = in.nextLine();
		System.out.print("Inserisci titolo: ");
		String titolo = in.nextLine();
		System.out.print("Inserisci anno di pubblicazione: ");
		String anno = in.nextLine();
		System.out.print("Inserisci numero di pagine: ");
		int pagine = Integer.parseInt(in.nextLine());
		System.out.print("Inserisci periodicità: [ settimanale | mensile | semestrale ] ");
		Periodicita periodicita = Periodicita.valueOf((in.nextLine()).toUpperCase());

		Rivista rivista = new Rivista(ISBN, titolo, anno, pagine, periodicita);
		System.out.println("Creando la rivista....");
		attesa(2);

		try {
			salvataggio(rivista.getInfo());
			System.out.println("Rivista Creata!");
			attesa(1);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void salvataggio(String obj) throws IOException {
		FileUtils.writeStringToFile(fileInfo, obj + System.lineSeparator(), ENCODING, true);
	}
	
	public static void attesa(int sec) {
		int millisecondi = sec * 1000;
		try {
		    Thread.sleep(millisecondi);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
}
