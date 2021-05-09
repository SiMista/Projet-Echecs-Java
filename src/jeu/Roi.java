package jeu;

public class Roi extends Pi�ce {
  private char symbole;
  
  public Roi(int colonne, int ligne, Pi�ce.Couleur c, Echiquier e) {
    super(colonne, ligne, c, e);
    this.symbole = 'R';
  }
  
  public boolean peutAllerEn(int colonne, int ligne, Echiquier e) {
    if (getColonne() - colonne > 1 || getLigne() - ligne > 1)
      return false; 
    return true;
  }
  
  public char getSymbole() {
    if (getCouleur() == Pi�ce.Couleur.BLANC)
      return Character.toUpperCase(this.symbole); 
    return Character.toLowerCase(this.symbole);
  }
}
