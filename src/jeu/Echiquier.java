package jeu;

import java.util.ArrayList;
import java.util.Scanner;

import jeu.Echiquier;
import jeu.Pi�ce.Couleur;

public class Echiquier {
	public static final int MAX = 8;
	public static final int ConversASCII = 96;
	ArrayList<Pi�ce> listePi�ces;
	boolean finDePartie = false;

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

	public boolean erreurSaisie(String s) {
        String probl�me = "";
        boolean erreur = false;

        if (s.length() != 4) {
            System.out.println("La saisie doit faire 4 caract�res");
            return true;
        }
        if (!Character.isLetter(s.charAt(0)) || !Character.isLetter(s.charAt(2))) {
            probl�me += "Le 1er et le 3�me caract�re doivent �tre des lettres";
            erreur = true;
        }
        if (!Character.isDigit(s.charAt(1)) || !Character.isDigit(s.charAt(3))) {
            if (erreur)
                probl�me += "\n";
            probl�me += "Le 2�me et le 4�me caract�re doivent �tre des chiffres";
            erreur = true;
        }
        if (probl�me != "")
            System.out.println(probl�me);
        return erreur;
    }

    public boolean erreurD�placement(int ligneA, int colonneA, int ligneD, int colonneD, Couleur c) {
        String probl�me = "";
        boolean erreur = false;
        if (outOfBounds(ligneA, colonneA) || outOfBounds(ligneD, colonneD)) {
            System.out.println("La case n'est pas dans l'echiquier");
            return true;
        }
        if (estLibre(ligneA, colonneA)) {
            System.out.println("La case actuelle est vide");
            return true;
        }
        if (ligneA == ligneD && colonneA == colonneD) {
            probl�me += "Les pi�ces ne peuvent pas se d�placer � la m�me place\n";
            erreur = true;
        }
        if (plateau[ligneA][colonneA].getCouleur() != c) {
            probl�me += "Vous ne pouvez pas jouer des pi�ces adverse\n";
            erreur = true;
        }
        if (probl�me != "")
            System.out.print(probl�me);
        return erreur;
    }

	public boolean jouer(String s, Couleur c) {

		if (erreurSaisie(s))
			return false;
		int colonneA = s.charAt(0) - ConversASCII - 1;
		int ligneA = MAX - Integer.parseInt(s.substring(1, 2));
		int colonneD = s.charAt(2) - ConversASCII - 1;
		int ligneD = MAX - Integer.parseInt(s.substring(3, 4));
		if (!erreurD�placement(ligneA, colonneA, ligneD, colonneD, c)) {
			if (plateau[ligneA][colonneA].peutAllerEn(ligneD, colonneD, this)) {
				plateau[ligneA][colonneA].d�placer(this, ligneD, colonneD);
				if (estLibre(ligneD, colonneD)) {
					return false;
				}
				if (plateau[ligneD][colonneD].metEnEchec(this))
					System.out.println("Vous avez mis le roi adverse en situation d'echec");
				// System.out.println("Le coup a march� !\n");
				if (plateau[ligneD][colonneD].estEnMat(this) || plateau[ligneD][colonneD].estEnPat(this)) {
					finDePartie = true;
					partieFinie();
				}
				return true;
			}
		}
		System.out.println("Veuillez rejouer votre coup");
		return false;
	}
	
	public boolean partieFinie() {
		return finDePartie;
	}

	/*
	public void initialiser(String s, Couleur c) {
		System.out.println("Joueur " + c.toString() +", veuillez indiquez l'emplacement de votre");
		
	}
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
}
