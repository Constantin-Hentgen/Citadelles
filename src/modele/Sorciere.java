package modele;

import java.util.Random;

import controleur.Interaction;

public class Sorciere extends Personnage {

    public Sorciere(){
        super("Sorciere",Caracteristiques.SORCIERE,1);
    }

    public void utiliserPouvoir(){

        System.out.println("Veuillez choisir un joueur a ensorcele :");
        System.out.println();

        //Affichage des joueurs avec leurs personnages
        for(int i = 0;i<plateau.getNombreJoueurs();i++){ 
            System.out.println(i+" | "+plateau.getPersonnage(i));
        }
        //Choix du personnage
        Personnage p = this;
        while(p.equals(this)){
            int choix = Interaction.lireUnEntier(0, plateau.getNombreJoueurs());
            p = plateau.getPersonnage(choix);
            if(p.equals(this)){System.out.println("Vous ne pouvez pas vous ensorceler vous meme");}
        }

        p.setEnsorcele();
        // L'idee dans le tour de jeu est de changer le personnage de joueurs quand il y a la sorciere
        // Aucune modif dans le tour de jeu pour l'instant
    }

    public void utiliserPouvoirAvatar(){
        Personnage p = this;
        Random r = new Random();
        int aleatoire = r.nextInt(plateau.getNombreJoueurs());
        while(p.equals(this)){
            aleatoire = r.nextInt(plateau.getNombreJoueurs());
            p = plateau.getPersonnage(aleatoire);
        }
        
        p.setEnsorcele();
        System.out.println(p.getNom() + " est ensorcele.");
    }
    
}
