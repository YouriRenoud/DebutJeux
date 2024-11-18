package main;

import java.awt.Rectangle;

import Entites.Entite;
import donnees.Progression;

public class GererEvent {

	Ecran ecran;
	EventRect rect[][][];
	Entite eventMaster;
	
	int eventPrecedentX, eventPrecedentY;
	boolean eventPossible = true;
	
	int tempCarte, tempCol, tempLign;
	
	public GererEvent (Ecran ecran) {
		this.ecran = ecran;

		eventMaster = new Entite(ecran);
		setDialogues();
		
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

	public void setDialogues() {

		eventMaster.dialogue[0][0] = "Téléportation !";
		eventMaster.dialogue[1][0] = "Vous tombez dans un piège";
		eventMaster.dialogue[2][0] = "Vous recevez une bénédiction\n et la partie a été sauvée !";
	}
	
	public void analyserEvent() {
		
		int distanceX = Math.abs(ecran.joueur.carteX - eventPrecedentX);
		int distanceY = Math.abs(ecran.joueur.carteY - eventPrecedentY);
		int distance = Math.max(distanceX, distanceY);
		if (distance > ecran.tailleFinale) {
			eventPossible = true;
		}
		
		if (eventPossible == true) {
			if (touche(0, 49, 52, "bas") == true) {
				piege(49,52,ecran.parler);
				eventPossible = false;
			}
			
			if (touche(0, 23, 18, "toutes") == true) {
				piege(23,18,ecran.parler);
				eventPossible = false;
			}
			
			else if (touche(0, 49, 39, "haut") == true) {
				soin(49,39,ecran.parler);
				eventPossible = false;
			}
			
			//else if (touche(0, 10, 10, "haut") == true) {
			//	teleporter(ecran.parler);
			//}
			
			else if (touche(0, 49, 94, "toutes") == true) {
				visiter(1, 12, 13, ecran.maison);
			}
			
			else if (touche(1, 12, 13, "toutes") == true) {
				visiter(0, 49, 94, ecran.dehors);
			}

			else if (touche(0, 1, 99, "toutes") == true) {
				visiter(8, 49, 50, ecran.dongeon);
			}

			else if (touche(8, 47, 1, "toutes") == true) {
				visiter(9, 49, 50, ecran.dongeon);
			}

			else if (touche(9, 49, 50, "toutes") == true) {
				visiter(8, 47, 1, ecran.dongeon);
			}

			// else if (touche(9, 49, 50, "toutes") == true) {
			// 	visiter(8, 49, 94);
			// }
			
			else if (touche(1, 12, 8, "haut") == true) {
				//ecran.etatJeu = ecran.marchander;
				parler(ecran.mage[1][0]);
			}

			else if (touche(9, 49, 49, "toutes") == true) {
				roiDemon();
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
			ecran.joueur.annulerAttaque = true;
			e.parler();
		}
	}
	
	public void visiter(int carte, int col, int lign, int lieu) {
		
		ecran.lieuSuivant = lieu;
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
		
		eventMaster.commencerDialogue(eventMaster, 0);
		ecran.joueur.carteX = ecran.tailleFinale*37;
		ecran.joueur.carteY = ecran.tailleFinale*10;
	}
	
	public void piege(int col, int lign, int etatJeu) {
		
		ecran.jouerSE(6);
		System.out.println(ecran.action.entree);
		eventMaster.commencerDialogue(eventMaster, 1);
		ecran.joueur.vie -= 1;
		rect[0][col][lign].eventFini = true;
		
	}
	
	public void soin(int col, int lign, int etatJeu) {
		
			ecran.jouerSE(2);
			ecran.joueur.annulerAttaque = true;
			eventMaster.commencerDialogue(eventMaster, 2);
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
			ecran.sauverConfiguration.sauver();

	}

	public void roiDemon() {
		
		if (!ecran.bossCombat && !Progression.roiDemonBattu) {
			ecran.etatJeu = ecran.scenes;
			ecran.scene.sceneNum = ecran.scene.roiDemon;
		}
	}
}
