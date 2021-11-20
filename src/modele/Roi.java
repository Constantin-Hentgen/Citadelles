package modele;

public class Roi extends Personnage {
	public Roi(){
		super("Roi",Caracteristiques.ROI,4);
	}

	void utiliserPouvoir(){
		System.out.println("Je prends la couronne.");
		joueur.setPossedeCouronne(true);
	}

	public void percevoirRessourcesSpecifiques(){
		int compteur = 0;
		if (this.joueur != null && this.assassine == false){
			for (Quartier unQuartier : joueur.getCite()){
				if (unQuartier.getType() != null && unQuartier.getType() == "NOBLE"){
					compteur ++;
				}
			}
			joueur.ajouterPieces(compteur);
			System.out.println(compteur + " quartiers nobles ont été trouvés dans la cité du joueur.");
		}
	}
}
