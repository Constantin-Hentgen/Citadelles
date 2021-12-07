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
        for (int i = 0; i < plateau.getNombrePersonnages(); i++) {
            System.out.println(plateau.getPersonnage(i).getRang()+"   "+plateau.getPersonnage(i).getNom());
        }

        do {
            for (int j = 0; j < plateau.getNombrePersonnages(); j++) {
                try {
                    int i = sc.nextInt();
                    if (i == this.getRang()) {
                        System.out.println("Vous ne pouvez pas vous assassiner !");
                    }
                    else if(i == plateau.getPersonnage(j).getRang()){
                        plateau.getPersonnage(j).setAssassine();
                        continu = false;
                        break;
                    }
                    else{
                        System.out.println("Veuillez entrer le rang d'un personnage");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Veuillez entrer le rang du personnage");
                    sc.next();
                }            
            }
        } while (continu);
    }
}
