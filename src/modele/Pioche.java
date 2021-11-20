package modele;
import java.util.ArrayList;
import java.util.Collections;

public class Pioche {
	private ArrayList<Quartier> liste;

	public Pioche(){
		this.liste = new ArrayList<Quartier>();
	}

	public Quartier piocher(){
		if (liste.size() >= 1){
			return this.liste.get(0);
		} else {
			return null;
		}
	}

	public void ajouter(Quartier nouveauQuartier){
		this.liste.add(nouveauQuartier);
	}

	public int nombreElements(){
		return this.liste.size();
	}

	void melanger(){
		Collections.shuffle(liste);
	}
}
