package terrain;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import main.Ecran;

public class Carte extends GestionTerrain {
    
    Ecran ecran;
    BufferedImage carteMonde[];
    public Boolean miniCarteOn = false;

    public Carte(Ecran ecran) {
        super(ecran);
        this.ecran = ecran;
        creerMondeCarte();
    }

    public void creerMondeCarte() {

        carteMonde = new BufferedImage[ecran.maxCartes];
        int mondeCarteX = ecran.tailleFinale * ecran.mondeColMax;
        int mondeCarteY = ecran.tailleFinale * ecran.mondeLignMax;

        for (int i = 0; i < ecran.maxCartes; i++) {
            carteMonde[i] = new BufferedImage(mondeCarteX, mondeCarteY, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graph = (Graphics2D)carteMonde[i].createGraphics();

            int col = 0;
            int lign = 0;

            while (col < ecran.mondeColMax && lign < ecran.mondeLignMax) {

                int terrainIndex = parcoursCarte[i][col][lign];
                int x = ecran.tailleFinale * col;
                int y = ecran.tailleFinale * lign;
                graph.drawImage(terrain[terrainIndex].image, x, y, null);

                col++;
                if (col == ecran.mondeColMax) {
                    col = 0;
                    lign++;
                }
            }
            graph.dispose();
        }
    }

    public void dessinerCarteEntiereEcran(Graphics2D graph) {

        graph.setColor(Color.BLACK);
        graph.fillRect(0, 0, ecran.ecranLongueur, ecran.ecranLargeur);

        int width = 500;
        int height = 500;
        int x = ecran.ecranLongueur/2 - width/2;
        int y = ecran.ecranLargeur/2 - height/2;
        graph.drawImage(carteMonde[ecran.carteActuelle], x, y, width, height, null);

        double echelle = (double)(ecran.tailleFinale * ecran.mondeColMax)/width;
        int joueurX = (int)(x + ecran.joueur.carteX/echelle);
        int joueurY = (int)(y + ecran.joueur.carteY/echelle);
        int joueurTaille = (int)(ecran.tailleFinale/echelle);
        graph.drawImage(ecran.joueur.arriere1, joueurX, joueurY, joueurTaille, joueurTaille, null);

        graph.setFont(graph.getFont().deriveFont(28F));
        graph.setColor(Color.WHITE);
        graph.drawString("M pour fermer.", 760, 520);
    }

    public void dessinerMiniCarte(Graphics2D graph) {

        if (miniCarteOn) {
            int width = 100;
            int height = 100;
            int x = ecran.ecranLongueur - width - 10;
            int y = 5;

            graph.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            graph.drawImage(carteMonde[ecran.carteActuelle], x, y, width, height, null);

            double echelle = (double)(ecran.tailleFinale * ecran.mondeColMax)/width;
            int joueurX = (int)(x + ecran.joueur.carteX/echelle);
            int joueurY = (int)(y + ecran.joueur.carteY/echelle);
            int joueurTaille = (int)(ecran.tailleFinale/(echelle/3));
            graph.drawImage(ecran.joueur.arriere1, joueurX, joueurY, joueurTaille, joueurTaille, null);

            graph.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
}
