package test;

import java.util.ArrayList;

import modele.*;

public class TestVoyante {
    public static void main(String[] args){
        TestVoyante test = new TestVoyante();
        //test.test1();
        test.test2();
    }

    public void test1() {
		System.out.println("TEST DU CONSTRUCTEUR");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Voyante voyante = new Voyante();
		plateau.ajouterPersonnage(voyante);
		Test.test(plateau.getNombrePersonnages()== 2,"nombre de joueurs");
		Test.test(plateau.getPersonnage(1)==voyante,
				"récupération du personnage de la voyante");
		Test.test(plateau.getPersonnage(1).getRang()==3,
				"récupération du rang");		
	}

    public void test2(){
        System.out.println("TEST POUVOIR");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Assassin assassin = new Assassin();
        Voleur voleur = new Voleur();
        Voyante voyante = new Voyante();
        plateau.ajouterPersonnage(assassin);
        plateau.ajouterPersonnage(voleur);
        plateau.ajouterPersonnage(voyante);

        Joueur joueur1 = new Joueur("Milou");
		plateau.ajouterJoueur(joueur1);
		Joueur joueur2 = new Joueur("Billy");
		plateau.ajouterJoueur(joueur2);
		Joueur joueur3 = new Joueur("Rantanplan");
		plateau.ajouterJoueur(joueur3);

        assassin.setJoueur(joueur1);
        voleur.setJoueur(joueur2);
        voyante.setJoueur(joueur3);

        Quartier q = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1); 
        joueur1.ajouterQuartierDansMain(q);
		q = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2); 
        joueur2.ajouterQuartierDansMain(q);
		q = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
        joueur3.ajouterQuartierDansMain(q);

        voyante.utiliserPouvoir();

        Test.test(joueur1.nbQuartiersDansMain()==1,"le joueur 1 a toujours sa carte dans sa main");
        Test.test(joueur2.nbQuartiersDansMain()==1,"le joueur 2 a toujours sa carte dans sa main");
        Test.test(joueur3.getMain().get(0).equals(q),"la voyante a la meme main(passed si elle garde le palais)");
        ArrayList<Quartier> mainVoyante = new ArrayList<Quartier>();
        mainVoyante.add(q);

        Test.test(joueur3.getMain().equals(mainVoyante),"la voyante a la meme main(passed si uniquement le palais en main)");

        
    }
}