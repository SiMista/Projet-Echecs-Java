package jeu;

public class Tour extends Pièce {
	private char symbole;

	public Tour(int ligne, int colonne, Pièce.Couleur c, Echiquier e) {
		super(ligne, colonne, c, e);
		if (c == Pièce.Couleur.BLANC)
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