package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class Cle extends Entite {
	
	Ecran ecran;
	
	public Cle(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;

		nom = "Cle";
		typeEntite = utilitaireType;
		arriere = initialiser("/items/Cle.png", ecran.tailleFinale, ecran.tailleFinale);
		description = "[" + nom + "]\nOuvre certaines portes.";
		prix = 10;
		empillable = true;

		setDialogues();
	}

	public void setDialogues() {
		dialogue[0][0] = "Vous avez utiliser " + nom + "\npour ouvrir la porte.";
		dialogue[1][0] = "Il n'y a pas de porte à ouvrir.";

	}

	public void utiliser(Entite e, int i) {

		int objIndex = getDetecte(e, ecran.obj, "Porte");

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
