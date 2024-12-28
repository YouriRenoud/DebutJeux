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

		setDialogues();
	}

	public void setDialogues() {
		dialogue[0][0] = "Vous ne pouvez pas déposer votre\néquipement actuel.";
	}

	public void deposer(Entite e, int i) {
		if (e.chaussuresActuelles == this) {
			commencerDialogue(this, 0);
		}
		else {
			deposerItem(e, i);
		}
	}
}
