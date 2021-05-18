package jeu;

import jeu.Pièce.Couleur;

public class Roi extends Pièce {
	private char symbole;

	public Roi(int ligne, int colonne, Pièce.Couleur c, Echiquier e) {
		super(ligne, colonne, c, e);
		if (c == Pièce.Couleur.BLANC)
			symbole = 'R';
		else
			symbole = 'r';
	}
	
	@Override
    public void déplacer(Echiquier e, int ligneD, int colonneD) {
        e.getPlateau()[ligneD][colonneD] = this;
        e.getPlateau()[getLigne()][getColonne()] = null;
        if (!seraEnEchec(ligneD, colonneD, e)) {
            setLigne(ligneD);
            setColonne(colonneD);
        } else {
            e.getPlateau()[getLigne()][getColonne()] = this;
            e.getPlateau()[ligneD][colonneD] = null;
            System.out.println("Ce coup mettrait votre roi en échec");
        }
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
		for (Pièce p : e.listePièces) {
			if (Character.toLowerCase(p.getSymbole()) != 'r' && this.getCouleur() != p.getCouleur() && p.peutAllerEn(ligneD, colonneD, e)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean estEnEchec(Echiquier e) {
		for (Pièce p : e.listePièces) {
			if (this.getCouleur() != p.getCouleur() && p.peutAllerEn(getLigne(), getColonne(), e)) {
				System.out.println("Le roi est en échec zebi");
				return true;
			}
		}
		return false;
	}
	/*
	@Override
	public void initialiser(String s, Couleur c) {
		// Dans le cas de joueur contre joueur en commencant par Blanc
		System.out.println("Joueur " + c.toString() +", veuillez indiquez l'emplacement de votre roi");

	}
	*/
	public char getSymbole() {
		return symbole;
	}
}
