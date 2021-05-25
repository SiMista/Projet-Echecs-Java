package joueurs;

import java.util.Scanner;

import jeu.Echiquier;
import jeu.Partie;
import pièces.Pièce.Couleur;

/*
 * Une instance de cette classe est un joueur d'un couleur NOIR ou BLANC qui joue les coups de l'utilisateur
 * @author DE ALMEIDA Jules, DEIVA Siméon, SIVANAND Nirussan
 */
public class Joueur {
	private Couleur couleur;
	
	public Joueur(Couleur c) {
		couleur = c;
	}
	
	/*
	 * @brief Méthode qui vérifie si le coup joué n'a pas d'erreur puis joue le coup de l'utilisateur
	 * @param[in] s, chaîne de caractères entrée par l'utilisateur qui indique le coup qu'il veut jouer
	 * @param[in] c, couleur du joueur
	 * @param[in out] e, echiquier sur lequel le coup se joue
	 * @return le booléen qui vérifie si le coup comprend une erreur
	 */
	public boolean joue(String s, Partie p, Echiquier e) {
		if (p.erreurSaisie(s))
			return false;
		int colonneA = s.charAt(0) - Echiquier.ConversASCII - 1;
		int ligneA = Echiquier.MAX - Integer.parseInt(s.substring(1, 2));
		int colonneD = s.charAt(2) - Echiquier.ConversASCII - 1;
		int ligneD = Echiquier.MAX - Integer.parseInt(s.substring(3, 4));
		if (!p.erreurDéplacement(ligneA, colonneA, ligneD, colonneD, couleur, e)) {
			if (e.getPlateau()[ligneA][colonneA].peutAllerEn(ligneD, colonneD, e)) {
				e.getPlateau()[ligneA][colonneA].déplacer(ligneD, colonneD, e);
				if (e.estLibre(ligneD, colonneD)) {
					return false;
				}
				if (e.getPlateau()[ligneD][colonneD].metEnEchec(e))
					System.out.println("Vous avez mis le roi adverse en situation d'echec");
				// System.out.println("Le coup a marché !\n");
				if (e.getPlateau()[ligneD][colonneD].metEnMatOuPat(e)) {
					p.setFinDePartie(true);
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
	public boolean demandeFin(String s, Partie p) {
		Scanner sc = new Scanner(System.in);
		if (s.equals("abandonner")) {
			System.out.println("\n       Les " + couleur + "S ont abandonné la partie");
			 p.setFinDePartie(true);
			 return p.partieFinie();
		}
		if (s.equals("match nul")) {
			System.out
					.println("Les " + couleur + "S ont demandé une proposition de nul\nMettez 'oui' ou 'non' pour accepter");
			s = sc.nextLine();
			if (s.equals("oui")) {
				System.out.println("\n		Match nul");
				 p.setFinDePartie(true);
				 return p.partieFinie();	
			}
		}
		return false;
	}
	
	/*
	 * @brief getter qui renvoie la couleur de la pièce
	 * @return une couleur correspondant à la couleur de la pièce
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
	/*
	 * @brief setter qui modifie la couleur du Joueur pour celle entrée en parmaètre
	 * @param[in] c, couleur qui va être appliquée à la couleur du joueur
	 */
	public void setCouleur(Couleur c) {
		couleur = c;
	}
}

