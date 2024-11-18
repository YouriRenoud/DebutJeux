package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import Entites.JoueurDebile;
import object.Emeraude;
import object.PorteFer;

public class GererScenes {
    
    Ecran ecran;
    Graphics2D graph;
    public int sceneNum;
    public int scenePhase;
    int compteur = 0;
    float alpha = 0f;
    int y;
    String creditsFin = "";

    public final int NA = 0;
    public final int roiDemon = 1;
    public final int finJeu = 2;

    public GererScenes(Ecran ecran) {
        this.ecran = ecran;

        creditsFin = "Adventure Time\n\n" +
        "\n\n\n\n\n\n\n\n\n\n" +
        "Programmation et conception par\n" +
        "Lhasir" + 
        "\n\n" +
        "Merci d'avoir joué !";
    }

    public void afficher(Graphics2D graph) {
        this.graph = graph;

        switch (sceneNum) {
            case NA:
                break;
            case roiDemon:
                roiDemonScene();
                break;
            case finJeu:
                finJeuScene();
                break;
        }
    }

    public void roiDemonScene() {

        if (scenePhase == 0) {
            ecran.bossCombat = true;

            for (int i = 0; i < ecran.obj[1].length; i++) {

                if (ecran.obj[ecran.carteActuelle][i] == null) {
                    ecran.obj[ecran.carteActuelle][i] = new PorteFer(ecran);
                    ecran.obj[ecran.carteActuelle][i].carteX = ecran.tailleFinale*49;
                    ecran.obj[ecran.carteActuelle][i].carteY = ecran.tailleFinale*50;
                    ecran.obj[ecran.carteActuelle][i].temp = true;
                    ecran.jouerSE(23);
                    break;
                }
            }

            for (int i = 0; i < ecran.mage[1].length; i++) {
                if (ecran.mage[ecran.carteActuelle][i] == null) {
                    ecran.mage[ecran.carteActuelle][i] = new JoueurDebile(ecran);
                    ecran.mage[ecran.carteActuelle][i].carteX = ecran.joueur.carteX;
                    ecran.mage[ecran.carteActuelle][i].carteY = ecran.joueur.carteY;
                    ecran.mage[ecran.carteActuelle][i].direction = ecran.joueur.direction;
                    break;
                }
            }
            ecran.joueur.dessiner = false;

            scenePhase++;
        }

        if (scenePhase == 1) {

            ecran.joueur.carteY -= 2;

            if (ecran.joueur.carteY < ecran.tailleFinale*30) {
                scenePhase++;
            }
        }

        if (scenePhase == 2) {

            for (int i = 0; i < ecran.monstre[1].length; i++) {

                if (ecran.monstre[ecran.carteActuelle][i] != null && ecran.monstre[ecran.carteActuelle][i].boss) {
                    ecran.monstre[ecran.carteActuelle][i].endormi = false;
                    ecran.interfaceJoueur.npc = ecran.monstre[ecran.carteActuelle][i];
                    scenePhase++;
                    break;
                }
            }
        }

        if (scenePhase == 3) {

            ecran.interfaceJoueur.dessinerDialogue();
        }

        if (scenePhase == 4) {

            for (int i = 0; i < ecran.mage[1].length; i++) {

                if (ecran.mage[ecran.carteActuelle][i] != null && ecran.mage[ecran.carteActuelle][i].nom.equals(JoueurDebile.objnom)) {
                    ecran.joueur.carteX = ecran.mage[ecran.carteActuelle][i].carteX;
                    ecran.joueur.carteY = ecran.mage[ecran.carteActuelle][i].carteY;
                    ecran.mage[ecran.carteActuelle][i] = null;
                    break;
                }
            }

            ecran.joueur.dessiner = true;

            scenePhase = 0;
            sceneNum = NA;
            ecran.etatJeu = ecran.jouer;

            ecran.stopperMusique();
            ecran.jouerMusique(25);
        }
    }

    public void finJeuScene() {

        if (scenePhase == 0) {

            ecran.stopperMusique();
            ecran.interfaceJoueur.npc = new Emeraude(ecran);
            scenePhase++;
        }

        if (scenePhase == 1) {

            ecran.interfaceJoueur.dessinerDialogue();
        }

        if (scenePhase == 2) {

            ecran.jouerSE(4);
            scenePhase++;
        }

        if (scenePhase == 3) {

            if (compteurAtteint(300)) {
                scenePhase++;
            }
        }

        if (scenePhase == 4) {

            alpha += 0.005f;
            if (alpha > 1f) {
                alpha = 1f;
            }
            dessinerEcranNoir(alpha);

            if (alpha == 1f) {
                alpha = 0f;
                scenePhase++;
            }
        }

        if (scenePhase == 5) {

            dessinerEcranNoir(1f);

            alpha += 0.002f;
            if (alpha > 1f) {
                alpha = 1f;
            }
            String texte = "Félicitations !\n Après toutes ces avantures vous etes arrivés jusque ici.\n"
            + "La bataille a été rude mais vous ressortez vainqueur !\nBravo !!! Votre aventure s'arrete ici,\n"
            + "ou peut etre pas ?"; 
            dessinerString(alpha, 30f, 200, texte, 60);
            if (compteurAtteint(700)) {
                ecran.jouerMusique(0);
                scenePhase++;
            }
        }

        if (scenePhase == 6) {

            dessinerEcranNoir(1f);
            dessinerString(1f, 120f, ecran.ecranLargeur/2, "Adventure Time", 40);
            if (compteurAtteint(400)) {
                scenePhase++;
            }
        }

        if (scenePhase == 7) {

            y = ecran.ecranLargeur/2;

            dessinerEcranNoir(1f);
            dessinerString(1f, 38f, y, creditsFin, 60);
            if (compteurAtteint(300)) {
                scenePhase++;
            }
        }

        if (scenePhase == 8) {

            dessinerEcranNoir(1f);
            y--;
            dessinerString(1f, 38f, y, creditsFin, 60);
            if (compteurAtteint(900)) {
                scenePhase++;
            }
        }

        if (scenePhase == 9) {

            y = ecran.ecranLargeur/2;
            ecran.stopperMusique();
            ecran.jouerMusique(0);
            dessinerEcranNoir(1f);
            dessinerString(1f, 38f, y, "La partie a été sauvegardée.\nContinuez à explorer le monde,\nvous trouverez peut-etre quelque chose", 60);
            if (compteurAtteint(200)) {
                ecran.etatJeu = ecran.jouer;
                scenePhase = 0;
                sceneNum = NA;
                ecran.carteActuelle = 0;
                ecran.joueur.carteX = ecran.tailleFinale*49;
                ecran.joueur.carteY = ecran.tailleFinale*49;
                ecran.sauverConfiguration.sauver();
            }

        }
    }

    public void dessinerString(float alpha, float taille, int y, String texte, int ligneHauteur) {

        graph.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        graph.setColor(Color.white);
        graph.setFont(graph.getFont().deriveFont(taille));

        for (String ligne : texte.split("\n")) {
            int x = ecran.interfaceJoueur.longueurCentree(ligne);
            graph.drawString(ligne, x, y);
            y += ligneHauteur;
        }
        graph.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

    public void dessinerEcranNoir(float alpha) {

        graph.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        graph.setColor(Color.BLACK);
        graph.fillRect(0, 0, ecran.ecranLongueur, ecran.ecranLongueur);
        graph.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

    public boolean compteurAtteint(int temps) {
        
        compteur++;

        if (compteur < temps) {
            return false;
        }
        else {
            compteur = 0;
            return true;
        }

    }
}
