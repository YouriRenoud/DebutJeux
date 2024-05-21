package Entites;

import java.util.Iterator;
import java.util.Random;

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

		Random random = new Random();
		for (int i = 0; i < 50; i = i + 3) {
			for (int j = 0; j < 50; j = j + 3) {
				double renardType = random.nextInt(100) + 1;
				double pouleType = random.nextInt(100) + 1;
				double vipereType = random.nextInt(100) + 1;
				int sexe = random.nextInt(2);
				if (renardType <= 20) {
					if (sexe == 0) {
						ecran.ent.add(new Renard(i, j, ecran.nbrEntite, "M", ecran));
					} else {
						ecran.ent.add(new Renard(i, j, ecran.nbrEntite, "F", ecran));
					}
				}
				if (vipereType <= 20) {
					if (sexe == 0) {
						ecran.ent.add(new Vipere(i, j, ecran.nbrEntite, "M", ecran));
					} else {
						ecran.ent.add(new Vipere(i, j, ecran.nbrEntite, "F", ecran));
					}
				}
				if (pouleType <= 20) {
					if (sexe == 0) {
						ecran.ent.add(new Poule(i, j, ecran.nbrEntite, "M", ecran));
					} else {
						ecran.ent.add(new Poule(i, j, ecran.nbrEntite, "F", ecran));
					}
				}
			}
		}
		// System.out.println(ecran.ent);

		// ecran.ent.add(new Poule(5,5,ecran.nbrEntite,"M",ecran));

		// Poule nouv1 = new Poule(0, 0, ecran.nbrEntite, "M",
		// ecran);ecran.ent.add(nouv1);
		// Renard nouv3 = new Renard(0, 0, ecran.nbrEntite, "M", ecran);

		// Renard nouv4 = new Renard(0, 0, ecran.nbrEntite, "M", ecran);

		// Vipere nouv5 = new Vipere(0, 0, ecran.nbrEntite, "M", ecran);

		// Vipere nouv6 = new Vipere(0, 0, ecran.nbrEntite, "M", ecran);

		// Poule nouv7 = new Poule(30, 30, ecran.nbrEntite, "M", ecran);

		// Poule nouv8 = new Poule(0, 0, ecran.nbrEntite, "M", ecran);

		// Renard nouv9 = new Renard(0, 0, ecran.nbrEntite, "M", ecran);

		// Renard nouv10 = new Renard(0, 0, ecran.nbrEntite, "M", ecran);

		// Vipere nouv11 = new Vipere(0, 0, ecran.nbrEntite, "M", ecran);

		// Vipere nouv12 = new Vipere(0, 0, ecran.nbrEntite, "M", ecran);

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