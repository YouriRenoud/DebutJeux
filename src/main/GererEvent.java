package main;

import java.awt.Rectangle;

import Entites.Entite;
import Entites.Maire;
import donnees.Progression;
import object.AuraDeFeu;
import object.BottesHermes;
import object.BouclierFer;
import object.CapeInvisible;
import object.CarteMonde;
import object.ChaussureDeForce;
import object.ChaussuresAttaque;
import object.CleSpeciale;
import object.EgideAthena;
import object.GantsDeForce;
import object.Invocateur;
import object.LameOmbre;
import object.Malediction;
import object.MarteauThor;
import object.OrbeMagique;
import object.PorteSpeciale;
import object.EclairZeus;
import terrain.ArbreCassable;

public class GererEvent {

	Ecran ecran;
	EventRect rect[][][];
	Entite eventMaster;
	
	int eventPrecedentX, eventPrecedentY;
	boolean eventPossible = true;
	
	boolean cleGeneree = false;

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

		rect[4][51][13].x = 0;
		rect[4][51][13].y = 0;
		rect[4][51][13].width = 28;
		rect[4][51][13].height = 50;
		rect[4][51][13].rectDefautX = rect[4][51][13].x;
		rect[4][51][13].rectDefautY = rect[4][51][13].y;

		rect[4][51][14].x = 0;
		rect[4][51][14].y = 0;
		rect[4][51][14].width = 28;
		rect[4][51][14].height = 50;
		rect[4][51][14].rectDefautX = rect[4][51][14].x;
		rect[4][51][14].rectDefautY = rect[4][51][14].y;

		rect[4][8][25].x = 0;
		rect[4][8][25].y = 0;
		rect[4][8][25].width = 28;
		rect[4][8][25].height = 50;
		rect[4][8][25].rectDefautX = rect[4][8][25].x;
		rect[4][8][25].rectDefautY = rect[4][8][25].y;
	}

	public void setDialogues() {

		eventMaster.dialogue[0][0] = "Téléportation !";
		eventMaster.dialogue[1][0] = "Vous tombez dans un piège";
		eventMaster.dialogue[2][0] = "Vous recevez une bénédiction\n et la partie a été sauvée !";
		eventMaster.dialogue[3][0] = "Vous devez éliminer plus de monstres\n avant de continuer.";
		eventMaster.dialogue[4][0] = "Reviens plus tard...";
		eventMaster.dialogue[5][0] = "Trouve l'accés au lac du nord,\ntu trouveras un outil pour continuer le chemin\nau sud de cette maison !";
		eventMaster.dialogue[6][0] = "Partie sauvegardée.";
		eventMaster.dialogue[7][0] = "Va chercher la quète.";
		eventMaster.dialogue[8][0] = "Tu as atteint le niveau 10 !\nTes pairs te reconnaissent désormais\ncomme un novice de ta classe !";
		eventMaster.dialogue[8][1] = "Les dieux te récompensent pour tes efforts.";
		eventMaster.dialogue[9][0] = "Tu as atteint le niveau 25 !\nTes pairs te reconnaissent désormais\ncomme un expérimenté de ta classe !";
		eventMaster.dialogue[9][1] = "Les dieux te récompensent pour tes efforts.";
		eventMaster.dialogue[10][0] = "Tu as atteint le niveau 50 !\nTes pairs te reconnaissent désormais\ncomme l'expert de ta classe !";
		eventMaster.dialogue[10][1] = "Les dieux te récompensent pour tes efforts.";
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

			else if (touche(0, 91, 26, "bas") == true) {
				parler(ecran.mage[1][0]);
			}

			else if (touche(0, 50, 73, "toutes")) {
				eventMaster.commencerDialogue(eventMaster, 5);
				eventPossible = false;
			}

			else if (touche(0, 1, 0, "haut")) {
				visiter(0, 81, 78, ecran.dehors);
				eventPossible = false;
			}

			else if (touche(0, 81, 78, "haut")) {
				visiter(0, 1, 0, ecran.dehors);
				eventPossible = false;
			}

			else if (touche(0, 81, 87, "bas")) {
				visiter(0, 81, 82, ecran.dehors);
				rect[0][81][87].eventFini = true;
				eventPossible = true;
			}

			else if (touche(0, 81, 83, "bas")) {
				ajouterObjet(new BouclierFer(ecran), 81, 83, ecran.jouer);
				rect[0][81][83].eventFini = true;
			}

			else if (touche(0, 81, 84, "toutes")) {
				visiter(0, 81, 87, ecran.dehors);
				rect[0][81][84].eventFini = true;
			}

			else if (touche(0, 96, 76, "haut") == true) {
				visiter(3, 12, 13, ecran.dehors);
			}

			else if (touche(0, 2, 1, "toutes")) {
				ajouterObjet(new CarteMonde(ecran), 2, 1, ecran.jouer);
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

			else if (touche(0, 1, 98, "bas") == true) {
				if (ecran.joueur.monstresElimines(2)) {
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
				if (ecran.joueur.monstresElimines(2)) {
					visiter(3, 54, 63, ecran.dongeon);
				}
				else {
					eventMaster.commencerDialogue(eventMaster, 3);
				}
			}

			else if (touche(2, 38, 0, "haut")) {
				if (ecran.joueur.queteEnCours >= 6) {
					visiter(8, 49, 50, ecran.dongeon);
				}
				else {
					eventMaster.commencerDialogue(eventMaster, 4);
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

			else if (touche(4, 54, 10, "haut")) {
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
				else {
					eventMaster.commencerDialogue(eventMaster, 7);
				}
			}

			else if (touche(4, 94, 94, "toutes")) {
				if (ecran.joueur.queteEnCours == 0 && !cleGeneree) {
					for (int i=0; i < ecran.obj[1].length; i++) {
						if (ecran.obj[ecran.carteActuelle][i] == null) {
							ecran.obj[ecran.carteActuelle][i] = new CleSpeciale(ecran);
							ecran.obj[ecran.carteActuelle][i].carteX = ecran.tailleFinale*94;
							ecran.obj[ecran.carteActuelle][i].carteY = ecran.tailleFinale*94;
							cleGeneree = true;
							break;
						}
					}
				}
			}

			else if (touche(4, 84, 17, "haut")) {
				visiter(5, 51, 86, ecran.dehors);
			}

			else if (touche(4, 52, 13, "gauche") || touche(4, 51, 14, "gauche")) {
				ecran.sauverConfiguration.sauver();
				eventMaster.commencerDialogue(eventMaster, 6);
			}

			else if (touche(4, 61, 6, "droite")) {
				if (ecran.joueur.queteEnCours >= 4) {
					visiter(6, 50, 49, ecran.dongeon);
				}
			}

			else if (touche(4, 55, 50, "toutes")) {
				if (ecran.joueur.queteEnCours >= 5) {
					visiter(7, 1, 98, ecran.dehors);
				}
			}

			else if (touche(7, 98, 1, "toutes")) {
				if (ecran.joueur.monstresElimines(5)) {
					visiter(4, 55, 50, ecran.dehors);
					if (ecran.joueur.queteEnCours == 5) {
						for (int i=0; i < ecran.mage[1].length; i++) {
							if (ecran.mage[4][i] != null
							&& ecran.mage[4][i] instanceof Maire) {
								ecran.mage[4][i].quetes[ecran.joueur.queteEnCours-1] = true;
								ecran.jouerSE(27);
								break;
							}
						}
					}
				}
			}

			else if (touche(7, 19, 87, "toutes")) {
				arbre(19, 87, ecran.jouer);
			}

			else if (touche(7, 30, 75, "toutes")) {
				arbre(30, 75, ecran.jouer);
			}

			else if (touche(7, 32, 77, "toutes")) {
				arbre(32, 77, ecran.jouer);
			}

			else if (touche(5, 51, 86, "toutes")) {
				visiter(4, 55, 96, ecran.dehors);
			}

			else if (touche(6, 50, 49, "toutes")) {
				visiter(4, 61, 6, ecran.dehors);
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

			else if (ecran.joueur.niveau >= 10 && !ecran.joueur.novice) {
				passage(1);
				ecran.joueur.novice = true;
			}
			else if (ecran.joueur.niveau >= 25 && !ecran.joueur.avance) {
				passage(2);
				ecran.joueur.avance = true;
			}
			else if (ecran.joueur.niveau >= 50 && !ecran.joueur.expert) {
				passage(3);
				ecran.joueur.expert = true;
			}
		}
	}

	public void passage(int n) {
		Entite obj = null;
		if (ecran.joueur.nomClasse.equals("paladin")) {
			if (n == 1) {
				obj = new EgideAthena(ecran);
			}
			else if (n == 2) {
				obj = new MarteauThor(ecran);
			}
			else if (n == 3) {
				obj = new EclairZeus(ecran);
			}
		}
		else if (ecran.joueur.nomClasse.equals("assassin")) {
			if (n == 1) {
				obj = new LameOmbre(ecran);
			}
			else if (n == 2) {
				obj = new ChaussuresAttaque(ecran);
			}
			else if (n == 3) {
				obj = new CapeInvisible (ecran);
			}		}
		else if (ecran.joueur.nomClasse.equals("mage")) {
			if (n == 1) {
				obj = new OrbeMagique(ecran);
			}
			else if (n == 2) {
				obj = new ChaussureDeForce(ecran);
			}
			else if (n == 3) {
				obj = new AuraDeFeu(ecran);
			}		}
		else if (ecran.joueur.nomClasse.equals("necromancien")) {
			if (n == 1) {
				obj = new GantsDeForce(ecran);
			}
			else if (n == 2) {
				obj = new Malediction(ecran);
			}
			else if (n == 3) {
				obj = new Invocateur(ecran);
			}		}
		for (int i=0; i < ecran.obj[1].length; i++) {
			if (ecran.obj[ecran.carteActuelle][i] == null) {
				ecran.obj[ecran.carteActuelle][i] = obj;
				if (ecran.joueur.direction.equals("haut")) {
					ecran.obj[ecran.carteActuelle][i].carteX = ecran.joueur.carteX;
					ecran.obj[ecran.carteActuelle][i].carteY = ecran.joueur.carteY-ecran.tailleFinale;
				}
				else if (ecran.joueur.direction.equals("bas")) {
					ecran.obj[ecran.carteActuelle][i].carteX = ecran.joueur.carteX;
					ecran.obj[ecran.carteActuelle][i].carteY = ecran.joueur.carteY+ecran.tailleFinale;
				}
				else if (ecran.joueur.direction.equals("gauche")) {
					ecran.obj[ecran.carteActuelle][i].carteX = ecran.joueur.carteX-ecran.tailleFinale;
					ecran.obj[ecran.carteActuelle][i].carteY = ecran.joueur.carteY;
				}
				else if (ecran.joueur.direction.equals("droite")) {
					ecran.obj[ecran.carteActuelle][i].carteX = ecran.joueur.carteX+ecran.tailleFinale;
					ecran.obj[ecran.carteActuelle][i].carteY = ecran.joueur.carteY;
				}
				break;
			}
		}
		if (n == 1) {
			eventMaster.commencerDialogue(eventMaster, 8);
		}
		else if (n == 2) {
			eventMaster.commencerDialogue(eventMaster, 9);
		}
		else if (n == 3) {
			eventMaster.commencerDialogue(eventMaster, 10);
		}
	}

	public void ajouterObjet (Entite e, int col, int lign, int etatJeu) {
		ecran.etatJeu = etatJeu;
		for (int i = 0; i < ecran.obj[1].length; i++) {
			if (ecran.obj[ecran.carteActuelle][i] == null) {
				ecran.obj[ecran.carteActuelle][i] = e;
				ecran.obj[ecran.carteActuelle][i].carteX = ecran.tailleFinale*col;
				ecran.obj[ecran.carteActuelle][i].carteY = ecran.tailleFinale*lign;
				break;
			}
		}
		rect[ecran.carteActuelle][col][lign].eventFini = true;
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
	
	public void arbre (int col, int lign, int etatJeu) {
		ecran.jouerSE(6);
		ecran.etatJeu = etatJeu;
		for (int i = 0; i < ecran.iTerrain[1].length; i++) {
			if (ecran.iTerrain[ecran.carteActuelle][i] == null) {
				ecran.iTerrain[ecran.carteActuelle][i] = new ArbreCassable(ecran, col, lign);
				break;
			}
		}
		rect[ecran.carteActuelle][col][lign].eventFini = true;
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
			}
			if (ecran.joueur.mana < ecran.joueur.maxMana) {
				ecran.joueur.mana = ecran.joueur.maxMana;
			}
			ecran.gerer.setMonstre();
			rect[ecran.carteActuelle][col][lign].eventFini = true;
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
