package test;

import application.*;

public class TestJeu {

	public static void main(String[] args) {
		TestJeu test = new TestJeu();
		test.test();
	}

	public void test() {
		Jeu jeu = new Jeu();
		jeu.jouer();
	}
}
