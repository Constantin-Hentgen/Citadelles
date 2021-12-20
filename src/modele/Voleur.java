package modele;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Voleur extends Personnage {
    public Voleur() {
        super("Voleur", Caracteristiques.VOLEUR, 2);
    };

	@Override
    public void utiliserPouvoir() {
		Scanner sc = new Scanner(System.in);
		
		// affichage des différents personnages volables
		for (int i = 0; i < plateau.getNombrePersonnages(); i++) {
			System.out.println((i+1) + " | " + plateau.getPersonnage(i).getNom());
		}
		
		int selection = 0; // initialisation de la variable contenant le nombre correspondant au personnage à voler

		do {
			System.out.println("Quel personnage voulez-vous voler ?");

			boolean isInteger;
			do {
				try {
					selection = sc.nextInt() - 1;
					isInteger = true;
				} catch (InputMismatchException e) {
					isInteger = false;
				}
			} while (isInteger == false);
			
			if (selection + 1 <= plateau.getNombrePersonnages() && plateau.getPersonnage(selection).getNom().equals("Voleur")) {
				System.out.println("On ne peut pas se voler soi-même !");
			}

			else if (plateau.getPersonnage(selection).getRang() == 1){
				System.out.println("Vous ne pouvez pas voler un personnage de rang 1...");
			}

		} while (selection + 1 > plateau.getNombrePersonnages() || plateau.getPersonnage(selection).getNom().equals("Voleur") || plateau.getPersonnage(selection).getRang() == 1 || plateau.getPersonnage(selection).getAssassine() == true);
		// rajouter la condition de l'ensorcellement si on ajoute la classe
		
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
		sc.close();
	}
}
