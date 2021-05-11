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

	public boolean peutAllerEn(int ligne, int colonne, Echiquier e) {

		if (Math.abs(getLigne() - ligne) > 1 || Math.abs(getColonne() - colonne) > 1 || estEnEchec(ligne, colonne, e))
			return false;
		if (e.getPlateau()[ligne][colonne] != null)
			return peutManger(ligne, colonne, e);
		return true;
	}

	@Override
	public boolean peutManger(int ligne, int colonne, Echiquier e) {
		if (e.getPlateau()[ligne][colonne].getCouleur() != this.getCouleur()) {
			if (!estEnEchec(ligne, colonne, e))
				return true;
			return false;
		} else
			return false;
	}

	public boolean estEnEchec(int ligne, int colonne, Echiquier e) {
		for (Pi�ce p : e.listePi�ces) {
			if (this.getCouleur() != p.getCouleur() && p.peutAllerEn(ligne, colonne, e)) {
				System.out.println("tes UN echec sale noob");
				return true;
			}
		}
		return false;
	}

	public char getSymbole() {
		return symbole;
	}
}
