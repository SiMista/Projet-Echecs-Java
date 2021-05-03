package jeu;

public class Pièce implements IPièce {
	private int colonne, ligne;

	public Pièce(int colonne, int ligne) {
		this.colonne = colonne;
		this.ligne = ligne;
	}

	public boolean occupe(int colonne, int ligne) {
		return this.colonne == colonne && this.ligne == ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public int getLigne() {
		return ligne;
	}
}
