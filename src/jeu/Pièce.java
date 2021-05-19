package jeu;

public class Pi�ce implements IPi�ce {
	private int ligne, colonne;
	private Couleur couleur;

	public enum Couleur {
		BLANC, NOIR
	};

	public Pi�ce(int ligne, int colonne, Couleur c, Echiquier e) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.couleur = c;
		e.listePi�ces.add(this);
		e.placer(this);
	}

	public void d�placer(Echiquier e, int ligneD, int colonneD) {
		int roiLigne = 0;
		int roiColonne = 0;
		if (!e.estLibre(ligneD, colonneD)) {
			e.getPlateau()[ligneD][colonneD].estMang�(ligneD, colonneD, e);
		}
		e.getPlateau()[ligneD][colonneD] = this;
		e.getPlateau()[ligne][colonne] = null;
		roiLigne = getRoi(e).getLigne();
		roiColonne = getRoi(e).getColonne();
		if (!e.getPlateau()[roiLigne][roiColonne].estEnEchec(e)) {
			this.ligne = ligneD;
			this.colonne = colonneD;
		} else {
			e.getPlateau()[ligne][colonne] = this;
			e.getPlateau()[ligneD][colonneD] = null;
		}
	}

	public boolean peutAllerEn(int ligne, int colonne, Echiquier e) {
		return peutAllerEn(ligne, colonne, e);
	}

	public void estMang�(int ligne, int colonne, Echiquier e) {
		System.out.println("La pi�ce " + getClass().getSimpleName() + " a �t� mang�");
		e.listePi�ces.remove(e.getPlateau()[ligne][colonne]);
	}

	public boolean peutManger(int ligne, int colonne, Echiquier e) {
		if (e.getPlateau()[ligne][colonne].getCouleur() != this.getCouleur()) {
			return true;
		} else
			return false;
	}

	public boolean metEnEchec(Echiquier e) {
		for (Pi�ce p : e.listePi�ces) {
			if (Character.toLowerCase(p.getSymbole()) != 'r'
					&& p.peutAllerEn(getRoiAdverse(e).getLigne(), getRoiAdverse(e).getColonne(), e)
					&& p.getCouleur() != getRoiAdverse(e).getCouleur())
				return true;
		}
		return false;
	}

	public char getSymbole() {
		return getSymbole();
	}

	public boolean seraEnEchec(int i, int j, Echiquier e) {
		return false;
	}

	public boolean estEnEchec(Echiquier e) {
		return false;
	}

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

	public boolean estEnMat(Echiquier e) {
		int roiLigne = 0;
		int roiColonne = 0;
		roiLigne = getRoiAdverse(e).getLigne();
		roiColonne = getRoiAdverse(e).getColonne();
		if (getRoiAdverse(e).peutPasBouger(roiLigne - 1, roiColonne - 1, e)
				&& getRoiAdverse(e).peutPasBouger(roiLigne - 1, roiColonne, e)
				&& getRoiAdverse(e).peutPasBouger(roiLigne - 1, roiColonne + 1, e)
				&& getRoiAdverse(e).peutPasBouger(roiLigne + 1, roiColonne - 1, e)
				&& getRoiAdverse(e).peutPasBouger(roiLigne + 1, roiColonne, e)
				&& getRoiAdverse(e).peutPasBouger(roiLigne + 1, roiColonne + 1, e)
				&& getRoiAdverse(e).peutPasBouger(roiLigne, roiColonne - 1, e)
				&& getRoiAdverse(e).peutPasBouger(roiLigne, roiColonne + 1, e)) {
			if (getRoiAdverse(e).estEnEchec(e)) {
				System.out.println("Vous avez mis le roi adverse en Echec et mat");
				return true;
			}
		}
		return false;
	}

	public boolean estEnPat(Echiquier e) {
		int roiLigne = 0;
		int roiColonne = 0;
		if (roiSeul(e)) {
			roiLigne = getRoiAdverse(e).getLigne();
			roiColonne = getRoiAdverse(e).getColonne();
			if (getRoiAdverse(e).peutPasBouger(roiLigne - 1, roiColonne - 1, e)
					&& getRoiAdverse(e).peutPasBouger(roiLigne - 1, roiColonne, e)
					&& getRoiAdverse(e).peutPasBouger(roiLigne - 1, roiColonne + 1, e)
					&& getRoiAdverse(e).peutPasBouger(roiLigne + 1, roiColonne - 1, e)
					&& getRoiAdverse(e).peutPasBouger(roiLigne + 1, roiColonne, e)
					&& getRoiAdverse(e).peutPasBouger(roiLigne + 1, roiColonne + 1, e)
					&& getRoiAdverse(e).peutPasBouger(roiLigne, roiColonne - 1, e)
					&& getRoiAdverse(e).peutPasBouger(roiLigne, roiColonne + 1, e)) {
				System.out.println("Vous avez mis le roi adverse en Pat");
				return true;
			}
		}
		return false;
	}

	public Pi�ce getRoiAdverse(Echiquier e) {
		for (Pi�ce roi : e.listePi�ces) {
			if (roi.getCouleur() != getCouleur() && Character.toLowerCase(roi.getSymbole()) == 'r')
				return roi;
		}
		return null;
	}

	public Pi�ce getRoi(Echiquier e) {
		for (Pi�ce roi : e.listePi�ces) {
			if (roi.getCouleur() == getCouleur() && Character.toLowerCase(roi.getSymbole()) == 'r')
				return roi;
		}
		return null;
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

	public boolean roiSeul(Echiquier e) {
		for (Pi�ce p : e.listePi�ces) {
			if (p.getCouleur() != getCouleur() && Character.toLowerCase(p.getSymbole()) != 'r')
				return false;
		}
		return true;
	}

}
