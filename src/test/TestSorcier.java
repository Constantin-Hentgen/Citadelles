package test;
import modele.*;

public class TestSorcier {
    public static void main(String[] args){
        TestSorcier test = new TestSorcier();
        //test.test1();
        //test.test2();
        test.test3();
    }

    public void test1(){
        System.out.println("TEST DU CONSTRUCTEUR");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Sorcier sorcier= new Sorcier();
		plateau.ajouterPersonnage(sorcier);
		Test.test(plateau.getNombrePersonnages()== 2,"nombre de joueurs");
		Test.test(plateau.getPersonnage(1)== sorcier,
				"récupération du personnage du sorcier");
		Test.test(plateau.getPersonnage(1).getRang()==3,
				"récupération du rang");
    }

    public void test2(){
        System.out.println("TEST POUVOIR SANS TRESOR");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Assassin assassin = new Assassin();
        Voleur voleur = new Voleur();
        Sorcier sorcier = new Sorcier();
        plateau.ajouterPersonnage(assassin);
        plateau.ajouterPersonnage(voleur);
        plateau.ajouterPersonnage(sorcier);

        Joueur joueur1 = new Joueur("Milou");
		plateau.ajouterJoueur(joueur1);
		Joueur joueur2 = new Joueur("Billy");
		plateau.ajouterJoueur(joueur2);
		Joueur joueur3 = new Joueur("Rantanplan");
		plateau.ajouterJoueur(joueur3);

        assassin.setJoueur(joueur1);
        voleur.setJoueur(joueur2);
        sorcier.setJoueur(joueur3);

        Quartier q = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1); 
        joueur1.ajouterQuartierDansMain(q);
		q = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2); 
        joueur2.ajouterQuartierDansMain(q);
		q = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
        joueur3.ajouterQuartierDansMain(q);

        int taille = sorcier.getJoueur().getMain().size();
        sorcier.utiliserPouvoir();
        


        Test.test((joueur1.nbQuartiersDansMain() == 0)||(joueur2.nbQuartiersDansMain() ==0),"quartier pris dans la main");
        Test.test(taille +1  == joueur3.nbQuartiersDansMain() , "quartier ajouter dans la main");
        Test.test(sorcier.getJoueur().nbQuartiersDansCite()==0,"pas de construction");

    }

    public void test3(){
        System.out.println("TEST POUVOIR AVEC TRESOR");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Assassin assassin = new Assassin();
        Voleur voleur = new Voleur();
        Sorcier sorcier = new Sorcier();
        plateau.ajouterPersonnage(assassin);
        plateau.ajouterPersonnage(voleur);
        plateau.ajouterPersonnage(sorcier);

        Joueur joueur1 = new Joueur("Milou");
		plateau.ajouterJoueur(joueur1);
		Joueur joueur2 = new Joueur("Billy");
		plateau.ajouterJoueur(joueur2);
		Joueur joueur3 = new Joueur("Rantanplan");
		plateau.ajouterJoueur(joueur3);

        assassin.setJoueur(joueur1);
        voleur.setJoueur(joueur2);
        sorcier.setJoueur(joueur3);

        Quartier q = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1); 
        joueur1.ajouterQuartierDansMain(q);
		q = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2); 
        joueur2.ajouterQuartierDansMain(q);
		q = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
        joueur3.ajouterQuartierDansMain(q);
        joueur3.ajouterPieces(10);
        int taille = sorcier.getJoueur().getMain().size();
        int tresor = joueur3.tresor();
        sorcier.utiliserPouvoir();



        Test.test((joueur1.nbQuartiersDansMain() == 0)||(joueur2.nbQuartiersDansMain() ==0),"quartier pris dans la main");
        Test.test(taille == sorcier.getJoueur().getMain().size(), "quartier non ajouter dans la main");
        Test.test(sorcier.getJoueur().nbQuartiersDansCite()==1,"construction");
        Test.test(joueur3.tresor()  == tresor - 1, "construction depuis joueur 1");

    }
    
    
}
