package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Espion extends Personnage {

    public Espion(){
        super("Espion",Caracteristiques.ESPION,2);
    }

    public void utiliserPouvoir(){
        if(!assassine){
            System.out.println("Veuillez choisir un type de quartier :");
            System.out.println();

            for(int i = 0; i<5;i++){
                System.out.println(i+" | "+Quartier.TYPE_QUARTIERS[i]);
            }
            Interaction j = new Interaction();
            int c = j.lireUnEntier(0,5);
            String choix = Quartier.TYPE_QUARTIERS[c];

            System.out.println("Veuillez choisir un joueur pour reagrder sa main :");
            System.out.println();
            for (int i = 0; i < plateau.getNombreJoueurs(); i++) {                
                System.out.println(i+" : "+plateau.getJoueur(i).getNom());
            }
            
            Joueur choisi = this.joueur;
            do{
                c = j.lireUnEntier(0,plateau.getNombreJoueurs());
                choisi = plateau.getJoueur(c);
                if ((choisi.equals(this.joueur))){ System.out.println("Vous ne pouvez pas vous choisir vous meme.");}
            } while ((choisi.equals(this.joueur)));
    

            System.out.println("La main de " + choisi.getNom());
            System.out.println();
            ArrayList<Quartier> main = choisi.getMain();
            int ajout = 0;
            for(int i = 0; i<choisi.nbQuartiersDansMain();i++){
                Quartier q = main.get(i);
                System.out.println(i + " | " +q.getNom() + " | " + q.getType());
                if(q.getType().equals(choix)){
                    ajout = ajout +1;
                }
            }
            System.out.println();
            System.out.println("Vous avez gagner "+ajout+" pieces");
            this.joueur.ajouterPieces(ajout);
        }
    }

    public void utiliserPouvoirAvatar(){
        if(!assassine){
            Random r = new Random();
            int c = r.nextInt(5);
            String choix = Quartier.TYPE_QUARTIERS[c];
            Joueur choisi = this.joueur;

            while(choisi.equals(this.joueur)){
                c = r.nextInt(plateau.getNombreJoueurs());
                choisi = plateau.getJoueur(c);
            }

            ArrayList<Quartier> main = choisi.getMain();
            int ajout = 0;
            for(int i =0;i< choisi.nbQuartiersDansMain();i++){
                if(main.get(i).getType().equals(choix)){
                    ajout = ajout+1;
                }
            }

            this.joueur.ajouterPieces(ajout);

        }

    }
    
}
