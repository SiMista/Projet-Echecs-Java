package jeu;

public interface IPièce {
	boolean occupe(int ligne, int colonne);
	int getLigne();
	int getColonne();
}
