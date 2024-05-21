package Entites;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Ecran;
import main.FonctionUtiles;

public class Vipere extends Entite {

    public Vipere(int numero, String sexe, Ecran ecran) {
        super(150, 500, 100, 60, 15, 10, "bebe Vipere" + sexe + numero, sexe, ecran);
        dureeVie = 15;


        aireCollision = new Rectangle(20, 20, 28, 28);
		
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
        
        ecran.nbrEntite++;
        this.initialiser();
		this.getImage();
    }

    public void initialiser() {
		
		carteX = ecran.tailleFinale * 23;
		carteY = ecran.tailleFinale * 21;
		vitesse = 3;
		direction = "bas";
	}
	
	public void getImage() {

			avant = initialiser("Avant");
			
			arriere = initialiser("Arriere");

			gauche = initialiser("Gauche");

			droite = initialiser("Droite");
			
			avant1 = initialiser("Avant1");
			
			arriere1 = initialiser("Arriere1");

			gauche1 = initialiser("Gauche1");

			droite1 = initialiser("Droite1");
	}

	public BufferedImage initialiser(String image) {
		FonctionUtiles fct = new FonctionUtiles();
		BufferedImage imageEchelle = null;
		try {
			imageEchelle = ImageIO.read(getClass().getResourceAsStream("/vipere/" + image + ".png"));
			imageEchelle = fct.miseAEchelle(imageEchelle, ecran.tailleFinale, ecran.tailleFinale);
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return imageEchelle;
	}
    
}
