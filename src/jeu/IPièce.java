package jeu;

import jeu.Pi�ce.Couleur;

public interface IPi�ce {
	void d�placer(Echiquier e, int ligne, int colonne);

	boolean peutAllerEn(int ligne, int colonne, Echiquier e);

	int getLigne();

	int getColonne();

	char getSymbole();

	Couleur getCouleur();

	/*
	 * jsp sa sert a quoi boolean occupe(int ligne, int colonne);
	 */
}
