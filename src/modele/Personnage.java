package modele;

public abstract class Personnage {
	protected String nom, caracteristiques;
	protected int rang;
	protected Joueur joueur;
	protected boolean vole, assassine;
	public PlateauDeJeu plateau;

	public Personnage(String nom, String caracteristiques, int rang){
		this.nom = nom;
		this.caracteristiques = caracteristiques;
		this.rang = rang;
		this.joueur = null;
		this.vole = false;
		this.assassine = false;
	}

	public void setPlateau(PlateauDeJeu unPlateau){
		this.plateau = unPlateau;
	}

	public PlateauDeJeu getPlateau(){
		this.plateau = new PlateauDeJeu();
		for (int i = 0; i < 8; i++){
			this.plateau.ajouterPersonnage(PlateauDeJeu.getPersonnage(i));
			this.plateau.ajouterJoueur(PlateauDeJeu.getJoueur(i));
		}
		return this.plateau;
	}

	public String getNom(){
		return this.nom;
	}

	public int getRang(){
		return this.rang;
	}

	public String getCaracteristiques(){
		return this.caracteristiques;
	}

	public Joueur getJoueur(){
		return this.joueur;
	}
	
	public boolean getAssassine(){
		return this.assassine;
	}

	public boolean getVole(){
		return this.vole;
	}

	public void setJoueur(Joueur j){
		this.joueur = j;
	}

	public void setVole(){
		this.vole = true;
	}

	public void setAssassine(){
		this.assassine = true;
	}

	public void ajouterPieces(){
		if (this.joueur != null && this.assassine == false){
			this.joueur.ajouterPieces(2);
		}
	}

	public void ajouterQuartier(Quartier nouveauQuartier){
		if (this.joueur != null && this.assassine == false){
			this.joueur.ajouterQuartierDansMain(nouveauQuartier);
		}
	}

	public void construire(Quartier nouveauQuartier){
		if (this.joueur != null && this.assassine == false){
			this.joueur.ajouterQuartierDansCite(nouveauQuartier);
		}
	}

	void percevoirRessourcesSpecifiques(){
		if (this.joueur != null && this.assassine == false){
			System.out.println("Aucune ressource spécifique.");
		}
	}

	abstract void utiliserPouvoir();

	public void reinitialiser(){
		this.joueur = null;
		this.vole =  false;
		this.assassine = false;
	}
}
