package object;

import java.awt.Color;

import Entites.Entite;
import Entites.Projectiles;
import main.Ecran;

public class BouleDeFeu extends Projectiles {

	Ecran ecran;
	public BouleDeFeu(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;
		nom = "Boule de feu";
		vitesse = 7;
		vieMax = 80;
		vie = vieMax;
		attaquer = 5;
		coutUtilisation = 1;
		vivant = false;
		reculForce = 8;
		getImage();
	}
	
	public void getImage() {
		avant = initialiser("/projectiles/BouleDeFeuAv1.png", ecran.tailleFinale, ecran.tailleFinale);
		avant1 = initialiser("/projectiles/BouleDeFeuAv2.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere = initialiser("/projectiles/BouleDeFeuAr1.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/projectiles/BouleDeFeuAr2.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche = initialiser("/projectiles/BouleDeFeuG1.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche1 = initialiser("/projectiles/BouleDeFeuG2.png", ecran.tailleFinale, ecran.tailleFinale);
		droite = initialiser("/projectiles/BouleDeFeuD1.png", ecran.tailleFinale, ecran.tailleFinale);
		droite1 = initialiser("/projectiles/BouleDeFeuD2.png", ecran.tailleFinale, ecran.tailleFinale);
	}
	
	public boolean ressourcesSuffisantes(Entite joueur) {
		boolean resDispo = false;
		if (joueur.mana >= coutUtilisation) {
			resDispo = true;
		}
		return resDispo;
	}
	
	public void utiliserRessource(Entite joueur) {
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
