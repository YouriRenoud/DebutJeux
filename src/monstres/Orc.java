package monstres;

import java.util.Random;

import Entites.Entite;
import main.Ecran;
import object.Cle;
import object.Coeur;
import object.Lance;
import object.Mana;
import object.Pieces;

public class Orc extends Entite {

	public Ecran ecran;

	public Orc (Ecran ecran, int carte) {
		super(ecran);
		this.ecran = ecran;
		
		nom = "Orc";
		typeEntite = monstreType;
		vitesseDefaut = 3;
		vitesse = vitesseDefaut;
		vieMax = 35*carte;
		vie = vieMax;
		attaquer = 3*carte;
		attVal = 6*carte;
		defendre = 5*carte;
		experience = 6*carte;
		magie = 0;
		reculForce = 4;
        dureeAttFrame1 = 30;
        dureeAttFrame2 = 65;
		
		aireCollision.x = 6;
		aireCollision.y = 10;
		aireCollision.width = 36;
		aireCollision.height = 34;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
        attArea.width = 35;
        attArea.height = 35;
		
		getImage();
        getAttImage();
	}
	
	public void getImage() {
		avant = initialiser("/Monsters/orcAvant.png", ecran.tailleFinale, ecran.tailleFinale);
		avant1 = initialiser("/Monsters/orcAvant1.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere = initialiser("/Monsters/orcArriere.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/Monsters/orcArriere1.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche = initialiser("/Monsters/orcGauche.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche1 = initialiser("/Monsters/orcGauche1.png", ecran.tailleFinale, ecran.tailleFinale);
		droite = initialiser("/Monsters/orcDroite.png", ecran.tailleFinale, ecran.tailleFinale);
		droite1 = initialiser("/Monsters/orcDroite1.png", ecran.tailleFinale, ecran.tailleFinale);
	}

    public void getAttImage() {
        attAvant = initialiser("/Monsters/orcAttAvant.png", ecran.tailleFinale, ecran.tailleFinale*2);
        attArriere = initialiser("/Monsters/orcAttArriere.png", ecran.tailleFinale, ecran.tailleFinale*2);
        attGauche = initialiser("/Monsters/orcAttGauche.png", ecran.tailleFinale*2, ecran.tailleFinale);
        attDroite = initialiser("/Monsters/orcAttDroite.png", ecran.tailleFinale*2, ecran.tailleFinale);
        attArriere1 = initialiser("/Monsters/orcAttArriere1.png", ecran.tailleFinale, ecran.tailleFinale*2);
        attAvant1 = initialiser("/Monsters/orcAttAvant1.png", ecran.tailleFinale, ecran.tailleFinale*2);
        attGauche1 = initialiser("/Monsters/orcAttGauche1.png", ecran.tailleFinale*2, ecran.tailleFinale);
        attDroite1 = initialiser("/Monsters/orcAttDroite1.png", ecran.tailleFinale*2, ecran.tailleFinale);
    }
	
	public void actions() {

		if (enChemin) {

			verifierChasse(ecran.joueur, 8, 100);

			chercherChemin(getColArrivee(ecran.joueur), getLignArrivee(ecran.joueur));

		}
		else {
			arreterChasse(ecran.joueur, 8, 100);

			getRandomDirection(80);
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
		enChemin = false;
		direction = ecran.joueur.direction;
	}

}
