package modele;
import java.util.ArrayList;
import java.util.Collections;

public class Pioche {
	private ArrayList<Quartier> liste;

	public Pioche(){
		this.liste = new ArrayList<Quartier>();
	}

	public Quartier piocher() {
		Quartier temp;
		if (liste.size() >= 1){
			temp = liste.get(0);
			liste.remove(0);
			return temp;
		} else {
			return null;
		}
	}

	public void ajouter(Quartier nouveauQuartier) {
		this.liste.add(nouveauQuartier);
	}

	public int nombreElements() {
		return this.liste.size();
	}

	public void melanger() {
		Collections.shuffle(liste);
	}
}
