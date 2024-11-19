package Entites;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Ecran;
import main.UtilityTool;
import object.Pieces;

public class Entite {
	
	Ecran ecran;
	
	public int carteX, carteY;
	public int vitesse;
	
	public BufferedImage avant, arriere, gauche, droite, avant1, arriere1, gauche1, droite1;
	public BufferedImage attAvant, attArriere, attGauche, attDroite, attAvant1, attArriere1, attGauche1, attDroite1;
	public BufferedImage gardeHaut, gardeBas, gardeGauche, gardeDroite;
	public String direction = "haut";
	
	int marcher = 1;
	public int compteur = 0;
	
	public Rectangle aireCollision = new Rectangle(0, 0, 48, 48);
	public Rectangle attArea = new Rectangle(0, 0, 0, 0);
	public int aireSolideDefautX, aireSolideDefautY;
	public boolean collision0 = false;
	public int attente = 0;
	
	public String dialogue[][] = new String[20][20];
	public int dialogueIndex = 0;
	public int dialogueSet = 0;
	
	public int vieMax;
	public int vie;
	public boolean vivant = true;
	public boolean mort = false;
	public int mortCompteur = 0;
	public boolean vieBarreActive = false;
	public int vieBarreCompteur = 0;
	
	public boolean attaque = false;
	public boolean recul = false;
	int reculCompteur = 0;
	public int vitesseDefaut;
	public int niveau;
	public int force;
	public int agilite;
	public int attaquer;
	public int defendre;
	public int experience;
	public int niveauSuivant;
	public int argent;
	public int maxMana;
	public int mana;
	public Entite armeActuelle;
	public Entite bouclierActuel;
	public Entite lumiereActuelle;
	public Entite chaussuresActuelles;

	public int magie;
	
	public ArrayList<Entite> inventaire = new ArrayList<>();
	public final int tailleInventaireMax = 20;
	
	public Projectiles projectile;
	public int coutUtilisation;
	public int tirPossible = 0;
	
	public int attVal;
	public int defVal;
	public int reculForce;
	public int valeur;
	public int type;
	public int rayonLumiere;
	public String description = "";
	public int prix;
	public int dureeAttFrame1;
	public int dureeAttFrame2;
	public boolean empillable = false;
	public int possedes = 1;
	public Entite attaquant;
	public String reculDirection;
	public boolean proteger = false;
	public int parerCompteur = 0;
	public int desequilibreCompteur = 0;
	public boolean desequilibre = false;
	public Entite contenu;
	public boolean ouvert = false;
	public Entite entiteReliee;
	public boolean enrage = false;
	public boolean boss = false;
	public boolean endormi = false;
	
	public BufferedImage image, image1, image2;
	public String nom;
	public boolean collision1 = false;
	
	public boolean invincible = false;
	public boolean transparent = false;
	public int tempsInvincible = 0;

	public boolean enChemin = false;

	public boolean temp = false;
	public boolean dessiner = true;
	
	public int typeEntite;
	public final int joueurType = 0;
	public final int iaType = 1;
	public final int monstreType = 2;
	public final int epeeType = 3;
	public final int bouclierType = 4;
	public final int hacheType = 5;
	public final int utilitaireType = 6;
	public final int ramasserType = 7;
	public final int obstacleType = 8;
	public final int lumiereType = 9;
	public final int piocheType = 10;
	public final int chaussureType = 11;
	
	public Entite(Ecran ecran) {
		this.ecran = ecran;
	}
		
	public void utiliser(Entite e) {}
	public void utiliser(Entite entite, int index) {}
	
	public void verifierDrop() {}

	public void interaction() {}
	
	public void initialiserContenu(Entite contenu) {}

	public void dropItem(Entite item) {
		for (int i=0; i < ecran.obj[1].length; i++) {
			if (ecran.obj[ecran.carteActuelle][i] == null) {
				ecran.obj[ecran.carteActuelle][i] = item;
				ecran.obj[ecran.carteActuelle][i].carteX = carteX;
				ecran.obj[ecran.carteActuelle][i].carteY = carteY;
				break;
			}
		}
	}
	
	public void actions() {}
	
	public void attaqueReaction() {}

	public void deplacer(String direction) {}
	
	public void resetCompteur() {
		compteur = 0;
		tempsInvincible = 0;
		tirPossible = 0;
		mortCompteur = 0;
		reculCompteur = 0;
		vieBarreCompteur = 0;
		parerCompteur = 0;
		desequilibreCompteur = 0;
		vieBarreCompteur = 0;
		attente = 0;
	}

	public int getXEcran () {
		return carteX - ecran.joueur.carteX + ecran.joueur.ecranX;
	}

	public int getYEcran () {
		return carteY - ecran.joueur.carteY + ecran.joueur.ecranY;
	}

	public int getGaucheX() {
		return carteX + aireCollision.x;
	}

	public int getDroiteX() {
		return carteX + aireCollision.x + aireCollision.width;
	}

	public int getHautY() {
		return carteY + aireCollision.y;
	}

	public int getBasY() {
		return carteY + aireCollision.y + aireCollision.height;
	}

	public int getCol() {
		return (carteX+aireCollision.x)/ecran.tailleFinale;
	}

	public int getLign() {
		return (carteY+aireCollision.y)/ecran.tailleFinale;
	}

	public int getDistanceX (Entite e) {
		return Math.abs(getXCentre() - e.getXCentre());
	}

	public int getDistanceY (Entite e) {
		return Math.abs(getYCentre() - e.getYCentre());
	}

	public int getParticuleMaxVie() {
		return 0;
	}

	public int getTerrainDistance(Entite e) {
		return (getDistanceX(e) + getDistanceY(e))/ecran.tailleFinale;
	}

	public int getColArrivee (Entite e) {
		return (e.carteX + e.aireCollision.x)/ecran.tailleFinale;
	}

	public int getLignArrivee (Entite e) {
		return (e.carteY + e.aireCollision.y)/ecran.tailleFinale;
	}
	
	public int getParticuleVitesse() {
		return 0;
	}
	
	public int getParticuleTaille() {
		return 0;
	}
	
	public Color getParticuleCouleur() {
		return null;
	}
	
	public int getDetecte(Entite e, Entite[][] obj, String type) {
		int index = 999;

		int xSuivant = e.getGaucheX();
		int ySuivant = e.getHautY();

		switch(e.direction) {
			case "haut":
				ySuivant = e.getHautY()-ecran.joueur.vitesse; break;
			case "bas":
				ySuivant = e.getBasY()+ecran.joueur.vitesse; break;
			case "gauche":
				xSuivant = e.getGaucheX()-ecran.joueur.vitesse; break;
			case "droite":
				xSuivant = e.getDroiteX()+ecran.joueur.vitesse; break;
		}

		int colSuivant = xSuivant/ecran.tailleFinale;
		int lignSuivant = ySuivant/ecran.tailleFinale;

		for (int i=0; i < obj[1].length; i++) {
			if (obj[ecran.carteActuelle][i] != null) {
				if (obj[ecran.carteActuelle][i].getCol() == colSuivant && obj[ecran.carteActuelle][i].getLign() == lignSuivant
					&& obj[ecran.carteActuelle][i].nom.equals(type)) {

					index = i;
					break;
				}
			}
		}

		return index;
	}

	public String getDirectionOpposee(String e) {
		String direction;

		switch (e) {
			case "haut": direction = "bas"; break;
			case "bas": direction = "haut"; break;
			case "gauche": direction = "droite"; break;
			case "droite": direction = "gauche"; break;
			default: direction = "haut"; break;
		}
		return direction;
	}

	public int getXCentre() {
		int centreX = carteX + gauche.getWidth()/2;
		return centreX;
	}

	public int getYCentre() {
		int centreY = carteY + avant.getHeight()/2;
		return centreY;
	}

	public void genererParticules(Entite generateur, Entite cible) {
		
		Color couleur = generateur.getParticuleCouleur();
		int taille = generateur.getParticuleTaille();
		int vitesse = generateur.getParticuleVitesse();
		int vieMax = generateur.getParticuleMaxVie();
		
		Particules p1 = new Particules(ecran, cible, couleur, taille, vitesse, vieMax, -2, -1);
		ecran.listParticules.add(p1);
		Particules p2 = new Particules(ecran, cible, couleur, taille, vitesse, vieMax, 2, -1);
		ecran.listParticules.add(p2);
		Particules p3 = new Particules(ecran, cible, couleur, taille, vitesse, vieMax, 2, 1);
		ecran.listParticules.add(p3);
		Particules p4 = new Particules(ecran, cible, couleur, taille, vitesse, vieMax, -2, 1);
		ecran.listParticules.add(p4);
	}
	
	public void regarderJoueur() {
		
		switch(ecran.joueur.direction) {
		case "haut":
			direction = "bas";
			break;
		case "bas":
			direction = "haut";
			break;
		case "gauche":
			direction = "droite";
			break;
		case "droite":
			direction = "gauche";
			break;
		}
	}

	public void parler() {}

	public void commencerDialogue(Entite e, int setNum) {
		
		ecran.etatJeu = ecran.parler;
		ecran.interfaceJoueur.npc = e;
		dialogueSet = setNum;
	}
	
	public void verifierCollision() {
		collision0 = false;
		
		ecran.collisions.AnalyserTerrain(this);
		boolean contactJoueur = ecran.collisions.analyserJoueur(this);
		ecran.collisions.analyserEntite(this, ecran.mage);
		ecran.collisions.analyserEntite(this, ecran.monstre);
		ecran.collisions.analyserEntite(this, ecran.iTerrain);
		ecran.collisions.analyserObjet(this, false);

		if (this.typeEntite == monstreType && contactJoueur == true) {
			degatJoueur(this.attaquer);
		}
	}

	public void verifierTirer(int rate, int intervalleTir) {
		int i = new Random().nextInt(rate);
		if (i <= 1 ) {				

			if (projectile.ressourcesSuffisantes(this)
					&& tirPossible <= intervalleTir) {
				projectile.initialiser(carteX, carteY, direction, true, this);
				//ecran.listProjectiles.add(projectile);
				System.out.println("sljdf");

				for (int j = 0; j < ecran.listProjectiles[1].length; j++) {
					if (ecran.listProjectiles[ecran.carteActuelle][j] == null) {
						ecran.listProjectiles[ecran.carteActuelle][j] = projectile;
						break;
					}
				}
				ecran.jouerSE(11);
				projectile.utiliserRessource(this);
				tirPossible = 0;
			}
		}
	}

	public void arreterChasse(Entite e, int distance, int rate) {

		if (getTerrainDistance(e) < distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				enChemin = true;
			}
		}
	}

	public void verifierChasse(Entite e, int distance, int rate) {

		if (getTerrainDistance(e) > distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				enChemin = false;
			}
		}
	}

	public void recul(Entite entite, int reculForce, Entite attaquant) {

		this.attaquant = attaquant;
		entite.reculDirection = attaquant.direction;
		entite.vitesse += reculForce;
		entite.recul = true;
	}

	public void verifierAttaque(int rate, int vertical, int horizontal) {

		boolean cibleRange = false;
		int xDistance = getDistanceX(ecran.joueur);
		int yDistance = getDistanceY(ecran.joueur);

		switch(direction) {
		case "haut":
			if (xDistance < horizontal && yDistance < vertical && ecran.joueur.getYCentre() < getYCentre()){
				cibleRange = true;
			}
			break;
		case "bas":
			if (xDistance < horizontal && yDistance < vertical && ecran.joueur.getYCentre() > getYCentre()){
				cibleRange = true;
			}
			break;
		case "gauche":
			if (xDistance < horizontal && yDistance < vertical && ecran.joueur.getXCentre() < getXCentre()){
				cibleRange = true;
			}
			break;
		case "droite":
			if (xDistance < horizontal && yDistance < vertical && ecran.joueur.getXCentre() > getXCentre()){
				cibleRange = true;
			}
			break;
		}

		if (cibleRange) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				attaque = true;
				tirPossible = 0;
				compteur = 0;
				marcher = 0;
			}
		}

	}

	public void attaque() {
		
		compteur++;
		
		if (compteur <= dureeAttFrame1) {
			marcher = 1;
		}
		if (compteur > dureeAttFrame1 && compteur <= dureeAttFrame2) {
			marcher = 2;
			
			int actuelMondeX = carteX;
			int actuelMondeY = carteY;
			
			int aireSolHeight = aireCollision.height;
			int aireSolWidth = aireCollision.width;
			
			switch(direction) {
			case "haut": carteY -= attArea.height + 15; break;
			case "bas": carteY += attArea.height; break;
			case "gauche": carteX -= attArea.width; break;
			case "droite": carteX += attArea.width; break;
			}
			
			aireCollision.width = attArea.width;
			aireCollision.height = attArea.height;
			
			if (typeEntite == monstreType) {
				if (ecran.collisions.analyserJoueur(this)) {
					degatJoueur(attaquer);
				}
			}
			else {
				int ennemiIndex = ecran.collisions.analyserEntite(this, ecran.monstre);
				ecran.joueur.blesserMonstre(this, ennemiIndex, attaquer, armeActuelle.reculForce);
				
				int iTerrainIndex = ecran.collisions.analyserEntite(this, ecran.iTerrain);
				ecran.joueur.attaquerTerrain(iTerrainIndex);
	
				int projectileIndex = ecran.collisions.analyserEntite(this, ecran.listProjectiles);
				ecran.joueur.attaquerProjectile(projectileIndex);
			}
			
			carteX = actuelMondeX;
			carteY = actuelMondeY;
			
			aireCollision.height = aireSolHeight;
			aireCollision.width = aireSolWidth;
		}
		if (compteur > dureeAttFrame2) {
			marcher = 1;
			compteur = 0;
			attaque = false;
		}
	}

	public void getRandomDirection(int rate) {
		attente++;
		
		if (attente > rate) {
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

	public void miseAJour() {

		if (!endormi) {
			if (recul) {

				verifierCollision();
				if (collision0) {
					reculCompteur = 0;
					recul = false;
					vitesse = vitesseDefaut;
				}
				else if (!collision0) {
					switch(reculDirection) {
						case "haut":
						carteY -= vitesse;
						break;
					case "bas":
						carteY += vitesse;
						break;
					case "gauche":
						carteX -= vitesse;
						break;
					case "droite":
						carteX += vitesse;
						break;
					}
				}
	
				reculCompteur++;
				if (reculCompteur == 10) {
					reculCompteur = 0;
					recul = false;
					vitesse = vitesseDefaut;
				}
			}
			else if (attaque) {
				attaque();
			}
			else {
				actions();
				verifierCollision();
				
				if (collision0 == false) {
					switch(direction) {
					case "haut":
						carteY -= vitesse;
						break;
					case "bas":
						carteY += vitesse;
						break;
					case "gauche":
						carteX -= vitesse;
						break;
					case "droite":
						carteX += vitesse;
						break;
					}
				}
	
				compteur++;
				if (compteur > 24) {
					if (marcher == 1) {
						marcher = 2;
					}
					else if (marcher == 2) {
						marcher = 1;
					}
					compteur = 0;
				}
			}
			
			if (invincible == true) {
				tempsInvincible ++;
				if (tempsInvincible > 40) {
					tempsInvincible = 0;
					invincible = false;
					transparent = false;
				}
			}
			
			if (tirPossible < 60) {
				tirPossible++;
			}
			else {
				tirPossible = 0;
			}
	
			if (desequilibre) {
				desequilibreCompteur++;
				if (desequilibreCompteur > 60) {
					desequilibre = false;
					desequilibreCompteur = 0;
				}
			}
		}
		else {

		}
	}
	
	public void degatJoueur(int attaquer) {
		if (ecran.joueur.invincible == false) {
			int degats = attaquer - ecran.joueur.defendre;

			String peutProteger = getDirectionOpposee(direction);

			if (ecran.joueur.proteger && ecran.joueur.direction.equals(peutProteger)) {

				if (ecran.joueur.parerCompteur < 30) {
					degats = 0;
					ecran.jouerSE(18);
					recul(this, reculForce, ecran.joueur);
					desequilibre = true;
					compteur =- 60;
				}
				else {
					degats = (int)(degats/3);
					ecran.jouerSE(17);
				}

			}
			else {
				ecran.jouerSE(6);
			}

			if (degats > 0) {
				ecran.joueur.invincible = true;
				ecran.joueur.transparent = true;
				recul(ecran.joueur, reculForce, this);
			}

			if (degats <= 0) {degats = 1;}
			ecran.joueur.vie -= degats;
			ecran.joueur.invincible = true;
		}
	}
	
	public void chercherChemin(int arriveeCol, int arriveeLign) {

		int col = (carteX+aireCollision.x)/ecran.tailleFinale;
		int lign = (carteY+aireCollision.y)/ecran.tailleFinale;

		ecran.chemin.setNoeuds(col, lign, arriveeCol, arriveeLign, this);

		if (ecran.chemin.chercher()) {

			int xSuivant = ecran.chemin.cheminList.get(0).col*ecran.tailleFinale;
			int ySuivant = ecran.chemin.cheminList.get(0).lign*ecran.tailleFinale;
			int eGaucheX = carteX + aireCollision.x;
			int eDroiteX = carteX + aireCollision.x + aireCollision.width;
			int eHautY = carteY + aireCollision.y;
			int eBasY = carteY + aireCollision.y + aireCollision.height;

			if (eHautY > ySuivant && eGaucheX >= xSuivant && eDroiteX < xSuivant + ecran.tailleFinale) {
				direction = "haut";
			}
			else if (eHautY < ySuivant && eGaucheX >= xSuivant && eDroiteX < xSuivant + ecran.tailleFinale) {
				direction = "bas";
			}
			else if (eHautY >= ySuivant && eBasY < ySuivant + ecran.tailleFinale) {
				if (eGaucheX > xSuivant) {
					direction = "gauche";
				}
				if (eGaucheX < xSuivant) {
					direction = "droite";
				}
			}
			else if (eHautY > ySuivant && eGaucheX - ecran.tailleFinale > xSuivant) {

				direction = "haut";
				verifierCollision();
				if (collision1 == true) {
					direction = "gauche";
				}
			}
			else if (eHautY > ySuivant && eGaucheX < xSuivant) {

				direction = "haut";
				verifierCollision();
				if (collision1) {
					direction = "droite";
				}
			}
			else if (eHautY < ySuivant && eGaucheX - ecran.tailleFinale > xSuivant) {

				direction = "bas";

				verifierCollision();
				if (collision1 == true) {
					direction = "gauche";
				}
			}
			else if (eHautY < ySuivant && eGaucheX < xSuivant) {

				direction = "bas";
				verifierCollision();
				if (collision1) {
					direction = "droite";
				}
			}

			// int colSuivante = ecran.chemin.cheminList.get(0).col;
			// int lignSuivante = ecran.chemin.cheminList.get(0).lign;
			// if (colSuivante == arriveeCol && lignSuivante == arriveeLign) {
			// 	enChemin = false;
			// }
		}
	}

	public void mortEcran(Graphics2D graph) {
		mortCompteur++;
		ecran.jouerSE(8);

		int i = 5;

		if (mortCompteur <= i) {
			changerAlpha(graph, 0f);		}
		if (mortCompteur > i && mortCompteur <= i*2) {
			changerAlpha(graph, 1f);		}
		if (mortCompteur > i*2 && mortCompteur <= i*3) {
			changerAlpha(graph, 0f);		}
		if (mortCompteur > i*3 && mortCompteur <= i*4) {
			changerAlpha(graph, 1f);		}
		if (mortCompteur > i*4 && mortCompteur <= i*5) {
			changerAlpha(graph, 0f);		}
		if (mortCompteur > i*5 && mortCompteur <= i*6) {
			changerAlpha(graph, 1f);		}
		if (mortCompteur > i*6 && mortCompteur <= i*7) {
			changerAlpha(graph, 0f);		}
		if (mortCompteur > i*7 && mortCompteur <= i*8) {
			changerAlpha(graph, 1f);		}
		if (mortCompteur > i*8) {
			vivant = false;
		}
	}
	
	public void changerAlpha (Graphics2D graph, float alphaVal) {
		graph.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaVal));
	}
	
	public void bougerSelonJoueur(int intervalle) {

		attente++;

		if (attente > intervalle) {

			if (getDistanceX(ecran.joueur) > getDistanceY(ecran.joueur)) {
				if (ecran.joueur.getXCentre() < getXCentre()) {
					direction = "gauche";
				}
				else {direction = "droite";}
			}
			else if (getDistanceX(ecran.joueur) < getDistanceY(ecran.joueur)) {
				if (ecran.joueur.getYCentre() < getYCentre()) {
					direction = "haut";
				}
				else {direction = "bas";}
			}
			attente = 0;
		}
	}

	public boolean surEcran() {
		return carteX + ecran.tailleFinale*5 > ecran.joueur.carteX - ecran.joueur.ecranX
		&& carteX - ecran.tailleFinale < ecran.joueur.carteX + ecran.joueur.ecranX
		&& carteY + ecran.tailleFinale*5 > ecran.joueur.carteY - ecran.joueur.ecranY
		&& carteY - ecran.tailleFinale < ecran.joueur.carteY + ecran.joueur.ecranY;
	}

	public void afficher(Graphics2D graph) {
		
		BufferedImage image = null;

		if (surEcran()) { 
					//System.out.println(attaquer + " " + vie + " " + defendre);
					int modifEcranX = getXEcran();
					int modifEcranY = getYEcran();
					
					switch(direction) {
					case "haut":
						if (attaque) {
							modifEcranY = getYEcran() - avant.getWidth();
							if (marcher == 1) {
								image = attArriere;
							}
							if (marcher == 2) {
								image = attArriere1;
							}
						}
						if (!attaque) {
							if (marcher == 1) {
								image = arriere;
							}
							if (marcher == 2) {
								image = arriere1;
							}	
						}
						break;
					case "bas":
						if (attaque) {
							if (marcher == 1) {
								image = attAvant;
							}
							if (marcher == 2) {
								image = attAvant1;
							}
						}
						if (!attaque) {
							if (marcher == 1) {
								image = avant;
							}
							if (marcher == 2) {
								image = avant1;
							}	
						}
						break;
					case "gauche":
						if (attaque) {
							modifEcranX = getXEcran() - gauche.getWidth();
							if (marcher == 1) {
								image = attGauche;
							}
							if (marcher == 2) {
								image = attGauche1;
							}
						}
						if (!attaque) {
							if (marcher == 1) {
								image = gauche;
							}
							if (marcher == 2) {
								image = gauche1;
							}	
						}
						break;
					case "droite":
						if (attaque) {
							if (marcher == 1) {
								image = attDroite;
							}
							if (marcher == 2) {
								image = attDroite1;
							}
						}
						if (!attaque) {
							if (marcher == 1) {
								image = droite;
							}
							if (marcher == 2) {
								image = droite1;
							}	
						}
						break;
					}
			
			if (this instanceof Pieces) {
				if (this.type == 1) {
					image = arriere;
				}
				if (this.type == 2) {
					image = arriere1;
				}
				if (this.type == 3) {
					image = avant;
				}
			}
			
			if (invincible == true) {
				vieBarreActive = true;
				vieBarreCompteur = 0;
				graph.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
			}
			
			if (mort) {
				mortEcran(graph);
			}
			
			if (image != null) {
				graph.drawImage(image, modifEcranX, modifEcranY, image.getWidth(), image.getHeight(), null);
			}
			graph.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
	}
	
	public BufferedImage initialiser(String image, int hauteur, int largeur) {
		UtilityTool outil = new UtilityTool();
		BufferedImage im = null;
		
		try {
			im = ImageIO.read(getClass().getResourceAsStream(image));
			im = outil.scaleImage(im, hauteur, largeur);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return im;
	}
}
