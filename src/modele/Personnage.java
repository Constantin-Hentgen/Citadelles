package modele;

public abstract class Personnage {
	protected String nom, caracteristiques;
	protected int rang;
	protected Joueur joueur;
	protected boolean vole, assassine,ensorcele; //ajout d'ensorcele pour la sorciere
	public PlateauDeJeu plateau;

	public Personnage(String nom, String caracteristiques, int rang) {
		this.nom = nom;
		this.caracteristiques = caracteristiques;
		this.rang = rang;
		this.joueur = null;
		this.vole = false;
		this.assassine = false;
		this.ensorcele = false;
	}

	public void setPlateau(PlateauDeJeu unPlateau) {
		this.plateau = unPlateau;
	}

	public PlateauDeJeu getPlateau() {
		return this.plateau;
	}

	public String getNom() {
		return this.nom;
	}

	public int getRang() {
		return this.rang;
	}

	public String getCaracteristiques() {
		return this.caracteristiques;
	}

	public Joueur getJoueur() {
		return this.joueur;
	}
	
	public boolean getAssassine() {
		return this.assassine;
	}

	public boolean getVole() {
		return this.vole;
	}

	public void setJoueur(Joueur j) {
		this.joueur = j;
		this.joueur.monPersonnage = this;
	}

	public void setVole() {
		this.vole = true;
	}

	public void setAssassine() {
		this.assassine = true;
	}

	public void setEnsorcele(){
		this.ensorcele = true;
	}

	public boolean getEnsorcele(){
		return this.ensorcele;
	}

	public void ajouterPieces() {
		if (this.joueur != null && this.assassine == false) {
			this.joueur.ajouterPieces(2);
		}
	}

	public void ajouterQuartier(Quartier nouveauQuartier) {
		if (this.joueur != null && this.assassine == false) {
			this.joueur.ajouterQuartierDansMain(nouveauQuartier);
		}
	}

	public void construire(Quartier nouveauQuartier) {
		if (this.joueur != null && this.assassine == false) {
			this.joueur.ajouterQuartierDansCite(nouveauQuartier);
		}
	}

	public void percevoirRessourcesSpecifiques() {
		if (this.joueur != null && this.assassine == false) {
			System.out.println("Aucune ressource spécifique.");
		}
	}

	public abstract void utiliserPouvoir();
	public abstract void utiliserPouvoirAvatar();

	public void reinitialiser() {
		this.joueur = null;
		this.vole =  false;
		this.assassine = false;
		this.joueur.monPersonnage = null;
		this.ensorcele = false;
	}
}
