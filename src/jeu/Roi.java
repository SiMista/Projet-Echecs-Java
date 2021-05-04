package jeu;

public class Roi extends Pièce {
	
	public Roi(int colonne, int ligne, Couleur c, Echiquier e) {
		super(colonne, ligne, c, e);
		
	}
	
	@Override
	public String getSymbole() {
		if (getCouleur() == Couleur.BLANC) {
			return "R";
		}
		else return "r";
	}
}
