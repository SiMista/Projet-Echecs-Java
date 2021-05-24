package jeu;

import pièces.Pièce.Couleur;

public interface IPièce {
	void déplacer(int ligne, int colonne, Echiquier e);

	boolean peutAllerEn(int ligne, int colonne, Echiquier e);
	
	void estMangé(int ligneD, int colonneD, Echiquier e);
	
	boolean estEnEchec(Echiquier e);

	boolean metEnEchec(Echiquier e);

	boolean metEnMatOuPat(Echiquier e);

	boolean peutPasBouger(int i, int j, Echiquier e);
	
	char getSymbole();
	
	Couleur getCouleur();
	
	int getLigne();

	int getColonne();
}
