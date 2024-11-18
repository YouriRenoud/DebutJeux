package main;

import Entites.Entite;

public class VerifierCollision {
	
	Ecran ecran;
	
	public VerifierCollision(Ecran ecran) {
		this.ecran = ecran;
	}
	
	public void AnalyserTerrain(Entite entite) {
		
		int gaucheX = entite.carteX + entite.aireCollision.x;
		int droiteX = entite.carteX + entite.aireCollision.x + entite.aireCollision.width;
		int hautY = entite.carteY + entite.aireCollision.y;
		int basY = entite.carteY + entite.aireCollision.y + entite.aireCollision.height;
		
		int colGauche = gaucheX/ecran.tailleFinale;
		int colDroite = droiteX/ecran.tailleFinale;
		int lignHaut = hautY/ecran.tailleFinale;
		int lignBas = basY/ecran.tailleFinale;
		
		int numTerrain1, numTerrain2;

		entite.collision0 = false;
		
		String direction = entite.direction;
		if (entite.recul) {
			direction = entite.reculDirection;
		}

		switch(direction) {
		case "haut":
			lignHaut = (hautY-entite.vitesse)/ecran.tailleFinale;
			numTerrain1 = ecran.terrain.parcoursCarte[ecran.carteActuelle][colGauche][lignHaut];
			numTerrain2 = ecran.terrain.parcoursCarte[ecran.carteActuelle][colDroite][lignHaut];
			if (ecran.terrain.terrain[numTerrain1].interaction == true || ecran.terrain.terrain[numTerrain2].interaction == true) {
				entite.collision0 = true;
			}
			break;
		case "bas":
			lignBas = (basY+entite.vitesse)/ecran.tailleFinale;
			numTerrain1 = ecran.terrain.parcoursCarte[ecran.carteActuelle][colGauche][lignBas];
			numTerrain2 = ecran.terrain.parcoursCarte[ecran.carteActuelle][colDroite][lignBas];
			if (ecran.terrain.terrain[numTerrain1].interaction == true || ecran.terrain.terrain[numTerrain2].interaction == true) {
				entite.collision0 = true;
			}
			break;
		case "gauche":
			colGauche = (gaucheX-entite.vitesse)/ecran.tailleFinale;
			numTerrain1 = ecran.terrain.parcoursCarte[ecran.carteActuelle][colGauche][lignHaut];
			numTerrain2 = ecran.terrain.parcoursCarte[ecran.carteActuelle][colGauche][lignBas];
			if (ecran.terrain.terrain[numTerrain1].interaction == true || ecran.terrain.terrain[numTerrain2].interaction == true) {
				entite.collision0 = true;
			}
			break;
		case "droite":
			colDroite = (droiteX+entite.vitesse)/ecran.tailleFinale;
			numTerrain1 = ecran.terrain.parcoursCarte[ecran.carteActuelle][colDroite][lignHaut];
			numTerrain2 = ecran.terrain.parcoursCarte[ecran.carteActuelle][colDroite][lignBas];
			if (ecran.terrain.terrain[numTerrain1].interaction == true || ecran.terrain.terrain[numTerrain2].interaction == true) {
				entite.collision0 = true;
			}
			break;
		}
	}
	
	public int analyserObjet(Entite entite, boolean joueur) {
		
		int index = 999;
		for (int i=0; i < ecran.obj[1].length; i++) {
			if (ecran.obj[ecran.carteActuelle][i] != null) {
			entite.aireCollision.x = entite.aireCollision.x + entite.carteX;
			entite.aireCollision.y = entite.aireCollision.y + entite.carteY;
			
			ecran.obj[ecran.carteActuelle][i].aireCollision.x = ecran.obj[ecran.carteActuelle][i].aireCollision.x + ecran.obj[ecran.carteActuelle][i].carteX;
			ecran.obj[ecran.carteActuelle][i].aireCollision.y = ecran.obj[ecran.carteActuelle][i].aireCollision.y + ecran.obj[ecran.carteActuelle][i].carteY;
			
			switch (entite.direction) {
			case "haut":
				entite.aireCollision.y -= entite.vitesse;
				break;
			case "bas":
				entite.aireCollision.y += entite.vitesse;
				break;
			case "gauche":
				entite.aireCollision.x -= entite.vitesse;
				break;
			case "droite":
				entite.aireCollision.x += entite.vitesse;
				break;
			}
			
			if (entite.aireCollision.intersects(ecran.obj[ecran.carteActuelle][i].aireCollision)) {
				if (ecran.obj[ecran.carteActuelle][i].collision1 == true) {
					entite.collision0 = true;
				}
				if (joueur == true) {
					index = i;
				}
			}
			
			entite.aireCollision.x = entite.aireSolideDefautX;
			entite.aireCollision.y = entite.aireSolideDefautY;
			
			ecran.obj[ecran.carteActuelle][i].aireCollision.x = ecran.obj[ecran.carteActuelle][i].aireSolideDefautX;
			ecran.obj[ecran.carteActuelle][i].aireCollision.y = ecran.obj[ecran.carteActuelle][i].aireSolideDefautY;
		}
		}
		return index;
	}
	
	public int analyserEntite(Entite entite, Entite[][] cible) {
		int index = 999;

		String direction = entite.direction;
		if (entite.recul) {
			direction = entite.reculDirection;
		}
		
		for (int i=0; i < cible[1].length; i++) {
			if (cible[ecran.carteActuelle][i] != null) {
			entite.aireCollision.x = entite.aireCollision.x + entite.carteX;
			entite.aireCollision.y = entite.aireCollision.y + entite.carteY;
			
			cible[ecran.carteActuelle][i].aireCollision.x = cible[ecran.carteActuelle][i].aireCollision.x + cible[ecran.carteActuelle][i].carteX;
			cible[ecran.carteActuelle][i].aireCollision.y = cible[ecran.carteActuelle][i].aireCollision.y + cible[ecran.carteActuelle][i].carteY;
			
			switch (direction) {
			case "haut":
				entite.aireCollision.y -= entite.vitesse;
				break;
			case "bas":
				entite.aireCollision.y += entite.vitesse;
				break;
			case "gauche":
				entite.aireCollision.x -= entite.vitesse;
				break;
			case "droite":
				entite.aireCollision.x += entite.vitesse;
				break;
			}
			if (entite.aireCollision.intersects(cible[ecran.carteActuelle][i].aireCollision)) {
				if (cible[ecran.carteActuelle][i] != entite) {
					entite.collision0 = true;
					index = i;
				}
			}
			entite.aireCollision.x = entite.aireSolideDefautX;
			entite.aireCollision.y = entite.aireSolideDefautY;
			
			cible[ecran.carteActuelle][i].aireCollision.x = cible[ecran.carteActuelle][i].aireSolideDefautX;
			cible[ecran.carteActuelle][i].aireCollision.y = cible[ecran.carteActuelle][i].aireSolideDefautY;
		}
		}
		return index;
	}
	
	public boolean analyserJoueur(Entite entite) {
		
		boolean contactJoueur = false;
		
		entite.aireCollision.x = entite.aireCollision.x + entite.carteX;
		entite.aireCollision.y = entite.aireCollision.y + entite.carteY;
		
		ecran.joueur.aireCollision.x = ecran.joueur.aireCollision.x + ecran.joueur.carteX;
		ecran.joueur.aireCollision.y = ecran.joueur.aireCollision.y + ecran.joueur.carteY;
		
		switch (entite.direction) {
		case "haut":
			entite.aireCollision.y -= entite.vitesse;
			break;
		case "bas":
			entite.aireCollision.y += entite.vitesse;
			break;
		case "gauche":
			entite.aireCollision.x -= entite.vitesse;
			break;
		case "droite":
			entite.aireCollision.x += entite.vitesse;
			break;
		}
		
		if (entite.aireCollision.intersects(ecran.joueur.aireCollision)) {
			entite.collision0 = true;
			contactJoueur = true;
		}
		
		entite.aireCollision.x = entite.aireSolideDefautX;
		entite.aireCollision.y = entite.aireSolideDefautY;
		
		ecran.joueur.aireCollision.x = ecran.joueur.aireSolideDefautX;
		ecran.joueur.aireCollision.y = ecran.joueur.aireSolideDefautY;

		return contactJoueur;
	}
}
