package jeu;

public class Pièce implements IPièce {
	private int colonne, ligne;
	private Couleur couleur;

	public enum Couleur {
		BLANC, NOIR
	};

	public Pièce(int ligne, int colonne, Couleur c, Echiquier e) {
	    this.ligne = ligne;
	    this.colonne = colonne;
	    this.couleur = c;
	    placer(e);
	  }

	public void placer(Echiquier e) {
		e.getPlateau()[this.colonne - 1][this.ligne - 1] = this;
	}

	public boolean occupe(int colonne, int ligne) {
		return (this.colonne == colonne && this.ligne == ligne);
	}

	public int getColonne() {
		return this.colonne;
	}

	public int getLigne() {
		return this.ligne;
	}

	public Couleur getCouleur() {
		return this.couleur;
	}

	public boolean peutAllerEn(int colonne, int ligne, Echiquier e) {
		return peutAllerEn(colonne, ligne, e);
	}

	public char getSymbole() {
		return getSymbole();
	}
}
