package object;

import Entites.Entite;
import main.Ecran;

public class GantsDeForce extends Entite {
	
	public static final String objnom = "Le gant qui impose";
	public Ecran ecran;

	public GantsDeForce(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;
		nom = objnom;
		typeEntite = bouclierType;
		arriere = initialiser("/items/gantsdeforce.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 5;
        defVal = 2;
        prix = 2000;
		prixForge = 3000;
        ralentissement = true;
        rayonLumiere = 4;
		description = "[" + nom + "]\nDégats de zone.\nAttaque = " + attVal;

		setDialogues();
	}

	public void setDialogues() {
		dialogue[0][0] = "Vous ne pouvez pas déposer votre\néquipement actuel.";
	}

    public void actions() {
        attVal = (int)(ecran.joueur.attaquer/2);
    }

	public void deposer(Entite e, int i) {
		if (e.chaussuresActuelles == this) {
			commencerDialogue(this, 0);
		}
		else {
			deposerItem(e, i);
            ecran.joueur.ralentissement = false;
		}
	}
}
