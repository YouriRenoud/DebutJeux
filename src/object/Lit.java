package object;

import Entites.Entite;
import main.Ecran;

public class Lit extends Entite {
    
    Ecran ecran;
    public static final String objnom = "Lit";

    public Lit(Ecran ecran) {
        super(ecran);
        this.ecran = ecran;

        typeEntite = utilitaireType;
        nom = objnom;
        arriere = initialiser("/items/Lit.png", ecran.tailleFinale, ecran.tailleFinale);
        description = "[Lit]\nUn lit pour passer la nuit.";
        prix = 300;
        
    }

    public void utiliser(Entite e, int i) {
        
        ecran.etatJeu = ecran.dormir;
        ecran.jouerSE(16);
        ecran.joueur.getDormirImage(arriere);
        ecran.joueur.inventaire.remove(i);
    }
}
