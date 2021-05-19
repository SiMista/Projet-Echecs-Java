package jeu;

import jeu.Pi�ce.Couleur;

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
	public void d�placer(Echiquier e, int ligneD, int colonneD) {

		e.getPlateau()[ligneD][colonneD] = this;
		e.getPlateau()[getLigne()][getColonne()] = null;
		setLigne(ligneD);
		setColonne(colonneD);
	}

	public boolean peutAllerEn(int ligneD, int colonneD, Echiquier e) {
		if (Math.abs(getLigne() - ligneD) > 1 || Math.abs(getColonne() - colonneD) > 1
				|| seraEnEchec(ligneD, colonneD, e)) {
			return false;
		}
		if (e.estLibre(ligneD, colonneD)) {
			int ligneA = getLigne();
			int colonneA = getColonne();
			e.getPlateau()[ligneD][colonneD] = this;
			e.getPlateau()[ligneA][colonneA] = null;

			if (seraEnEchec(ligneD, colonneD, e)) {
				e.getPlateau()[ligneD][colonneD] = null;
				e.getPlateau()[ligneA][colonneA] = this;
				//System.out.println("Vous �tes toujours en �chec");
				return false;
			}
			e.getPlateau()[ligneD][colonneD] = null;
			e.getPlateau()[ligneA][colonneA] = this;
			return true;
		} else if (!e.estLibre(ligneD, colonneD)) {
			Echiquier eTmp = new Echiquier();
			eTmp.getPlateau()[ligneD][colonneD] = e.getPlateau()[ligneD][colonneD];
			e.getPlateau()[ligneD][colonneD] = this;
			e.getPlateau()[getLigne()][getColonne()] = null;
			if (seraEnEchec(ligneD, colonneD, e)) {
				e.getPlateau()[getLigne()][getColonne()] = this;
				e.getPlateau()[ligneD][colonneD] = eTmp.getPlateau()[ligneD][colonneD];
				eTmp.getPlateau()[ligneD][colonneD] = null;
				//System.out.println("Ce coup mettrait votre roi en �chec");
				return false;
			}
			e.getPlateau()[getLigne()][getColonne()] = this;
			e.getPlateau()[ligneD][colonneD] = eTmp.getPlateau()[ligneD][colonneD];
			e.getPlateau()[ligneD][colonneD].estMang�(ligneD, colonneD, e);
			return peutManger(ligneD, colonneD, e);
		}
		return false;
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
			if (Character.toLowerCase(p.getSymbole()) != 'r' && this.getCouleur() != p.getCouleur()
					&& p.peutAllerEn(ligneD, colonneD, e)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean estEnEchec(Echiquier e) {
		for (Pi�ce p : e.listePi�ces) {
			if (this.getCouleur() != p.getCouleur() && p.peutAllerEn(getLigne(), getColonne(), e)) {
				System.out.println("Votre roi est en �chec");
				return true;
			}
		}
		return false;
	}

	/*
	 * @Override public void initialiser(String s, Couleur c) { // Dans le cas de
	 * joueur contre joueur en commencant par Blanc System.out.println("Joueur " +
	 * c.toString() +", veuillez indiquez l'emplacement de votre roi");
	 * 
	 * }
	 */
	public char getSymbole() {
		return symbole;
	}
}
