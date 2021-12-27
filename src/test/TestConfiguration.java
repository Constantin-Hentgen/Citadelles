package test;

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

		pioche = Configuration.nouvellePioche(pioche);
		
		Test.test(pioche.nombreElements() == 54, "test du nombre d'éléments dans la pioche");
	}
}
