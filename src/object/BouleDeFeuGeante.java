package object;

import java.awt.Color;
import java.awt.Rectangle;

import Entites.Entite;
import Entites.Projectiles;
import main.Ecran;

public class BouleDeFeuGeante extends Projectiles {

	Ecran ecran;
	public static final String objnom = "Boule de feu";

	public BouleDeFeuGeante(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;
		nom = objnom;
		vitesse = 7;
		vieMax = 80;
		vie = vieMax;
		attaquer = 4*ecran.carteActuelle;
		coutUtilisation = 1;
		vivant = false;
		reculForce = 10;
        aireCollision = new Rectangle(0, 0, 130, 130);
        aireSolideDefautX = aireCollision.x;
        aireSolideDefautY = aireCollision.y;
		getImage();
	}
	
	public void getImage() {
		avant = initialiser("/projectiles/BouleDeFeuAv1.png", ecran.tailleFinale*3, ecran.tailleFinale*3);
		avant1 = initialiser("/projectiles/BouleDeFeuAv2.png", ecran.tailleFinale*3, ecran.tailleFinale*3);
		arriere = initialiser("/projectiles/BouleDeFeuAr1.png", ecran.tailleFinale*3, ecran.tailleFinale*3);
		arriere1 = initialiser("/projectiles/BouleDeFeuAr2.png", ecran.tailleFinale*3, ecran.tailleFinale*3);
		gauche = initialiser("/projectiles/BouleDeFeuG1.png", ecran.tailleFinale*3, ecran.tailleFinale*3);
		gauche1 = initialiser("/projectiles/BouleDeFeuG2.png", ecran.tailleFinale*3, ecran.tailleFinale*3);
		droite = initialiser("/projectiles/BouleDeFeuD1.png", ecran.tailleFinale*3, ecran.tailleFinale*3);
		droite1 = initialiser("/projectiles/BouleDeFeuD2.png", ecran.tailleFinale*3, ecran.tailleFinale*3);
	}
	
	public boolean ressourcesSuffisantes(Entite joueur) {
		boolean resDispo = false;
		if (joueur.mana >= coutUtilisation) {
			resDispo = true;
		}
		return resDispo;
	}
	
	public void utiliserRessource(Entite joueur) {
		// this.attaquer = joueur.magie;
		joueur.mana -= coutUtilisation;
	}
	
	public int getParticuleMaxVie() {
		return 15;
	}
	
	public int getParticuleVitesse() {
		return 2;
	}
	
	public int getParticuleTaille() {
		return 8;
	}
	
	public Color getParticuleCouleur() {
		return new Color(240,50,0);
	}
	
}
