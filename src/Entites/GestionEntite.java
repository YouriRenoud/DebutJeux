package Entites;

import main.Ecran;
import main.FonctionUtiles;
import terrain.Terrain;

public class GestionEntite {
	
	Ecran ecran;
	public Terrain[] terrain;
	public int parcoursCarte[][];
	
	public GestionEntite(Ecran ecran) {
		this.ecran = ecran;
		
	}

    public void setObjects() {
		
		ecran.ent[0] = new Poule(ecran.nbrEntite, "M", ecran);
		ecran.ent[0].carteX = 25 * ecran.tailleFinale;
		ecran.ent[0].carteY = 8 * ecran.tailleFinale;
		
		ecran.ent[1] = new Poule(ecran.nbrEntite, "F", ecran);
		ecran.ent[1].carteX = 25 * ecran.tailleFinale;
		ecran.ent[1].carteY = 38 * ecran.tailleFinale;

        ecran.ent[2] = new Vipere(ecran.nbrEntite, "F", ecran);
		ecran.ent[2].carteX = 20 * ecran.tailleFinale;
		ecran.ent[2].carteY = 40 * ecran.tailleFinale;

        ecran.ent[3] = new Vipere(ecran.nbrEntite, "F", ecran);
		ecran.ent[3].carteX = 10 * ecran.tailleFinale;
		ecran.ent[3].carteY = 3 * ecran.tailleFinale;
		
	}
}