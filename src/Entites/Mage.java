package Entites;

import java.awt.Rectangle;
import java.util.Random;

import main.Ecran;

public class Mage extends Entite {

	public Mage(Ecran ecran) {
		super(ecran);
		
		direction = "bas";
		vitesse = 1;
		
		getImage();
		setDialogues();

	}
	
	public void actions() {
		
		if (enChemin) {
			int arriveeCol = 10;
			int arriveeLign = 11;
			chercherChemin(arriveeCol, arriveeLign);

			aireCollision = new Rectangle(10, 25, 28, 23);
		
			aireSolideDefautX = aireCollision.x;
			aireSolideDefautY = aireCollision.y;
		}
		else {
			attente++;
		
			if (attente == 100) {
				Random alea = new Random();
				int i = alea.nextInt(100) + 1;
				
				if (i <= 25) {
					direction = "haut";
				}
				if (i > 25 && i <= 50) {
					direction = "bas";
				}
				if (i > 50 && i <= 75) {
					direction = "gauche";
				}
				if (i > 75) {
					direction = "droite";
				}
				attente = 0;
			}
		}
	}
	
	public void setDialogues() {
		
		dialogue[0] = "Bonjour jeune entrepreneur !";
		dialogue[1] = "Tu veux savoir comment créer\n ton entreprise ?";
		dialogue[2] = "En 2024 c'est très facile.";
		dialogue[3] = "Aller, achète ma formation \nen ligne";
		dialogue[4] = "Promis c'est pas une arnaque !";
	}
	
	public void parler() {
		super.parler();
		enChemin = true;
	}
	
	public void getImage() {
		avant = initialiser("/mage/oldman_down_1.png", ecran.tailleFinale, ecran.tailleFinale);
		
		arriere = initialiser("/mage/oldman_up_1.png", ecran.tailleFinale, ecran.tailleFinale);

		gauche = initialiser("/mage/oldman_left_1.png", ecran.tailleFinale, ecran.tailleFinale);

		droite = initialiser("/mage/oldman_right_1.png", ecran.tailleFinale, ecran.tailleFinale);
		
		avant1 = initialiser("/mage/oldman_down_2.png", ecran.tailleFinale, ecran.tailleFinale);
		
		arriere1 = initialiser("/mage/oldman_up_2.png", ecran.tailleFinale, ecran.tailleFinale);

		gauche1 = initialiser("/mage/oldman_left_2.png", ecran.tailleFinale, ecran.tailleFinale);

		droite1 = initialiser("/mage/oldman_right_2.png", ecran.tailleFinale, ecran.tailleFinale);
	}
}
