package Entites;

import java.util.Random;

import main.Ecran;
import object.BouclierRenforce;
import object.Cle;
import object.EpeeNormale;
import object.Fer;
import object.Mana;
import object.PorteFer;
import object.PotionSoin;

public class Forgeron extends Entite {

	public Forgeron(Ecran ecran) {
		super(ecran);
		
		direction = "bas";
		vitesse = 0;
		
		getImage();
		setDialogues();
		setItems();

	}
	
	public void setDialogues() {
		
		dialogue[0][0] = "Ho, ho, tu m'a trouvé, j'ai\ndes compétences intéressantes pour\ntoi !";
        dialogue[0][1] = "Je t'ouvre les portes de ma\nforge !";
        dialogue[0][2] = "Je suis le forgeron, je peux\naméliorer ton équipement !";
        dialogue[0][3] = "Je t'ai aussi ouvert les portes\n de la mairie ! Tu devrais aller\nvoir le maire.";
		dialogue[1][0] = "Reviens quand tu veux hehehe !";
		dialogue[2][0] = "Vous n'avez pas assez d'argent !";
		dialogue[3][0] = "Votre inventaire est plein !";
		dialogue[4][0] = "Vous ne pouvez pas forger\ncet équipement !";
		dialogue[5][0] = "Vous ne pouvez pas forger une\npartie de votre corps !";
        dialogue[6][0] = "Vous ne pouvez pas forger\nvotre équipement actuel !";
        dialogue[7][0] = "Cet objet ne peux pas être\nforgé plus de fois !";
        dialogue[8][0] = "La forge est un succés, votre\néquipement deviens plus fort !";
        dialogue[9][0] = "Malheureusement la forge a\néchouée, vous récupérez votre objet !";

		dialogue[10][0] = "Ho ho tu as été parlé au maire !";
		dialogue[10][1] = "Il t'a dis que je t'aiderai ?";
		dialogue[10][2] = "Cependant je ne travaille pas gratuitement !";
		dialogue[10][3] = "En ce moment je manque de materiel !";
		dialogue[10][4] = "Récupère moi 10 minerais de fer et\nje t'aiderai !";
		dialogue[10][5] = "Cherche la mine dans la foret derrière la mairie.";

		dialogue[11][0] = "Tu as les minerais de fer ?";
		
		dialogue[12][0] = "Je tiens toujours parole !";
		dialogue[12][1] = "Tu trouveras ce que tu cherches dans la fontaine\ndu village !";

		dialogue[13][0] = "Tu te moques de moi ?";
		dialogue[13][1] = "Je ne parle plus avec toi tant que tu n'as pas\naccompli ma mission !";
	}
	
	public void parler() {
		regarderJoueur();
		if (ecran.joueur.queteEnCours == 3) {
			commencerDialogue(this, 9);
			ecran.joueur.queteEnCours++;
		}
		else if (ecran.joueur.queteEnCours == 4) {
			commencerDialogue(this, 10);
			int  ferIndice = 0;
			int nbPossedes = 0;
			for (int i = 0; i < ecran.joueur.inventaire.size(); i++) {
				if (ecran.joueur.inventaire.get(i) != null && ecran.joueur.inventaire.get(i).nom.equals(Fer.objnom)) {
					ferIndice = i;
					nbPossedes = ecran.joueur.inventaire.get(i).possedes;
					break;
				}
			}
			if (nbPossedes < 10) {
				commencerDialogue(this, 12);
			}
			else {
				commencerDialogue(this, 11);
				if (nbPossedes == 10) {
					ecran.joueur.inventaire.remove(ferIndice);
				}
				else {
					ecran.joueur.inventaire.get(ferIndice).possedes -= 10;
				}
				ecran.joueur.queteEnCours++;
			}
		}
		else {
			ecran.etatJeu = ecran.forger;
			ecran.interfaceJoueur.npc = this;
		}

        for (int i = 0; i < ecran.obj[1].length; i++) {
            if (ecran.obj[ecran.carteActuelle][i] != null && ecran.obj[ecran.carteActuelle][i].nom.equals(PorteFer.objnom)) {
                ecran.obj[ecran.carteActuelle][i] = null;
                ecran.jouerSE(24);
            }
        }
	}
	
	public void getImage() {
		avant = initialiser("/mage/forgeronAvant.png", ecran.tailleFinale, ecran.tailleFinale);
		
		arriere = initialiser("/mage/forgeronAvant.png", ecran.tailleFinale, ecran.tailleFinale);

		gauche = initialiser("/mage/forgeronAvant.png", ecran.tailleFinale, ecran.tailleFinale);

		droite = initialiser("/mage/forgeronAvant.png", ecran.tailleFinale, ecran.tailleFinale);
		
		avant1 = initialiser("/mage/forgeronAvant1.png", ecran.tailleFinale, ecran.tailleFinale);
		
		arriere1 = initialiser("/mage/forgeronAvant1.png", ecran.tailleFinale, ecran.tailleFinale);

		gauche1 = initialiser("/mage/forgeronAvant1.png", ecran.tailleFinale, ecran.tailleFinale);

		droite1 = initialiser("/mage/forgeronAvant1.png", ecran.tailleFinale, ecran.tailleFinale);
	}
	
	public void setItems() {

	}
}
