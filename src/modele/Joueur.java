package modele;
import java.util.ArrayList;
import java.util.Random;

public class Joueur {
	private String nom;
	private int tresor;
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
		if (montant > 0){
			this.tresor += montant;
		}
	}

	public void retirerPieces(int montant){
		if (montant > 0 && this.tresor >= montant){
			this.tresor -= montant;
		}
	}

	public int nbPieces(){
		return this.tresor;
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
			if (element != null && element.getNom().equals(nomQuartier)){
				return true;
			}
		}
		return test;
	}

	public Quartier retirerQuartierDansCite(String nomQuartier){
		Quartier element = new Quartier();

		if (quartierPresentDansCite(nomQuartier)) {
			for (int i = 0; i < nbQuartiersDansCite(); i ++){
				if (this.cite[i].getNom().equals(nomQuartier)){
					element = this.cite[i];   // je sauve le quartier avant de remettre en forme l'array
					this.cite[i] = this.cite[nbQuartiersDansCite()-1];
					this.cite[nbQuartiersDansCite()-1] = null;
				}
			}
			return element;
		}
		else {
			return null;
		}
	}

	public void ajouterQuartierDansMain(Quartier monQuartier){
		this.main.add(monQuartier);
	}

	public Quartier retirerQuartierDansMain(){
		Quartier temp;
		if (nbQuartiersDansMain() > 0){
			Random generateur = new Random();
			int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
			temp = this.main.get(numeroHasard);
			this.main.remove(numeroHasard);
			return temp;
		}
		else{
			return null;
		}
	}

	public void reinitialiser(){
		this.tresor = 0;

		while (nbQuartiersDansMain() > 0){
			retirerQuartierDansMain();
		}

		for (int i = 0; i < nbQuartiersDansCite(); i ++){
			retirerQuartierDansCite(cite[i].getNom());
		}
	}
}
