package modele;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Magicienne extends Personnage {

    public Magicienne(){
        super("Magicienne", Caracteristiques.MAGICIENNE, 3);
    }

    Scanner sc = new Scanner(System.in);

    @Override
    public void utiliserPouvoir() {
        
        Joueur joueur = this.getJoueur();
        ArrayList<Quartier> copieMain = new ArrayList<>(joueur.getMain());
        ArrayList<Joueur> listeJoueurs = new ArrayList<>();
        Quartier q;

        Joueur selected = null;
        boolean continu = true;

        System.out.println("Voulez-vous échanger des cartes ?");
        String a = sc.nextLine();

        if (a.equals("o") || a.equals("oui")) {

            for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
                
                System.out.println(i+" : "+plateau.getJoueur(i).getNom()+" --  Nombre de cartes : "+plateau.getJoueur(i).nbQuartiersDansMain());
            }

            System.out.println("Avec quel joueur voulez-vous échanger vos cartes ?");
            do {
                int b = sc.nextInt();
                if (plateau.getJoueur(b) == joueur) {
                    System.out.println("Vous ne pouvez pas échanger des cartes avec vous-mêmes !");
                }else{
                    try {
                        for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
                            listeJoueurs.add(plateau.getJoueur(i));
                        }    
                        selected = listeJoueurs.get(b);

                        ArrayList<Quartier> copieMainSelected = new ArrayList<>(selected.getMain());

                        for (int i = 0; i <= selected.getMain().size() + 1; i++) {
                            selected.retirerQuartierDansMain();
                        }
                        for (int i = 0; i <= joueur.getMain().size() + 1; i++) {
                            joueur.retirerQuartierDansMain();
                        }
                        for (int i = 0; i < copieMainSelected.size(); i++) {
                            joueur.ajouterQuartierDansMain(copieMainSelected.get(i));
                        }
                        for (int i = 0; i < copieMain.size(); i++) {
                            selected.ajouterQuartierDansMain(copieMain.get(i));
                        }

                        continu = false;
                     } catch (IndexOutOfBoundsException e) {
                         System.out.println("Merci d'indiquer un joueur parmi ceux affichés");
                     }   
                }             
            } while (continu);

        }else if(a.equals("n") || a.equals("non")){
            System.out.println("Combien de cartes de votre main voulez-vous échanger avec la pioche ?");
            System.out.println("Taille de votre main : "+joueur.getMain().size());

            do {
                int c = sc.nextInt();
                if (c <= joueur.getMain().size()) {
                    for (int i = 0; i < c; i++) {
                        joueur.retirerQuartierDansMain();
                        joueur.ajouterQuartierDansMain(plateau.getPioche().piocher());
                        q = new Quartier();
                        plateau.getPioche().ajouter(q);
                    }
                    continu = false;
                }else{

                }      
            } while (continu);
        }
    }    
}
