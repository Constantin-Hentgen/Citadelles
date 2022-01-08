package application;

import java.util.ArrayList;
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
				System.out.println(this.plateau.getJoueur(i).getNom() + " a pioché.");
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

	private void afficherMaMain() {
		System.out.println("\nVotre main :\n");

		for (int z = 0; z < moi().getMain().size(); z++) {
			System.out.println("\t" + (z+1) + " | " + moi().getMain().get(z).getNom() + " | " + moi().getMain().get(z).getCout() + " PO");
		}

		System.out.println();
	}

	private Joueur moi() {
		Joueur moi = new Joueur("placeholder");
		
		// je choppe le joueur 1
		for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
			if (this.plateau.getJoueur(i).getNom().equals("joueur1")) {
				moi = this.plateau.getJoueur(i);
			}
		}

		return moi;
	}

	private void afficheJeuJoueur() {		
		try {
			if (moi().getCite()[0] != null) {
				System.out.println("\nVotre cité :\n");

				for (int c = 0; c < moi().nbQuartiersDansCite(); c++) {
					if (!moi().getCite()[c].equals(null)) {
						// System.out.println(moi().getCite()[c].getType());
						System.out.println("\t"+ (c+1) + " | " + moi().getCite()[c].getNom() + " | " + moi().getCite()[c].getCout() + "PO | " + moi().getCite()[c].getType());
						// System.out.println("\t"+ (c+1) + " | " + moi().getCite()[c].getNom() + " | " + moi().getCite()[c].getCout() + "PO");
					}
				}
			}
		} catch (NullPointerException npe) {};
	
		System.out.println("\nVotre main :\n");
		try {
			for (int z = 0; z < moi().nbQuartiersDansMain(); z++) {
				System.out.println("\t" + (z+1) + " | " + moi().getMain().get(z).getNom() + " | " + moi().getMain().get(z).getCout() + " PO | " + moi().getMain().get(z).getType().toLowerCase());
			}
					
			System.out.println("\nVotre trésor : " + moi().tresor() + " PO.");							
		} catch (NullPointerException npe) {};
	}

	private void afficheCiteJoueur (int i) {
		int counterQuartierDansCite = 1;
		
		if (this.plateau.getJoueur(i).getCite()[0] != null) {
			System.out.println("\nCité de " + this.plateau.getJoueur(i).getNom().toUpperCase() + "\n");

			for (int c = 0; c < this.plateau.getJoueur(i).getCite().length; c++) {
				try {
					if (!this.plateau.getJoueur(i).getCite()[c].equals(null)) {
						System.out.println("\t"+ (counterQuartierDansCite) + " | " + this.plateau.getJoueur(i).getCite()[c].getNom() + " | " + this.plateau.getJoueur(i).getCite()[c].getCout() + " PO | " + this.plateau.getJoueur(i).getCite()[c].getType().toLowerCase());
						counterQuartierDansCite ++;
					}
				} catch (NullPointerException npe) {};
			}
			System.out.println();
		}
	}

	private void sortJoueurSelonRangPersonnage() {
		System.out.println();

		ArrayList<Joueur> tempJoueur = new ArrayList<>();
		ArrayList<Integer> tempRang = new ArrayList<>();

		Joueur[] listeJoueurRemplacable = new Joueur[4];

		// j'alimente les arraylist
		for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
			Joueur j = this.plateau.getJoueur(i);
			tempJoueur.add(j);
			tempRang.add(j.getPersonnage().getRang());
		}

		sortListJoueur(tempJoueur, tempRang);

		// puis stocker cette ordre dans la vraie liste
		for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
			Joueur j = tempJoueur.get(3-i);
			listeJoueurRemplacable[i] = j;
		}

		this.plateau.setListeJoueurs(listeJoueurRemplacable);

		for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
			Joueur j = this.plateau.getJoueur(i);
			System.out.println(j.getNom() + " | " + j.getPersonnage().getNom() + " | " + j.getPersonnage().getRang());
		}

		System.out.println();
	}

	private void tourDeJeu() {
		choixPersonnages();
		sortJoueurSelonRangPersonnage();

		for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
			System.out.println("-----------------------------------------------");
			System.out.println("\nC'est au tour de " + this.plateau.getJoueur(i).getNom() + " qui joue " + this.plateau.getJoueur(i).getPersonnage().getNom() + ".");


 			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println(this.plateau.getJoueur(i).getNom() + " | nb de cartes dans sa main | " + this.plateau.getJoueur(i).nbQuartiersDansMain());
			System.out.println();

			for (int l = 0; l<this.plateau.getJoueur(i).nbQuartiersDansMain();l++)
				{
					System.out.println("\t\t"+this.plateau.getJoueur(i).getMain().get(l).getNom());
				}
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");



			// checker si il est assassiné
			if (!this.plateau.getJoueur(i).getPersonnage().getAssassine()) {
				// donner à chacun ses ressources
				// donner à ceux concernés les ressources spécifiques
				percevoirRessources(i);

				// boolean choix;
				int choixQuartier;

				// Dans le cas où le joueur est humain
				if (this.plateau.getJoueur(i).equals(moi())) {
					this.plateau.getJoueur(i).getPersonnage().utiliserPouvoir();
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
							Quartier quartierChoisi = this.plateau.getJoueur(i).getMain().get(choixQuartier);

							// après sélection du quartier je vérifie qu'il n'existe pas déjà dans la cité
							if (this.plateau.getJoueur(i).quartierPresentDansCite(quartierChoisi.getNom())) {
								System.out.println("Vous êtes sur le point de faire un doublon.");
							}
						
							

							// POUVOIR DE LA CARRIÈRE
							// je peux construire autant de bâtiments identiques que je souhaite si je possède une carrière
							if (this.plateau.getJoueur(i).quartierPresentDansCite("carrière")) {
								if (this.plateau.getJoueur(i).getMain().get(choixQuartier).getCout() <= this.plateau.getJoueur(i).tresor()) {
									construireQuartier(i, choixQuartier);
									afficheCiteJoueur(i);
								} else {
									System.out.println("\nVous n'avez pas les moyens de construire ce quartier.");
								}
							} else {
								// POUVOIR DE LA MANUFACTURE
								if (moi().quartierPresentDansCite("manufacture") && quartierChoisi.getType().equals("MERVEILLE")) {
									if (moi().getMain().get(choixQuartier).getCout() - 1 <= moi().tresor() && !moi().quartierPresentDansCite(quartierChoisi.getNom())) {
										moi().ajouterPieces(1);
										construireQuartier(i, choixQuartier);
										afficheCiteJoueur(i);	
									}
								} else if (moi().getMain().get(choixQuartier).getCout() <= moi().tresor() && !moi().quartierPresentDansCite(quartierChoisi.getNom())) {
									construireQuartier(i, choixQuartier);
									afficheCiteJoueur(i);
								} else if (moi().getMain().get(choixQuartier).getCout() > moi().tresor() ) {
									System.out.println("\nVous n'avez pas les moyens de construire ce quartier.");
								} else {
									System.out.println("\nDoublon interdit.");
								}
							}
						}
						afficherMaMain();
					}
				} else {					
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {}

					this.plateau.getJoueur(i).getPersonnage().utiliserPouvoirAvatar();
					System.out.println();

					int nbConstruction = 1;

					if (this.plateau.getJoueur(i).getPersonnage().getNom().equals("Architecte")) {
						nbConstruction = 3;
					}

					for (int x = 0; x < nbConstruction; x++) {
						choixQuartier = Interaction.randomizer(this.plateau.getJoueur(i).nbQuartiersDansMain()+1);
						
						if (choixQuartier > 0) {
								choixQuartier --;
								Quartier quartierChoisi = this.plateau.getJoueur(i).getMain().get(choixQuartier);

							// après sélection du quartier je vérifie qu'il n'existe pas déjà dans la cité


							// POUVOIR DE LA CARRIÈRE
							// je peux construire autant de bâtiments identiques que je souhaite si je possède une carrière
							if (this.plateau.getJoueur(i).quartierPresentDansCite("carrière")) {
								if (this.plateau.getJoueur(i).getMain().get(choixQuartier).getCout() <= this.plateau.getJoueur(i).tresor()) {
									construireQuartier(i, choixQuartier);
									afficheCiteJoueur(i);
								}
							} else {
								// POUVOIR DE LA MANUFACTURE
								if (this.plateau.getJoueur(i).quartierPresentDansCite("manufacture") && quartierChoisi.getType().equals("MERVEILLE")) {
									if (this.plateau.getJoueur(i).getMain().get(choixQuartier).getCout() - 1 <= this.plateau.getJoueur(i).tresor() && !this.plateau.getJoueur(i).quartierPresentDansCite(quartierChoisi.getNom())) {
										construireQuartier(i, choixQuartier);
										this.plateau.getJoueur(i).ajouterPieces(1);
										System.out.println("Le joueur " + this.plateau.getJoueur(i).getNom() + " a construit le quartier " + quartierChoisi.getNom() + " à moindre coût grâce au pouvoir de la manufacture.");
										afficheCiteJoueur(i);
									}
								} else if (this.plateau.getJoueur(i).getMain().get(choixQuartier).getCout() <= this.plateau.getJoueur(i).tresor() && !this.plateau.getJoueur(i).quartierPresentDansCite(quartierChoisi.getNom())) {
									construireQuartier(i, choixQuartier);
									System.out.println("Le joueur " + this.plateau.getJoueur(i).getNom() + " a construit " + quartierChoisi.getNom());
									afficheCiteJoueur(i);
								}
							}

						}
					}
				}
			}
		}
	}

	private void construireQuartier(int i, int choixQuartier) {
		this.plateau.getJoueur(i).retirerPieces(this.plateau.getJoueur(i).getMain().get(choixQuartier).getCout());
		this.plateau.getJoueur(i).getPersonnage().construire(this.plateau.getJoueur(i).getMain().get(choixQuartier));
		this.plateau.getJoueur(i).retirerQuartierDansMain(this.plateau.getJoueur(i).getMain().get(choixQuartier));
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

		// le joueur humain est systématiquement le joueur1

		int choix;

		// si le roi c'est moi
		if (this.plateau.getJoueur(rangRoi).getNom().equals("joueur1")) {
			afficheJeuJoueur();
			System.out.println("\nVous avez la couronne, c'est à vous de choisir un personnage :\n");
			for (int i = 0; i < this.plateau.getNombrePersonnages(); i++) {
				System.out.println("\t" + (i+1) + " | " + this.plateau.getPersonnage(i).getNom());
			}
			System.out.println();

			choix = Interaction.lireUnEntier(1, this.plateau.getNombrePersonnages()+1)-1;
			this.plateau.getPersonnage(choix).setJoueur(moi());
			System.out.println("Vous avez choisi de jouer le personnage " + moi().getPersonnage().getNom() + ".\n");
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
				// si c'est moi
				if (this.plateau.getJoueur(h) == moi()) {
					afficheJeuJoueur();
					// affichage des choix disponibles
					System.out.println("\nC'est à vous de choisir un personnage :\n");

					for (int i = 0; i < this.plateau.getNombrePersonnages(); i++) {
						System.out.println("\t" + (i+1) + " | " + this.plateau.getPersonnage(i).getNom());
					}

					System.out.println();
					
					choix = Interaction.lireUnEntier(1, this.plateau.getNombrePersonnages()+1)-1;
					this.plateau.getPersonnage(choix).setJoueur(moi());
					System.out.println("Vous avez choisi de jouer le personnage " + moi().getPersonnage().getNom() + ".\n");
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

	private void percevoirRessources(int i) {
		this.plateau.getJoueur(i).getPersonnage().percevoirRessourcesSpecifiques();
		System.out.println();

		if (this.plateau.getJoueur(i).equals(moi())) {
			afficheJeuJoueur();

			// POUVOIR ÉCOLE DE MAGIE
			if (moi().quartierPresentDansCite("école de magie")) {
				System.out.println("\nVous possédez une école de magie, choisissez son type pour ce tour.\n");

				// on demande au joueur de donner un type pour l'école de magie
				for (int w = 0; w < Quartier.TYPE_QUARTIERS.length; w ++) {
					System.out.println("\t" + (w + 1) + " | " + Quartier.TYPE_QUARTIERS[w].toLowerCase());
				}

				System.out.println();
				int choixTypeQuartier = Interaction.lireUnEntier(1, Quartier.TYPE_QUARTIERS.length) - 1;

				for (int aie = 0; aie < this.plateau.getJoueur(i).nbQuartiersDansCite(); aie ++) {
					if (this.plateau.getJoueur(i).getCite()[aie].getNom().equals("école de magie")) {
						this.plateau.getJoueur(i).getCite()[aie].setType(Quartier.TYPE_QUARTIERS[choixTypeQuartier]);
					}
				}
			}
	
			System.out.println("\nVoulez-vous une nouvelle carte ?");
			boolean isNouvelleCarte = Interaction.lireOuiOuNon();
	
			if (isNouvelleCarte) {
				Quartier a = this.plateau.getPioche().piocher();
				Quartier b = this.plateau.getPioche().piocher();
				// pioche d'une nouvelle carte


				// POUVOIR BIBLIOTHÈQUE
				if (moi().quartierPresentDansCite("bibliothèque")) {

					moi().ajouterQuartierDansMain(a);
					plateau.getPioche().ajouter(b);
					
					moi().ajouterQuartierDansMain(b);
					plateau.getPioche().ajouter(a);

					System.out.println(moi().getNom() + " a pioché 2 quartiers grâce au pouvoir de la Bibliothèque.");
				}



				System.out.println("Faites votre choix entre ces 2 cartes : ");

				System.out.println("\n\t" + 1 + " | " + a.getNom() + " | " + a.getCout() + " PO | " + a.getType().toLowerCase());
				System.out.println("\t" + 2 + " | " + b.getNom() + " | " + b.getCout() + " PO | "  + b.getType().toLowerCase());

				int choixCarteQuartier = Interaction.lireUnEntier(1, 3);

				if (choixCarteQuartier == 1) {
					moi().ajouterQuartierDansMain(a);
					System.out.println(moi().getNom() + " a pioché.");
					plateau.getPioche().ajouter(b);
				} else {
					moi().ajouterQuartierDansMain(b);
					System.out.println(moi().getNom() + " a pioché.");
					plateau.getPioche().ajouter(a);
				}
			} else {
				moi().ajouterPieces(2);
				System.out.println("\n2 pièces ont été ajoutées au trésor du joueur.");
			}

			// POUVOIR FORGE
			if (moi().quartierPresentDansCite("forge")) {
				System.out.println("\nVoulez-vous payer 2 PO pour piocher 3 cartes ?\n");
				boolean choixForge = Interaction.lireOuiOuNon();

				if (choixForge && moi().tresor() >= 2) {
					moi().ajouterQuartierDansMain(this.plateau.getPioche().piocher());
					moi().ajouterQuartierDansMain(this.plateau.getPioche().piocher());
					moi().ajouterQuartierDansMain(this.plateau.getPioche().piocher());
				}
			}

		} else {
			boolean isNouvelleCarte = Interaction.randomizerBoolean();
	
			// POUVOIR ÉCOLE DE MAGIE
			if (this.plateau.getJoueur(i).quartierPresentDansCite("école de magie")) {

				// on demande au joueur de donner un type pour l'école de magie

				int choixTypeQuartier = Interaction.randomizer(Quartier.TYPE_QUARTIERS.length-1);

				for (int aie = 0; aie < this.plateau.getJoueur(i).nbQuartiersDansCite(); aie ++) {
					if (this.plateau.getJoueur(i).getCite()[aie].getNom().equals("école de magie")) {
						this.plateau.getJoueur(i).getCite()[aie].setType(Quartier.TYPE_QUARTIERS[choixTypeQuartier]);
					}
				}
			}

			if (isNouvelleCarte) {
				Quartier a = this.plateau.getPioche().piocher();
				Quartier b = this.plateau.getPioche().piocher();
				// pioche d'une nouvelle carte
		
				int choixCarteQuartier = Interaction.randomizer(2) + 1;
	
				if (choixCarteQuartier == 1) {
					this.plateau.getJoueur(i).ajouterQuartierDansMain(a);
					System.out.println(this.plateau.getJoueur(i).getNom() + " a pioché.");
					plateau.getPioche().ajouter(b);
				} else {
					this.plateau.getJoueur(i).ajouterQuartierDansMain(b);
					System.out.println(this.plateau.getJoueur(i).getNom() + " a pioché.");
					plateau.getPioche().ajouter(a);
				}
			} else {
				this.plateau.getJoueur(i).ajouterPieces(2);
				System.out.println("\n2 pièces ont été ajoutées au trésor du joueur.");
			}


			// POUVOIR FORGE
			if (moi().quartierPresentDansCite("forge")) {
				boolean choixForge = Interaction.randomizerBoolean();

				if (choixForge && moi().tresor() >= 2) {
					moi().ajouterQuartierDansMain(this.plateau.getPioche().piocher());
					moi().ajouterQuartierDansMain(this.plateau.getPioche().piocher());
					moi().ajouterQuartierDansMain(this.plateau.getPioche().piocher());
				}
			}
		}
	}

	private void calculDesPoints () {
		// calcul et affiche les points de chaque joueurs : acclame le vainqueur
		// affiche le score POUR CHAQUE JOUEUR

		// System.out.println("le premier à avoir fini sa cité : " + this.plateau.getJoueur(rangPremierATerminerCite).getNom().toUpperCase());
		int nbPoints;
		List<String> typeQuartiers = new ArrayList<String>();
		ArrayList<Integer> tableauScores = new ArrayList<>();
		ArrayList<String> tableauJoueurs = new ArrayList<>();


		// ITÉRATION PAR JOUEUR
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
			
			// ITÉRATION PAR QUARTIER
			for (int j = 0; j < this.plateau.getJoueur(i).nbQuartiersDansCite(); j++) {
				// + somme des couts de construction
				nbPoints += this.plateau.getJoueur(i).getCite()[j].getCout();

				typeQuartiers.add(this.plateau.getJoueur(i).getCite()[j].getType());
				
				// POUVOIR DU CAPITOLE
				// le joueur a dans sa cité la CAPITOLE
				if (this.plateau.getJoueur(i).getCite()[j].getNom().equals("capitole")) {
					int quartierDeMemeCouleur = 0;

					for (int r = 0; r < this.plateau.getJoueur(i).nbQuartiersDansCite(); r++) {
						quartierDeMemeCouleur = 0;
						for (int k = 0; k < this.plateau.getJoueur(i).nbQuartiersDansCite(); k++) {
							if (this.plateau.getJoueur(i).getCite()[r].getType().equals(this.plateau.getJoueur(i).getCite()[r].getType())) {
								quartierDeMemeCouleur ++;
							}
							if (quartierDeMemeCouleur >= 3) {
								nbPoints += 3;
								break;
							}
						}
					}
				}

				// POUVOIR COUR DES MIRACLES
				if (this.plateau.getJoueur(i).equals(moi()) && this.plateau.getJoueur(i).quartierPresentDansCite("cour des miracles")) {
					System.out.println();

					// on demande au joueur de donner un type pour l'école de magie
					for (int w = 0; w < Quartier.TYPE_QUARTIERS.length; w ++) {
						System.out.println("\t" + (w + 1) + " | " + Quartier.TYPE_QUARTIERS[w].toLowerCase());
					}

					System.out.println();
					int choixTypeQuartier = Interaction.lireUnEntier(1, Quartier.TYPE_QUARTIERS.length) - 1;

					for (int aie = 0; aie < this.plateau.getJoueur(i).nbQuartiersDansCite(); aie ++) {
						if (this.plateau.getJoueur(i).getCite()[aie].getNom().equals("cour des miracles")) {
							this.plateau.getJoueur(i).getCite()[aie].setType(Quartier.TYPE_QUARTIERS[choixTypeQuartier]);
						}
					}
				} else {
					int choixTypeQuartier = Interaction.randomizer(Quartier.TYPE_QUARTIERS.length-1);

					for (int aie = 0; aie < this.plateau.getJoueur(i).nbQuartiersDansCite(); aie ++) {
						if (this.plateau.getJoueur(i).getCite()[aie].getNom().equals("cour des miracles")) {
							this.plateau.getJoueur(i).getCite()[aie].setType(Quartier.TYPE_QUARTIERS[choixTypeQuartier]);
						}
					}
				}
					
				// POUVOIR FONTAINE AUX SOUHAITS
				if (this.plateau.getJoueur(i).quartierPresentDansCite("fontaine aux souhaits")) {
					if (this.plateau.getJoueur(i).getCite()[j].getType().equals("MERVEILLE")) {
						nbPoints ++;
					}
				}
			};

			// POUVOIR DE LA BASILIQUE
			if (this.plateau.getJoueur(i).quartierPresentDansCite("basilique")) {
				// le joueur a dans sa cité la BASILIQUE ET que le quartier en question a un COUT impair
				for (int k = 0; k < this.plateau.getJoueur(i).nbQuartiersDansCite(); k++) {
					if (this.plateau.getJoueur(i).getCite()[k].getCout()%2 != 0) {
						nbPoints ++;
					}
				}
			}

			// POUVOIR DU DRACOPORT
			if (this.plateau.getJoueur(i).quartierPresentDansCite("dracoport")) {
				nbPoints += 2;
			}

			// POUVOIR TRÉSOR IMPÉRIAL
			if (this.plateau.getJoueur(i).quartierPresentDansCite("trésor impérial")) {
				nbPoints += this.plateau.getJoueur(i).tresor();
			}

			// POUVOIR STATUE ÉQUESTRE
			if (this.plateau.getJoueur(i).quartierPresentDansCite("statue équestre")) {
				if (this.plateau.getJoueur(i).getPossedeCouronne()) {
					nbPoints += 5;
				}
			}

			// POUVOIR SALLE DES CARTES
			if (this.plateau.getJoueur(i).quartierPresentDansCite("salle des cartes")) {
				nbPoints += this.plateau.getJoueur(i).nbQuartiersDansMain();
			}





			// TRAITEMENT LIÉ À L'AFFICHAGE DES RÉSULTATS
			
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
		

		// traitement pour l'affichage propre du tableau des scores
		sortList(tableauJoueurs, tableauScores);
		
		for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
			System.out.println("\n\t\t\t" + (i+1) + " | " + tableauJoueurs.get(i) + " | " + tableauScores.get(i) + " points.");
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

	private void sortListJoueur(ArrayList<Joueur> joueurList, ArrayList<Integer> integerList) {
		int tempInt = 0;
		Joueur tempJoueur;
		
		for (int j = 0; j < integerList.size(); j++) {
			for (int i = 0; i < integerList.size(); i++) {
				try {
					if (integerList.get(i) < integerList.get(i+1)) {
						tempInt = integerList.get(i);
						integerList.set(i, integerList.get(i+1));
						integerList.set(i+1, tempInt);

						tempJoueur = joueurList.get(i);
						joueurList.set(i, joueurList.get(i+1));
						joueurList.set(i+1, tempJoueur);
					}
				} catch (IndexOutOfBoundsException ibe) {};
			}
		}
	}
}
