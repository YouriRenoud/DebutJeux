package terrain;

import main.Ecran;

public class Plaque extends ElementInteractif {

    Ecran ecran;
    public final static String objnom = "Plaque";

    public Plaque(Ecran ecran, int col, int lign) {
		super(ecran, col, lign);
		this.ecran = ecran;
		
		this.carteX = ecran.tailleFinale*col;
		this.carteY = ecran.tailleFinale*lign;
		
        nom = objnom;
		aireCollision.x = 0;
		aireCollision.y = 0;
		aireCollision.height = 0;
		aireCollision.width = 0;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
		
		arriere = initialiser("/ElementInteractif/plaque.png", ecran.tailleFinale, ecran.tailleFinale);
	}

}
