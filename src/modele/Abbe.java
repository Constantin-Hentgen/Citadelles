package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Abbe extends Personnage {

    public Abbe(){
        super("Abbe",Caracteristiques.ABBE,5);
    }
    
    public void percevoirRessourcesSpecifiques(){
        if(!assassine){
            int quartierReligieux = 0;

            for(int i = 0; i<this.joueur.nbQuartiersDansCite();i++){
                if(joueur.getCite()[i].getType().equals("RELIGIEUX")){
                    quartierReligieux = quartierReligieux +1;
                }
            }

            System.out.println("Vous avez " + quartierReligieux + "dans votre cite");
            if(quartierReligieux > 0){
                System.out.println("Combien voulez vous d'or ?(Maximum : " +quartierReligieux);
                int choixPiece = Interaction.lireUnEntier(0, quartierReligieux+1);
                this.joueur.ajouterPieces(choixPiece);
                System.out.println();
                System.out.println("Vous piochez "+ (quartierReligieux-choixPiece)+" cartes");
                for(int i = 0;i<(quartierReligieux-choixPiece);i++){
                    this.joueur.ajouterQuartierDansMain(plateau.getPioche().piocher());
                }
            }
        }
    }

    public void utiliserPouvoir(){
        if(!assassine){
            if(plateau.getNombreJoueurs()>0){
                ArrayList<Joueur> plusRiche = new ArrayList<Joueur>();
                try{
                    plusRiche.add(plateau.getJoueur(0));
                } catch(NullPointerException e){
                    System.out.println("Il n'y a aucun joueur"); // N'est pas censé arriver
                }
            
                //On cherche le joeur le plus riche, ou les plus riches
                for(int i = 1;i<plateau.getNombreJoueurs();i++){ // On commence a 1 car 0 est notre ^plus riche de base
                    if(plateau.getJoueur(i).tresor()>=plusRiche.get(0).tresor()){
                        if(plateau.getJoueur(i).tresor() == plusRiche.get(0).tresor()){
                            plusRiche.add(plateau.getJoueur(i));
                        }
                        else{
                            plusRiche = new ArrayList<Joueur>();
                            plusRiche.add(plateau.getJoueur(i));
                        }
                    }
                }

                // on donne l'or  l'abbe si un joueur est plus riche
                if(plusRiche.size() == 1 && !plusRiche.get(0).equals(this.joueur)){
                    System.out.println(plusRiche.get(0).getNom()+" est le plus riche.");
                    if(plusRiche.get(0).tresor()>0){
                        System.out.println("Vous lui prenez 1 pièce d'or");
                        this.joueur.ajouterPieces(1);
                        plusRiche.get(0).retirerPieces(1);
                    }
                    else{System.out.println("Le plus riche n'as pas de piece d'or");}
                    
                }
                else if (plusRiche.size() ==1 && plusRiche.contains(this.joueur)){
                    System.out.println("Vous etes le plus riche, vous ne pouvez pas prendre de pieces");

                }
                else{
                    System.out.println("A qui voulez vous prendre 1 piece");
                    System.out.println();
                    for(int i = 0; i<plusRiche.size();i++){
                        System.out.println(i +"| "+plusRiche.get(i).getNom());
                    }
                    System.out.println();

                    Joueur victime = this.joueur;
                    while(victime.equals(this.joueur)){
                        int choix = Interaction.lireUnEntier(0,plusRiche.size());
                        victime = plusRiche.get(choix);
                        if(victime.equals(this.joueur)){
                            System.out.println("Vous ne pouvez pas vous cibler vous meme");
                        }
                    }

                    this.joueur.ajouterPieces(1);
                    victime.retirerPieces(1);

                }
                
            }
        } else{ 
            System.out.println("Le joueur a ete asassine");
        }

       
    }
    
    public void utiliserPouvoirAvatar(){ // les ressources specifiques aussi seront la dedans puisqu'elle demande un choix
                                        // a voir si il faire de meme pour un vrai joueur
        if(!assassine){
            int quartierReligieux = 0;

            for(int i = 0; i<this.joueur.nbQuartiersDansCite();i++){
                if(joueur.getCite()[i].getType().equals("RELIGIEUX")){
                    quartierReligieux = quartierReligieux +1;
                }
            }

            if(quartierReligieux > 0){
                Random random = new Random();
                int choixPiece = random.nextInt(1, quartierReligieux+1);
                this.joueur.ajouterPieces(choixPiece);
                System.out.println();
                for(int i = 0;i<(quartierReligieux-choixPiece);i++){
                    this.joueur.ajouterQuartierDansMain(plateau.getPioche().piocher());
                }
            }
        }

        // Le pouvoir
        if(plateau.getNombreJoueurs()>0){
            ArrayList<Joueur> plusRiche = new ArrayList<Joueur>();
            try{
                plusRiche.add(plateau.getJoueur(0));
            } catch(NullPointerException e){
                System.out.println("Il n'y a aucun joueur"); // N'est pas censé arriver
            }
        
            //On cherche le joeur le plus riche, ou les plus riches
            for(int i = 1;i<plateau.getNombreJoueurs();i++){ // On commence a 1 car 0 est notre ^plus riche de base
                if(plateau.getJoueur(i).tresor()>=plusRiche.get(0).tresor()){
                    if(plateau.getJoueur(i).tresor() == plusRiche.get(0).tresor()){
                        plusRiche.add(plateau.getJoueur(i));
                    }
                    else{
                        plusRiche = new ArrayList<Joueur>();
                        plusRiche.add(plateau.getJoueur(i));
                    }
                }
            }

            // on donne l'or  l'abbe si un joueur est plus riche
            if(plusRiche.size() == 1 && !plusRiche.get(0).equals(this.joueur)){
                if(plusRiche.get(0).tresor()>0){
                    this.joueur.ajouterPieces(1);
                    plusRiche.get(0).retirerPieces(1);
                }
                else{}
                
            }
            else if (plusRiche.size() ==1 && plusRiche.contains(this.joueur)){

            }
            else{

                
                Random random = new Random();
                Joueur victime = this.joueur;
                while(victime.equals(this.joueur)){
                    int choix = random.nextInt(plusRiche.size());
                    victime = plusRiche.get(choix);
                }
                this.joueur.ajouterPieces(1);
                victime.retirerPieces(1);

            }
            
        }


    }
}
