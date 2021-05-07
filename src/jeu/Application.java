package jeu;

import java.util.Scanner;

import jeu.Pièce.Couleur;

public class Application {

	public static void main(String[] args) {
		String s;
		Scanner sc = new Scanner(System.in);
		Echiquier echiquier = new Echiquier();
		Roi roi = new Roi(5, 1, Couleur.BLANC, echiquier);
		System.out.println(echiquier.toString());
		char c = 'a';
		int i = c - 96;
		System.out.println(i);
		s = sc.nextLine();
		echiquier.jouer(s);
	}
}
