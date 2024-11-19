package Environnement;

import java.awt.Graphics2D;

import main.Ecran;

public class GererEnvironnement {
    
    Ecran ecran;
    public Lumieres lumieres;

    public GererEnvironnement(Ecran ecran) {
        this.ecran = ecran;
    }

    public void initialiser() {
        lumieres = new Lumieres(ecran);
    }

    public void miseAJour() {
        lumieres.miseAJour();
    }

    public void afficher(Graphics2D graph) {
        lumieres.afficher(graph);
    }
}
