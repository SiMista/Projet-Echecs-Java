package jeu;

public class Roi extends Pièce {
  private char symbole;
  
  public Roi(int colonne, int ligne, Pièce.Couleur c, Echiquier e) {
    super(colonne, ligne, c, e);
    symbole = 'R';
  }
  
  public boolean peutAllerEn(int colonne, int ligne, Echiquier e) {
    if (getColonne() - colonne > 1 || getLigne() - ligne > 1)
      return false; 
    return true;
  }
  
  public char getSymbole() {
    if (getCouleur() == Pièce.Couleur.BLANC)
      return Character.toUpperCase(symbole); 
    return Character.toLowerCase(symbole);
  }
}
