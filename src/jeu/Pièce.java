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
		couleur = c;
		placer(e);
	}
	
	public void placer(Echiquier e) {
		e.getPlateau()[colonne - 1][ligne - 1] = this;
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
	
	public Couleur getCouleur() {
		return couleur;
	}

	public String getSymbole() {
		return null;
	}
}
