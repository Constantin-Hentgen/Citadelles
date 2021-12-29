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
		System.out.println(
			"\nDeux à huit joueurs s'affrontent pour construire le plus rapidement possible la plus prestigieuse cité." + 
			"\nPour cela, chaque joueur devra construire des quartiers, ayant chacun des coûts différents." + 
			"\nComme dans un jeu de rôle, chaque joueur doit se mettre dans la peau d'un personnage," + 
			"\nà ceci près que les joueurs changent de personnage à chaque tour de jeu." + 
			"\nCes personnages ont chacun des pouvoirs particuliers : la meilleure statégie est de choisir un personnage au bon moment du jeu."
			);

		System.out.println(
			"\nLa quatrième édition de Citadelles comportent 84 quartiers, répartis en 5 catégories : religieux, militaires, nobles, commerçants et merveilles. "+
			"\nLes quartiers sont présents plusieurs fois, à part les	merveilles qui sont uniques. "+
			"\nUne cité ne doit pas contenir deux quartiers identiques (sauf exception)."+
			"\nChaque quartier possède un nom et un coût de contruction. "+
			"\nSeuls les quartiers merveilles	possèdent chacun des effets particuliers liés au coût de construction, "+
			"\nà la perception des revenus ou encore au calcul des points."
		);

		// à la fin de la méthode :
		jouer();
	}

	private void jouerPartie() {
		initialisation();

		do {
			tourDeJeu();
			gestionCouronne();
			reinitialisationPersonnages();

			// on joue le tour
		} while (!partieFinie());

		calculDesPoints();

		// à la fin de la méthode
		jouer();
	}

	private void initialisation() {
		// initialisation du plateau de jeu et de la pioche
		Pioche pioche = this.plateau.getPioche();
		this.plateau = Configuration.configurationDeBase(pioche);
		Configuration.nouvellePioche(pioche);

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
		// TOUS LES JOUEURS SONT BIEN CRÉÉS : VÉRIFIER QUE LES PERSONNAGES LE SONT BIEN
		// VERIFIER QUE TOUS LES PERSONNAGES SONT BIEN AFFILIÉS AUX JOUEURS CORRESPONDANTS

		choixPersonnages();
		
		System.out.println("_______________________________________________________\n");
		System.out.println("\nMATCHING LIST");
		
		for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
			System.out.println(this.plateau.getJoueur(i).getNom() + " joue " + this.plateau.getJoueur(i).getPersonnage().getNom());
		}

		// appeler les personnages un par un pour les faire jouer
		for (int i = 0; i < this.plateau.getNombrePersonnages(); i++) {
			// checker si il est assassiné
			if (!this.plateau.getPersonnage(i).getAssassine()) {
				// donner à chacun ses ressources
				// donner à ceux concernés les ressources spécifiques
				percevoirRessources();

				// faire un petit affichage récapitulatif ??

				boolean choix;
				int choixQuartier;

				// Dans le cas où le joueur est humain
				if (i == 0) {
					// System.out.println("Vous êtes " + this.plateau.getPersonnage(i).getJoueur().getNom() + " et vous jouez " + this.plateau.getPersonnage(i).getNom());
					// System.out.println("vous êtes un " + this.plateau.getPersonnage(i).getNom());
					this.plateau.getJoueur(0).getPersonnage().utiliserPouvoir();

					System.out.println("Voulez-vous construire ?");
					choix = Interaction.lireOuiOuNon();
					if (choix) {
						for (int j = 0; j < this.plateau.getJoueur(i).nbQuartiersDansMain(); j++) {
							System.out.println("\t" + (j+1) + this.plateau.getJoueur(i).getMain().get(j).getNom());
						}

						choixQuartier = Interaction.lireUnEntier(1, this.plateau.getJoueur(i).nbQuartiersDansMain());
						this.plateau.getJoueur(i).getPersonnage().construire(this.plateau.getJoueur(i).getMain().get(choixQuartier));
					}
				} else {
					// Dans le cas où le joueur est un robot
					this.plateau.getJoueur(i).getPersonnage().utiliserPouvoirAvatar();
					choix = Interaction.randomizerBoolean();

					if (choix) {
						choixQuartier = Interaction.randomizer(this.plateau.getJoueur(i).nbQuartiersDansMain()-1);
						this.plateau.getJoueur(i).getPersonnage().construire(this.plateau.getJoueur(i).getMain().get(choixQuartier));
					}
				}
			}
		}
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
		System.out.println("Le roi " + this.plateau.getJoueur(this.rangRoi).getNom() + " choisit en premier.");

		// le joueur humain est systèmatiquement le joueur1

		int choix;

		// si le roi c'est moi
		if (rangRoi == 0) {
			System.out.println();
			for (int i = 0; i < this.plateau.getNombrePersonnages(); i++) {
				System.out.println("\t" + (i+1) + " | " + this.plateau.getPersonnage(i).getNom());
			}

			choix = Interaction.lireUnEntier(1, this.plateau.getNombrePersonnages())-1;
			this.plateau.getPersonnage(choix).setJoueur(this.plateau.getJoueur(0));
			System.out.println("Vous avez choisi de jouer le personnage " + this.plateau.getJoueur(0).getPersonnage().getNom() + ".\n");
		} else {
			// si le roi est un bot
			choix = Interaction.randomizer(this.plateau.getNombrePersonnages()-1);
			this.plateau.getPersonnage(choix).setJoueur(this.plateau.getJoueur(this.rangRoi));
			System.out.println(this.plateau.getJoueur(this.rangRoi).getNom() + " a choisi son personnage.");
		}
		
		persoAEcarter = this.plateau.getPersonnage(choix);
		this.plateau.ecarterPersonnage(persoAEcarter);

		// on boucle pour le reste des joueurs
		for (int h = 0; h < this.plateau.getNombreJoueurs(); h++) {
			// si ce n'est pas le roi
			if (h != this.rangRoi) {
				
				if (h == 0) {
					// affichage des choix disponibles
					System.out.println("\nC'est à vous de choisir un personnage :\n");

					for (int i = 0; i < this.plateau.getNombrePersonnages(); i++) {
						System.out.println("\t" + (i+1) + " | " + this.plateau.getPersonnage(i).getNom());
					}
					
					choix = Interaction.lireUnEntier(1, this.plateau.getNombrePersonnages()+1)-1;
					this.plateau.getPersonnage(choix).setJoueur(this.plateau.getJoueur(0));
					System.out.println("Vous avez choisi de jouer le personnage " + this.plateau.getJoueur(0).getPersonnage().getNom() + ".\n");
				} else {
					choix = Interaction.randomizer(this.plateau.getNombrePersonnages()-1);
					this.plateau.getPersonnage(choix).setJoueur(this.plateau.getJoueur(h));
					System.out.println(this.plateau.getJoueur(h).getNom() + " a choisi son personnage.");
				}

				persoAEcarter = this.plateau.getPersonnage(choix);
				this.plateau.ecarterPersonnage(persoAEcarter);
			}
		}
	}

	private void percevoirRessources() {

	}

	private void calculDesPoints () {
		// calcul et affiche les points de chaque joueurs : acclame le vainqueur
	}
}
