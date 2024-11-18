package Entites;

import java.util.Random;

import main.Ecran;
import object.BouclierRenforce;
import object.Cle;
import object.EpeeNormale;
import object.Mana;
import object.PotionSoin;

public class Marchant extends Entite {

	public Marchant(Ecran ecran) {
		super(ecran);
		
		direction = "bas";
		vitesse = 0;
		
		getImage();
		setDialogues();
		setItems();

	}
	
	public void setDialogues() {
		
		dialogue[0][0] = "Ho, ho, tu m'a trouvé, j'ai\ndes objets intéressants pour\ntoi !";
		dialogue[1][0] = "Reviens quand tu veux hehehe !";
		dialogue[2][0] = "Vous n'avez pas assez d'argent !";
		dialogue[3][0] = "Votre inventaire est plein !";
		dialogue[4][0] = "Vous ne pouvez pas vendre\nvotre équipement actuel !";

	}
	
	public void parler() {
		regarderJoueur();
		ecran.etatJeu = ecran.marchander;
		ecran.interfaceJoueur.npc = this;
	}
	
	public void getImage() {
		avant = initialiser("/mage/merchant.png", ecran.tailleFinale, ecran.tailleFinale);
		
		arriere = initialiser("/mage/merchant.png", ecran.tailleFinale, ecran.tailleFinale);

		gauche = initialiser("/mage/merchant.png", ecran.tailleFinale, ecran.tailleFinale);

		droite = initialiser("/mage/merchant.png", ecran.tailleFinale, ecran.tailleFinale);
		
		avant1 = initialiser("/mage/merchant1.png", ecran.tailleFinale, ecran.tailleFinale);
		
		arriere1 = initialiser("/mage/merchant1.png", ecran.tailleFinale, ecran.tailleFinale);

		gauche1 = initialiser("/mage/merchant1.png", ecran.tailleFinale, ecran.tailleFinale);

		droite1 = initialiser("/mage/merchant1.png", ecran.tailleFinale, ecran.tailleFinale);
	}
	
	public void setItems() {
		
		inventaire.add(new Cle(ecran));
		inventaire.add(new BouclierRenforce(ecran));
		inventaire.add(new PotionSoin(ecran));
		inventaire.add(new Mana(ecran));
		inventaire.add(new EpeeNormale(ecran));
	}
}
