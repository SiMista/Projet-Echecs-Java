package jeu;

import java.util.Scanner;

import jeu.Pièce.Couleur;

public class Application {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Echiquier echiquier = new Echiquier();
		Roi roiBLANC = new Roi(0, 0, Pièce.Couleur.BLANC, echiquier);
		Tour tourBLANC = new Tour(0, 3, Pièce.Couleur.BLANC, echiquier);
		Roi roiRENOI = new Roi(4, 4, Pièce.Couleur.NOIR, echiquier);
		Tour tourRENOI = new Tour(7, 6, Pièce.Couleur.NOIR, echiquier);
		Tour tourNWORD = new Tour(7, 1, Pièce.Couleur.NOIR, echiquier);

		Couleur joueur = Couleur.BLANC;

		System.out.println(echiquier.toString());
		System.out.println("Tour des " + joueur.toString() + "S :");
		String s;

		while (true) {
			s = sc.nextLine();
			while (!echiquier.jouer(s, joueur)) {
				System.out.println("Tour des " + joueur.toString() + "S :");
				s = sc.nextLine();
			}
			if (joueur == Couleur.BLANC)
				joueur = Couleur.NOIR;
			else
				joueur = Couleur.BLANC;
			System.out.println(echiquier.toString());
			System.out.println("Tour des " + joueur.toString() + "S :");
			
		}
	}
}