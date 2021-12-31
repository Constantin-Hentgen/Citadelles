package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import controleur.*;
import modele.*;

public class Jeu {
	private PlateauDeJeu plateau;
	private int numeroConfiguration;
	private int rangRoi;
	private int rangPremierATerminerCite;

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
		int numeroDuTour = 1;
		boolean partieFinie = true;

		do {
			System.out.println("\n\t\t--------------------------------------");
			System.out.println("\n\t\t\t    TOUR " + numeroDuTour + " DE JEU");
			System.out.println("\n\t\t--------------------------------------");

			tourDeJeu();

			System.out.println("\n\t\t--------------------------------------");
			System.out.println("\n\t\t\t    TOUR " + numeroDuTour + " TERMINÉ");

			numeroDuTour ++;
			
			// actualisation de l'état de la partie
			partieFinie = partieFinie();
			gestionCouronne();
			reinitialisationPersonnages();

			// on joue le tour
		} while (!partieFinie);

		calculDesPoints();

		// à la fin de la méthode
		jouer();
	}

	private void initialisation() {
		// initialisation du plateau de jeu et de la pioche
		this.plateau = Configuration.configurationDeBase();
		this.plateau.setPioche(Configuration.nouvellePioche());

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
		// on commence par enlever la couronne au précédent possesseur
		this.plateau.getJoueur(rangRoi).setPossedeCouronne(false);

		// si c'est le dernier joueur qui a la couronne alors on la donne au premier
		if (rangRoi == this.plateau.getNombreJoueurs()-1) {
			this.plateau.getJoueur(0).setPossedeCouronne(true);
		} else {
			this.plateau.getJoueur(rangRoi+1).setPossedeCouronne(true);
		}
	}

	private void reinitialisationPersonnages() {
		this.plateau.setListePersonnages(new Personnage[8]);

		// Ajout des 8 personnages
		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);

		Voleur voleur = new Voleur();
		plateau.ajouterPersonnage(voleur);

		Magicienne magicienne = new Magicienne();
		plateau.ajouterPersonnage(magicienne);

		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);

		Eveque eveque = new Eveque();
		plateau.ajouterPersonnage(eveque);

		Marchande marchande = new Marchande();
		plateau.ajouterPersonnage(marchande);

		Architecte architecte = new Architecte();
		plateau.ajouterPersonnage(architecte);

		Condottiere condottiere = new Condottiere();
		plateau.ajouterPersonnage(condottiere);
	}

	private boolean partieFinie() {
		// verifie si un joueur a une cité complète
		boolean partieFinie = true;
		// int nb = this.plateau.getNombreJoueurs();
		
		for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
			int nbQuartier = 0;
			
			try {
				for (int j = 0; j < 8; j++) {
					if (!this.plateau.getJoueur(i).getCite()[j].equals(null)) {
						nbQuartier ++;
					}
				}
			} catch (NullPointerException npe) {};

			// if ((((nb == 2 || nb == 3 || nb == 8 ) && (nbQuartier == 8)) || ((nb >= 4 && nb <= 7) && (nbQuartier == 7)))) {
			if (nbQuartier == 7) {
				rangPremierATerminerCite = i;
				partieFinie = true;
				break;
			} else {
				partieFinie = false;
			}
		}

		return partieFinie;
	}

	private void afficheMainJoueur() {
		System.out.println("Votre main :\n");

		for (int z = 0; z < this.plateau.getJoueur(0).getMain().size(); z++) {
			System.out.println("\t" + (z+1) + " | " + this.plateau.getJoueur(0).getMain().get(z).getNom() + " | " + this.plateau.getJoueur(0).getMain().get(z).getCout() + " PO");
		}

		System.out.println();
	}

	private void afficheJeuJoueur() {
		if (this.plateau.getJoueur(0).getCite()[0] != null) {
			System.out.println("\nVotre cité :\n");
											
			int counterQuartierDansCite = 1;
			for (int c = 0; c < this.plateau.getJoueur(0).getCite().length; c++) {
				try {
					if (!this.plateau.getJoueur(0).getCite()[c].equals(null)) {
						System.out.println("\t"+ (counterQuartierDansCite) + " | " + this.plateau.getJoueur(0).getCite()[c].getNom());
						counterQuartierDansCite ++;
					}
				} catch (NullPointerException npe) {};
			}
			System.out.println();
		}

		System.out.println("Votre main :\n");
		
		for (int z = 0; z < this.plateau.getJoueur(0).getMain().size(); z++) {
			System.out.println("\t" + (z+1) + " | " + this.plateau.getJoueur(0).getMain().get(z).getNom() + " | " + this.plateau.getJoueur(0).getMain().get(z).getCout() + " PO");
		}

		System.out.println("\nVotre trésor : " + this.plateau.getJoueur(0).tresor() + " PO.");							
	}

	private void afficheCiteJoueur (int i) {
		int counterQuartierDansCite = 1;
		
		if (this.plateau.getJoueur(i).getCite()[0] != null) {
			System.out.println("\nCité de " + this.plateau.getJoueur(i).getNom().toUpperCase() + "\n");

			for (int c = 0; c < this.plateau.getJoueur(i).getCite().length; c++) {
				try {
					if (!this.plateau.getJoueur(i).getCite()[c].equals(null)) {
						System.out.println("\t"+ (counterQuartierDansCite) + " | " + this.plateau.getJoueur(i).getCite()[c].getNom());
						counterQuartierDansCite ++;
					}
				} catch (NullPointerException npe) {};
			}
		}
	}

	private void tourDeJeu() {
		choixPersonnages();
		System.out.println();

		// on appelle les personnages un par un pour les faire jouer
		for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {

			System.out.println("-----------------------------");
			System.out.println("\nC'est au tour de " + this.plateau.getJoueur(i).getNom() + " qui joue " + this.plateau.getJoueur(i).getPersonnage().getNom() + ".");
 
			// checker si il est assassiné
			if (!this.plateau.getJoueur(i).getPersonnage().getAssassine()) {
				// donner à chacun ses ressources
				// donner à ceux concernés les ressources spécifiques
				percevoirRessources(this.plateau.getJoueur(i).getPersonnage());

				boolean choix;
				int choixQuartier;

				// Dans le cas où le joueur est humain
				if (i == 0) {
					afficheJeuJoueur();

					System.out.println("\nVoulez-vous une nouvelle carte ?");
					boolean isNouvelleCarte = Interaction.lireOuiOuNon();

					if (isNouvelleCarte) {
						Quartier a = this.plateau.getPioche().piocher();
						Quartier b = this.plateau.getPioche().piocher();
						// pioche d'une nouvelle carte
						System.out.println("Faites votre choix entre ces 2 cartes : ");

						System.out.println("\n\t" + 1 + " | " + a.getNom() + " | " + a.getCout() + " PO.");
						System.out.println("\t" + 2 + " | " + b.getNom() + " | " + b.getCout() + " PO.");

						int choixCarteQuartier = Interaction.lireUnEntier(1, 3);

						if (choixCarteQuartier == 1) {
							this.plateau.getJoueur(i).ajouterQuartierDansMain(a);
							plateau.getPioche().ajouter(b);
						} else {
							this.plateau.getJoueur(i).ajouterQuartierDansMain(b);
							plateau.getPioche().ajouter(a);
						}
					} else {
						this.plateau.getJoueur(i).ajouterPieces(2);
						System.out.println("\n2 pièces ont été ajoutées au trésor du joueur.");
					}

					this.plateau.getJoueur(0).getPersonnage().utiliserPouvoir();
					afficheJeuJoueur();

					int nbConstruction = 1;

					if (this.plateau.getJoueur(i).getPersonnage().getNom().equals("Architecte")) {
						nbConstruction = 3;
					}

					for (int x = 0; x < nbConstruction; x++) {
						System.out.println("\nQuel quartier voulez-vous construire ? (0 pour ne rien faire )");

						choixQuartier = Interaction.lireUnEntier(0, this.plateau.getJoueur(i).nbQuartiersDansMain()+1);
						
						System.out.println();

						if (choixQuartier > 0) {
							choixQuartier --;

							if (this.plateau.getJoueur(i).getMain().get(choixQuartier).getCout() <= this.plateau.getJoueur(i).tresor()) {
								this.plateau.getJoueur(i).retirerPieces(this.plateau.getJoueur(i).getMain().get(choixQuartier).getCout());
								this.plateau.getJoueur(i).getPersonnage().construire(this.plateau.getJoueur(i).getMain().get(choixQuartier));
								this.plateau.getJoueur(i).retirerQuartierDansMain(this.plateau.getJoueur(i).getMain().get(choixQuartier));
				
								afficheCiteJoueur(i);
							} else {
								System.out.println("\nVous n'avez pas les moyens de construire ce quartier.");
							}
						}
						afficheMainJoueur();
					}
				} else {
					boolean isNouvelleCarte = Interaction.randomizerBoolean();

					if (isNouvelleCarte) {
						Quartier a = this.plateau.getPioche().piocher();
						Quartier b = this.plateau.getPioche().piocher();
						// pioche d'une nouvelle carte

						int choixCarteQuartier = Interaction.randomizer(1) + 1;

						if (choixCarteQuartier == 1) {
							this.plateau.getJoueur(i).ajouterQuartierDansMain(a);
							plateau.getPioche().ajouter(b);
						} else {
							this.plateau.getJoueur(i).ajouterQuartierDansMain(b);
							plateau.getPioche().ajouter(a);
						}
					} else {
						this.plateau.getJoueur(i).ajouterPieces(2);
						System.out.println("2 pièces ont été ajoutées au trésor du joueur.");
					}

					// Dans le cas où le joueur est un robot
					this.plateau.getJoueur(i).getPersonnage().utiliserPouvoirAvatar();
					choix = Interaction.randomizerBoolean();

					if (choix) {
						choixQuartier = Interaction.randomizer(this.plateau.getJoueur(i).nbQuartiersDansMain()-1);
						this.plateau.getJoueur(i).retirerPieces(this.plateau.getJoueur(i).getMain().get(choixQuartier).getCout());
						this.plateau.getJoueur(i).getPersonnage().construire(this.plateau.getJoueur(i).getMain().get(choixQuartier));
						this.plateau.getJoueur(i).retirerQuartierDansMain(this.plateau.getJoueur(i).getMain().get(choixQuartier));

						afficheCiteJoueur(i);

						System.out.println("\nTrésor de " + this.plateau.getJoueur(i).getNom() + " est de " + this.plateau.getJoueur(i).tresor() + " PO.");
					}
				}
			}
			// try {
			// 	TimeUnit.SECONDS.sleep(2);
			// } catch (InterruptedException e) {}
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
		System.out.println(this.plateau.getJoueur(this.rangRoi).getNom().toUpperCase() + " a la couronne et choisit donc en premier son personnage.");

		// le joueur humain est systèmatiquement le joueur1

		int choix;

		// si le roi c'est moi
		if (rangRoi == 0) {
			afficheJeuJoueur();
			System.out.println("\nVous avez la couronne, c'est à vous de choisir un personnage :\n");
			for (int i = 0; i < this.plateau.getNombrePersonnages(); i++) {
				System.out.println("\t" + (i+1) + " | " + this.plateau.getPersonnage(i).getNom());
			}
			System.out.println();

			choix = Interaction.lireUnEntier(1, this.plateau.getNombrePersonnages()+1)-1;
			this.plateau.getPersonnage(choix).setJoueur(this.plateau.getJoueur(0));
			System.out.println("Vous avez choisi de jouer le personnage " + this.plateau.getJoueur(0).getPersonnage().getNom() + ".\n");
		} else {
			// si le roi est un bot
			choix = Interaction.randomizer(this.plateau.getNombrePersonnages()-1);
			this.plateau.getPersonnage(choix).setJoueur(this.plateau.getJoueur(this.rangRoi));
			System.out.println(this.plateau.getJoueur(this.rangRoi).getNom().toUpperCase() + " a choisit son personnage.\n");
		}
		
		persoAEcarter = this.plateau.getPersonnage(choix);
		this.plateau.ecarterPersonnage(persoAEcarter);

		// on boucle pour le reste des joueurs
		for (int h = 0; h < this.plateau.getNombreJoueurs(); h++) {
			// si ce n'est pas le roi
			if (h != this.rangRoi) {
				
				if (h == 0) {
					afficheJeuJoueur();
					// affichage des choix disponibles
					System.out.println("\nC'est à vous de choisir un personnage :\n");

					for (int i = 0; i < this.plateau.getNombrePersonnages(); i++) {
						System.out.println("\t" + (i+1) + " | " + this.plateau.getPersonnage(i).getNom());
					}

					System.out.println();
					
					choix = Interaction.lireUnEntier(1, this.plateau.getNombrePersonnages()+1)-1;
					this.plateau.getPersonnage(choix).setJoueur(this.plateau.getJoueur(0));
					System.out.println("Vous avez choisi de jouer le personnage " + this.plateau.getJoueur(0).getPersonnage().getNom() + ".\n");
				} else {
					choix = Interaction.randomizer(this.plateau.getNombrePersonnages()-1);
					this.plateau.getPersonnage(choix).setJoueur(this.plateau.getJoueur(h));
					System.out.println(this.plateau.getJoueur(h).getNom().toUpperCase() + " a choisit son personnage.");
				}

				persoAEcarter = this.plateau.getPersonnage(choix);
				this.plateau.ecarterPersonnage(persoAEcarter);
			}
		}
	}

	private void percevoirRessources(Personnage p) {
		p.percevoirRessourcesSpecifiques();
		System.out.println();
	}

	private void calculDesPoints () {
		// calcul et affiche les points de chaque joueurs : acclame le vainqueur
		// affiche le score POUR CHAQUE JOUEUR

		// System.out.println("le premier à avoir fini sa cité : " + this.plateau.getJoueur(rangPremierATerminerCite).getNom().toUpperCase());
		int nbPoints;
		List<String> typeQuartiers = new ArrayList<String>();
		ArrayList<Integer> tableauScores = new ArrayList<>();
		ArrayList<String> tableauJoueurs = new ArrayList<>();

		for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
			nbPoints = 0;
			
			// + 4 points si il est le premier à avoir fini sa cité
			if (i == rangPremierATerminerCite) {
				nbPoints += 4;
			} else if (i != rangPremierATerminerCite && this.plateau.getJoueur(i).nbQuartiersDansCite() == 7) {
				// + 2 points si il a fini sa cité mais il est pas le premier
				// System.out.println("ce joueur a fini mais pas en premier : " + this.plateau.getJoueur(i).getNom().toUpperCase());
				nbPoints += 2;
			}
			
			for (int j = 0; j < this.plateau.getJoueur(i).nbQuartiersDansCite(); j++) {
				// + somme des couts de construction
				nbPoints += this.plateau.getJoueur(i).getCite()[j].getCout();

				typeQuartiers.add(this.plateau.getJoueur(i).getCite()[j].getType());
				
				// ajouter les bonus des différentes merveilles
			};
			
			// je fais une liste de tous les types puis je supprime les doublons : si la taille finale = 5 alors + 3
			Set<String> typeQuartiersWithoutDuplicates = new LinkedHashSet<String>(typeQuartiers); 
			
			// + 3 points si il y a un quartier de chaque section
			// System.out.println("La variété des quartiers : " + typeQuartiersWithoutDuplicates + " | " + typeQuartiersWithoutDuplicates.size());
			if (typeQuartiersWithoutDuplicates.size() == 5) {
				nbPoints += 3;
			}

			tableauScores.add(nbPoints);
			tableauJoueurs.add(this.plateau.getJoueur(i).getNom());

			
			// System.out.println(this.plateau.getJoueur(i).getNom().toUpperCase() + " a terminé la partie avec un total de " + nbPoints + " points.\n");
		}
		
		sortList(tableauJoueurs, tableauScores);
		
		for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
			System.out.println("\n\t" + (i+1) + " | " + tableauJoueurs.get(i) + " | " + tableauScores.get(i) + " points.");
		}

		// si il y a égalité alors c'est celui qui avait le personnage de rang le plus élevé
	}

	private void sortList(ArrayList<String> stringList, ArrayList<Integer> integerList) {
		int tempInt = 0;
		String tempString = "";
		
		for (int j = 0; j < integerList.size(); j++) {
			for (int i = 0; i < integerList.size(); i++) {
				try {
					if (integerList.get(i) < integerList.get(i+1)) {
						tempInt = integerList.get(i);
						integerList.set(i, integerList.get(i+1));
						integerList.set(i+1, tempInt);

						tempString = stringList.get(i);
						stringList.set(i, stringList.get(i+1));
						stringList.set(i+1, tempString);
					}
				} catch (IndexOutOfBoundsException ibe) {};
			}
		}
	}
}
