package modele;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Voleur extends Personnage {
	Scanner sc = new Scanner(System.in);

    public Voleur() {
        super("Voleur", Caracteristiques.VOLEUR, 2);
    };

	@Override
    public void utiliserPouvoir() {
		
		// affichage des différents personnages volables
		for (int i = 0; i < plateau.getNombrePersonnages(); i++) {
			System.out.println((i+1) + " | " + plateau.getPersonnage(i).getNom());
		}
		
		int selection = 0; // initialisation de la variable contenant le nombre correspondant au personnage à voler

		do {
			System.out.println("Quel personnage voulez-vous voler ?");

			boolean verifEntier;
			do {
				try {
					selection = sc.nextInt() - 1;
					verifEntier = false;
					System.out.println("VerifEntier = false");
				} catch (InputMismatchException e) {
					verifEntier = true;
					System.out.println("VerifEntier = true");
				}
			} while (verifEntier == true);

			

			if (selection + 1 <= plateau.getNombrePersonnages() && plateau.getPersonnage(selection).getNom().equals("Voleur")) {
				System.out.println("Vous ne pouvez pas vous voler !");
			}

			} while (selection + 1 > plateau.getNombrePersonnages() || plateau.getPersonnage(selection).getNom().equals("Voleur") || plateau.getPersonnage(selection).getRang() == 1 || plateau.getPersonnage(selection).getAssassine() == true);
			// RAJOUTER LA CONDITION SUR L'ENSORCELLEMENT

		System.out.println("Le personnage choisi pour le vol est le " + plateau.getPersonnage(selection).getNom() + " joué par " + plateau.getJoueur(selection).getNom());

		plateau.getPersonnage(selection).setVole();
		
		// on stocke la valeur qui va être volée
		int montantVole = plateau.getJoueur(0).tresor();
		
		// on retire le total de l'argent du joueur volé
		plateau.getJoueur(0).retirerPieces(plateau.getJoueur(0).nbPieces());

		// on cherche le voleur dans la liste puis on atteint le joueur correspondant
		for (int k = 0; k < plateau.getNombrePersonnages(); k++) {
			if (plateau.getPersonnage(k).getNom().equals("Voleur")) {
				// on ajoute l'or volé au voleur
				plateau.getJoueur(k).ajouterPieces(montantVole);
			}
		}
	}
}
