package modele;

import java.util.Random;

import controleur.Interaction;

public class Navigatrice extends Personnage{

    public Navigatrice(){
        super("Navigatrice",Caracteristiques.NAVIGATRICE,7);

    }

    public void utiliserPouvoir(){

        if(!assassine){
            System.out.println("Voulez vous 4 pieces d'or ou 4 quartiers ?");
            System.out.println();
            System.out.println("0 | 4 pieces d'or");
            System.out.println("1 | 4 quartiers");
            System.out.println();
            Interaction inter = new Interaction();
            int choixPiece = inter.lireUnEntier(0,2);
            if(choixPiece == 0){
                System.out.println("Vous recevez 4 pieces");
                this.joueur.ajouterPieces(4);
            }
            else if (choixPiece == 1){
                System.out.println("Vous piochez 4 cartes");
                for(int i = 0;i<4;i++){
                    try{
                        this.joueur.ajouterQuartierDansMain(plateau.getPioche().piocher());
                    } catch(NullPointerException e){
                        System.out.println("Pioche vide");
                    }
                    
                }
            }
        } else {System.out.println("Le joueur a été assassiné");}
    }

    public void utiliserPouvoirAvatar(){
        if(!assassine){
            Random random = new Random();
            int choix = random.nextInt(2);

            if(choix == 0){
                this.joueur.ajouterPieces(4);
            }
            else{
                for(int i = 0; i<4;i++){
                    try{
                        this.joueur.ajouterQuartierDansMain(plateau.getPioche().piocher());
                    } catch(NullPointerException e){
                        System.out.println("Pioche vide");
                    }
                }
                
            }
        }else{System.out.println("Le joueur a ete assassine");}
    }
    
}
