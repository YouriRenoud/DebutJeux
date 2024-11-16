package Entites;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.ActionClavier;
import main.Ecran;
import object.BouclierBasique;
import object.BouleDeFeu;
import object.Cle;
import object.EpeeNormale;
import object.HacheEnPierre;

public class Joueur extends Entite {
	
	ActionClavier action;
	
	public final int ecranX;
	public final int ecranY;
	
	public boolean toucheEnfoncee;
	
	public boolean annulerAttaque = false;

	public boolean lumiereChangee = false;
		
	public Joueur(Ecran ecran, ActionClavier action) {
		
		super(ecran);
		
		this.action = action;
		
		this.ecranX = ecran.ecranLongueur/2 - ecran.tailleFinale/2;
		this.ecranY = ecran.ecranLargeur/2 - ecran.tailleFinale/2;
		
		aireCollision = new Rectangle(15, 25, 23, 25);
		
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
		
		this.initialiser();
		this.getImage();
		this.getAttImage();
		this.setItems();
	}

	public void setItems() {
		inventaire.clear();
		inventaire.add(armeActuelle);
		inventaire.add(bouclierActuel);
		inventaire.add(new Cle(ecran));
		inventaire.add(new EpeeNormale(ecran));
	}	
	
	public void initialiser() {
		
		carteX = ecran.tailleFinale * 23;
		carteY = ecran.tailleFinale * 21;
		//carteX = ecran.tailleFinale * 12;
		//carteY = ecran.tailleFinale * 12;
		vitesseDefaut = 3;
		vitesse = vitesseDefaut;
		direction = "bas";
		
		vieMax = 10;
		vie = vieMax;
		niveau = 1;
		force = 1;
		agilite = 1;
		experience = 0;
		niveauSuivant = 5;
		argent = 1000;
		maxMana = 6;
		mana = maxMana;
		armeActuelle = new HacheEnPierre(ecran);
		bouclierActuel = new BouclierBasique(ecran);
		projectile = new BouleDeFeu(ecran);
		attaquer = getAttaque();
		defendre = getDefense();
	}
	
	public void valeurDefaut() {
		carteX = ecran.tailleFinale * 23;
		carteY = ecran.tailleFinale * 21;
		direction = "bas";
	}
	
	public void retablirVieMana() {
		vie = vieMax;
		mana = maxMana;
	}
	
	public int getAttaque() {
		attArea = armeActuelle.attArea;
		return force*armeActuelle.attVal;
	}
	
	public int getDefense() {
		return agilite*bouclierActuel.defVal;
	}
	
	public void getImage() {
			avant = initialiser("/joueur/Avant.png", ecran.tailleFinale, ecran.tailleFinale);
			
			arriere = initialiser("/joueur/Arriere.png", ecran.tailleFinale, ecran.tailleFinale);

			gauche = initialiser("/joueur/Gauche.png", ecran.tailleFinale, ecran.tailleFinale);

			droite = initialiser("/joueur/Droite.png", ecran.tailleFinale, ecran.tailleFinale);
			
			avant1 = initialiser("/joueur/Avant1.png", ecran.tailleFinale, ecran.tailleFinale);
			
			arriere1 = initialiser("/joueur/Arriere1.png", ecran.tailleFinale, ecran.tailleFinale);

			gauche1 = initialiser("/joueur/Gauche1.png", ecran.tailleFinale, ecran.tailleFinale);

			droite1 = initialiser("/joueur/Droite1.png", ecran.tailleFinale, ecran.tailleFinale);
	}
	
	public void getAttImage() {
		
		if (armeActuelle.typeEntite == epeeType) {
			attAvant = initialiser("/joueur/AttAv1.png", ecran.tailleFinale, ecran.tailleFinale*2);
			
			attArriere = initialiser("/joueur/AttAr1.png", ecran.tailleFinale, ecran.tailleFinale*2);

			attGauche = initialiser("/joueur/AttG1.png", ecran.tailleFinale*2, ecran.tailleFinale);

			attDroite = initialiser("/joueur/AttD1.png", ecran.tailleFinale*2, ecran.tailleFinale);
			
			attAvant1 = initialiser("/joueur/AttAv2.png", ecran.tailleFinale, ecran.tailleFinale*2);
			
			attArriere1 = initialiser("/joueur/AttAr2.png", ecran.tailleFinale, ecran.tailleFinale*2);

			attGauche1 = initialiser("/joueur/AttG2.png", ecran.tailleFinale*2, ecran.tailleFinale);

			attDroite1 = initialiser("/joueur/AttD2.png", ecran.tailleFinale*2, ecran.tailleFinale);	
		}
		
		if (armeActuelle.typeEntite == hacheType) {
			attAvant = initialiser("/joueur/AxAttAv1.png", ecran.tailleFinale, ecran.tailleFinale*2);
			
			attArriere = initialiser("/joueur/AxAttAr1.png", ecran.tailleFinale, ecran.tailleFinale*2);

			attGauche = initialiser("/joueur/AxAttG1.png", ecran.tailleFinale*2, ecran.tailleFinale);

			attDroite = initialiser("/joueur/AxAttD1.png", ecran.tailleFinale*2, ecran.tailleFinale);
			
			attAvant1 = initialiser("/joueur/AxAttAv2.png", ecran.tailleFinale, ecran.tailleFinale*2);
			
			attArriere1 = initialiser("/joueur/AxAttAr2.png", ecran.tailleFinale, ecran.tailleFinale*2);

			attGauche1 = initialiser("/joueur/AxAttG2.png", ecran.tailleFinale*2, ecran.tailleFinale);

			attDroite1 = initialiser("/joueur/AxAttD2.png", ecran.tailleFinale*2, ecran.tailleFinale);
		}
	}
	
	public void getDormirImage(BufferedImage im) {
		avant = im;
			
		avant1 = im;

		arriere = im;

		arriere1 = im;
		
		gauche = im;
		
		gauche1 = im;

		droite = im;

		droite1 = im;	
	}

	public void miseAJour() {
		
		if (attaque == true) {
			attaque();
		}
		
		else if (action.haut || action.bas || action.gauche || action.droite || action.attaquer) {
			if (action.haut == true) {
				direction = "haut";
			}
			if (action.bas == true) {
				direction = "bas";
			}
			if (action.gauche == true) {
				direction = "gauche";
			}
			if (action.droite == true) {
				direction = "droite";
			}
			if (action.attaquer == true) {
				if (!toucheEnfoncee) {
					attaque = true;
					toucheEnfoncee = true;
				}
				else {toucheEnfoncee = false;}
			}
						
			collision0 = false;
			ecran.collisions.AnalyserTerrain(this);
			
			int objIndex = ecran.collisions.analyserObjet(this, true);
			interactionObject(objIndex);
			
			int entIndex = ecran.collisions.analyserEntite(this, ecran.mage);
			interactionEntite(entIndex);
			
			int monstreIndex = ecran.collisions.analyserEntite(this, ecran.monstre);
			interactionMonstre(monstreIndex);
			
			int iElement = ecran.collisions.analyserEntite(this, ecran.iTerrain);
			
			
			ecran.event.analyserEvent();
						
			ecran.action.entree = false;

			if (collision0 == false && !action.attaquer) {
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
			
			if (action.attaquer && !annulerAttaque) {
				ecran.jouerSE(7);
				attaque = true;
				compteur = 0;
			}
			
			annulerAttaque = false;
			ecran.action.attaquer = false;

			compteur++;
			if (compteur > 15) {
				if (marcher == 1) {
					marcher = 2;
				}
				else if (marcher == 2) {
					marcher = 1;
				}
				compteur = 0;
			}
		}
		
		if (ecran.action.tirer == true && projectile.vivant == false
				&& tirPossible == 60 && projectile.ressourcesSuffisantes(this)) {
			projectile.initialiser(carteX, carteY, direction, true, this);
			
			projectile.utiliserRessource(this);
			//ecran.listProjectiles.add(projectile);
			for (int i = 0; i < ecran.listProjectiles[1].length; i++) {
				if (ecran.listProjectiles[ecran.carteActuelle][i] == null) {
					ecran.listProjectiles[ecran.carteActuelle][i] = projectile;
					break;
				}
			}	
			
			tirPossible = 0;
			
			ecran.jouerSE(11);
		}
		
		if (invincible == true) {
			tempsInvincible ++;
			if (tempsInvincible > 60) {
				tempsInvincible = 0;
				invincible = false;
			}
		}
		
		if (tirPossible < 60) {
			tirPossible++;
		}
		
		if (vie > vieMax) {
			vie = vieMax;
		}
		if (mana > maxMana) {
			mana = maxMana;
		}
		if (vie <= 0) {
			ecran.etatJeu = ecran.perdu;
			ecran.interfaceJoueur.numCommande = -1;
			ecran.stopperMusique();
			ecran.jouerSE(13);
		}
	}
	
	public void interactionObject(int i) {
		
		if (i != 999) {
			// juste ramasser
			if (ecran.obj[ecran.carteActuelle][i].typeEntite == ramasserType) {
				ecran.obj[ecran.carteActuelle][i].utiliser(this, i);
			}
			else if (ecran.obj[ecran.carteActuelle][i].typeEntite == obstacleType) {
				if (ecran.action.entree) {
					ecran.obj[ecran.carteActuelle][i].interaction();
					annulerAttaque = true;
				}
			}
			else {
				// Inventaire items
				String texte;

				if (itemRecuperable(ecran.obj[ecran.carteActuelle][i])) {
					ecran.jouerSE(1);
					texte = "Vous avez trouvé cet objet :\n" + ecran.obj[ecran.carteActuelle][i].nom + "!";
				}
				else {
					texte = "Inventaire plein";
				}
				ecran.interfaceJoueur.ajouterMessage(texte);
				ecran.obj[ecran.carteActuelle][i] = null;
			}	
		}
	}
	
	public void interactionMonstre(int index) {
		
		if (index != 999) {
			
			if (invincible == false && !ecran.monstre[ecran.carteActuelle][index].mort) {
				ecran.jouerSE(6);
				int degats = ecran.monstre[ecran.carteActuelle][index].attaquer - defendre;
				if (degats <= 0) {degats = 0;}
				vie -= degats;
				invincible = true;
			}
		}
	}
	
	public void interactionEntite(int index) {
		
		if (index != 999) {
			
			if(ecran.action.entree) {
				annulerAttaque = true;
				ecran.etatJeu = ecran.parler;
				ecran.mage[ecran.carteActuelle][index].parler();
			}
		}
		
	}
	
	public void attaque() {
		
		compteur++;
		
		if (compteur <= 5) {
			marcher = 1;
		}
		if (compteur > 5 && compteur <= 25) {
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
			
			int ennemiIndex = ecran.collisions.analyserEntite(this, ecran.monstre);
			blesserMonstre(ennemiIndex, attaquer, armeActuelle.reculForce);
			
			int iTerrainIndex = ecran.collisions.analyserEntite(this, ecran.iTerrain);
			attaquerTerrain(iTerrainIndex);

			int projectileIndex = ecran.collisions.analyserEntite(this, ecran.listProjectiles);
			attaquerProjectile(projectileIndex);
			
			carteX = actuelMondeX;
			carteY = actuelMondeY;
			
			aireCollision.height = aireSolHeight;
			aireCollision.width = aireSolWidth;
		}
		if (compteur > 20) {
			marcher = 1;
			compteur = 0;
			attaque = false;
		}
	}

	public void attaquerProjectile(int index) {
		if (index != 999) {
			Entite projectile = ecran.listProjectiles[ecran.carteActuelle][index];
			projectile.vie -= projectile.vieMax;
			if (projectile.vie <= 0) {
				ecran.listProjectiles[ecran.carteActuelle][index].vivant = false;
				genererParticules(projectile, projectile);
			}
		}
	}
	
	public void attaquerTerrain(int i) {
		if (i != 999 && ecran.iTerrain[ecran.carteActuelle][i].destructible
				&& ecran.iTerrain[ecran.carteActuelle][i].armeCorrecte(this) == true
				&& !ecran.iTerrain[ecran.carteActuelle][i].invincible) {
			ecran.iTerrain[ecran.carteActuelle][i].jouerSE();
			ecran.iTerrain[ecran.carteActuelle][i].vie--;
			ecran.iTerrain[ecran.carteActuelle][i].invincible = true;
			
			genererParticules(ecran.iTerrain[ecran.carteActuelle][i], ecran.iTerrain[ecran.carteActuelle][i]);
			
			if (ecran.iTerrain[ecran.carteActuelle][i].vie <= 0) {
				ecran.iTerrain[ecran.carteActuelle][i] = ecran.iTerrain[ecran.carteActuelle][i].getFormeDetruite();			
			}
		}
	}	
	
	public void blesserMonstre(int i, int attaquer, int reculForce) {
		if (i != 999) {
			if (!ecran.monstre[ecran.carteActuelle][i].invincible) {
				ecran.jouerSE(5);

				if (reculForce > 0) {
					recul(ecran.monstre[ecran.carteActuelle][i], reculForce);
				}
				
				int degats = attaquer - ecran.monstre[ecran.carteActuelle][i].defendre;
				if (degats <= 0) {degats = 0;}
				
				ecran.interfaceJoueur.ajouterMessage(degats + " degats !");
				ecran.monstre[ecran.carteActuelle][i].vie -= degats;
				ecran.monstre[ecran.carteActuelle][i].attaqueReaction();
				ecran.monstre[ecran.carteActuelle][i].invincible = true;
			
				if (ecran.monstre[ecran.carteActuelle][i].vie <= 0) {
					ecran.monstre[ecran.carteActuelle][i].mort = true;
					ecran.interfaceJoueur.ajouterMessage(ecran.monstre[ecran.carteActuelle][i].nom + " est mort !");
					ecran.joueur.experience += ecran.monstre[ecran.carteActuelle][i].experience;
					ecran.interfaceJoueur.ajouterMessage(ecran.monstre[ecran.carteActuelle][i].experience + " d'experience gagné !");
					verifierNiveau();
				}
			}
			if (ecran.monstre[ecran.carteActuelle][i].vie <= 0) {
				ecran.monstre[ecran.carteActuelle][i].attaquer = 0;
			}
		}
	}

	public void recul(Entite entite, int reculForce) {

		entite.direction = direction;
		entite.vitesse += reculForce;
		entite.recul = true;
	}

	public void verifierNiveau() {
		if (experience >= niveauSuivant) {
			niveau ++;
			experience -= niveauSuivant;
			niveauSuivant *= 2;
			vieMax += 2;
			vie = vieMax;
			mana = maxMana;
			force++;
			agilite++;
			attaquer = getAttaque();
			defendre = getDefense();
			
			ecran.jouerSE(9);
			ecran.etatJeu = ecran.parler;
			ecran.interfaceJoueur.dialogueCourant = "Tu es passé niveau : " + niveau + " te sens tu plus fort";
		}
	}	
	
	public int chercherItemInventaire(String nom) {

		int itemIndex = 999;

		for (int i = 0; i < inventaire.size(); i++) {
			if (inventaire.get(i).nom.equals(nom)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}

	public boolean itemRecuperable(Entite item) {

		boolean obtainable = false;

		if (item.empillable) {
			int index = chercherItemInventaire(item.nom);

			if (index != 999) {
				inventaire.get(index).possedes++;
				obtainable = true;
			}
			else {
				if (inventaire.size() < tailleInventaireMax) {
					inventaire.add(item);
					obtainable = true;
				}
			}
		}
		else {
			if (inventaire.size() < tailleInventaireMax) {
				inventaire.add(item);
				obtainable = true;
			}
		}
		return obtainable;
	}

	public void selectionnerItem() {
		int itemIndex = ecran.interfaceJoueur.getItemIndexSelectionne(ecran.interfaceJoueur.emplacementCol, ecran.interfaceJoueur.emplacementLign);
		
		if (itemIndex < inventaire.size()) {
			Entite itemSelectionne = inventaire.get(itemIndex);
			
			if (itemSelectionne.typeEntite == epeeType || itemSelectionne.typeEntite == hacheType) {
				armeActuelle = itemSelectionne;
				attaquer = getAttaque();
				getAttImage();
			}
			
			if (itemSelectionne.typeEntite == bouclierType) {
				bouclierActuel = itemSelectionne;
				defendre = getDefense();
			}
			
			if (itemSelectionne.typeEntite == utilitaireType) {
				itemSelectionne.utiliser(this, itemIndex);
			}

			if (itemSelectionne.typeEntite == lumiereType) {
				if (lumiereActuelle == itemSelectionne) {
					lumiereActuelle = null;
				}
				else {
					lumiereActuelle = itemSelectionne;
				}
				lumiereChangee = true;
			}
		}
	}	

	public void afficher(Graphics2D graph2) {
		//graph2.setColor(Color.black);
		//graph2.fillRect(x, y, ecran.tailleFinale, ecran.tailleFinale);
		
		BufferedImage image = null;
		int modifEcranX = ecranX;
		int modifEcranY = ecranY;
		
		switch(direction) {
		case "haut":
			if (attaque) {
				modifEcranY = ecranY - ecran.tailleFinale;
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
				modifEcranX = ecranX - ecran.tailleFinale;
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
		
		if (invincible == true) {
			graph2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
		}
		graph2.drawImage(image, modifEcranX, modifEcranY, null);
		graph2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

	}

}
