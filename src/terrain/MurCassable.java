package terrain;

import java.awt.Color;
import java.util.Random;

import Entites.Entite;
import main.Ecran;
import object.Coeur;
import object.Mana;
import object.Pieces;

public class MurCassable extends ElementInteractif {
	
	Ecran ecran;
	
	public MurCassable (Ecran ecran, int col, int lign) {
		super(ecran, col, lign);
		this.ecran = ecran;
		
		carteX = ecran.tailleFinale*col;
		carteY = ecran.tailleFinale*lign;
		
		vie = 5;
		direction = "haut";
		
		arriere = initialiser("/ElementInteractif/murCassable.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/ElementInteractif/murCassable.png", ecran.tailleFinale, ecran.tailleFinale);

		destructible = true;
	}
	
	public boolean armeCorrecte(Entite joueur) {
		boolean itemCorrect = false;
		if (joueur.armeActuelle.typeEntite == ecran.joueur.piocheType) {
			itemCorrect = true;
		}
		return itemCorrect;
	}
	
	public void jouerSE() {
		ecran.jouerSE(23);
	}
	
	public ElementInteractif getFormeDetruite() {
		ElementInteractif i = null;
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
		return new Color(65,65,65);
	}

    public void verifierDrop() {
	    int i = new Random().nextInt(100)+1;
		
	    if (i < 50) {
			dropItem(new Pieces(ecran, 1));
		}
		if (i >= 50 && i < 80) {
			dropItem(new Pieces(ecran, 2));
			dropItem(new Coeur(ecran));
		}
		if (i >= 80 && i < 99) {
			dropItem(new Pieces(ecran, 2));
			dropItem(new Mana(ecran));
		}
		if (i == 99) {
			dropItem(new Pieces(ecran, 3));
		}
	}
}
