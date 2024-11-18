package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class Pieces extends Entite {
	Ecran ecran;
	public static final String objnom = "Piece de monnaie";

	public Pieces(Ecran ecran, int tpe) {
		super(ecran);
		this.ecran = ecran;
		type = tpe;
		if (type == 1) {
			valeur = 1;
		}
		if (type == 2) {
			valeur = 5;
		}
		if (type == 3) {
			valeur = 10;
		}
		
		nom = objnom;
		typeEntite = ramasserType;
		arriere = initialiser("/items/PieceBronze.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/items/PieceArgent.png", ecran.tailleFinale, ecran.tailleFinale);
		avant = initialiser("/items/PieceOr.png", ecran.tailleFinale, ecran.tailleFinale);
		description = "[" + nom + "]\nPour acheter des trucs..";
	}
	
	public void utiliser(Entite entite, int i) {
		entite.argent += valeur;
		ecran.interfaceJoueur.ajouterMessage("Vous gagner " + valeur + " pièces !");
		ecran.jouerSE(1);
		ecran.obj[ecran.carteActuelle][i] = null;
	}
}
