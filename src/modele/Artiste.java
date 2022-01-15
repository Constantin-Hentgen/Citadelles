package modele;

import java.util.Random;

import controleur.Interaction;

public class Artiste extends Personnage{
    //Ajout de boolean embellir a quartier pour fonctionner
    public Artiste(){
        super("Artiste",Caracteristiques.ARTISTE,9);
    }

    public void utiliserPouvoir(){
        if(!assassine){
            //Existence d'un quartier a embelir
            boolean quartierAEmbellir = false;
            for(int i = 0;i<this.joueur.nbQuartiersDansCite();i++){
                if(!this.joueur.getCite()[i].getEmbelli()){
                    quartierAEmbellir = true;
                    break;
                }
            }

            if(quartierAEmbellir){
                System.out.println("Souhaitez vous embellir un quartier ?");
                boolean oui = Interaction.lireOuiOuNon();

                if(oui){
                    System.out.println("Quel quartier voulez-vou embellir ?");
                    System.out.println();
                    

                    for(int i =0;i<joueur.nbQuartiersDansCite();i++){

                        if(joueur.getCite()[i].getEmbelli()){
                            System.out.println(i+" | "+joueur.getCite()[i].getNom()+" | deja embelli"); 
                        }
                        else{System.out.println(i+" | "+joueur.getCite()[i].getNom());}
                    }

                    Quartier quartierChoisi = new Quartier("","",0);
                    quartierChoisi.setEmbelli(); //force a entrer dans la boucle
                    int choix = 0;// Correspondra forcement aux choix du joueur car on rentre dans la boucle
                    while(quartierChoisi.getEmbelli()){
                        choix = Interaction.lireUnEntier(0,joueur.nbQuartiersDansCite());
                        quartierChoisi = joueur.getCite()[choix];
                        if(quartierChoisi.getEmbelli()){
                            System.out.println("Ce quartier a deja ete embelli.");
                        }
                    }
                    joueur.getCite()[choix].setEmbelli();
                    System.out.println(joueur.getCite()[choix].getNom() +" est embelli !");

                }
            }else{System.out.println("Vous n'avez aucun quartier qui peut etre embelli");}

        }else{System.out.println("Le joueur a ete assassine.");}
    }

    public void utiliserPouvoirAvatar(){
        if(!assassine){
            boolean quartierAEmbellir = false;
            for(int i = 0;i<this.joueur.nbQuartiersDansCite();i++){
                if(!this.joueur.getCite()[i].getEmbelli()){
                    quartierAEmbellir = true;
                    break;
                }
            }

            if(quartierAEmbellir){
                Random random = new Random();
                boolean oui = random.nextBoolean();

                if(oui){
                    Quartier quartierChoisi = new Quartier("","",0);
                    quartierChoisi.setEmbelli(); //force a entrer dans la boucle
                    int choix = 0;// Correspondra forcement aux choix du joueur car on rentre dans la boucle
                    while(quartierChoisi.getEmbelli()){
                        choix = random.nextInt(0,joueur.nbQuartiersDansCite());
                        quartierChoisi = joueur.getCite()[choix];
                    }
                    joueur.getCite()[choix].setEmbelli();
                    System.out.println(this.joueur.getNom()+" a embelli "+joueur.getCite()[choix].getNom());
                }
            }

        }else{System.out.println("Le joueur a ete assassine");}
    }
    
}
