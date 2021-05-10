package jeu;

public class Roi extends Pi�ce {
  private char symbole;
  
  public Roi(int ligne, int colonne, Pi�ce.Couleur c, Echiquier e) {
    super(ligne, colonne, c, e);
    symbole = 'R';
  }
  
  public boolean peutAllerEn(int ligne, int colonne, Echiquier e) {
    if (Math.abs(getLigne() - ligne) > 1 || Math.abs(getColonne() - colonne) > 1)
      return false; 
    return true;
  }
  
  public char getSymbole() {
    if (getCouleur() == Pi�ce.Couleur.BLANC)
      return Character.toUpperCase(symbole); 
    return Character.toLowerCase(symbole);
  }
  
}
