package IA;

import java.util.ArrayList;

import Entites.Entite;
import main.Ecran;
import terrain.ElementInteractif;

public class ChercheurChemin {
    
    Ecran ecran;
    Noeud[][] grille;
    ArrayList<Noeud> ouvert = new ArrayList<>();
    public ArrayList<Noeud> cheminList = new ArrayList<>();
    Noeud depart, arrivee, actuel;
    boolean atteint = false;
    int etape = 0;

    int debug = 0;

    public ChercheurChemin(Ecran ecran) {
        this.ecran = ecran;
        preparerNoeuds();
    }
    

    public void preparerNoeuds() {
        grille = new Noeud[ecran.mondeColMax][ecran.mondeLignMax];
        
        int col = 0;
        int lign = 0;
    
        while (col < ecran.mondeColMax && lign < ecran.mondeLignMax) {

            grille[col][lign] = new Noeud(col, lign);

            col++;
            if (col == ecran.mondeColMax) {
                col = 0;
                lign++;
            }
        }
    }

    public void initialiserNoeud() {
        int col = 0;
        int lign = 0;

        while (col < ecran.mondeColMax && lign < ecran.mondeLignMax) {
                grille[col][lign].ouvert = false;
                grille[col][lign].verifie = false;
                grille[col][lign].solide = false;
            
            col++;
            if (col == ecran.mondeColMax) {
                col = 0;
                lign++;
            }
        }
        ouvert.clear();
        cheminList.clear();
        atteint = false;
        etape = 0;
    }

    public void setNoeuds(int col, int lign, int goalCol, int goalLign, Entite entite) {

        initialiserNoeud();

        depart = grille[col][lign];
        arrivee = grille[goalCol][goalLign];
        actuel = depart;
        ouvert.add(actuel);

        int colActuel = 0;
        int lignActuel = 0;

        while(colActuel < ecran.mondeColMax && lignActuel < ecran.mondeLignMax) {
            int numTerrain = ecran.terrain.parcoursCarte[ecran.carteActuelle][colActuel][lignActuel];
               if (ecran.terrain.terrain[numTerrain].interaction) {
                grille[colActuel][lignActuel].solide = true;
            }
            for (int i = 0; i < ecran.iTerrain[1].length; i++) {
                ElementInteractif entiteActuelle = ecran.iTerrain[ecran.carteActuelle][i];
                if (entiteActuelle != null && entiteActuelle.destructible) {
                    int iCol = ecran.iTerrain[ecran.carteActuelle][i].carteX/ecran.tailleFinale;
                    int iLign = ecran.iTerrain[ecran.carteActuelle][i].carteY/ecran.tailleFinale;
                    grille[iCol][iLign].solide = true;
                }
            }

            getCout(grille[colActuel][lignActuel]);
            colActuel++;
            if (colActuel == ecran.mondeColMax) {
                colActuel = 0;
                lignActuel++;    
            }
        }

        if (debug == 0) {
            afficherGrilleSolide();
            debug = 1;
        }
    }

    public void afficherGrilleSolide() {
        for (int lign = 0; lign < ecran.mondeLignMax; lign++) {
            for (int col = 0; col < ecran.mondeColMax; col++) {
                if (grille[col][lign].solide) {
                    System.out.print("S ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
    

    public void getCout(Noeud noeud) {
        int gCost = Math.abs(noeud.col - depart.col) + Math.abs(noeud.lign - depart.lign);
        int hCost = Math.abs(noeud.col - arrivee.col) + Math.abs(noeud.lign - arrivee.lign);
        noeud.hCost = hCost;
        noeud.gCost = gCost;
        noeud.fCost = noeud.hCost + noeud.gCost;
    }

    public boolean chercher() {
        
        while (atteint == false && etape < 500) {
            int colActuel = actuel.col;
            int lignActuel = actuel.lign;

            actuel.verifie = true;
            ouvert.remove(actuel);

            if (colActuel - 1 >= 0) {
                ouvrirNoeud(grille[colActuel - 1][lignActuel]);
            }
            if (colActuel + 1 < ecran.mondeColMax) {
                ouvrirNoeud(grille[colActuel + 1][lignActuel]);
            }
            if (lignActuel - 1 >= 0) {
                ouvrirNoeud(grille[colActuel][lignActuel - 1]);
            }
            if (lignActuel + 1 < ecran.mondeLignMax) {
                ouvrirNoeud(grille[colActuel][lignActuel + 1]);
            }

            int meilleurNoeudIndex = 0;
            int meilleurNoeudCout = 999;

            for (int i = 0; i < ouvert.size(); i++) {
                if (ouvert.get(i).fCost < meilleurNoeudCout) {
                    meilleurNoeudCout = ouvert.get(i).fCost;
                    meilleurNoeudIndex = i;
                }
                else if (ouvert.get(i).fCost == meilleurNoeudCout) {
                    if (ouvert.get(i).gCost < ouvert.get(meilleurNoeudIndex).gCost) {
                        meilleurNoeudIndex = i;
                    }
                }
            }

            if (ouvert.size() == 0) {
                break;
            }

            actuel = ouvert.get(meilleurNoeudIndex);

            if (actuel == arrivee) {
                atteint = true;
                suivreChemin();
            }
            etape++;
        }

        return atteint;
    }

    public void suivreChemin() {
        Noeud noeudCourant = arrivee;
        Noeud debug = noeudCourant;
        while (noeudCourant != depart) {
            cheminList.add(0,noeudCourant);
            noeudCourant = noeudCourant.parent;
        }
    }

    public void ouvrirNoeud(Noeud noeud) {

        if (!noeud.ouvert && !noeud.verifie && !noeud.solide) {
            noeud.ouvert = true;
            noeud.parent = actuel;
            ouvert.add(noeud);
        }
    }
}
