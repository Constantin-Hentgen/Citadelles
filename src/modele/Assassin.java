package modele;

public class Assassin extends Personnage {

	public Assassin(String nom, String caracteristiques, int rang){
		super(nom, caracteristiques, rang);
	}

	@Override
	void utiliserPouvoir() {
		System.out.println(plateau.getNombrePersonnages());
		// System.out.println(plateau.getPersonnage(1));
		// for (int i = 0; i < plateau.getNombrePersonnages(); i++){
		// 	System.out.println(plateau.getPersonnage(i));
		// }
	}

	public static void main(String[] args) {
		Assassin vilain = new Assassin("valin", "jspCaracteristique", 1);
		vilain.utiliserPouvoir();
	}
}
