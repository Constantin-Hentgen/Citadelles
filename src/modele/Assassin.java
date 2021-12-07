package modele;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Assassin extends Personnage {
    
    public Assassin(){
        super("Assassin", Caracteristiques.ASSASSIN, 1);
    }

    Scanner sc = new Scanner(System.in);

    @Override
    public void utiliserPouvoir() {
        boolean continu = true;

        System.out.println("Quel personnage voulez-vous assassiner ?");
        for (int i = 0; i < plateau.getNombreJoueurs() - 1; i++) {
            System.out.println(plateau.getPersonnage(i).getRang()+"   "+plateau.getPersonnage(i).getNom());
        }

        do {
            try {
                int i = sc.nextInt();
                for (int j = 0; j < plateau.getNombreJoueurs(); j++) {
                    if (i != plateau.getPersonnage(j).getRang() || i == this.getRang()) {
                        System.out.println("Vous ne pouvez pas vous assassiner !");
                    }
                    else{
                        plateau.getPersonnage(i).setAssassine();
                        continu = false;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer le rang du personnage");
            }
        } while (continu);
        
    }

}
