package Entites;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Ecran;

public class Particules extends Entite {

	Entite generateur;
	Color couleur;
	int taille;
	int xd;
	int yd;
	
	public Particules(Ecran ecran, Entite generateur, Color couleur, int taille, int vitesse, int vieMax, int xd, int yd) {
		super(ecran);
		
		this.generateur = generateur;
		this.couleur = couleur;
		this.xd = xd;
		this.yd = yd;
		this.taille = taille;
		this.vitesse = vitesse;
		this.vieMax = vieMax;
		
		int decalage = (ecran.tailleFinale/2) - (taille/2);
		vie = vieMax;
		carteX = generateur.carteX + decalage;
		carteY = generateur.carteY + decalage;
	}
	
	public void miseAJour() {
		vie--;
		
		if (vie < vieMax/2) {
			yd--;
			vitesse--;
		}
		
		carteX += xd*vitesse;
		carteY += yd*vitesse;
		
		if (vie <= 0) {
			vivant = false;
		}
	}
	
	public void afficher(Graphics2D graph) {
		int ecranX = carteX - ecran.joueur.carteX + ecran.joueur.ecranX;
		int ecranY = carteY - ecran.joueur.carteY + ecran.joueur.ecranY;

		graph.setColor(couleur);
		graph.fillRect(ecranX, ecranY, taille, taille);
	}
}
