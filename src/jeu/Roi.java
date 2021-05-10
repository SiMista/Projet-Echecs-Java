package jeu;

public class Roi extends Pièce {
  private char symbole;
  
  public Roi(int ligne, int colonne, Pièce.Couleur c, Echiquier e) {
    super(ligne, colonne, c, e);
    symbole = 'R';
  }
  
  public boolean peutAllerEn(int ligne, int colonne, Echiquier e) {
    if (Math.abs(getLigne() - ligne) > 1 || Math.abs(getColonne() - colonne) > 1)
      return false; 
    return true;
  }
  
  public char getSymbole() {
    if (getCouleur() == Pièce.Couleur.BLANC)
      return Character.toUpperCase(symbole); 
    return Character.toLowerCase(symbole);
  }
  
}
