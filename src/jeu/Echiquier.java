package jeu;

import java.util.ArrayList;
import java.util.Scanner;

import Pièces.Pièce;
import Pièces.Pièce.Couleur;
import jeu.Echiquier;

public class Echiquier {
	public static final int MAX = 8;
	public static final int ConversASCII = 96;
	private ArrayList<Pièce> listePièces;


	private Pièce[][] plateau = new Pièce[MAX][MAX];

	public Echiquier() {
		listePièces = new ArrayList<>();
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

	public void placer(Pièce p) {
		plateau[p.getLigne()][p.getColonne()] = p;
	}

	public Pièce[][] getPlateau() {
		return plateau;
	}
	
	public ArrayList<Pièce> getListePièces() {
		return listePièces;
	}
}