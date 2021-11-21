package test;

import modele.Caracteristiques;
import modele.Joueur;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Roi;

public class TestPlateauDeJeu {

	public static void main(String[] args) {
		TestPlateauDeJeu testPlateau = new TestPlateauDeJeu();
		// testPlateau.test1();
		// testPlateau.test2();
		// testPlateau.test3();
		testPlateau.test4();
	}
	
	// public void test1() {
	// 	System.out.println("TEST DU CONSTRUCTEUR");
	// 	PlateauDeJeu plateau = new PlateauDeJeu();
	// 	Test.test(plateau.getNombreJoueurs()== 0,"test du nombre de joueurs");
	// 	Test.test(plateau.getNombrePersonnages()== 0,"test du nombre de personnages");
	// 	Test.test(plateau.getPioche()!= null && 
	// 			plateau.getPioche().nombreElements()==0,
	// 			"test de l'existence de la pioche");
	// }

	// public void test2() {
	// 	System.out.println("TEST DE L'AJOUT D'UN JOUEUR");
	// 	PlateauDeJeu plateau = new PlateauDeJeu();
	// 	Joueur joueur = new Joueur("Billy");
	// 	plateau.ajouterJoueur(joueur);

	// 	for (int i = 0; i<8; i++){
	// 		System.out.println(plateau.listeJoueurs[i]);
	// 	}

	// 	Test.test(plateau.getNombreJoueurs() == 1,"nombre de joueurs");
	// 	Test.test(plateau.getJoueur(0) == joueur,
	// 			"récupération de ce joueur depuis le tableau");
	// }

	// public void test3() {
	// 	System.out.println("TEST DE L'AJOUT D'UN PERSONNAGE");
	// 	PlateauDeJeu plateau = new PlateauDeJeu();
	// 	Roi roi = new Roi();
	// 	plateau.ajouterPersonnage(roi);
	// 	Test.test(plateau.getNombrePersonnages()== 1,"nombre de joueurs");
	// 	Test.test(plateau.getPersonnage(0)== roi,
	// 			"récupération du personnage depuis le tableau");
	// }

	public void test4() {
		System.out.println("TEST DE L'ASSOCIATION DU PLATEAU AU PERSONNAGE");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Roi roi = new Roi();

			System.out.println(roi.getNom());
			// renvoie "roi"

		plateau.ajouterPersonnage(roi);

			System.out.println(plateau.getPersonnage(0).getRang());
			// jusqu'ici c'est ok

			System.out.println(roi.getPlateau());
			// renvoie null ici

			System.out.println(plateau.getPersonnage(0).getNom());
			// renvoie "roi"

		Test.test(roi.getPlateau() == plateau,
				"association du plateau au personnage");
	}
}
