package modele;

import java.util.Random;

import controleur.Interaction;

public class Empereur extends Personnage {

    public Empereur(){
        super("Empeureur",Caracteristiques.EMPEREUR,4);
    }

    public void percevoirRessourcesSpecifiques(){
        int compteur = 0;
		if (this.joueur != null && this.assassine != true) {
			for (Quartier unQuartier : joueur.getCite()) {
				if (unQuartier != null && unQuartier.getType() == "NOBLE") {
					compteur ++;
				}
			}
			this.joueur.ajouterPieces(compteur);
		}
    }

    public void utiliserPouvoir(){
        // Recherche et retirement de la couronne
        boolean couronneExiste = false;
        Joueur ancienCouronne = this.plateau.getJoueur(0);
        for(int i = 0;i<plateau.getNombreJoueurs();i++){
            if(plateau.getJoueur(i).getPossedeCouronne()){
                ancienCouronne = plateau.getJoueur(i);
                couronneExiste = true;
                plateau.getJoueur(i).setPossedeCouronne(false);
                break;
            }
        }
        
        if(couronneExiste){
            //Affichage + selcetion du joueur
            System.out.println("A qui voulez vous donner la couronne ?");
            System.out.println();

            for(int i = 0;i<plateau.getNombreJoueurs();i++){
                System.out.println(i+" | "+ plateau.getJoueur(i).getNom());
            }
            System.out.println();

            Joueur j = this.joueur;
            Interaction inter = new Interaction();
            while(j.equals(this.joueur) || j.equals(ancienCouronne)){
                int choix = inter.lireUnEntier(0, plateau.getNombreJoueurs());
                j = plateau.getJoueur(choix);
                if(j.equals(this.joueur)){System.out.println("Vous ne pouvez pas vous donnez la couronne");}
                if(j.equals(ancienCouronne)){System.out.println("Vous ne pouvez pas redonner la couronne a son ancien proprietaire");}
            }
            //Don de la couronne au dit joueur
            j.setPossedeCouronne(true);

            //Paiement aleatoire(bots)

            int tresor = j.tresor();
            int nbMain = j.nbQuartiersDansMain();
            Random r = new Random();
            if(tresor != 0 && nbMain != 0){
                boolean paiementPiece = r.nextBoolean();
                if(paiementPiece){
                    System.out.println(j.getNom() + " vous donne 1 piece pour la couronne");
                    j.retirerPieces(1);
                    this.joueur.ajouterPieces(1);
                }
                else{
                    System.out.println(j.getNom()+" vous donne 1 quartier pour la couronne");
                    int aleatoire = r.nextInt(nbMain);
                    Quartier q = j.getMain().get(aleatoire);
                    j.retirerQuartierDansMain(q);
                    this.joueur.ajouterQuartierDansMain(q);
                    System.out.println();
                    System.out.println("Vous avez obtenu : " + q.getNom());
                }
            }
            else {
                if(tresor == 0){
                    try{
                        System.out.println(j.getNom()+" vous donne 1 quartier pour la couronne");
                        int aleatoire = r.nextInt(nbMain);
                        Quartier q = j.getMain().get(aleatoire);
                        j.retirerQuartierDansMain(q);
                        this.joueur.ajouterQuartierDansMain(q);
                        System.out.println();
                        System.out.println("Vous avez obtenu : " + q.getNom());
                    } catch(NullPointerException e){
                        System.out.println("Le joueur selectionne n'a ni or ni quartier a donner");
                    }
                }
                else{
                    System.out.println(j.getNom() + " vous donne 1 piece pour la couronne");
                    j.retirerPieces(1);
                    this.joueur.ajouterPieces(1);
                }
            }
        
        } else { System.out.println("Personne ne possede la couronne");}
        

    }

    public void utiliserPouvoirAvatar(){
        // Recherche et retirement de la couronne
        boolean couronneExiste = false;
        Joueur ancienCouronne = this.plateau.getJoueur(0);
        for(int i = 0;i<plateau.getNombreJoueurs();i++){
            if(plateau.getJoueur(i).getPossedeCouronne()){
                ancienCouronne = plateau.getJoueur(i);
                couronneExiste = true;
                plateau.getJoueur(i).setPossedeCouronne(false);
                break;
            }
        }

        Random r = new Random();
        if(couronneExiste){

            Joueur j = this.joueur;
            while(j.equals(this.joueur) || j.equals(ancienCouronne)){
                int aleatoire = r.nextInt(plateau.getNombreJoueurs());
                j = plateau.getJoueur(aleatoire);
            }
            //Don de la couronne au dit joueur
            j.setPossedeCouronne(true);
            System.out.println(j.getNom()+"recois la couronne");
            System.out.println();

            Interaction inter = new Interaction();
            //Si le joueur est humain paiement;
            if(plateau.getJoueur(0).equals(j)){
                Joueur humain = plateau.getJoueur(0);
                if(humain.tresor() == 0 && humain.nbQuartiersDansMain() == 0  ){
                    System.out.println("Vous ne pouvez pas payer pour la couronne");
                }
                if(humain.tresor() !=0 && humain.nbQuartiersDansMain() != 0){
                    System.out.println("Veuillez choisir un moyen de payement :");
                    System.out.println();
                    System.out.println("0 | 1 Piece");
                    System.out.println("1 | 1 Quatier alatoire de votre main");
                    System.out.println();
                    int choix = inter.lireUnEntier(0, 2);
                    if(choix == 0){
                        humain.retirerPieces(1);
                        this.joueur.ajouterPieces(1);
                    }
                    else{
                        try{
                            int aleatoire = r.nextInt(humain.nbQuartiersDansMain());
                            Quartier retire = humain.getMain().get(aleatoire);
                            humain.retirerQuartierDansMain(retire);
                            this.joueur.ajouterQuartierDansMain(retire);
                        } catch (NullPointerException e){ System.out.println("Vous ne possedez pas de cartes");}
                    }
 
                }
                if(humain.tresor() == 0 && humain.nbQuartiersDansMain() != 0){
                    System.out.println("Vous pouvez uniquement payer avec un quartier.");
                    try{
                        int aleatoire = r.nextInt(humain.nbQuartiersDansMain());
                        Quartier retire = humain.getMain().get(aleatoire);
                        humain.retirerQuartierDansMain(retire);
                        this.joueur.ajouterQuartierDansMain(retire);
                    } catch(NullPointerException e){
                        System.out.println("Vous ne possedez pas de quartiers.");
                    }
                }
                if(humain.tresor() != 0 && humain.nbQuartiersDansMain() == 0){
                    System.out.println("Vous pouvez uniquement payer en pièces");
                    humain.retirerPieces(1);
                    this.joueur.ajouterPieces(1);
                    System.out.println("Vous avez perdu une piece");
                }
            } else {
                int tresor = j.tresor();
            int nbMain = j.nbQuartiersDansMain();
            if(tresor != 0 && nbMain != 0){
                boolean paiementPiece = r.nextBoolean();
                if(paiementPiece){
                    j.retirerPieces(1);
                    this.joueur.ajouterPieces(1);
                }
                else{
                    int aleatoire = r.nextInt(nbMain);
                    Quartier q = j.getMain().get(aleatoire);
                    j.retirerQuartierDansMain(q);
                    this.joueur.ajouterQuartierDansMain(q);
                }
            }
            else {
                if(tresor == 0){
                    try{
                        int aleatoire = r.nextInt(nbMain);
                        Quartier q = j.getMain().get(aleatoire);
                        j.retirerQuartierDansMain(q);
                        this.joueur.ajouterQuartierDansMain(q);
                    } catch(NullPointerException e){
                    }
                }
                else{
                    j.retirerPieces(1);
                    this.joueur.ajouterPieces(1);
                }
            }
                
            }
        } else { System.out.println("Personne ne possede la couronne");}

    }
    
}
