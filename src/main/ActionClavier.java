package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionClavier implements KeyListener {

	Ecran ecran;
	public boolean haut, bas, gauche, droite, entree, attaquer, tirer, proteger, deposer;
	public boolean debug = false;
	public boolean godMode = false;
	
	public ActionClavier(Ecran ecran) {
		this.ecran = ecran;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int touche = e.getKeyCode();
		
		if (ecran.etatJeu == ecran.intro) {
			etatIntro(touche);
		}
		
		else if (ecran.etatJeu == ecran.jouer) {
			etatJouer(touche);
		}
		
		else if (ecran.etatJeu == ecran.pause) {
			etatPause(touche);
		}
		
		else if (ecran.etatJeu == ecran.parler || ecran.etatJeu == ecran.scenes) {
			etatParler(touche);
		}
		
		else if (ecran.etatJeu == ecran.stats) {
			etatStats(touche);
		}
		
		else if (ecran.etatJeu == ecran.options) {
			etatOptions(touche);
		}
		
		else if (ecran.etatJeu == ecran.perdu) {
			etatPerdu(touche);
		}
		
		else if (ecran.etatJeu == ecran.marchander) {
			etatMarchant(touche);
		}

		else if (ecran.etatJeu == ecran.cartes) {
			etatCarte(touche);
		}

		else if (ecran.etatJeu == ecran.forger) {
			etatForgeron(touche);
		}
	}
	
	public void etatForgeron(int touche) {
				
		if (touche == KeyEvent.VK_SPACE) {
			entree = true;
		}
		
		if (ecran.interfaceJoueur.sousEtats == 1) {
			if (ecran.joueur.queteEnCours >= 5) {
				if (touche == KeyEvent.VK_UP) {
					ecran.interfaceJoueur.numCommande--;
					if (ecran.interfaceJoueur.numCommande < 0) {
						ecran.interfaceJoueur.numCommande = 2;
					}
					ecran.jouerSE(10);
				}
				
				if (touche == KeyEvent.VK_DOWN) {
					ecran.interfaceJoueur.numCommande++;
					if (ecran.interfaceJoueur.numCommande > 2) {
						ecran.interfaceJoueur.numCommande = 0;
					}
					ecran.jouerSE(10);
				}
			}
			else {
				if (touche == KeyEvent.VK_UP) {
					ecran.interfaceJoueur.numCommande--;
					if (ecran.interfaceJoueur.numCommande < 0) {
						ecran.interfaceJoueur.numCommande = 2;
					}
					ecran.jouerSE(10);
				}
				
				if (touche == KeyEvent.VK_DOWN) {
					ecran.interfaceJoueur.numCommande++;
					if (ecran.interfaceJoueur.numCommande > 1) {
						ecran.interfaceJoueur.numCommande = 0;
					}
					ecran.jouerSE(10);
				}
			}
		}

		if (ecran.interfaceJoueur.sousEtats == 2) {
			joueurInventaire(touche);
			if (touche == KeyEvent.VK_ESCAPE) {
				ecran.interfaceJoueur.sousEtats = 1;
			}
		}

		if (ecran.interfaceJoueur.sousEtats == 3) {
			npcInventaire(touche);
			if (touche == KeyEvent.VK_ESCAPE) {
				ecran.interfaceJoueur.sousEtats = 1;
			}
		}

		if (ecran.interfaceJoueur.sousEtats == 4) {
			joueurInventaire(touche);
			if (touche == KeyEvent.VK_ESCAPE) {
				ecran.interfaceJoueur.sousEtats = 1;
			}
		}

		
	}

	public void etatCarte(int touche) {
		if (touche == KeyEvent.VK_M) {
			ecran.etatJeu = ecran.jouer;
		}
	}

	public void etatMarchant(int touche) {
		
		if (touche == KeyEvent.VK_SPACE) {
			entree = true;
		}
		
		if (ecran.interfaceJoueur.sousEtats == 0) {
			if (touche == KeyEvent.VK_UP) {
				ecran.interfaceJoueur.numCommande--;
				if (ecran.interfaceJoueur.numCommande < 0) {
					ecran.interfaceJoueur.numCommande = 2;
				}
				ecran.jouerSE(10);
			}
			
			if (touche == KeyEvent.VK_DOWN) {
				ecran.interfaceJoueur.numCommande++;
				if (ecran.interfaceJoueur.numCommande > 2) {
					ecran.interfaceJoueur.numCommande = 0;
				}
				ecran.jouerSE(10);
			}
		}

		if (ecran.interfaceJoueur.sousEtats == 1) {
			npcInventaire(touche);
			if (touche == KeyEvent.VK_ESCAPE) {
				ecran.interfaceJoueur.sousEtats = 0;
			}
		}

		if (ecran.interfaceJoueur.sousEtats == 2) {
			joueurInventaire(touche);
			if (touche == KeyEvent.VK_ESCAPE) {
				ecran.interfaceJoueur.sousEtats = 0;
			}
		}
	}
	
	public void etatPerdu(int touche) {
		
		if (touche == KeyEvent.VK_UP) {
			ecran.interfaceJoueur.numCommande--;
			if (ecran.interfaceJoueur.numCommande < 0) {
				ecran.interfaceJoueur.numCommande = 1;
			}
			ecran.jouerSE(10);
		}
		if (touche == KeyEvent.VK_DOWN) {
			ecran.interfaceJoueur.numCommande++;
			if (ecran.interfaceJoueur.numCommande > 1) {
				ecran.interfaceJoueur.numCommande = 0;
			}
			ecran.jouerSE(10);
		}
		if (touche == KeyEvent.VK_SPACE) {
			if (ecran.interfaceJoueur.numCommande == 0) {
				ecran.etatJeu = ecran.jouer;
				ecran.resetJeu(false);
				if (ecran.musique.clip != null) {
					ecran.stopperMusique();
				}
				ecran.jouerMusique(0);
			}
			else if (ecran.interfaceJoueur.numCommande == 1) {
				ecran.etatJeu = ecran.intro;
				ecran.interfaceJoueur.numCommande = 0;
				ecran.resetJeu(true);
			}
		}
	}
	
	public void etatOptions(int touche) {
		if (touche == KeyEvent.VK_ESCAPE) {
			ecran.etatJeu = ecran.jouer;
		}
		if (touche == KeyEvent.VK_SPACE) {
			entree = true;
		}
		
		int maxNumCommande = 0;
		switch(ecran.interfaceJoueur.sousEtats) {
		case 0: maxNumCommande = 5; break;
		case 3: maxNumCommande = 1; break;
		}
		if (touche == KeyEvent.VK_UP) {
			ecran.interfaceJoueur.numCommande--;
			ecran.jouerSE(10);
			if (ecran.interfaceJoueur.numCommande < 0) {
				ecran.interfaceJoueur.numCommande = maxNumCommande;
			}
		}
		if (touche == KeyEvent.VK_DOWN) {
			ecran.interfaceJoueur.numCommande++;
			ecran.jouerSE(10);
			if (ecran.interfaceJoueur.numCommande > maxNumCommande) {
				ecran.interfaceJoueur.numCommande = 0;
			}
		}
		if (touche == KeyEvent.VK_LEFT) {
			if (ecran.interfaceJoueur.sousEtats == 0) {
				if (ecran.interfaceJoueur.numCommande == 1 && ecran.musique.echelleVolume > 0) {
					ecran.musique.echelleVolume--;
					ecran.musique.verifierVolume();
					ecran.jouerSE(10);
				}
				if (ecran.interfaceJoueur.numCommande == 2 && ecran.son.echelleVolume > 0) {
					ecran.son.echelleVolume--;
					ecran.jouerSE(10);
				}
			}
		}
		if (touche == KeyEvent.VK_RIGHT) {
			if (ecran.interfaceJoueur.sousEtats == 0) {
				if (ecran.interfaceJoueur.numCommande == 1 && ecran.musique.echelleVolume < 5) {
					ecran.musique.echelleVolume++;
					ecran.musique.verifierVolume();
					ecran.jouerSE(10);
				}
				if (ecran.interfaceJoueur.numCommande == 2 && ecran.son.echelleVolume < 5) {
					ecran.son.echelleVolume++;
					ecran.jouerSE(10);
				}
			}
		}
	}
	
	public void etatIntro (int touche) {
		if (ecran.interfaceJoueur.introNum == 0) {
			if (touche == KeyEvent.VK_UP) {
				ecran.interfaceJoueur.numCommande--;
			}
			
			if (touche == KeyEvent.VK_DOWN) {
				ecran.interfaceJoueur.numCommande++;
			}
			
			if (touche == KeyEvent.VK_SPACE) {
				
				if (ecran.interfaceJoueur.numCommande == 0) {
					ecran.interfaceJoueur.introNum = 1;
					if (ecran.musique.clip != null) {
						ecran.stopperMusique();
					}
					ecran.jouerMusique(0);
				}
				
				if (ecran.interfaceJoueur.numCommande == 1) {
					ecran.sauverConfiguration.charger();
					ecran.joueur.getImage();
					ecran.joueur.getAttImage();
					ecran.joueur.getProtegerImage();
					ecran.etatJeu = ecran.jouer;
					if (ecran.musique.clip != null) {
						ecran.stopperMusique();
					}
					ecran.jouerMusique(0);
				}
				
				if (ecran.interfaceJoueur.numCommande == 2) {
					System.exit(0);
				}
			}
		}
		
		else if (ecran.interfaceJoueur.introNum == 1) {
			if (touche == KeyEvent.VK_UP) {
				ecran.interfaceJoueur.numCommande--;
			}
			
			if (touche == KeyEvent.VK_DOWN) {
				ecran.interfaceJoueur.numCommande++;
			}
			
			if (touche == KeyEvent.VK_SPACE) {
				
				if (ecran.interfaceJoueur.numCommande == 0) {
					ecran.etatJeu = ecran.jouer;
					ecran.joueur.classe = 0;
					ecran.joueur.initialiser(0);
				}
				
				if (ecran.interfaceJoueur.numCommande == 1) {
					ecran.etatJeu = ecran.jouer;
					ecran.joueur.classe = 1;
					ecran.joueur.initialiser(1);
				}
				
				if (ecran.interfaceJoueur.numCommande == 2) {
					ecran.etatJeu = ecran.jouer;
					ecran.joueur.classe = 2;
					ecran.joueur.initialiser(2);
				}
				
				if (ecran.interfaceJoueur.numCommande == 3) {
					ecran.etatJeu = ecran.jouer;
					ecran.joueur.classe = 3;
					ecran.joueur.initialiser(3);;
				}
				
				if (ecran.interfaceJoueur.numCommande == 4) {
					ecran.interfaceJoueur.introNum = 0;
					ecran.interfaceJoueur.numCommande = 0;
				}
			}
		}
	}
	
	public void etatJouer (int touche) {

		if (touche == KeyEvent.VK_RIGHT) {
			droite = true;
		}
		
		if (touche == KeyEvent.VK_LEFT) {
			gauche = true;
		}
		
		if (touche == KeyEvent.VK_UP) {
			haut = true;
		}
	
		if (touche == KeyEvent.VK_DOWN) {
			bas = true;
		}
		
		if (touche == KeyEvent.VK_S) {
			tirer = true;
		}
		
		if (touche == KeyEvent.VK_T) {
			if (debug == false) {
				debug = true;
			}
			else {debug = false;}
		}

		if (touche == KeyEvent.VK_G) {
			if (godMode == false) {
				godMode = true;
			}
			else {godMode = false;}
		}
		
		if (touche == KeyEvent.VK_R) {
			switch (ecran.carteActuelle) {
			case 0: ecran.terrain.chargerCarte("/cartes/mondeDepart.txt", 0); break;
			case 1: ecran.terrain.chargerCarte("/cartes/interior01.txt", 1); break;
			case 2: ecran.terrain.chargerCarte("/cartes/monde2.txt", 2); break;
			case 3: ecran.terrain.chargerCarte("/cartes/grotte.txt", 3); break;
			case 4: ecran.terrain.chargerCarte("/cartes/village.txt", 4); break;
			case 5: ecran.terrain.chargerCarte("/cartes/entrainement.txt", 5); break;
			case 6: ecran.terrain.chargerCarte("/cartes/mine.txt", 6); break;
			case 7: ecran.terrain.chargerCarte("/cartes/riviere.txt", 7); break;
			case 8: ecran.terrain.chargerCarte("/cartes/dongeonDebut.txt", 8); break;
			case 9: ecran.terrain.chargerCarte("/cartes/dongeonBossFin.txt", 9); break;
			}
		}
		
		if (touche == KeyEvent.VK_C) {
			ecran.etatJeu = ecran.stats;
		}
		
		if (touche == KeyEvent.VK_P) {
			ecran.etatJeu = ecran.pause;
		}
		
		if (touche == KeyEvent.VK_SPACE) {
			entree = true;
		}
		
		if (touche == KeyEvent.VK_X) {
			attaquer = true;
		}

		if (touche == KeyEvent.VK_Q) {
			proteger = true;
		}
		
		if (touche == KeyEvent.VK_ESCAPE) {
			ecran.etatJeu = ecran.options;
		}

		if (touche == KeyEvent.VK_M) {
			ecran.etatJeu = ecran.cartes;
		}

		if (touche == KeyEvent.VK_L) {
			if (ecran.carte.miniCarteOn == true) {
				ecran.carte.miniCarteOn = false;
			}
			else {
				ecran.carte.miniCarteOn = true;
			}
		}
	}
	
	public void etatPause (int touche) {
		if (touche == KeyEvent.VK_P) {
			ecran.etatJeu = ecran.jouer;
		}
	}
	
	public void etatParler (int touche) {
		if (touche == KeyEvent.VK_SPACE) {
			entree = true;
		}
	}
	
	public void etatStats (int touche) {
		if (touche == KeyEvent.VK_C) {
			ecran.etatJeu = ecran.jouer;
		}
		if (touche == KeyEvent.VK_SPACE) {
			ecran.joueur.selectionnerItem();
		}
		if (touche == KeyEvent.VK_ENTER) {
			deposer = true;
		}

		joueurInventaire(touche);
	}

	public void joueurInventaire(int touche) {
		if (touche == KeyEvent.VK_RIGHT && ecran.interfaceJoueur.emplacementCol != 4) {
			ecran.interfaceJoueur.emplacementCol++;
			ecran.jouerSE(10);
		}
		
		if (touche == KeyEvent.VK_LEFT && ecran.interfaceJoueur.emplacementCol != 0) {
			ecran.interfaceJoueur.emplacementCol--;
			ecran.jouerSE(10);
		}
		
		if (touche == KeyEvent.VK_UP && ecran.interfaceJoueur.emplacementLign != 0) {
			ecran.interfaceJoueur.emplacementLign--;
			ecran.jouerSE(10);
		}
		
		if (touche == KeyEvent.VK_DOWN && ecran.interfaceJoueur.emplacementLign != 3) {
			ecran.interfaceJoueur.emplacementLign++;
			ecran.jouerSE(10);
		}
	}

	public void npcInventaire(int touche) {
		if (touche == KeyEvent.VK_RIGHT && ecran.interfaceJoueur.npcCol != 4) {
			ecran.interfaceJoueur.npcCol++;
			ecran.jouerSE(10);
		}
		
		if (touche == KeyEvent.VK_LEFT && ecran.interfaceJoueur.npcCol != 0) {
			ecran.interfaceJoueur.npcCol--;
			ecran.jouerSE(10);
		}
		
		if (touche == KeyEvent.VK_UP && ecran.interfaceJoueur.npcLign != 0) {
			ecran.interfaceJoueur.npcLign--;
			ecran.jouerSE(10);
		}
		
		if (touche == KeyEvent.VK_DOWN && ecran.interfaceJoueur.npcLign != 3) {
			ecran.interfaceJoueur.npcLign++;
			ecran.jouerSE(10);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int touche = e.getKeyCode();
		
		if (touche == KeyEvent.VK_RIGHT) {
			droite = false;
		}
		
		if (touche == KeyEvent.VK_LEFT) {
			gauche = false;
		}
		
		if (touche == KeyEvent.VK_UP) {
			haut = false;
		}
		
		if (touche == KeyEvent.VK_DOWN) {
			bas = false;
		}
		
		if (touche == KeyEvent.VK_S) {
			tirer = false;
		}

		if (touche == KeyEvent.VK_SPACE) {
			entree = false;
		}

		if (touche == KeyEvent.VK_ENTER) {
			if (deposer) {
				ecran.joueur.deposer();
			}
			deposer = false;
		}

		if (touche == KeyEvent.VK_X) {
			attaquer = false;
		}

		if (touche == KeyEvent.VK_Q) {
			proteger = false;
		}
	}

}
