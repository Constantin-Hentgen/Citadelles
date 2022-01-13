package modele;

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
            Interaction inter = new Interaction();
            boolean oui = inter.lireOuiOuNon();
            

            if(oui){
                boolean evequeMort = false; // On ne peut pas echanger avec l'eveaue Mort
                boolean existeCiteConforme = false; // On ne peut pas  echanger avecune cite vide ou une cite complete
                boolean citeDiplomateVide = (this.joueur.nbQuartiersDansCite() == 0);
                //Eveque assassine ?
                for(int i = 0;i<plateau.getNombreJoueurs();i++){
                    Joueur j = plateau.getJoueur(i);
                    if(j.getPersonnage().getNom().equals("Eveque")){
                        evequeMort = j.getPersonnage().getAssassine();
                        if(!evequeMort){
                            if(j.nbQuartiersDansCite()>0 && j.nbQuartiersDansCite()<8){
                                existeCiteConforme =true;
                            }
                        }
                    }else{
                        if(j.nbQuartiersDansCite()>0 && j.nbQuartiersDansCite()<8){
                            for(int k =0;k<j.nbQuartiersDansCite();k++){
                                if(!this.joueur.quartierPresentDansCite(j.getCite()[k].getNom())){
                                    existeCiteConforme = true;
                                    break;
                                }
                            }
                            existeCiteConforme =true;
                        }
                    }
                }

                if(existeCiteConforme && !citeDiplomateVide){
                    System.out.println("Avec qui voulez vous effectuer un echange ?");
                    System.out.println();
                    for(int i =0;i<plateau.getNombreJoueurs();i++){
                        System.out.println(i+" | "+plateau.getJoueur(i)+ " | " + plateau.getJoueur(i).getPersonnage().getNom());
                    }

                    Joueur victime = this.joueur;
                    
                    while(victime.equals(this.joueur) || (victime.getPersonnage().getNom().equals("Eveque") && evequeMort )){
                        int choix = inter.lireUnEntier(0, plateau.getNombreJoueurs());
                        victime = plateau.getJoueur(choix);
                        if(victime.equals(this.joueur)){
                            System.out.println("Vous ne pouvez pas choisir vous meme");
                        }
                        if(victime.getPersonnage().getNom().equals("Eveque") && evequeMort){
                            System.out.println("L'eveque est protégé de vos attaques");
                        }
                    }
                    //Choix de la carte du diplomate a echanger
                    System.out.println("Quelle quartier de votre cité voulez-vous échanger ?");
                    
                    for(int i = 0;i<this.joueur.nbQuartiersDansCite();i++){
                        System.out.println();
                    } 
                    

                }
                
                

            }
            
        }
    }
    
}
