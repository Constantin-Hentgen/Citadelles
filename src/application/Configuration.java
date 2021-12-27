package application;

import java.util.ArrayList;
import java.util.Hashtable;

import modele.*;

public class Configuration {
	// private static PlateauDeJeu plateau;
	// private static Pioche p = plateau.getPioche();

	// Quartiers Religieux
	private static Quartier temple = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
	private static Quartier eglise = new Quartier("eglise",Quartier.TYPE_QUARTIERS[0],2);
	private static Quartier monastere = new Quartier("monastere",Quartier.TYPE_QUARTIERS[0],3);
	private static Quartier cathedrale = new Quartier("cathedrale",Quartier.TYPE_QUARTIERS[0],5);

	// Quartiers Militaires
	private static Quartier tourDeGuet = new Quartier("tour de guet",Quartier.TYPE_QUARTIERS[1],1);
	private static Quartier prison = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
	private static Quartier caserne = new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3);
	private static Quartier forteresse = new Quartier("forteresse",Quartier.TYPE_QUARTIERS[1],5);

	// Quartiers Nobles
	private static Quartier manoir = new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3);
	private static Quartier chateau = new Quartier("chateau",Quartier.TYPE_QUARTIERS[2],4);
	private static Quartier palais = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);

	// Quartiers Commerçants
	private static Quartier taverne = new Quartier("taverne",Quartier.TYPE_QUARTIERS[3],1);
	private static Quartier echoppe = new Quartier("echoppe",Quartier.TYPE_QUARTIERS[3],2);
	private static Quartier marche = new Quartier("marche",Quartier.TYPE_QUARTIERS[3],2);
	private static Quartier comptoir = new Quartier("comptoir",Quartier.TYPE_QUARTIERS[3],3);
	private static Quartier port = new Quartier("port",Quartier.TYPE_QUARTIERS[3],4);
	private static Quartier hotelDeVille = new Quartier("hotel de ville",Quartier.TYPE_QUARTIERS[3],5);
	
	public static Pioche nouvellePioche(Pioche p) {
		// Ajout des différents quartiers selon la répartition voulue par la configuration
	
		// Ajout des quartiers en quantité 2
		for (int i = 0; i < 1; i++) {
			p.ajouter(cathedrale);
			p.ajouter(forteresse);
			p.ajouter(hotelDeVille);
		}

		// Ajout des quartiers en quantité 3
		for (int i = 0; i < 2; i++) {
			p.ajouter(temple);
			p.ajouter(eglise);
			p.ajouter(monastere);
			p.ajouter(tourDeGuet);
			p.ajouter(prison);
			p.ajouter(caserne);
			p.ajouter(palais);
			p.ajouter(echoppe);
			p.ajouter(comptoir);
			p.ajouter(port);
		}

		// Ajout des quartiers en quantité 4
		for (int i = 0; i < 3; i++) {
			p.ajouter(chateau);
			p.ajouter(marche);
		}

		// Ajout des quartiers en quantité 5
		for (int i = 0; i < 4; i++) {
			p.ajouter(manoir);
			p.ajouter(taverne);
		}

		p.melanger();
		return p;
	}

	// méthode de vérification
	public static void affichePioche(Pioche p) {
		for (int i = 0; i <= 54; i++) {
			// System.out.println(p.piocher().getNom());
			p.piocher();
		}
	}

	// static PlateauDeJeu configurationDeBase(Pioche p) {
	// 	return Pioche;
	// }
}

// Implémentez la méthode statique nouvellePioche() qui renvoie une nouvelle pioche
// qui comporte les 54 cartes Quartiers des types religieux, militaires, nobles et com-
// merçants