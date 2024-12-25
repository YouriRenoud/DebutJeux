package Environnement;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

import main.Ecran;

public class Lumieres {

    Ecran ecran;
    BufferedImage assombrir;

    public int jourCompteur = 0;
    public float filtreAlpha = 0.0f;

    public final int jour = 0;
    public final int crepuscule = 1;
    public final int nuit = 2;
    public final int aube = 3;
    public int momentJour = jour;


    public Lumieres(Ecran ecran) {
        this.ecran = ecran;
        setLumiereSource();
    }

    public void setLumiereSource() {
        assombrir = new BufferedImage(ecran.ecranLongueur, ecran.ecranLargeur, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graph = (Graphics2D) assombrir.getGraphics();

        if (ecran.joueur.lumiereActuelle == null) {
            graph.setColor(new Color(0, 0, 0.2f, 0.98f));
        }
        else {
            int centreX = ecran.joueur.ecranX + ecran.tailleFinale / 2;
            int centreY = ecran.joueur.ecranY + ecran.tailleFinale / 2;
    
            Color couleur[] = new Color[12];
            float fraction[] = new float[12];
    
            couleur[0] = new Color(0, 0, 0.2f, 0.1f);
            couleur[1] = new Color(0, 0, 0.2f, 0.42f);
            couleur[2] = new Color(0, 0, 0.2f, 0.52f);
            couleur[3] = new Color(0, 0, 0.2f, 0.61f);
            couleur[4] = new Color(0, 0, 0.2f, 0.69f);
            couleur[5] = new Color(0, 0, 0.2f, 0.76f);
            couleur[6] = new Color(0, 0, 0.2f, 0.82f);
            couleur[7] = new Color(0, 0, 0.2f, 0.87f);
            couleur[8] = new Color(0, 0, 0.2f, 0.91f);
            couleur[9] = new Color(0, 0, 0.2f, 0.94f);
            couleur[10] = new Color(0, 0, 0.2f, 0.96f);
            couleur[11] = new Color(0, 0, 0.2f, 0.97f);
    
            fraction[0] = 0.0f;
            fraction[1] = 0.4f;
            fraction[2] = 0.5f;
            fraction[3] = 0.6f;
            fraction[4] = 0.65f;
            fraction[5] = 0.7f;
            fraction[6] = 0.75f;
            fraction[7] = 0.8f;
            fraction[8] = 0.85f;
            fraction[9] = 0.9f;
            fraction[10] = 0.95f;
            fraction[11] = 1.0f;
    
            RadialGradientPaint gradient = new RadialGradientPaint(centreX, centreY, ecran.joueur.lumiereActuelle.rayonLumiere, fraction, couleur);
            graph.setPaint(gradient);
        }
        
        graph.fillRect(0, 0, ecran.ecranLongueur, ecran.ecranLargeur);

        graph.dispose();
    }

    public void miseAJour() {
        if (ecran.joueur.lumiereChangee) {
            setLumiereSource();
            ecran.joueur.lumiereChangee = false;
        }

        if (momentJour == jour) {
            jourCompteur++;

            if (jourCompteur > 10800) {
                momentJour = crepuscule;
                jourCompteur = 0;
            }
        }

        if (momentJour == crepuscule) {
            filtreAlpha += 0.0001f;

            if (filtreAlpha >= 0.98f) {
                momentJour = nuit;
                filtreAlpha = 0.98f;
            }
        }

        if (momentJour == nuit) {
            jourCompteur++;

            if (jourCompteur > 10800) {
                momentJour = aube;
                jourCompteur = 0;
            }
        }

        if (momentJour == aube) {
            filtreAlpha -= 0.0001f;

            if (filtreAlpha <= 0.0f) {
                momentJour = jour;
                filtreAlpha = 0.0f;
            }
        }
    }

    public void resetJour() {
        momentJour = jour;
        filtreAlpha = 0.0f;
    }

    public void afficher(Graphics2D g) {

        if (ecran.lieuActuel == ecran.dehors) {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filtreAlpha));
        }
        if (ecran.lieuActuel == ecran.dehors || ecran.lieuActuel == ecran.dongeon) {
            g.drawImage(assombrir, 0, 0, null);
}
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        String moment = "";

        switch (momentJour) {
            case jour: moment = "Jour"; break;
            case crepuscule: moment = "Crepuscule"; break;
            case nuit: moment = "Nuit"; break;
            case aube: moment = "Aube"; break;
        }

        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(40f));
        g.drawString(moment, 720, 540);
    }
}
