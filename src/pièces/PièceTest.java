package pièces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu.Echiquier;
import pièces.Pièce.Couleur;

class PièceTest {
	Echiquier eTest = new Echiquier();

	@Test
	void testDéplacer() {
		Roi RoiTEST = new Roi(1,1,Couleur.BLANC,eTest);
		Tour TourTEST = new Tour(5,5,Couleur.NOIR,eTest);
		RoiTEST.déplacer(2,2,eTest);
		TourTEST.déplacer(3,5,eTest);
		
		assert(eTest.getPlateau()[2][2] == RoiTEST && eTest.getPlateau()[1][1] == null);
		assert(eTest.getPlateau()[3][5] == TourTEST && eTest.getPlateau()[5][5] == null);
	}
	
	@Test
	void testEstMangé() {
		Tour TourTEST = new Tour(5,5,Couleur.BLANC,eTest);
		int tailleListe = eTest.getListePièces().size();
		TourTEST.estMangé(5, 5, eTest);
		assert(eTest.getListePièces().size() == tailleListe - 1);
	}
	
	@Test
	void testPeutManger() {
		Tour TourTBlanche = new Tour(5,5,Couleur.BLANC,eTest);
		Tour TourTBlanche2 = new Tour(4,3,Couleur.BLANC,eTest);
		Tour TourTNoire = new Tour(5,4,Couleur.NOIR,eTest);
		assert(TourTBlanche.peutManger(5, 4, eTest));
		assert(TourTNoire.peutManger(5, 5, eTest));
		assert(!TourTBlanche.peutManger(4, 3, eTest));
	}
	

	@Test
	void testMetEnEchec() {
		Roi RoiTNoir = new Roi(1,1,Couleur.NOIR,eTest);
		Tour TourTBlanche = new Tour(5,1,Couleur.BLANC,eTest);
		Tour TourTBlanche2 = new Tour(4,4,Couleur.BLANC,eTest);

		assertTrue(eTest.getPlateau()[4][4].metEnEchec(eTest));
	}

}
