package Pièces;

import jeu.Echiquier;

public class Tour extends Pièce {
	private char symbole;

	public Tour(int ligne, int colonne, Pièce.Couleur c, Echiquier e) {
		super(ligne, colonne, c, e);
		if (c == Pièce.Couleur.BLANC)
			symbole = 'T';
		else
			symbole = 't';
	}

	@Override
	public boolean peutAllerEn(int ligneD, int colonneD, Echiquier e) {

		int dx = getColonne() - colonneD > 0 ? -1 : 1;
		int dy = getLigne() - ligneD > 0 ? -1 : 1;

		if ((getLigne() != ligneD && getColonne() != colonneD) || (getLigne() == ligneD && getColonne() == colonneD))
			return false;
		if (getLigne() != ligneD && getColonne() == colonneD) {
			for (int i = 1; i < Math.abs(getLigne() - ligneD); ++i) {
				if (!e.estLibre(getLigne() + i * dy, getColonne()))
					return false;
			}
		}
		if (getLigne() == ligneD && getColonne() != colonneD) {
			for (int i = 1; i < Math.abs(getColonne() - colonneD); ++i) {
				if (!e.estLibre(getLigne(), getColonne()  + i * dx))
					return false;
			}
		}
		Echiquier eTmp = new Echiquier();
		eTmp.getPlateau()[ligneD][colonneD] = e.getPlateau()[ligneD][colonneD];
		e.getPlateau()[ligneD][colonneD] = this;
		e.getPlateau()[getLigne()][getColonne()] = null;

		if (e.getPlateau()[getRoi(e).getLigne()][getRoi(e).getColonne()].estEnEchec(e)) {
			e.getPlateau()[getLigne()][getColonne()] = this;
			e.getPlateau()[ligneD][colonneD] = null;
			e.getPlateau()[ligneD][colonneD] = eTmp.getPlateau()[ligneD][colonneD];
			return false;
		}
		e.getPlateau()[getLigne()][getColonne()] = this;
		e.getPlateau()[ligneD][colonneD] = null;
		e.getPlateau()[ligneD][colonneD] = eTmp.getPlateau()[ligneD][colonneD];
		if (!e.estLibre(ligneD, colonneD))
			return peutManger(ligneD, colonneD, e);
		return true;
	}

	public char getSymbole() {
		return symbole;
	}

}