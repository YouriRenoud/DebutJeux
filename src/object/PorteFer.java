package object;

import Entites.Entite;
import main.Ecran;

public class PorteFer extends Entite {
	
	Ecran ecran;
	public static final String objnom = "Porte en fer";

	public PorteFer(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;

		nom = objnom;
		arriere = initialiser("/items/porteEnFer.png", ecran.tailleFinale, ecran.tailleFinale);
		collision1 = true;
		
		typeEntite = obstacleType;
		aireCollision.x = 0;
		aireCollision.y = 16;
		aireCollision.width = 48;
		aireCollision.height = 32;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;

		setDialogues();
	}

	public void setDialogues() {
		dialogue[0][0] = "Il vous faut une clé pour ouvrir cette porte.\nMais pas n'importe laquelle...";

	}

	public void interaction() {
		commencerDialogue(this, 0);
	}
}