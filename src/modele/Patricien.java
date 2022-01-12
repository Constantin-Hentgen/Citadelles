package modele;

public class Patricien extends Personnage{

    public Patricien(){
        super("Patricien",Caracteristiques.PATRICIEN,4);
    }

    public void utiliserPouvoir(){
        //Recherche du joueur qui a la couronne
        for(int i = 0;i<plateau.getNombreJoueurs();i++){
            if(plateau.getJoueur(i).getPossedeCouronne()){
                plateau.getJoueur(i).setPossedeCouronne(false);
                break;
            }
        }
        // On donne la couronne au patricien
        // Regarder si cela ne chamboule pas tout dans le déroulement du tour
        System.out.println(this.joueur.getNom() +" prend la couronne");
        this.joueur.setPossedeCouronne(true);
    }
    
    public void percevoirRessourcesSpecifiques(){
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

    public void utiliserPouvoirAvatar(){
         //Recherche du joueur qui a la couronne
         for(int i = 0;i<plateau.getNombreJoueurs();i++){
            if(plateau.getJoueur(i).getPossedeCouronne()){
                plateau.getJoueur(i).setPossedeCouronne(false);
                break;
            }
        }
        // On donne la couronne au patricien
        System.out.println(this.joueur.getNom() +" prend la couronne");
        this.joueur.setPossedeCouronne(true);
    }
}
