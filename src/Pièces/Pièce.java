package pièces;

import java.util.Scanner;

import jeu.Echiquier;
import jeu.IPièce;
import jeu.Partie;

public abstract class Pièce implements IPièce {
	private int ligne, colonne;
	private Couleur couleur;
	private char symbole;

	public enum Couleur {
		BLANC, NOIR
	};
	
	/*
	 * @brief Le constructeur de la Pièce, sert à placer la pièce suite à la demande de l'utilisateur
	 * @param[in] ligne, un entier qui va être la ligne de la pièce dans l'échiquier
	 * @param[in] colonne, un entier qui va être la colonne de la pièce dans l'échiquier
	 * @param[in] c, la couleur qui va être la couleur de la pièce
	 * @param[in out] e, l'échiquier sur lequel se place la pièce 
	 */
	public Pièce(int ligne, int colonne, Couleur c, Echiquier e) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.couleur = c;
		e.getListePièces().add(this);
		e.placer(this);
	}
	
	/*
	 * @brief Méthode servant à déplacer les pièces à la case entrée en paramètre
	 * @param[in out] ligneD, ligne de la case de destination de la pièce
	 * @param[in out] colonneD, colonne de la case de destination de la pièce
	 * @param[in out] e, echiquier sur lequel se déplace la pièce
	 */
	public void déplacer(int ligneD, int colonneD, Echiquier e) {
		if (!e.estLibre(ligneD, colonneD))
			e.getPlateau()[ligneD][colonneD].estMangé(ligneD, colonneD, e);
		e.getPlateau()[ligneD][colonneD] = this;
		e.getPlateau()[getLigne()][getColonne()] = null;
		setLigne(ligneD);
		setColonne(colonneD);
	}
	
	/*
	 * @brief Méthode abstraite, qui détermine la possiblité d'aller à une case propre
	 * aux mouvements de chaque pièce
	 * @param[in] ligneD, ligne de la case de destination de la pièce
	 * @param[in] colonneD, colonne de la case de destination de la pièce
	 * @param[in] e, echiquier sur lequel la pièce joue
	 * @return le booléen qui vérifie si la pièce peut bien aller à la case de destination
	 */
	public abstract boolean peutAllerEn(int ligne, int colonne, Echiquier e);

	/*
	 * @brief Méthode supprime une pièce mangée
	 * @param[in] ligne, ligne de la pièce mangée
	 * @param[in] colonne, colonne de la pièce mangée
	 * @param[in out] e, echiquier sur lequel se déplace la pièce
	 */
	public void estMangé(int ligne, int colonne, Echiquier e) {
		System.out.println("La pièce " + getClass().getSimpleName() + " a été mangé");
		e.getListePièces().remove(e.getPlateau()[ligne][colonne]);
	}

	/*
	 * @brief Méthode qui vérifie si une pièce peut bien manger la pièce de la case destination
	 * @param[in] ligne, ligne de la pièce qui doit être mangée
	 * @param[in] colonne, colonne de la pièce qui doit être mangée
	 * @param[in] e, echiquier sur lequel les pièces jouent
	 * @return le booléen qui vérifie si la pièce peut manger la pièce de la case destination
	 */
	public boolean peutManger(int ligne, int colonne, Echiquier e) {
		if (e.getPlateau()[ligne][colonne].getCouleur() != this.getCouleur()) {
			return true;
		} else
			return false;
	}
	
	/*
	 * @brief Méthode qui invoque la méthode du roi allié lorsqu'il est en échec
	 * @param[in] e, echiquier sur lequel les pièces jouent
	 * @return le booléen qui vérifie si le roi est en échec
	 */
	public boolean estEnEchec(Echiquier e) {
		return false;
	}
	
	/*
	 * @brief Méthode qui vérfie si la pièce jouée met en échec le roi adverse
	 * @param[in] e, echiquier sur lequel joue la pièce
	 * @return le booléen qui vérifie si la pièce met en échec
	 */
	public boolean metEnEchec(Echiquier e) {
		for (Pièce p : e.getListePièces()) {
			if (Character.toLowerCase(p.getSymbole()) != 'r'
					&& p.peutAllerEn(getRoiAdverse(e).getLigne(), getRoiAdverse(e).getColonne(), e)
					&& p.getCouleur() != getRoiAdverse(e).getCouleur())
				return true;
		}
		return false;
	}

	/*
	 * @brief Méthode qui vérifie si la pièce jouée met en Pat ou met en Mat le roi adverse
	 * @param[in] e, echiquier sur lequel joue la pièce
	 * @return le booléen qui vérifie si la pièce met en mat ou pat
	 */
	public boolean metEnMatOuPat(Echiquier e) {
		for (Pièce piècesAdv : e.getListePièces()) {
			if (piècesAdv.getCouleur() != this.getCouleur()) {
				for (int i = -1; i <= 1; ++i) {
					for (int j = -1; j <= 1; ++j) {
						if (!piècesAdv.peutPasBouger(piècesAdv.getLigne() + i, piècesAdv.getColonne() + j, e)) {
							return false;
						}
					}
				}
			}
		}
		if (getRoiAdverse(e).estEnEchec(e)) {
			for (Pièce piècesAdv : e.getListePièces()) {
				if (piècesAdv.getCouleur() != this.getCouleur() && Character.toLowerCase(piècesAdv.getSymbole()) != 'r') {
					for (int i = -Echiquier.MAX + 1; i <= Echiquier.MAX; ++i) {
						for (int j = -Echiquier.MAX + 1; j <= Echiquier.MAX; ++j) {
							if (!piècesAdv.peutPasBouger(piècesAdv.getLigne() + i,piècesAdv.getColonne() + j, e)) {
								return false;
							}
						}
					}
				}
			}
			System.out.println("Vous avez mis le roi adverse en échec et mat");
			System.out.println("\n       Les " + getRoi(e).getCouleur() + "S gagnent la partie");
			return true;
		}
		System.out.println("Vous avez mis le roi adverse en Pat");
		System.out.println("\n		Match nul");
		return true;

	}

	/*
	 * @brief Méthode qui vérifie si la pièce ne peut pas se déplacer à la case choisie
	 * @param[in] ligneD, ligne de la case de destination
	 * @param[in] colonneD, colonne de la case de destination
	 * @param[in] e, echiquier sur lequel joue la pièce
	 * @return le booléen qui vérifie si la pièce ne peut pas bouger
	 */
	public boolean peutPasBouger(int ligneD, int colonneD, Echiquier e) {
		if (!e.outOfBounds(ligneD, colonneD)) {
			if (!peutAllerEn(ligneD, colonneD, e)) {
				return true;
			} else
				return false;
		}
		return true;
	}

	/*
	 * @brief getter qui renvoie le roi allié
	 * @param[in] e, echiquier sur lequel joue la pièce
	 * @return le roi allié
	 */
	public Pièce getRoi(Echiquier e) {
		for (Pièce roi : e.getListePièces()) {
			if (roi.getCouleur() == getCouleur() && Character.toLowerCase(roi.getSymbole()) == 'r')
				return roi;
		}
		return null;
	}

	/*
	 * @brief getter qui renvoie le roi adverse
	 * @param[in out] e, echiquier sur lequel joue la pièce
	 * @return le roi adverse
	 */
	public Pièce getRoiAdverse(Echiquier e) {
		for (Pièce roi : e.getListePièces()) {
			if (roi.getCouleur() != getCouleur() && Character.toLowerCase(roi.getSymbole()) == 'r')
				return roi;
		}
		return null;
	}

	/*
	 * @brief Méthode qui vérifie si le déplacement de la pièce 
	 * @param[in] ligneD, ligne de la case de destination
	 * @param[in] colonneD, colonne de la case de destination
	 * @param[in out] e, echiquier sur lequel joue la pièce
	 * @return le booléen qui vérifie si la pièce ne peut pas bouger
	 */
	public boolean échecSimulé(int ligneD, int colonneD, Echiquier e) {
		Echiquier eTmp = new Echiquier();
		eTmp.getPlateau()[ligneD][colonneD] = e.getPlateau()[ligneD][colonneD];
		e.getPlateau()[ligneD][colonneD] = this;
		e.getPlateau()[getLigne()][getColonne()] = null;
		if (e.getPlateau()[getRoi(e).getLigne()][getRoi(e).getColonne()].estEnEchec(e)) {
			e.getPlateau()[getLigne()][getColonne()] = this;
			e.getPlateau()[ligneD][colonneD] = null;
			e.getPlateau()[ligneD][colonneD] = eTmp.getPlateau()[ligneD][colonneD];
			if (!e.estLibre(ligneD, colonneD))
				return !peutManger(ligneD, colonneD, e);
			return true;
		}
		e.getPlateau()[getLigne()][getColonne()] = this;
		e.getPlateau()[ligneD][colonneD] = null;
		e.getPlateau()[ligneD][colonneD] = eTmp.getPlateau()[ligneD][colonneD];
		return false;
	}
	
	/*
	 * @brief getter qui renvoie la ligne actuelle de la pièce
	 * @return un int correspondant à la ligne de la pièce
	 */
	public int getLigne() {
		return ligne;
	}

	/*
	 * @brief getter qui renvoie la colonne actuelle de la pièce
	 * @return un int correspondant à la colonne de la pièce
	 */
	public int getColonne() {
		return colonne;
	}
	
	/*
	 * @brief getter qui renvoie la couleur de la pièce
	 * @return une couleur correspondant à la couleur de la pièce
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
	/*
	 * @brief getter qui renvoie le symbole de la pièce
	 * @return un char correspondant au symbole de la pièce
	 */
	public char getSymbole() {
		return symbole;
	}
	
	/*
	 * @brief setter qui modifie la ligne de la pièce avec le paramètre[in]
	 * @param[in] la nouvelle ligne qui devient la ligne de la pièce
	 */
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	/*
	 * @brief setter qui modifie la ligne de la pièce avec le paramètre[in]
	 * @param[in] la nouvelle colonne qui devient la colonne de la pièce
	 */
	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	
	/*
	 * @brief setter qui modifie le symbole de la pièce avec le paramètre[in]
	 * @param[in] la nouveau symbole de la pièce qui devient le symbole de la pièce
	 */
	public void setSymbole(char s) {
		symbole = s;
	}
}