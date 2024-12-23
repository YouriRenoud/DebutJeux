package Entites;

import java.awt.Rectangle;
import java.util.Random;

import main.Ecran;

public class Villageois extends Entite {

	public Villageois(Ecran ecran) {
		super(ecran);
		
		direction = "bas";
		vitesse = 1;

		dialogueSet = -1;
		
		getImage();
		setDialogues();

	}
	
	public void actions() {
			aireCollision = new Rectangle(0, 0, 48, 48);
		
			aireSolideDefautX = aireCollision.x;
			aireSolideDefautY = aireCollision.y;

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
	
	public void setDialogues() {
		
		dialogue[0][0] = "Hmmm...";
		dialogue[1][0] = "J'ai mal au dos.";
		dialogue[2][0] = "Blablabla";
		dialogue[3][0] = "Bonjour !";
		dialogue[4][0] = "Je suis fatigué.";
	}
	
	public void parler() {
		regarderJoueur();
		commencerDialogue(this, dialogueSet);
		//enChemin = true;

		dialogueSet++;
		if (dialogue[dialogueSet][0] == null) {
			dialogueSet = 0;
		}
	}
	
	public void getImage() {

        int choix = new Random().nextInt(2);
        if (choix == 0) {
            avant = initialiser("/mage/villageoisAvant.png", ecran.tailleFinale, ecran.tailleFinale);
		
            arriere = initialiser("/mage/villageoisArriere.png", ecran.tailleFinale, ecran.tailleFinale);
    
            gauche = initialiser("/mage/villageoisGauche.png", ecran.tailleFinale, ecran.tailleFinale);
    
            droite = initialiser("/mage/villageoisDroite.png", ecran.tailleFinale, ecran.tailleFinale);
            
            avant1 = initialiser("/mage/villageoisAvant1.png", ecran.tailleFinale, ecran.tailleFinale);
            
            arriere1 = initialiser("/mage/villageoisArriere1.png", ecran.tailleFinale, ecran.tailleFinale);
    
            gauche1 = initialiser("/mage/villageoisGauche1.png", ecran.tailleFinale, ecran.tailleFinale);
    
            droite1 = initialiser("/mage/villageoisDroite1.png", ecran.tailleFinale, ecran.tailleFinale);
        }
        else {
            avant = initialiser("/mage/villageoiseAvant.png", ecran.tailleFinale, ecran.tailleFinale);
		
            arriere = initialiser("/mage/villageoiseArriere.png", ecran.tailleFinale, ecran.tailleFinale);
    
            gauche = initialiser("/mage/villageoiseGauche.png", ecran.tailleFinale, ecran.tailleFinale);
    
            droite = initialiser("/mage/villageoiseDroite.png", ecran.tailleFinale, ecran.tailleFinale);
            
            avant1 = initialiser("/mage/villageoiseAvant1.png", ecran.tailleFinale, ecran.tailleFinale);
            
            arriere1 = initialiser("/mage/villageoiseArriere1.png", ecran.tailleFinale, ecran.tailleFinale);
    
            gauche1 = initialiser("/mage/villageoiseGauche1.png", ecran.tailleFinale, ecran.tailleFinale);
    
            droite1 = initialiser("/mage/villageoiseDroite1.png", ecran.tailleFinale, ecran.tailleFinale);
        }
	}
}
