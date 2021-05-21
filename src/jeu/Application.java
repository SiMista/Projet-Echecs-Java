package jeu;

import java.util.Scanner;

import Pièces.Pièce;
import Pièces.Roi;
import Pièces.Tour;
import Pièces.Pièce.Couleur;

public class Application {
	public static void main(String[] args) {
		Partie partie = new Partie();
		Scanner sc = new Scanner(System.in);
		Echiquier echiquier = new Echiquier();
		String s;
		Couleur cJoueur = Couleur.BLANC;
		int i = 0;
		while (i < 2) {
			System.out.println("\n		Joueur " + cJoueur + " où voulez vous placer votre roi ?");
			s = sc.nextLine();
			if (!partie.erreurInitialisation(s)) {
				int ligne = Echiquier.MAX - Integer.parseInt(s.substring(1, 2));
				int colonne = s.charAt(0) - Echiquier.ConversASCII - 1;
				if (!echiquier.outOfBounds(ligne, colonne) && echiquier.estLibre(ligne, colonne) /*&&  !seraEnEchec(ligne, colonne, echiquier)*/) {
					Roi rois = new Roi(ligne, colonne, cJoueur, echiquier);
					cJoueur = cJoueur == Couleur.BLANC ? Couleur.NOIR : Couleur.BLANC;
					System.out.println(echiquier.toString());
					++i;
				}
				else System.out.println("La case selectionnée n'est pas valide");
			}
		}


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