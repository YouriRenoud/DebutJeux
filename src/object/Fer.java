package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class Fer extends Entite {
	
	Ecran ecran;
	public static final String objnom = "Minerai de fer";

	
	public Fer(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;

		nom = objnom;
		typeEntite = utilitaireType;
		arriere = initialiser("/items/Cle.png", ecran.tailleFinale, ecran.tailleFinale);
		description = "[" + nom + "]\nUn matériau solide.";
		prix = 30;
		empillable = true;

		setDialogues();
	}

	public void setDialogues() {
        dialogue[0][0] = "Vous n'avez pas assez de matériaux.";
	}

	public void utiliser(Entite e, int i) {
    }
}
