package modele;

public class Roi extends Personnage {
	public Roi() {
		super("Roi",Caracteristiques.ROI,4);
	}

	public void percevoirRessourcesSpecifiques() {
		int compteur = 0;
		if (this.joueur != null && this.assassine != true) {
			for (Quartier unQuartier : joueur.getCite()) {
				if (unQuartier != null && unQuartier.getType() == "NOBLE") {
					compteur ++;
				}
			}
			this.joueur.ajouterPieces(compteur);
		}
	}

	public void utiliserPouvoir() {
		if (this.joueur != null && this.assassine != true) {
			System.out.println("\nLe joueur prend la couronne.");
			
			for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
				this.plateau.getJoueur(i).setPossedeCouronne(false);
			}
			
			joueur.setPossedeCouronne(true);
		}
	}

	public void utiliserPouvoirAvatar() {
		if (this.joueur != null && this.assassine != true) {
			System.out.println("\nLe joueur prend la couronne.");
			
			for (int i = 0; i < this.plateau.getNombreJoueurs(); i++) {
				this.plateau.getJoueur(i).setPossedeCouronne(false);
			}
			
			joueur.setPossedeCouronne(true);
		}
	}
}
