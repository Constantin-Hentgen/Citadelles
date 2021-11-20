package modele;

public class PlateauDeJeu {
	private Personnage[] listePersonnages;
	private Joueur[] listeJoueurs;
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

	public Personnage getPersonnage(int rang){
		if (rang >= 0 && rang <= this.listePersonnages.length){
			return this.listePersonnages[rang];
		} else {
			return null;
		}
	}

	public Joueur getJoueur(int rang){
		if ( rang >= 0 && rang <= this.listeJoueurs.length){
			return this.listeJoueurs[rang];
		} else {
			return null;
		}
	}

	void ajouterPersonnage(Personnage persoAjout){
		if (persoAjout != null){
			for (Personnage perso : this.listePersonnages){
				if (perso == null){
					// Personnage.plateau.setPlateau(new PlateauDeJeu());
					perso = persoAjout;
					break;
				}
			}
		}
	}	
	
	void ajouterJoueur(Joueur joueurAjout){
		if (joueurAjout != null){
			for (Joueur perso : this.listeJoueurs){
				if (perso == null){
					perso = joueurAjout;
					break;
				}
			}
		}
	}
}
