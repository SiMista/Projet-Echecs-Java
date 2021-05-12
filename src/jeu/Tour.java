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

	public boolean peutAllerEn(int ligneD, int colonneD, Echiquier e) {
		/*
		if (Math.abs(getColonne() - colonneD) != Math.abs(getLigne() - ligneD))
			return false;
		int dx = getColonne() - colonneD > 0 ? -1 : 1;
		int dy = getLigne() - ligneD > 0 ? -1 : 1;
		for (int i = 1; i < Math.abs(getColonne() - colonneD); ++i)
			if (e.getPlateau()[getColonne() + i * dx][getLigne() + i * dy] == null)
				return false;
		*/
		if ((getLigne() != ligneD && getColonne() != colonneD))
			return false;
		if (getLigne() != ligneD && getColonne() == colonneD) {
			System.out.println("zefouzebofj");

			for (int i = getLigne() + 1; i < Math.abs(ligneD - getLigne()); ++i) {
				System.out.println(Math.abs(ligneD - getLigne()));
				System.out.println(getLigne());
				if (e.getPlateau()[i][colonneD] != null)
					return false;
			}
		}
		if (getLigne() == ligneD && getColonne() != colonneD) {
			System.out.println(Math.abs(colonneD - getColonne()));
			System.out.println(getColonne());
			for (int i = getColonne() + 1; i < Math.abs(colonneD - getColonne()); ++i) {
				System.out.println(Math.abs(colonneD - getColonne()));
				System.out.println(getColonne());
				if (e.getPlateau()[ligneD][i] != null)
					return false;
			}
		}
		if (e.getPlateau()[ligneD][colonneD] != null)
			return peutManger(ligneD, colonneD, e);
		return true;		
	}

	public char getSymbole() {
		return symbole;
	}
}