package Entites;

import java.awt.Rectangle;
import java.util.Random;

import main.Ecran;

public class Maire extends Entite {

	public Maire(Ecran ecran) {
		super(ecran);
		
		direction = "gauche";
        directionActuelle = direction;
		vitesse = 1;

        quetes = new boolean[10];
		dialogueSet = -1;
		
		getImage();
		setDialogues();

	}
	
	public void actions() {
            if (!direction.equals(directionActuelle)) {
                direction = directionActuelle;
            }
			aireCollision = new Rectangle(0, 0, 48, 48);
		
			aireSolideDefautX = aireCollision.x;
			aireSolideDefautY = aireCollision.y;

			attente++;
		
			if (attente == 300) {
				if (direction == "gauche") {
                    direction = "droite";
                    directionActuelle = direction;
                }
                else {
                    direction = "gauche";
                    directionActuelle = direction;
                }
                attente = 0;
			}
	}
	
	public void setDialogues() {
		
		dialogue[0][0] = "Bonjour jeune aventurier !";
		dialogue[0][1] = "Je suis le maire de ce village.";
		dialogue[0][2] = "Je connais le chemin vers la suite\nde ta quète.";
		dialogue[0][3] = "Seuleument des obstacles m'empeche de te\nle montrer.";
		dialogue[0][4] = "Retrouve déjà la clé de la pièce\n derrière moi.";
        dialogue[0][5] = "Cherche dans les grandes maisons à\n droite du village !";

		dialogue[1][0] = "Maintenant que le vieil homme est avec toi,\ntu pourras peut-etre réussir ?";
        dialogue[1][1] = "Cependant avec ta force actuelle, le roi démon\nne te laissera aucune chance.";
        dialogue[1][2] = "Je te conseille de trouver un moyen de\nrenforcer tes capacités.";
        dialogue[1][3] = "Survis aux 10 vagues dans la zone d'entrainement\navant de revenir.";
        dialogue[1][4] = "Cherche vers la maison seul à droite du village.";
        dialogue[1][5] = "Attention l'entrée est bien cachée.";

        dialogue[10][0] = "Occupe toi d'abord de ce que je\nt'ai demandé !";
	}
	
    public void parler() {
        // Met à jour la direction actuelle et regarde le joueur
        directionActuelle = direction;
        regarderJoueur();
    
        // Gère les dialogues en fonction de l'état des quêtes
        if (ecran.joueur.queteEnCours == 0) {
            // Si aucune quête n'est en cours, lance le dialogue initial
            commencerDialogue(this, -1);
        } else if (!quetes[ecran.joueur.queteEnCours-1]) {
            // Si la quête actuelle n'est pas terminée, lance le dialogue 10
            commencerDialogue(this, 9);
        } else {
            // Si la quête actuelle est terminée, passe au dialogue suivant
            commencerDialogue(this, ecran.joueur.queteEnCours-1);
            ecran.joueur.queteEnCours++; // Passe à la quête suivante
        }
    
        // Réinitialise `dialogueSet` uniquement si nécessaire
        dialogueSet++;
        if (dialogueSet >= dialogue.length || dialogue[dialogueSet][0] == null) {
            dialogueSet = 0; // Réinitialise seulement si tous les dialogues sont épuisés
        }
    }
    
    
	
	public void getImage() {
		avant = initialiser("/mage/maireAvant.png", ecran.tailleFinale, ecran.tailleFinale);
		
		arriere = initialiser("/mage/maireArriere.png", ecran.tailleFinale, ecran.tailleFinale);

		gauche = initialiser("/mage/maireGauche.png", ecran.tailleFinale, ecran.tailleFinale);

		droite = initialiser("/mage/maireDroite.png", ecran.tailleFinale, ecran.tailleFinale);
		
		avant1 = initialiser("/mage/maireAvant1.png", ecran.tailleFinale, ecran.tailleFinale);
		
		arriere1 = initialiser("/mage/maireArriere1.png", ecran.tailleFinale, ecran.tailleFinale);

		gauche1 = initialiser("/mage/maireGauche1.png", ecran.tailleFinale, ecran.tailleFinale);

		droite1 = initialiser("/mage/maireDroite1.png", ecran.tailleFinale, ecran.tailleFinale);
	}
}
