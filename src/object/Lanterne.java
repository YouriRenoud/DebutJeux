package object;

import Entites.Entite;
import main.Ecran;

public class Lanterne extends Entite {
 
    Ecran ecran;
    public static final String objnom = "Lanterne";

    public Lanterne(Ecran ecran) {
        super(ecran);
        this.ecran = ecran;

        typeEntite = lumiereType;
        nom = objnom;
        arriere = initialiser("/items/lanterne.png", ecran.tailleFinale, ecran.tailleFinale);
        description = "[Lanterne]\nUne lanterne qui éclaire les\nténèbres.";
        prix = 150;
        rayonLumiere = 250;
    }
}
