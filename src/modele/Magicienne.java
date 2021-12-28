package modele;

import java.util.ArrayList;
import controleur.Interaction;

public class Magicienne extends Personnage {

    public Magicienne() {
        super("Magicienne", Caracteristiques.MAGICIENNE, 3);
    }

    public void utiliserPouvoir() {
        Joueur joueur = this.getJoueur();
        ArrayList<Quartier> copieMain = new ArrayList<>(joueur.getMain());
        ArrayList<Joueur> listeJoueurs = new ArrayList<>();
        Quartier q;

        Joueur selected = null;
        boolean continu = true;

        System.out.println("\nVoulez-vous échanger des cartes ?\n");
        Boolean a = Interaction.lireOuiOuNon();

        if (a) {
			System.out.println();

            for (int i = 0; i < plateau.getNombreJoueurs(); i++) {                
                System.out.println(i+" : "+plateau.getJoueur(i).getNom()+" --  Nombre de cartes : "+plateau.getJoueur(i).nbQuartiersDansMain());
            }

            System.out.println("\nAvec quel joueur voulez-vous échanger vos cartes ?");
            do {
                int b = Interaction.lireUnEntier(0,plateau.getNombreJoueurs());
                if (plateau.getJoueur(b) == joueur) {
                    System.out.println("\nVous ne pouvez pas échanger des cartes avec vous-mêmes !");
                } else {
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
                    
					System.out.println("Cartes échangées avec le joueur " + plateau.getJoueur(b).getNom() + ".");					
					continu = false;
                }             
            } while (continu);
        } else {
            System.out.println("Combien de cartes de votre main voulez-vous échanger avec la pioche ?");
            System.out.println("Taille de votre main : "+joueur.getMain().size());

            do {
                int c = Interaction.lireUnEntier();

                if (c <= joueur.getMain().size()) {
                    for (int i = 0; i < c; i++) {
                        joueur.retirerQuartierDansMain();
                        joueur.ajouterQuartierDansMain(plateau.getPioche().piocher());
                        q = new Quartier();
                        plateau.getPioche().ajouter(q);
                    }
                    continu = false;
					System.out.println("\t"+c+" cartes échangées avec la pioche.");
                }
            } while (continu);
        }
    }

	public void utiliserPouvoirAvatar() {        
        Joueur joueur = this.getJoueur();
        ArrayList<Quartier> copieMain = new ArrayList<>(joueur.getMain());
        ArrayList<Joueur> listeJoueurs = new ArrayList<>();
        Quartier q;

        Joueur selected = null;
        boolean continu = true;

        Boolean a = Interaction.randomizerBoolean();

        if (a) {
            do {
                int b = Interaction.randomizer(plateau.getNombreJoueurs());
                if (plateau.getJoueur(b) != joueur) {
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
					System.out.println("Cartes échangées avec le joueur " + plateau.getJoueur(b).getNom() + ".");
                }             
            } while (continu);
        } else {
            do {
                int c = Interaction.randomizer();

                if (c <= joueur.getMain().size()) {
                    for (int i = 0; i < c; i++) {
                        joueur.retirerQuartierDansMain();
                        joueur.ajouterQuartierDansMain(plateau.getPioche().piocher());
                        q = new Quartier();
                        plateau.getPioche().ajouter(q);
                    }
                    continu = false;
					System.out.println("\t"+c+" cartes échangées avec la pioche.");
                }
            } while (continu);
        }
    }    
}