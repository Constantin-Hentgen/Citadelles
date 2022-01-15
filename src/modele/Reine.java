package modele;

public class Reine extends Personnage {
    private boolean revenirPlusTard = false;

    public Reine(){
        super("Reine",Caracteristiques.REINE,9);
    }


    public void setRevenirPlusTard(){
        this.revenirPlusTard = true;
    }

    public boolean getRevenirPlusTard(){
        return this.revenirPlusTard;
    }

    public void percevoirRessourcesSpecifiques(){
        if(!assassine){
            //Recherche du rang du joueur dans la liste.
            // On definit "a cote" par position dans la liste + ou - 1, si dernier le suivant est le premier de la liste
            int positionJoueur = 0;
            for(int i = 0;i<plateau.getNombreJoueurs();i++){
                if(plateau.getJoueur(i).equals(this.joueur)){
                    positionJoueur = i;
                    break;
                }
            }
            
            boolean roiACote = false;
            Joueur rang4 = this.joueur;
            //Recherhce de roi a cote
            if(positionJoueur == 0){ // Si le joueur est au debut
                if(plateau.getJoueur(positionJoueur+1).getPersonnage().getRang() == 4){
                    roiACote = true;
                    rang4 = plateau.getJoueur(positionJoueur+1);
                }
                else if(plateau.getJoueur(plateau.getNombreJoueurs()-1).getPersonnage().getRang() == 4){
                    roiACote = true;
                    rang4 = plateau.getJoueur(plateau.getNombreJoueurs()-1);
                }
            }
            else if(positionJoueur == plateau.getNombreJoueurs()-1){ //si le joueur est a la fin
                if(plateau.getJoueur(0).getPersonnage().getRang() == 4){
                    roiACote = true;
                    rang4 = plateau.getJoueur(0);
                }
                else if(plateau.getJoueur(positionJoueur-1).getPersonnage().getRang() == 4){
                    roiACote = true;
                    rang4 = plateau.getJoueur(positionJoueur-1);
                }
            }
            else{
                if(plateau.getJoueur(positionJoueur+1).getPersonnage().getRang() == 4){
                    roiACote = true;
                    rang4 = plateau.getJoueur(positionJoueur+1);
                }
                else if(plateau.getJoueur(positionJoueur-1).getPersonnage().getRang() == 4){
                    roiACote = true;
                    rang4 = plateau.getJoueur(positionJoueur-1);
                }
            }

            if(roiACote){
                if(!rang4.getPersonnage().getAssassine() && !revenirPlusTard){
                    System.out.println("Vous recevez 3 pieces car vous etes assis a cote du Roi");
                    this.joueur.ajouterPieces(3);
                }

                if(revenirPlusTard){
                    System.out.println("Vous recevez 3 pieces car vous etes assis a cote du Roi.");
                    this.joueur.ajouterPieces(3);
                }
                
                if(rang4.getPersonnage().getAssassine() && !revenirPlusTard){
                    System.out.println("Revenez a la fin du tour pour recevoir vos 3 pieces.");
                    setRevenirPlusTard();
                }
            }else{
                System.out.println("Vous n'etes pas assis a cote du Roi");
            }
            
        }else{System.out.println("Le joueur a ete assassine");}
    }

    public void utiliserPouvoir(){
        System.out.println("La reine ne possede pas de pouvoir");
    }

    public void utiliserPouvoirAvatar(){

    }
    
}
