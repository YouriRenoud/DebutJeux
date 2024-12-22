package monstres;

import java.awt.Rectangle;
import java.util.Random;

import Entites.Entite;
import donnees.Progression;
import main.Ecran;
import object.BouleDeFeuGeante;
import object.Coeur;
import object.Mana;
import object.Pieces;
import object.Pierre;
import object.PorteFer;
import object.Sceptre;

public class RoiSquelette extends Entite {

	public Ecran ecran;
    public static final String objnom = "Roi des squelettes";

	public RoiSquelette (Ecran ecran, int carte) {
		super(ecran);
		this.ecran = ecran;
		
		nom = objnom;
		typeEntite = monstreType;
		vitesseDefaut = 1;
		vitesse = vitesseDefaut;
		vieMax = 250*carte;
		vie = vieMax;
		attaquer = 12*carte;
		defendre = 16*carte;
		experience = 60*carte;
		magie = 20;
		reculForce = 10;
        dureeAttFrame1 = 25;
        dureeAttFrame2 = 50;
        boss = true;
        endormi = true;
        direction = "bas";
        projectile = new BouleDeFeuGeante(ecran);
		
		aireCollision.x = ecran.tailleFinale;
		aireCollision.y = ecran.tailleFinale;
		aireCollision.width = 3*ecran.tailleFinale;
		aireCollision.height = 4*ecran.tailleFinale;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
        attArea.width = 5*ecran.tailleFinale;
        attArea.height = 4*ecran.tailleFinale;
		
		getImage();
        setDialogues();
	}
	
	public void getImage() {

        int i = 5;
            avant = initialiser("/Monsters/RoiSqueletteAvant.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            avant1 = initialiser("/Monsters/RoiSqueletteAvant1.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            arriere = initialiser("/Monsters/RoiSqueletteArriere.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            arriere1 = initialiser("/Monsters/RoiSqueletteArriere1.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            gauche = initialiser("/Monsters/RoiSqueletteGauche.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            gauche1 = initialiser("/Monsters/RoiSqueletteGauche1.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            droite = initialiser("/Monsters/RoiSqueletteDroite.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
            droite1 = initialiser("/Monsters/RoiSqueletteDroite1.png", ecran.tailleFinale*i, ecran.tailleFinale*i);
	}
	
    public void setDialogues() {
        dialogue[0][0] = "Je suis le roi des squelettes !";
        dialogue[0][1] = "Tu as osé tuer mes enfants ?";
        dialogue[0][2] = "Cette acte sera punis !";
    }

	public void actions() {

        if (!enrage && vie < vieMax/2) {
            enrage = true;
            attaquer = 18;
            vitesse = vitesseDefaut*2;
            getImage();
        }

		if (getTerrainDistance(ecran.joueur) < 15) {
            bougerSelonJoueur(60);
            verifierTirer(100, 38);
            //System.out.println("Bouger selon joueur");
		}
		else {

			getRandomDirection(120);
		}
	}
	
	public void verifierDrop() {
        
        ecran.bossCombat = false;

        ecran.stopperMusique();
        ecran.jouerMusique(21);

        Progression.roiSqueletteBattu = true;

        for (int i = 0; i < ecran.obj[1].length; i++) {

            if (ecran.obj[ecran.carteActuelle][i] != null && ecran.obj[ecran.carteActuelle][i].nom.equals(PorteFer.objnom)) {
                ecran.obj[ecran.carteActuelle][i] = null;
                ecran.jouerSE(24);
            }
        }

        drop.add(new Sceptre(ecran));
	}
	
	public void attaqueReaction() {
		attente = 0;
	}

}
