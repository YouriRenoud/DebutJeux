package terrain;

import Entites.Entite;
import main.Ecran;

public class TroncArbre extends ElementInteractif {
	
	Ecran ecran;
	
	public TroncArbre (Ecran ecran, int col, int lign) {
		super(ecran, col, lign);
		this.ecran = ecran;
		
		this.carteX = ecran.tailleFinale*col;
		this.carteY = ecran.tailleFinale*lign;
		
		aireCollision.x = 0;
		aireCollision.y = 0;
		aireCollision.height = 0;
		aireCollision.width = 0;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
		
		arriere = initialiser("/ElementInteractif/ArbreCoupe.png", ecran.tailleFinale, ecran.tailleFinale);
	}
}
