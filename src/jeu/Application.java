package jeu;

import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Echiquier echiquier = new Echiquier();
		Roi roi = new Roi(0, 4, Pièce.Couleur.BLANC, echiquier);
		Tour tour = new Tour(0, 1, Pièce.Couleur.BLANC, echiquier);

		System.out.println(echiquier.toString());
		String s = sc.nextLine();
		while (s != "fin") {
			echiquier.jouer(s);
			System.out.println(echiquier.toString());
			s = sc.nextLine();
		}
	}
}
