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
	}

	public void utiliser(Entite e, int i) {

		ecran.etatJeu = ecran.parler;

		int objIndex = getDetecte(e, ecran.obj, "Porte");

		if (objIndex != 999) {
			ecran.interfaceJoueur.dialogueCourant = "Vous avez utiliser" + nom + "\npour ouvrir la porte.";
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
			ecran.interfaceJoueur.dialogueCourant = "Il n'y a pas de porte à ouvrir.";
		}
	}
}
