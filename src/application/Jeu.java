package application;

import controleur.Interaction;
import modele.PlateauDeJeu;

public class Jeu {
	PlateauDeJeu plateau;
	int numeroConfiguration;

	public Jeu(){
		this.plateau = new PlateauDeJeu();
		// 1 -> configuration de base
		this.numeroConfiguration = 1;
	}

	public void jouer() {
		// affichage du message de bienvenue
		System.out.println("Bienvenue dans le jeu Citadelles");

		// affichage du menu
		System.out.println(
			"\n\t1 | Jouer une partie" +
			"\n\t2 | Afficher les règles"+
			"\n\t3 | Quitter"
		);

		int choix = Interaction.lireUnEntier(1, 3);

		if (choix == 1) {
			jouerPartie();
		} else if (choix == 2) {
			afficherLesRegles();
		}
	}

	private void afficherLesRegles() {

		// à la fin de la méthode :
		jouer();
	}

	private void jouerPartie() {
		do {
			initialisation();
			tourDeJeu();
			gestionCouronne();
			reinitialisationPersonnages();
		} while (!partieFinie());

		// à la fin de la méthode
		jouer();
	}

	private void initialisation() {
		// initialisation du plateau de jeu et de la pioche

	}

	private void gestionCouronne() {

	}

	private void reinitialisationPersonnages() {

	}

	private boolean partieFinie() {
		return true;
	}

	private void tourDeJeu() {

	}

	private void choixPersonnages() {

	}

	private void percevoirRessources() {

	}

	private void calculDesPoints () {

	}
}
