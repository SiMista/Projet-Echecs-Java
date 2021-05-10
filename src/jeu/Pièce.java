package jeu;

public class Pièce implements IPièce {
	private int ligne, colonne;
	private Couleur couleur;

	public enum Couleur {
		BLANC, NOIR
	};

	public Pièce(int ligne, int colonne, Couleur c, Echiquier e) {
	    this.ligne = ligne;
	    this.colonne = colonne;
	    this.couleur = c;
	    e.placer(this);
	  }

	
	
	public void déplacer(Echiquier e, int ligne, int colonne) {
		e.getPlateau()[ligne][colonne] = this;
		e.getPlateau()[this.ligne][this.colonne] = null;
		this.ligne = ligne;
		this.colonne = colonne;
		System.out.println(ligne);
		System.out.println(colonne);

	}

	public boolean occupe( int ligne, int colonne) {
		return (this.ligne == ligne && this.colonne == colonne);
	}

	public int getColonne() {
		return colonne;
	}

	public int getLigne() {
		return ligne;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public boolean peutAllerEn(int ligne, int colonne, Echiquier e) {
		return peutAllerEn(ligne, colonne, e);
	}

	public char getSymbole() {
		return getSymbole();
	}
}
