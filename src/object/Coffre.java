package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class Coffre extends Entite {
	
	Ecran ecran;
	Entite contenu;
	boolean ouvert = false;

	public Coffre(Ecran ecran, Entite contenu) {
		super(ecran);
		this.ecran = ecran;
		this.contenu = contenu;
		
		typeEntite = obstacleType;
		nom = "Coffre";
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

	public void interaction() {

		ecran.etatJeu = ecran.parler;

		if (!ouvert) {
			ecran.jouerSE(3);
			StringBuilder sb = new StringBuilder();
			sb.append("Vous avez ouvert le coffre et\ntrouvé ceci : " + contenu.nom + " !");

			if (!ecran.joueur.itemRecuperable(contenu)) {
				sb.append("\nVotre inventaire est plein.");
			}
			else {
				sb.append("\nVous avez récupéré " + contenu.nom + " !");
				arriere = arriere1;
				ouvert = true;
			}
			ecran.interfaceJoueur.dialogueCourant = sb.toString();
		}
		else {
			ecran.interfaceJoueur.dialogueCourant = "Le coffre est vide.";
		}
	}
}
