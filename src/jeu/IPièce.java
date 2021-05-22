package jeu;

import pi�ces.Pi�ce.Couleur;

public interface IPi�ce {
	void d�placer(Echiquier e, int ligne, int colonne);

	boolean peutAllerEn(int ligne, int colonne, Echiquier e);

	int getLigne();

	int getColonne();

	char getSymbole();

	Couleur getCouleur();
}
