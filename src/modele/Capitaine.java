package modele;

import java.util.ArrayList;

import controleur.Interaction;

public class Capitaine extends Personnage {

    public Capitaine(){
        super("Capitaine",Caracteristiques.CAPITAINE,8);
    }

    public void percevoirRessourcesSpecifiques(){
        try {
			Joueur condottiere = new Joueur("Condottiere");

			// on va chercher le condottiere et on le stocke
			for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
				if (plateau.getPersonnage(i).getNom().equals("Condottiere")) {
					condottiere = plateau.getJoueur(i);
				}
			}

			// on ajoute une pièce pour chaque quartier militaire
			for (int j = 0; j < condottiere.nbQuartiersDansCite() - 1; j++) {
				String nomQuartier = condottiere.getCite()[j].getNom();
				if (nomQuartier.equals("prison") || nomQuartier.equals("tour de guet") || nomQuartier.equals("caserne") || nomQuartier.equals("forteresse")) {
					condottiere.ajouterPieces(1);
				}
			}
		} catch (NullPointerException npe) {}
    }

    public void utiliserPouvoir(){
        if(!assassine){
            //On verifie si le pouvoir est utilisable.
            boolean pouvoirUtilisable = false;

            if(joueur.tresor() >= 3){ // On doit payer au minimu 3 PO
                int tresor = joueur.tresor();
                //On cherche une personne avec un quartier different achetable
                for(int i = 0;i<plateau.getNombreJoueurs();i++){
                    if(!(plateau.getJoueur(i).getPersonnage().getNom().equals("Eveque") && !plateau.getJoueur(i).getPersonnage().getAssassine())){
                        for(int j =0;j<plateau.getJoueur(i).nbQuartiersDansCite();j++){
                            if(!joueur.quartierPresentDansCite(plateau.getJoueur(i).getCite()[j].getNom())){
                                if(plateau.getJoueur(i).getCite()[j].getCout() >= 3 && plateau.getJoueur(i).getCite()[j].getCout() <= tresor){
                                    pouvoirUtilisable = true;
                                    break;
                                }
                            }
                        }
                    }
                    
                }

                if(pouvoirUtilisable){
                    System.out.println("Souhaitez vous utiliser votre Pouvoir ?");
                    System.out.println();

                    boolean oui =Interaction.lireOuiOuNon();
                    if(oui){
                        System.out.println("A quel joueur voulez vous prendre un quartier dans sa cite ?");
                        System.out.println();

                        //Affichage des joueurs
                        for(int i = 0;i<plateau.getNombreJoueurs();i++){
                            System.out.println(i+" | "+plateau.getJoueur(i).getNom());
                        }
                        //Choix du personnage
                        boolean choisi = false;
                        int choix = 0;
                        while(!choisi){
                            choix = Interaction.lireUnEntier(0,plateau.getNombreJoueurs());
                            if(plateau.getJoueur(choix).getNom().equals("Eveque")&& !plateau.getJoueur(choix).getPersonnage().getAssassine()){
                                System.out.println("L'Eveque est protege de votre pouvoir");
                            }
                            else if(plateau.getJoueur(choix).equals(this.joueur)){
                                System.out.println("Vous ne pouvez pas vous choisis vous meme");
                            }
                            else{
                                //On regarde si la cite du joueur choisis possede un echange possible
                                for(int i =0;i<plateau.getJoueur(i).nbQuartiersDansCite();i++){  
                                    if(!joueur.quartierPresentDansCite(plateau.getJoueur(choix).getCite()[i].getNom())){
                                        if(plateau.getJoueur(choix).getCite()[i].getCout() >= 3 && plateau.getJoueur(choix).getCite()[i].getCout() <= tresor){
                                            choisi= true;
                                            break;
                                        }
                                    } 
                                }
                                if(!choisi){
                                    System.out.println("Ce joueur ne possede pas de carte echangable avec vous");
                                }
                            }
                        }

                        System.out.println("Quartier voulez vous prendre ?");
                        for(int i = 0; i<plateau.getJoueur(choix).nbQuartiersDansCite();i++){
                            System.out.println(i+" | "+plateau.getJoueur(choix).getCite()[i]+" | "+plateau.getJoueur(choix).getCite()[i].getCout()+" PO");
                        }
                        
                        do{
                            int carte = Interaction.lireUnEntier(0,plateau.getJoueur(choix).nbQuartiersDansCite());
                            if(!(plateau.getJoueur(choix).getCite()[carte].getCout() <= tresor && !joueur.quartierPresentDansCite(plateau.getJoueur(choix).getCite()[carte].getNom()))){
                                System.out.println("Vous ne pouvez pas prendre cette carte");
                            }
                        } while( !(plateau.getJoueur(choix).getCite()[carte].getCout() <= tresor && !joueur.quartierPresentDansCite(plateau.getJoueur(choix).getCite()[carte].getNom())))

                    }
                }

            }
        }
    }
    
}
