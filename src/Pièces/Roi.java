package pièces;

import jeu.Echiquier;
import jeu.IPièce;

public class Roi extends Pièce {
	
	/*
	 * @brief Le constructeur de roi, qui invoque le constructeur de la pièce pour se placer sur l'échiquier
	 * @param[in] ligne, un entier qui va être la ligne du roi dans l'échiquier
	 * @param[in] colonne, un entier qui va être la colonne du roi dans l'échiquier
	 * @param[in] c, la couleur qui a être la couleur du roi
	 * @param[in out] e, l'échiquier sur lequel se place le roi 
	 */
	public Roi(int ligne, int colonne, Pièce.Couleur c, Echiquier e) {
		super(ligne, colonne, c, e);
		if (c == Pièce.Couleur.BLANC)
			setSymbole('R');
		else
			setSymbole('r');
	}

	/*
	 * @brief Méthode qui vérifie si le roi peut aller à la case de destination selon ses mouvements
	 * @param[in] ligneD, ligne de la case de destination du roi
	 * @param[in] colonneD, colonne de la case de destination du roi
	 * @param[in] e, echiquier sur lequel le roi joue
	 * @return le booléen qui vérifie si le roi peut bien aller à la case de destination
	 */
	public boolean peutAllerEn(int ligneD, int colonneD, Echiquier e) {
		if (Math.abs(getLigne() - ligneD) > 1 || Math.abs(getColonne() - colonneD) > 1
				|| seraEnEchec(ligneD, colonneD, e) || roiACoté(ligneD, colonneD, e)) {
			return false;
		}
		if (!e.estLibre(ligneD, colonneD))
			return peutManger(ligneD, colonneD, e);
		return true;
	}

	/*
	 * @brief Méthode qui vérifie si le roi va se trouver à côté du roi adverse à la case de destination
	 * @param[in] ligneD, ligne de la case de destination du roi
	 * @param[in] colonneD, colonne de la case de destination du roi
	 * @param[in] e, echiquier sur lequel le roi joue
	 * @return le booléen qui vérifie si le roi adverse se trouve à côté du roi
	 */
	public boolean roiACoté(int ligneD, int colonneD, Echiquier e) {
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
	 * @brief Méthode qui vérifie si le roi sera en échec à la case de destination
	 * @param[in] ligneD, ligne de la case de destination du roi
	 * @param[in] colonneD, colonne de la case de destination du roi
	 * @param[in] e, echiquier sur lequel le roi joue
	 * @return le booléen qui vérifie si le roi sera en échec à la case de destination
	 */
	private boolean seraEnEchec(int ligneD, int colonneD, Echiquier e) {
		Echiquier eTmp = new Echiquier();
		eTmp.getPlateau()[ligneD][colonneD] = e.getPlateau()[ligneD][colonneD];
		e.getPlateau()[ligneD][colonneD] = this;
		e.getPlateau()[getLigne()][getColonne()] = null;
		for (IPièce p : e.getListePièces()) {
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
	 * @brief Méthode qui vérifie si le roi est actuellement en échec
	 * @param[in] e, échiquier sur lequel le roi joue
	 * @return le booléen qui vérifie si le roi est en échec
	 */
	@Override
	public boolean estEnEchec(Echiquier e) {
		for (IPièce p : e.getListePièces()) {
			if (this.getCouleur() != p.getCouleur() && p.peutAllerEn(getLigne(), getColonne(), e)) {
				return true;
			}
		}
		return false;
	}
	
}
