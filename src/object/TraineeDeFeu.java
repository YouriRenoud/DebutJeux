package object;

import java.awt.Color;

import Entites.Projectiles;
import main.Ecran;

public class TraineeDeFeu extends Projectiles {

	Ecran ecran;
	public static final String objnom = "Trainee de feu";

	public TraineeDeFeu(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;
		nom = objnom;
		vitesse = 0;
		vieMax = 200;
		vie = vieMax;
		attaquer = 1;
		vivant = false;
        rayonLumiere = 250;
		getImage();
	}
	
	public void getImage() {
		avant = initialiser("/items/AuraDeFeu1.png", ecran.tailleFinale, ecran.tailleFinale);
		avant1 = initialiser("/items/AuraDeFeu2.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere = initialiser("/items/AuraDeFeu1.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/items/AuraDeFeu2.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche = initialiser("/items/AuraDeFeu1.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche1 = initialiser("/items/AuraDeFeu2.png", ecran.tailleFinale, ecran.tailleFinale);
		droite = initialiser("/items/AuraDeFeu1.png", ecran.tailleFinale, ecran.tailleFinale);
		droite1 = initialiser("/items/AuraDeFeu2.png", ecran.tailleFinale, ecran.tailleFinale);
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
