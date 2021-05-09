package jeu;

import java.util.Scanner;


public class Application {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Echiquier echiquier = new Echiquier();
    Roi roi = new Roi(5, 1, Pièce.Couleur.BLANC, echiquier);
    System.out.println(echiquier.toString());
    String s = sc.nextLine();
    echiquier.jouer(s);
  }
}
