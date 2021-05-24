package jeu;

import pi�ces.Pi�ce.Couleur;

public interface IPi�ce {
	void d�placer(int ligne, int colonne, Echiquier e);

	boolean peutAllerEn(int ligne, int colonne, Echiquier e);

	int getLigne();

	int getColonne();

	char getSymbole();

	Couleur getCouleur();
}
