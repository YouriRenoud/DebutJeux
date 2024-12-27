package object;

import java.awt.Color;
import java.awt.Rectangle;

import Entites.Entite;
import Entites.Projectiles;
import main.Ecran;

public class TeteChercheuse extends Projectiles {

	Ecran ecran;
	public static final String objnom = "Boule de feu";

	public TeteChercheuse(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;
		nom = objnom;
		vitesse = 2;
		vieMax = 600;
		vie = vieMax;
		attaquer = 4*ecran.carteActuelle;
		coutUtilisation = 1;
		vivant = false;
		reculForce = 10;
        intelligent = true;
        aireCollision = new Rectangle(0, 0, 35, 35);
        aireSolideDefautX = aireCollision.x;
        aireSolideDefautY = aireCollision.y;
		getImage();
	}
	
	public void getImage() {
		avant = initialiser("/projectiles/TeteChercheuseAvant.png", ecran.tailleFinale, ecran.tailleFinale);
		avant1 = initialiser("/projectiles/TeteChercheuseAvant1.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere = initialiser("/projectiles/TeteChercheuseArriere.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/projectiles/TeteChercheuseArriere1.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche = initialiser("/projectiles/TeteChercheuseGauche.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche1 = initialiser("/projectiles/TeteChercheuseGauche1.png", ecran.tailleFinale, ecran.tailleFinale);
		droite = initialiser("/projectiles/TeteChercheuseDroite.png", ecran.tailleFinale, ecran.tailleFinale);
		droite1 = initialiser("/projectiles/TeteChercheuseDroite1.png", ecran.tailleFinale, ecran.tailleFinale);
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
