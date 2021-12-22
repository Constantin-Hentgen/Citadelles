package modele;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Condottiere extends Personnage {

	public Condottiere(){
		super("Condottiere", Caracteristiques.CONDOTTIERE, 8); // checker le rang de condottiere
	}

	public void percevoirRessourcesSpecifiques(){
		// percevoir ressources : 1 or pour chaque bâtiment militaire

		// on vient chercher le condottiere
		try {
			System.out.println("plateau.getNombrePersonnages() : " + plateau.getNombrePersonnages());
			for (int i = 0; i < plateau.getNombreJoueurs(); i++){
				System.out.println(plateau.getPersonnage(i).getNom());
				if (plateau.getPersonnage(i).getNom().equals("Condottiere")){
					System.out.println("CONDOTTIERE TROUVÉ");
				}
			}
		} catch (NullPointerException npe) {
			System.out.println("ça pointe vers du NULL");
		}
	}

	@Override
	public void utiliserPouvoir() {
		System.out.print("\nVoulez-vous utiliser votre pouvoir de destruction ? o/n : ");
		
		String choix;
		
		// on s'assure que la réponse apporté sera o ou n
		Scanner sc = new Scanner(System.in);

		do {
			sc = new Scanner(System.in);
			choix = sc.next();
		} while (!choix.equals("o") && !choix.equals("n"));


		if (choix.equals("o")){
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

		int choixPersonnage = 0;
		boolean isInteger;
			
		// on demande le joueur à choisir
		// on s'assure que l'entrée est bien un entier et qu'il est logique
		do {
			do {
				try {
					System.out.print("Quel joueur choisissez vous ? (0 pour ne rien faire) ");
					sc = new Scanner(System.in);
					choixPersonnage = sc.nextInt();
					isInteger = true;
				} catch (InputMismatchException e) {
					isInteger = false;
				}
			} while (isInteger == false);
		} while (choixPersonnage < 0 || choixPersonnage > plateau.getNombreJoueurs());

		int choixQuartier = 0;
			
		// on demande le quartier à détruire
		// on s'assure que l'entrée est bien un entier et qu'il est logique
		if (choixPersonnage > 0) {
			do {
				do {
					try {
						System.out.print("Quel quartier choisissez vous ? ");
						sc = new Scanner(System.in);
						choixQuartier = sc.nextInt();
						isInteger = true;
					} catch (InputMismatchException e) {
						isInteger = false;
					}
				} while (isInteger == false);
			} while (choixQuartier <= 0 || choixQuartier > plateau.getNombreJoueurs());
			
			System.out.println("_____________________________________________________________");

			Joueur cible = plateau.getJoueur(choixPersonnage-1);

			int prixAPayer = cible.getCite()[choixQuartier-1].getCout() - 1;
			// contrôler si il reste de l'or au joueur
			for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
				Personnage personnage = plateau.getPersonnage(i);
					
				if (personnage.getNom().equals("Condottiere")) {
					if (joueur.tresor() < prixAPayer) {
						System.out.println("\n\t- Votre capital ne vous permet pas de détruire ce quartier.\n");
					} else {
						System.out.println("\n\t- Félicitations ! Vous êtes solvable, vous pouvez donc détruire ce quartier l'esprit tranquille.");
						String nomQuartierADetruire = cible.getCite()[choixQuartier-1].getNom();
						Quartier quartierADetruire = cible.getCite()[choixQuartier-1];
				
						System.out.println(
							"\n\t- Le quartier " +  
							nomQuartierADetruire +
							" possédé par " +  
							plateau.getJoueur(choixPersonnage - 1).getNom() + " aka " +
							plateau.getPersonnage(choixPersonnage - 1).getNom() + " a été détruit avec succès."
						);
							
						// si le nombre de joueurs est
						int nb = plateau.getNombreJoueurs();
						Quartier[] cite = cible.getCite();
						int tailleCite = cible.getCite().length;
				
						// comptabilise le nombre de quartiers dans la cité concernée
						int nbQuartier = 0;
						for (int j = 0; j < tailleCite; j++) {
							try{
								if (!cite[j].equals(null)){
									nbQuartier ++;
								}
							} catch (NullPointerException npe) {}
						}
							
						// message d'erreur dans le cas d'une cité complète :
							// cité de 7 quartiers ou plus pour les parties de 4 à 7 joueurs, 
							// ou une cité de 8 quartiers pour les parties à 2, 3 ou 8 joueurs.
						if (((nb == 2 || nb == 3 || nb == 8 ) && (nbQuartier == 8)) || ((nb >= 4 && nb <= 7) && (nbQuartier == 7))){
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
		sc.close();
	}
}
