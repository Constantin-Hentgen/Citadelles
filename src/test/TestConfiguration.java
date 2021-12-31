package test;

import application.Configuration;
import modele.*;

public class TestConfiguration {
	public static void main(String[] args) {
		TestConfiguration test = new TestConfiguration();
		test.test();
	}

	public void test() {
		PlateauDeJeu plateau = new PlateauDeJeu();
		plateau.setPioche(Configuration.nouvellePioche());
		Pioche pioche = plateau.getPioche();

		Test.test(pioche.nombreElements() == 54, "test du nombre d'éléments dans la pioche après ajout des quartiers de bases");

		plateau = Configuration.configurationDeBase();

		Test.test(pioche.nombreElements() == 68, "test du nombre d'éléments dans la pioche après ajout des merveilles");

		Test.test(plateau.getNombreJoueurs() == 4, "test du nombre de joueurs dans la partie");

		Test.test(plateau.getNombrePersonnages() == 8, "test du nombre de personnages dans la partie");
	}
}
