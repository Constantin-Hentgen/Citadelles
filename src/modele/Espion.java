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
            int c = Interaction.lireUnEntier(0,5);
            String choix = Quartier.TYPE_QUARTIERS[c];

            System.out.println("Veuillez choisir un joueur pour regarder sa main :");
            System.out.println();
            for (int i = 0; i < plateau.getNombreJoueurs(); i++) {                
                System.out.println(i+" : "+plateau.getJoueur(i).getNom());
            }
            
            Joueur choisi = this.joueur;
            do{
                c = Interaction.lireUnEntier(0,plateau.getNombreJoueurs());
                choisi = plateau.getJoueur(c);
                if ((choisi.equals(this.joueur))){ System.out.println("Vous ne pouvez pas vous choisir vous meme.");}
            } while ((choisi.equals(this.joueur)));
    

            System.out.println("La main de " + choisi.getNom());
            System.out.println();
            ArrayList<Quartier> main = choisi.getMain();
            int nbQuartierType = 0;
            for(int i = 0; i<choisi.nbQuartiersDansMain();i++){ // Pas d'exception pour une main vide(On ne rentre pas dans la boucle)
                Quartier q = main.get(i);
                System.out.println(i + " | " +q.getNom() + " | " + q.getType());
                if(q.getType().equals(choix)){
                    nbQuartierType = nbQuartierType +1;
                }
            }
            int tresorVole = choisi.tresor();
            if(tresorVole >= nbQuartierType){
                System.out.println();
                System.out.println("Vous volez "+nbQuartierType+" pieces");
                this.joueur.ajouterPieces(nbQuartierType);
                choisi.retirerPieces(nbQuartierType);
            }
            else{
                int nbPioche = nbQuartierType - tresorVole;
                System.out.println("Vous volez "+ tresorVole+" pieces");
                this.joueur.ajouterPieces(tresorVole);
                choisi.retirerPieces(tresorVole);
                System.out.println("Vous piochez "+nbPioche+" cartes");
                for(int i =0;i<nbPioche ;i++ ){
                    try{
                        this.joueur.ajouterQuartierDansMain(plateau.getPioche().piocher());
                    }catch(NullPointerException e){
                        System.out.println("La pioche est vide.");
                    }
                }
            }
        }
    }

    public void utiliserPouvoirAvatar(){
        if(!assassine){
            Random r = new Random();
            int c = r.nextInt(5);
            String choix = Quartier.TYPE_QUARTIERS[c];
            Joueur choisi = this.joueur;

            while(choisi.equals(this.joueur) && plateau.getNombreJoueurs() > 1){
                c = r.nextInt(plateau.getNombreJoueurs());
                choisi = plateau.getJoueur(c);
            }

            ArrayList<Quartier> main = choisi.getMain();
            int nbQuartierType = 0;
            for(int i =0;i< choisi.nbQuartiersDansMain();i++){
                if(main.get(i).getType().equals(choix)){
                    nbQuartierType = nbQuartierType+1;
                }
            }

            if(choisi.tresor() >= nbQuartierType){
                this.joueur.ajouterPieces(nbQuartierType);
                choisi.retirerPieces(nbQuartierType);
            }
            else{
                int nbPioche = nbQuartierType - choisi.tresor();
                this.joueur.ajouterPieces(choisi.tresor());
                choisi.retirerPieces(choisi.tresor());
                for(int i = 0; i<nbPioche;i++){
                    this.joueur.ajouterQuartierDansMain(plateau.getPioche().piocher());
                }
            }
            

        }

    }
    
}
