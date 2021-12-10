package modele;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Voleur extends Personnage {    
	Scanner sc = new Scanner(System.in);

    public Voleur(){
        super("Voleur", Caracteristiques.VOLEUR, 2);
    };

	@Override
    public void utiliserPouvoir() {
		Boolean testDeVerite = false;
		
		for (int i = 0; i < plateau.getNombrePersonnages(); i++) {
			System.out.println(plateau.getPersonnage(i).getRang()+"   "+plateau.getPersonnage(i).getNom());
			if ( plateau.getPersonnage(i).getNom().equals("Voleur") ) {
				testDeVerite = true;
			}
		}
		
		if ( testDeVerite.equals(true)){

			boolean continu = true;
	
			System.out.println("Quel personnage voulez-vous voler ?");
			for (int i = 0; i < plateau.getNombrePersonnages(); i++) {
				System.out.println(plateau.getPersonnage(i).getRang()+"   "+plateau.getPersonnage(i).getNom());
			}
	
			do {
				for (int j = 0; j < plateau.getNombrePersonnages(); j++) {
					try {
						int i = sc.nextInt();
						if (i == this.getRang()) {
							System.out.println("Vous ne pouvez pas vous voler !");
						}
						else if(i == plateau.getPersonnage(j).getRang()){
							plateau.getPersonnage(j).setVole();
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
}