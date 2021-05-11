package jeu;

import jeu.Pièce.Couleur;

public interface IPièce {
	void déplacer(Echiquier e, int ligne, int colonne);

	boolean peutAllerEn(int ligne, int colonne, Echiquier e);

	int getLigne();

	int getColonne();

	char getSymbole();

	Couleur getCouleur();

	/*
	 * jsp sa sert a quoi boolean occupe(int ligne, int colonne);
	 */
}
