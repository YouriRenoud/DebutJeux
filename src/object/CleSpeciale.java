package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class CleSpeciale extends Entite {
	
	Ecran ecran;
	public static final String objnom = "Clé mystérieuse";

	
	public CleSpeciale(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;

		nom = objnom;
		typeEntite = utilitaireType;
		arriere = initialiser("/items/CleSpeciale.png", ecran.tailleFinale, ecran.tailleFinale);
		description = "[" + nom + "]\nOuvre toutes les portes.";
		prix = 600;
		empillable = true;

		setDialogues();
	}

	public void setDialogues() {
		dialogue[0][0] = "Vous avez utiliser " + nom + "\npour ouvrir la porte.";
		dialogue[1][0] = "Il n'y a pas de porte à ouvrir.";

	}

	public void utiliser(Entite e, int i) {

		int objIndex = getDetecte(e, ecran.obj, PorteSpeciale.objnom);
		objIndex = getDetecte(e, ecran.obj, Porte.objnom);
		if (objIndex != 999) {
			commencerDialogue(this, 0);
			ecran.jouerSE(3);
			ecran.obj[ecran.carteActuelle][objIndex] = null;
			if (this.possedes > 1) {
				this.possedes--;
			}
			else {
				ecran.joueur.inventaire.remove(i);
			}
		}
		else {
			commencerDialogue(this, 1);
		}
	}
}
