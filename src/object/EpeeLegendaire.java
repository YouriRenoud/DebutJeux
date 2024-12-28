package object;

import Entites.Entite;
import main.Ecran;

public class EpeeLegendaire extends Entite {

	public static final String objnom = "L'épée légendaire";

	public EpeeLegendaire(Ecran ecran) {
		super(ecran);
		nom = objnom;
		typeEntite = epeeType;
		arriere = initialiser("/items/epeeLegendaire.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 100;
		attArea.width = 30;
		attArea.height = 48;
		description = "[" + nom + "]\nLa meilleure épée.\nAttaque = " + attVal;
		prix = 50000;
		prixForge = 1000;
		nbForgeReussi = 0;
		reculForce = 20;
		dureeAttFrame1 = 5;
		dureeAttFrame2 = 20;

		setDialogues();
	}

    public void setDialogues() {

        dialogue[0][0] = "Vous avez trouvé l'épée légendaire !";
        dialogue[0][1] = "Cet objet est très puissant !";
        dialogue[0][2] = "Il a été forgé par les dieux eux-mêmes !";
        dialogue[0][3] = "Et donné en cadeau au créateur de cette contrée !";
        dialogue[0][4] = "Fais en bon usage !";

		dialogue[1][0] = "Vous ne pouvez pas déposer votre\néquipement actuel.";
    }

	public void deposer(Entite e, int i) {
		if (e.armeActuelle == this) {
			commencerDialogue(this, 1);
		}
		else {
			deposerItem(e, i);
		}
	}
}
