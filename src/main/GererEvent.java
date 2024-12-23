package main;

import java.awt.Rectangle;

import Entites.Entite;
import Entites.Maire;
import donnees.Progression;
import object.BottesHermes;

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

		rect[0][91][26].x = 0;
		rect[0][91][26].y = 0;
		rect[0][91][26].width = 28;
		rect[0][91][26].height = 50;
		rect[0][91][26].rectDefautX = rect[0][91][26].x;
		rect[0][91][26].rectDefautY = rect[0][91][26].y;

		rect[4][8][25].x = 0;
		rect[4][8][25].y = 0;
		rect[4][8][25].width = 28;
		rect[4][8][25].height = 50;
		rect[4][8][25].rectDefautX = rect[0][8][25].x;
		rect[4][8][25].rectDefautY = rect[0][8][25].y;
	}

	public void setDialogues() {

		eventMaster.dialogue[0][0] = "Téléportation !";
		eventMaster.dialogue[1][0] = "Vous tombez dans un piège";
		eventMaster.dialogue[2][0] = "Vous recevez une bénédiction\n et la partie a été sauvée !";
		eventMaster.dialogue[3][0] = "Vous devez éliminer plus de monstres\n avant de continuer";
	}
	
	public void analyserEvent() {
		
		int distanceX = Math.abs(ecran.joueur.carteX - eventPrecedentX);
		int distanceY = Math.abs(ecran.joueur.carteY - eventPrecedentY);
		int distance = Math.max(distanceX, distanceY);
		if (distance > ecran.tailleFinale) {
			eventPossible = true;
		}
		
		if (eventPossible == true) {
			if (touche(0, 45, 74, "bas") == true) {
				piege(45,74,ecran.parler);
				eventPossible = false;
			}
			
			if (touche(0, 53, 71, "toutes") == true) {
				piege(53,71,ecran.parler);
				eventPossible = false;
			}
			
			else if (touche(0, 49, 39, "haut") == true) {
				soin(49,39,ecran.parler);
				eventPossible = false;
			}

			else if (touche(0, 91, 26, "haut") == true) {
				parler(ecran.mage[1][0]);
			}

			else if (touche(0, 96, 76, "haut") == true) {
				visiter(3, 12, 13, ecran.dehors);
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

			else if (touche(0, 1, 99, "bas") == true) {
				if (ecran.joueur.monstresElimines()) {
					visiter(2, 27, 79, ecran.dehors);
				}
				else {
					eventMaster.commencerDialogue(eventMaster, 3);
				}
			}

			else if (touche(2, 27, 79, "toutes") == true) {
				visiter(0, 1, 99, ecran.dehors);
			}

			else if (touche(2, 73, 98, "bas") == true) {
				visiter(2, 87, 17, ecran.dehors);
			}

			else if (touche(2, 87, 17, "bas") == true) {
				visiter(2, 73, 98, ecran.dehors);
			}

			else if (touche(2, 49, 0, "haut")) {
				if (ecran.joueur.monstresElimines()) {
					visiter(3, 54, 63, ecran.dongeon);
				}
				else {
					eventMaster.commencerDialogue(eventMaster, 3);
				}
			}

			else if (touche(3, 54, 63, "bas")) {
				visiter(2, 49, 0, ecran.dehors);
			}

			else if (touche(3, 11, 17, "toutes")) {
				roisSquelette();
			}

			else if (touche(3, 27, 1, "toutes")) {
				visiter(4, 55, 96, ecran.dehors);
			}

			else if (touche(4, 3, 91, "toutes")) {
				for (int i = 0; i < ecran.obj[1].length; i++) {
					if (ecran.obj[ecran.carteActuelle][i] != null) {
						ecran.obj[ecran.carteActuelle][i] = new BottesHermes(ecran);
						break;
					}
				}
			}

			else if (touche(4, 8, 25, "haut")) {
				parler(ecran.mage[4][0]);
			}

			else if (touche(4, 54, 10, "toutes")) {
				if (ecran.joueur.queteEnCours == 0) {
					for (int i=0; i < ecran.mage[1].length; i++) {
						if (ecran.mage[ecran.carteActuelle][i] != null
						&& ecran.mage[ecran.carteActuelle][i] instanceof Maire) {
							ecran.mage[ecran.carteActuelle][i].quetes[ecran.joueur.queteEnCours] = true;
							ecran.joueur.queteEnCours++;
							ecran.jouerSE(27);
							break;
						}
					}
				}
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
		eventMaster.commencerDialogue(eventMaster, 1);
		ecran.joueur.vie -= ecran.carteActuelle+1;
		rect[ecran.carteActuelle][col][lign].eventFini = true;
	}
	
	public void soin(int col, int lign, int etatJeu) {
		
			ecran.jouerSE(2);
			ecran.joueur.annulerAttaque = true;
			eventMaster.commencerDialogue(eventMaster, 2);
			if (ecran.joueur.vie < ecran.joueur.vieMax) {
				ecran.joueur.vie = ecran.joueur.vieMax;
				ecran.gerer.setMonstre();
				rect[ecran.carteActuelle][col][lign].eventFini = true;
			}
			if (ecran.joueur.mana < ecran.joueur.maxMana) {
				ecran.gerer.setMonstre();
				rect[ecran.carteActuelle][col][lign].eventFini = true;
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

	public void roisSquelette() {
		
		if (!ecran.bossCombat && !Progression.roiSqueletteBattu) {
			ecran.etatJeu = ecran.scenes;
			ecran.scene.sceneNum = ecran.scene.roiSquelette;
		}
	}
}
