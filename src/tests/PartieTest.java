package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu.Echiquier;
import jeu.Partie;
import pièces.Pièce.Couleur;
import pièces.Roi;
import pièces.Tour;

class PartieTest {
	Partie partie = new Partie();
	Echiquier eTest = new Echiquier();
	
	@Test
	void testErreurInitialisation() {
		String s = "~";
		assertTrue(partie.erreurInitialisation(s));
		s = "a4";
		assertFalse(partie.erreurInitialisation(s));
	}

	@Test
	void testErreurSaisie() {
		String s = "a&e";
		assertTrue(partie.erreurSaisie(s));
		s = "a4b6";
		assertFalse(partie.erreurSaisie(s));
	}

	@Test
	void testErreurDéplacement() {
		Tour TourTBlanche = new Tour(1,1, Couleur.NOIR, eTest);
		Tour TourTNoire = new Tour(7,7, Couleur.NOIR, eTest);
		assertTrue(partie.erreurDéplacement(7,7, 7, 8, Couleur.BLANC, eTest));
		assertFalse(partie.erreurDéplacement(7,7, 7, 5, Couleur.NOIR, eTest));
	}

	@Test
	void testErreurNbPiècesDouble() {
		String s = "éç";
		assertTrue(partie.erreurNbPiècesDouble(s));
		s = "4";
		assertTrue(partie.erreurNbPiècesDouble(s));
		s = "1";
		assertFalse(partie.erreurNbPiècesDouble(s));
	}

	@Test
	void testInitialiserRoi() {
		String s = "a6";
		partie.initialiserRoi(s,Couleur.BLANC, eTest);
		
		assertTrue(Roi.class.isInstance(eTest.getPlateau()[2][0]));
	}

	@Test
	void testInitialiserTour() {
		Roi RoiTNoir = new Roi(1, 1, Couleur.NOIR, eTest);
		Roi RoiTBlanc = new Roi(6, 7, Couleur.BLANC, eTest);
		String s = "a6";
		partie.initialiserTour(s,Couleur.BLANC, eTest);
		
		assertTrue(Tour.class.isInstance(eTest.getPlateau()[2][0]));
	}

	@Test
	void testPartieFinie() {
		assertFalse(partie.partieFinie());
	}

	@Test
	void testSetFinDePartie() {
		partie.setFinDePartie(true);
		assertTrue(partie.partieFinie());
	}
}
