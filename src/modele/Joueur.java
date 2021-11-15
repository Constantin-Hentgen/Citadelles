package modele;
import java.util.ArrayList;
import java.util.Random;

public class Joueur {
	private String nom;
	private int nbQuartiers, nbPieces;
	private int tresor = 10;
	private Quartier[] cite = new Quartier[8];
	private boolean possedeCouronne;
	private ArrayList<Quartier> main;

	public Joueur(String nom){
		this.nom = nom;
		this.nbPieces = 0;
		this.nbQuartiers = 0;
		this.possedeCouronne = false;
		this.main = new ArrayList<Quartier>();
		this.cite = new Quartier[8];
	}

	public String getNom(){
		return this.nom;
	}

	public int nbPieces(){
		return this.nbPieces;
	}

	public int nbQuartiersDansCite(){
		Quartier ininteressant = new Quartier();
		int counter = 0;
		for (int i = 0; i < 8; i ++){
			if (this.cite[i] != ininteressant && this.cite[i] != null){
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
		if (montant > 0 && this.tresor >= montant){
			this.nbPieces += montant;
			this.tresor -= montant;
		}
	}

	public void retirerPieces(int montant){
		if (montant > 0 && this.nbPieces >= montant){
			this.nbPieces -= montant;
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
			if (this.cite[i].getNom() == nomQuartier){
				test = true;
			}
		}
		return test;
	}

	public Quartier retirerQuartierDansCite(String nomQuartier){

		Quartier element = new Quartier();

		if (quartierPresentDansCite(nomQuartier)){
			for (int i = 0; i < 8; i ++){
				if (this.cite[i].getNom() == nomQuartier){
					element = this.cite[i];
					this.cite[i] = new Quartier();
				}
			}
			return element;
		}
		else{
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
		this.nbPieces = 0;

		while (nbQuartiersDansMain() > 0){
			retirerQuartierDansMain();
		}

		for (int i = 0; i < nbQuartiersDansCite(); i ++){
			retirerQuartierDansCite(cite[i].getNom());
		}
	}
}
