package modele;

import java.util.Random;

import controleur.Interaction;

public class Assassin extends Personnage {

    public Assassin() {
        super("Assassin", Caracteristiques.ASSASSIN, 1);
    }

    public void utiliserPouvoir() {
		// affichage de tous les personnages
        System.out.println("Quel personnage voulez-vous assassiner ?");

        for (int i = 0; i < plateau.getNombrePersonnages(); i++) {
			System.out.println((i+1)+"   "+plateau.getPersonnage(i).getNom());
        }
		
		int rangPersonnageATuer = 0;

        do {
			rangPersonnageATuer = Interaction.lireUnEntier(1, plateau.getNombrePersonnages()+1)-1;

            if (plateau.getPersonnage(rangPersonnageATuer).getNom().equals("Assassin")) {
                System.out.println("\nVous ne pouvez pas vous assassiner !");
            }
        } while (plateau.getPersonnage(rangPersonnageATuer).getNom().equals("Assassin"));

		plateau.getPersonnage(rangPersonnageATuer).setAssassine();
    }

    public void utiliserPouvoirAvatar() {
		Random generateur = new Random();
		int rangPersonnageATuer;

		do {
			rangPersonnageATuer = generateur.nextInt(plateau.getNombrePersonnages());
        } while (plateau.getPersonnage(rangPersonnageATuer).getNom().equals("Assassin"));

		plateau.getPersonnage(rangPersonnageATuer).setAssassine();
    }
}
