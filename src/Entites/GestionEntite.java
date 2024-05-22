package Entites;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import main.Ecran;
import main.FonctionUtiles;
import terrain.GestionTerrain;
import terrain.Terrain;

public class GestionEntite {

	Ecran ecran;
	public Terrain[] terrain;

	public GestionEntite(Ecran ecran) {
		this.ecran = ecran;

	}

	public void setObjects() {

		Random random = new Random();
		for (int i = 2; i < ecran.mondeColMax; i = i + 3) {
			for (int j = 2; j < ecran.mondeLignMax; j = j + 3) {
				int typeTerrain = ecran.terrain.parcoursCarte[i][j];
				double renardType = random.nextInt(100) + 1;
				double pouleType = random.nextInt(100) + 1;
				double vipereType = random.nextInt(100) + 1;
				int sexe = random.nextInt(2);
				if (renardType <= 20 && ecran.nbrEntite < 400 && ecran.terrain.terrain[typeTerrain].interaction == false) {
					if (sexe == 0) {
						ecran.ent.add(new Renard(i, j, ecran.nbrEntite, "M", ecran));
					} else {
						ecran.ent.add(new Renard(i, j, ecran.nbrEntite, "F", ecran));
					}
				}
				if (vipereType <= 20 && ecran.nbrEntite < 400 && ecran.terrain.terrain[typeTerrain].interaction == false) {
					if (sexe == 0) {
						ecran.ent.add(new Vipere(i, j, ecran.nbrEntite, "M", ecran));
					} else {
						ecran.ent.add(new Vipere(i, j, ecran.nbrEntite, "F", ecran));
					}
				}
				if (pouleType <= 20 && ecran.nbrEntite < 400 && ecran.terrain.terrain[typeTerrain].interaction == false) {
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
		List<Entite> aRetirer = new ArrayList<>();
		for (Entite e: ecran.ent) {
			if (e != null && e.getPv() <= 0) {
				if (e instanceof Poule) {
					ecran.nombrePoules--;
				}
				if (e instanceof Renard) {
					ecran.nombreRenards--;
				}
				if (e instanceof Vipere) {
					ecran.nombreViperes--;
				}
				aRetirer.add(e);
			}
			e.setAge(1);
			e.setFood(-1);
			e.setFertilite(20);
			System.out.println(e.getFood());
		}
		ecran.ent.removeAll(aRetirer);
		ecran.nbrEntite -= aRetirer.size();
	}

}