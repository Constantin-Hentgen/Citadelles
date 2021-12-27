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
			System.out.println("Je prends la couronne.");
			joueur.setPossedeCouronne(true);
		}
	}

	public void utiliserPouvoirAvatar() {
		if (this.joueur != null && this.assassine != true) {
			System.out.println("Je prends la couronne.");
			joueur.setPossedeCouronne(true);
		}
	}
}
