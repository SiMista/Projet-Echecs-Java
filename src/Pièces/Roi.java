package pièces;

import jeu.Echiquier;
import pièces.Pièce.Couleur;

public class Roi extends Pièce {
	private char symbole;

	public Roi(int ligne, int colonne, Pièce.Couleur c, Echiquier e) {
		super(ligne, colonne, c, e);
		if (c == Pièce.Couleur.BLANC)
			symbole = 'R';
		else
			symbole = 'r';
	}

	public boolean peutAllerEn(int ligneD, int colonneD, Echiquier e) {
		if (Math.abs(getLigne() - ligneD) > 1 || Math.abs(getColonne() - colonneD) > 1
				|| seraEnEchec(ligneD, colonneD, e) || roiACoté(ligneD, colonneD, e)) {
			return false;
		}
		if (!e.estLibre(ligneD, colonneD))
			return peutManger(ligneD, colonneD, e);
		return true;
	}

	public boolean roiACoté(int ligneD, int colonneD, Echiquier e) {
		for (int i = -1; i <= 1; ++i) {
			for (int j = -1; j <= 1; ++j) {
				if (!e.outOfBounds(ligneD + i, colonneD + j)
						&& e.getPlateau()[ligneD + i][colonneD + j] == getRoiAdverse(e)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean seraEnEchec(int ligneD, int colonneD, Echiquier e) {
		Echiquier eTmp = new Echiquier();
		eTmp.getPlateau()[ligneD][colonneD] = e.getPlateau()[ligneD][colonneD];
		e.getPlateau()[ligneD][colonneD] = this;
		e.getPlateau()[getLigne()][getColonne()] = null;
		for (Pièce p : e.getListePièces()) {
			if (Character.toLowerCase(p.getSymbole()) != 'r' && this.getCouleur() != p.getCouleur()
					&& p.peutAllerEn(ligneD, colonneD, e)) {
				e.getPlateau()[ligneD][colonneD] = null;
				e.getPlateau()[getLigne()][getColonne()] = this;
				e.getPlateau()[ligneD][colonneD] = eTmp.getPlateau()[ligneD][colonneD];
				return true;
			}
		}
		e.getPlateau()[ligneD][colonneD] = null;
		e.getPlateau()[getLigne()][getColonne()] = this;
		e.getPlateau()[ligneD][colonneD] = eTmp.getPlateau()[ligneD][colonneD];
		return false;
	}

	@Override
	public boolean estEnEchec(Echiquier e) {
		for (Pièce p : e.getListePièces()) {
			if (this.getCouleur() != p.getCouleur() && p.peutAllerEn(getLigne(), getColonne(), e)) {
				return true;
			}
		}
		return false;
	}

	public char getSymbole() {
		return symbole;
	}

}
