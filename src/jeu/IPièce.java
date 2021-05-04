package jeu;

public interface IPièce {
	boolean occupe(int colonne, int ligne);
	int getLigne();
	int getColonne();
}
