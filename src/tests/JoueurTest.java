package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu.Echiquier;
import jeu.Partie;
import joueurs.Joueur;
import pièces.Roi;
import pièces.Tour;
import pièces.Pièce.Couleur;

class JoueurTest {
	Echiquier eTest = new Echiquier();
	Partie partie = new Partie();

	@Test
	void testJoueur() {
		Joueur joueur = new Joueur(Couleur.BLANC);
		assertEquals(joueur.couleur, Couleur.BLANC);
	}

	@Test
	void testJoue() {
		Roi RoiTBlanc = new Roi(0, 0, Couleur.BLANC, eTest);
		Roi RoiTNoir = new Roi(7, 6, Couleur.NOIR, eTest);
		Tour TourTNoire = new Tour(7, 1, Couleur.NOIR, eTest);
		Tour TourTNoire2 = new Tour(1, 7, Couleur.NOIR, eTest);
		Joueur joueur = new Joueur(Couleur.BLANC);
		String coupInvalide = "a8b7";
		String coupValide = "a8a7";
		
		assertFalse(joueur.joue(coupInvalide, partie, eTest));
		assertFalse(joueur.joue(coupValide, partie, eTest));
	}



	@Test
	void testGetCouleur() {
		Joueur joueur = new Joueur(Couleur.BLANC);
		assertEquals(joueur.getCouleur(), Couleur.BLANC);
	}
	
	
	@Test
	void testSetCouleur() {
		Joueur joueur = new Joueur(Couleur.BLANC);
		joueur.setCouleur(Couleur.NOIR);
		assertEquals(joueur.getCouleur(), Couleur.NOIR);
	}
	
	@Test
	void testDemandeFin() {
		Joueur joueur = new Joueur(Couleur.BLANC);
		String matchNul = "match nul";
		String abandonner = "abandonner";
		
		assertTrue(joueur.demandeFin(matchNul, partie));
		// Il faut saisir oui ou non pour valider le test
		assertTrue(joueur.demandeFin(abandonner, partie));
	}
}
