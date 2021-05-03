package jeu;

public class Pi�ce implements IPi�ce {
	private int colonne, ligne;

	public Pi�ce(int colonne, int ligne) {
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
