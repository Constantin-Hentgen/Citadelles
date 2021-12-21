package modele;

public class Condottiere extends Personnage {

	public Condottiere(){
		super("Condottiere", Caracteristiques.CONDOTTIERE, 8); // checker le rang de condottiere
	}


	public void percevoirRessourcesSpecifiques(){
		// percevoir ressources : 1 or pour chaque bâtiment militaire

		// on vient chercher le condottiere
		try {
			System.out.println("plateau.getNombrePersonnages() : " + plateau.getNombrePersonnages());
			for (int i = 0; i < plateau.getNombreJoueurs(); i++){
				System.out.println(plateau.getPersonnage(i).getNom());
				if (plateau.getPersonnage(i).getNom().equals("Condottiere")){
					System.out.println("CONDOTTIERE TROUVÉ");
				}
			}
		} catch (NullPointerException npe) {
			System.out.println("ça pointe vers du NULL");
		}
	}

	@Override
	public void utiliserPouvoir() {

		// je choisis un quartier et je peux le détruire pour COUT - 1
		// --> les quartiers de coût 1 sont donc gratuits à détruire
	
		// si la cité est complète alors je ne peux rien détruire
		// on peut détruire ses propres quartiers
	
		// les quartiers détruits sont ajoutés à la fin de la pioche

		for ( int i = 0; i < plateau.getNombreJoueurs(); i++) {
			System.out.println(plateau.getPersonnage(i).getNom());
			for ( int j = 0; j < joueur.getMain().size(); j++) {
				System.out.println(joueur.getMain().get(i));
			}
		}
	}
}
