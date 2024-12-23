package terrain;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.Ecran;
import main.UtilityTool;

public class GestionTerrain {
	
	Ecran ecran;
	public Terrain[] terrain;
	public int parcoursCarte[][][];

	public boolean dessinerChemin = true;

	public ArrayList<String> nomFichier = new ArrayList<>();
	public ArrayList<String> collisionStatut = new ArrayList<>();
	
	public GestionTerrain(Ecran ecran) {
		this.ecran = ecran;
		
		InputStream fichier = getClass().getResourceAsStream("/cartes/terrainMondeDepart.txt");
		BufferedReader lire = new BufferedReader(new InputStreamReader(fichier));

		String ligne;

		try {
			while ((ligne = lire.readLine()) != null) {
				nomFichier.add(ligne);
				collisionStatut.add(lire.readLine());
			}
			lire.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		terrain = new Terrain[nomFichier.size()];		
		getImage();

		fichier = getClass().getResourceAsStream("/cartes/mondeDepart.txt");
		lire = new BufferedReader(new InputStreamReader(fichier));

		try {
			ligne = lire.readLine();
			String dimensions[] = ligne.split(" ");

			ecran.mondeColMax = dimensions.length;
			ecran.mondeLignMax = dimensions.length;
			parcoursCarte = new int[ecran.maxCartes][ecran.mondeColMax][ecran.mondeLignMax];

			lire.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		chargerCarte("/cartes/mondeDepart.txt", 0);
		
		// chargerCarte("/cartes/monde1.txt", 1);
		chargerCarte("/cartes/interior01.txt", 1);
		chargerCarte("/cartes/monde2.txt", 2);
		chargerCarte("/cartes/grotte.txt", 3);
		chargerCarte("/cartes/village.txt", 4);
		chargerCarte("/cartes/entrainement.txt", 5);
		chargerCarte("/cartes/dongeonDebut.txt", 8);
		chargerCarte("/cartes/dongeonBossFin.txt", 9);
	}
	
	public void getImage() {

		for (int i=0; i < nomFichier.size(); i++) {
			init(i, nomFichier.get(i), Boolean.parseBoolean(collisionStatut.get(i)));}
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
			terrain[index].image = ImageIO.read(getClass().getResourceAsStream("/terrains/" + image));
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

		// if (dessinerChemin) {
		// 	graph.setColor(new Color(255, 0, 0, 70));

		// 	for (int i=0; i < ecran.chemin.cheminList.size(); i++) {
		// 		int carteX = ecran.chemin.cheminList.get(i).col * ecran.tailleFinale;
		// 		int carteY = ecran.chemin.cheminList.get(i).lign * ecran.tailleFinale;
		// 		int ecranX = carteX - ecran.joueur.carteX + ecran.joueur.ecranX;
		// 		int ecranY = carteY - ecran.joueur.carteY + ecran.joueur.ecranY;

		// 		graph.fillRect(ecranX, ecranY, ecran.tailleFinale, ecran.tailleFinale);
		// 	}
		// }
	}
	
}
