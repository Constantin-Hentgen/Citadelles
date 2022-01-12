package modele;

import java.util.ArrayList;

import controleur.Interaction;

public class Sorcier extends Personnage{

    public Sorcier(){
        super("Sorcier",Caracteristiques.SORCIER,3);
    }

    public void utiliserPouvoir(){
        //Affichage des joueurs
        System.out.println("Veuillez choisir un joueur :");
        System.out.println();
        for(int i = 0; i<plateau.getNombreJoueurs();i++){
            System.out.println(i+" | "+ plateau.getJoueur(i).getNom());
        }
        //Choix du joueur
        System.out.println();

        Joueur j = this.joueur;
        Interaction inter = new Interaction();
        while (j.equals(this.joueur)){   
            int choix = inter.lireUnEntier(0, plateau.getNombreJoueurs());
            j = plateau.getJoueur(choix);
            if(j.equals(this.joueur)){System.out.println("Vous ne puvez pas choisir vous meme");}
        }


        //Choix de la carte ( à l'aveugle par interpretation de la carte)
        System.out.println("Quelle carte voulez vous ?");
        ArrayList<Quartier> main = j.getMain();
        System.out.println();
        for(int i = 0;i<main.size();i++){
            System.out.println(i+" | Carte "+i);
        }
        System.out.println();
        int choix = inter.lireUnEntier(0, main.size());
        Quartier q = main.get(choix);
        j.getMain().remove(q);
        System.out.println("La carte est : "+q.getNom());
        System.out.println();

        //Construction ou ajouter a la main
        if(this.joueur.tresor() < q.getCout()){
            System.out.println("Vous n'avez pas assez d'or dans votre trésor pour construire");
            System.out.println("La carte est ajoutée a votre main");
            this.joueur.ajouterQuartierDansMain(q);
        }
        else{
            System.out.println("Voulez vons contruire ou ajouter a votre main ?");
            System.out.println("0 | Construire");
            System.out.println("1 | Ajouter a la main");
            System.out.println();

            choix = inter.lireUnEntier(0,2);
            if(choix == 0){
                this.joueur.ajouterQuartierDansCite(q);
                this.joueur.retirerPieces(q.getCout());
            }
            else{
                this.joueur.ajouterQuartierDansMain(q);
            }
        }

    }

    public void utiliserPouvoirAvatar(){

    }
    
}
