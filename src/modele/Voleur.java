package modele;

import controleur.Interaction;
public class Voleur extends Personnage {
    public Voleur() {
        super("Voleur", Caracteristiques.VOLEUR, 2);
    };

	@Override
    public void utiliserPouvoir() {
		// affichage des différents personnages volables
		for (int i = 0; i < plateau.getNombrePersonnages(); i++) {
			System.out.println((i+1) + " | " + plateau.getPersonnage(i).getNom());
		}
		
		// initialisation de la variable contenant le nombre correspondant au personnage à voler
		int selection = 0;

		do {
			System.out.println("Quel personnage voulez-vous voler ?");
			selection = Interaction.lireUnEntier(1, plateau.getNombrePersonnages()+1) - 1;
			
			if (plateau.getPersonnage(selection).getNom().equals("Voleur")) {
				System.out.println("\nOn ne peut pas se voler soi-même !\n");
			}

			else if (plateau.getPersonnage(selection).getRang() == 1){
				System.out.println("\nVous ne pouvez pas voler un personnage de rang 1.\n");
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
	}
}
