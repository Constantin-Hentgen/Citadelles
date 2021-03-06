package controleur;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Interaction {
	private static Scanner sc = new Scanner(System.in);

	public static int lireUnEntier() {
		int i = 0;
		boolean continu = true;
		do {
			try {
				do {
					System.out.print("Entrez un entier naturel : ");
					i = sc.nextInt();
				} while ( i < 0 );
				continu = false;
			} catch (InputMismatchException e) {
				sc.next(); // passe l'entier pour éviter de boucler
			}
		} while(continu);
		return i;
	}

	// renvoie un entier lu au clavier compris dans l'intervalle
	//     [borneMin, borneMax[
	public static int lireUnEntier(int borneMin, int borneMax) {
		int i = 0;
		do{
			i = lireUnEntier();
		} while ( i < borneMin || i >= borneMax);
		return i;
	}

	// lit les réponses "oui", "non", "o" ou "n" et renvoie un booléen
	public static boolean lireOuiOuNon() {
		boolean retour = true;
		String valeur;

		do {			
			System.out.print("Entrez o/oui ou n/non : ");
			valeur = sc.next();
		} while ( !valeur.equals("oui") && !valeur.equals("non") && !valeur.equals("o") && !valeur.equals("n"));

		if (valeur.equals("oui") || valeur.equals("o")){
			retour = true;
		} else { retour = false; }

		return retour;
	}

	// renvoie une chaîne de caractères lue au clavier:
	public static String lireUneChaine() {
		String retour = "";
		
		do {
			retour = sc.next();
		} while (retour.length() == 0);

		return retour;
	}

	public static int randomizer() {
		Random generateur = new Random();
		int numeroHasard = generateur.nextInt();
		return numeroHasard;
	}

	public static int randomizer(int borne) {
		Random generateur = new Random();
		int numeroHasard = generateur.nextInt(borne);
		return numeroHasard;
	}

	public static boolean randomizerBoolean() {
		Random generateur = new Random();
		int numeroHasard = generateur.nextInt(1);
		if (numeroHasard == 0) {
			return true;
		} else {
			return false;
		}
	}
}