package modele;

import controleur.Interaction;
public class Voleur extends Personnage {
    public Voleur() {
        super("Voleur", Caracteristiques.VOLEUR, 2);
    };

    public void utiliserPouvoir() {
		// affichage des différents personnages volables
		for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
			System.out.println((i+1) + " | " + plateau.getJoueur(i).getPersonnage().getNom());
		}
		
		// initialisation de la variable contenant le nombre correspondant au personnage à voler
		int selection = 0;

		do {
			System.out.println("\nQuel personnage voulez-vous voler ?");
			selection = Interaction.lireUnEntier(1, plateau.getNombreJoueurs()+1) - 1;
			
			if (plateau.getJoueur(selection).getPersonnage().getNom().equals("Voleur")) {
				System.out.println("\nOn ne peut pas se voler soi-même !\n");
			}

			else if (plateau.getJoueur(selection).getPersonnage().getRang() == 1){
				System.out.println("\nVous ne pouvez pas voler un personnage de rang 1.\n");
			}

		} while (plateau.getJoueur(selection).getPersonnage().getNom().equals("Voleur") || plateau.getJoueur(selection).getPersonnage().getRang() == 1 || plateau.getJoueur(selection).getPersonnage().getAssassine() == true);
		// rajouter la condition de l'ensorcellement si on ajoute la classe
		
		plateau.getJoueur(selection).getPersonnage().setVole();
		
		// on stocke la valeur qui va être volée
		int montantVole = plateau.getJoueur(selection).tresor();
		
		// on retire le total de l'argent du joueur volé
		plateau.getJoueur(selection).retirerPieces(plateau.getJoueur(selection).nbPieces());

		System.out.println("Le joueur " + plateau.getJoueur(selection).getNom() + " a été volé.");
		
		// on cherche le voleur dans la liste puis on atteint le joueur correspondant
		for (int k = 0; k < plateau.getNombreJoueurs(); k++) {
			if (plateau.getJoueur(k).getPersonnage().getNom().equals("Voleur")) {
				// on ajoute l'or volé au voleur
				plateau.getJoueur(k).ajouterPieces(montantVole);
				System.out.println("Vous avez " + plateau.getJoueur(k).tresor() + " PO à présent.");
			}
		}
	}
	
	public void utiliserPouvoirAvatar() {
		
		// initialisation de la variable contenant le nombre correspondant au personnage à voler
		int selection = 0;

		do {
			selection = Interaction.randomizer(plateau.getNombreJoueurs());
		} while (plateau.getJoueur(selection).getPersonnage().getNom().equals("Voleur") || plateau.getJoueur(selection).getPersonnage().getRang() == 1 || plateau.getJoueur(selection).getPersonnage().getAssassine() == true);
		// rajouter la condition de l'ensorcellement si on ajoute la classe

		plateau.getJoueur(selection).getPersonnage().setVole();
		
		// on stocke la valeur qui va être volée
		int montantVole = plateau.getJoueur(selection).tresor();
		
		// on retire le total de l'argent du joueur volé
		plateau.getJoueur(selection).retirerPieces(plateau.getJoueur(selection).nbPieces());

		System.out.println("Le joueur " + plateau.getJoueur(selection).getNom() + " a été volé.");
		
		// on cherche le voleur dans la liste puis on atteint le joueur correspondant
		for (int k = 0; k < plateau.getNombreJoueurs(); k++) {
			if (plateau.getJoueur(k).getPersonnage().getNom().equals("Voleur")) {
				// on ajoute l'or volé au voleur
				plateau.getJoueur(k).ajouterPieces(montantVole);
			}
		}
	}
}
