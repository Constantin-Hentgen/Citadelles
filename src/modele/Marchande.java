package modele;
public class Marchande extends Personnage {
    
    public Marchande() {
        super("Marchande", Caracteristiques.MARCHANDE, 6);
    }

    public void percevoirRessourcesSpecifiques() {
        int recevoir = 0;
        
        if (!assassine) {            
            for (int i = 0; i < joueur.nbQuartiersDansCite(); i++) {
                if (this.joueur.getCite()[i].getType().equals(Quartier.TYPE_QUARTIERS[3])) {
                    recevoir++;
                }
            }

            joueur.ajouterPieces(recevoir);
            System.out.println(recevoir+" pièces données au joueur");
        } else {
            System.out.println("Le joueur a été assassiné");
        }
    }

    public void utiliserPouvoir() {
        if (!assassine) {
			// vérifier si il y a un un quartier commerçant dans la cité du joueur
            joueur.ajouterPieces(1);
            System.out.println("Le joueur récupère une pièce supplémentaire."); 
			
			try {
				for (int a = 0; a < this.getJoueur().getCite().length; a++) {
					if (this.getJoueur().getCite()[a].getType().equals(Quartier.TYPE_QUARTIERS[3])) {
						joueur.ajouterPieces(1);
						System.out.println("Le joueur récupère une pièce supplémentaire.");
					}
				}
			} catch (NullPointerException npe) {};
        } else {
            System.out.println("La marchande a été assassinée.");
        }
    }

	public void utiliserPouvoirAvatar() {   
        if (!assassine) {
            joueur.ajouterPieces(1);
            System.out.println("Le joueur récupère une pièce supplémentaire."); 

			try {
				for (int a = 0; a < this.getJoueur().getCite().length; a++) {
					if (this.getJoueur().getCite()[a].getType().equals(Quartier.TYPE_QUARTIERS[3])) {
						joueur.ajouterPieces(1);
						System.out.println("Le joueur récupère une pièce supplémentaire.");
					}
				}
			} catch (NullPointerException npe) {};
        } else {
            System.out.println("La marchande a été assassinée.");
        }
    }
}
