package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class Cle extends Entite {
	
	public Cle(Ecran ecran) {
		super(ecran);
		
		nom = "Cle";
		typeEntite = utilitaireType;
		arriere = initialiser("/items/Cle.png", ecran.tailleFinale, ecran.tailleFinale);
		description = "[" + nom + "]\nOuvre certaines portes.";
	}
}
