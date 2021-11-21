package modele;

public class PlateauDeJeu {
	private Personnage[] listePersonnages;
	public Joueur[] listeJoueurs;
	private Pioche pioche;
	private int nombrePersonnages, nombreJoueurs;

	public PlateauDeJeu(){
		this.listePersonnages = new Personnage[9];
		this.listeJoueurs = new Joueur[9];
		this.pioche = new Pioche(); 
	}

	public int getNombreJoueurs(){
		this.nombreJoueurs = 0;

		for (Joueur joueur : listeJoueurs){
			if (joueur != null){
				this.nombreJoueurs ++;
			}
		}
		return this.nombreJoueurs;
	}

	public int getNombrePersonnages(){
		this.nombrePersonnages = 0;

		for (Personnage perso : listePersonnages){
			if (perso != null){
				this.nombrePersonnages ++;
			}
		}
		return this.nombrePersonnages;
	}

	public Pioche getPioche(){
		return this.pioche;
	}

	public Personnage getPersonnage(int rangDansLaListe){
		if (rangDansLaListe >= 0 && rangDansLaListe <= this.listePersonnages.length){
			return this.listePersonnages[rangDansLaListe];
		} else {
			return null;
		}
	}

	public Joueur getJoueur(int rangDansLaListe){
		if ( rangDansLaListe >= 0 && rangDansLaListe <= this.listeJoueurs.length){
			return this.listeJoueurs[rangDansLaListe];
		} else {
			return null;
		}
	}

	public void ajouterPersonnage(Personnage persoAjout){
		if (persoAjout != null && this.getNombrePersonnages() != this.listePersonnages.length){
			this.listePersonnages[this.getNombrePersonnages()] = persoAjout;
			persoAjout.setPlateau(this);
			this.nombrePersonnages ++;
		}
	}	
	
	public void ajouterJoueur(Joueur joueurAjout){
		for (int i = 0; i < this.listeJoueurs.length; i++){
			if (this.listeJoueurs[i] == null){
				this.listeJoueurs[i] = joueurAjout;
				break;
			}
		}
	}
}
