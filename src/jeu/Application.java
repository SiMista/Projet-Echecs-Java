package jeu;

import java.util.Scanner;

import jeu.Pièce.Couleur;

public class Application {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Echiquier echiquier = new Echiquier();
		String s;
		Couleur joueur = Couleur.BLANC;

		Roi roiBLANC = new Roi(0, 0, Pièce.Couleur.BLANC, echiquier);
		Tour tourBLANC = new Tour(0, 1, Pièce.Couleur.BLANC, echiquier);
		Tour tourBLANChe = new Tour(2, 0, Pièce.Couleur.BLANC, echiquier);
		Roi roiRENOI = new Roi(4, 4, Pièce.Couleur.NOIR, echiquier);
		Tour tourRENOI = new Tour(7, 6, Pièce.Couleur.NOIR, echiquier);
		Tour tourNWORD = new Tour(7, 0, Pièce.Couleur.NOIR, echiquier);

		/*
		 * System.out.println("Joueur BLANC, combien de Tour voulez vous ?"); s =
		 * sc.nextLine(); while (s.equals("fin")) { echiquier.initialiser(s, joueur); }
		 */
		System.out.println(echiquier.toString());

		while (!echiquier.partieFinie()) {
			System.out.println("\n             Tour des " + joueur.toString() + "S :");
			s = sc.nextLine();
			while (!echiquier.jouer(s, joueur)) {
				System.out.println("\n             Tour des " + joueur.toString() + "S :");
				s = sc.nextLine();
				 if(echiquier.finDuGame(s, joueur))
					 break;
			}
			if (joueur == Couleur.BLANC)
				joueur = Couleur.NOIR;
			else
				joueur = Couleur.BLANC;
			System.out.println(echiquier.toString());
		}
	}
}