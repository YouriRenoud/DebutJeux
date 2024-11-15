package object;

import Entites.Entite;
import main.Ecran;

public class Porte extends Entite {
	
	Ecran ecran;
	public Porte(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;

		nom = "Porte";
		arriere = initialiser("/items/PorteFermee.png", ecran.tailleFinale, ecran.tailleFinale);
		collision1 = true;
		
		typeEntite = obstacleType;
		aireCollision.x = 0;
		aireCollision.y = 16;
		aireCollision.width = 48;
		aireCollision.height = 32;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
	}

	public void interaction() {

		ecran.etatJeu = ecran.parler;
		ecran.interfaceJoueur.dialogueCourant = "Il vous faut une clé pour ouvrir cette porte.";
	}
}