package appli;

import java.util.Scanner;

import jeu.Echiquier;
import jeu.Partie;
import pièces.Pièce.Couleur;

public class Application {

	public static void main(String[] args) {
		Partie partie = new Partie();
		Scanner sc = new Scanner(System.in);
		Echiquier echiquier = new Echiquier();
		String s;
		StringBuilder message = new StringBuilder("");
		Couleur cJoueur = Couleur.BLANC;
		message.append("\n		JEU D'ECHECS - Fin de partie\n");
		message.append("Voici une simulation d'un fin de partie d'un jeu d'échecs\n");
		message.append("Vous avez la possibilité d'abandonner en écrivant 'abandonner'\n");
		message.append("Mais également de proposer un match nul en écrivant 'match nul'\n");
		message.append("Pour jouer vos coups mettez la colonne puis la ligne\n\nPar exemple: 'd5'\n");
		System.out.println(message);
		int i = 0;
		System.out.println(echiquier.toString());
		while (i < 2) {
			System.out.println("\n	Joueur " + cJoueur + ", où voulez vous placer votre roi ?");
			s = sc.nextLine();
			if (!partie.erreurInitialisation(s)) {
				if (partie.initialiserRoi(s, cJoueur, echiquier)) {
					cJoueur = cJoueur == Couleur.BLANC ? Couleur.NOIR : Couleur.BLANC;
					System.out.println(echiquier.toString());
					++i;
				}
			}
		}
		int j = 0, nbTourB = 0, nbTourN = 0;
		do {
			System.out.println("\n	Joueur " + cJoueur + ", combien de tour voulez vous ?");
			s = sc.nextLine();
			if (!partie.erreurNbPiècesDouble(s)) {
				if (cJoueur == Couleur.BLANC)
					nbTourB = Integer.parseInt(s.substring(0, 1));
				else
					nbTourN = Integer.parseInt(s.substring(0, 1));
				cJoueur = cJoueur == Couleur.BLANC ? Couleur.NOIR : Couleur.BLANC;
				++j;
			}
		} while (j < 2);
		j = 0;
		while (nbTourB + nbTourN != 0) {
			if (nbTourB == 0)
				cJoueur = Couleur.NOIR;
			System.out.println("\n	Joueur " + cJoueur + " où voulez vous placer votre Tour ?");
			s = sc.nextLine();
			if (!partie.erreurInitialisation(s)) {
				if (partie.initialiserTour(s, cJoueur, echiquier)) {
					if (cJoueur == Couleur.BLANC) {
						--nbTourB;
						if (nbTourN > 0)
							cJoueur = cJoueur == Couleur.BLANC ? Couleur.NOIR : Couleur.BLANC;
					} else {
						--nbTourN;
						if (nbTourB > 0)
							cJoueur = cJoueur == Couleur.BLANC ? Couleur.NOIR : Couleur.BLANC;
					}
				}
				System.out.println(echiquier.toString());
			}
		}
		cJoueur = Couleur.BLANC;
		while (!partie.partieFinie()) {
			System.out.println("\n             Tour des " + cJoueur.toString() + "S :");
			s = sc.nextLine();
			if (partie.finDemandée(s, cJoueur)) {
				System.out.println(echiquier.toString());
				break;
			}
			while (!partie.jouer(s, cJoueur, echiquier)) {
				System.out.println("\n             Tour des " + cJoueur.toString() + "S :");
				s = sc.nextLine();
				if (partie.finDemandée(s, cJoueur))
					break;
			}
			cJoueur = cJoueur == Couleur.BLANC ? Couleur.NOIR : Couleur.BLANC;
			System.out.println(echiquier.toString());
		}
	}
}