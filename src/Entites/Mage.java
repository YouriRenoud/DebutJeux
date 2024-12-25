package Entites;

import java.awt.Rectangle;
import java.util.Random;

import main.Ecran;

public class Mage extends Entite {

	public Mage(Ecran ecran) {
		super(ecran);
		
		direction = "bas";
		vitesse = 1;

		dialogueSet = -1;
		
		getImage();
		setDialogues();

	}
	
	public void actions() {
		
		if (enChemin) {
			int arriveeCol = 10;
			int arriveeLign = 8;
			arriveeCol = (ecran.joueur.carteX + ecran.joueur.aireCollision.x) / ecran.tailleFinale;
			arriveeLign = (ecran.joueur.carteY + ecran.joueur.aireCollision.y) / ecran.tailleFinale;
			chercherChemin(arriveeCol, arriveeLign);

			aireCollision = new Rectangle(10, 25, 28, 23);
		
			aireSolideDefautX = aireCollision.x;
			aireSolideDefautY = aireCollision.y;
		}
		else {

			aireCollision = new Rectangle(0, 0, 48, 48);
		
			aireSolideDefautX = aireCollision.x;
			aireSolideDefautY = aireCollision.y;

			if (ecran.carteActuelle == 7) {
				if (delaiSuppression) {
					compteurSuppression++;
					if (compteurSuppression >= 120) {
						delaiSuppression = false;
						compteurSuppression = 0;
						for (int i = 0; i < ecran.mage[1].length; i++) {
							if (ecran.mage[ecran.carteActuelle][i] != null 
							&& ecran.mage[ecran.carteActuelle][i].equals(this)) {
								ecran.mage[ecran.carteActuelle][i] = null;
								break;
							}
						}
					}
				}
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
	}
	
	public void setDialogues() {
		
		dialogue[0][0] = "Belle journée jeune héros !";
		dialogue[0][1] = "Moi aussi j'ai parcouru le monde pour devenir\nun grand homme.";
		dialogue[0][2] = "Et je suis un grand mage maintenant !";
		dialogue[0][3] = "Mais je suis fatigué aujourd'hui.";
		dialogue[0][4] = "C'est à ton tour de prendre la relève.";

		dialogue[1][0] = "Quand tu es fatigué tu peux aller marcher\ndans l'eau,";
		dialogue[1][1] = "ça te rendra ta force.";
		dialogue[1][2] = "Cependant les monstres réapparaitront.";
		dialogue[1][3] = "Je ne sais pas pourquoi... Mais c'est comme\nça.";
		dialogue[1][4] = "Ne te fatigue pas trop !";
		dialogue[1][5] = "Garde des forces, tu en auras besoin.";
		dialogue[1][6] = "Bon courage reviens quand tu en saura plus.";

		dialogue[2][0] = "Un grand secret ce cache dans ces contrées.";
		dialogue[2][1] = "Je connais le chemin...";
		dialogue[2][2] = "Mais je n'ai jamais trouvé la clé...";

		dialogue[3][0] = "Cette épreuve est destinée à vérifier si tu es\ndigne de te présenter devant le roi démon !";
		dialogue[3][1] = "Tu dois atteindre la fin de cette rivière avant la\nfin du temps.";
		dialogue[3][2] = "Sans quoi la porte du dongeon du roi ne s'ouvrira pas.";
		dialogue[3][3] = "Bonne chance !";
	}
	
	public void parler() {
		regarderJoueur();
		if (ecran.carteActuelle == 7) {
			commencerDialogue(this, 3);
			ecran.joueur.lancerCompteur = true;
			delaiSuppression = true;
		}
		else {
			commencerDialogue(this, dialogueSet);
			enChemin = true;
	
			dialogueSet++;
			if (dialogue[dialogueSet][0] == null) {
				dialogueSet = 0;
			}
		}
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
