package Entites;

import java.util.Iterator;

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
		
		Poule nouv = new Poule(30, 30, ecran.nbrEntite, "M", ecran);
		nouv.carteX = 3 * ecran.tailleFinale;
		nouv.carteY = 8 * ecran.tailleFinale;
		ecran.ent.add(nouv);

		Poule nouv1 = new Poule(0, 0, ecran.nbrEntite, "M", ecran);
		nouv1.carteX = 25 * ecran.tailleFinale;
		nouv1.carteY = 2 * ecran.tailleFinale;
		ecran.ent.add(nouv1);

		Renard nouv3 = new Renard(0, 0, ecran.nbrEntite, "M", ecran);
		nouv3.carteX = 45 * ecran.tailleFinale;
		nouv3.carteY = 4 * ecran.tailleFinale;
		ecran.ent.add(nouv3);

		Renard nouv4 = new Renard(0, 0, ecran.nbrEntite, "M", ecran);
		nouv4.carteX = 31 * ecran.tailleFinale;
		nouv4.carteY = 9 * ecran.tailleFinale;
		ecran.ent.add(nouv4);

		Vipere nouv5 = new Vipere(0, 0, ecran.nbrEntite, "M", ecran);
		nouv5.carteX = 27 * ecran.tailleFinale;
		nouv5.carteY = 1 * ecran.tailleFinale;
		ecran.ent.add(nouv5);

		Vipere nouv6 = new Vipere(0, 0, ecran.nbrEntite, "M", ecran);
		nouv6.carteX = 33 * ecran.tailleFinale;
		nouv6.carteY = 26 * ecran.tailleFinale;
		ecran.ent.add(nouv6);
		
	}

	public void verifierMorts() {
		Iterator<Entite> iterator = ecran.ent.iterator();
		while (iterator.hasNext()) {
			Entite e = iterator.next();
			if (e != null && e.getPv() <= 0) {
				iterator.remove();
			}
			e.setAge(1);
			e.setFood(-5);
			e.setFertilite(20);
		}
	}
	
}