package Entites;

import main.Ecran;
import object.EpeeNormale;

public class ChevalierMort extends Entite {
	public Ecran ecran;

	public ChevalierMort (Ecran ecran, int carte) {
		super(ecran);
		this.ecran = ecran;
		
		nom = "Chevalier servant";
		typeEntite = joueurType;
		vitesseDefaut = 3;
		vitesse = vitesseDefaut;
		vieMax = 15*carte;
		vie = vieMax;
		armeActuelle = new EpeeNormale(ecran);
		attaquer = 6;
		defendre = 6;
		
		aireCollision.x = 3;
		aireCollision.y = 7;
		aireCollision.width = 42;
		aireCollision.height = 41;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
		
		getImage();
	}
	
	public void getImage() {
		avant = initialiser("/mage/chevalieravant.png", ecran.tailleFinale, ecran.tailleFinale);
		avant1 = initialiser("/mage/chevalieravant1.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere = initialiser("/mage/chevalierarriere.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/mage/chevalierarriere1.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche = initialiser("/mage/chevaliergauche.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche1 = initialiser("/mage/chevaliergauche1.png", ecran.tailleFinale, ecran.tailleFinale);
		droite = initialiser("/mage/chevalierdroite.png", ecran.tailleFinale, ecran.tailleFinale);
		droite1 = initialiser("/mage/chevalierdroite1.png", ecran.tailleFinale, ecran.tailleFinale);

	}
	
	public void actions() {
		attaquer = 6*(ecran.carteActuelle+1);
		defendre = 6*(ecran.carteActuelle+1);

		Entite e = chercherMonstre();
		chercherChemin(getColArrivee(e), getLignArrivee(e));

		int ennemiIndex = ecran.collisions.analyserEntite(this, ecran.monstre);
		ecran.joueur.blesserMonstre(this, ennemiIndex, attaquer, 1);
		System.out.println(attaquer);
		
	}
}
