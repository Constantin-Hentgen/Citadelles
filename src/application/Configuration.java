package application;

import java.util.ArrayList;
import java.util.Hashtable;

import modele.*;

public class Configuration {
	// private static PlateauDeJeu plateau;
	// private static Pioche p = plateau.getPioche();

	private static Hashtable<Quartier, Integer> configDeBase = new Hashtable<Quartier, Integer>();

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
		configDeBase.put(temple,3);
		configDeBase.put(eglise,3);
		configDeBase.put(monastere,3);
		configDeBase.put(cathedrale,3);
		configDeBase.put(tourDeGuet,3);
		configDeBase.put(prison,3);
		configDeBase.put(caserne,3);
		configDeBase.put(forteresse,2);
		configDeBase.put(manoir,5);
		configDeBase.put(chateau,4);
		configDeBase.put(palais,3);
		configDeBase.put(taverne,5);
		configDeBase.put(echoppe,3);
		configDeBase.put(marche,4);
		configDeBase.put(comptoir,3);
		configDeBase.put(port,3);
		configDeBase.put(hotelDeVille,2);
		// fill la database

		// boucler dans la hashtable pour ajouter les éléments
		for (int i = 0; i < 54; i++) {
			p.ajouter(temple);
			// faire en sorte que l'ajout se fasse de manière aléatoire
		}
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