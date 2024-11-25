package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class Coffre extends Entite {
	
	Ecran ecran;
	public static final String objnom = "Coffre";

	public Coffre(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;
		
		typeEntite = obstacleType;
		nom = objnom;
		arriere = initialiser("/items/CoffreFerme.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/items/CoffreOuvert.png", ecran.tailleFinale, ecran.tailleFinale);

		collision1 = true;

		aireCollision.x = 4;
		aireCollision.y = 16;
		aireCollision.width = 40;
		aireCollision.height = 32;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
	}

	public void initialiserContenu(Entite contenu) {
		this.contenu = contenu;		
		setDialogues();
	}

	public void setDialogues() {
		dialogue[0][0] = "Vous avez ouvert le coffre et trouvé ceci :\n" + contenu.nom + " !" + "\nMais votre inventaire est plein.";
		dialogue[1][0] = "Vous avez ouvert le coffre et trouvé ceci :\n" + contenu.nom + " !" + "\nEt vous avez récupéré " + contenu.nom + " !";
		dialogue[2][0] = "Le coffre est vide.";
	}

	public void interaction() {

		if (!ouvert) {
			ecran.jouerSE(3);

			if (!ecran.joueur.itemRecuperable(contenu)) {
				commencerDialogue(this, 0);
			}
			else {
				commencerDialogue(this, 1);
				arriere = arriere1;
				ouvert = true;
			}
		}
		else {
			commencerDialogue(this, 2);
		}
	}
}
