package pièces;

import jeu.Echiquier;

/*
 * Une instance de cette classe représente une Tour de couleur blanc ou noir qui étend la classe Pièce
 * @author DE ALMEIDA Jules, DEIVA Siméon, SIVANAND Nirussan
 */
public class Tour extends Pièce {
	
	/*
	 * @brief Le constructeur de la tour, qui invoque le constructeur de la pièce pour se placer sur l'échiquier
	 * @param[in] ligne, un entier qui va être la ligne due la tour dans l'échiquier
	 * @param[in] colonne, un entier qui va être la colonne de la tour dans l'échiquier
	 * @param[in] c, la couleur qui a être la couleur de la tour
	 * @param[in out] e, l'échiquier sur lequel se place la tour 
	 */
	public Tour(int ligne, int colonne, Pièce.Couleur c, Echiquier e) {
		super(ligne, colonne, c, e);
		if (c == Pièce.Couleur.BLANC)
			setSymbole('T');
		else
			setSymbole('t');
	}

	/*
	 * @brief Méthode qui vérifie si la tour peut aller à la case de destination selon ses mouvements
	 * @param[in] ligneD, ligne de la case de destination de la tour
	 * @param[in] colonneD, colonne de la case de destination de la tour
	 * @param[in out] e, echiquier sur lequel la tour joue
	 * @return le booléen qui vérifie si la tour peut bien aller à la case de destination
	 */
	public boolean peutAllerEn(int ligneD, int colonneD, Echiquier e) {
		int dx = getColonne() - colonneD > 0 ? -1 : 1;
		int dy = getLigne() - ligneD > 0 ? -1 : 1;

		if ((getLigne() != ligneD && getColonne() != colonneD) || (getLigne() == ligneD && getColonne() == colonneD))
			return false;
		if (getLigne() != ligneD && getColonne() == colonneD) {
			for (int i = 1; i < Math.abs(getLigne() - ligneD); ++i) {
				if (!e.estLibre(getLigne() + i * dy, getColonne()))
					return false;
			}
		}
		if (getLigne() == ligneD && getColonne() != colonneD) {
			for (int i = 1; i < Math.abs(getColonne() - colonneD); ++i) {
				if (!e.estLibre(getLigne(), getColonne() + i * dx))
					return false;
			}
		}
		if (échecSimulé(ligneD, colonneD, e))
			return false;
		if (!e.estLibre(ligneD, colonneD))
			return peutManger(ligneD, colonneD, e);
		return true;
	}
}