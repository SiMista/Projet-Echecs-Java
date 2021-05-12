package jeu;

import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Echiquier echiquier = new Echiquier();
		Roi roi = new Roi(0, 4, Pi�ce.Couleur.BLANC, echiquier);
		Tour tour = new Tour(0, 3, Pi�ce.Couleur.BLANC, echiquier);
		//Roi roiRENOI = new Roi(7, 3, Pi�ce.Couleur.NOIR, echiquier);
		Tour tourRENOI = new Tour(7, 6, Pi�ce.Couleur.NOIR, echiquier);
		Tour tourNWORD = new Tour(7, 2, Pi�ce.Couleur.NOIR, echiquier);

		System.out.println(echiquier.toString());
		String s = sc.nextLine();

		while (true) {
			echiquier.jouer(s);
			System.out.println(echiquier.toString());
			s = sc.nextLine();
		}
	}
}
