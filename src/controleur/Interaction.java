package controleur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction {
	private static Scanner sc = new Scanner(System.in);

	public static int lireUnEntier() {
		int i = 0;
		boolean continu = true;
		do {
			try {
				i = sc.nextInt();
				continu = false;
			} catch (InputMismatchException e) {
				System.out.print("Veuillez rentrer un chiffre : ");
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
			i = sc.nextInt();
		} while ( i < borneMin || i >= borneMax);
		return i;
	}

	// lit les réponses "oui", "non", "o" ou "n" et renvoie un booléen
	public static boolean lireOuiOuNon() {
		boolean retour = true;
		String valeur;

		do {
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
}