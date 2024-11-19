package main;

import Entites.GrossePierre;
import Entites.Mage;
import Entites.Marchant;
import donnees.Progression;
import monstres.ChauveSouris;
import monstres.Demon;
import monstres.Geant;
import monstres.Orc;
import monstres.Slime;
import object.BouclierRenforce;
import object.Chaussure;
import object.Cle;
import object.Coeur;
import object.Coffre;
import object.Emeraude;
import object.EpeeLegendaire;
import object.EpeeNormale;
import object.HacheEnPierre;
import object.Lanterne;
import object.Lit;
import object.Mana;
import object.Pieces;
import object.Pioche;
import object.Porte;
import object.PorteFer;
import object.PotionSoin;
import terrain.ArbreCassable;
import terrain.MurCassable;
import terrain.Plaque;

public class GererObject {

	Ecran ecran;
	
	public GererObject(Ecran ecran) {
		this.ecran = ecran;
	}
	
	public void setObjects() {
		
		int mapNum = 0;
		int i = 0;
		
		ecran.obj[mapNum][i] = new Cle(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*56;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*74;
		i++;
		ecran.obj[mapNum][i] = new Pieces(ecran, 2);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*98;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*1;
		i++;
		ecran.obj[mapNum][i] = new Mana(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*47;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*38;
		i++;
		ecran.obj[mapNum][i] = new Coeur(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*51;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*38;
		i++;
		ecran.obj[mapNum][i] = new BouclierRenforce(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*14;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*63;
		i++;
		ecran.obj[mapNum][i] = new HacheEnPierre(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*36;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*13;
		i++;
		ecran.obj[mapNum][i] = new Pioche(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*55;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*49;
		i++;
		ecran.obj[mapNum][i] = new PotionSoin(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*16;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*17;
		i++;
		ecran.obj[mapNum][i] = new Porte(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*90;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*65;
		i++;
		ecran.obj[mapNum][i] = new Porte(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*84;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*38;
		i++;
		ecran.obj[mapNum][i] = new Porte(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*85;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*38;
		i++;
		ecran.obj[mapNum][i] = new Coffre(ecran);
		ecran.obj[mapNum][i].initialiserContenu(new EpeeNormale(ecran));
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*92;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*66;
		i++;
		ecran.obj[mapNum][i] = new Coffre(ecran);
		ecran.obj[mapNum][i].initialiserContenu(new Pieces(ecran, 3));
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*18;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*98;
		i++;
		ecran.obj[mapNum][i] = new Coffre(ecran);
		ecran.obj[mapNum][i].initialiserContenu(new BouclierRenforce(ecran));
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*19;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*98;
		i++;
		ecran.obj[mapNum][i] = new Coffre(ecran);
		ecran.obj[mapNum][i].initialiserContenu(new Chaussure(ecran));
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*20;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*98;
		i++;
		ecran.obj[mapNum][i] = new Lit(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*20;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*65;
		i++;
		ecran.obj[mapNum][i] = new Lanterne(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*51;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*50;
		i++;
		ecran.obj[mapNum][i] = new Chaussure(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*45;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*49;
		i++;


		ecran.obj[8][i] = new PorteFer(ecran);
		ecran.obj[8][i].carteX = ecran.tailleFinale*66;
		ecran.obj[8][i].carteY = ecran.tailleFinale*48;
		i++;

		mapNum = 9;
		i = 0;
		ecran.obj[mapNum][i] = new PorteFer(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*49;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*23;
		i++;
		ecran.obj[mapNum][i] = new PorteFer(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*48;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*23;
		i++;
		ecran.obj[mapNum][i] = new EpeeLegendaire(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*48;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*22;
		i++;
		ecran.obj[mapNum][i] = new Emeraude(ecran);
		ecran.obj[mapNum][i].carteX = ecran.tailleFinale*49;
		ecran.obj[mapNum][i].carteY = ecran.tailleFinale*22;
		i++;
	}
	
	public void setMage() {
		
		int mapNum = 0;
		int i = 0;
		
		ecran.mage[mapNum][i] = new Mage(ecran);
		ecran.mage[mapNum][i].carteX = ecran.tailleFinale*90;
		ecran.mage[mapNum][i].carteY = ecran.tailleFinale*70;
		i++;
		ecran.mage[mapNum][i] = new Mage(ecran);
		ecran.mage[mapNum][i].carteX = ecran.tailleFinale*49;
		ecran.mage[mapNum][i].carteY = ecran.tailleFinale*40;
		i++;
		
		mapNum = 1;
		i = 0;
		ecran.mage[mapNum][i] = new Marchant(ecran);
		ecran.mage[mapNum][i].carteX = ecran.tailleFinale*12;
		ecran.mage[mapNum][i].carteY = ecran.tailleFinale*7;
		i++;

		mapNum = 8;
		i = 0;
		ecran.mage[mapNum][i] = new GrossePierre(ecran);
		ecran.mage[mapNum][i].carteX = ecran.tailleFinale*50;
		ecran.mage[mapNum][i].carteY = ecran.tailleFinale*46;
		i++;
		ecran.mage[mapNum][i] = new GrossePierre(ecran);
		ecran.mage[mapNum][i].carteX = ecran.tailleFinale*60;
		ecran.mage[mapNum][i].carteY = ecran.tailleFinale*46;
		i++;
		ecran.mage[mapNum][i] = new GrossePierre(ecran);
		ecran.mage[mapNum][i].carteX = ecran.tailleFinale*68;
		ecran.mage[mapNum][i].carteY = ecran.tailleFinale*46;
		i++;
	}
	
	public void setMonstre() {
		
		int mapNum = 0;
		int i = 0;
		ecran.monstre[mapNum][i] = new Slime(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*47;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*63;
		i++;
		ecran.monstre[mapNum][i] = new Slime(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*49;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*63;
		i++;
		ecran.monstre[mapNum][i] = new Slime(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*51;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*63;
		i++;
		ecran.monstre[mapNum][i] = new Slime(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*50;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*67;
		i++;
		ecran.monstre[mapNum][i] = new Geant(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*30;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*49;
		i++;

		mapNum = 1;
		i = 0;
		ecran.monstre[mapNum][i] = new Orc(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*50;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*67;
		i++;
		ecran.monstre[mapNum][i] = new Orc(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*50;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*67;
		i++;
		ecran.monstre[mapNum][i] = new Orc(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*50;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*67;
		i++;
		ecran.monstre[mapNum][i] = new Geant(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*50;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*67;
		i++;
		ecran.monstre[mapNum][i] = new Geant(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*50;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*67;
		i++;

		mapNum = 8;
		i = 0;
		ecran.monstre[mapNum][i] = new ChauveSouris(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*20;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*61;
		i++;
		ecran.monstre[mapNum][i] = new ChauveSouris(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*24;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*65;
		i++;
		ecran.monstre[mapNum][i] = new ChauveSouris(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*17;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*85;
		i++;
		ecran.monstre[mapNum][i] = new ChauveSouris(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*35;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*85;
		i++;
		ecran.monstre[mapNum][i] = new ChauveSouris(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*49;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*44;
		i++;
		ecran.monstre[mapNum][i] = new ChauveSouris(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*86;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*57;
		i++;
		ecran.monstre[mapNum][i] = new ChauveSouris(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*93;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*63;
		i++;
		ecran.monstre[mapNum][i] = new ChauveSouris(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*49;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*20;
		i++;
		ecran.monstre[mapNum][i] = new ChauveSouris(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*22;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*21;
		i++;
		ecran.monstre[mapNum][i] = new ChauveSouris(ecran);
		ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*74;
		ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*23;
		i++;

		
		i = 0;
		mapNum = 9;
		if (!Progression.roiDemonBattu) {
			ecran.monstre[mapNum][i] = new Demon(ecran);
			ecran.monstre[mapNum][i].carteX = ecran.tailleFinale*47;
			ecran.monstre[mapNum][i].carteY = ecran.tailleFinale*28;
			i++;
		}
	}
	
	public void setElementInteractif() {
		
		int mapNum = 0;

		int i = 0;
		
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 91, 64);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 92, 64);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 93, 64);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 94, 64);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 94, 65);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 94, 66);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 94, 67);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 94, 68);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 94, 69);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 94, 70);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 94, 71);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 94, 72);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 94, 73);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 94, 74);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 94, 75);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 94, 76);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 95, 76);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 49, 88);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 49, 89);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 49, 90);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 49, 91);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 49, 92);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 49, 93);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 47);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 46);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 45);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17,44);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 43);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 42);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 41);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 40);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 39);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 38);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 37);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 36);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 35);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 34);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 33);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 32);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 31);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 30);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 17, 29);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 18, 29);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 18, 28);
		i++;
		ecran.iTerrain[mapNum][i] = new ArbreCassable(ecran, 18, 27);
		i++;

		ecran.iTerrain[mapNum][i] = new MurCassable(ecran, 49, 50);
		i++;

		mapNum = 8;
		ecran.iTerrain[mapNum][i] = new Plaque(ecran, 62, 45);
		i++;
		ecran.iTerrain[mapNum][i] = new Plaque(ecran, 64, 45);
		i++;
		ecran.iTerrain[mapNum][i] = new Plaque(ecran, 66, 45);
		i++;
	}
}
