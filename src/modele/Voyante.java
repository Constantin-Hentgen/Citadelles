package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Voyante extends Personnage {

    public Voyante(){
        super("Voyante",Caracteristiques.VOYANTE,3);
    }

    public void utiliserPouvoir(){
        // Contruction de la liste des autres joueurs
        ArrayList<Joueur> restants = new ArrayList<Joueur>();
        for(int i=0;i< plateau.getNombreJoueurs();i++){
            Joueur j = plateau.getJoueur(i);
            if(!j.equals(this.joueur)){
                restants.add(j);
            }
        }

        // On prend les cartes au hasard
        ArrayList<Quartier> pris = new ArrayList<Quartier>();
        Random r = new Random();
        for(Joueur j :restants ){
            int n = j.nbQuartiersDansMain();
            int i = r.nextInt(n);
            try {
                ArrayList<Quartier> main = j.getMain();
                pris.add(main.get(i));
                j.retirerQuartierDansMain(main.get(i));
            } catch (NullPointerException e){
                System.out.println("Un joueur a une main vide");
            }
        }

        // Affichage des cartes
        Interaction inter = new Interaction();
        Quartier q = new Quartier();
        while(pris.size() != 0){
            // choix de la carte a donner
            System.out.println("Quelle carte souhaitez vous donner ?");
            System.out.println();

            for(int i = 0;i<pris.size();i++){
                System.out.println(i+" | "+pris.get(i).getNom());
            }
            System.out.println();
            int choix = inter.lireUnEntier(0, pris.size());
            q = pris.get(choix);
            pris.remove(q);
            System.out.println();

            // Choix du joueur
            System.out.println("A qui voulez vous donner " + q.getNom());
            System.out.println();
            for(int i = 0;i<restants.size();i++){
                System.out.println(i+" | "+ restants.get(i).getNom());
                
            }

            choix = inter.lireUnEntier(0,restants.size());
            Joueur receveur = restants.get(choix);
            receveur.ajouterQuartierDansMain(q);
            restants.remove(receveur);
            System.out.println();

        }
    }

    public void utiliserPouvoirAvatar(){
         // Contruction de la liste des autres joueurs
         ArrayList<Joueur> restants = new ArrayList<Joueur>();
         for(int i=0;i< plateau.getNombreJoueurs();i++){
             Joueur j = plateau.getJoueur(i);
             if(!j.equals(this.joueur)){
                 restants.add(j);
             }
         }
 
         // On prend les cartes au hasard
         ArrayList<Quartier> pris = new ArrayList<Quartier>();
         Random r = new Random();
         for(Joueur j :restants ){
             int n = j.nbQuartiersDansMain();
             int i = r.nextInt(n);
             try {
                ArrayList<Quartier> main = j.getMain();
                pris.add(main.get(i));
                j.retirerQuartierDansMain(main.get(i));
                }catch(NullPointerException e){
                    //Vide
                }
         }
        
         while(pris.size()!=0){
            int numeroCarte = r.nextInt(pris.size());
            int numeroJoueur = r.nextInt(restants.size());
            Quartier q = pris.get(numeroCarte);
            Joueur j = restants.get(numeroJoueur);
            j.ajouterQuartierDansMain(q);
            pris.remove(q);
            restants.remove(j);

         }

    }

    
}
