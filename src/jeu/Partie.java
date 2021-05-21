package jeu;

import java.util.Scanner;
import jeu.Echiquier;
import Pièces.Pièce.Couleur;

public class Partie {
	boolean finDePartie = false;
	
	public boolean erreurInitialisation(String s) {
		String problème = "";
		boolean erreur = false;
		if (s.length() != 2) {
			System.out.println("La saisie doit faire 2 caractères");
			return true;
		}
		if (!Character.isLetter(s.charAt(0)) || !Character.isDigit(s.charAt(1))) {
			problème += "Le 1er caractère doit être une lettre et le 2ème doit être un chiffre";
			erreur = true;
		}
		if (problème != "")
			System.out.println(problème);
		return erreur;
	}
	
	public boolean erreurSaisie(String s) {
		String problème = "";
		boolean erreur = false;

		if(s.equals("match nul")) {
			System.out.println("Proposition de match nul refusée");
			return true;
		}
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

	public boolean erreurDéplacement(int ligneA, int colonneA, int ligneD, int colonneD, Couleur c, Echiquier e) {
		String problème = "";
		boolean erreur = false;
		if (e.outOfBounds(ligneA, colonneA) || e.outOfBounds(ligneD, colonneD)) {
			System.out.println("La case n'est pas dans l'echiquier");
			return true;
		}
		if (e.estLibre(ligneA, colonneA)) {
			System.out.println("La case actuelle est vide");
			return true;
		}
		if (ligneA == ligneD && colonneA == colonneD) {
			problème += "Les pièces ne peuvent pas se déplacer à la même place\n";
			erreur = true;
		}
		if (e.getPlateau()[ligneA][colonneA].getCouleur() != c) {
			problème += "Vous ne pouvez pas jouer des pièces adverse\n";
			erreur = true;
		}
		if (problème != "")
			System.out.print(problème);
		return erreur;
	}

	public boolean jouer(String s, Couleur c, Echiquier e) {
		if (erreurSaisie(s))
			return false;
		int colonneA = s.charAt(0) - Echiquier.ConversASCII - 1;
		int ligneA = Echiquier.MAX - Integer.parseInt(s.substring(1, 2));
		int colonneD = s.charAt(2) - Echiquier.ConversASCII - 1;
		int ligneD = Echiquier.MAX - Integer.parseInt(s.substring(3, 4));
		if (!erreurDéplacement(ligneA, colonneA, ligneD, colonneD, c, e)) {
			if (e.getPlateau()[ligneA][colonneA].peutAllerEn(ligneD, colonneD, e)) {
				e.getPlateau()[ligneA][colonneA].déplacer(e, ligneD, colonneD);
				if (e.estLibre(ligneD, colonneD)) {
					return false;
				}
				if (e.getPlateau()[ligneD][colonneD].metEnEchec(e))
					System.out.println("Vous avez mis le roi adverse en situation d'echec");
				// System.out.println("Le coup a marché !\n");
				if (e.getPlateau()[ligneD][colonneD].metEnMatOuPat(e)) {
					finDePartie = true;
				}
				return true;
			}
		}
		System.out.println("Veuillez rejouer votre coup");
		return false;
	}

	public boolean finDemandée(String s, Couleur c) {
		Scanner sc = new Scanner(System.in);
		if (s.equals("abandonner")) {
			System.out.println("\n       Les " + c + "S ont abandonné la partie");
			return finDePartie = true;
		}
		if (s.equals("match nul")) {
			System.out.println("Les " + c + "S ont demandé une proposition de nul\nMettez 'oui' ou 'non' pour accepter");
			s = sc.nextLine();
			if (s.equals("oui")) {
				System.out.println("\n		Match nul");
				return finDePartie = true;
			}
		}
		return false;
	}

	public boolean partieFinie() {
		return finDePartie;
	} 
}
