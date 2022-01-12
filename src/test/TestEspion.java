package test;

import modele.Espion;
import modele.*;
import modele.PlateauDeJeu;

public class TestEspion {
    public static void main(String[] args){
        TestEspion test = new TestEspion();
        //test.test1();
        test.test2();
    }

    public void test1(){
        System.out.println("TEST DU CONSTRUCTEUR");
        Espion espion = new Espion();
        Test.test(espion.getRang()==2,"rang espion");
        Test.test(espion.getNom().equals("Espion"), "nom espion");

    }

    public void test2(){
        System.out.println("TEST POUVOIR");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Assassin assassin = new Assassin();
        plateau.ajouterPersonnage(assassin);
        Roi roi = new Roi();
        plateau.ajouterPersonnage(roi);
        Espion espion = new Espion();
        plateau.ajouterPersonnage(espion);

        Joueur joueur1 = new Joueur("Milou");
		plateau.ajouterJoueur(joueur1);
		Joueur joueur2 = new Joueur("Billy");
		plateau.ajouterJoueur(joueur2);
		Joueur joueur3 = new Joueur("Rantanplan");
		plateau.ajouterJoueur(joueur3);

        roi.setJoueur(joueur1);
		assassin.setJoueur(joueur2);
		espion.setJoueur(joueur3);

        Pioche pioche = plateau.getPioche();
		Quartier q = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1); pioche.ajouter(q);
		q = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2); pioche.ajouter(q);
		q = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5); pioche.ajouter(q);
		q = new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1); pioche.ajouter(q);
		q = new Quartier("échoppe",Quartier.TYPE_QUARTIERS[3],2); pioche.ajouter(q);
		q = new Quartier("basilique",Quartier.TYPE_QUARTIERS[4],4,"A la fin de la partie, ..."); 
		pioche.ajouter(q);
		q = new Quartier("cathédrale",Quartier.TYPE_QUARTIERS[0],5); pioche.ajouter(q);
		q = new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3); pioche.ajouter(q);
		q = new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3); pioche.ajouter(q);
		q = new Quartier("hôtel de ville",Quartier.TYPE_QUARTIERS[3],15); pioche.ajouter(q);
		q = new Quartier("bibliothèque",Quartier.TYPE_QUARTIERS[4],6,"Si vous choisissez..."); 
		pioche.ajouter(q);
		pioche.melanger();
		
		// on distribue les cartes aux joueurs:
		joueur1.ajouterQuartierDansMain(pioche.piocher());
		joueur1.ajouterQuartierDansMain(pioche.piocher());
		joueur1.ajouterQuartierDansMain(pioche.piocher());
		joueur2.ajouterQuartierDansMain(pioche.piocher());
		joueur2.ajouterQuartierDansMain(pioche.piocher());
		joueur2.ajouterQuartierDansMain(pioche.piocher());
		joueur3.ajouterQuartierDansMain(pioche.piocher());
		joueur3.ajouterQuartierDansMain(pioche.piocher());
        System.out.println("Tresor Espion : " + joueur3.tresor());
        espion.utiliserPouvoir();
        System.out.println("Tresor Espion : " + joueur3.tresor());

    }
}
