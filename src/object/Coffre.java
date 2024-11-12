package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class Coffre extends Entite {
	
	public Coffre(Ecran ecran) {
		super(ecran);
		
		nom = "Coffre";
		arriere = initialiser("/items/CoffreFerme", ecran.tailleFinale, ecran.tailleFinale);
		
		collision1 = true;
	}
}
