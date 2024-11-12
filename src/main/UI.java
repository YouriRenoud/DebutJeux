package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

import Entites.Entite;
import Entites.Joueur;
import object.Cle;
import object.Coeur;
import object.Mana;

public class UI {
	
	Ecran ecran;
	Font arial30, arial80;
	Graphics2D graph;
	
	BufferedImage coeurPlein, coeurMoitie, coeurVide, crystalPlein, crystalVide;
	
	public Entite npc;
	
	public boolean messagePret = false;
	public String message = "";
	public int dureeMessage = 0;
	
	ArrayList<String> messages = new ArrayList<>();
	ArrayList<Integer> dureesMessages = new ArrayList<>();
	public String dialogueCourant = "";
	public int numCommande = 0;
	public int introNum = 0;
	public int sousEtats = 0;
	public int dureeTransition = 0;
	
	public int emplacementCol = 0;
	public int emplacementLign = 0;
	
	public int npcCol = 0;
	public int npcLign = 0;
	
	public boolean finDuJeu;
	
	public UI(Ecran ecran) {
		this.ecran = ecran;
		
		arial30 = new Font("Arial", Font.PLAIN, 30);
		arial80 = new Font("Arial", Font.BOLD, 80);

		Entite coeur = new Coeur(ecran);
		coeurPlein = coeur.image;
		coeurMoitie = coeur.image1;
		coeurVide = coeur.image2;
		
		Entite crystal = new Mana(ecran);
		crystalPlein = crystal.image;
		crystalVide = crystal.image1;
	}
	
	public void ajouterMessage(String text) {
		//message = text;
		//messagePret = true;
		messages.add(text);
		dureesMessages.add(0);
	}
	
	public void afficher(Graphics2D graph) {
		this.graph = graph;
		
		graph.setFont(arial30);
		graph.setColor(Color.white);
		
		if (ecran.etatJeu == ecran.intro) {
			dessinerIntro();
		}
		if (ecran.etatJeu == ecran.jouer) {
			dessinerVie();
			dessinerMessages();
		}
		if (ecran.etatJeu == ecran.pause) {
			dessinerPause();
			dessinerVie();
			}
		if (ecran.etatJeu == ecran.parler) {
			dessinerDialogue();
			dessinerVie();
		}
		if(ecran.etatJeu == ecran.stats) {
			dessinerStats();
			dessinerInventaire(ecran.joueur, true);
		}
		if(ecran.etatJeu == ecran.options) {
			dessinerOptions();
		}
		if(ecran.etatJeu == ecran.perdu) {
			dessinerPerdu();
		}
		if(ecran.etatJeu == ecran.transitionCartes) {
			dessinerTransitionCartes();
		}
		if(ecran.etatJeu == ecran.marchander) {
			dessinerMarcher();
		}
	}
	
	public void dessinerMarcher() {
		switch (sousEtats) {
		case 0: choixSelectionne(); break;
		case 1: choixAchete(); break;
		case 2: choixVendu(); break;
		}
		ecran.action.entree = false;
		
	}
	
	public void choixSelectionne() {
		dessinerDialogue();
		
		int x = ecran.tailleFinale * 15;
		int y = ecran.tailleFinale * 4;
		int width = ecran.tailleFinale * 3;
		int height = (int)(ecran.tailleFinale*3.5);
		
		dessinerFenetre(x-30, y, width+30, height);
		
		x += ecran.tailleFinale - 35;
		y += ecran.tailleFinale;
		graph.drawString("Acheter", x, y);
		if (numCommande == 0) {
			graph.drawString(">", x-30, y);
			if (ecran.action.entree) {
				sousEtats = 1;
			}
		}
		y += ecran.tailleFinale;
		graph.drawString("Vendre", x, y);
		if (numCommande == 1) {
			graph.drawString(">", x-30, y);
			if (ecran.action.entree) {
				sousEtats = 2;
			}
		}
		y += ecran.tailleFinale;
		graph.drawString("Partir", x, y);
		if (numCommande == 2) {
			graph.drawString(">", x-30, y);
			if (ecran.action.entree) {
				numCommande = 0;
				ecran.etatJeu = ecran.parler;
				dialogueCourant = "Reviens quand tu veux hehehe !";
			}
		}
	}
	
	public void choixAchete() {
		
	}
	
	public void choixVendu() {
		
	}
	
	public void dessinerTransitionCartes() {
		
		dureeTransition++;
		graph.setColor(new Color(0, 0, 0, dureeTransition*5));
		graph.fillRect(0, 0, ecran.ecranLongueur, ecran.ecranLargeur);
		
		if (dureeTransition >= 50) {
			dureeTransition = 0;
			ecran.etatJeu = ecran.jouer;
			ecran.carteActuelle = ecran.event.tempCarte;
			ecran.joueur.carteX = ecran.tailleFinale * ecran.event.tempCol;
			ecran.joueur.carteY = ecran.tailleFinale * ecran.event.tempLign;
			ecran.event.eventPrecedentX = ecran.joueur.carteX;
			ecran.event.eventPrecedentY = ecran.joueur.carteY;
			//ecran.terrain.chargerCarte("/cartes/interior01.txt", 1);
		}
	}
	
	public void dessinerPerdu() {
		
		graph.setColor(new Color(0, 0, 0, 150));
		graph.fillRect(0, 0, ecran.ecranLongueur, ecran.ecranLargeur);
		
		int x;
		int y;
		String texte;
		graph.setFont(graph.getFont().deriveFont(110F));
		
		texte = "Game Over";
		
		graph.setColor(Color.black);
		x = longueurCentree(texte);
		y = ecran.tailleFinale*4;
		graph.drawString(texte, x, y);
		
		graph.setColor(Color.white);
		graph.drawString(texte, x-4, y-4);
		
		graph.setFont(graph.getFont().deriveFont(50f));
		texte = "Réessayer";
		x = longueurCentree(texte);
		y += ecran.tailleFinale*4;
		graph.drawString(texte, x, y);
		if (numCommande == 0) {
			graph.drawString(">", x-35, y);
		}

		texte = "Quitter";
		x = longueurCentree(texte);
		y += 75;
		graph.drawString(texte, x, y);
		if (numCommande == 1) {
			graph.drawString(">", x-35, y);
		}
	}

	public void dessinerOptions() {
		
		graph.setColor(Color.white);
		graph.setFont(graph.getFont().deriveFont(32F));
		
		int stX = ecran.tailleFinale*6;
		int stY = ecran.tailleFinale;
		int width = ecran.tailleFinale*8;
		int height = ecran.tailleFinale*10;
		
		dessinerFenetre(stX, stY, width, height);
		
		switch(sousEtats) {
		case 0: optionAccueil(stX, stY); break;
		case 1: optionPleinEcran(stX, stY); break;
		case 2: optionControle(stX, stY); break;
		case 3: optionFinJeu(stX, stY); break;
		}
		
		ecran.action.entree = false;
	}
	
	public void optionAccueil(int x, int y) {
		int texteX;
		int texteY;
		
		String texte = "Options";
		texteX = longueurCentree(texte);
		texteY = y + ecran.tailleFinale;
		graph.drawString(texte, texteX, texteY);
		
		texteX = x + ecran.tailleFinale - 12;
		texteY += ecran.tailleFinale*2;
		graph.drawString("Plein écran", texteX, texteY);
		if (numCommande == 0) {
			graph.drawString(">", texteX-25, texteY);
			if (ecran.action.entree) {
				if (ecran.pleinEcranOn) {
					ecran.pleinEcranOn = false;
				}
				else {
					ecran.pleinEcranOn = true;
				}
				sousEtats = 1;
			}

		}
		
		texteY += ecran.tailleFinale;
		graph.drawString("Musiques", texteX, texteY);
		if (numCommande == 1) {
			graph.drawString(">", texteX-25, texteY);
		}

		texteY += ecran.tailleFinale;
		graph.drawString("Effets sonores", texteX, texteY);
		if (numCommande == 2) {
			graph.drawString(">", texteX-25, texteY);
		}
		
		texteY += ecran.tailleFinale;
		graph.drawString("Controles", texteX, texteY);
		if (numCommande == 3) {
			graph.drawString(">", texteX-25, texteY);
			if (ecran.action.entree) {
				sousEtats = 2;
				numCommande = 0;
			}
		}
		
		texteY += ecran.tailleFinale;
		graph.drawString("Fin de partie", texteX, texteY);
		if (numCommande == 4) {
			graph.drawString(">", texteX-25, texteY);
			if (ecran.action.entree) {
				sousEtats = 3;
				numCommande = 0;
			}
		}
		
		texteY += ecran.tailleFinale + 40;
		graph.drawString("Retour", texteX, texteY);
		if (numCommande == 5) {
			graph.drawString(">", texteX-25, texteY);
			if (ecran.action.entree) {
				ecran.etatJeu = ecran.jouer;
				numCommande = 0;
			}
		}
		
		texteX = x + ecran.tailleFinale*5;
		texteY = y + (int)(ecran.tailleFinale*2.5) + 3;
		graph.setStroke(new BasicStroke(3));
		graph.drawRect(texteX, texteY, 24, 24);
		if (ecran.pleinEcranOn) {
			graph.fillRect(texteX, texteY, 24, 24);
		}
		
		texteY += ecran.tailleFinale;
		graph.drawRect(texteX, texteY, 120, 24);
		int volumeWidth = 24 * ecran.musique.echelleVolume;
		graph.fillRect(texteX, texteY, volumeWidth, 24);
		
		texteY += ecran.tailleFinale;
		graph.drawRect(texteX, texteY, 120, 24);
		volumeWidth = 24 * ecran.son.echelleVolume;
		graph.fillRect(texteX, texteY, volumeWidth, 24);
		
		ecran.configuration.sauverConfiguration();
	}
	
	public void optionPleinEcran(int x, int y) {
		
		int texteX = x + ecran.tailleFinale - 27;
		int texteY = y + ecran.tailleFinale*3;
		
		dialogueCourant = "Ce changement prendra\neffet en rechargeant le\njeu.";
		
		for (String ligne: dialogueCourant.split("\n")) {
			graph.drawString(ligne, texteX, texteY);
			texteY += 40;
		}
		
		texteX = x + ecran.tailleFinale - 10;
		texteY = y + ecran.tailleFinale*9;
		graph.drawString("Retour", texteX, texteY);
		if (numCommande == 0) {
			graph.drawString(">", texteX-25, texteY);
			if (ecran.action.entree) {
				sousEtats = 0;
			}
		}
	}
	
	public void optionControle(int x, int y) {
		
		int texteX;
		int texteY;
		
		String texte = "Controles";
		texteX = longueurCentree(texte);
		texteY = y + ecran.tailleFinale;
		graph.drawString(texte, texteX, texteY);
		
		texteX = x + ecran.tailleFinale - 20;
		texteY += ecran.tailleFinale;
		
		graph.drawString("Move", texteX, texteY); texteY += ecran.tailleFinale;
		graph.drawString("Confirmer", texteX, texteY); texteY += ecran.tailleFinale;
		graph.drawString("Attaquer", texteX, texteY); texteY += ecran.tailleFinale;
		graph.drawString("Tirer", texteX, texteY); texteY += ecran.tailleFinale;
		graph.drawString("Afficher les attributs", texteX, texteY); texteY += ecran.tailleFinale;
		graph.drawString("Pause", texteX, texteY); texteY += ecran.tailleFinale;
		graph.drawString("Options", texteX, texteY); texteY += ecran.tailleFinale;
		
		texteX = x + ecran.tailleFinale*6;
		texteY = y + ecran.tailleFinale*2;
		graph.setFont(graph.getFont().deriveFont(20F));
		graph.drawString("UpDownLeftRight", texteX-80, texteY); texteY += ecran.tailleFinale;
		graph.setFont(graph.getFont().deriveFont(32F));
		graph.drawString("Enter", texteX, texteY); texteY += ecran.tailleFinale;
		graph.drawString("X", texteX+40, texteY); texteY += ecran.tailleFinale;
		graph.drawString("S", texteX+40, texteY); texteY += ecran.tailleFinale;
		graph.drawString("C", texteX+40, texteY); texteY += ecran.tailleFinale;
		graph.drawString("P", texteX+40, texteY); texteY += ecran.tailleFinale;
		graph.drawString("Echap", texteX-5, texteY); texteY += ecran.tailleFinale;
		
		texteX = x + ecran.tailleFinale;
		texteY = y + ecran.tailleFinale*9 + 20;
		graph.drawString("Retour", texteX, texteY);
		if (numCommande == 0) {
			graph.drawString(">", texteX-25, texteY);
			if (ecran.action.entree) {
				sousEtats = 0;
				numCommande = 3;
			}
		}

	}
	
	public void optionFinJeu(int x, int y) {
		
		int texteX = x + ecran.tailleFinale;
		int texteY = y + ecran.tailleFinale*3;
		
		dialogueCourant = "Souhaitez vous quitter\ncette partie et retourner\nà l'écran principal ?";
		for (String ligne: dialogueCourant.split("\n")) {
			graph.drawString(ligne, texteX, texteY);
			texteY += 40;
		}
		
		String texte = "Oui";
		texteX = longueurCentree(texte);
		texteY += ecran.tailleFinale*3;
		graph.drawString(texte, texteX, texteY);
		if (numCommande == 0) {
			graph.drawString(">", texteX - 30, texteY);
			if (ecran.action.entree) {
				sousEtats = 0;
				ecran.etatJeu = ecran.intro;
			}
		}
		
		texte = "Non";
		texteX = longueurCentree(texte);
		texteY += ecran.tailleFinale;
		graph.drawString(texte, texteX, texteY);
		if (numCommande == 1) {
			graph.drawString(">", texteX - 30, texteY);
			if (ecran.action.entree) {
				sousEtats = 0;
				numCommande = 4;
			}
		}
	}
	
	public void dessinerInventaire(Entite e, boolean curseur) {
		
		int stX = 0;
		int stY = 0;
		int width = 0;
		int height = 0;
		int posCol = 0;
		int posLign = 0;
		
		if (e instanceof Joueur) {
			// fenetre d'inventaire joueur
			stX = ecran.tailleFinale*12;
			stY = ecran.tailleFinale;
			width = ecran.tailleFinale*6;
			height = ecran.tailleFinale*5;	
			posCol = emplacementCol;
			posLign = emplacementLign;
		}
		else {
			// fenetre d'inventaire npc
			stX = ecran.tailleFinale*2;
			stY = ecran.tailleFinale;
			width = ecran.tailleFinale*6;
			height = ecran.tailleFinale*5;	
			posCol = npcCol;
			posLign = npcLign;
		}
		
		dessinerFenetre(stX, stY, width, height);
		
		final int emplacementXDebut = stX + 20;
		final int emplacementYDebut = stY + 20;
		int emplacementX = emplacementXDebut;
		int emplacementY = emplacementYDebut;
		int emplacementTaille = ecran.tailleFinale + 3;
		
		// dessiner les items du joueur
		for (int i = 0; i < e.inventaire.size(); i++) {
			//equiper curseur
			if (e.inventaire.get(i) == e.bouclierActuel 
					|| e.inventaire.get(i) == e.armeActuelle) {
				graph.setColor(new Color(240, 190, 90));
				graph.fillRoundRect(emplacementX, emplacementY, ecran.tailleFinale, ecran.tailleFinale, 10, 10);
			}
			graph.drawImage(e.inventaire.get(i).arriere, emplacementX, emplacementY, null);
			
			emplacementX += emplacementTaille;
			
			if (i == 4 || i == 9 || i == 14) {
				emplacementX = emplacementXDebut;
				emplacementY += emplacementTaille;
			}
		}
		
		if (curseur == true) {
			// curseur
			int curseurX = emplacementXDebut + emplacementTaille*emplacementCol;
			int curseurY = emplacementYDebut + emplacementTaille*emplacementLign;
			int curseurWidth = ecran.tailleFinale;
			int curseurHeight = ecran.tailleFinale;
			
			graph.setColor(Color.white);
			graph.setStroke(new BasicStroke(3));
			graph.drawRoundRect(curseurX, curseurY, curseurWidth, curseurHeight, 10, 10);
			
			// description de l'objet selectionné
			int dStX = stX;
			int dStY = stY + height;
			int dWidth = width;
			int dHeight = ecran.tailleFinale*3;
			
			int textX = dStX + 20;
			int textY = dStY + ecran.tailleFinale - 10;
			graph.setFont(graph.getFont().deriveFont(18f));
			
			int selectionne = getItemIndexSelectionne(posCol, posLign);
			if (selectionne < e.inventaire.size()) {		
				dessinerFenetre(dStX, dStY, dWidth, dHeight);
				for (String ligne: e.inventaire.get(selectionne).description.split("\n")) {
					graph.drawString(ligne, textX, textY);
					textY += 28;
				}
			}	
		}
	}

	public int getItemIndexSelectionne(int emCol, int emLign) {
		return emCol + (emLign*5); 
	}
	
	public void dessinerMessages() {
		int messX = ecran.tailleFinale;
		int messY = ecran.tailleFinale*4;
		graph.setFont(graph.getFont().deriveFont(Font.BOLD, 24F));
		
		for(int i = 0; i<messages.size(); i++) {
			if (messages.get(i) != null) {
				graph.setColor(Color.white);
				for (String ligne: messages.get(i).split("\n")) {
					if (ligne.length() != 0) {
						graph.drawString(ligne, messX, messY);				
						messY += 50;
					}
				}
				int compteur = dureesMessages.get(i) + 1;
				dureesMessages.set(i, compteur);

				
				if (dureesMessages.get(i) > 180) {
					messages.remove(i);
					dureesMessages.remove(i);
				}
			}
		}
	}
	
	public void dessinerStats() {
		
		final int stX = ecran.tailleFinale;
		final int stY = ecran.tailleFinale;
		final int width = ecran.tailleFinale*5;
		final int height = ecran.tailleFinale*10;
		
		dessinerFenetre(stX, stY, width, height);
		graph.setColor(Color.white);
		graph.setFont(graph.getFont().deriveFont(20F));
		
		int textX = stX + 20;
		int textY = stY + ecran.tailleFinale;
		final int hauteurLigne = 32;
		
		graph.drawString("Niveau", textX, textY);
		textY += hauteurLigne;
		graph.drawString("Experience", textX, textY);
		textY += hauteurLigne;
		graph.drawString("Niveau suivant", textX, textY);
		textY += hauteurLigne;
		graph.drawString("Vie", textX, textY);
		textY += hauteurLigne;
		graph.drawString("Mana", textX, textY);
		textY += hauteurLigne;
		graph.drawString("Force", textX, textY);
		textY += hauteurLigne;
		graph.drawString("Agilite", textX, textY);
		textY += hauteurLigne;
		graph.drawString("Arme", textX, textY);
		textY += hauteurLigne;
		graph.drawString("Protection", textX, textY);
		textY += hauteurLigne;
		graph.drawString("Attaque", textX, textY);
		textY += hauteurLigne;
		graph.drawString("Defense", textX, textY);
		textY += hauteurLigne;
		graph.drawString("Argent", textX, textY);
		textY += hauteurLigne;
		
		int valX = (stX + width) - 30;
		textY = stY + ecran.tailleFinale;
		
		String valeur;
		valeur = String.valueOf(ecran.joueur.niveau);
		textX = alignerDroite(valeur, valX);
		graph.drawString(valeur, textX, textY);
		textY += hauteurLigne;
		valeur = String.valueOf(ecran.joueur.experience);
		textX = alignerDroite(valeur, valX);
		graph.drawString(valeur, textX, textY);
		textY += hauteurLigne;
		valeur = String.valueOf(ecran.joueur.niveauSuivant);
		textX = alignerDroite(valeur, valX);
		graph.drawString(valeur, textX, textY);
		textY += hauteurLigne;
		valeur = String.valueOf(ecran.joueur.vie+"/"+ecran.joueur.vieMax);
		textX = alignerDroite(valeur, valX);
		graph.drawString(valeur, textX, textY);
		textY += hauteurLigne;
		valeur = String.valueOf(ecran.joueur.mana+"/"+ecran.joueur.maxMana);
		textX = alignerDroite(valeur, valX);
		graph.drawString(valeur, textX, textY);
		textY += hauteurLigne;
		valeur = String.valueOf(ecran.joueur.force);
		textX = alignerDroite(valeur, valX);
		graph.drawString(valeur, textX, textY);
		textY += hauteurLigne;
		valeur = String.valueOf(ecran.joueur.agilite);
		textX = alignerDroite(valeur, valX);
		graph.drawString(valeur, textX, textY);
		textY += hauteurLigne - 1;
		graph.setFont(graph.getFont().deriveFont(8F));
		valeur = String.valueOf(ecran.joueur.armeActuelle.nom);
		textX = alignerDroite(valeur, valX);
		graph.drawString(valeur, textX + 5, textY);
		textY += hauteurLigne - 1;
		valeur = String.valueOf(ecran.joueur.bouclierActuel.nom);
		textX = alignerDroite(valeur, valX);
		graph.drawString(valeur, textX + 5, textY);
		textY += hauteurLigne + 2;
		graph.setFont(graph.getFont().deriveFont(20F));
		valeur = String.valueOf(ecran.joueur.attaquer);
		textX = alignerDroite(valeur, valX);
		graph.drawString(valeur, textX, textY);
		textY += hauteurLigne;
		valeur = String.valueOf(ecran.joueur.defendre);
		textX = alignerDroite(valeur, valX);
		graph.drawString(valeur, textX, textY);
		textY += hauteurLigne;
		valeur = String.valueOf(ecran.joueur.argent);
		textX = alignerDroite(valeur, valX);
		graph.drawString(valeur, textX, textY);
		textY += hauteurLigne;
	}
	
	public void dessinerVie() {
				
		int x = ecran.tailleFinale/2;
		int y = ecran.tailleFinale/2;
		int i = 0;
		
		while (i < ecran.joueur.vieMax/2) {
			graph.drawImage(coeurVide, x, y, null);
			i++;
			x += ecran.tailleFinale;
		}
		
		x = ecran.tailleFinale/2;
		y = ecran.tailleFinale/2;
		i = 0;
		
		while (i < ecran.joueur.vie) {
			graph.drawImage(coeurMoitie, x, y, null);
			i++;
			if (i < ecran.joueur.vie) {
				graph.drawImage(coeurPlein, x, y, null);			
				
			}
			i++;
			x += ecran.tailleFinale;
		}
		
		x = ecran.tailleFinale/2 - 10;
		y = ecran.tailleFinale*2 - 20;
		i = 0;
		int lim = 1;
		while (i < ecran.joueur.maxMana) {
			graph.drawImage(crystalVide, x, y, null);
			i++;
			x += 40;
			
			if (i >= 4*lim) {
				y += 40;
				x = ecran.tailleFinale/2 - 10;
				lim++;
			}
		}
		
		x = ecran.tailleFinale/2 - 10;
		y = ecran.tailleFinale*2 - 20;
		i = 0;
		lim = 1;
		
		while (i < ecran.joueur.mana) {
			graph.drawImage(crystalPlein, x, y, null);
			i++;
			x += 40;
			if (i >= 4*lim) {
				y += 40;
				x = ecran.tailleFinale/2 - 10;
				lim++;
			}
		}
	}
	
	public void dessinerIntro() {
		
		if (introNum == 0) {
			graph.setColor(new Color(125,125,125));
			graph.fillRect(0, 0, ecran.ecranLongueur, ecran.ecranLargeur);
			
			graph.setFont(graph.getFont().deriveFont(Font.BOLD, 100F));
			String titre = "Adventure Time";
			int x = longueurCentree(titre);
			int y = ecran.tailleFinale*3;
			
			graph.setColor(Color.black);
			graph.drawString(titre, x+5, y+5);
			graph.setColor(Color.white);
			graph.drawString(titre, x, y);
			
			x = ecran.ecranLongueur/2 - ecran.tailleFinale;
			y += ecran.tailleFinale*2;
			graph.drawImage(ecran.joueur.avant1, x, y, ecran.tailleFinale*2, ecran.tailleFinale*2, null);
			
			graph.setFont(graph.getFont().deriveFont(Font.BOLD, 48F));
			
			titre = "Nouveau Jeu :";
			x = longueurCentree(titre);
			y += ecran.tailleFinale*3 + 20;
			graph.drawString(titre, x, y);
			if (numCommande == 0) {
				graph.drawString(">", x-ecran.tailleFinale, y);
			}
			
			titre = "Charger Partie :";
			x = longueurCentree(titre);
			y += ecran.tailleFinale;
			graph.drawString(titre, x, y);
			if (numCommande == 1) {
				graph.drawString(">", x-ecran.tailleFinale, y);
			}
			
			titre = "Quitter :";
			x = longueurCentree(titre);
			y += ecran.tailleFinale;
			graph.drawString(titre, x, y);
			if (numCommande == 2) {
				graph.drawString(">", x-ecran.tailleFinale, y);
			}
			
			if (numCommande == -1) {
				numCommande = 2;
			}
			
			if (numCommande == 3) {
				numCommande = 0;
			}
		}
		
		else if (introNum == 1) {
			
			graph.setColor(new Color(125,125,125));
			graph.fillRect(0, 0, ecran.ecranLongueur, ecran.ecranLargeur);
			
			graph.setColor(Color.white);
			graph.setFont(graph.getFont().deriveFont(42F));
			
			String texte = "Choisi ta spécialité :";
			int x = longueurCentree(texte);
			int y = ecran.tailleFinale*3;
			graph.drawString(texte, x, y);
			
			texte = "Paladin";
			x = longueurCentree(texte);
			y += ecran.tailleFinale*2;
			graph.drawString(texte, x, y);
			if (numCommande == 0) {
				graph.drawString(">", x-ecran.tailleFinale, y);
			}
			
			texte = "Assassin";
			x = longueurCentree(texte);
			y += ecran.tailleFinale;
			graph.drawString(texte, x, y);
			if (numCommande == 1) {
				graph.drawString(">", x-ecran.tailleFinale, y);
			}
			
			texte = "Mage";
			x = longueurCentree(texte);
			y += ecran.tailleFinale;
			graph.drawString(texte, x, y);
			if (numCommande == 2) {
				graph.drawString(">", x-ecran.tailleFinale, y);
			}
			
			texte = "Necromancien";
			x = longueurCentree(texte);
			y += ecran.tailleFinale;
			graph.drawString(texte, x, y);
			if (numCommande == 3) {
				graph.drawString(">", x-ecran.tailleFinale, y);
			}
			
			texte = "Retour";
			x = longueurCentree(texte);
			y += ecran.tailleFinale*2;
			graph.drawString(texte, x, y);
			if (numCommande == 4) {
				graph.drawString(">", x-ecran.tailleFinale, y);
			}
			
			if (numCommande == -1) {
				numCommande = 4;
			}
			
			if (numCommande == 5) {
				numCommande = 0;
			}
		}

	}
	
	public void dessinerDialogue() {
		
		int x = ecran.tailleFinale*2;
		int y = ecran.tailleFinale/2;
		int longueur = ecran.ecranLongueur - ecran.tailleFinale*4;
		int largeur = ecran.tailleFinale*4;
		
		dessinerFenetre(x, y, longueur, largeur);
		
		graph.setFont(graph.getFont().deriveFont(Font.PLAIN, 32F));
		x += ecran.tailleFinale;
		y += ecran.tailleFinale;
		
		for (String ligne : dialogueCourant.split("\n")) {
			graph.drawString(ligne, x, y);
			y += 40;
		}
	}
	
	public void dessinerFenetre(int x, int y, int longueur, int largeur) {
		
		Color c = new Color(0, 0, 0, 180);
		graph.setColor(c);
		graph.fillRoundRect(x, y, longueur, largeur, 35, 35);
		
		c = new Color(255, 255, 255);
		graph.setColor(c);
		graph.setStroke(new BasicStroke(5));
		graph.drawRoundRect(x+5, y+5, longueur-10, largeur-10, 25, 25);
		
	}
	
	public void dessinerPause() {
		
		String texte = "Pause";
		int x = longueurCentree(texte);
		int y = ecran.ecranLargeur/2;
		
		graph.drawString(texte, x, y);
		
	}
	
	public int longueurCentree(String texte) {
		int longueur = (int)graph.getFontMetrics().getStringBounds(texte, graph).getWidth();
		int x = ecran.ecranLongueur/2 - longueur/2;
		return x;
	}
	
	public int alignerDroite(String texte, int val) {
		int longueur = (int)graph.getFontMetrics().getStringBounds(texte, graph).getWidth();
		int x = val - longueur;
		return x;
	}
}
