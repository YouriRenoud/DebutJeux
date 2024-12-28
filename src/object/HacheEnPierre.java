package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class HacheEnPierre extends Entite {
	
	public static final String objnom = "Hache en pierre";

	public HacheEnPierre(Ecran ecran) {
		super(ecran);
		
		nom = objnom;
		typeEntite = hacheType;
		arriere = initialiser("/items/HacheEnPierre.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 3;
		attArea.width = 35;
		attArea.height = 22;
		description = "[" + nom + "]\nArme de barbares,\nPeut couper des arbres.\nDégats = " + attVal;
		prix = 50;
		prixForge = 30;
		nbForgeReussi = 0;
		reculForce = 3;
		dureeAttFrame1 = 10;
		dureeAttFrame2 = 30;

		setDialogues();
	}

	public void setDialogues() {
		dialogue[0][0] = "Vous ne pouvez pas déposer votre\néquipement actuel.";
	}

	public void deposer(Entite e, int i) {
		if (e.armeActuelle == this) {
			commencerDialogue(this, 0);
		}
		else {
			deposerItem(e, i);
		}
	}
}
