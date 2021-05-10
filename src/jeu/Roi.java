package jeu;

public class Roi extends Pièce {
	private char symbole;

	public Roi(int ligne, int colonne, Pièce.Couleur c, Echiquier e) {
		super(ligne, colonne, c, e);
		if (c == Pièce.Couleur.BLANC)
			symbole = 'R';
		else symbole = 'r';
	}

	public boolean peutAllerEn(int ligne, int colonne, Echiquier e) {
		if (Math.abs(getLigne() - ligne) > 1 || Math.abs(getColonne() - colonne) > 1)
			return false;
		return true;
	}

	public char getSymbole() {
		return symbole;
	}
}
