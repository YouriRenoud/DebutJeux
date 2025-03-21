package Entites;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import main.Ecran;
import object.Porte;
import object.PorteFer;
import terrain.ElementInteractif;
import terrain.Plaque;

public class GrossePierre extends Entite {

    public static String objnom = "GrossePierre"; 
    public int compter;

    ArrayList<ElementInteractif> liste = new ArrayList<>();
    ArrayList<Entite> entites = new ArrayList<>();

	public GrossePierre(Ecran ecran) {
		super(ecran);
		
        nom = objnom;
		direction = "bas";
		vitesse = 1;

		dialogueSet = -1;

        aireCollision = new Rectangle(2, 8, 44, 39);
		
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
		
		getImage();
		setDialogues();

	}
	
	public void actions() {}
	public void miseAJour() {}
	public void setDialogues() {
		
		dialogue[0][0] = "Un gros caillou";
	}
	
	public void parler() {
		regarderJoueur();
		commencerDialogue(this, dialogueSet);

		dialogueSet++;
		if (dialogue[dialogueSet][0] == null) {
			dialogueSet = 0;
		}
	}

    public void deplacer (String dir) {

        direction = dir;
        verifierCollision();
        
        if (!collision0) {
            switch(direction) {
                case "haut": 
                    carteY -= vitesse;
                    break;
                case "bas":
                    carteY += vitesse;
                    break;
                case "gauche":
                    carteX -= vitesse;
                    break;
                case "droite":
                    carteX += vitesse;
                    break;
            }
        }

        detecterPlaque();
    }

    public void detecterPlaque() {

        liste = new ArrayList<>();
        entites = new ArrayList<>();

        for (int i=0; i < ecran.iTerrain[1].length; i++) {
            if (ecran.iTerrain[ecran.carteActuelle][i] != null && ecran.iTerrain[ecran.carteActuelle][i].nom != null
            && ecran.iTerrain[ecran.carteActuelle][i].nom.equals(Plaque.objnom)) {
                liste.add(ecran.iTerrain[ecran.carteActuelle][i]);
            }
        }
        
        for (int i=0; i < ecran.mage[1].length; i++) {
            if (ecran.mage[ecran.carteActuelle][i] != null && ecran.mage[ecran.carteActuelle][i].nom != null
            && ecran.mage[ecran.carteActuelle][i].nom.equals(GrossePierre.objnom)) {
                entites.add(ecran.mage[ecran.carteActuelle][i]);
            }
        }

        for (int i=0; i < liste.size(); i++) {

            int xDistance = Math.abs(carteX - liste.get(i).carteX);
            int yDistance = Math.abs(carteY - liste.get(i).carteY);
            int distance = Math.max(xDistance, yDistance);

            if (distance < 10) {
                if (entiteReliee == null) {
                    entiteReliee = liste.get(i);
                    ecran.jouerSE(3);
                }
            }
            else {
                if (entiteReliee == liste.get(i)) {
                    entiteReliee = null;
                }
            }
        }
        compter = 0;
        for (int i=0; i < entites.size(); i++) {
            
            if (entites.get(i).entiteReliee != null) {
                compter++;
            }
        }
        System.out.println(compter + " " + entites.size());
        if (compter != 0 && compter == entites.size()) {
            for (int i=0; i < ecran.obj[1].length; i++) {

                if (ecran.obj[ecran.carteActuelle][i] != null && ecran.obj[ecran.carteActuelle][i].nom.equals(PorteFer.objnom)) {
                    ecran.obj[ecran.carteActuelle][i] = null;
                    ecran.jouerSE(24);
                }
            }
        }
    }
	
	public void getImage() {
		avant = initialiser("/items/grossePierre.png", ecran.tailleFinale, ecran.tailleFinale);
		
		arriere = initialiser("/items/grossePierre.png", ecran.tailleFinale, ecran.tailleFinale);

		gauche = initialiser("/items/grossePierre.png", ecran.tailleFinale, ecran.tailleFinale);

		droite = initialiser("/items/grossePierre.png", ecran.tailleFinale, ecran.tailleFinale);
		
		avant1 = initialiser("/items/grossePierre.png", ecran.tailleFinale, ecran.tailleFinale);
		
		arriere1 = initialiser("/items/grossePierre.png", ecran.tailleFinale, ecran.tailleFinale);

		gauche1 = initialiser("/items/grossePierre.png", ecran.tailleFinale, ecran.tailleFinale);

		droite1 = initialiser("/items/grossePierre.png", ecran.tailleFinale, ecran.tailleFinale);
	}
}
