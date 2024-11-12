package main;

import java.awt.Rectangle;

import Entites.Entite;

public class GererEvent {

	Ecran ecran;
	EventRect rect[][][];
	
	int eventPrecedentX, eventPrecedentY;
	boolean eventPossible = true;
	
	int tempCarte, tempCol, tempLign;
	
	public GererEvent (Ecran ecran) {
		this.ecran = ecran;
		
		rect = new EventRect[ecran.maxCartes][ecran.mondeColMax][ecran.mondeLignMax];
		
		int carte = 0;
		int col = 0;
		int lign = 0;
		while (carte < ecran.maxCartes && col < ecran.mondeColMax && lign < ecran.mondeLignMax) {
			
			rect[carte][col][lign] = new EventRect();
			rect[carte][col][lign].x = 23;
			rect[carte][col][lign].y = 23;
			rect[carte][col][lign].width = 2;
			rect[carte][col][lign].height = 2;
			rect[carte][col][lign].rectDefautX = rect[carte][col][lign].x;
			rect[carte][col][lign].rectDefautY = rect[carte][col][lign].y;
			
			col++;
			if (col == ecran.mondeColMax) {
				col = 0;
				lign++;
				
				if (lign == ecran.mondeLignMax) {
					lign = 0;
					carte++;
				}
			}
		}
		
		rect[1][12][8].x = 0;
		rect[1][12][8].y = 0;
		rect[1][12][8].width = 28;
		rect[1][12][8].height = 50;
		rect[1][12][8].rectDefautX = rect[1][12][8].x;
		rect[1][12][8].rectDefautY = rect[1][12][8].y;
	}
	
	public void analyserEvent() {
		
		int distanceX = Math.abs(ecran.joueur.carteX - eventPrecedentX);
		int distanceY = Math.abs(ecran.joueur.carteY - eventPrecedentY);
		int distance = Math.max(distanceX, distanceY);
		if (distance > ecran.tailleFinale) {
			eventPossible = true;
		}
		
		if (eventPossible == true) {
			if (touche(0, 27, 16, "droite") == true) {
				piege(27,16,ecran.parler);
				eventPossible = false;
			}
			
			if (touche(0, 23, 18, "toutes") == true) {
				piege(23,18,ecran.parler);
				eventPossible = false;
			}
			
			else if (touche(0, 23, 6, "haut") == true) {
				soin(23,6,ecran.parler);
				eventPossible = false;
			}
			
			else if (touche(0, 10, 10, "haut") == true) {
				teleporter(ecran.parler);
			}
			
			else if (touche(0, 30, 6, "toutes") == true) {
				visiter(1, 12, 13);
			}
			
			else if (touche(1, 12, 13, "toutes") == true) {
				visiter(0, 30, 6);
			}
			
			else if (touche(1, 12, 8, "haut") == true) {
				//ecran.etatJeu = ecran.marchander;
				parler(ecran.mage[1][0]);
			}
		}
	}
	
	public boolean touche(int carte, int col, int lign, String direction) {
		
		boolean touche = false;
		
		if (carte == ecran.carteActuelle) {
			ecran.joueur.aireCollision.x = ecran.joueur.carteX + ecran.joueur.aireCollision.x;
			ecran.joueur.aireCollision.y = ecran.joueur.carteY + ecran.joueur.aireCollision.y;;
			rect[carte][col][lign].x = col*ecran.tailleFinale + rect[carte][col][lign].x;
			rect[carte][col][lign].y = lign*ecran.tailleFinale + rect[carte][col][lign].y;
			
			if (ecran.joueur.aireCollision.intersects(rect[carte][col][lign]) && rect[carte][col][lign].eventFini == false) {
				if (ecran.joueur.direction.contentEquals(direction) || direction.contentEquals("toutes")) {
					touche = true;

					eventPrecedentX = ecran.joueur.carteX;
					eventPrecedentY = ecran.joueur.carteY;
				}
			}
			
			ecran.joueur.aireCollision.x = ecran.joueur.aireSolideDefautX;
			ecran.joueur.aireCollision.y = ecran.joueur.aireSolideDefautY;
			rect[carte][col][lign].x = rect[carte][col][lign].rectDefautX;
			rect[carte][col][lign].y = rect[carte][col][lign].rectDefautY;	
		}
		
		return touche;
	}
	
	public void parler(Entite e) {

		if (ecran.action.entree) {
			ecran.etatJeu = ecran.parler;
			ecran.joueur.annulerAttaque = true;
			e.parler();
		}
	}
	
	public void visiter(int carte, int col, int lign) {
		
		ecran.etatJeu = ecran.transitionCartes;
		tempCarte = carte;
		tempCol = col;
		tempLign = lign;
		
//		ecran.carteActuelle = carte;
//		ecran.joueur.carteX = ecran.tailleFinale * col;
//		ecran.joueur.carteY = ecran.tailleFinale * lign;
//		eventPrecedentX = ecran.joueur.carteX;
//		eventPrecedentY = ecran.joueur.carteY;
		eventPossible = false;
		ecran.jouerSE(14);
		
	}
	
	public void teleporter(int etatJeu) {
		
		ecran.etatJeu = etatJeu;
		ecran.interfaceJoueur.dialogueCourant = "Téléportation !";
		ecran.joueur.carteX = ecran.tailleFinale*37;
		ecran.joueur.carteY = ecran.tailleFinale*10;
	}
	
	public void piege(int col, int lign, int etatJeu) {
		
		ecran.etatJeu = etatJeu;
		ecran.jouerSE(6);
		ecran.interfaceJoueur.dialogueCourant = "Vous tombez dans un piège";
		ecran.joueur.vie -= 1;
		rect[0][col][lign].eventFini = true;
		
	}
	
	public void soin(int col, int lign, int etatJeu) {
		
			ecran.etatJeu = etatJeu;
			ecran.jouerSE(2);
			ecran.joueur.annulerAttaque = true;
			ecran.interfaceJoueur.dialogueCourant = "Vous recevez une bénédiction";
			if (ecran.joueur.vie < ecran.joueur.vieMax) {
				ecran.joueur.vie = ecran.joueur.vieMax;
				ecran.gerer.setMonstre();
				rect[0][col][lign].eventFini = true;
			}
			if (ecran.joueur.mana < ecran.joueur.maxMana) {
				ecran.gerer.setMonstre();
				rect[0][col][lign].eventFini = true;
				ecran.joueur.mana = ecran.joueur.maxMana;
			}

	}
}
