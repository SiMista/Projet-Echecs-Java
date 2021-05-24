package pi�ces;

import java.util.Scanner;

import jeu.Echiquier;
import jeu.IPi�ce;
import jeu.Partie;

public abstract class Pi�ce implements IPi�ce {
	private int ligne, colonne;
	private Couleur couleur;
	private char symbole;

	public enum Couleur {
		BLANC, NOIR
	};
	
	/*
	 * @brief Le constructeur de la Pi�ce, sert � placer la pi�ce suite � la demande de l'utilisateur
	 * @param[in] ligne, un entier qui va �tre la ligne de la pi�ce dans l'�chiquier
	 * @param[in] colonne, un entier qui va �tre la colonne de la pi�ce dans l'�chiquier
	 * @param[in] c, la couleur qui va �tre la couleur de la pi�ce
	 * @param[in out] e, l'�chiquier sur lequel se place la pi�ce 
	 */
	public Pi�ce(int ligne, int colonne, Couleur c, Echiquier e) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.couleur = c;
		e.getListePi�ces().add(this);
		e.placer(this);
	}
	
	/*
	 * @brief M�thode servant � d�placer les pi�ces � la case entr�e en param�tre
	 * @param[in out] ligneD, ligne de la case de destination de la pi�ce
	 * @param[in out] colonneD, colonne de la case de destination de la pi�ce
	 * @param[in out] e, echiquier sur lequel se d�place la pi�ce
	 */
	public void d�placer(int ligneD, int colonneD, Echiquier e) {
		if (!e.estLibre(ligneD, colonneD))
			e.getPlateau()[ligneD][colonneD].estMang�(ligneD, colonneD, e);
		e.getPlateau()[ligneD][colonneD] = this;
		e.getPlateau()[getLigne()][getColonne()] = null;
		setLigne(ligneD);
		setColonne(colonneD);
	}
	
	/*
	 * @brief M�thode abstraite, qui d�termine la possiblit� d'aller � une case propre
	 * aux mouvements de chaque pi�ce
	 * @param[in] ligneD, ligne de la case de destination de la pi�ce
	 * @param[in] colonneD, colonne de la case de destination de la pi�ce
	 * @param[in] e, echiquier sur lequel la pi�ce joue
	 * @return le bool�en qui v�rifie si la pi�ce peut bien aller � la case de destination
	 */
	public abstract boolean peutAllerEn(int ligne, int colonne, Echiquier e);

	/*
	 * @brief M�thode supprime une pi�ce mang�e
	 * @param[in] ligne, ligne de la pi�ce mang�e
	 * @param[in] colonne, colonne de la pi�ce mang�e
	 * @param[in out] e, echiquier sur lequel se d�place la pi�ce
	 */
	public void estMang�(int ligne, int colonne, Echiquier e) {
		System.out.println("La pi�ce " + getClass().getSimpleName() + " a �t� mang�");
		e.getListePi�ces().remove(e.getPlateau()[ligne][colonne]);
	}

	/*
	 * @brief M�thode qui v�rifie si une pi�ce peut bien manger la pi�ce de la case destination
	 * @param[in] ligne, ligne de la pi�ce qui doit �tre mang�e
	 * @param[in] colonne, colonne de la pi�ce qui doit �tre mang�e
	 * @param[in] e, echiquier sur lequel les pi�ces jouent
	 * @return le bool�en qui v�rifie si la pi�ce peut manger la pi�ce de la case destination
	 */
	public boolean peutManger(int ligne, int colonne, Echiquier e) {
		if (e.getPlateau()[ligne][colonne].getCouleur() != this.getCouleur()) {
			return true;
		} else
			return false;
	}
	
	/*
	 * @brief M�thode qui invoque la m�thode du roi alli� lorsqu'il est en �chec
	 * @param[in] e, echiquier sur lequel les pi�ces jouent
	 * @return le bool�en qui v�rifie si le roi est en �chec
	 */
	public boolean estEnEchec(Echiquier e) {
		return false;
	}
	
	/*
	 * @brief M�thode qui v�rfie si la pi�ce jou�e met en �chec le roi adverse
	 * @param[in] e, echiquier sur lequel joue la pi�ce
	 * @return le bool�en qui v�rifie si la pi�ce met en �chec
	 */
	public boolean metEnEchec(Echiquier e) {
		for (Pi�ce p : e.getListePi�ces()) {
			if (Character.toLowerCase(p.getSymbole()) != 'r'
					&& p.peutAllerEn(getRoiAdverse(e).getLigne(), getRoiAdverse(e).getColonne(), e)
					&& p.getCouleur() != getRoiAdverse(e).getCouleur())
				return true;
		}
		return false;
	}

	/*
	 * @brief M�thode qui v�rifie si la pi�ce jou�e met en Pat ou met en Mat le roi adverse
	 * @param[in] e, echiquier sur lequel joue la pi�ce
	 * @return le bool�en qui v�rifie si la pi�ce met en mat ou pat
	 */
	public boolean metEnMatOuPat(Echiquier e) {
		for (Pi�ce pi�cesAdv : e.getListePi�ces()) {
			if (pi�cesAdv.getCouleur() != this.getCouleur()) {
				for (int i = -1; i <= 1; ++i) {
					for (int j = -1; j <= 1; ++j) {
						if (!pi�cesAdv.peutPasBouger(pi�cesAdv.getLigne() + i, pi�cesAdv.getColonne() + j, e)) {
							return false;
						}
					}
				}
			}
		}
		if (getRoiAdverse(e).estEnEchec(e)) {
			for (Pi�ce pi�cesAdv : e.getListePi�ces()) {
				if (pi�cesAdv.getCouleur() != this.getCouleur() && Character.toLowerCase(pi�cesAdv.getSymbole()) != 'r') {
					for (int i = -Echiquier.MAX + 1; i <= Echiquier.MAX; ++i) {
						for (int j = -Echiquier.MAX + 1; j <= Echiquier.MAX; ++j) {
							if (!pi�cesAdv.peutPasBouger(pi�cesAdv.getLigne() + i,pi�cesAdv.getColonne() + j, e)) {
								return false;
							}
						}
					}
				}
			}
			System.out.println("Vous avez mis le roi adverse en �chec et mat");
			System.out.println("\n       Les " + getRoi(e).getCouleur() + "S gagnent la partie");
			return true;
		}
		System.out.println("Vous avez mis le roi adverse en Pat");
		System.out.println("\n		Match nul");
		return true;

	}

	/*
	 * @brief M�thode qui v�rifie si la pi�ce ne peut pas se d�placer � la case choisie
	 * @param[in] ligneD, ligne de la case de destination
	 * @param[in] colonneD, colonne de la case de destination
	 * @param[in] e, echiquier sur lequel joue la pi�ce
	 * @return le bool�en qui v�rifie si la pi�ce ne peut pas bouger
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
	 * @brief getter qui renvoie le roi alli�
	 * @param[in] e, echiquier sur lequel joue la pi�ce
	 * @return le roi alli�
	 */
	public Pi�ce getRoi(Echiquier e) {
		for (Pi�ce roi : e.getListePi�ces()) {
			if (roi.getCouleur() == getCouleur() && Character.toLowerCase(roi.getSymbole()) == 'r')
				return roi;
		}
		return null;
	}

	/*
	 * @brief getter qui renvoie le roi adverse
	 * @param[in out] e, echiquier sur lequel joue la pi�ce
	 * @return le roi adverse
	 */
	public Pi�ce getRoiAdverse(Echiquier e) {
		for (Pi�ce roi : e.getListePi�ces()) {
			if (roi.getCouleur() != getCouleur() && Character.toLowerCase(roi.getSymbole()) == 'r')
				return roi;
		}
		return null;
	}

	/*
	 * @brief M�thode qui v�rifie si le d�placement de la pi�ce 
	 * @param[in] ligneD, ligne de la case de destination
	 * @param[in] colonneD, colonne de la case de destination
	 * @param[in out] e, echiquier sur lequel joue la pi�ce
	 * @return le bool�en qui v�rifie si la pi�ce ne peut pas bouger
	 */
	public boolean �checSimul�(int ligneD, int colonneD, Echiquier e) {
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
	 * @brief getter qui renvoie la ligne actuelle de la pi�ce
	 * @return un int correspondant � la ligne de la pi�ce
	 */
	public int getLigne() {
		return ligne;
	}

	/*
	 * @brief getter qui renvoie la colonne actuelle de la pi�ce
	 * @return un int correspondant � la colonne de la pi�ce
	 */
	public int getColonne() {
		return colonne;
	}
	
	/*
	 * @brief getter qui renvoie la couleur de la pi�ce
	 * @return une couleur correspondant � la couleur de la pi�ce
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
	/*
	 * @brief getter qui renvoie le symbole de la pi�ce
	 * @return un char correspondant au symbole de la pi�ce
	 */
	public char getSymbole() {
		return symbole;
	}
	
	/*
	 * @brief setter qui modifie la ligne de la pi�ce avec le param�tre[in]
	 * @param[in] la nouvelle ligne qui devient la ligne de la pi�ce
	 */
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	/*
	 * @brief setter qui modifie la ligne de la pi�ce avec le param�tre[in]
	 * @param[in] la nouvelle colonne qui devient la colonne de la pi�ce
	 */
	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	
	/*
	 * @brief setter qui modifie le symbole de la pi�ce avec le param�tre[in]
	 * @param[in] la nouveau symbole de la pi�ce qui devient le symbole de la pi�ce
	 */
	public void setSymbole(char s) {
		symbole = s;
	}
}