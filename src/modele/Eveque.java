package modele;

public class Eveque extends Personnage {

    public Eveque() {
        super("Eveque",Caracteristiques.EVEQUE,5);
    }

    public void percevoirRessourcesSpecifiques() {
        if (!assassine) {
            Quartier[] c = this.joueur.getCite();
            int recevoir = 0;
            for(int i = 0; i<this.joueur.nbQuartiersDansCite();i++) {
                if( c[i].getType().equals(Quartier.TYPE_QUARTIERS[0])) {
                    recevoir = recevoir +1;
                }
            }
        this.joueur.ajouterPieces(recevoir);
        System.out.println("L'évêque a reçu " + recevoir + " pièce(s)");
        }
    }

    public void utiliserPouvoir() {
        System.out.println("Le joueur est protégé contre les attaques des personnages de rang 8");
    }

    public void utiliserPouvoirAvatar() {
        System.out.println("Le joueur est protégé contre les attaques des personnages de rang 8");
    }
}