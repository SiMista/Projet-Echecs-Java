package jeu;

import java.util.ArrayList;
import java.util.Scanner;

import jeu.Echiquier;
import jeu.Pièce.Couleur;

public class Echiquier {
	public static final int MAX = 8;
	public static final int ConversASCII = 96;
	ArrayList<Pièce> listePièces;
	boolean finDePartie = false;

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

	public boolean erreurSaisie(String s) {
        String problème = "";
        boolean erreur = false;

        if (s.length() != 4) {
            System.out.println("La saisie doit faire 4 caractères");
            return true;
        }
        if (!Character.isLetter(s.charAt(0)) || !Character.isLetter(s.charAt(2))) {
            problème += "Le 1er et le 3ème caractère doivent être des lettres";
            erreur = true;
        }
        if (!Character.isDigit(s.charAt(1)) || !Character.isDigit(s.charAt(3))) {
            if (erreur)
                problème += "\n";
            problème += "Le 2ème et le 4ème caractère doivent être des chiffres";
            erreur = true;
        }
        if (problème != "")
            System.out.println(problème);
        return erreur;
    }

    public boolean erreurDéplacement(int ligneA, int colonneA, int ligneD, int colonneD, Couleur c) {
        String problème = "";
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
            problème += "Les pièces ne peuvent pas se déplacer à la même place\n";
            erreur = true;
        }
        if (plateau[ligneA][colonneA].getCouleur() != c) {
            problème += "Vous ne pouvez pas jouer des pièces adverse\n";
            erreur = true;
        }
        if (problème != "")
            System.out.print(problème);
        return erreur;
    }

	public boolean jouer(String s, Couleur c) {

		if (erreurSaisie(s))
			return false;
		int colonneA = s.charAt(0) - ConversASCII - 1;
		int ligneA = MAX - Integer.parseInt(s.substring(1, 2));
		int colonneD = s.charAt(2) - ConversASCII - 1;
		int ligneD = MAX - Integer.parseInt(s.substring(3, 4));
		if (!erreurDéplacement(ligneA, colonneA, ligneD, colonneD, c)) {
			if (plateau[ligneA][colonneA].peutAllerEn(ligneD, colonneD, this)) {
				plateau[ligneA][colonneA].déplacer(this, ligneD, colonneD);
				if (estLibre(ligneD, colonneD)) {
					return false;
				}
				if (plateau[ligneD][colonneD].metEnEchec(this))
					System.out.println("Vous avez mis le roi adverse en situation d'echec");
				// System.out.println("Le coup a marché !\n");
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

	public void placer(Pièce p) {
		plateau[p.getLigne()][p.getColonne()] = p;
	}
	
	public Pièce[][] getPlateau() {
		return plateau;
	}
}
