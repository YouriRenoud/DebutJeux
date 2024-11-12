package terrain;

import java.awt.Color;

import Entites.Entite;
import main.Ecran;

public class ArbreCassable extends ElementInteractif {
	
	Ecran ecran;
	
	public ArbreCassable (Ecran ecran, int col, int lign) {
		super(ecran, col, lign);
		this.ecran = ecran;
		
		this.carteX = ecran.tailleFinale*col;
		this.carteY = ecran.tailleFinale*lign;
		
		vie = 3;
		
		arriere = initialiser("/ElementInteractif/ArbreCassable.png", ecran.tailleFinale, ecran.tailleFinale);
		destructible = true;
	}
	
	public boolean armeCorrecte(Entite joueur) {
		boolean itemCorrect = false;
		if (joueur.armeActuelle.typeEntite == ecran.joueur.hacheType) {
			itemCorrect = true;
		}
		return itemCorrect;
	}
	
	public void jouerSE() {
		ecran.jouerSE(12);
	}
	
	public ElementInteractif getFormeDetruite() {
		ElementInteractif i = new TroncArbre(ecran, carteX/ecran.tailleFinale, carteY/ecran.tailleFinale);
		return i;
	}
	
	public int getParticuleMaxVie() {
		return 10;
	}
	
	public int getParticuleVitesse() {
		return 2;
	}
	
	public int getParticuleTaille() {
		return 6;
	}
	
	public Color getParticuleCouleur() {
		return new Color(65,50,30);
	}
}
