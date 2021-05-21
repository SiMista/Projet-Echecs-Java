package Pièces;

import java.util.Scanner;

import jeu.Echiquier;
import jeu.IPièce;
import jeu.Partie;

public abstract class Pièce implements IPièce {
	private int ligne, colonne;
	private Couleur couleur;

	public enum Couleur {
		BLANC, NOIR
	};

	public Pièce(int ligne, int colonne, Couleur c, Echiquier e) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.couleur = c;
		e.getListePièces().add(this);
		e.placer(this);
	}

	public void déplacer(Echiquier e, int ligneD, int colonneD) {
		if (!e.estLibre(ligneD, colonneD))
			e.getPlateau()[ligneD][colonneD].estMangé(ligneD, colonneD, e);
		e.getPlateau()[ligneD][colonneD] = this;
		e.getPlateau()[ligne][colonne] = null;
		setLigne(ligneD);
		setColonne(colonneD);
	}

	public abstract boolean peutAllerEn(int ligne, int colonne, Echiquier e);

	public void estMangé(int ligne, int colonne, Echiquier e) {
		System.out.println("La pièce " + getClass().getSimpleName() + " a été mangé");
		e.getListePièces().remove(e.getPlateau()[ligne][colonne]);
	}

	public boolean peutManger(int ligne, int colonne, Echiquier e) {
		if (e.getPlateau()[ligne][colonne].getCouleur() != this.getCouleur()) {
			return true;
		} else
			return false;
	}

	public boolean estEnEchec(Echiquier e) {
		return false;
	}

	public boolean metEnEchec(Echiquier e) {
		for (Pièce p : e.getListePièces()) {
			if (Character.toLowerCase(p.getSymbole()) != 'r'
					&& p.peutAllerEn(getRoiAdverse(e).getLigne(), getRoiAdverse(e).getColonne(), e)
					&& p.getCouleur() != getRoiAdverse(e).getCouleur())
				return true;
		}
		return false;
	}

	public boolean metEnMatOuPat(Echiquier e) {
		for (Pièce piècesAdv : e.getListePièces()) {
			if (piècesAdv.getCouleur() != this.getCouleur()) {
				for (int i = -1; i <= 1; ++i) {
					for (int j = -1; j <= 1; ++j) {
						if (!piècesAdv.peutPasBouger(piècesAdv.getLigne() + i, piècesAdv.getColonne() + j, e)) {
							return false;
						}
					}
				}
			}
		}
		if (getRoiAdverse(e).estEnEchec(e)) {
			for (Pièce piècesAdv : e.getListePièces()) {
				if (piècesAdv.getCouleur() != this.getCouleur() && Character.toLowerCase(piècesAdv.getSymbole()) != 'r') {
					for (int i = -Echiquier.MAX + 1; i <= Echiquier.MAX; ++i) {
						for (int j = -Echiquier.MAX + 1; j <= Echiquier.MAX; ++j) {
							if (!piècesAdv.peutPasBouger(piècesAdv.getLigne() + i,piècesAdv.getColonne() + j, e)) {
								return false;
							}
						}
					}
				}
			}
			System.out.println("Vous avez mis le roi adverse en échec et mat");
			System.out.println("\n       Les " + getRoi(e).getCouleur() + "S ont gagné la partie");
			return true;
		}
		System.out.println("Vous avez mis le roi adverse en Pat");
		System.out.println("\n		Match nul");
		return true;

	}

	public boolean peutPasBouger(int ligneD, int colonneD, Echiquier e) {
		if (!e.outOfBounds(ligneD, colonneD)) {
			if (!peutAllerEn(ligneD, colonneD, e)) {
				return true;
			} else
				return false;
		}
		return true;
	}

	public Pièce getRoi(Echiquier e) {
		for (Pièce roi : e.getListePièces()) {
			if (roi.getCouleur() == getCouleur() && Character.toLowerCase(roi.getSymbole()) == 'r')
				return roi;
		}
		return null;
	}

	public Pièce getRoiAdverse(Echiquier e) {
		for (Pièce roi : e.getListePièces()) {
			if (roi.getCouleur() != getCouleur() && Character.toLowerCase(roi.getSymbole()) == 'r')
				return roi;
		}
		return null;
	}

	public abstract char getSymbole();

	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public Couleur getCouleur() {
		return couleur;
	}

}