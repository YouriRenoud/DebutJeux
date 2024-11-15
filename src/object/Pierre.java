package object;

import java.awt.Color;

import Entites.Entite;
import Entites.Projectiles;
import main.Ecran;

public class Pierre extends Projectiles {

	Ecran ecran;
	public Pierre(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;
		nom = "Pierre";
		vitesse = 3;
		vieMax = 80;
		vie = vieMax;
		attaquer = 3;
		coutUtilisation = 1;
		vivant = false;
		getImage();
	}
	
	public void getImage() {
		arriere = initialiser("/projectiles/Pierre.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/projectiles/Pierre.png", ecran.tailleFinale, ecran.tailleFinale);
		avant = initialiser("/projectiles/Pierre.png", ecran.tailleFinale, ecran.tailleFinale);
		avant1 = initialiser("/projectiles/Pierre.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche = initialiser("/projectiles/Pierre.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche1 = initialiser("/projectiles/Pierre.png", ecran.tailleFinale, ecran.tailleFinale);
		droite = initialiser("/projectiles/Pierre.png", ecran.tailleFinale, ecran.tailleFinale);
		droite1 = initialiser("/projectiles/Pierre.png", ecran.tailleFinale, ecran.tailleFinale);

	}
	
	public boolean ressourcesSuffisantes(Entite joueur) {
		boolean resDispo = false;
		if (joueur.magie >= coutUtilisation) {
			resDispo = true;
		}
		return resDispo;
	}
	
	public void utiliserRessource(Entite joueur) {
		joueur.magie -= coutUtilisation;
	}
	
	public int getParticuleMaxVie() {
		return 15;
	}
	
	public int getParticuleVitesse() {
		return 2;
	}
	
	public int getParticuleTaille() {
		return 4;
	}
	
	public Color getParticuleCouleur() {
		return new Color(240,50,50);
	}
}
