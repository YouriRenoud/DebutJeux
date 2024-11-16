package main;

import Entites.Mage;
import Entites.Marchant;
import monstres.Slime;
import object.BouclierRenforce;
import object.Chaussure;
import object.Cle;
import object.Coeur;
import object.Coffre;
import object.HacheEnPierre;
import object.Lanterne;
import object.Lit;
import object.Mana;
import object.Pieces;
import object.Porte;
import object.PotionSoin;
import terrain.ArbreCassable;

public class GererObject {

	Ecran ecran;
	
	public GererObject(Ecran ecran) {
		this.ecran = ecran;
	}
	
	public void setObjects() {
		
		int mapNum = 0;
		int i = 0;
		
		ecran.obj[mapNum][i] = new Cle(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*22;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*21;
		i++;
		ecran.obj[mapNum][i] = new Pieces(ecran, 1);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*25;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*21;
		i++;
		ecran.obj[mapNum][i] = new Mana(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*22;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*19;
		i++;
		ecran.obj[mapNum][i] = new Coeur(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*25;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*24;
		i++;
		ecran.obj[mapNum][i] = new BouclierRenforce(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*20;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*20;
		i++;
		ecran.obj[mapNum][i] = new HacheEnPierre(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*35;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*21;
		i++;
		ecran.obj[mapNum][i] = new PotionSoin(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*36;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*23;
		i++;
		ecran.obj[mapNum][i] = new Porte(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*24;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*21;
		i++;
		ecran.obj[mapNum][i] = new Coffre(ecran, new Chaussure(ecran));
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*22;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*21;
		i++;
		ecran.obj[mapNum][i] = new Coffre(ecran, new Lanterne(ecran));
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*23;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*15;
		i++;
		ecran.obj[mapNum][i] = new Lit(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*23;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*24;
		i++;
	}
	
	public void setMage() {
		
		int mapNum = 0;
		int i = 0;
		
		ecran.mage[mapNum][i] = new Mage(ecran);
		ecran.mage[mapNum][i].carteX = ecran.tailleFinale*21;
		ecran.mage[mapNum][i].carteY = ecran.tailleFinale*21;
		i++;
		
		mapNum = 1;
		i = 0;
		ecran.mage[mapNum][i] = new Marchant(ecran);
		ecran.mage[mapNum][i].carteX = ecran.tailleFinale*12;
		ecran.mage[mapNum][i].carteY = ecran.tailleFinale*7;
		i++;
	}
	
	public void setMonstre() {
		
		int mapNum = 0;
		
		ecran.monstre[mapNum][0] = new Slime(ecran);
		ecran.monstre[mapNum][0].carteX = ecran.tailleFinale*23;
		ecran.monstre[mapNum][0].carteY = ecran.tailleFinale*37;
		ecran.monstre[mapNum][1] = new Slime(ecran);
		ecran.monstre[mapNum][1].carteX = ecran.tailleFinale*23;
		ecran.monstre[mapNum][1].carteY = ecran.tailleFinale*27;
		ecran.monstre[mapNum][2] = new Slime(ecran);
		ecran.monstre[mapNum][2].carteX = ecran.tailleFinale*23;
		ecran.monstre[mapNum][2].carteY = ecran.tailleFinale*40;
	}
	
	public void setElementIntercatif() {
		
		int mapNum = 0;

		int i = 0;
		
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 27, 7);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 28, 7);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 29, 7);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 30, 7);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 31, 7);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 32, 7);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 33, 7);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 20, 22);
		i++;
		//ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 21, 21);
		i++;
	}
}
