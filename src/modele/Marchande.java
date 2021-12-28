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
            joueur.ajouterPieces(1);
            System.out.println("La marchande récupère une pièce."); 
        } else {
            System.out.println("La marchande a été assassinée.");
        }
    }

	public void utiliserPouvoirAvatar() {   
        if (!assassine) {
            joueur.ajouterPieces(1);
            System.out.println("La marchande récupère une pièce."); 
        } else {
            System.out.println("La marchande a été assassinée.");
        }
    }
}
