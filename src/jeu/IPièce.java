package jeu;

import pièces.Pièce.Couleur;

public interface IPièce {
	void déplacer(Echiquier e, int ligne, int colonne);

	boolean peutAllerEn(int ligne, int colonne, Echiquier e);

	int getLigne();

	int getColonne();

	char getSymbole();

	Couleur getCouleur();
}
