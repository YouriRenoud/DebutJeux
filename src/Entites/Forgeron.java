package Entites;

import java.util.Random;

import main.Ecran;
import object.BouclierRenforce;
import object.Cle;
import object.EpeeNormale;
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
	}
	
	public void parler() {
		regarderJoueur();
		ecran.etatJeu = ecran.forger;
		ecran.interfaceJoueur.npc = this;

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
