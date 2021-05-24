package pi�ces;

import jeu.Echiquier;
import jeu.IPi�ce;

public class Roi extends Pi�ce {
	
	/*
	 * @brief Le constructeur de roi, qui invoque le constructeur de la pi�ce pour se placer sur l'�chiquier
	 * @param[in] ligne, un entier qui va �tre la ligne du roi dans l'�chiquier
	 * @param[in] colonne, un entier qui va �tre la colonne du roi dans l'�chiquier
	 * @param[in] c, la couleur qui a �tre la couleur du roi
	 * @param[in out] e, l'�chiquier sur lequel se place le roi 
	 */
	public Roi(int ligne, int colonne, Pi�ce.Couleur c, Echiquier e) {
		super(ligne, colonne, c, e);
		if (c == Pi�ce.Couleur.BLANC)
			setSymbole('R');
		else
			setSymbole('r');
	}

	/*
	 * @brief M�thode qui v�rifie si le roi peut aller � la case de destination selon ses mouvements
	 * @param[in] ligneD, ligne de la case de destination du roi
	 * @param[in] colonneD, colonne de la case de destination du roi
	 * @param[in] e, echiquier sur lequel le roi joue
	 * @return le bool�en qui v�rifie si le roi peut bien aller � la case de destination
	 */
	public boolean peutAllerEn(int ligneD, int colonneD, Echiquier e) {
		if (Math.abs(getLigne() - ligneD) > 1 || Math.abs(getColonne() - colonneD) > 1
				|| seraEnEchec(ligneD, colonneD, e) || roiACot�(ligneD, colonneD, e)) {
			return false;
		}
		if (!e.estLibre(ligneD, colonneD))
			return peutManger(ligneD, colonneD, e);
		return true;
	}

	/*
	 * @brief M�thode qui v�rifie si le roi va se trouver � c�t� du roi adverse � la case de destination
	 * @param[in] ligneD, ligne de la case de destination du roi
	 * @param[in] colonneD, colonne de la case de destination du roi
	 * @param[in] e, echiquier sur lequel le roi joue
	 * @return le bool�en qui v�rifie si le roi adverse se trouve � c�t� du roi
	 */
	public boolean roiACot�(int ligneD, int colonneD, Echiquier e) {
		for (int i = -1; i <= 1; ++i) {
			for (int j = -1; j <= 1; ++j) {
				if (!e.outOfBounds(ligneD + i, colonneD + j)
						&& e.getPlateau()[ligneD + i][colonneD + j] == getRoiAdverse(e)) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * @brief M�thode qui v�rifie si le roi sera en �chec � la case de destination
	 * @param[in] ligneD, ligne de la case de destination du roi
	 * @param[in] colonneD, colonne de la case de destination du roi
	 * @param[in] e, echiquier sur lequel le roi joue
	 * @return le bool�en qui v�rifie si le roi sera en �chec � la case de destination
	 */
	private boolean seraEnEchec(int ligneD, int colonneD, Echiquier e) {
		Echiquier eTmp = new Echiquier();
		eTmp.getPlateau()[ligneD][colonneD] = e.getPlateau()[ligneD][colonneD];
		e.getPlateau()[ligneD][colonneD] = this;
		e.getPlateau()[getLigne()][getColonne()] = null;
		for (IPi�ce p : e.getListePi�ces()) {
			if (Character.toLowerCase(p.getSymbole()) != 'r' && this.getCouleur() != p.getCouleur()
					&& p.peutAllerEn(ligneD, colonneD, e)) {
				e.getPlateau()[ligneD][colonneD] = null;
				e.getPlateau()[getLigne()][getColonne()] = this;
				e.getPlateau()[ligneD][colonneD] = eTmp.getPlateau()[ligneD][colonneD];
				return true;
			}
		}
		e.getPlateau()[ligneD][colonneD] = null;
		e.getPlateau()[getLigne()][getColonne()] = this;
		e.getPlateau()[ligneD][colonneD] = eTmp.getPlateau()[ligneD][colonneD];
		return false;
	}

	
	/*
	 * @brief M�thode qui v�rifie si le roi est actuellement en �chec
	 * @param[in] e, �chiquier sur lequel le roi joue
	 * @return le bool�en qui v�rifie si le roi est en �chec
	 */
	@Override
	public boolean estEnEchec(Echiquier e) {
		for (IPi�ce p : e.getListePi�ces()) {
			if (this.getCouleur() != p.getCouleur() && p.peutAllerEn(getLigne(), getColonne(), e)) {
				return true;
			}
		}
		return false;
	}
	
}
