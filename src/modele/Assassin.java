package modele;

import controleur.Interaction;
public class Assassin extends Personnage {

    public Assassin() {
        super("Assassin", Caracteristiques.ASSASSIN, 1);
    }

    public void utiliserPouvoir() {
		// affichage de tous les personnages
        System.out.println("Quel personnage voulez-vous assassiner ?\n");

        for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
			System.out.println((i+1)+"   "+plateau.getJoueur(i).getPersonnage().getNom());
        }
		System.out.println();
		
		int rangPersonnageATuer = 0;

        do {
			rangPersonnageATuer = Interaction.lireUnEntier(1, plateau.getNombreJoueurs()+1)-1;

            if (plateau.getJoueur(rangPersonnageATuer).getPersonnage().getNom().equals("Assassin")) {
                System.out.println("\nVous ne pouvez pas vous assassiner !");
            }
        } while (plateau.getJoueur(rangPersonnageATuer).getPersonnage().getNom().equals("Assassin"));

		plateau.getJoueur(rangPersonnageATuer).getPersonnage().setAssassine();
		System.out.println("Le personnage " + plateau.getJoueur(rangPersonnageATuer).getPersonnage().getNom() + " a été assassiné.");
    }

    public void utiliserPouvoirAvatar() {
		int rangPersonnageATuer;

		do {
			rangPersonnageATuer = Interaction.randomizer(plateau.getNombreJoueurs());
        } while (plateau.getJoueur(rangPersonnageATuer).getPersonnage().getNom().equals("Assassin"));

		plateau.getJoueur(rangPersonnageATuer).getPersonnage().setAssassine();
		System.out.println("Le personnage " + plateau.getJoueur(rangPersonnageATuer).getPersonnage().getNom() + " a été assassiné.");
    }
}
