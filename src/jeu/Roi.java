package jeu;

public class Roi extends Pi�ce {
	private char symbole;

	public Roi(int ligne, int colonne, Pi�ce.Couleur c, Echiquier e) {
		super(ligne, colonne, c, e);
		if (c == Pi�ce.Couleur.BLANC)
			symbole = 'R';
		else
			symbole = 'r';
	}
	
	@Override
	public void d�placer(Echiquier e, int ligne, int colonne) {
		e.getPlateau()[ligne][colonne] = this;
		e.getPlateau()[getLigne()][getColonne()] = null;
		setLigne(ligne);
		setColonne(colonne);
	}
	
	public boolean peutAllerEn(int ligneD, int colonneD, Echiquier e) {
		if (Math.abs(getLigne() - ligneD) > 1 || Math.abs(getColonne() - colonneD) > 1 || seraEnEchec(ligneD, colonneD, e))
			return false;
		if (!e.estLibre(ligneD, colonneD))
			return peutManger(ligneD, colonneD, e);
		return true;
	}

	@Override
	public boolean peutManger(int ligneD, int colonneD, Echiquier e) {
		if (e.getPlateau()[ligneD][colonneD].getCouleur() != this.getCouleur()) {
			return true;
		} else
			return false;
	}
	
	public boolean seraEnEchec(int ligneD, int colonneD, Echiquier e) {
		for (Pi�ce p : e.listePi�ces) {
			if (Character.toLowerCase(p.getSymbole()) != 'r' && this.getCouleur() != p.getCouleur() && p.peutAllerEn(ligneD, colonneD, e)) {
				System.out.println("Tu te mets toi meme en echec sale con");
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean estEnEchec(Echiquier e) {
		for (Pi�ce p : e.listePi�ces) {
			if (this.getCouleur() != p.getCouleur() && p.peutAllerEn(getLigne(), getColonne(), e)) {
				System.out.println("Le roi est en �chec zebi");
				return true;
			}
		}
		return false;
	}
	
	public char getSymbole() {
		return symbole;
	}
}
