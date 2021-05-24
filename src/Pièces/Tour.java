package pi�ces;

import jeu.Echiquier;

/*
 * Une instance de cette classe repr�sente une Tour de couleur blanc ou noir qui �tend la classe Pi�ce
 * @author DE ALMEIDA Jules, DEIVA Sim�on, SIVANAND Nirussan
 */
public class Tour extends Pi�ce {
	
	/*
	 * @brief Le constructeur de la tour, qui invoque le constructeur de la pi�ce pour se placer sur l'�chiquier
	 * @param[in] ligne, un entier qui va �tre la ligne due la tour dans l'�chiquier
	 * @param[in] colonne, un entier qui va �tre la colonne de la tour dans l'�chiquier
	 * @param[in] c, la couleur qui a �tre la couleur de la tour
	 * @param[in out] e, l'�chiquier sur lequel se place la tour 
	 */
	public Tour(int ligne, int colonne, Pi�ce.Couleur c, Echiquier e) {
		super(ligne, colonne, c, e);
		if (c == Pi�ce.Couleur.BLANC)
			setSymbole('T');
		else
			setSymbole('t');
	}

	/*
	 * @brief M�thode qui v�rifie si la tour peut aller � la case de destination selon ses mouvements
	 * @param[in] ligneD, ligne de la case de destination de la tour
	 * @param[in] colonneD, colonne de la case de destination de la tour
	 * @param[in out] e, echiquier sur lequel la tour joue
	 * @return le bool�en qui v�rifie si la tour peut bien aller � la case de destination
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
		if (�checSimul�(ligneD, colonneD, e))
			return false;
		if (!e.estLibre(ligneD, colonneD))
			return peutManger(ligneD, colonneD, e);
		return true;
	}
}