package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class CarteMonde extends Entite {
	
	Ecran ecran;
	public static final String objnom = "Carte du monde";

	
	public CarteMonde(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;

		nom = objnom;
		typeEntite = utilitaireType;
		arriere = initialiser("/items/CarteMonde.png", ecran.tailleFinale, ecran.tailleFinale);
		description = "[" + nom + "]\nPlan détaillé des contrées.";
		prix = 10;
		empillable = false;

		setDialogues();
	}

	public void setDialogues() {
		dialogue[0][0] = "La carte du monde est maintenant en votre\npossession.";
	}

    public void utiliser(Entite e, int i) {
        ecran.joueur.carteTrouvee = true;
        ecran.jouerSE(3);
    }

    public void deposer(Entite e, int i) {
        e.carteTrouvee = false;
        deposerItem(e, i);
    }
}
