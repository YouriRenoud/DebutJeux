package terrain;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Ecran;
import main.UtilityTool;

public class GestionTerrain {
	
	Ecran ecran;
	public Terrain[] terrain;
	public int parcoursCarte[][][];
	
	public GestionTerrain(Ecran ecran) {
		this.ecran = ecran;
		
		terrain = new Terrain[50];
		parcoursCarte = new int[ecran.maxCartes][ecran.mondeColMax][ecran.mondeLignMax];
		
		getImage();
		
		chargerCarte("/cartes/monde1.txt", 0);
		chargerCarte("/cartes/interior01.txt", 1);
	}
	
	public void getImage() {

			init(0, "Herbe", false);
			init(10, "Herbe", false);
			init(2, "EauPeuProfonde", false);
			init(1, "Mur", true);
			init(40, "Mur", true);
			init(5, "Sable", false);
			init(4, "Arbre (2)", true);
			init(41, "Arbre (2)", true);
			init(3, "Terre", false);
			init(6, "Sable2", false);
			init(7, "Desert", false);
			init(8, "Maison", false);
			init(43, "Sol", false);
			init(44, "Table", true);
	}
	
	public void chargerCarte(String plan, int carteNum) {
		try {
			InputStream carte = getClass().getResourceAsStream(plan);
			BufferedReader parcours = new BufferedReader(new InputStreamReader(carte));
			
			int colonnes = 0;
			int lignes = 0;
			
			while (colonnes < ecran.mondeColMax && lignes < ecran.mondeLignMax) {
				String ligne = parcours.readLine();
				String terrains[] = ligne.split(" ");
				
				while (colonnes < ecran.mondeColMax) {
					int num = Integer.parseInt(terrains[colonnes]);
					
					parcoursCarte[carteNum][colonnes][lignes] = num;
					colonnes++;
				}
				if (colonnes == ecran.mondeColMax) {
					colonnes = 0;
					lignes++;
				}
			}
			parcours.close();
			
		} catch(Exception e) {
			
		}
	}
	
	public void init(int index, String image, boolean collision) {
		
		UtilityTool outil = new UtilityTool();
		try {
			terrain[index] = new Terrain();
			terrain[index].image = ImageIO.read(getClass().getResourceAsStream("/terrains/" + image + ".png"));
			terrain[index].image = outil.scaleImage(terrain[index].image, ecran.tailleFinale, ecran.tailleFinale);
			terrain[index].interaction = collision;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void afficher(Graphics2D graph) {
		
		int colonne = 0, ligne = 0;
		
		while (colonne < ecran.mondeColMax && ligne < ecran.mondeLignMax) {
			
			int mondeX = colonne * ecran.tailleFinale;
			int mondeY = ligne * ecran.tailleFinale;
			
			int actuelX = mondeX - ecran.joueur.carteX + ecran.joueur.ecranX;
			int actuelY = mondeY - ecran.joueur.carteY + ecran.joueur.ecranY;

			if (mondeX + ecran.tailleFinale > ecran.joueur.carteX - ecran.joueur.ecranX 
					&& mondeX - ecran.tailleFinale < ecran.joueur.carteX + ecran.joueur.ecranX
					&& mondeY + ecran.tailleFinale > ecran.joueur.carteY - ecran.joueur.ecranY 
					&& mondeY - ecran.tailleFinale < ecran.joueur.carteY + ecran.joueur.ecranY) { 
				graph.drawImage(terrain[parcoursCarte[ecran.carteActuelle][colonne][ligne]].image, actuelX, actuelY, null);
			}
			colonne++;
			
			if (colonne == ecran.mondeColMax) {
				colonne = 0;
				ligne++;
			}
		}
	}
	
}
