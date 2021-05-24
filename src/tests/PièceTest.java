package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu.Echiquier;
import pi�ces.Pi�ce;
import pi�ces.Pi�ce.Couleur;
import pi�ces.Roi;
import pi�ces.Tour;

class Pi�ceTest {
	Echiquier eTest = new Echiquier();

	@Test
	void testPi�ce() {
		Roi RoiTEST = new Roi(1, 1, Couleur.BLANC, eTest);
		Tour TourTEST = new Tour(5, 5, Couleur.NOIR, eTest);

		assertEquals(eTest.getPlateau()[1][1], RoiTEST);
		assertEquals(eTest.getPlateau()[5][5], TourTEST);
	}

	@Test
	void testD�placer() {
		Roi RoiTEST = new Roi(1, 1, Couleur.BLANC, eTest);
		Tour TourTEST = new Tour(5, 5, Couleur.NOIR, eTest);
		RoiTEST.d�placer(2, 2, eTest);
		TourTEST.d�placer(3, 5, eTest);

		assertTrue(eTest.getPlateau()[2][2] == RoiTEST && eTest.getPlateau()[1][1] == null);
		assertTrue(eTest.getPlateau()[3][5] == TourTEST && eTest.getPlateau()[5][5] == null);
	}

	@Test
	void testEstMang�() {
		Tour TourTEST = new Tour(5, 5, Couleur.BLANC, eTest);
		int tailleListe = eTest.getListePi�ces().size();
		TourTEST.estMang�(5, 5, eTest);

		assertEquals(eTest.getListePi�ces().size(), tailleListe - 1);
	}

	@Test
	void testPeutManger() {
		Tour TourTBlanche = new Tour(5, 5, Couleur.BLANC, eTest);
		Tour TourTBlanche2 = new Tour(4, 3, Couleur.BLANC, eTest);
		Tour TourTNoire = new Tour(5, 4, Couleur.NOIR, eTest);

		assertTrue(TourTBlanche.peutManger(5, 4, eTest));
		assertTrue(TourTNoire.peutManger(5, 5, eTest));
		assertFalse(TourTBlanche.peutManger(4, 3, eTest));
	}

	@Test
	void testMetEnEchec() {
		Roi RoiTNoir = new Roi(1, 1, Couleur.NOIR, eTest);
		Roi RoiTBlanc = new Roi(6, 7, Couleur.BLANC, eTest);
		Tour TourTBlanche = new Tour(5, 1, Couleur.BLANC, eTest);

		assertTrue(TourTBlanche.metEnEchec(eTest));
	}

	@Test
	void testPeutAllerEn() {
		Roi RoiTNoir = new Roi(1, 1, Couleur.NOIR, eTest);
		Roi RoiTBlanc = new Roi(6, 7, Couleur.BLANC, eTest);
		Tour TourTBlanche = new Tour(5, 3, Couleur.BLANC, eTest);

		assertFalse(TourTBlanche.peutAllerEn(6, 2, eTest));
		assertTrue(TourTBlanche.peutAllerEn(3, 3, eTest));
	}

	@Test
	void testEstEnEchec() {
		Roi RoiTNoir = new Roi(1, 1, Couleur.NOIR, eTest);
		Roi RoiTBlanc = new Roi(6, 7, Couleur.BLANC, eTest);
		Tour TourTBlanche = new Tour(5, 1, Couleur.BLANC, eTest);

		assertTrue(RoiTNoir.estEnEchec(eTest));
	}

	@Test
	void testMetEnMatOuPat() {
		// Pat
		Roi RoiTBlanc = new Roi(0, 0, Couleur.BLANC, eTest);
		Roi RoiTNoir = new Roi(7, 6, Couleur.NOIR, eTest);
		Tour TourTNoire = new Tour(1, 1, Couleur.NOIR, eTest);
		Tour TourTNoire2 = new Tour(1, 6, Couleur.NOIR, eTest);

		assertTrue(TourTNoire2.metEnMatOuPat(eTest));
		// Mat
		TourTNoire.d�placer(0, 6, eTest);

		assertTrue(TourTNoire.metEnMatOuPat(eTest));
	}

	@Test
	void testPeutPasBouger() {
		Roi RoiTBlanc = new Roi(0, 0, Couleur.BLANC, eTest);
		Roi RoiTNoir = new Roi(7, 6, Couleur.NOIR, eTest);
		Tour TourTNoire = new Tour(7, 1, Couleur.NOIR, eTest);
		Tour TourTNoire2 = new Tour(1, 7, Couleur.NOIR, eTest);

		assertTrue(RoiTBlanc.peutPasBouger(0, 1, eTest));
		assertTrue(RoiTBlanc.peutPasBouger(1, 0, eTest));
		assertTrue(RoiTBlanc.peutPasBouger(1, 1, eTest));

	}

	@Test
	void testGetRoi() {
		Roi RoiTBlanc = new Roi(0, 0, Couleur.BLANC, eTest);
		Tour TourTBlanche = new Tour(5, 3, Couleur.BLANC, eTest);
		Roi RoiTNoir = new Roi(7, 6, Couleur.NOIR, eTest);

		assertEquals(TourTBlanche.getRoi(eTest), RoiTBlanc);
	}

	@Test
	void testGetRoiAdverse() {
		Roi RoiTBlanc = new Roi(0, 0, Couleur.BLANC, eTest);
		Tour TourTBlanche = new Tour(5, 3, Couleur.BLANC, eTest);
		Roi RoiTNoir = new Roi(7, 6, Couleur.NOIR, eTest);

		assertEquals(TourTBlanche.getRoiAdverse(eTest), RoiTNoir);
	}

	@Test
	void test�checSimul�() {
		Roi RoiTBlanc = new Roi(0, 0, Couleur.BLANC, eTest);
		Tour TourTBlanche = new Tour(0, 4, Couleur.BLANC, eTest);
		Roi RoiTNoir = new Roi(7, 6, Couleur.NOIR, eTest);
		Tour TourTNoire = new Tour(0, 6, Couleur.NOIR, eTest);

		assertTrue(TourTBlanche.�checSimul�(1, 4, eTest));
	}

	@Test
	void testGetLigne() {
		Roi RoiTBlanc = new Roi(3, 0, Couleur.BLANC, eTest);

		assertEquals(RoiTBlanc.getLigne(), 3);
	}

	@Test
	void testGetColonne() {
		Roi RoiTBlanc = new Roi(3, 0, Couleur.BLANC, eTest);

		assertEquals(RoiTBlanc.getColonne(), 0);
	}

	@Test
	void testGetCouleur() {
		Roi RoiTBlanc = new Roi(3, 0, Couleur.BLANC, eTest);

		assertEquals(RoiTBlanc.getCouleur(), Couleur.BLANC);
	}

	@Test
	void testGetSymbole() {
		Roi RoiTBlanc = new Roi(3, 0, Couleur.BLANC, eTest);
		Tour TourTNoire = new Tour(0, 6, Couleur.NOIR, eTest);
		assertEquals(RoiTBlanc.getSymbole(), 'R');
		assertEquals(TourTNoire.getSymbole(), 't');
	}

	@Test
	void testSetSymbole() {
		Roi RoiTBlanc = new Roi(3, 0, Couleur.BLANC, eTest);
		RoiTBlanc.setSymbole('r');
		assertEquals(RoiTBlanc.getSymbole(), 'r');
	}
}
