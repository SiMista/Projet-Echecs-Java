package jeu;

import java.util.ArrayList;
import jeu.Echiquier;

public class Echiquier {
	public static final int MAX = 8;
	public static final int ConversASCII = 96;
	private ArrayList<IPièce> listePièces;
	private IPièce[][] plateau = new IPièce[MAX][MAX];
	
	/*
	 * @brief Le constructeur de l'échiquier qui initialise toutes les cases en null
	 */
	public Echiquier() {
		listePièces = new ArrayList<>();
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++)
				plateau[i][j] = null;
		}
	}

	/*
	 * @brief Le constructeur de l'échiquier qui initialise toutes les cases en null
	 * @return une chaine de caractère représentant l'entièreté du plateau
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
	 * @brief Méthode qui vérifie si la tour peut aller à la case de destination selon ses mouvements
	 * @param[in] ligneD, ligne de la case sélectionnée
	 * @param[in] colonneD, colonne de la case sélectionnée
	 * @return le booléen qui vérifie si la case en paramètres est hors du plateau
	 */
	public boolean outOfBounds(int ligne, int colonne) {
		if (ligne < 0 || ligne >= MAX || colonne < 0 || colonne >= MAX)
			return true;
		else
			return false;
	}

	/*
	 * @brief Méthode qui vérifie si la case en paramètres est bien libre sur le plateau
	 * @param[in] ligneD, ligne de la case sélectionnée
	 * @param[in] colonneD, colonne de la case sélectionnée
	 * @return le booléen qui vérifie si la case du plateau est libre
	 */
	public boolean estLibre(int ligne, int colonne) {
		return (plateau[ligne][colonne] == null);
	}

	/*
	 * @brief Méthode qui place la pièce en paramètres sur le plateau avec sa ligne et sa colonne
	 * @param[in] p, la pièce que l'on va placer sur le plateau
	 * @return le booléen qui vérifie si la case en paramètres est hors du plateau
	 */
	public void placer(IPièce p) {
		plateau[p.getLigne()][p.getColonne()] = p;
	}

	/*
	 * @brief getter qui renvoie le plateau
	 * @return le tableau à 2 dimensions correspondant au plateau
	 */
	public IPièce[][] getPlateau() {
		return plateau;
	}
	
	/*
	 * @brief getter qui renvoie une liste de pièces, qui possède toutes les pièces sur le plateau
	 * @return une ArrayList de Pièce
	 */
	public ArrayList<IPièce> getListePièces() {
		return listePièces;
	}
}