package object;

import Entites.Entite;
import main.Ecran;

public class Porte extends Entite {
	
	public Porte(Ecran ecran) {
		super(ecran);
		
		nom = "Porte";
		arriere = initialiser("/items/PorteFermee.png", ecran.tailleFinale, ecran.tailleFinale);
		collision1 = true;
		
		aireCollision.x = 0;
		aireCollision.y = 16;
		aireCollision.width = 48;
		aireCollision.height = 32;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
	}
}