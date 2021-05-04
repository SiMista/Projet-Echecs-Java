package jeu;

import jeu.Pièce.Couleur;

public class Application {

	public static void main(String[] args) {
		Echiquier e = new Echiquier();
		Roi r = new Roi(2, 3, Couleur.BLANC, e);
		System.out.println(e.toString());
	}
}
