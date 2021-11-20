package modele;
import java.util.ArrayList;
import java.util.Random;

public class Joueur {
	private String nom;
	private int tresor;
	private int tresorJeu = 10;
	private Quartier[] cite = new Quartier[8];
	public boolean possedeCouronne;
	private ArrayList<Quartier> main;

	public Joueur(String nom){
		this.nom = nom;
		this.tresor = 0;
		this.possedeCouronne = false;
		this.main = new ArrayList<Quartier>();
		this.cite = new Quartier[8];
	}

	public String getNom(){
		return this.nom;
	}

	public int tresor(){
		return this.tresor;
	}

	public int nbQuartiersDansCite(){
		int counter = 0;
		for (int i = 0; i < 8; i ++){
			if (this.cite[i] == null || this.cite[i].getNom() == ""){
				break;
			} else {
				counter ++;
			}
		}
		return counter;
	}

	public Quartier[] getCite(){
		return this.cite;
	}

	public ArrayList<Quartier> getMain(){
		return this.main;
	}
	
	public int nbQuartiersDansMain(){
		return this.main.size();
	}

	public boolean getPossedeCouronne(){
		return this.possedeCouronne;
	}

	public void setPossedeCouronne(boolean b){
		this.possedeCouronne = b;
	}

	public void ajouterPieces(int montant){
		if (montant > 0 && this.tresorJeu >= montant){
			this.tresor += montant;
			this.tresorJeu -= montant;
		}
	}

	public void retirerPieces(int montant){
		if (montant > 0 && this.tresor >= montant){
			this.tresor -= montant;
		}
	}

	public void ajouterQuartierDansCite(Quartier nomQuartier){
		for (int i = 0; i < 8; i ++){
			if (this.cite[i] == null){
				this.cite[i] = nomQuartier;
				break;
			}
		}
	}

	public boolean quartierPresentDansCite(String nomQuartier){
		boolean test = false;
		for (Quartier element : this.cite){
			try {
				if (element.getNom() == nomQuartier){
					test = true;
					break;
				}
			} 
			catch (NullPointerException npe) {
				test = false;
			}
		}
		return test;
	}

	public Quartier retirerQuartierDansCite(String nomQuartier){
		Quartier element = new Quartier();
		if (quartierPresentDansCite(nomQuartier)){
			try{
				for (int i = 0; i < 8; i ++){
					if (this.cite[i].getNom() == nomQuartier){
						element = this.cite[i];
						this.cite[i] = new Quartier();
						// if (i != 7){ // si on veut supprimer un élément non-dernier
							for (int k = i; k < this.cite.length - 1; k++) {
								this.cite[k] = this.cite[k + 1];
								// TOUS STACK AU DÉBUT et les derniers à NULL
							}
						// } else {
						// 	Quartier[] temp = new Quartier[8];
						// 	for (int l = 0; l < 7; l ++){
						// 		temp[l] = this.cite[l];
						// 	}
						// 	this.cite = temp;
						// 	// mettre le dernier élément à NULL
						// }
					}
				}
			}
			catch (NullPointerException npe) {
			}
			return element;
		} else {
			return null;
		}		
	}

	void ajouterQuartierDansMain(Quartier monQuartier){
		this.main.add(monQuartier);
	}

	public String retirerQuartierDansMain(){
		if (nbQuartiersDansMain() > 0){
			Random generateur = new Random();
			int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
			this.main.remove(numeroHasard);
			return "OK";
		}
		else{
			return null;
		}
	}

	void reinitialiser(){
		this.tresor = 0;

		while (nbQuartiersDansMain() > 0){
			retirerQuartierDansMain();
		}

		for (int i = 0; i < nbQuartiersDansCite(); i ++){
			retirerQuartierDansCite(cite[i].getNom());
		}
	}
}
