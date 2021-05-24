package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu.Echiquier;
import pièces.Roi;
import pièces.Tour;
import pièces.Pièce.Couleur;

class TourTest {
	Echiquier eTest = new Echiquier();

	@Test
	void testTour() {
		Tour TourTBlanc = new Tour(1, 6, Couleur.BLANC, eTest);
		assertEquals(eTest.getPlateau()[1][6], TourTBlanc);
	}
	
	@Test
	void testPeutAllerEn() {
		Roi RoiTNoir = new Roi(1, 1, Couleur.NOIR, eTest);
		Roi RoiTBlanc = new Roi(6, 7, Couleur.BLANC, eTest);
		Tour TourTBlanche = new Tour(5, 3, Couleur.BLANC, eTest);
		
		assertFalse(TourTBlanche.peutAllerEn(6, 4, eTest));
		assertTrue(TourTBlanche.peutAllerEn(5, 4, eTest));
	}
}
