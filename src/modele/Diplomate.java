package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Diplomate extends Personnage {

    public Diplomate(){
        super("Diplomate",Caracteristiques.DIPLOMATE,8);
    }

    public void percevoirRessourcesSpecifiques(){
        if (!assassine) {
            Quartier[] c = this.joueur.getCite();
            int recevoir = 0;

            for (int i = 0; i<this.joueur.nbQuartiersDansCite();i++) {
                if( c[i].getType().equals(Quartier.TYPE_QUARTIERS[1])) {
                    recevoir = recevoir +1;
                }
            }

        	this.joueur.ajouterPieces(recevoir);
			
			if (recevoir > 0) {
				System.out.println("Le diplomate a reçu " + recevoir + " pièce(s)");
			} else {
				System.out.println("Le diplomate n'a pas reçu de ressources spécifiques.");
			}
        }
    }

    public void utiliserPouvoir(){
        if(!assassine){
            System.out.println("Souhaitez vous echanger un des quartier de votre cite ? ");
            System.out.println();
            boolean oui = Interaction.lireOuiOuNon();
            

            if(oui){
                //Detection des personnes avec qui un echange est possible
                ArrayList<Joueur> joueurEchangePossible = new ArrayList<Joueur>();

                for(int i = 0;i<plateau.getNombreJoueurs();i++){
                    Joueur j = plateau.getJoueur(i);
                    if(!j.equals(this.joueur)){ // On ne veut pas de nous meme dans cette liste
                        if(j.nbQuartiersDansCite()>0 && j.nbQuartiersDansCite()<(j.getCite().length)){ //Cite non vide et non pleine + adaptabilite pour limite de quatiers dans cite
                            if(!j.getPersonnage().getNom().equals("Eveque") || j.getPersonnage().getAssassine()){ // Cas de perso non eveque ou mort
                                boolean ajoutPresence = true; //Autorisatiion car aucune copie
                                boolean ajoutCout = false; // Autorisation car peut s'offrir une carte
                                for(int k = 0;k<j.nbQuartiersDansCite();k++){
                                    if(j.quartierPresentDansCite(joueur.getCite()[k].getNom())){ //Si les deux joueurs possede le meme quartier on ne peut pas echanger avec lui 
                                        ajoutPresence = false;
                                        break;
                                    }
                                    else{ //Gestion du cout des cartes, possibilite de s'offrir une carte
                                            for(int n = 0; n<this.joueur.nbQuartiersDansCite();n++){
                                                if(j.getCite()[k].getCout() - this.getJoueur().getCite()[n].getCout() <= this.joueur.tresor()){ //Au mois une possible a s'offrir
                                                    ajoutCout = true;
                                                    break;
                                                }
                                            }
                                    }
                                }
                                if(ajoutPresence&& ajoutCout){ 
                                    joueurEchangePossible.add(j);
                                }
                            }
                        }
                    }
                }

                if(joueurEchangePossible.size()>0 && this.joueur.nbQuartiersDansCite() >0){ //Existe un echange et main diplomate non vide
                    //Selection de la victime del'echange
                    System.out.println("Avec quel joueur voulez vous echanger vos cartes ?");

                    for(int i = 0;i<joueurEchangePossible.size();i++){
                        System.out.println(i +" | " + joueurEchangePossible.get(i).getNom());
                    }
                    int choix = Interaction.lireUnEntier(0, joueurEchangePossible.size());
                    Joueur victime = joueurEchangePossible.get(choix);

                    //Reevaluation des possibilite d'echange pour le cout de la carte
                    ArrayList<Quartier> donPossible = new ArrayList<Quartier>();
                    ArrayList<Quartier> recevoirPossible = new ArrayList<Quartier>();

                    for(int i = 0; i<this.joueur.nbQuartiersDansCite();i++){
                        for(int k = 0;k<victime.nbQuartiersDansCite();k++){
                            if(victime.getCite()[k].getCout() - this.getJoueur().getCite()[i].getCout() <= this.joueur.tresor()){
                                if(!donPossible.contains(this.getJoueur().getCite()[i])){ // On ajoute pas deux fois le meme quartier
                                    donPossible.add(this.joueur.getCite()[i]);
                                }
                                if(!recevoirPossible.contains(victime.getCite()[k])){
                                    recevoirPossible.add(victime.getCite()[k]);
                                }
                            }
                        }
                            
                    }

                    if(donPossible.size() > 0 && recevoirPossible.size() > 0){
                        boolean echangeFait = false;
                        System.out.println("");
                            System.out.println("Quelle de vos carte voulez vous echanger ?");

                            for(int i = 0;i<donPossible.size();){
                                System.out.println(i +" | "+ donPossible.get(i).getNom() +" | "+ donPossible.get(i).getCout() + " PO");
                            }
                            choix = Interaction.lireUnEntier(0,donPossible.size());
                            Quartier don = donPossible.get(choix);

                        while(!echangeFait){
                            System.out.println();
                            System.out.println("Avec quelle carte ?");

                            for(int i = 0;i<recevoirPossible.size();){
                                System.out.println(i +" | "+ recevoirPossible.get(i).getNom() +" | "+ recevoirPossible.get(i).getCout() + " PO");
                            }

                            choix = Interaction.lireUnEntier(0,recevoirPossible.size());
                            Quartier recevoir = donPossible.get(choix);

                            if(recevoir.getCout() - don.getCout() <=this.joueur.tresor()){
                                System.out.println("Vous echangez vos quartier !");
                                victime.retirerQuartierDansCite(recevoir.getNom());
                                victime.ajouterQuartierDansCite(don);
                                this.joueur.retirerQuartierDansCite(don.getNom());
                                this.joueur.ajouterQuartierDansCite(recevoir);
                                if(recevoir.getCout() > don.getCout()){
                                    System.out.println("Vous payez "+ (recevoir.getCout() - don.getCout()) +"PO pour l'echange");
                                    this.joueur.retirerPieces(recevoir.getCout()-don.getCout());
                                    victime.ajouterPieces(recevoir.getCout()-don.getCout());
                                }
                                echangeFait = true;
                            }
                            else{
                                System.out.println("Fonds insuffisants");
                            }
                        }
                    }
                    else{ System.out.println("Echange impossible!!!(erreur)");}


                }else{System.out.println("Aucune cite compatible pour echange.");}
            }
            
        } else{System.out.println("Le joueur a ete assassine");}
    }

    public void utiliserPouvoirAvatar(){
        if(!assassine){
            Random random = new Random();

            if(random.nextBoolean()){
                 //Detection des personnes avec qui un echange est possible
                ArrayList<Joueur> joueurEchangePossible = new ArrayList<Joueur>();

                for(int i = 0;i<plateau.getNombreJoueurs();i++){
                    Joueur j = plateau.getJoueur(i);
                    if(!j.equals(this.joueur)){ // On ne veut pas de nous meme dans cette liste
                        if(j.nbQuartiersDansCite()>0 && j.nbQuartiersDansCite()<(j.getCite().length)){ //Cite non vide et non pleine + adaptabilite pour limite de quatiers dans cite
                            if(!j.getPersonnage().getNom().equals("Eveque") || j.getPersonnage().getAssassine()){ // Cas de perso non eveque ou mort
                                boolean ajoutPresence = true; //Autorisatiion car aucune copie
                                boolean ajoutCout = false; // Autorisation car peut s'offrir une carte
                                for(int k = 0;k<j.nbQuartiersDansCite();k++){
                                    if(j.quartierPresentDansCite(joueur.getCite()[k].getNom())){ //Si les deux joueurs possede le meme quartier on ne peut pas echanger avec lui 
                                        ajoutPresence = false;
                                        break;
                                    }
                                    else{ //Gestion du cout des cartes, possibilite de s'offrir une carte
                                            for(int n = 0; n<this.joueur.nbQuartiersDansCite();n++){
                                                if(j.getCite()[k].getCout() - this.getJoueur().getCite()[n].getCout() <= this.joueur.tresor()){ //Au mois une possible a s'offrir
                                                    ajoutCout = true;
                                                    break;
                                                }
                                            }
                                    }
                                }
                                if(ajoutPresence&& ajoutCout){ 
                                    joueurEchangePossible.add(j);
                                }
                            }
                        }
                    }
                }

                if(joueurEchangePossible.size()>0 && this.joueur.nbQuartiersDansCite() >0){ //Existe un echange et main diplomate non vide
                    //Selection de la victime del'echange

                    int choix = random.nextInt(0, joueurEchangePossible.size());
                    Joueur victime = joueurEchangePossible.get(choix);

                    //Reevaluation des possibilite d'echange pour le cout de la carte
                    ArrayList<Quartier> donPossible = new ArrayList<Quartier>();
                    ArrayList<Quartier> recevoirPossible = new ArrayList<Quartier>();

                    for(int i = 0; i<this.joueur.nbQuartiersDansCite();i++){
                        for(int k = 0;k<victime.nbQuartiersDansCite();k++){
                            if(victime.getCite()[k].getCout() - this.getJoueur().getCite()[i].getCout() <= this.joueur.tresor()){
                                if(!donPossible.contains(this.getJoueur().getCite()[i])){ // On ajoute pas deux fois le meme quartier
                                    donPossible.add(this.joueur.getCite()[i]);
                                }
                                if(!recevoirPossible.contains(victime.getCite()[k])){
                                    recevoirPossible.add(victime.getCite()[k]);
                                }
                            }
                        }
                            
                    }

                    if(donPossible.size() > 0 && recevoirPossible.size() > 0){
                        boolean echangeFait = false;// Choix de la carte a echanger

                        for(int i = 0;i<donPossible.size();){
                            System.out.println(i +" | "+ donPossible.get(i).getNom() +" | "+ donPossible.get(i).getCout() + " PO");
                        }
                        choix = random.nextInt(0,donPossible.size());
                        Quartier don = donPossible.get(choix);

                        while(!echangeFait){ //Choix de la carte a prendre

                            for(int i = 0;i<recevoirPossible.size();){
                                System.out.println(i +" | "+ recevoirPossible.get(i).getNom() +" | "+ recevoirPossible.get(i).getCout() + " PO");
                            }

                            choix = random.nextInt(0,recevoirPossible.size());
                            Quartier recevoir = donPossible.get(choix);

                            if(recevoir.getCout() - don.getCout() <=this.joueur.tresor()){
                                victime.retirerQuartierDansCite(recevoir.getNom());
                                victime.ajouterQuartierDansCite(don);
                                this.joueur.retirerQuartierDansCite(don.getNom());
                                this.joueur.ajouterQuartierDansCite(recevoir);
                                System.out.println(this.joueur.getNom()+" a echange un quartier de sa cite avec "+victime.getNom()+".");
                                if(recevoir.getCout() > don.getCout()){
                                    this.joueur.retirerPieces(recevoir.getCout()-don.getCout());
                                    victime.ajouterPieces(recevoir.getCout()-don.getCout());
                                }
                                echangeFait = true;
                            }

                        }
                    }
                    else{ System.out.println("Echange impossible!!!(erreur)");}


                }else{System.out.println("Echange Impossible.");}
                


            }else{System.out.println("Aucun echange n'a ete effectue");}
        } else{System.out.println("Le joueur a ete assassine");}
    }

    
}
