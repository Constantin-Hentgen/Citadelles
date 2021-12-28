package application;

import controleur.*;
import modele.*;

public class Jeu {
	private PlateauDeJeu plateau;
	private int numeroConfiguration;
	private int rangRoi;

	public Jeu(){
		this.plateau = new PlateauDeJeu();
		// 1 -> configuration de base
		this.numeroConfiguration = 1;
	}

	public void jouer() {
		// affichage du message de bienvenue
		System.out.println("\n______________________________________________________________________");
		System.out.println("______________________________________________________________________");
		System.out.println("\n\t\tBienvenue dans le jeu Citadelles\n");

		// affichage du menu
		System.out.println(
			"\n\t1 | Jouer une partie" +
			"\n\t2 | Afficher les règles"+
			"\n\t3 | Quitter"
		);

		System.out.println("\n______________________________________________________________________\n");

		int choix = Interaction.lireUnEntier(1, 4);

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
		
		choixPersonnages();
		// à la fin de la méthode
		jouer();
	}

	private void initialisation() {
		// initialisation du plateau de jeu et de la pioche
		Pioche pioche = this.plateau.getPioche();
		this.plateau = Configuration.configurationDeBase(pioche);
		Configuration.nouvellePioche(pioche);

		// 4 joueurs dont 3 bots : les bots utilisent utiliserPouvoirAvatar

		for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
			// 2 pièces d'or
			this.plateau.getJoueur(i).ajouterPieces(2);
			for (int j = 0; j < 4; j++) {
				// 4 cartes quartiers pour tout le monde
				this.plateau.getJoueur(i).ajouterQuartierDansMain(this.plateau.getPioche().piocher());
			}
		}
		
		// donner la couronne à quelqu'un
		this.rangRoi = Interaction.randomizer(3);
		this.plateau.getJoueur(rangRoi).setPossedeCouronne(true);
	}

	private void gestionCouronne() {

	}

	private void reinitialisationPersonnages() {

	}

	private boolean partieFinie() {
		// verifie si un joueur a une cité complète
		return true;
	}

	private void tourDeJeu() {

	}

	private void choixPersonnages() {
		// écarter 2 cartes faces cachées
		Personnage persoAEcarter;

		for (int i = 0; i < 2; i++) {
			persoAEcarter = this.plateau.getPersonnage(Interaction.randomizer(this.plateau.getNombrePersonnages()));
			this.plateau.ecarterPersonnage(persoAEcarter);
		}

		// et 1 carte face visible
		persoAEcarter = this.plateau.getPersonnage(Interaction.randomizer(this.plateau.getNombrePersonnages()));
		this.plateau.ecarterPersonnage(persoAEcarter);

		System.out.println("\n3 cartes personnages dont "+ persoAEcarter.getNom() +" ont été écartées.\n");








		// affichage : untel a la couronne : il commence

		System.out.println("Le roi " + this.plateau.getJoueur(this.rangRoi).getNom() + " choisit en premier.\n");

		// le joueur humain est systématiquement le joueur1


		// le roi start puis j'avise
		int choix;
		// si le roi c'est moi


		if (rangRoi == 0) {
			System.out.println();
			for (int i = 0; i < this.plateau.getNombrePersonnages(); i++) {
				System.out.println("\t" + (i+1) + " | " + this.plateau.getPersonnage(i).getNom());
			}
			System.out.println();

			choix = Interaction.lireUnEntier(1, this.plateau.getNombrePersonnages())-1;
			this.plateau.getPersonnage(choix).setJoueur(this.plateau.getJoueur(0));
		} else {

			System.out.println();
			for (int i = 0; i < this.plateau.getNombrePersonnages(); i++) {
				System.out.println("\t" + (i+1) + " | " + this.plateau.getPersonnage(i).getNom());
			}
			System.out.println();
			// si le roi est un bot
			choix = Interaction.randomizer(this.plateau.getNombrePersonnages()-1);
			this.plateau.getPersonnage(choix).setJoueur(this.plateau.getJoueur(this.rangRoi));
			System.out.println("CHOIX RANDOM DU ROI ROBOT : " + choix);
		}
		
		System.out.println(this.plateau.getJoueur(this.rangRoi).getNom() + " joue le personnage " + this.plateau.getJoueur(this.rangRoi).getPersonnage().getNom());
		persoAEcarter = this.plateau.getPersonnage(choix);
		this.plateau.ecarterPersonnage(persoAEcarter);

		// on boucle pour le reste des joueurs
		for (int h = 0; h < this.plateau.getNombreJoueurs(); h++) {
			// affichage des choix disponibles
			// si ce n'est pas le roi
			if (h != this.rangRoi) {
				for (int i = 0; i < this.plateau.getNombrePersonnages(); i++) {
					System.out.println("\t" + (i+1) + " | " + this.plateau.getPersonnage(i).getNom());
				}
				
				if (h == 0) {
					choix = Interaction.lireUnEntier(1, this.plateau.getNombrePersonnages())-1;
					this.plateau.getPersonnage(choix).setJoueur(this.plateau.getJoueur(0));
				} else {
					choix = Interaction.randomizer(this.plateau.getNombrePersonnages()-1);
					System.out.println("CHOIX RANDOM DU ROBOT : " + choix);
					this.plateau.getPersonnage(choix).setJoueur(this.plateau.getJoueur(h));
				}

				persoAEcarter = this.plateau.getPersonnage(choix);
				this.plateau.ecarterPersonnage(persoAEcarter);
				
				System.out.println(this.plateau.getJoueur(h).getNom() + " joue le personnage " + this.plateau.getJoueur(h).getPersonnage().getNom());
			}
		}

		// determiner qui est le roi : si c'est le joueur c'est cool
		// [joueur1, joueur2, roi, joueur3]
	}

	private void percevoirRessources() {

	}

	private void calculDesPoints () {

	}
}
