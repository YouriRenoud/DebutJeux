package Entites;

import main.Ecran;

public class JoueurDebile extends Entite {
    Ecran ecran;
    public final static String objnom = "JoueurDebile";

    public JoueurDebile(Ecran ecran) {
        super(ecran);
        this.ecran = ecran;

        nom = objnom;
        getImage();
    }

    public void getImage() {
        avant = initialiser("/joueur/Avant.png", ecran.tailleFinale, ecran.tailleFinale);
        
        arriere = initialiser("/joueur/Arriere.png", ecran.tailleFinale, ecran.tailleFinale);

        gauche = initialiser("/joueur/Gauche.png", ecran.tailleFinale, ecran.tailleFinale);

        droite = initialiser("/joueur/Droite.png", ecran.tailleFinale, ecran.tailleFinale);
        
        avant1 = initialiser("/joueur/Avant1.png", ecran.tailleFinale, ecran.tailleFinale);
        
        arriere1 = initialiser("/joueur/Arriere1.png", ecran.tailleFinale, ecran.tailleFinale);

        gauche1 = initialiser("/joueur/Gauche1.png", ecran.tailleFinale, ecran.tailleFinale);

        droite1 = initialiser("/joueur/Droite1.png", ecran.tailleFinale, ecran.tailleFinale);
}
}
