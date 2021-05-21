package jeu;

import java.util.Scanner;
import jeu.Echiquier;
import Pi�ces.Pi�ce.Couleur;

public class Partie {
	boolean finDePartie = false;
	
	public boolean erreurInitialisation(String s) {
		String probl�me = "";
		boolean erreur = false;
		if (s.length() != 2) {
			System.out.println("La saisie doit faire 2 caract�res");
			return true;
		}
		if (!Character.isLetter(s.charAt(0)) || !Character.isDigit(s.charAt(1))) {
			probl�me += "Le 1er caract�re doit �tre une lettre et le 2�me doit �tre un chiffre";
			erreur = true;
		}
		if (probl�me != "")
			System.out.println(probl�me);
		return erreur;
	}
	
	public boolean erreurSaisie(String s) {
		String probl�me = "";
		boolean erreur = false;

		if(s.equals("match nul")) {
			System.out.println("Proposition de match nul refus�e");
			return true;
		}
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

	public boolean erreurD�placement(int ligneA, int colonneA, int ligneD, int colonneD, Couleur c, Echiquier e) {
		String probl�me = "";
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
			probl�me += "Les pi�ces ne peuvent pas se d�placer � la m�me place\n";
			erreur = true;
		}
		if (e.getPlateau()[ligneA][colonneA].getCouleur() != c) {
			probl�me += "Vous ne pouvez pas jouer des pi�ces adverse\n";
			erreur = true;
		}
		if (probl�me != "")
			System.out.print(probl�me);
		return erreur;
	}

	public boolean jouer(String s, Couleur c, Echiquier e) {
		if (erreurSaisie(s))
			return false;
		int colonneA = s.charAt(0) - Echiquier.ConversASCII - 1;
		int ligneA = Echiquier.MAX - Integer.parseInt(s.substring(1, 2));
		int colonneD = s.charAt(2) - Echiquier.ConversASCII - 1;
		int ligneD = Echiquier.MAX - Integer.parseInt(s.substring(3, 4));
		if (!erreurD�placement(ligneA, colonneA, ligneD, colonneD, c, e)) {
			if (e.getPlateau()[ligneA][colonneA].peutAllerEn(ligneD, colonneD, e)) {
				e.getPlateau()[ligneA][colonneA].d�placer(e, ligneD, colonneD);
				if (e.estLibre(ligneD, colonneD)) {
					return false;
				}
				if (e.getPlateau()[ligneD][colonneD].metEnEchec(e))
					System.out.println("Vous avez mis le roi adverse en situation d'echec");
				// System.out.println("Le coup a march� !\n");
				if (e.getPlateau()[ligneD][colonneD].metEnMatOuPat(e)) {
					finDePartie = true;
				}
				return true;
			}
		}
		System.out.println("Veuillez rejouer votre coup");
		return false;
	}

	public boolean finDemand�e(String s, Couleur c) {
		Scanner sc = new Scanner(System.in);
		if (s.equals("abandonner")) {
			System.out.println("\n       Les " + c + "S ont abandonn� la partie");
			return finDePartie = true;
		}
		if (s.equals("match nul")) {
			System.out.println("Les " + c + "S ont demand� une proposition de nul\nMettez 'oui' ou 'non' pour accepter");
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
