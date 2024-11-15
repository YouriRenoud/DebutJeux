package object;

import Entites.Entite;
import main.Ecran;

public class Lanterne extends Entite {
 
    Ecran ecran;

    public Lanterne(Ecran ecran) {
        super(ecran);
        this.ecran = ecran;

        typeEntite = lumiereType;
        nom = "Lanterne";
        arriere = initialiser("/items/Lanterne.png", ecran.tailleFinale, ecran.tailleFinale);
        description = "[Lanterne]\nUne lanterne qui éclaire les\nténèbres.";
        prix = 150;
        rayonLumiere = 250;
    }
}
