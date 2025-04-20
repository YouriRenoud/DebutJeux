package monstres;

import java.awt.Rectangle;
import java.util.Random;

import Entites.Entite;
import main.Ecran;
import object.Cle;
import object.Coeur;
import object.Lance;
import object.Mana;
import object.Pieces;
import object.Pierre;

public class Squelette extends Entite {

	public Ecran ecran;
	public static final String monstreNom = "Squelette";

	public Squelette (Ecran ecran, int carte) {
		super(ecran);
		this.ecran = ecran;
		
		nom = monstreNom;
		typeEntite = monstreType;
		vitesseDefaut = 1;
		vitesse = vitesseDefaut;
		vieMax = 60*carte;
		vie = vieMax;
		attaquer = 1*carte;
		attVal = 8*carte;
		defendre = 5*carte;
		experience = 8*carte;
		magie = 0;
		reculForce = 2;
        dureeAttFrame1 = 10;
        dureeAttFrame2 = 35;
		
		aireCollision.x = 6;
		aireCollision.y = 10;
		aireCollision.width = 36;
		aireCollision.height = 34;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
        attArea.x = 0;
        attArea.y = 6;
        attArea.width = 35;
        attArea.height = 35;
		
		getImage();
        getAttImage();
	}
	
	public void getImage() {
		avant = initialiser("/Monsters/SqueletteAvant.png", ecran.tailleFinale, ecran.tailleFinale);
		avant1 = initialiser("/Monsters/SqueletteAvant1.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere = initialiser("/Monsters/SqueletteArriere.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/Monsters/SqueletteArriere1.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche = initialiser("/Monsters/SqueletteGauche.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche1 = initialiser("/Monsters/SqueletteGauche1.png", ecran.tailleFinale, ecran.tailleFinale);
		droite = initialiser("/Monsters/SqueletteDroite.png", ecran.tailleFinale, ecran.tailleFinale);
		droite1 = initialiser("/Monsters/SqueletteDroite1.png", ecran.tailleFinale, ecran.tailleFinale);
	}

    public void getAttImage() {
        attAvant = initialiser("/Monsters/SqueletteAttAvant.png", ecran.tailleFinale, ecran.tailleFinale*2);
        attArriere = initialiser("/Monsters/SqueletteAttArriere.png", ecran.tailleFinale, ecran.tailleFinale*2);
        attGauche = initialiser("/Monsters/SqueletteAttGauche.png", ecran.tailleFinale*2, ecran.tailleFinale);
        attDroite = initialiser("/Monsters/SqueletteAttDroite.png", ecran.tailleFinale*2, ecran.tailleFinale);
        attArriere1 = initialiser("/Monsters/SqueletteAttArriere1.png", ecran.tailleFinale, ecran.tailleFinale*2);
        attAvant1 = initialiser("/Monsters/SqueletteAttAvant1.png", ecran.tailleFinale, ecran.tailleFinale*2);
        attGauche1 = initialiser("/Monsters/SqueletteAttGauche1.png", ecran.tailleFinale*2, ecran.tailleFinale);
        attDroite1 = initialiser("/Monsters/SqueletteAttDroite1.png", ecran.tailleFinale*2, ecran.tailleFinale);
    }
	
	public void actions() {

		if (enChemin) {

			verifierChasse(ecran.joueur, 6, 100);

			chercherChemin(getColArrivee(ecran.joueur), getLignArrivee(ecran.joueur));

		}
		else {
			getRandomDirection(150);
		}

        if (!attaque) {
            verifierAttaque(15, ecran.tailleFinale*4, ecran.tailleFinale);
        }
	}
	
	public void verifierDrop() {
		int i = new Random().nextInt(100)+1;
		
		if (i < 50) {
			drop.add(new Coeur(ecran));
			drop.add(new Mana(ecran));
			drop.add(new Pieces(ecran, 1));
		}
		if (i >= 50 && i < 80) {
			drop.add(new Coeur(ecran));
			drop.add(new Pieces(ecran, 3));
		}
		if (i >= 80 && i < 99) {
			drop.add(new Cle(ecran));
			drop.add(new Mana(ecran));
			drop.add(new Pieces(ecran, 2));
		}
		if (i == 99) {
			drop.add(new Lance(ecran));
		}
		dropItem();
	}
	
	public void attaqueReaction() {
		attente = 0;
		enChemin = true;
	}

}
