package jeu;

public class Roi extends Pi�ce {
	private char symbole;

	public Roi(int ligne, int colonne, Pi�ce.Couleur c, Echiquier e) {
		super(ligne, colonne, c, e);
		if (c == Pi�ce.Couleur.BLANC)
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
