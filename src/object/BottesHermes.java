package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class BottesHermes extends Entite {
	
	public static final String objnom = "Bottes d'Hermes";

	public BottesHermes(Ecran ecran) {
		super(ecran);
		
		nom = objnom;
		typeEntite = chaussureType;
		arriere = initialiser("/items/BottesHermes.png", ecran.tailleFinale, ecran.tailleFinale);
		prix = 700;
		vitesse = 3;
		description = "[" + nom + "]\nChaussure du dieu du voyage.\nVitesse + " + vitesse;
	}
}
