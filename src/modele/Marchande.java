package modele;
public class Marchande extends Personnage {
    
    public Marchande() {
        super("Marchande", Caracteristiques.MARCHANDE, 6);
    }

    public void percevoirRessourcesSpecifiques() {		
        if (!assassine) {
			// vérifier si il y a un un quartier commerçant dans la cité du joueur
            joueur.ajouterPieces(1);
            System.out.println("La marchande récupère une pièce supplémentaire."); 
			
			try {
				for (int a = 0; a < this.getJoueur().getCite().length; a++) {
					if (this.getJoueur().getCite()[a].getType().equals(Quartier.TYPE_QUARTIERS[3])) {
						joueur.ajouterPieces(1);
						System.out.println("La marchande récupère une pièce supplémentaire par quartier commerçant.");
					}
				}
			} catch (NullPointerException npe) {};
        } else {
            System.out.println("La marchande a été assassinée.");
        }
    }

    public void utiliserPouvoir() {}

	public void utiliserPouvoirAvatar() {   
        if (!assassine) {
            joueur.ajouterPieces(1);
            System.out.println("La marchande récupère une pièce supplémentaire."); 

			try {
				for (int a = 0; a < this.getJoueur().getCite().length; a++) {
					if (this.getJoueur().getCite()[a].getType().equals(Quartier.TYPE_QUARTIERS[3])) {
						joueur.ajouterPieces(1);
						System.out.println("La marchande récupère une pièce supplémentaire par quartier commerçant.");
					}
				}
			} catch (NullPointerException npe) {};
        } else {
            System.out.println("La marchande a été assassinée.");
        }
    }
}
