package jeu;

import pièces.Pièce.Couleur;

public interface IPièce {
	void déplacer(int ligne, int colonne, Echiquier e);

	boolean peutAllerEn(int ligne, int colonne, Echiquier e);

	int getLigne();

	int getColonne();

	char getSymbole();

	Couleur getCouleur();
}
