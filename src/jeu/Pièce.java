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
		if (e.getPlateau()[ligneD][colonneD] != null) {
			e.getPlateau()[ligneD][colonneD].estMang�(ligneD, colonneD, e);
			;
		}
		e.getPlateau()[ligneD][colonneD] = this;
		e.getPlateau()[this.ligne][this.colonne] = null;
		this.ligne = ligneD;
		this.colonne = colonneD;

	}
	/*
	 * Jsp a quoi sa sert mais c'est la public boolean occupe( int ligne, int
	 * colonne) { return (this.ligne == ligne && this.colonne == colonne); }
	 */

	/*
	 * Prototype d'une m�thode qui �vite la r�petition public boolean vaLaBas(int
	 * ligne, int colonne, Echiquier e) { if (peutAllerEn(ligne, colonne, e)) if
	 * (e.getPlateau()[ligne][colonne] != null) return peutManger(ligne, colonne,
	 * e); return true; }
	 */

	public boolean peutAllerEn(int ligne, int colonne, Echiquier e) {
		return peutAllerEn(ligne, colonne, e);
	}

	public void estMang�(int ligne, int colonne, Echiquier e) {
		System.out.println("Le " + e.getPlateau()[ligne][colonne].getSymbole() + " a �t� mang�");
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
					if (p.peutAllerEn(roi.getLigne(), roi.getColonne(), e) && p.getCouleur() != roi.getCouleur())
						return true;
				}
		}
		return false;
	}

	public boolean estEnEchec(int ligne, int colonne, Echiquier e) {
		return estEnEchec(ligne, colonne, e);
	}

	public char getSymbole() {
		return getSymbole();
	}

	public int getColonne() {
		return colonne;
	}

	public int getLigne() {
		return ligne;
	}

	public Couleur getCouleur() {
		return couleur;
	}
}
