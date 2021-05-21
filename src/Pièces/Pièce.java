package Pi�ces;

import java.util.Scanner;

import jeu.Echiquier;
import jeu.IPi�ce;
import jeu.Partie;

public abstract class Pi�ce implements IPi�ce {
	private int ligne, colonne;
	private Couleur couleur;

	public enum Couleur {
		BLANC, NOIR
	};

	public Pi�ce(int ligne, int colonne, Couleur c, Echiquier e) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.couleur = c;
		e.getListePi�ces().add(this);
		e.placer(this);
	}

	public void d�placer(Echiquier e, int ligneD, int colonneD) {
		if (!e.estLibre(ligneD, colonneD))
			e.getPlateau()[ligneD][colonneD].estMang�(ligneD, colonneD, e);
		e.getPlateau()[ligneD][colonneD] = this;
		e.getPlateau()[ligne][colonne] = null;
		setLigne(ligneD);
		setColonne(colonneD);
	}

	public abstract boolean peutAllerEn(int ligne, int colonne, Echiquier e);

	public void estMang�(int ligne, int colonne, Echiquier e) {
		System.out.println("La pi�ce " + getClass().getSimpleName() + " a �t� mang�");
		e.getListePi�ces().remove(e.getPlateau()[ligne][colonne]);
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
		for (Pi�ce p : e.getListePi�ces()) {
			if (Character.toLowerCase(p.getSymbole()) != 'r'
					&& p.peutAllerEn(getRoiAdverse(e).getLigne(), getRoiAdverse(e).getColonne(), e)
					&& p.getCouleur() != getRoiAdverse(e).getCouleur())
				return true;
		}
		return false;
	}

	public boolean metEnMatOuPat(Echiquier e) {
		for (Pi�ce pi�cesAdv : e.getListePi�ces()) {
			if (pi�cesAdv.getCouleur() != this.getCouleur()) {
				for (int i = -1; i <= 1; ++i) {
					for (int j = -1; j <= 1; ++j) {
						if (!pi�cesAdv.peutPasBouger(pi�cesAdv.getLigne() + i, pi�cesAdv.getColonne() + j, e)) {
							return false;
						}
					}
				}
			}
		}
		if (getRoiAdverse(e).estEnEchec(e)) {
			for (Pi�ce pi�cesAdv : e.getListePi�ces()) {
				if (pi�cesAdv.getCouleur() != this.getCouleur() && Character.toLowerCase(pi�cesAdv.getSymbole()) != 'r') {
					for (int i = -Echiquier.MAX + 1; i <= Echiquier.MAX; ++i) {
						for (int j = -Echiquier.MAX + 1; j <= Echiquier.MAX; ++j) {
							if (!pi�cesAdv.peutPasBouger(pi�cesAdv.getLigne() + i,pi�cesAdv.getColonne() + j, e)) {
								return false;
							}
						}
					}
				}
			}
			System.out.println("Vous avez mis le roi adverse en �chec et mat");
			System.out.println("\n       Les " + getRoi(e).getCouleur() + "S ont gagn� la partie");
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

	public Pi�ce getRoi(Echiquier e) {
		for (Pi�ce roi : e.getListePi�ces()) {
			if (roi.getCouleur() == getCouleur() && Character.toLowerCase(roi.getSymbole()) == 'r')
				return roi;
		}
		return null;
	}

	public Pi�ce getRoiAdverse(Echiquier e) {
		for (Pi�ce roi : e.getListePi�ces()) {
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