package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.Cle;

public class UI {
	
	Ecran ecran;
	Graphics2D graph;
	Font arial30, arial80;
	// BufferedImage cleImage;
	
	public boolean messagePret = false;
	public String message = "";
	public int dureeMessage = 0;
	
	public double tempsDeJeu = 0;
	public DecimalFormat format = new DecimalFormat("#0.000");
	
	public boolean finDuJeu;
	
	public UI(Ecran ecran) {
		this.ecran = ecran;
		
		arial30 = new Font("Arial", Font.PLAIN, 30);
		arial80 = new Font("Arial", Font.BOLD, 80);
		// Cle cle = new Cle(ecran);
		// cleImage = cle.image;
	}
	
	public void afficherMessage(String text) {
		message = text;
		messagePret = true;
	}
	
	public void afficher(Graphics2D graph) {

		this.graph = graph;

		graph.setFont(arial30);
		graph.setColor(Color.white);

		if (finDuJeu) {

			resumePartie();
			ecran.filDuJeu = null;
		} else {
			if (ecran.commencer == false) {
				dessinerCommandes();
			} else {
			if (ecran.etatActuel == ecran.enJeu) {
			
				tempsDeJeu += (double) 1/60;
 				graph.drawString("Time: " + format.format(tempsDeJeu), ecran.tailleFinale*12, 12*ecran.tailleFinale);
				afficherstats();
			}
			if (ecran.etatActuel == ecran.pauseJeu) {
				dessinerPause();
				afficherstats();
			}
		}
		}
//		if (finDuJeu) {
			
// 			graph.setFont(arial30);
// 			graph.setColor(Color.white);
			
// 			String text;
// 			int textLength;
// 			int x, y;
			
// 			text = "Tu as terminé le jeu, felicitations !";
// 			textLength = (int) graph.getFontMetrics().getStringBounds(text, graph).getWidth();
			
// 			x = ecran.ecranLongueur/2 - textLength/2;
// 			y = ecran.ecranLargeur/2 - ecran.tailleFinale*3;
			
// 			graph.drawString(text, x, y);
			
// 			graph.setFont(arial80);
// 			graph.setColor(Color.yellow);
			
// 			text = "Bravo !!!";
// 			textLength = (int) graph.getFontMetrics().getStringBounds(text, graph).getWidth();
			
// 			x = ecran.ecranLongueur/2 - textLength/2;
// 			y = ecran.ecranLargeur/2 + ecran.tailleFinale*2;
			
// 			graph.drawString(text, x, y);
			
// 			graph.setFont(arial80);
// 			graph.setColor(Color.white);
			
// 			text = "Temps en jeu:" + format.format(tempsDeJeu);
// 			textLength = (int) graph.getFontMetrics().getStringBounds(text, graph).getWidth();
			
// 			x = ecran.ecranLongueur/2 - textLength/2;
// 			y = ecran.ecranLargeur/2 + ecran.tailleFinale*4;
			
// 			graph.drawString(text, x, y);
			
// 			ecran.filDuJeu = null;
			
// 		} else {
		
// 			graph.setFont(arial30);
// 			graph.setColor(Color.white);
// 			graph.drawImage(cleImage, ecran.tailleFinale/3, ecran.tailleFinale/3,
// 				ecran.tailleFinale/2, ecran.tailleFinale/2, null);
// 			graph.drawString(" x " + ecran.joueur.clePossedees, 30, 38);
			
// 			tempsDeJeu += (double) 1/60;
// 			graph.drawString("Time: " + format.format(tempsDeJeu), ecran.tailleFinale*12, 50);
			
// 			if (messagePret) {
// 				dureeMessage++;
// 				graph.setFont(graph.getFont().deriveFont(30F));
// 				graph.drawString(message, ecran.tailleFinale*5, ecran.tailleFinale*5);
// 				if (dureeMessage > 120) {
// 					message = "";
// 					messagePret = false;
// 					dureeMessage = 0;
// 				}
// 			}
			

// 		}
 	}

	public void dessinerPause() {
		graph.setFont(graph.getFont().deriveFont(Font.PLAIN, 30F));
		String texte = "PAUSE";
		int x = getXCentre(texte);
		int y = ecran.ecranLargeur/2;

		graph.drawString(texte, x, y);
	}

	public void dessinerCommandes() {
		String texte = "BIENVENUE DANS";
		String texte9 = "AVENTURE TIME:";
		String texte1 = " Commandes clavier: ";
		String texte2 = "Touche P: pause ";
		String texte3 = "Touche L: lancer le jeu";
		String texte4 = "Touche E: fin du jeu";
		String texte5 = "Flèches: deplacement de l'écran";
		String texte6 = "Nombre viperes crées: " + ecran.vipereTotal;
		String texte7 = "Viperes restantes: " + ecran.nombreViperes;
		String texte8 = "Temps écoule: " + tempsDeJeu;

		int x = getXCentre(texte);
		int y = ecran.ligneMax * ecran.tailleFinale / 2;

		graph.setColor(Color.white);

		graph.drawString(texte1, x-4, y + ecran.tailleFinale/2);
		graph.drawString(texte2, x-4, y + 2*ecran.tailleFinale/2);
		graph.drawString(texte3, x-4, y + 3*ecran.tailleFinale/2);
		graph.drawString(texte4, x-4, y + 4*ecran.tailleFinale/2);
		graph.drawString(texte5, x-4, y + 5*ecran.tailleFinale/2);

		graph.setFont(graph.getFont().deriveFont(Font.BOLD, 60F));
		graph.setColor(Color.black);
		graph.drawString(texte, x - 120, y - 180);
		graph.drawString(texte9, x - 120, y - 120);

	}

	public void afficherstats() {
		String texte = "Nombre animaux: " + ecran.nbrEntite;

		String texte1 = "Nombre poules: " + ecran.nombrePoules;
		String texte2 = "Nombre renards: " + ecran.nombreRenards;
		String texte3 = "Nombre viperes: " + ecran.nombreViperes;
		int x = 12*ecran.tailleFinale - 3;

		graph.setFont(graph.getFont().deriveFont(Font.PLAIN, 20F));

		graph.setColor(Color.yellow);
		graph.drawString(texte1, x, 1*ecran.tailleFinale/2);
		graph.setColor(Color.red);
		graph.drawString(texte2, x, 2*ecran.tailleFinale/2);
		graph.setColor(Color.green);
		graph.drawString(texte3, x, 3*ecran.tailleFinale/2);

		graph.setColor(Color.white);		
		graph.drawString(texte, x, 4*ecran.tailleFinale/2);

	}

	public int getXCentre(String texte) {
		int longueur = (int) graph.getFontMetrics().getStringBounds(texte, graph).getWidth();
		int x = ecran.ecranLongueur/2 - longueur/2;
		return x;
	}

	public void resumePartie() {

		String texte = "Partie terminée:";
		String texte1 = "Nombre animaux crées: " + (ecran.poulesTotal+ecran.renradTotal+ecran.vipereTotal);
		String texte2 = "Nombre poules crées: " + ecran.poulesTotal;
		String texte3 = "Poules restantes: " + ecran.nombrePoules;
		String texte4 = "Nombre renards crées: " + ecran.renradTotal;
		String texte5 = "Renards restants: " + ecran.nombreRenards;
		String texte6 = "Nombre viperes crées: " + ecran.vipereTotal;
		String texte7 = "Viperes restantes: " + ecran.nombreViperes;
		String texte8 = "Temps écoule: " + tempsDeJeu;

		int x = getXCentre(texte);
		int y = ecran.ligneMax * ecran.tailleFinale / 2;

		graph.setFont(graph.getFont().deriveFont(Font.PLAIN, 20F));

		graph.setColor(Color.white);
		graph.drawString(texte1, x, y + ecran.tailleFinale/2);

		graph.setColor(Color.yellow);
		graph.drawString(texte2, x, y + 4*ecran.tailleFinale/2);
		graph.drawString(texte3, x, y + 5*ecran.tailleFinale/2);

		graph.setColor(Color.red);
		graph.drawString(texte4, x, y + 7*ecran.tailleFinale/2);
		graph.drawString(texte5, x, y + 8*ecran.tailleFinale/2);

		graph.setColor(Color.green);
		graph.drawString(texte6, x, y + 10*ecran.tailleFinale/2);
		graph.drawString(texte7, x, y + 11*ecran.tailleFinale/2);

		graph.setColor(Color.white);	
		graph.drawString(texte8, x, y + 13*ecran.tailleFinale/2);

		graph.setFont(graph.getFont().deriveFont(Font.PLAIN, 80F));
		graph.setColor(Color.black);
		graph.drawString(texte, x - 120, y - 80);

	}
}
