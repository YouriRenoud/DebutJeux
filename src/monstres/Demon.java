package monstres;

import java.awt.Rectangle;
import java.util.Random;

import Entites.Entite;
import donnees.Progression;
import main.Ecran;
import object.Coeur;
import object.Mana;
import object.Pieces;
import object.Pierre;
import object.PorteFer;

public class Demon extends Entite {

	public Ecran ecran;
    public static final String objnom = "Roi des démons";

	public Demon (Ecran ecran, int carte) {
		super(ecran);
		this.ecran = ecran;
		
		nom = objnom;
		typeEntite = monstreType;
		vitesseDefaut = 1;
		vitesse = vitesseDefaut;
		vieMax = 250*carte;
		vie = vieMax;
		attaquer = 25*carte;
		defendre = 20*carte;
		experience = 60*carte;
		magie = 20;
		reculForce = 10;
        dureeAttFrame1 = 25;
        dureeAttFrame2 = 50;
        boss = true;
        endormi = true;
        direction = "bas";
		
		aireCollision.x = ecran.tailleFinale;
		aireCollision.y = ecran.tailleFinale;
		aireCollision.width = 3*ecran.tailleFinale;
		aireCollision.height = 4*ecran.tailleFinale;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
        attArea.width = 5*ecran.tailleFinale;
        attArea.height = 4*ecran.tailleFinale;
		
		getImage();
        getAttImage();
        setDialogues();
	}
	
	public void getImage() {

        int i = 5;

        if (enrage) {
            avant = initialiser("/Monsters/demonEnrageAvant.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            avant1 = initialiser("/Monsters/demonEnrageAvant1.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            arriere = initialiser("/Monsters/demonEnrageArriere.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            arriere1 = initialiser("/Monsters/demonEnrageArriere1.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            gauche = initialiser("/Monsters/demonEnrageGauche.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            gauche1 = initialiser("/Monsters/demonEnrageGauche1.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            droite = initialiser("/Monsters/demonEnrageDroite.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            droite1 = initialiser("/Monsters/demonEnrageDroite1.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
        }
        else {
            avant = initialiser("/Monsters/demonAvant.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            avant1 = initialiser("/Monsters/demonAvant1.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            arriere = initialiser("/Monsters/demonArriere.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            arriere1 = initialiser("/Monsters/demonArriere1.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            gauche = initialiser("/Monsters/demonGauche.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            gauche1 = initialiser("/Monsters/demonGauche1.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            droite = initialiser("/Monsters/demonDroite.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            droite1 = initialiser("/Monsters/demonDroite1.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
        }
	}

    public void getAttImage() {

        int i = 5;

        if (enrage) {
            attAvant = initialiser("/Monsters/demonEnrageFeuAvant.png", ecran.tailleFinale*i, ecran.tailleFinale*i*2);
            attArriere = initialiser("/Monsters/demonEnrageFeuArriere.png", ecran.tailleFinale*i, ecran.tailleFinale*i*2);
            attGauche = initialiser("/Monsters/demonEnrageFeuGauche.png", ecran.tailleFinale*i*2, ecran.tailleFinale*i);
            attDroite = initialiser("/Monsters/demonEnrageFeuDroite.png", ecran.tailleFinale*i*2, ecran.tailleFinale*i);
            attArriere1 = initialiser("/Monsters/demonEnrageFeuArriere1.png", ecran.tailleFinale*i, ecran.tailleFinale*i*2);
            attAvant1 = initialiser("/Monsters/demonEnrageFeuAvant1.png", ecran.tailleFinale*i, ecran.tailleFinale*2*i);
            attGauche1 = initialiser("/Monsters/demonEnrageFeuGauche1.png", ecran.tailleFinale*i*2, ecran.tailleFinale*i);
            attDroite1 = initialiser("/Monsters/demonEnrageFeuDroite1.png", ecran.tailleFinale*i*2, ecran.tailleFinale*i);
        }
        else {
            attAvant = initialiser("/Monsters/demonFeuAvant.png", ecran.tailleFinale*i, ecran.tailleFinale*i*2);
            attArriere = initialiser("/Monsters/demonFeuArriere.png", ecran.tailleFinale*i, ecran.tailleFinale*i*2);
            attGauche = initialiser("/Monsters/demonFeuGauche.png", ecran.tailleFinale*i*2, ecran.tailleFinale*i);
            attDroite = initialiser("/Monsters/demonFeuDroite.png", ecran.tailleFinale*i*2, ecran.tailleFinale*i);
            attArriere1 = initialiser("/Monsters/demonFeuArriere1.png", ecran.tailleFinale*i, ecran.tailleFinale*i*2);
            attAvant1 = initialiser("/Monsters/demonFeuAvant1.png", ecran.tailleFinale*i, ecran.tailleFinale*2*i);
            attGauche1 = initialiser("/Monsters/demonFeuGauche1.png", ecran.tailleFinale*i*2, ecran.tailleFinale*i);
            attDroite1 = initialiser("/Monsters/demonFeuDroite1.png", ecran.tailleFinale*i*2, ecran.tailleFinale*i);   
        }
    }
	
    public void setDialogues() {
        dialogue[0][0] = "Je suis le roi des démons!";
        dialogue[0][1] = "Qui ose troubler mon sommeil?";
        dialogue[0][2] = "Je vais te réduire en cendres!";
    }

	public void actions() {

        if (!enrage && vie < vieMax/2) {
            enrage = true;
            attaquer = 50;
            vitesse = vitesseDefaut*2;
            getImage();
            getAttImage();
        }

		if (getTerrainDistance(ecran.joueur) < 15) {
            bougerSelonJoueur(60);
            //System.out.println("Bouger selon joueur");
		}
		else {

			getRandomDirection(150);
		}

        if (!attaque) {
            verifierAttaque(30, ecran.tailleFinale*10, ecran.tailleFinale*5);
        }
	}
	
	public void verifierDrop() {
        
        ecran.bossCombat = false;

        ecran.stopperMusique();
        ecran.jouerMusique(21);

        Progression.roiDemonBattu = true;

        for (int i = 0; i < ecran.obj[1].length; i++) {

            if (ecran.obj[ecran.carteActuelle][i] != null && ecran.obj[ecran.carteActuelle][i].nom.equals(PorteFer.objnom)) {
                ecran.obj[ecran.carteActuelle][i] = null;
                ecran.jouerSE(24);            }
        }
		
		dropItem(new Pieces(ecran, 1));
	}
	
	public void attaqueReaction() {
		attente = 0;
	}

}
