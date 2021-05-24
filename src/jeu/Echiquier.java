package jeu;

import java.util.ArrayList;
import jeu.Echiquier;

public class Echiquier {
	public static final int MAX = 8;
	public static final int ConversASCII = 96;
	private ArrayList<IPi�ce> listePi�ces;
	private IPi�ce[][] plateau = new IPi�ce[MAX][MAX];
	
	/*
	 * @brief Le constructeur de l'�chiquier qui initialise toutes les cases en null
	 */
	public Echiquier() {
		listePi�ces = new ArrayList<>();
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++)
				plateau[i][j] = null;
		}
	}

	/*
	 * @brief Le constructeur de l'�chiquier qui initialise toutes les cases en null
	 * @return une chaine de caract�re repr�sentant l'enti�ret� du plateau
	 */
	public String toString() {
		String s = "";
		for (int i = 1; i <= MAX; i++) {
			s += "    " + (char) (ConversASCII + i);
		}
		s += "\n ";
		for (int i = 0; i < MAX; i++) {
			if (i == MAX - 1)
				s += "  ---\n";
			else
				s += "  ---";
		}
		for (int i = 0; i < MAX; i++) {
			s += (MAX - i) + " ";
			for (int j = 0; j < MAX; j++) {
				s += '|';
				if (estLibre(i, j)) {
					s += "   ";
				} else {
					s += " " + plateau[i][j].getSymbole() + " ";
				}
				s += '|';
			}
			s += " " + (MAX - i) + "\n ";
			for (int x = 0; x < MAX; x++) {
				if (x == MAX - 1)
					s += "  ---\n";
				else
					s += "  ---";
			}
		}
		for (int i = 1; i <= MAX; i++) {
			s += "    " + (char) (96 + i);
		}
		return s;
	}

	/*
	 * @brief M�thode qui v�rifie si la tour peut aller � la case de destination selon ses mouvements
	 * @param[in] ligneD, ligne de la case s�lectionn�e
	 * @param[in] colonneD, colonne de la case s�lectionn�e
	 * @return le bool�en qui v�rifie si la case en param�tres est hors du plateau
	 */
	public boolean outOfBounds(int ligne, int colonne) {
		if (ligne < 0 || ligne >= MAX || colonne < 0 || colonne >= MAX)
			return true;
		else
			return false;
	}

	/*
	 * @brief M�thode qui v�rifie si la case en param�tres est bien libre sur le plateau
	 * @param[in] ligneD, ligne de la case s�lectionn�e
	 * @param[in] colonneD, colonne de la case s�lectionn�e
	 * @return le bool�en qui v�rifie si la case du plateau est libre
	 */
	public boolean estLibre(int ligne, int colonne) {
		return (plateau[ligne][colonne] == null);
	}

	/*
	 * @brief M�thode qui place la pi�ce en param�tres sur le plateau avec sa ligne et sa colonne
	 * @param[in] p, la pi�ce que l'on va placer sur le plateau
	 * @return le bool�en qui v�rifie si la case en param�tres est hors du plateau
	 */
	public void placer(IPi�ce p) {
		plateau[p.getLigne()][p.getColonne()] = p;
	}

	/*
	 * @brief getter qui renvoie le plateau
	 * @return le tableau � 2 dimensions correspondant au plateau
	 */
	public IPi�ce[][] getPlateau() {
		return plateau;
	}
	
	/*
	 * @brief getter qui renvoie une liste de pi�ces, qui poss�de toutes les pi�ces sur le plateau
	 * @return une ArrayList de Pi�ce
	 */
	public ArrayList<IPi�ce> getListePi�ces() {
		return listePi�ces;
	}
}