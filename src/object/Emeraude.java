package object;

import Entites.Entite;
import main.Ecran;

public class Emeraude extends Entite {
	Ecran ecran;
	public static final String objnom = "La pierre du monde";

	public Emeraude(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;

		
		nom = objnom;
		typeEntite = ramasserType;
		arriere = initialiser("/items/pierreDuMonde.png", ecran.tailleFinale, ecran.tailleFinale);
		description = "[" + nom + "]\nL'objet à l'origine de toute chose..";
        setDialogues();
	}
	
	public void utiliser(Entite entite, int i) {

        ecran.etatJeu = ecran.scenes;
        ecran.scene.sceneNum = ecran.scene.finJeu;

	}

    public void setDialogues() {

        dialogue[0][0] = "Vous avez trouvé la pierre du monde !";
        dialogue[0][1] = "Nul ne sait d'où elle vient..";
        dialogue[0][2] = "Mais elle renferme un grand pouvoir !";
        dialogue[0][3] = "Vous avez réussi à la retrouver !";
        dialogue[0][4] = "Félicitations !";

        dialogue[1][0] = "Vous pouvez rentrer chez vous en paix !";
    }
}
