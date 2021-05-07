package jeu;

public class Roi extends Pièce {
	private char symbole;
	
	public Roi(int colonne, int ligne, Couleur c, Echiquier e) {
		super(colonne, ligne, c, e);
		symbole = 'R';
	}
	
	public boolean peutAllerEn(int colonne, int ligne, Echiquier e) {
		if (this.getColonne() - colonne > 1 || this.getLigne()- ligne > 1)
			return false;
		return true;
	}
	
	@Override
	public char getSymbole() {
		if (getCouleur() == Couleur.BLANC) {
			return Character.toUpperCase(symbole);
		}
		else return Character.toLowerCase(symbole);
	}
}
