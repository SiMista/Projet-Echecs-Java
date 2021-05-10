package jeu;

public class Tour extends Pi�ce {
	private char symbole;

	public Tour(int ligne, int colonne, Pi�ce.Couleur c, Echiquier e) {
		super(ligne, colonne, c, e);
		if (c == Pi�ce.Couleur.BLANC)
			symbole = 'T';
		else symbole = 't';
	}

	public boolean peutAllerEn(int ligne, int colonne, Echiquier e) {
		if ((getLigne() != ligne && getColonne() == colonne) || (getLigne() == ligne && getColonne() != colonne))
			return true;
		return false;
	}

	public char getSymbole() {
		return symbole;
	}
}