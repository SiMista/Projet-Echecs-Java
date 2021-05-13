package jeu;

import java.util.ArrayList;
import java.util.Scanner;

import jeu.Echiquier;
import jeu.Pièce.Couleur;

public class Echiquier {
	public static final int MAX = 8;
	public static final int ConversASCII = 96;
	ArrayList<Pièce> listePièces;

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
			s += " " + (MAX - i);

			s += "\n ";
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

	public boolean jouer(String s, Couleur c) {

		int colonneA = s.charAt(0) - ConversASCII - 1;
		int ligneA = MAX - Integer.parseInt(s.substring(1, 2));
		int colonneD = s.charAt(2) - ConversASCII - 1;
		int ligneD = MAX - Integer.parseInt(s.substring(3, 4));

		if (plateau[ligneA][colonneA].getCouleur() != c) {
			System.out.println("Vous ne pouvez pas jouer des pièces adverse");
			return false;
		}

		if (Character.toLowerCase(plateau[ligneA][colonneA].getSymbole()) == 'r'
				&& c == plateau[ligneA][colonneA].getCouleur()) {
			if (plateau[ligneA][colonneA].seraEnEchec(ligneD, colonneD, this)) {
				return false;
			}
		}

		if ((colonneA >= 0 && colonneA <= MAX && ligneA >= 0 && ligneA <= MAX)
				&& (colonneD >= 0 && colonneD <= MAX && ligneD >= 0 && ligneD <= MAX)
				&& (ligneA != ligneD || colonneA != colonneD)) {

			if (!estLibre(ligneA, colonneA) && plateau[ligneA][colonneA].peutAllerEn(ligneD, colonneD, this)) {
				plateau[ligneA][colonneA].déplacer(this, ligneD, colonneD);
				if(estLibre(ligneD,colonneD)) {
					System.out.println("y'a heja mon reuf");
					return false;
				}
				if (plateau[ligneD][colonneD].metEnEchec(this)) {
					System.out.println("Vous avez mis le roi adverse en situation d'echec");
				}
				System.out.println("Le coup a marché !");
				return true;
			} else
				System.out.println("Le coup n'est pas valable");
			return false;
		} else
			System.out.println("La position est wtf les amis");
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
}
