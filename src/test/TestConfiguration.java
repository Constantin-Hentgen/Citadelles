package test;

import java.util.ArrayList;
import java.util.Hashtable;

import application.Configuration;
import modele.*;

public class TestConfiguration {
	public static void main(String[] args) {
		TestConfiguration test = new TestConfiguration();
		test.test1();
	}

	public void test1() {
		PlateauDeJeu plateau = new PlateauDeJeu();
		Pioche pioche = plateau.getPioche();

		Quartier q = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1); pioche.ajouter(q);
		Configuration.nouvellePioche(pioche);
		Configuration.affichePioche(pioche);

		Test.test(pioche.nombreElements() == 0, "test du nombre d'éléments dans la pioche");

		Hashtable<Quartier, Integer> configDeBase = new Hashtable<Quartier, Integer>();
		configDeBase.put(q,1);	
	}
}
