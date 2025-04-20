package donnees;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Entites.Entite;
import main.Ecran;
import object.BouclierBasique;
import object.BouclierRenforce;
import object.Chaussure;
import object.Cle;
import object.Coeur;
import object.Coffre;
import object.EpeeNormale;
import object.HacheEnPierre;
import object.Lanterne;
import object.Lit;
import object.Mana;
import object.Pieces;
import object.Porte;
import object.PotionSoin;

public class SauverChargement {
    
    Ecran ecran;

    public SauverChargement(Ecran ecran) {
        this.ecran = ecran;
    }

    public void sauver() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stockageDonnees.dst"));
            StockageDonnees sd = new StockageDonnees();

            sd.niveau = ecran.joueur.niveau;
            sd.vie = ecran.joueur.vie;
            sd.vieMax = ecran.joueur.vieMax;
            sd.mana = ecran.joueur.mana;
            sd.maxMana = ecran.joueur.maxMana;
            sd.pieces = ecran.joueur.argent;
            sd.force = ecran.joueur.force;
            sd.agilite = ecran.joueur.agilite;
            sd.experience = ecran.joueur.experience;
            sd.niveauSuivant = ecran.joueur.niveauSuivant;
            sd.vitesse = ecran.joueur.vitesse;
            sd.vitesseDefaut = ecran.joueur.vitesseDefaut;
            sd.carteX = ecran.joueur.carteX;
            sd.carteY = ecran.joueur.carteY;
            
            sd.carteActuelle = ecran.carteActuelle;

            for (int i = 0; i < ecran.joueur.inventaire.size(); i++) {
                sd.nomItems.add(ecran.joueur.inventaire.get(i).nom);
                sd.quantiteItems.add(ecran.joueur.inventaire.get(i).possedes);
            }
            sd.armeActuelleSlot = ecran.joueur.getArmeSlot();
            sd.armureActuelleSlot = ecran.joueur.getBouclierSlot();
            sd.lumiereActuelleSlot = ecran.joueur.getLumiereSlot();

            sd.mondeObjNoms = new String[ecran.maxCartes][ecran.obj[1].length];
            sd.mondeObjX = new int[ecran.maxCartes][ecran.obj[1].length];
            sd.mondeObjY = new int[ecran.maxCartes][ecran.obj[1].length];
            sd.typeObj = new int[ecran.maxCartes][ecran.obj[1].length];
            sd.mondeObjContenu = new String[ecran.maxCartes][ecran.obj[1].length];
            sd.mondeObjOuverts = new boolean[ecran.maxCartes][ecran.obj[1].length];

            sd.monstreNoms = new String[ecran.maxCartes][ecran.monstre[1].length];
            sd.monstreX = new int[ecran.maxCartes][ecran.monstre[1].length];
            sd.monstreY = new int[ecran.maxCartes][ecran.monstre[1].length];
            sd.monstreVie = new int[ecran.maxCartes][ecran.monstre[1].length];
            sd.monstreCarteNum = new int[ecran.maxCartes][ecran.monstre[1].length];

            for (int i = 0; i < ecran.maxCartes; i++) {
                for (int j = 0; j < ecran.obj[1].length; j++) {
                    if (ecran.obj[i][j] != null) {
                        sd.mondeObjNoms[i][j] = ecran.obj[i][j].nom;
                        sd.mondeObjX[i][j] = ecran.obj[i][j].carteX;
                        sd.mondeObjY[i][j] = ecran.obj[i][j].carteY;
                        if (ecran.obj[i][j].contenu != null) {
                            sd.mondeObjContenu[i][j] = ecran.obj[i][j].contenu.nom;
                            sd.mondeObjOuverts[i][j] = ecran.obj[i][j].ouvert;
                        }
                        if (ecran.obj[i][j].nom.equals("Piece de monnaie")) {
                            sd.typeObj[i][j] = ecran.obj[i][j].type;
                        }

                    }
                    else {
                        sd.mondeObjNoms[i][j] = "NN";
                    }
                }
            }

            for (int i = 0; i < ecran.maxCartes; i++) {
                for (int j = 0; j < ecran.monstre[1].length; j++) {
                    if (ecran.monstre[i][j] != null 
                    && ecran.monstre[i][j].nom != "Roi des squelettes"
                    && ecran.monstre[i][j].nom != "Roi des demons") {
                        sd.monstreNoms[i][j] = ecran.monstre[i][j].nom;
                        sd.monstreX[i][j] = ecran.monstre[i][j].carteX;
                        sd.monstreY[i][j] = ecran.monstre[i][j].carteY;
                        sd.monstreVie[i][j] = ecran.monstre[i][j].vie;
                        sd.monstreCarteNum[i][j] = i;
                    }
                    else {
                        sd.monstreNoms[i][j] = "NN";
                    }
                }
            }

            oos.writeObject(sd);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void charger() {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stockageDonnees.dst"));
            StockageDonnees sd = (StockageDonnees)ois.readObject();

            ecran.joueur.niveau = sd.niveau;
            ecran.joueur.vie = sd.vie;
            ecran.joueur.vieMax = sd.vieMax;
            ecran.joueur.mana = sd.mana;
            ecran.joueur.maxMana = sd.maxMana;
            ecran.joueur.argent = sd.pieces;
            ecran.joueur.force = sd.force;
            ecran.joueur.agilite = sd.agilite;
            ecran.joueur.experience = sd.experience;
            ecran.joueur.niveauSuivant = sd.niveauSuivant;
            ecran.joueur.vitesse = sd.vitesse;
            ecran.joueur.vitesseDefaut = sd.vitesseDefaut;
            ecran.joueur.carteX = sd.carteX;
            ecran.joueur.carteY = sd.carteY;

            ecran.carteActuelle = sd.carteActuelle;

            ecran.joueur.inventaire.clear();
            for (int i = 0; i < sd.nomItems.size(); i++) {
                Entite obj = ecran.generateur.getObject(sd.nomItems.get(i), 0);
                obj.possedes = sd.quantiteItems.get(i);
                ecran.joueur.inventaire.add(obj);
            }
            ecran.joueur.armeActuelle = ecran.joueur.inventaire.get(sd.armeActuelleSlot);
            ecran.joueur.bouclierActuel = ecran.joueur.inventaire.get(sd.armureActuelleSlot);
            ecran.joueur.lumiereActuelle = ecran.joueur.inventaire.get(sd.lumiereActuelleSlot);
            ecran.joueur.getAttaque();
            ecran.joueur.getDefense();
            ecran.joueur.getAttImage();

            for (int i = 0; i < ecran.maxCartes; i++) {
                for (int j = 0; j < ecran.obj[1].length; j++) {
                    if (sd.mondeObjNoms[i][j].equals("NN")) {
                        ecran.obj[i][j] = null;
                    }
                    else {
                        ecran.obj[i][j] = ecran.generateur.getObject(sd.mondeObjNoms[i][j], sd.typeObj[i][j]);
                        ecran.obj[i][j].carteX = sd.mondeObjX[i][j];
                        ecran.obj[i][j].carteY = sd.mondeObjY[i][j];
                        if (sd.mondeObjContenu[i][j] != null) {
                            ecran.obj[i][j].initialiserContenu(ecran.generateur.getObject(sd.mondeObjContenu[i][j], sd.typeObj[i][j]));
                            ecran.obj[i][j].ouvert = sd.mondeObjOuverts[i][j];
                            if (ecran.obj[i][j].ouvert) {
                                ecran.obj[i][j].arriere = ecran.obj[i][j].arriere1;
                            }
                            
                        }
                    }
                }
            }

            for (int i = 0; i < ecran.maxCartes; i++) {
                for (int j = 0; j < ecran.monstre[1].length; j++) {
                    if (sd.monstreNoms[i][j].equals("NN")) {
                        ecran.monstre[i][j] = null;
                    }
                    else {
                        ecran.monstre[i][j] = ecran.generateur.getMonstre(sd.monstreNoms[i][j], sd.monstreCarteNum[i][j]);
                        if (ecran.monstre[i][j] == null) {
                            System.out.println("Monstre null : " + sd.monstreNoms[i][j]);
                        }
                        ecran.monstre[i][j].carteX = sd.monstreX[i][j];
                        ecran.monstre[i][j].carteY = sd.monstreY[i][j];
                        ecran.monstre[i][j].vie = sd.monstreVie[i][j];
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
