package application;

import modele.*;

public class Configuration {	
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
	
	// Merveilles
	private static Quartier bibliotheque = new Quartier("bibliotheque",Quartier.TYPE_QUARTIERS[4],6);
	private static Quartier forge = new Quartier("forge",Quartier.TYPE_QUARTIERS[4],5);
	private static Quartier carriere = new Quartier("carriere",Quartier.TYPE_QUARTIERS[4],5);
	private static Quartier laboratoire = new Quartier("laboratoire",Quartier.TYPE_QUARTIERS[4],5);
	private static Quartier courDesMiracles = new Quartier("courDesMiracles",Quartier.TYPE_QUARTIERS[4],2);
	private static Quartier manufacture = new Quartier("manufacture",Quartier.TYPE_QUARTIERS[4],5);
	private static Quartier donjon = new Quartier("donjon",Quartier.TYPE_QUARTIERS[4],3);
	private static Quartier salleDesCartes = new Quartier("salleDesCartes",Quartier.TYPE_QUARTIERS[4],5);
	private static Quartier dracoport = new Quartier("dracoport",Quartier.TYPE_QUARTIERS[4],6);
	private static Quartier statueEquestre = new Quartier("statueEquestre",Quartier.TYPE_QUARTIERS[4],3);
	private static Quartier ecoleDeMagie = new Quartier("ecoleDeMagie",Quartier.TYPE_QUARTIERS[4],6);
	private static Quartier tresorImperial = new Quartier("tresorImperial",Quartier.TYPE_QUARTIERS[4],5);
	private static Quartier fontaineAuxSouhaits = new Quartier("fontaineAuxSouhaits",Quartier.TYPE_QUARTIERS[4],5);
	private static Quartier tripot = new Quartier("tripot",Quartier.TYPE_QUARTIERS[4],6);

	public static Pioche nouvellePioche(Pioche p) {
		// Ajout des quartiers en quantité 2
		for (int i = 0; i < 2; i++) {
			p.ajouter(cathedrale);
			p.ajouter(forteresse);
			p.ajouter(hotelDeVille);
		}
		
		// Ajout des quartiers en quantité 3
		for (int i = 0; i < 3; i++) {
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
		for (int i = 0; i < 4; i++) {
			p.ajouter(chateau);
			p.ajouter(marche);
		}
		
		// Ajout des quartiers en quantité 5
		for (int i = 0; i < 5; i++) {
			p.ajouter(manoir);
			p.ajouter(taverne);
		}
		
		p.melanger();
		return p;
	}
	
	public static PlateauDeJeu configurationDeBase(Pioche p) {
		PlateauDeJeu plateau = new PlateauDeJeu();

		// Ajout des 4 joueurs
		Joueur joueur1 = new Joueur("joueur1");
		plateau.ajouterJoueur(joueur1);

		Joueur joueur2 = new Joueur("joueur2");
		plateau.ajouterJoueur(joueur2);

		Joueur joueur3 = new Joueur("joueur3");
		plateau.ajouterJoueur(joueur3);

		Joueur joueur4 = new Joueur("joueur4");
		plateau.ajouterJoueur(joueur4);
		
		// Ajout des 8 personnages
		Assassin assassin = new Assassin();
		plateau.ajouterPersonnage(assassin);

		Voleur voleur = new Voleur();
		plateau.ajouterPersonnage(voleur);

		Magicienne magicienne = new Magicienne();
		plateau.ajouterPersonnage(magicienne);

		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);

		Eveque eveque = new Eveque();
		plateau.ajouterPersonnage(eveque);

		Marchande marchande = new Marchande();
		plateau.ajouterPersonnage(marchande);

		Architecte architecte = new Architecte();
		plateau.ajouterPersonnage(architecte);

		Condottiere condottiere = new Condottiere();
		plateau.ajouterPersonnage(condottiere);

		// Ajout des 14 quartiers merveille à la pioche		
		p.ajouter(bibliotheque);
		p.ajouter(forge);
		p.ajouter(carriere);
		p.ajouter(laboratoire);
		p.ajouter(courDesMiracles);
		p.ajouter(manufacture);
		p.ajouter(donjon);
		p.ajouter(salleDesCartes);
		p.ajouter(dracoport);
		p.ajouter(statueEquestre);
		p.ajouter(ecoleDeMagie);
		p.ajouter(tresorImperial);
		p.ajouter(fontaineAuxSouhaits);
		p.ajouter(tripot);

		p.melanger();

		return plateau;
	}
}
