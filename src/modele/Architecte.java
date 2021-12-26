package modele;

public class Architecte extends Personnage {

    public Architecte() {
        super("Architecte",Caracteristiques.ARCHITECTE,7);
    }

    public void utiliserPouvoir() {
        for (int i = 0; i < 2; i++) {
            Quartier q = this.plateau.getPioche().piocher();
            this.joueur.ajouterQuartierDansMain(q);
        }
    }    
}
