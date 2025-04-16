package object;

import Entites.Entite;
import Entites.Projectiles;
import main.Ecran;

public class AuraDeFeu extends Projectiles {

	Ecran ecran;
	public static final String objnom = "Aura de feu";

	public AuraDeFeu(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;
        typeEntite = bouclierType;
		nom = objnom;
        defVal = 4;
        auraDuree = 40;
        prix = 5000;
        prixForge = 5000;
        projectile = new TraineeDeFeu(ecran);
        arriere = initialiser("/items/aura.png", ecran.tailleFinale, ecran.tailleFinale);
        description = "[Aura de feu]\nEclairer et détruire\npourquoi choisir ?.";
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
