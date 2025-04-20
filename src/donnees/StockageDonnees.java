package donnees;

import java.io.Serializable;
import java.util.ArrayList;

public class StockageDonnees implements Serializable {
    
    int niveau;
    int vie;
    int vieMax;
    int mana;
    int maxMana;
    int pieces;
    int force;
    int agilite;
    int experience;
    int niveauSuivant;
    int vitesse;
    int vitesseDefaut;
    int carteX;
    int carteY;

    int carteActuelle;

    ArrayList<String> nomItems = new ArrayList<>();
    ArrayList<Integer> quantiteItems = new ArrayList<>();

    int armeActuelleSlot;
    int armureActuelleSlot;
    int lumiereActuelleSlot;

    String mondeObjNoms[][];
    int mondeObjX[][];
    int mondeObjY[][];
    int typeObj[][];
    String mondeObjContenu[][];
    boolean mondeObjOuverts[][];

    String monstreNoms[][];
    int monstreX[][];
    int monstreY[][];
    int monstreVie[][];
    int monstreCarteNum[][];
}
