package jeu;

public class Echiquier {
	public static final int MAX = 8;
	private Pi�ce plateau[][];

	public Echiquier() {
		plateau = new Pi�ce[MAX][MAX];
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
		return s;
	}
	
	public Pi�ce[][] getPlateau(){
		return plateau;
	}
}
