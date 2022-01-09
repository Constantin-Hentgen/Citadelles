package modele;

import java.util.ArrayList;

import controleur.Interaction;

public class Magicienne extends Personnage {

    public Magicienne() {
        super("Magicienne", Caracteristiques.MAGICIENNE, 3);
    }

    public void utiliserPouvoir() {
		Joueur magicienne = this.getJoueur();

		// demander entre échanger cartes avec joueur ou pioche
        // Boolean echangerCarteAvecJoueur = Interaction.randomizerBoolean();
		System.out.println("Voulez-vous échanger vos cartes avec un joueur ?");
		Boolean echangerCarteAvecJoueur = Interaction.lireOuiOuNon();

        if (echangerCarteAvecJoueur) {
			// demander le joueur avec lequel échanger les cartes
			
			System.out.println("Choisissez un joueur pour échanger vos mains : \n");
			
			// affichage des différents joueurs
			for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
				System.out.println((i+1) + " | " + this.plateau.getJoueur(i).getNom() + " | " + this.plateau.getJoueur(i).getPersonnage().getNom());
				if (this.plateau.getJoueur(i).getPersonnage().getNom().equals("magicienne")) {
				}
			}

			System.out.println();
			int choixJoueurAEchanger = 0;

			do {
				System.out.println("Choisissez un joueur :");
				choixJoueurAEchanger = Interaction.lireUnEntier(1,this.plateau.getNombreJoueurs())-1;
			} while (this.plateau.getJoueur(choixJoueurAEchanger) == magicienne);

			Joueur cible = this.plateau.getJoueur(choixJoueurAEchanger);

			System.out.println("Vous avez choisi " + cible.getNom().toUpperCase());

			System.out.println("\nla main de ce joueur est : \n");

			for (int i = 0; i < cible.nbQuartiersDansMain(); i++) {
				System.out.println("\t" + cible.getMain().get(i).getNom());
			}

			System.out.println("\nvotre main pour rappel est : \n");


			for (int i = 0; i < magicienne.nbQuartiersDansMain(); i++) {
				System.out.println("\t" + magicienne.getMain().get(i).getNom());
			}

			ArrayList<Quartier> TEMP = magicienne.getMain();

			magicienne.setMain(cible.getMain());
			cible.setMain(TEMP);

			System.out.println("Les mains ont été échangées.");

			System.out.println("\n\nLA PREUVE EN IMAGE");

			System.out.println("\nla main de la cible est devenue \n");

			for (int i = 0; i < cible.nbQuartiersDansMain(); i++) {
				System.out.println("\t" + cible.getMain().get(i).getNom());
			}

			System.out.println("\nvotre main maintenant est : \n");

			for (int i = 0; i < magicienne.nbQuartiersDansMain(); i++) {
				System.out.println("\t" + magicienne.getMain().get(i).getNom());
			}

        } else {
			System.out.println("Combien de cartes voulez-vous échanger avec la pioche ?");

			int nombreDeCarteAEchanger = Interaction.lireUnEntier(0, magicienne.nbQuartiersDansMain());

			for (int i = 0; i < nombreDeCarteAEchanger; i++) {
				for (int j = 0; j < magicienne.nbQuartiersDansMain(); j++) {
					System.out.println("\t" + (j+1) + " | " + magicienne.getMain().get(j).getNom());
				}
				
				// il choisit la carte à dégager
				int choixCarteAEcarter = Interaction.lireUnEntier(1,magicienne.nbQuartiersDansMain()+1)-1;

				System.out.println("Le quartier a dégager est : " + magicienne.getMain().get(choixCarteAEcarter).getNom());
				// on vire la carte et une autre est piochée
				magicienne.retirerQuartierDansMain(magicienne.getMain().get(choixCarteAEcarter));
				magicienne.ajouterQuartierDansMain(this.plateau.getPioche().piocher());
			}
        }
    }

	public void utiliserPouvoirAvatar() {
		
		Joueur magicienne = this.getJoueur();

		// demander entre échanger cartes avec joueur ou pioche
        // Boolean echangerCarteAvecJoueur = Interaction.randomizerBoolean();
		Boolean echangerCarteAvecJoueur = Interaction.randomizerBoolean();

        if (echangerCarteAvecJoueur) {
			// demander le joueur avec lequel échanger les cartes
			
			System.out.println("Choisissez un joueur pour échanger vos mains : \n");
			
			// affichage des différents joueurs
			for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
				System.out.println((i+1) + " | " + this.plateau.getJoueur(i).getNom() + " | " + this.plateau.getJoueur(i).getPersonnage().getNom());
				if (this.plateau.getJoueur(i).getPersonnage().getNom().equals("magicienne")) {
				}
			}

			System.out.println();
			int choixJoueurAEchanger = 0;

			do {
				choixJoueurAEchanger = Interaction.randomizer(this.plateau.getNombreJoueurs()-1);
			} while (this.plateau.getJoueur(choixJoueurAEchanger) == magicienne);

			Joueur cible = this.plateau.getJoueur(choixJoueurAEchanger);

			System.out.println("Vous avez choisi " + cible.getNom().toUpperCase());

			System.out.println("\nla main de ce joueur est : \n");

			for (int i = 0; i < cible.nbQuartiersDansMain(); i++) {
				System.out.println("\t" + cible.getMain().get(i).getNom());
			}

			System.out.println("\nvotre main pour rappel est : \n");


			for (int i = 0; i < magicienne.nbQuartiersDansMain(); i++) {
				System.out.println("\t" + magicienne.getMain().get(i).getNom());
			}

			ArrayList<Quartier> TEMP = magicienne.getMain();

			magicienne.setMain(cible.getMain());
			cible.setMain(TEMP);

			System.out.println("Les mains ont été échangées.");

			System.out.println("\n\nLA PREUVE EN IMAGE");

			System.out.println("\nla main de la cible est devenue \n");

			for (int i = 0; i < cible.nbQuartiersDansMain(); i++) {
				System.out.println("\t" + cible.getMain().get(i).getNom());
			}

			System.out.println("\nvotre main maintenant est : \n");

			for (int i = 0; i < magicienne.nbQuartiersDansMain(); i++) {
				System.out.println("\t" + magicienne.getMain().get(i).getNom());
			}

        } else {
			System.out.println("Combien de cartes voulez-vous échanger avec la pioche ?");

			int nombreDeCarteAEchanger = Interaction.randomizer(magicienne.nbQuartiersDansMain());

			for (int i = 0; i < nombreDeCarteAEchanger; i++) {
				for (int j = 0; j < magicienne.nbQuartiersDansMain(); j++) {
					System.out.println("\t" + (j+1) + " | " + magicienne.getMain().get(j).getNom());
				}
				
				// il choisit la carte à dégager
				int choixCarteAEcarter = Interaction.randomizer(magicienne.nbQuartiersDansMain());

				System.out.println("Le quartier a dégager est : " + magicienne.getMain().get(choixCarteAEcarter).getNom());
				// on vire la carte et une autre est piochée
				magicienne.retirerQuartierDansMain(magicienne.getMain().get(choixCarteAEcarter));
				magicienne.ajouterQuartierDansMain(this.plateau.getPioche().piocher());
			}
        }
    }    
}