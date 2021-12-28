package modele;

import controleur.Interaction;
public class Condottiere extends Personnage {

	public Condottiere() {
		super("Condottiere", Caracteristiques.CONDOTTIERE, 8);
	}

	public void percevoirRessourcesSpecifiques() {
		try {
			Joueur condottiere = new Joueur("Condottiere");

			// on va chercher le condottiere et on le stocke
			for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
				if (plateau.getPersonnage(i).getNom().equals("Condottiere")) {
					condottiere = plateau.getJoueur(i);
				}
			}

			// on ajoute une pièce pour chaque quartier militaire
			for (int j = 0; j < condottiere.nbQuartiersDansCite() - 1; j++) {
				String nomQuartier = condottiere.getCite()[j].getNom();
				if (nomQuartier.equals("prison") || nomQuartier.equals("tour de guet") || nomQuartier.equals("caserne") || nomQuartier.equals("forteresse")) {
					condottiere.ajouterPieces(1);
				}
			}
		} catch (NullPointerException npe) {}
	}

	public void utiliserPouvoir() {
		Boolean choix;

		do {
			System.out.print("\nVoulez-vous utiliser votre pouvoir de destruction ? ");
			choix = Interaction.lireOuiOuNon();
		} while (choix != true && choix != false);

		if (choix) {
			System.out.println("\nVoici la liste des joueurs et le contenu de leur cités : \n");
			
			for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
				// factorisation du code à localement
				Joueur joueur = plateau.getJoueur(i);
				Quartier[] cite = joueur.getCite();
				
				// on affiche tous les joueurs et personnages associés
				System.out.println((i+1) + " | " + joueur.getNom() + " | " + plateau.getPersonnage(i).getNom());
				
				// on vient chercher les cités correspondantes et on affiche leurs infos
				for ( int j = 0; j < joueur.nbQuartiersDansCite(); j++) {
					System.out.println("\t" + (j+1) + " | " + cite[j].getNom() + " | coût = " + cite[j].getCout());
				}
			}
			
			Joueur condottiere = new Joueur("condottiere");
			
			// affichage du trésor du joueur
			for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
				Personnage personnage = plateau.getPersonnage(i);
				
				if (personnage.getNom().equals("Condottiere")) {
					condottiere = plateau.getJoueur(i);
					System.out.println("\nPour information vous avez " + joueur.tresor() + " pièces d'or dans votre trésor.\n");
				}
			}
			
			int choixPersonnage = -1;
			
			// on demande le joueur à choisir
			do {
				System.out.println("Quel joueur choisissez vous ? (0 pour ne rien faire) ");
				choixPersonnage = Interaction.lireUnEntier(0, plateau.getNombrePersonnages()+1);
			} while (choixPersonnage == -1);
			// si 0 est choisi alors on stoppe le jeu
		
			int choixQuartier = 0;
		
			if (choixPersonnage > 0) {
				choixPersonnage--;
				Joueur cible = plateau.getJoueur(choixPersonnage);
			
				// on demande le quartier à détruire
				System.out.println("\nQuel quartier choisissez vous ?");
				choixQuartier = Interaction.lireUnEntier(1, cible.nbQuartiersDansCite()+1) - 1;
			
				System.out.println("_____________________________________________________________");
			
				int prixAPayer = cible.getCite()[choixQuartier].getCout() - 1;

				// contrôler s'il reste de l'or au joueur
				for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
					Personnage personnage = plateau.getPersonnage(i);
				
					if (personnage.getNom().equals("Condottiere")) {
						if (joueur.tresor() < prixAPayer) {
							System.out.println("\n\t- Votre capital ne vous permet pas de détruire ce quartier.\n");
						} else {
							System.out.println("\n\t- Félicitations ! Vous êtes solvable, vous pouvez donc détruire ce quartier l'esprit tranquille.");
							Quartier quartierADetruire = cible.getCite()[choixQuartier];
							String nomQuartierADetruire = quartierADetruire.getNom();

							System.out.println(
							"\n\t- Le quartier " +  
							nomQuartierADetruire +
							" possédé par " +  
							plateau.getJoueur(choixPersonnage).getNom() + " a été détruit."
							);
							
							int nb = plateau.getNombreJoueurs();
							Quartier[] cite = cible.getCite();
							int tailleCite = cible.getCite().length;
							
							// comptabilise le nombre de quartiers dans la cité concernée
							int nbQuartier = 0;
							for (int j = 0; j < tailleCite; j++) {
								try{
									if (!cite[j].equals(null)) {
										nbQuartier ++;
									}
								} catch (NullPointerException npe) {}
							}
							
							// message d'erreur dans le cas d'une cité complète :
							// cité de 7 quartiers ou plus pour les parties de 4 à 7 joueurs, 
							// ou une cité de 8 quartiers pour les parties à 2, 3 ou 8 joueurs.
							if (((nb == 2 || nb == 3 || nb == 8 ) && (nbQuartier == 8)) || ((nb >= 4 && nb <= 7) && (nbQuartier == 7))) {
								System.out.println("\nCité complète : impossible de détruire un quartier");
							} else {
								// destruction
								cible.getCite()[choixQuartier-1].getNom();
								cible.retirerQuartierDansCite(nomQuartierADetruire);
								
								condottiere.retirerPieces(prixAPayer);
								System.out.println("\n\t- Il vous reste " + condottiere.tresor() + " pièces d'or.\n");
								
								// mettre le quartier à la fin de la pioche = défausse
								plateau.getPioche().ajouter(quartierADetruire);
							}
						}
					}
				}				
			}
		}
	}

	public void utiliserPouvoirAvatar() {
		Boolean choix = Interaction.randomizerBoolean();

		if (choix) {			
			Joueur condottiere = new Joueur("condottiere");
						
			int choixPersonnage = Interaction.randomizer(plateau.getNombrePersonnages()+1);
					
			int choixQuartier = 0;
		
			if (choixPersonnage > 0) {
				choixPersonnage--;
				Joueur cible = plateau.getJoueur(choixPersonnage);
				choixQuartier = Interaction.randomizer(cible.nbQuartiersDansCite());
			
				int prixAPayer = cible.getCite()[choixQuartier].getCout() - 1;

				// contrôler s'il reste de l'or au joueur
				for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
					Personnage personnage = plateau.getPersonnage(i);
				
					if (personnage.getNom().equals("Condottiere")) {
						if (joueur.tresor() >= prixAPayer) {
							Quartier quartierADetruire = cible.getCite()[choixQuartier];
							String nomQuartierADetruire = quartierADetruire.getNom();

							System.out.println(
							"Le quartier " +  
							nomQuartierADetruire +
							" possédé par " +  
							plateau.getJoueur(choixPersonnage).getNom() + " a été détruit."
							);
							
							int nb = plateau.getNombreJoueurs();
							Quartier[] cite = cible.getCite();
							int tailleCite = cible.getCite().length;
							
							// comptabilise le nombre de quartiers dans la cité concernée
							int nbQuartier = 0;
							for (int j = 0; j < tailleCite; j++) {
								try{
									if (!cite[j].equals(null)) {
										nbQuartier ++;
									}
								} catch (NullPointerException npe) {}
							}
							
							// message d'erreur dans le cas d'une cité complète :
							// cité de 7 quartiers ou plus pour les parties de 4 à 7 joueurs, 
							// ou une cité de 8 quartiers pour les parties à 2, 3 ou 8 joueurs.
							if (!(((nb == 2 || nb == 3 || nb == 8 ) && (nbQuartier == 8)) || ((nb >= 4 && nb <= 7) && (nbQuartier == 7)))) {
								// destruction
								cible.getCite()[choixQuartier].getNom();
								cible.retirerQuartierDansCite(nomQuartierADetruire);
								
								condottiere.retirerPieces(prixAPayer);
								
								// mettre le quartier à la fin de la pioche = défausse
								plateau.getPioche().ajouter(quartierADetruire);
							}
						} else {
							System.out.println("Le joueur n'utilise pas son pouvoir.");
						}
					}
				}				
			} else {
				System.out.println("Le joueur n'utilise pas son pouvoir.");
			}
		} 
	}
}
