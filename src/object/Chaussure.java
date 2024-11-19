package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class Chaussure extends Entite {
	
	public static final String objnom = "Chaussure";

	public Chaussure(Ecran ecran) {
		super(ecran);
		
		nom = objnom;
		typeEntite = chaussureType;
		arriere = initialiser("/items/Chaussures.png", ecran.tailleFinale, ecran.tailleFinale);
		prix = 70;
		vitesse = 1;
		description = "[" + nom + "]\nChaussure de course.\nVitesse + 1";
	}
}
