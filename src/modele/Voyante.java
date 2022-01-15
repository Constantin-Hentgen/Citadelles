package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Voyante extends Personnage {

    public Voyante(){
        super("Voyante",Caracteristiques.VOYANTE,3);
    }

    public void utiliserPouvoir(){
        // Contruction de la liste des autres joueurs avec une main non vide
        ArrayList<Joueur> restants = new ArrayList<Joueur>();
        for(int i=0;i< plateau.getNombreJoueurs();i++){
            Joueur j = plateau.getJoueur(i);
            if(!j.equals(this.joueur) && j.getMain().size() != 0){
                restants.add(j);
            }
        }

        // On prend les cartes au hasard
        Random r = new Random();
        for(Joueur j :restants ){
            int n = j.nbQuartiersDansMain();
            int i = r.nextInt(n);
            try {
                ArrayList<Quartier> main = j.getMain();
                this.joueur.ajouterQuartierDansMain(main.get(i)); //on ajaoute la carte a la main
                j.retirerQuartierDansMain(main.get(i));
            } catch (NullPointerException e){
                System.out.println("Un joueur a une main vide"); // Nomrmalent cette erreur ne devrait pas s'afficher
            }
        }

        // Affichage des cartes
        Quartier q = new Quartier();
        while(restants.size() != 0){
            // choix de la carte a donner
            System.out.println("Quelle carte souhaitez vous donner ?");
            System.out.println();

            for(int i = 0;i<this.joueur.getMain().size();i++){
                System.out.println(i+" | "+this.joueur.getMain().get(i).getNom());
            }
            System.out.println();
            int choix = Interaction.lireUnEntier(0, this.joueur.getMain().size());
            q = this.joueur.getMain().get(choix);
            
            System.out.println();

            // Choix du joueur
            System.out.println("A qui voulez vous donner " + q.getNom());
            System.out.println();
            for(int i = 0;i<restants.size();i++){
                System.out.println(i+" | "+ restants.get(i).getNom());
                
            }

            choix = Interaction.lireUnEntier(0,restants.size());
            Joueur receveur = restants.get(choix);
            this.joueur.getMain().remove(q);
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
             if(!j.equals(this.joueur) && j.getMain().size() !=0){
                 restants.add(j);
             }
         }
 
         // On prend les cartes au hasard
         Random r = new Random();
         for(Joueur j :restants ){
             int n = j.nbQuartiersDansMain();
             int i = r.nextInt(n);
             try {
                ArrayList<Quartier> main = j.getMain();
                this.joueur.ajouterQuartierDansMain(main.get(i));
                j.retirerQuartierDansMain(main.get(i));
                }catch(NullPointerException e){
                    //Vide pas censé arriver
                }
         }
        
         while(restants.size()!=0){
            int numeroCarte = r.nextInt(this.joueur.getMain().size());
            int numeroJoueur = r.nextInt(restants.size());
            Quartier q = this.joueur.getMain().get(numeroCarte);
            Joueur j = restants.get(numeroJoueur);
            j.ajouterQuartierDansMain(q);
            this.joueur.retirerQuartierDansMain(q);
            restants.remove(j);

         }

    }

    
}
