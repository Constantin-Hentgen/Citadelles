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
		System.out.print("Voulez-vous utiliser votre pouvoir de destruction ? o/n : ");
		
		String choix;
		
		// on s'assure que la réponse apporté sera o ou n
		Scanner sc = new Scanner(System.in);
		do {
			// Scanner sc = new Scanner(System.in);
			choix = sc.next();
			// sc.close();
		} while (!choix.equals("o") && !choix.equals("n"));

		if (choix.equals("o")){
			System.out.println("Voici la liste des joueurs et le contenu de leur cités : \n");
			
			for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
				// factorisation du code
				Joueur joueur = plateau.getJoueur(i);
				Quartier[] cite = joueur.getCite();
	
				// on affiche tous les joueurs et personnages associés
				System.out.println((i+1) + " | " + joueur.getNom() + " | " + plateau.getPersonnage(i).getNom());
				
				// on vient chercher les cités correspondantes et on affiche leurs infos
				for ( int j = 0; j < joueur.nbQuartiersDansCite(); j++) {
					System.out.println("\t" + (j+1) + " | " + cite[j].getNom() + " | coût = " + cite[j].getCout());
				}
			}

			// affichage du tresor du joueur
			for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
				Personnage personnage = plateau.getPersonnage(i);
	
				if (personnage.getNom().equals("Condottiere")) {
					System.out.println("\nPour information vous avez " + joueur.tresor() + " pièces d'or dans votre trésor.");
				}
			}

			int choixPersonnage = 0;
			boolean isInteger;
			
			// on demande le joueur à choisir
			// on s'assure que l'entrée est bien un entier et qu'il est logique
			do {
				do {
					try {
						System.out.print("\nQuel joueur choisissez vous ? (0 pour ne rien faire) ");
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
			do {
				do {
					try {
						System.out.print("\nQuel quartier choisissez vous ? (0 pour ne rien faire) ");
						sc = new Scanner(System.in);
						choixQuartier = sc.nextInt();
						isInteger = true;
					} catch (InputMismatchException e) {
						isInteger = false;
					}
				} while (isInteger == false);
			} while (choixQuartier < 0 || choixQuartier > plateau.getNombreJoueurs());
			
			
			// contrôler si il reste de l'or au joueur
			for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
				Personnage personnage = plateau.getPersonnage(i);
				
				if (personnage.getNom().equals("Condottiere")) {
					if (joueur.tresor() < plateau.getJoueur(choixPersonnage-1).getCite()[choixQuartier-1].getCout() - 1) {
						System.out.println("Votre capital ne vous permet pas de détruire ce quartier.");
					} else {
						System.out.println("Félicitation ! Vous êtes solvable, vous pouvez donc détruire ce quartier l'esprit tranquille.");
					}
				}
			}
			
			// faire un feedback sur l'opération : tel quartier a été détruit, il vous reste 1or
			System.out.println(
				"Le personnage choisit pour la destruction est " +  
				plateau.getPersonnage(choixPersonnage - 1).getNom() +
				" joué par " + plateau.getJoueur(choixPersonnage - 1).getNom() +
				", son quartier à détruire est " +  
				plateau.getJoueur(choixPersonnage-1).getCite()[choixQuartier-1].getNom() + "."
			);

			// DESTRUCTION

			// CONDITION si la cité du joueur est complète alors je ne peux rien détruire
			// stocker le quartier concerné
			// placer le dernier quartier de la cité à la place du quartier détruit
			// mettre le dernier élément à null
			// mettre le quartier à la fin de la pioche
			// retirer COUT-1 au condottiere
		}
		sc.close();
	}
}
