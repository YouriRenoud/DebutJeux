package Entites;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

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
	public String direction = "haut";
	
	int marcher = 1;
	int compteur = 0;
	
	public Rectangle aireCollision = new Rectangle(0, 0, 48, 48);
	public Rectangle attArea = new Rectangle(0, 0, 0, 0);
	public int aireSolideDefautX, aireSolideDefautY;
	public boolean collision0 = false;
	public int attente = 0;
	
	String dialogue[] = new String[20];
	int dialogueIndex = 0;
	
	public int vieMax;
	public int vie;
	public boolean vivant = true;
	public boolean mort = false;
	public int mortCompteur = 0;
	public boolean vieBarreActive = false;
	public int vieBarreCompteur = 0;
	
	public boolean attaque = false;
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
	
	public int magie;
	
	public ArrayList<Entite> inventaire = new ArrayList<>();
	public final int tailleInventaireMax = 20;
	
	public Projectiles projectile;
	public int coutUtilisation;
	public int tirPossible = 0;
	
	public int attVal;
	public int defVal;
	public int valeur;
	public int type;
	public String description = "";
	public int prix;
	
	public BufferedImage image, image1, image2;
	public String nom;
	public boolean collision1 = false;
	
	public boolean invincible = false;
	public int tempsInvincible = 0;

	public boolean enChemin = false;
	
	public int typeEntite;
	public final int joueurType = 0;
	public final int iaType = 1;
	public final int monstreType = 2;
	public final int epeeType = 3;
	public final int bouclierType = 4;
	public final int hacheType = 5;
	public final int utilitaireType = 6;
	public final int ramasserType = 7;
	
	public Entite(Ecran ecran) {
		this.ecran = ecran;
	}
		
	public void utiliser(Entite entite, int index) {}
	
	public void verifierDrop() {}
	
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
	
	public int getParticuleMaxVie() {
		return 0;
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
	
	public void parler() {

		if (dialogue[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		ecran.interfaceJoueur.dialogueCourant = dialogue[dialogueIndex];
		dialogueIndex++;
		
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

	public void miseAJour() {
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
		
		if (invincible == true) {
			tempsInvincible ++;
			if (tempsInvincible > 40) {
				tempsInvincible = 0;
				invincible = false;
			}
		}
		
		if (tirPossible < 60) {
			tirPossible++;
		}
	}
	
	public void degatJoueur(int attaquer) {
		if (ecran.joueur.invincible == false) {
			ecran.jouerSE(6);
			int degats = attaquer - ecran.joueur.defendre;
			if (degats <= 0) {degats = 0;}
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
			else if (eHautY > ySuivant && eGaucheX > xSuivant) {

				direction = "haut";
				verifierCollision();
				if (collision1 == true) {
					direction = "gauche";
				}
			}
			else if (eHautY > ySuivant && eGaucheX < xSuivant) {

				direction = "haut";
				verifierCollision();
				if (collision1 == true) {
					direction = "droite";
				}
			}
			else if (eHautY < ySuivant && eGaucheX > xSuivant) {

				direction = "bas";
				verifierCollision();
				if (collision1 == true) {
					direction = "gauche";
				}
			}
			else if (eHautY < ySuivant && eGaucheX < xSuivant) {

				direction = "bas";
				verifierCollision();
				if (collision1 == true) {
					direction = "droite";
				}
			}

			int colSuivante = ecran.chemin.cheminList.get(0).col;
			int lignSuivante = ecran.chemin.cheminList.get(0).lign;
			if (colSuivante == arriveeCol && lignSuivante == arriveeLign) {
				enChemin = false;
			}
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
	
	public void afficher(Graphics2D graph) {
		int actuelX = carteX - ecran.joueur.carteX + ecran.joueur.ecranX;
		int actuelY = carteY - ecran.joueur.carteY + ecran.joueur.ecranY;
		
		BufferedImage image = null;

		if (carteX + ecran.tailleFinale > ecran.joueur.carteX - ecran.joueur.ecranX 
				&& carteX - ecran.tailleFinale < ecran.joueur.carteX + ecran.joueur.ecranX
				&& carteY + ecran.tailleFinale > ecran.joueur.carteY - ecran.joueur.ecranY 
				&& carteY - ecran.tailleFinale < ecran.joueur.carteY + ecran.joueur.ecranY) { 
			
			switch(direction) {
			case "haut":
				if (marcher == 1) {
					image = arriere;
				}
				if (marcher == 2) {
					image = arriere1;
				}
				break;
			case "bas":
				if (marcher == 1) {
					image = avant;
				}
				if (marcher == 2) {
					image = avant1;
				}
				break;
			case "gauche":
				if (marcher == 1) {
					image = gauche;
				}
				if (marcher == 2) {
					image = gauche1;
				}
				break;
			case "droite":
				if (marcher == 1) {
					image = droite;
				}
				if (marcher == 2) {
					image = droite1;
				}
				break;
			}
			
			if (this.typeEntite == 2 && vieBarreActive) {
				
				double echelle = (double)(ecran.tailleFinale-20)/vieMax;
				double vieBarre = echelle*vie;
				
				graph.setColor(new Color(35,35,35));
				graph.fillRect(actuelX+9, actuelY-1, ecran.tailleFinale-18, 7);
				
				graph.setColor(new Color(255, 0, 30));
				graph.fillRect(actuelX+10, actuelY, (int)vieBarre, 5);
				
				vieBarreCompteur ++;
				
				if (vieBarreCompteur > 300) {
					vieBarreCompteur = 0;
					vieBarreActive = false;
				}
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
			
			graph.drawImage(image, actuelX, actuelY, null);
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
