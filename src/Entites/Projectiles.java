package Entites;

import main.Ecran;

public class Projectiles extends Entite{

	Entite joueur;
	boolean intelligent = false;
	public Projectiles(Ecran ecran) {
		super(ecran);
		joueur = ecran.joueur;
	}
	
	public void initialiser(int carteX, int carteY, String direction, boolean vivant, Entite joueur) {
		this.carteX = carteX;
		this.carteY = carteY;
		this.direction = direction;
		this.vivant = vivant;
		this.joueur = joueur;
		if (joueur.nom == Joueur.objnom) {
			this.attaquer = 1*joueur.magie;
		}
		else {
			this.attaquer = 1*joueur.attaquer;
		}
		this.vie = this.vieMax;
	}
	
	public void miseAJour() {
		
		if (joueur == ecran.joueur) {
			int monstreIndex = ecran.collisions.analyserEntite(this, ecran.monstre);
			if (monstreIndex != 999) {
				ecran.joueur.blesserMonstre(this, monstreIndex, attaquer, reculForce);
				genererParticules(joueur.projectile, ecran.monstre[ecran.carteActuelle][monstreIndex]);
				vivant = false;
			}
		}
		if (joueur != ecran.joueur) {
			boolean contactJoueur = ecran.collisions.analyserJoueur(this);
			if (!ecran.joueur.invincible && contactJoueur) {
				degatJoueur(attaquer);
				genererParticules(joueur.projectile, joueur.projectile);
				vivant = false;
			}
		}
		if (!intelligent) {
			switch(direction) {
				case "bas": this.carteY += vitesse; break;
				case "haut": this.carteY -= vitesse; break;
				case "gauche": this.carteX -= vitesse; break;
				case "droite": this.carteX += vitesse; break;
				}
		}
		else {
			chercherChemin(getColArrivee(ecran.joueur), getLignArrivee(ecran.joueur));
		}
		
		vie--;
		if (vie <= 0) {
			vivant = false;
		}
		
		compteur++;
		if (compteur > 12) {
			if (marcher == 1) {
				marcher = 2;
			}
			else if (marcher == 2) {
				marcher = 1;
			}
			compteur = 0;
		}
		
	}
	
	public boolean ressourcesSuffisantes(Entite joueur) {
		if (joueur.magie >= joueur.projectile.coutUtilisation) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void utiliserRessource(Entite joueur) {}
}
