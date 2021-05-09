package jeu;

public class Echiquier {
	public static final int MAX = 8;
	private Pièce plateau[][];

	public Echiquier() {
		plateau = new Pièce[MAX][MAX];
		for (int i = 0; i < MAX; ++i) {
			for (int j = 0; j < MAX; ++j) {
				plateau[i][j] = null;
			}
		}
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < MAX; ++i) {
			for (int j = 0; j < MAX; ++j) {
				if (plateau[i][j] == null)
					s += "O";
				else
					s += plateau[i][j].getSymbole();
			}
			s += "\n";
		}
		return fdp;
	}

	public void jouer(String s) {
		int colonneActuelle = s.charAt(0) - 96;
		int ligneActuelle = MAX - s.charAt(1) + 1;
		int colonneDestination = s.charAt(2) - 96;
		int ligneDestination  = MAX - s.charAt(3) + 1;

		if (!((1 <= colonneActuelle && colonneActuelle <= 8) && (1 <= ligneActuelle && ligneActuelle <= 8))) {
			return;
		}
		if (plateau[colonneActuelle][ligneActuelle] != null) {
			plateau[colonneActuelle][ligneActuelle].peutAllerEn(colonneDestination, ligneDestination, this);
		}
		s.charAt(2) ;
		s.charAt(3) ;
	}

	public Pièce[][] getPlateau() {
		return plateau;
	}
}
