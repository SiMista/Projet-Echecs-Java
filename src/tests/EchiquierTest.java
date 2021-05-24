package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu.Echiquier;
import jeu.IPièce;
import pièces.Roi;
import pièces.Pièce.Couleur;

class EchiquierTest {
	Echiquier eTest = new Echiquier();

	@Test
	void testEchiquier() {
		assertTrue(Echiquier.class.isInstance(eTest));
	}

	
	@Test
	void testOutOfBounds() {
		assertTrue(eTest.outOfBounds(0,9));
		assertFalse(eTest.outOfBounds(4,6));
	}

	@Test
	void testEstLibre() {
		Roi RoiTBlanc = new Roi(1, 1, Couleur.BLANC, eTest);
		assertTrue(eTest.estLibre(5, 7));
		assertFalse(eTest.estLibre(1, 1));
	}

	@Test
	void testPlacer() {
		// La méthode placer se trouve dans le constructeur de Pièce
		Roi RoiTBlanc = new Roi(1, 5, Couleur.BLANC, eTest);
		assertEquals(eTest.getPlateau()[1][5], RoiTBlanc);
	}

	@Test
	void testGetPlateau() {
		assertEquals(eTest.getPlateau(), eTest.plateau);
	}

	@Test
	void testGetListePièces() {
		assertEquals(eTest.getListePièces(), eTest.listePièces);
	}
}
