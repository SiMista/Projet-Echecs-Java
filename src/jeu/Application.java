package jeu;

import java.util.Scanner;

import jeu.Pièce.Couleur;

public class Application {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Echiquier echiquier = new Echiquier();
		Roi roiKKK = new Roi(1, 4, Pièce.Couleur.BLANC, echiquier);
		Tour tourKKK = new Tour(0, 3, Pièce.Couleur.BLANC, echiquier);
		Roi roiRENOI = new Roi(7, 1, Pièce.Couleur.NOIR, echiquier);
		Tour tourRENOI = new Tour(7, 6, Pièce.Couleur.NOIR, echiquier);
		Tour tourNWORD = new Tour(7, 2, Pièce.Couleur.NOIR, echiquier);

		Couleur joueur = Couleur.BLANC;

		System.out.println(echiquier.toString());
		System.out.println("Tour des " + joueur.toString() + "S :");
		String s;
		
		while (true) {
			s = sc.nextLine();
			while (!echiquier.jouer(s, joueur)) {
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