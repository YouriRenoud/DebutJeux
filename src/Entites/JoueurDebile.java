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
        avant = initialiser("/joueur/"+ecran.joueur.nomClasse+"/Avant.png", ecran.tailleFinale, ecran.tailleFinale);
        
        arriere = initialiser("/joueur/"+ecran.joueur.nomClasse+"/Arriere.png", ecran.tailleFinale, ecran.tailleFinale);

        gauche = initialiser("/joueur/"+ecran.joueur.nomClasse+"/Gauche.png", ecran.tailleFinale, ecran.tailleFinale);

        droite = initialiser("/joueur/"+ecran.joueur.nomClasse+"/Droite.png", ecran.tailleFinale, ecran.tailleFinale);
        
        avant1 = initialiser("/joueur/"+ecran.joueur.nomClasse+"/Avant1.png", ecran.tailleFinale, ecran.tailleFinale);
        
        arriere1 = initialiser("/joueur/"+ecran.joueur.nomClasse+"/Arriere1.png", ecran.tailleFinale, ecran.tailleFinale);

        gauche1 = initialiser("/joueur/"+ecran.joueur.nomClasse+"/Gauche1.png", ecran.tailleFinale, ecran.tailleFinale);

        droite1 = initialiser("/joueur/"+ecran.joueur.nomClasse+"/Droite1.png", ecran.tailleFinale, ecran.tailleFinale);
}
}
