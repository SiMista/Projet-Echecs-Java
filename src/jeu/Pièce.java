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
	
	public void d�placer(Echiquier e, int ligne, int colonne) {
		e.getPlateau()[ligne][colonne] = this;
		e.getPlateau()[this.ligne][this.colonne] = null;
		this.ligne = ligne;
		this.colonne = colonne;
		System.out.println(ligne);
		System.out.println(colonne);

	}
	/* Jsp a quuoi sa sert mais c'est la
	public boolean occupe( int ligne, int colonne) {
		return (this.ligne == ligne && this.colonne == colonne);
	}
	*/
	
	/*Prototype d'une m�thode qui �vite la r�petition
	 * 	public boolean vaLaBas(int ligne, int colonne, Echiquier e) {
		if (peutAllerEn(ligne, colonne, e))
			if (e.getPlateau()[ligne][colonne] != null)
				return peutManger(ligne, colonne, e);
		return true;
	}
	 */
	
	public boolean peutAllerEn(int ligne, int colonne, Echiquier e) {
		return peutAllerEn(ligne, colonne, e);
	}
	
	public boolean peutManger(int ligne, int colonne, Echiquier e) {
		if (e.getPlateau()[ligne][colonne].getCouleur() != this.getCouleur()) {
			return true;
		} else
			return false;
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
