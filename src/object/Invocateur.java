package object;

import Entites.ChevalierMort;
import Entites.Entite;
import main.Ecran;

public class Invocateur extends Entite {

	public static final String objnom = "L'invocateur";
	public Ecran ecran;

	public Invocateur(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;
		nom = objnom;
		typeEntite = epeeType;
		arriere = initialiser("/items/invocateur.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 7;
        ecran.joueur.magie += 10;
		invocation = new ChevalierMort(ecran, ecran.carteActuelle+1);
		attArea.width = 30;
		attArea.height = 35;
		description = "[" + nom + "]\nL'appel des morts.\nAttaque = " + attVal + "\nMagie + 10\nInvocation = " + invocation.nom;
		prix = 5000;
		prixForge = 5000;
		nbForgeReussi = 0;
		reculForce = 4;
		dureeAttFrame1 = 50;
		dureeAttFrame2 = 80;
	}

	public void actions() {
		System.out.println("Invocation de " + invocation.nom);
		for (int i = 0; i < ecran.mage[1].length; i++) {
			if (ecran.mage[ecran.carteActuelle][i] == null) {
				ecran.mage[ecran.carteActuelle][i] = invocation;
				ecran.mage[ecran.carteActuelle][i].carteX = ecran.joueur.carteX;
				ecran.mage[ecran.carteActuelle][i].carteY = ecran.joueur.carteY + ecran.tailleFinale;
				break;
			}
		}
	}

    public void setDialogues() {
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
