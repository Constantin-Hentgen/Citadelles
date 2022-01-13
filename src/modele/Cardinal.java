package modele;

public class Cardinal extends Personnage {

    public Cardinal(){
        super("Cardinal",Caracteristiques.CARDINAL,5);
    }

    public void percevoirRessourcesSpecifiques(){
        int quartierReligieux = 0;

        for(int i = 0; i<this.joueur.nbQuartiersDansCite();i++){
            if(joueur.getCite()[i].getType().equals("RELIGIEUX")){
                this.joueur.ajouterQuartierDansMain(plateau.getPioche().piocher());
                quartierReligieux ++;
            }
        }
        System.out.println("Vous piochez"+ quartierReligieux+" cartes.");
        
    }

    public void utiliserPouvoir(){
        // avoir comment faire avec la gestion de la partie
        // Le pouvoir proc au moment de faire une constru
    }

    public void utiliserPouvoirAvatar(){
        //Meme problème
    }
    
    
}
