package jeu;

import java.util.Scanner;
import pièces.Roi;
import pièces.Tour;
import pièces.Pièce.Couleur;

public class Partie {
	boolean finDePartie = false;

	/*
	 * @brief Méthode qui vérifie si l'utilisateur a saisie des valeurs cohérentes pendant l'initialisation
	 * @param[in] s, chaîne de caractère entrée par l'utilisateur
	 * @return le booléen qui vérifie si la chaîne possède une erreur
	 */
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

	/*
	 * @brief Méthode qui vérifie si l'utilisateur a saisie des valeurs cohérentes pendant l'initialisation
	 * @param[in] s, chaîne de caractère entrée par l'utilisateur
	 * @return le booléen qui vérifie si la chaîne possède une erreur
	 */
	private boolean erreurSaisie(String s) {
		String problème = "";
		boolean erreur = false;

		if (s.equals("match nul")) {
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

	/*
	 * @brief Méthode qui vérifie si le déplacement a une erreur
	 * qui déplace une pièce hors de l'échiquier, qui déplace pas de case vide,
	 * si la pièce revient sur sa position, si la pièce jouée est une pièce adverse
	 * @param[in] ligneA, ligne qui correspond à la ligne actuelle de la pièce
	 * @param[in] colonneA, colonne qui correspond à la colonne actuelle de la pièce
	 * @param[in] ligneD, ligne qui correspond à la ligne actuelle de la pièce
	 * @param[in] colonneD, colonne qui correspond à la colonne actuelle de la pièce
	 * @param[in] c, couleur des pièces adverse
	 * @param[in] e, echiquier sur lequel la pièce joue
	 * @return le booléen qui vérifie si le déplacement comprend une erreur
	 */
	private boolean erreurDéplacement(int ligneA, int colonneA, int ligneD, int colonneD, Couleur c, Echiquier e) {
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
	
	/*
	 * @brief Méthode qui vérifie si l'utilisateur a bien demandé entre 0 et 2 pièces
	 * pour les pièces en double comme la tour, le fou et le cavalier
	 * @param[in] s, chaîne de caractère entrée par l'utilisateur
	 * @return le booléen qui vérifie si la chaîne possède une erreur
	 */
	public boolean erreurNbPiècesDouble(String s) {
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
	 * @brief Méthode qui vérifie si l'initialisation du roi n'est pas dans une case occupée ou hors de l'échiquier,
	 * ou à côté du roi adverse puis l'initialise grâce au constructeur du Roi et le place sur l'échiquier
	 * @param[in] s, chaîne de caractères entrée par l'utilisateur qui indique la ligne et la colonne du roi
	 * @param[in] c, couleur du joueur
	 * @param[in out] e, echiquier sur lequel le roi s'initialise
	 * @return le booléen qui vérifie si l'initialisation comprend une erreur
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
			if (roiNOIR.roiACoté(ligne, colonne, e)) {
				e.getPlateau()[ligne][colonne] = null;
				e.getListePièces().remove(roiNOIR);
				System.out.println("Vous ne pouvez pas placer le roi à coté du roi adverse");
				return false;
			} else
				return true;
		}
	}

	/*
	 * @brief Méthode qui vérifie si l'initialisation de la tour n'est pas dans une case occupée
	 * ou hors de l'échiquier, ou met en danger le roi adverse puis l'initialise
	 * grâce au constructeur de la tour et le place sur l'échiquier
	 * @param[in] s, chaîne de caractères entrée par l'utilisateur qui indique la ligne et la colonne de la tour
	 * @param[in] c, couleur du joueur
	 * @param[in out] e, echiquier sur lequel la tour s'initialise
	 * @return le booléen qui vérifie si l'initialisation comprend une erreur
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
				e.getListePièces().remove(tourBLANCHE);
				System.out.println("La tour ne peut pas mettre en échec le roi adverse");
				return false;
			}
			return true;
		} else {
			Tour tourNOIRE = new Tour(ligne, colonne, c, e);
			if (tourNOIRE.metEnMatOuPat(e) || tourNOIRE.metEnEchec(e)) {
				e.getPlateau()[ligne][colonne] = null;
				e.getListePièces().remove(tourNOIRE);
				System.out.println("La tour ne peut pas mettre en échec le roi adverse");
				return false;
			} else
				return true;
		}
	}
	
	/*
	 * @brief Méthode qui vérifie si le coup joué n'a pas d'erreur puis joue le coup de l'utilisateur
	 * @param[in] s, chaîne de caractères entrée par l'utilisateur qui indique le coup qu'il veut jouer
	 * @param[in] c, couleur du joueur
	 * @param[in out] e, echiquier sur lequel le coup se joue
	 * @return le booléen qui vérifie si le coup comprend une erreur
	 */
	public boolean jouer(String s, Couleur c, Echiquier e) {
		if (erreurSaisie(s))
			return false;
		int colonneA = s.charAt(0) - Echiquier.ConversASCII - 1;
		int ligneA = Echiquier.MAX - Integer.parseInt(s.substring(1, 2));
		int colonneD = s.charAt(2) - Echiquier.ConversASCII - 1;
		int ligneD = Echiquier.MAX - Integer.parseInt(s.substring(3, 4));
		if (!erreurDéplacement(ligneA, colonneA, ligneD, colonneD, c, e)) {
			if (e.getPlateau()[ligneA][colonneA].peutAllerEn(ligneD, colonneD, e)) {
				e.getPlateau()[ligneA][colonneA].déplacer(ligneD, colonneD, e);
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

	/*
	 * @brief Méthode qui vérifie si l'utilisateur a demandé une fin
	 * @param[in] s, chaîne de caractères entrée par l'utilisateur qui demande une fin
	 * @param[in] c, couleur du joueur qui demande l'abandon ou le match nul
	 * @return le booléen qui vérifie si le joueur a demandé une fin
	 */
	public boolean finDemandée(String s, Couleur c) {
		Scanner sc = new Scanner(System.in);
		if (s.equals("abandonner")) {
			System.out.println("\n       Les " + c + "S ont abandonné la partie");
			return finDePartie = true;
		}
		if (s.equals("match nul")) {
			System.out
					.println("Les " + c + "S ont demandé une proposition de nul\nMettez 'oui' ou 'non' pour accepter");
			s = sc.nextLine();
			if (s.equals("oui")) {
				System.out.println("\n		Match nul");
				return finDePartie = true;
			}
		}
		return false;
	}

	/*
	 * @brief Méthode qui renvoie le booléen finDePartie qui devient true lorsque la partie se termine
	 * @return le booléen qui vérifie si la partie doit se terminer
	 */
	public boolean partieFinie() {
		return finDePartie;
	}
}
