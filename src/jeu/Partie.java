package jeu;

import java.util.Scanner;
import pi�ces.Roi;
import pi�ces.Tour;
import pi�ces.Pi�ce.Couleur;

public class Partie {
	boolean finDePartie = false;

	/*
	 * @brief M�thode qui v�rifie si l'utilisateur a saisie des valeurs coh�rentes pendant l'initialisation
	 * @param[in] s, cha�ne de caract�re entr�e par l'utilisateur
	 * @return le bool�en qui v�rifie si la cha�ne poss�de une erreur
	 */
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

	/*
	 * @brief M�thode qui v�rifie si l'utilisateur a saisie des valeurs coh�rentes pendant l'initialisation
	 * @param[in] s, cha�ne de caract�re entr�e par l'utilisateur
	 * @return le bool�en qui v�rifie si la cha�ne poss�de une erreur
	 */
	private boolean erreurSaisie(String s) {
		String probl�me = "";
		boolean erreur = false;

		if (s.equals("match nul")) {
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

	/*
	 * @brief M�thode qui v�rifie si le d�placement a une erreur
	 * qui d�place une pi�ce hors de l'�chiquier, qui d�place pas de case vide,
	 * si la pi�ce revient sur sa position, si la pi�ce jou�e est une pi�ce adverse
	 * @param[in] ligneA, ligne qui correspond � la ligne actuelle de la pi�ce
	 * @param[in] colonneA, colonne qui correspond � la colonne actuelle de la pi�ce
	 * @param[in] ligneD, ligne qui correspond � la ligne actuelle de la pi�ce
	 * @param[in] colonneD, colonne qui correspond � la colonne actuelle de la pi�ce
	 * @param[in] c, couleur des pi�ces adverse
	 * @param[in] e, echiquier sur lequel la pi�ce joue
	 * @return le bool�en qui v�rifie si le d�placement comprend une erreur
	 */
	private boolean erreurD�placement(int ligneA, int colonneA, int ligneD, int colonneD, Couleur c, Echiquier e) {
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
	
	/*
	 * @brief M�thode qui v�rifie si l'utilisateur a bien demand� entre 0 et 2 pi�ces
	 * pour les pi�ces en double comme la tour, le fou et le cavalier
	 * @param[in] s, cha�ne de caract�re entr�e par l'utilisateur
	 * @return le bool�en qui v�rifie si la cha�ne poss�de une erreur
	 */
	public boolean erreurNbPi�cesDouble(String s) {
		if (s.length() != 1) {
			System.out.println("Vous devez saisir un chiffre entre 0 et 2");
			return true;
		}
		int nb = Integer.parseInt(s.substring(0, 1));
		if (nb >= 0 && nb <= 2) {
			return false;
		}
		return true;
	}
	
	/*
	 * @brief M�thode qui v�rifie si l'initialisation du roi n'est pas dans une case occup�e ou hors de l'�chiquier,
	 * ou � c�t� du roi adverse puis l'initialise gr�ce au constructeur du Roi et le place sur l'�chiquier
	 * @param[in] s, cha�ne de caract�res entr�e par l'utilisateur qui indique la ligne et la colonne du roi
	 * @param[in] c, couleur du joueur
	 * @param[in out] e, echiquier sur lequel le roi s'initialise
	 * @return le bool�en qui v�rifie si l'initialisation comprend une erreur
	 */
	public boolean initialiserRoi(String s, Couleur c, Echiquier e) {
		int ligne = Echiquier.MAX - Integer.parseInt(s.substring(1, 2));
		int colonne = s.charAt(0) - Echiquier.ConversASCII - 1;
		if (e.outOfBounds(ligne, colonne) || !e.estLibre(ligne, colonne)) {
			System.out.println("Choisissez une case valide");
			return false;
		}
		if (c == Couleur.BLANC) {
			Roi roiBLANC = new Roi(ligne, colonne, c, e);
			return true;
		} else {
			Roi roiNOIR = new Roi(ligne, colonne, c, e);
			if (roiNOIR.roiACot�(ligne, colonne, e)) {
				e.getPlateau()[ligne][colonne] = null;
				e.getListePi�ces().remove(roiNOIR);
				System.out.println("Vous ne pouvez pas placer le roi � cot� du roi adverse");
				return false;
			} else
				return true;
		}
	}

	/*
	 * @brief M�thode qui v�rifie si l'initialisation de la tour n'est pas dans une case occup�e
	 * ou hors de l'�chiquier, ou met en danger le roi adverse puis l'initialise
	 * gr�ce au constructeur de la tour et le place sur l'�chiquier
	 * @param[in] s, cha�ne de caract�res entr�e par l'utilisateur qui indique la ligne et la colonne de la tour
	 * @param[in] c, couleur du joueur
	 * @param[in out] e, echiquier sur lequel la tour s'initialise
	 * @return le bool�en qui v�rifie si l'initialisation comprend une erreur
	 */
	public boolean initialiserTour(String s, Couleur c, Echiquier e) {
		int ligne = Echiquier.MAX - Integer.parseInt(s.substring(1, 2));
		int colonne = s.charAt(0) - Echiquier.ConversASCII - 1;
		if (e.outOfBounds(ligne, colonne) || !e.estLibre(ligne, colonne)) {
			System.out.println("Choisissez une case valide");
			return false;
		}
		if (c == Couleur.BLANC) {
			Tour tourBLANCHE = new Tour(ligne, colonne, c, e);
			if (tourBLANCHE.metEnMatOuPat(e) || tourBLANCHE.metEnEchec(e)) {
				e.getPlateau()[ligne][colonne] = null;
				e.getListePi�ces().remove(tourBLANCHE);
				System.out.println("La tour ne peut pas mettre en �chec le roi adverse");
				return false;
			}
			return true;
		} else {
			Tour tourNOIRE = new Tour(ligne, colonne, c, e);
			if (tourNOIRE.metEnMatOuPat(e) || tourNOIRE.metEnEchec(e)) {
				e.getPlateau()[ligne][colonne] = null;
				e.getListePi�ces().remove(tourNOIRE);
				System.out.println("La tour ne peut pas mettre en �chec le roi adverse");
				return false;
			} else
				return true;
		}
	}
	
	/*
	 * @brief M�thode qui v�rifie si le coup jou� n'a pas d'erreur puis joue le coup de l'utilisateur
	 * @param[in] s, cha�ne de caract�res entr�e par l'utilisateur qui indique le coup qu'il veut jouer
	 * @param[in] c, couleur du joueur
	 * @param[in out] e, echiquier sur lequel le coup se joue
	 * @return le bool�en qui v�rifie si le coup comprend une erreur
	 */
	public boolean jouer(String s, Couleur c, Echiquier e) {
		if (erreurSaisie(s))
			return false;
		int colonneA = s.charAt(0) - Echiquier.ConversASCII - 1;
		int ligneA = Echiquier.MAX - Integer.parseInt(s.substring(1, 2));
		int colonneD = s.charAt(2) - Echiquier.ConversASCII - 1;
		int ligneD = Echiquier.MAX - Integer.parseInt(s.substring(3, 4));
		if (!erreurD�placement(ligneA, colonneA, ligneD, colonneD, c, e)) {
			if (e.getPlateau()[ligneA][colonneA].peutAllerEn(ligneD, colonneD, e)) {
				e.getPlateau()[ligneA][colonneA].d�placer(ligneD, colonneD, e);
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

	/*
	 * @brief M�thode qui v�rifie si l'utilisateur a demand� une fin
	 * @param[in] s, cha�ne de caract�res entr�e par l'utilisateur qui demande une fin
	 * @param[in] c, couleur du joueur qui demande l'abandon ou le match nul
	 * @return le bool�en qui v�rifie si le joueur a demand� une fin
	 */
	public boolean finDemand�e(String s, Couleur c) {
		Scanner sc = new Scanner(System.in);
		if (s.equals("abandonner")) {
			System.out.println("\n       Les " + c + "S ont abandonn� la partie");
			return finDePartie = true;
		}
		if (s.equals("match nul")) {
			System.out
					.println("Les " + c + "S ont demand� une proposition de nul\nMettez 'oui' ou 'non' pour accepter");
			s = sc.nextLine();
			if (s.equals("oui")) {
				System.out.println("\n		Match nul");
				return finDePartie = true;
			}
		}
		return false;
	}

	/*
	 * @brief M�thode qui renvoie le bool�en finDePartie qui devient true lorsque la partie se termine
	 * @return le bool�en qui v�rifie si la partie doit se terminer
	 */
	public boolean partieFinie() {
		return finDePartie;
	}
}
