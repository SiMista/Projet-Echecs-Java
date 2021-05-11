package jeu;

public class Tour extends Pièce {
	private char symbole;

	public Tour(int ligne, int colonne, Pièce.Couleur c, Echiquier e) {
		super(ligne, colonne, c, e);
		if (c == Pièce.Couleur.BLANC)
			symbole = 'T';
		else
			symbole = 't';
	}

	public boolean peutAllerEn(int ligne, int colonne, Echiquier e) {
		System.out.println("je suis une tour");
		if (getLigne() != ligne && getColonne() == colonne) {
			for (int i = getLigne() + 1; i < Math.abs(ligne - getLigne()); ++i) {
				if (e.getPlateau()[i][colonne] != null)
					return false;	
			}
		}
		if (getLigne() == ligne && getColonne() != colonne)
			for (int i = getColonne() + 1; i < Math.abs(colonne - getColonne()); ++i) {
				if (e.getPlateau()[ligne][i] != null)
					return false;
		}
		if (e.getPlateau()[ligne][colonne] != null)
			return peutManger(ligne, colonne, e);
		return true;
	}
	
	public char getSymbole() {
		return symbole;
	}
}