package modele;

import java.util.ArrayList;
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
        ArrayList<Quartier> carteRemovedJoueur = new ArrayList<>();
        ArrayList<Quartier> carteRemovedMagicienne = new ArrayList<>();

        Joueur selected = null;
        boolean continu = true;

        System.out.println("Voulez-vous échanger des cartes ?");
        String a = sc.nextLine();

        if (a.equals("o") || a.equals("oui")) {

            for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
                
                System.out.println(i+" : "+plateau.getJoueur(i).getNom()+" --  Nombre de cartes : "+plateau.getJoueur(i).nbQuartiersDansMain());
            }

            System.out.println("Avec quel joueur voulez-vous échanger vos cartes ?");
            int b = sc.nextInt();
            do {
                try {
                    for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
                        listeJoueurs.add(plateau.getJoueur(i));
                    }
  
                    selected = listeJoueurs.get(b);
                    continu = false;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Merci d'indiquer un joueur parmi ceux affichés");
                    sc.next();
                }                
            } while (continu);

            System.out.println("Combien de cartes souhaitez vous échanger ?");
            int c = sc.nextInt();
            try {

                for (int i = 0; i < c; i++) {
                    carteRemovedJoueur.add(selected.retirerQuartierDansMain());
                    carteRemovedMagicienne.add(joueur.retirerQuartierDansMain());
                }
                for (int i = 0; i < c; i++) {
                    selected.ajouterQuartierDansMain(carteRemovedMagicienne.get(i));
                    joueur.ajouterQuartierDansMain(carteRemovedJoueur.get(i));
                }
                
            } catch (IndexOutOfBoundsException e) {
                System.out.println("L'un des joueurs n'a pas assez de cartes dans sa main");
                sc.next();
            }
        }
    }    
}
