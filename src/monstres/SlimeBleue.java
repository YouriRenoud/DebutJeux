package monstres;

import java.awt.Rectangle;
import java.util.Random;

import Entites.Entite;
import main.Ecran;
import object.Coeur;
import object.Mana;
import object.Pieces;
import object.Pierre;
import object.TeteChercheuse;

public class SlimeBleue extends Entite {
	
	public Ecran ecran;
	public static final String monstreNom = "Slime marin";
	
	public SlimeBleue (Ecran ecran, int carte) {
		super(ecran);
		this.ecran = ecran;
		
		nom = monstreNom;
		typeEntite = monstreType;
		vitesseDefaut = 1;
		vitesse = vitesseDefaut;
		vieMax = 20*carte;
		vie = vieMax;
		attaquer = 4*carte;
		defendre = 0+carte-1;
		experience = 2*carte;
		magie = 10;
        projectile = new TeteChercheuse(ecran);
		
		aireCollision.x = 3;
		aireCollision.y = 10;
		aireCollision.width = 42;
		aireCollision.height = 20;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
		
		getImage();
	}
	
	public void getImage() {
		avant = initialiser("/Monsters/SlimeBleue.png", ecran.tailleFinale, ecran.tailleFinale);
		avant1 = initialiser("/Monsters/SlimeBleue1.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere = initialiser("/Monsters/SlimeBleue.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/Monsters/SlimeBleue1.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche = initialiser("/Monsters/SlimeBleue.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche1 = initialiser("/Monsters/SlimeBleue1.png", ecran.tailleFinale, ecran.tailleFinale);
		droite = initialiser("/Monsters/SlimeBleue.png", ecran.tailleFinale, ecran.tailleFinale);
		droite1 = initialiser("/Monsters/SlimeBleue1.png", ecran.tailleFinale, ecran.tailleFinale);

	}
	
	public void actions() {
			getRandomDirection(100);

            if (enChemin) {
                verifierChasse(ecran.joueur, 12, 40);

                if (projectileLance) {
                    projectileLanceCompteur++;
                    if (projectileLanceCompteur > projectile.vieMax) {
                        projectileLance = false;
                        projectileLanceCompteur = 0;
                    }
                }
                else {
                    verifierTirer(50, 30);
                }
            }
            else {
                arreterChasse(ecran.joueur, 10, 30);
            }
	}
	
	public void verifierDrop() {
		int i = new Random().nextInt(100)+1;
		
		if (i < 50) {
			drop.add(new Pieces(ecran, 1));
		}
		if (i >= 50 && i < 80) {
			drop.add(new Coeur(ecran));
			drop.add(new Pieces(ecran, 2));
		}
		if (i >= 80 && i < 99) {
			drop.add(new Mana(ecran));
			drop.add(new Pieces(ecran, 2));
		}
		if (i == 99) {
			drop.add(new Pieces(ecran, 3));
		}

		dropItem();
	}
	
	public void attaqueReaction() {
		attente = 0;
		direction = ecran.joueur.direction;
	}

}
