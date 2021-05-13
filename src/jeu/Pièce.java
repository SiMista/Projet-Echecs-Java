package jeu;

public class Pièce implements IPièce {
	private int ligne, colonne;
	private Couleur couleur;

	public enum Couleur {
		BLANC, NOIR
	};

	public Pièce(int ligne, int colonne, Couleur c, Echiquier e) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.couleur = c;
		e.listePièces.add(this);
		e.placer(this);
	}

	public void déplacer(Echiquier e, int ligneD, int colonneD) {
		int roiLigne = 0;
		int roiColonne = 0;
		if (!e.estLibre(ligneD, colonneD)) {
			e.getPlateau()[ligneD][colonneD].estMangé(ligneD, colonneD, e);
		}
		e.getPlateau()[ligneD][colonneD] = this;
		e.getPlateau()[ligne][colonne] = null;

		for (Pièce roi : e.listePièces) {
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

	public void estMangé(int ligne, int colonne, Echiquier e) {
		System.out.println("Le " + e.getPlateau()[ligne][colonne].getSymbole() + " a été mangé");
		e.listePièces.remove(e.getPlateau()[ligne][colonne]);
	}

	public boolean peutManger(int ligne, int colonne, Echiquier e) {
		if (e.getPlateau()[ligne][colonne].getCouleur() != this.getCouleur()) {
			return true;
		} else
			return false;
	}

	public boolean metEnEchec(Echiquier e) {
		for (Pièce roi : e.listePièces) {
			if (Character.toLowerCase(roi.getSymbole()) == 'r' && getCouleur() != roi.getCouleur())
				for (Pièce p : e.listePièces) {
					if (Character.toLowerCase(e.getPlateau()[p.getLigne()][p.getColonne()].getSymbole()) != 'r'
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
		return seraEnEchec(i, j, e);
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

}
