package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Archiviste extends Personnage {

    public Archiviste(){
        super("Archiviste",Caracteristiques.ARCHIVISTE,7);
    }
    
    public void utiliserPouvoir(){
        if(!assassine){
            //Pioche des 7 cartes
            ArrayList<Quartier> selection = new ArrayList<Quartier>();
            for(int i = 0; i<7;i++){
                try{
                    selection.add(plateau.getPioche().piocher());
                }catch (NullPointerException e){
                    System.out.println("Pioche Vide");
                    break;
                }
            }
            //Il faut au moins pouvoir choisir une carte
            if(selection.size()>0){
                //Affichage des cartes
                System.out.println("Veuillez choisir une de ces cartes :");
                System.out.println();
                for(int i = 0; i<selection.size();i++){
                    System.out.println(i+" | "+selection.get(i).getNom());
                }
                System.out.println();

                // Choix + addition de la carte a la main
                Interaction inter = new Interaction();
                int choix = inter.lireUnEntier(0, selection.size());
                System.out.println("Vous avez choisi : "+selection.get(choix).getNom());
                this.joueur.ajouterQuartierDansMain(selection.get(choix));
                selection.remove(choix);

                //Retour + melange des autres carte dans la pioche
                for(int i = 0;i<selection.size();i++){
                    plateau.getPioche().ajouter(selection.get(i));
                    selection.remove(i);
                }
                plateau.getPioche().melanger();
            }
            else{System.out.println("Pioche Vide");}

        }else{System.out.println("Le joueur a ete assassine");} 

    }

    public void utiliserPouvoirAvatar(){
        if(!assassine){
            ArrayList<Quartier> selection = new ArrayList<Quartier>();
            for(int i = 0; i<7;i++){
                try{
                    selection.add(plateau.getPioche().piocher());
                }catch (NullPointerException e){
                    System.out.println("Pioche Vide");
                    break;
                }
            }

            if(selection.size() > 0){
                //Choix de la carte
                Random random = new Random();
                int aleatoire = random.nextInt(selection.size());
                this.joueur.ajouterQuartierDansMain(selection.get(aleatoire));
                selection.remove(aleatoire);
                //Carte dans la pioche + melange
                for(int i = 0;i<selection.size();i++){
                    plateau.getPioche().ajouter(selection.get(i));
                    selection.remove(i);
                }
                plateau.getPioche().melanger();

            } else{System.out.println("Pioche Vide");}
        }else{System.out.println("Le joueur a ete assassine");}
    }
    
}
