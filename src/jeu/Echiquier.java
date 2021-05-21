package jeu;

import java.util.ArrayList;
import java.util.Scanner;

import Pi�ces.Pi�ce;
import Pi�ces.Pi�ce.Couleur;
import jeu.Echiquier;

public class Echiquier {
	public static final int MAX = 8;
	public static final int ConversASCII = 96;
	private ArrayList<Pi�ce> listePi�ces;


	private Pi�ce[][] plateau = new Pi�ce[MAX][MAX];

	public Echiquier() {
		listePi�ces = new ArrayList<>();
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++)
				plateau[i][j] = null;
		}
	}

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
	 * public void initialiser(String s, Couleur c) { System.out.println("Joueur " +
	 * c.toString() +", veuillez indiquez l'emplacement de votre");
	 * 
	 * }
	 */

	public boolean outOfBounds(int ligne, int colonne) {
		if (ligne < 0 || ligne >= MAX || colonne < 0 || colonne >= MAX)
			return true;
		else
			return false;
	}

	public boolean estLibre(int ligne, int colonne) {
		return (plateau[ligne][colonne] == null);
	}

	public void placer(Pi�ce p) {
		plateau[p.getLigne()][p.getColonne()] = p;
	}

	public Pi�ce[][] getPlateau() {
		return plateau;
	}
	
	public ArrayList<Pi�ce> getListePi�ces() {
		return listePi�ces;
	}
}