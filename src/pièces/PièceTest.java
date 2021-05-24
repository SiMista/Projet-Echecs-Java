package pi�ces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu.Echiquier;
import pi�ces.Pi�ce.Couleur;

class Pi�ceTest {
	Echiquier eTest = new Echiquier();

	@Test
	void testD�placer() {
		Roi RoiTEST = new Roi(1,1,Couleur.BLANC,eTest);
		Tour TourTEST = new Tour(5,5,Couleur.NOIR,eTest);
		RoiTEST.d�placer(2,2,eTest);
		TourTEST.d�placer(3,5,eTest);
		
		assert(eTest.getPlateau()[2][2] == RoiTEST && eTest.getPlateau()[1][1] == null);
		assert(eTest.getPlateau()[3][5] == TourTEST && eTest.getPlateau()[5][5] == null);
	}
	
	@Test
	void testEstMang�() {
		Tour TourTEST = new Tour(5,5,Couleur.BLANC,eTest);
		int tailleListe = eTest.getListePi�ces().size();
		TourTEST.estMang�(5, 5, eTest);
		assert(eTest.getListePi�ces().size() == tailleListe - 1);
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
