package jeu;

import java.util.ArrayList;
import java.util.Scanner;

import jeu.Echiquier;

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
				if (this.plateau[i][j] == null) {
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

	public boolean jouer(String s) {

		int colonneActuelle = s.charAt(0) - ConversASCII - 1;
		int ligneActuelle = MAX - Integer.parseInt(s.substring(1, 2));
		int colonneDestination = s.charAt(2) - ConversASCII - 1;
		int ligneDestination = MAX - Integer.parseInt(s.substring(3, 4));
		
		if ((colonneActuelle >= 0 && colonneActuelle <= MAX && ligneActuelle >= 0 && ligneActuelle <= MAX)
				&& (colonneDestination >= 0 && colonneDestination <= MAX && ligneDestination >= 0
						&& ligneDestination <= MAX)
				&& (ligneActuelle != ligneDestination || colonneActuelle != colonneDestination)) {
			
			
			if (this.plateau[ligneActuelle][colonneActuelle] != null && this.plateau[ligneActuelle][colonneActuelle]
					.peutAllerEn(ligneDestination, colonneDestination, this)) {
				
				this.plateau[ligneActuelle][colonneActuelle].déplacer(this, ligneDestination, colonneDestination);
				//if (this.plateau[ligneDestination][colonneDestination].metEnEchec(this)) {
				//	System.out.println("Vous etes en situation d'echec");
				//}
				System.out.println("Le coup a marché !");
				return true;
			} else
				System.out.println("Le coup n'est pas valable");
			return false;
		} else
			System.out.println("La position est wtf les amis");
		return false;
	}

	public void placer(Pièce p) {
		plateau[p.getLigne()][p.getColonne()] = p;
	}

	public Pièce[][] getPlateau() {
		return plateau;
	}
}
