package modele;

public class Quartier {
	public static final String[] TYPE_QUARTIERS = {"RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};
	private String nom, type, caracteristique;
	private int coutConstruction;

	public Quartier(){
		nom = "";
		type = "";
		coutConstruction = 0;
		caracteristique = "";
	}

	public Quartier(String nom, String type, int coutConstruction){
		this.nom = nom;
		this.type = type;
		this.coutConstruction = coutConstruction;
		caracteristique = "";
	}

	public Quartier(String nom, String type, int coutConstruction, String caracteristique){
		this.nom = nom;
		this.type = type;
		this.coutConstruction = coutConstruction;
		this.caracteristique = caracteristique;
	}

	public String getNom(){
		return this.nom;
	}

	public void setNom(String nom){
		this.nom = nom;
	}

	public String getType(){
		return this.type;
	}

	public void setType(String typeInput){
		this.type = "";

		for (String element : TYPE_QUARTIERS){
			if (element == typeInput){
				this.type = typeInput;
				break;
			}
		}
	}

	public int getCout(){
		return this.coutConstruction;
	}

	public void setCout(int coutInput){
		this.coutConstruction = 0;

		if (coutInput >= 1 && coutInput <= 6){
			this.coutConstruction = coutInput;
		}
	}

	public String getCaracteristiques(){
		return this.caracteristique;
	}

	public void setCaracteristiques(String caracteristiqueInput){
		this.caracteristique = caracteristiqueInput;
	}
	public static void main(String[] args) {
		System.out.println(TYPE_QUARTIERS[0]);
	}
}
