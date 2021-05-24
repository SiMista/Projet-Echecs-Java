package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu.Echiquier;
import pièces.Roi;
import pièces.Tour;
import pièces.Pièce.Couleur;

class RoiTest {
	Echiquier eTest = new Echiquier();

	@Test
	void testRoi() {
		Roi RoiTBlanc = new Roi(1, 1, Couleur.BLANC, eTest);
		assertEquals(eTest.getPlateau()[1][1], RoiTBlanc);
	}
	
	@Test
	void testPeutAllerEn() {
		Roi RoiTBlanc = new Roi(1, 1, Couleur.BLANC, eTest);
		Roi RoiTNoir = new Roi(6, 7, Couleur.NOIR, eTest);

		assertTrue(RoiTBlanc.peutAllerEn(2, 2, eTest));
		assertFalse(RoiTBlanc.peutAllerEn(3, 3, eTest));
	}

	@Test
	void testEstEnEchec() {
		Roi RoiTBlanc = new Roi(1, 1, Couleur.BLANC, eTest);
		Roi RoiTNoir = new Roi(6, 7, Couleur.NOIR, eTest);
		Tour TourTNoire = new Tour(1, 6, Couleur.NOIR, eTest);
		
		assertTrue(RoiTBlanc.estEnEchec(eTest));
	}

	@Test
	void testRoiACoté() {
		Roi RoiTBlanc = new Roi(1, 1, Couleur.BLANC, eTest);
		Roi RoiTNoir = new Roi(1, 3, Couleur.NOIR, eTest);
		
		assertTrue(RoiTBlanc.roiACoté(1, 2, eTest));
	}
}
