package Entites;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import main.ActionClavier;
import main.Ecran;
import object.BouclierBasique;
import object.BouleDeFeu;
import object.Cle;
import object.EpeeLourde;
import object.EpeeNormale;
import object.HacheEnPierre;
import object.Lanterne;
import object.PiedsNu;
import object.Pioche;
import object.Poings;

public class Joueur extends Entite {
	
	ActionClavier action;
	
	public final int ecranX;
	public final int ecranY;

	public final int paladin = 0;
	public final int assassin = 1;
	public final int mage = 2;
	public final int necromancien = 3;

	public static final String objnom = "Joueur";

	public int classe;
	public String nomClasse = "";
	
	public boolean toucheEnfoncee;
	public boolean retour = false;

	public boolean annulerAttaque = false;
	public boolean lumiereChangee = false;

	public Boolean[] carteVisitee = new Boolean[ecran.maxCartes];
		
	public Joueur(Ecran ecran, ActionClavier action) {
		
		super(ecran);
		
		this.action = action;
		this.nom = objnom;
		this.ecranX = ecran.ecranLongueur/2 - ecran.tailleFinale/2;
		this.ecranY = ecran.ecranLargeur/2 - ecran.tailleFinale/2;
		
		aireCollision = new Rectangle(15, 25, 23, 25);
		
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
		queteEnCours = 5;
		minerais = 30;
		initialiser(0);
		
	}

	public void setItems() {
		inventaire.clear();
		inventaire.add(armeActuelle);
		inventaire.add(bouclierActuel);
		inventaire.add(new EpeeLourde(ecran));
		inventaire.add(new EpeeLourde(ecran));
		inventaire.add(chaussuresActuelles);
		inventaire.add(new Cle(ecran));
		inventaire.add(new Cle(ecran));
		inventaire.add(new Cle(ecran));
		inventaire.add(new HacheEnPierre(ecran));
		inventaire.add(new Pioche(ecran));
		inventaire.add(new Lanterne(ecran));
	}
	
	public void initialiser(int classe) {
		
		if (classe == paladin) {

			vitesseDefaut = 3;
			argent = 10;
			vieMax = 12;
			vie = vieMax;
			niveau = 1;
			force = 300;
			agilite = 3;
			maxMana = 0;
			mana = maxMana;

			nomClasse = "paladin";
		}
		else if (classe == assassin) {

			vitesseDefaut = 4;
			argent = 50;
			vieMax = 7;
			vie = vieMax;
			niveau = 1;
			force = 4;
			agilite = 0;
			maxMana = 3;
			mana = maxMana;

			nomClasse = "assassin";
		}
		else if (classe == mage) {

			vitesseDefaut = 3;
			argent = 20;
			vieMax = 8;
			vie = vieMax;
			niveau = 1;
			force = 2;
			agilite = 1;
			maxMana = 16;
			magie = 3;
			mana = maxMana;

			nomClasse = "mage";
		}
		else if (classe == necromancien) {
			
			vitesseDefaut = 2;
			argent = 10;
			vieMax = 8;
			vie = vieMax;
			niveau = 1;
			force = 1;
			agilite = 0;
			maxMana = 20;
			magie = 12;
			mana = maxMana;

			nomClasse = "necromancien";
		}

		carteX = ecran.tailleFinale * 49;
		carteY = ecran.tailleFinale * 49;
		carteX = ecran.tailleFinale * 1;
		carteY = ecran.tailleFinale * 98;
		vitesse = vitesseDefaut;
		direction = "bas";
		experience = 0;
		niveauSuivant = 5;
		armeActuelle = new Poings(ecran);
		bouclierActuel = new BouclierBasique(ecran);
		projectile = new BouleDeFeu(ecran);
		//lumiereActuelle = null;
		chaussuresActuelles = new PiedsNu(ecran);

		attaquer = getAttaque();
		defendre = getDefense();
		vitesse = getVitesse();

		this.getImage();
		this.getAttImage();
		this.getProtegerImage();
		this.setItems();
		this.setDialogues();
	}
	
	public void valeurDefaut() {

		ecran.carteActuelle = 0;
		carteX = ecran.tailleFinale * 49;
		carteY = ecran.tailleFinale * 49;
		direction = "bas";
	}
	
	public void retablirStatuts() {
		vie = vieMax;
		mana = maxMana;
		attaque = false;
		recul = false;
		vitesse = vitesseDefaut;
		lumiereChangee = true;
		invincible = false;
		transparent = false;
		proteger = false;
	}

	public int getChaussuresSlot() {
		int slot = 0;
		for (int i = 0; i < inventaire.size(); i++) {
			if (inventaire.get(i) == chaussuresActuelles) {
				slot = i;
				break;
			}
		}
		return slot;
	}
	
	public int getArmeSlot() {
		int slot = 0;
		for (int i = 0; i < inventaire.size(); i++) {
			if (inventaire.get(i) == armeActuelle) {
				slot = i;
				break;
			}
		}
		return slot;
	}

	public int getBouclierSlot() {
		int slot = 0;
		for (int i = 0; i < inventaire.size(); i++) {
			if (inventaire.get(i) == bouclierActuel) {
				slot = i;
				break;
			}
		}
		return slot;
	}

	public int getLumiereSlot() {
		int slot = 0;
		for (int i = 0; i < inventaire.size(); i++) {
			if (inventaire.get(i) == lumiereActuelle) {
				slot = i;
				break;
			}
		}
		return slot;
	}

	public int getAttaque() {
		attArea = armeActuelle.attArea;
		dureeAttFrame1 = armeActuelle.dureeAttFrame1;
		dureeAttFrame2 = armeActuelle.dureeAttFrame2;
		return force*armeActuelle.attVal;
	}
	
	public int getDefense() {
		return agilite*bouclierActuel.defVal;
	}

	public int getVitesse() {
		return vitesseDefaut + chaussuresActuelles.vitesse;
	}
	
	public void getImage() {
			avant = initialiser("/joueur/" + nomClasse + "/Avant.png", ecran.tailleFinale, ecran.tailleFinale);
			
			arriere = initialiser("/joueur/" + nomClasse + "/Arriere.png", ecran.tailleFinale, ecran.tailleFinale);

			gauche = initialiser("/joueur/" + nomClasse + "/Gauche.png", ecran.tailleFinale, ecran.tailleFinale);

			droite = initialiser("/joueur/" + nomClasse + "/Droite.png", ecran.tailleFinale, ecran.tailleFinale);
			
			avant1 = initialiser("/joueur/" + nomClasse + "/Avant1.png", ecran.tailleFinale, ecran.tailleFinale);
			
			arriere1 = initialiser("/joueur/" + nomClasse + "/Arriere1.png", ecran.tailleFinale, ecran.tailleFinale);

			gauche1 = initialiser("/joueur/" + nomClasse + "/Gauche1.png", ecran.tailleFinale, ecran.tailleFinale);

			droite1 = initialiser("/joueur/" + nomClasse + "/Droite1.png", ecran.tailleFinale, ecran.tailleFinale);
	}
	
	public void getAttImage() {
		
		if (armeActuelle.typeEntite == epeeType) {
			attAvant = initialiser("/joueur/" + nomClasse + "/AttAv1.png", ecran.tailleFinale, ecran.tailleFinale*2);
			
			attArriere = initialiser("/joueur/" + nomClasse + "/AttAr1.png", ecran.tailleFinale, ecran.tailleFinale*2);

			attGauche = initialiser("/joueur/" + nomClasse + "/AttG1.png", ecran.tailleFinale*2, ecran.tailleFinale);

			attDroite = initialiser("/joueur/" + nomClasse + "/AttD1.png", ecran.tailleFinale*2, ecran.tailleFinale);
			
			attAvant1 = initialiser("/joueur/" + nomClasse + "/AttAv2.png", ecran.tailleFinale, ecran.tailleFinale*2);
			
			attArriere1 = initialiser("/joueur/" + nomClasse + "/AttAr2.png", ecran.tailleFinale, ecran.tailleFinale*2);

			attGauche1 = initialiser("/joueur/" + nomClasse + "/AttG2.png", ecran.tailleFinale*2, ecran.tailleFinale);

			attDroite1 = initialiser("/joueur/" + nomClasse + "/AttD2.png", ecran.tailleFinale*2, ecran.tailleFinale);	
		}
		
		if (armeActuelle.typeEntite == hacheType) {
			attAvant = initialiser("/joueur/" + nomClasse + "/AxAttAv1.png", ecran.tailleFinale, ecran.tailleFinale*2);
			
			attArriere = initialiser("/joueur/" + nomClasse + "/AxAttAr1.png", ecran.tailleFinale, ecran.tailleFinale*2);

			attGauche = initialiser("/joueur/" + nomClasse + "/AxAttG1.png", ecran.tailleFinale*2, ecran.tailleFinale);

			attDroite = initialiser("/joueur/" + nomClasse + "/AxAttD1.png", ecran.tailleFinale*2, ecran.tailleFinale);
			
			attAvant1 = initialiser("/joueur/" + nomClasse + "/AxAttAv2.png", ecran.tailleFinale, ecran.tailleFinale*2);
			
			attArriere1 = initialiser("/joueur/" + nomClasse + "/AxAttAr2.png", ecran.tailleFinale, ecran.tailleFinale*2);

			attGauche1 = initialiser("/joueur/" + nomClasse + "/AxAttG2.png", ecran.tailleFinale*2, ecran.tailleFinale);

			attDroite1 = initialiser("/joueur/" + nomClasse + "/AxAttD2.png", ecran.tailleFinale*2, ecran.tailleFinale);
		}

		if (armeActuelle.typeEntite == piocheType) {
			attAvant = initialiser("/joueur/" + nomClasse + "/piocheAvant.png", ecran.tailleFinale, ecran.tailleFinale*2);
			
			attArriere = initialiser("/joueur/" + nomClasse + "/piocheArriere.png", ecran.tailleFinale, ecran.tailleFinale*2);

			attGauche = initialiser("/joueur/" + nomClasse + "/piocheGauche.png", ecran.tailleFinale*2, ecran.tailleFinale);

			attDroite = initialiser("/joueur/" + nomClasse + "/piocheDroite.png", ecran.tailleFinale*2, ecran.tailleFinale);
			
			attAvant1 = initialiser("/joueur/" + nomClasse + "/piocheAvant1.png", ecran.tailleFinale, ecran.tailleFinale*2);
			
			attArriere1 = initialiser("/joueur/" + nomClasse + "/piocheArriere1.png", ecran.tailleFinale, ecran.tailleFinale*2);

			attGauche1 = initialiser("/joueur/" + nomClasse + "/piocheGauche1.png", ecran.tailleFinale*2, ecran.tailleFinale);

			attDroite1 = initialiser("/joueur/" + nomClasse + "/piocheDroite1.png", ecran.tailleFinale*2, ecran.tailleFinale);
		}

	}
	
	public void getProtegerImage() {
		gardeBas = initialiser("/joueur/" + nomClasse + "/protegerAvant.png", ecran.tailleFinale, ecran.tailleFinale);
		gardeHaut = initialiser("/joueur/" + nomClasse + "/protegerArriere.png", ecran.tailleFinale, ecran.tailleFinale);
		gardeGauche = initialiser("/joueur/" + nomClasse + "/protegerGauche.png", ecran.tailleFinale, ecran.tailleFinale);
		gardeDroite = initialiser("/joueur/" + nomClasse + "/protegerDroite.png", ecran.tailleFinale, ecran.tailleFinale);

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

	public void setDialogues() {
		dialogue[0][0] = "Tu es passé niveau : " + niveau + " te sens tu plus fort";

		dialogue[1][0] = "Le temps est écoulé, essai encore !";
	}

	public void miseAJour() {

		if (recul) {

			collision0 = false;
			ecran.collisions.AnalyserTerrain(this);
			
			ecran.collisions.analyserObjet(this, true);
			ecran.collisions.analyserEntite(this, ecran.mage);
			ecran.collisions.analyserEntite(this, ecran.monstre);
			ecran.collisions.analyserEntite(this, ecran.iTerrain);

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

		else if (attaque == true) {
			attaque();
		}
		
		else if (action.proteger) {
			proteger = true;
			parerCompteur++;
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
			
			ecran.collisions.analyserEntite(this, ecran.iTerrain);
			
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
			proteger = false;
			parerCompteur = 0;
		
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
		else {
			compteur++;
			if (compteur == 20) {
				marcher = 1;
				compteur = 0;
			}
			proteger = false;
			parerCompteur = 0;
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
				transparent = false;
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

		if (ecran.action.godMode == false) {
			if (vie <= 0) {
				ecran.etatJeu = ecran.perdu;
				ecran.interfaceJoueur.numCommande = -1;
				ecran.stopperMusique();
				ecran.jouerSE(13);
			}
		}

		if (ecran.carteActuelle == 7) {
			if (retour) {
				ecran.event.visiter(4, 55, 50, ecran.dehors);
				retour = false;
			}
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
				if (degats <= 0) {degats = 1;}
				vie -= degats;
				invincible = true;
				transparent = true;
			}
		}
	}
	
	public void interactionEntite(int index) {
		
		if (index != 999) {
			
			if(ecran.action.entree) {
				annulerAttaque = true;
				ecran.mage[ecran.carteActuelle][index].parler();
			}

			ecran.mage[ecran.carteActuelle][index].deplacer(this.direction);
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
				ecran.iTerrain[ecran.carteActuelle][i].verifierDrop();
				ecran.iTerrain[ecran.carteActuelle][i] = ecran.iTerrain[ecran.carteActuelle][i].getFormeDetruite();
			}
		}
	}	
	
	public void blesserMonstre(Entite attaquant, int i, int attaquer, int reculForce) {
		if (i != 999) {
			if (!ecran.monstre[ecran.carteActuelle][i].invincible) {
				ecran.jouerSE(5);

				if (reculForce > 0) {
					recul(ecran.monstre[ecran.carteActuelle][i], reculForce, attaquant);
				}

				if (ecran.monstre[ecran.carteActuelle][i].desequilibre) {
					attaquer *= 5;
				}
				
				int degats = attaquer - ecran.monstre[ecran.carteActuelle][i].defendre;
				if (degats <= 0) {degats = 1;}
				
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

	public void verifierNiveau() {
		if (experience >= niveauSuivant) {
			niveau ++;		
			setDialogues();
			experience -= niveauSuivant;
			niveauSuivant *= 2;
			vie = vieMax;
			mana = maxMana;

			if (classe == 0) {
				vieMax += 2;
				force++;
				agilite++;
				magie++;
			}
			else if (classe == 1) {
				vieMax += 1;
				force += 2;
				agilite++;
			}
			else if (classe == 2) {
				vieMax += 1;
				force++;
				agilite++;
				magie += 2;
			}
			else if (classe == 3) {
				vieMax += 1;
				force++;
				magie += 2;
			}
			
			attaquer = getAttaque();
			defendre = getDefense();
			
			ecran.jouerSE(9);
			ecran.etatJeu = ecran.parler;
			commencerDialogue(this, 0);
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
		Entite itemObtenu = ecran.generateur.getObject(item.nom, 0);

		if (itemObtenu.empillable) {
			int index = chercherItemInventaire(itemObtenu.nom);

			if (index != 999) {
				inventaire.get(index).possedes++;
				obtainable = true;
			}
			else {
				if (inventaire.size() < tailleInventaireMax) {
					inventaire.add(itemObtenu);
					obtainable = true;
				}
			}
		}
		else {
			if (inventaire.size() < tailleInventaireMax) {
				inventaire.add(itemObtenu);
				obtainable = true;
			}
		}
		return obtainable;
	}

	public void selectionnerItem() {
		int itemIndex = ecran.interfaceJoueur.getItemIndexSelectionne(ecran.interfaceJoueur.emplacementCol, ecran.interfaceJoueur.emplacementLign);
		
		if (itemIndex < inventaire.size()) {
			Entite itemSelectionne = inventaire.get(itemIndex);
			
			if (itemSelectionne.typeEntite == epeeType || itemSelectionne.typeEntite == hacheType
			|| itemSelectionne.typeEntite == piocheType) {
				armeActuelle = itemSelectionne;
				attaquer = getAttaque();
				getAttImage();
			}
			
			if (itemSelectionne.typeEntite == bouclierType) {
				bouclierActuel = itemSelectionne;
				defendre = getDefense();
			}

			if (itemSelectionne.typeEntite == chaussureType) {
				chaussuresActuelles = itemSelectionne;
				vitesse = getVitesse();
			}
			else
			
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
			if (proteger) {
				image = gardeHaut;
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
			if (proteger) {
				image = gardeBas;
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
			if (proteger) {
				image = gardeGauche;
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
			if (proteger) {
				image = gardeDroite;
			}
			break;
		}

		if (ecran.carteActuelle == 7) {
			if (lancerCompteur) {
				compteARebour--;
				if (compteARebour <= 0) {
					lancerCompteur = false;
					retour = true;
					compteARebour = tempsDispo;
					commencerDialogue(this, 1);
				}
				graph2.setColor(Color.WHITE);
				graph2.setFont(graph2.getFont().deriveFont(28f));
				graph2.drawString("Temps restant : " + (int)(compteARebour/70) + " secondes", 600, 20);
			}
		}
		
		if (transparent == true) {
			graph2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
		}
		if (dessiner) {
			graph2.drawImage(image, modifEcranX, modifEcranY, null);
		}
		graph2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

	}

}
