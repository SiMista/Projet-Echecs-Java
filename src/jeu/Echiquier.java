package jeu;

import jeu.Echiquier;

public class Echiquier {
  public static final int MAX = 8;
  
  private Pièce[][] plateau = new Pièce[8][8];
  
  public Echiquier() {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++)
        this.plateau[i][j] = null; 
    } 
  }
  
  public String toString() {
    String s = "    a    b    c    d    e    f    g    h\n";
    s = String.valueOf(s) + "   ---  ---  ---  ---  ---  ---  ---  ---\n";
    for (int i = 0; i < 8; i++) {
      s = String.valueOf(s) + (8 - i) + " ";
      for (int j = 0; j < 8; j++) {
        s = String.valueOf(s) + '|';
        if (this.plateau[i][j] == null) {
          s = String.valueOf(s) + "   ";
        } else {
          s = String.valueOf(s) + " " + this.plateau[i][j].getSymbole() + " ";
        } 
        s = String.valueOf(s) + '|';
      } 
      s = String.valueOf(s) + " " + (8 - i);
      s = String.valueOf(s) + "\n   ---  ---  ---  ---  ---  ---  ---  ---\n";
    } 
    s = String.valueOf(s) + "    a    b    c    d    e    f    g    h\n";
    return s;
  }
  
  public void jouer(String s) {
    int colonneActuelle = s.charAt(0) - 96;
    int ligneActuelle = 8 - s.charAt(1) + 1;
    int colonneDestination = s.charAt(2) - 96;
    int ligneDestination = 8 - s.charAt(3) + 1;
    if (1 > colonneActuelle || colonneActuelle > 8 || 1 > ligneActuelle || ligneActuelle > 8)
      return; 
    if (this.plateau[colonneActuelle][ligneActuelle] != null)
      this.plateau[colonneActuelle][ligneActuelle].peutAllerEn(colonneDestination, ligneDestination, this); 
    s.charAt(2);
    s.charAt(3);
  }
  
  public Pièce[][] getPlateau() {
    return this.plateau;
  }
}
