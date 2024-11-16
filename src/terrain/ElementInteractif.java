package terrain;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entites.Entite;
import main.Ecran;
import object.Pieces;

public class ElementInteractif extends Entite {
	
	Ecran ecran;
	public boolean destructible = false;
	
	public ElementInteractif(Ecran ecran, int col, int lign) {
		super(ecran);
		this.ecran = ecran;
	}
	
	public void miseAJour() {
		if (invincible == true) {
			tempsInvincible ++;
			if (tempsInvincible > 30) {
				tempsInvincible = 0;
				invincible = false;
			}
		}
	}
	
	public void jouerSE() {
		
	}
	
	public ElementInteractif getFormeDetruite() {
		ElementInteractif i = null;
		return i;
	}
	
	public boolean armeCorrecte(Entite joueur) {
		boolean itemCorrect = false;
		return itemCorrect;
	}
	
	public void afficher(Graphics2D graph) {
		int actuelX = carteX - ecran.joueur.carteX + ecran.joueur.ecranX;
		int actuelY = carteY - ecran.joueur.carteY + ecran.joueur.ecranY;
	
		if (carteX + ecran.tailleFinale > ecran.joueur.carteX - ecran.joueur.ecranX 
				&& carteX - ecran.tailleFinale < ecran.joueur.carteX + ecran.joueur.ecranX
				&& carteY + ecran.tailleFinale > ecran.joueur.carteY - ecran.joueur.ecranY 
				&& carteY - ecran.tailleFinale < ecran.joueur.carteY + ecran.joueur.ecranY) {

			graph.drawImage(arriere, actuelX, actuelY, null);
		}
		else {
		}
	}
}
