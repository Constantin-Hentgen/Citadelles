package test;

import modele.*;

public class TestAvatar {

	public static void main(String[] args) {
		TestAvatar test = new TestAvatar();
		test.test();
	}

	public void test() {
		PlateauDeJeu plateau = new PlateauDeJeu();
		Pioche pioche = plateau.getPioche();


		// création de quatre personnages
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		

		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);

		Voleur voleur = new Voleur();
		plateau.ajouterPersonnage(voleur);

		Magicienne magicienne = new Magicienne();
		plateau.ajouterPersonnage(magicienne);

		Marchande marchande = new Marchande();
		plateau.ajouterPersonnage(marchande);

		Condottiere condottiere= new Condottiere();
		plateau.ajouterPersonnage(condottiere);
			
		// création de trois joueurs
		Joueur joueur1 = new Joueur("Milou");
		plateau.ajouterJoueur(joueur1);
		
		Joueur joueur2 = new Joueur("Billy");
		plateau.ajouterJoueur(joueur2);

		Joueur joueur3 = new Joueur("Rantanplan");
		plateau.ajouterJoueur(joueur3);

		Joueur joueur4 = new Joueur("Tintin");
		plateau.ajouterJoueur(joueur4);

		Joueur joueur5 = new Joueur("Haddock");
		plateau.ajouterJoueur(joueur5);

		Joueur joueur6 = new Joueur("Dupondt");
		plateau.ajouterJoueur(joueur6);
			
		// on associe les personnages aux joueurs
		roi.setJoueur(joueur1);
		assassin.setJoueur(joueur2);
		condottiere.setJoueur(joueur3);
		voleur.setJoueur(joueur4);
		magicienne.setJoueur(joueur5);
		marchande.setJoueur(joueur6);

		condottiere.ajouterPieces();

		Quartier q = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1); pioche.ajouter(q);
		q = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2); pioche.ajouter(q);
		q = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5); pioche.ajouter(q);
		q = new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1); pioche.ajouter(q);
		q = new Quartier("échoppe",Quartier.TYPE_QUARTIERS[3],2); pioche.ajouter(q);
		q = new Quartier("basilique",Quartier.TYPE_QUARTIERS[4],4); pioche.ajouter(q);
		q = new Quartier("cathédrale",Quartier.TYPE_QUARTIERS[0],5); pioche.ajouter(q);
		q = new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3); pioche.ajouter(q);
		q = new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3); pioche.ajouter(q);
		q = new Quartier("hôtel de ville",Quartier.TYPE_QUARTIERS[3],5); pioche.ajouter(q);
		q = new Quartier("bibliothèque",Quartier.TYPE_QUARTIERS[4],6); pioche.ajouter(q);
		q = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2); pioche.ajouter(q);
		q = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5); pioche.ajouter(q);
		q = new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1); pioche.ajouter(q);
		q = new Quartier("échoppe",Quartier.TYPE_QUARTIERS[3],2); pioche.ajouter(q);
		q = new Quartier("basilique",Quartier.TYPE_QUARTIERS[4],4); pioche.ajouter(q);
		q = new Quartier("cathédrale",Quartier.TYPE_QUARTIERS[0],5); pioche.ajouter(q);
		q = new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3); pioche.ajouter(q);
		q = new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3); pioche.ajouter(q);
		q = new Quartier("hôtel de ville",Quartier.TYPE_QUARTIERS[3],5); pioche.ajouter(q);
		q = new Quartier("bibliothèque",Quartier.TYPE_QUARTIERS[4],6); pioche.ajouter(q);
		q = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2); pioche.ajouter(q);
		q = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5); pioche.ajouter(q);
		q = new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1); pioche.ajouter(q);
		q = new Quartier("échoppe",Quartier.TYPE_QUARTIERS[3],2); pioche.ajouter(q);
		q = new Quartier("basilique",Quartier.TYPE_QUARTIERS[4],4); pioche.ajouter(q);
		q = new Quartier("cathédrale",Quartier.TYPE_QUARTIERS[0],5); pioche.ajouter(q);
		q = new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3); pioche.ajouter(q);
		q = new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3); pioche.ajouter(q);
		q = new Quartier("hôtel de ville",Quartier.TYPE_QUARTIERS[3],5); pioche.ajouter(q);
		q = new Quartier("bibliothèque",Quartier.TYPE_QUARTIERS[4],6); pioche.ajouter(q);
		pioche.melanger();
		
		// on distribue les cartes aux joueurs:
		joueur1.ajouterQuartierDansCite(pioche.piocher());
		joueur1.ajouterQuartierDansCite(pioche.piocher());
		joueur1.ajouterQuartierDansCite(pioche.piocher());
		joueur2.ajouterQuartierDansCite(pioche.piocher());
		joueur2.ajouterQuartierDansCite(pioche.piocher());
		joueur2.ajouterQuartierDansCite(pioche.piocher());
		joueur3.ajouterQuartierDansCite(pioche.piocher());
		joueur3.ajouterQuartierDansCite(pioche.piocher());
		joueur4.ajouterQuartierDansCite(pioche.piocher());
		joueur4.ajouterQuartierDansCite(pioche.piocher());
		joueur4.ajouterQuartierDansCite(pioche.piocher());
		joueur5.ajouterQuartierDansCite(pioche.piocher());
		joueur5.ajouterQuartierDansCite(pioche.piocher());
		joueur6.ajouterQuartierDansCite(pioche.piocher());
		joueur6.ajouterQuartierDansCite(pioche.piocher());
		joueur6.ajouterQuartierDansCite(pioche.piocher());
		

		System.out.println("\nAssassin :");
		assassin.utiliserPouvoirAvatar();

		System.out.println("\nCondottiere :");
		condottiere.utiliserPouvoirAvatar();

		System.out.println("\nVoleur :");
		voleur.utiliserPouvoirAvatar();

		System.out.println("\nMagicienne :");
		magicienne.utiliserPouvoirAvatar();

		System.out.println("\nMarchande :");
		marchande.utiliserPouvoirAvatar();

		System.out.println();
	}	
}
