package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class Chaussure extends Entite {
	
	public Chaussure(Ecran ecran) {
		super(ecran);
		
		nom = "Chaussure";
		arriere = initialiser("/items/Chaussures.png", ecran.tailleFinale, ecran.tailleFinale);
		prix = 70;
		description = "[" + nom + "]\nChaussure de course.\nVitesse + 1";
	}
}
