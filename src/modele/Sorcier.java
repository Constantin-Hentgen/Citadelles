package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Sorcier extends Personnage{

    public Sorcier(){
        super("Sorcier",Caracteristiques.SORCIER,3);
    }

    public void utiliserPouvoir(){
        if(!assassine){
            //Affichage des joueurs
            System.out.println("Veuillez choisir un joueur :");
            System.out.println();
            for(int i = 0; i<plateau.getNombreJoueurs();i++){
                System.out.println(i+" | "+ plateau.getJoueur(i).getNom());
            }

            // Verification au moins une personne avec une main non vide
            boolean nonVide = false;
            for(int i = 0; i<plateau.getNombreJoueurs();i++){
                if(!plateau.getJoueur(i).equals(this.joueur)){
                    if(plateau.getJoueur(i).getMain().size() != 0){
                        nonVide = true;
                    }
                }
            }



            //Choix de la carte ( à l'aveugle par interpretation de la carte)
            if(nonVide){

                System.out.println();
                // Selection du joueur
                Joueur j = this.joueur;
                Interaction inter = new Interaction();
                while (j.equals(this.joueur) && j.nbQuartiersDansMain() !=0 ){   
                    int choix = inter.lireUnEntier(0, plateau.getNombreJoueurs());
                    j = plateau.getJoueur(choix);
                    if(j.equals(this.joueur)){System.out.println("Vous ne puvez pas choisir vous meme");}
                    if(j.nbQuartiersDansMain() == 0 ){ System.out.println("Ce joueur n'as pas de carte dans sa main");}
                }

                System.out.println("Quelle carte voulez vous ?");
                ArrayList<Quartier> main = j.getMain();
                System.out.println();
                for(int i = 0;i<main.size();i++){
                    System.out.println(i+" | "+ main.get(i).getNom());
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
        
            } else { System.out.println("Aucun joueur n'as de cartes en main");}
        } else {System.out.println("Le joueur a ete assassiné");}
    }
       

    public void utiliserPouvoirAvatar(){
        if(!assassine){

        
            // Verifaction d'une main non vide
            boolean nonVide = false;
            for(int i = 0; i<plateau.getNombreJoueurs();i++){
                if(!plateau.getJoueur(i).equals(this.joueur)){
                    if(plateau.getJoueur(i).getMain().size() != 0){
                        nonVide = true;
                    }
                }
            }
            
            if(nonVide){
                Random r = new Random();
                int aleatoire = r.nextInt(plateau.getNombreJoueurs());
                Joueur j = this.joueur;
                
                while(this.joueur.equals(j) && j.nbQuartiersDansMain() !=0){
                    j = plateau.getJoueur(aleatoire);
                    aleatoire = r.nextInt(plateau.getNombreJoueurs());
                }

                aleatoire = r.nextInt(j.nbQuartiersDansMain());
                Quartier q = j.getMain().get(aleatoire);
                j.retirerQuartierDansMain(q);
                boolean alea = r.nextBoolean();
                if(alea){
                    this.joueur.ajouterQuartierDansMain(q);
                }else {
                    if(this.joueur.tresor() >= q.getCout()){
                        this.joueur.ajouterQuartierDansCite(q);
                        this.joueur.retirerPieces(q.getCout());
                    }
                    else { this.joueur.ajouterQuartierDansMain(q);}
                }

            }
        }else{System.out.println("Le joueur a ete assassine");}
        
    }

    
}
