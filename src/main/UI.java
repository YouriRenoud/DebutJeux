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

		if (ecran.etatActuel == ecran.enJeu) {

		}
		if (ecran.etatActuel == ecran.pauseJeu) {
			dessinerPause();
		}
		
// 		if (finDuJeu) {
			
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
		graph.setFont(graph.getFont().deriveFont(Font.PLAIN, 80F));
		String texte = "PAUSE";
		int x = getXCentre(texte);
		int y = ecran.ecranLargeur/2;

		graph.drawString(texte, x, y);
	}

	public int getXCentre(String texte) {
		int longueur = (int) graph.getFontMetrics().getStringBounds(texte, graph).getWidth();
		int x = ecran.ecranLongueur/2 - longueur/2;
		return x;
	}
}
