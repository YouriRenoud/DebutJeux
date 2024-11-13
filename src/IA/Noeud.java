package IA;

public class Noeud {
    
    Noeud parent;
    public int col, lign;
    int gCost, hCost, fCost;
    boolean solide;
    boolean ouvert, verifie;

    public Noeud(int col, int lign) {
        this.col = col;
        this.lign = lign;
    }
}
