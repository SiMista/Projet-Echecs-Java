package jeu;

import java.util.Scanner;

import pi�ces.Pi�ce;
import pi�ces.Roi;
import pi�ces.Tour;
import pi�ces.Pi�ce.Couleur;

public class Application {

	public static void main(String[] args) {
		Partie partie = new Partie();
		Scanner sc = new Scanner(System.in);
		Echiquier echiquier = new Echiquier();
		String s;
		Couleur cJoueur = Couleur.BLANC;

		int i = 0;
		System.out.println(echiquier.toString());
		while (i < 2) {
			System.out.println("\n	Joueur " + cJoueur + ", o� voulez vous placer votre roi ?");
			s = sc.nextLine();
			if (!partie.erreurInitialisation(s)) {
				if (partie.initialiserRoi(s, cJoueur, echiquier, i)) {
					cJoueur = cJoueur == Couleur.BLANC ? Couleur.NOIR : Couleur.BLANC;
					System.out.println(echiquier.toString());
					++i;
				}
			}
		}

		int j = 0, nbTB = 0, nbTN = 0;
		do {
			System.out.println("\n	Joueur " + cJoueur + ", combien de tour voulez vous ?");
			s = sc.nextLine();
			if (!partie.erreurNbPi�ces(s)) {
				if (cJoueur == Couleur.BLANC)
					nbTB = Integer.parseInt(s.substring(0, 1));
				else
					nbTN = Integer.parseInt(s.substring(0, 1));
				cJoueur = cJoueur == Couleur.BLANC ? Couleur.NOIR : Couleur.BLANC;
				++j;
			}
		} while (j < 2);
		j = 0;
		while (nbTB + nbTN != 0) {
			if (nbTB == 0)
				cJoueur = Couleur.NOIR;
			System.out.println("\n	Joueur " + cJoueur + " o� voulez vous placer votre Tour ?");
			s = sc.nextLine();
			if (!partie.erreurInitialisation(s)) {
				if (partie.initialiserPi�ce(s, cJoueur, echiquier)) {
					if (cJoueur == Couleur.BLANC) {
						--nbTB;
						if (nbTN > 0)
							cJoueur = cJoueur == Couleur.BLANC ? Couleur.NOIR : Couleur.BLANC;
					} else {
						--nbTN;
						if (nbTB > 0)
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
			if (partie.finDemand�e(s, cJoueur)) {
				System.out.println(echiquier.toString());
				break;
			}
			while (!partie.jouer(s, cJoueur, echiquier)) {
				System.out.println("\n             Tour des " + cJoueur.toString() + "S :");
				s = sc.nextLine();
				if (partie.finDemand�e(s, cJoueur))
					break;
			}
			cJoueur = cJoueur == Couleur.BLANC ? Couleur.NOIR : Couleur.BLANC;
			System.out.println(echiquier.toString());
		}
	}
}