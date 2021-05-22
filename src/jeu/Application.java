package jeu;

import java.util.Scanner;

import pièces.Pièce;
import pièces.Roi;
import pièces.Tour;
import pièces.Pièce.Couleur;

public class Application {



	public static void main(String[] args) {
		Partie partie = new Partie();
		Scanner sc = new Scanner(System.in);
		Echiquier echiquier = new Echiquier();
		String s;
		Couleur cJoueur = Couleur.BLANC;

		int i = 0;
		while (i < 2) {
			System.out.println("\n	Joueur " + cJoueur + ", où voulez vous placer votre roi ?");
			s = sc.nextLine();
			if (!partie.erreurInitialisation(s)) {
				if (partie.initialiserRoi(s, cJoueur, echiquier, i)) {
					cJoueur = cJoueur == Couleur.BLANC ? Couleur.NOIR : Couleur.BLANC;
					System.out.println(echiquier.toString());
					++i;
				}
			}
		}
		
		i = 0;
		while (i < 2) {
			System.out.println("\n	Joueur " + cJoueur + ", combien de tour voulez vous ?");
			s = sc.nextLine();
			if (!partie.erreurInitialisation(s)) {
				if (partie.initialiserPièce(s, cJoueur, echiquier)) {
					cJoueur = cJoueur == Couleur.BLANC ? Couleur.NOIR : Couleur.BLANC;
					System.out.println(echiquier.toString());
					++i;
				}
			}
		}
		
		/*
		 * Roi roiBLANC = new Roi(0, 0, Pièce.Couleur.BLANC, echiquier); Tour tourBLANC
		 * = new Tour(0, 1, Pièce.Couleur.BLANC, echiquier); Roi roiRENOI = new Roi(4,
		 * 4, Pièce.Couleur.NOIR, echiquier); Tour tourRENOI = new Tour(7, 6,
		 * Pièce.Couleur.NOIR, echiquier); Tour tourNWORD = new Tour(7, 2,
		 * Pièce.Couleur.NOIR, echiquier); System.out.println(echiquier.toString());
		 */

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