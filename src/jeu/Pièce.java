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

		for (Pi�ce roi : e.listePi�ces) {
			if (roi.getCouleur() == getCouleur() && Character.toLowerCase(roi.getSymbole()) == 'r') {
				roiLigne = roi.getLigne();
				roiColonne = roi.getColonne();
			}
		}
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
		for (Pi�ce roi : e.listePi�ces) {
			if (Character.toLowerCase(roi.getSymbole()) == 'r' && getCouleur() != roi.getCouleur())
				for (Pi�ce p : e.listePi�ces) {
					if (Character.toLowerCase(p.getSymbole()) != 'r'
							&& p.peutAllerEn(roi.getLigne(), roi.getColonne(), e) && p.getCouleur() != roi.getCouleur())
						return true;
				}
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

	public boolean pat(Echiquier e) {
		int roiLigne = 0;
		int roiColonne = 0;
		if (roiSeul(e)) {
			for (Pi�ce roi : e.listePi�ces) {
				if (roi.getCouleur() != getCouleur() && Character.toLowerCase(roi.getSymbole()) == 'r') {
					roiLigne = roi.getLigne();
					roiColonne = roi.getColonne();
					if (roi.peutBouger(roiLigne - 1, roiColonne - 1, e) && roi.peutBouger(roiLigne - 1, roiColonne, e)
							&& roi.peutBouger(roiLigne - 1, roiColonne + 1, e)
							&& roi.peutBouger(roiLigne + 1, roiColonne - 1, e)
							&& roi.peutBouger(roiLigne + 1, roiColonne, e)
							&& roi.peutBouger(roiLigne + 1, roiColonne + 1, e)
							&& roi.peutBouger(roiLigne, roiColonne - 1, e) && roi.peutBouger(roiLigne, roiColonne + 1, e)) {
						return true;
					}

				}
			}
		}
		return false;
	}

	public boolean peutBouger(int ligneD, int colonneD, Echiquier e) {
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
