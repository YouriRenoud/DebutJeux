package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import Entites.Entite;
import Entites.Joueur;
import IA.ChercheurChemin;
import terrain.ElementInteractif;
import terrain.GestionTerrain;

public class Ecran extends JPanel implements Runnable {
	final int tailleElement = 16;
	final int echelle = 3;
	
	public final int tailleFinale = tailleElement * echelle;
	
	public final int colonneMax = 20;
	public final int ligneMax = 12;
	public final int ecranLongueur = colonneMax * tailleFinale;
	public final int ecranLargeur = ligneMax * tailleFinale;
	
	// param map monde
	public final int mondeColMax = 50;
	public final int mondeLignMax = 50;
	public final int mondeLongueur = tailleFinale * mondeColMax;
	public final int mondeLargeur = tailleFinale * mondeLignMax;
	public final int maxCartes = 10;
	public int carteActuelle = 0;
	
	// plein ecran
	int ecranWidth = mondeLongueur;
	int ecranHeight = mondeLargeur;
	BufferedImage tempEcran;
	Graphics2D graph2;
	
	public ActionClavier action = new ActionClavier(this);
	public Configuration configuration = new Configuration(this);
	Thread filDuJeu;
	
	public GestionTerrain terrain = new GestionTerrain(this);
	public VerifierCollision collisions = new VerifierCollision(this);
	public GererObject gerer = new GererObject(this);
	public GererEvent event = new GererEvent(this);
	public ChercheurChemin chemin = new ChercheurChemin(this);
	
	public Son son = new Son();
	public Son musique = new Son();
	
	public Joueur joueur = new Joueur(this, action);
	public Entite obj[][] = new Entite[maxCartes][20];
	public Entite mage[][] = new Entite[maxCartes][10];
	public Entite monstre[][] = new Entite[maxCartes][20];
	ArrayList<Entite> listEntite = new ArrayList<>();
	public ArrayList<Entite> listProjectiles = new ArrayList<>();
	public ArrayList<Entite> listParticules = new ArrayList<>();
	public ElementInteractif iTerrain[][] = new ElementInteractif[maxCartes][50];
	
	public UI interfaceJoueur = new UI(this);
	
	//etats jeu
	public int etatJeu;
	public final int intro = 0;
	public final int pause = 2;
	public final int jouer = 1;
	public final int parler = 3;
	public final int stats = 4;
	public final int options = 5;
	public final int perdu = 6;
	public final int transitionCartes = 7;
	public final int marchander = 8;
	
	public boolean pleinEcranOn = false;
	
	int FPS = 60;
	
	public Ecran() {
		
		this.setPreferredSize(new Dimension(ecranLongueur, ecranLargeur));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(action);
		this.setFocusable(true);
	}
	
	public void initialiserJeu() {
		gerer.setObjects();
		gerer.setMage();
		gerer.setMonstre();
		gerer.setElementIntercatif();
		//jouerMusique(0);
		etatJeu = intro;
		
		tempEcran = new BufferedImage(ecranWidth, ecranHeight, BufferedImage.TYPE_INT_ARGB);
		graph2 = (Graphics2D)tempEcran.getGraphics();
		
	}

	public void lancerFil() {
		
		filDuJeu = new Thread(this);
		filDuJeu.start();
	}
	
	@Override
	public void run() {
		
		while (filDuJeu != null) {
			
			double intervalle = 1000000000/FPS;
			double prochainIntervalle = System.nanoTime() + intervalle;
						
			miseAJour();
			
			repaint();
			//dessinerTempEcran();
			//dessinerEcran();
			
			try {
				double tempsRestant = (prochainIntervalle - System.nanoTime())/1000000;
				
				if (tempsRestant < 0) {
					tempsRestant = 0;
				}
				
				Thread.sleep((long) tempsRestant);
				
				prochainIntervalle += intervalle;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void reessayer() {
		joueur.valeurDefaut();
		joueur.retablirVieMana();
		gerer.setMonstre();
		gerer.setMage();
	}
	
	public void recommencer() {
		joueur.initialiser();
		joueur.setItems();
		gerer.setObjects();
		gerer.setMage();
		gerer.setMonstre();
		gerer.setElementIntercatif();
	}
	
	public void miseAJour () {
		if (etatJeu == jouer) {
			joueur.miseAJour();
			
			for (int i = 0; i < mage[1].length; i++) {
				if (mage[carteActuelle][i] != null) {
					mage[carteActuelle][i].miseAJour();
				}
			}
			
			for (int i = 0; i < monstre[1].length; i++) {
				if (monstre[carteActuelle][i] != null) {
					if (monstre[carteActuelle][i].vivant && !monstre[carteActuelle][i].mort) {
						monstre[carteActuelle][i].miseAJour();
					}
					if (!monstre[carteActuelle][i].vivant) {
						monstre[carteActuelle][i].verifierDrop();
						monstre[carteActuelle][i] = null;
					}
				}
			}

			for (int i = 0; i < listProjectiles.size(); i++) {
				if (listProjectiles.get(i) != null) {
					if (listProjectiles.get(i).vivant == true) {
						listProjectiles.get(i).miseAJour();
					}
					if (listProjectiles.get(i).vivant == false) {
						listProjectiles.remove(i);
					}
				}
			}
			
			for (int i = 0; i < listParticules.size(); i++) {
				if (listParticules.get(i) != null) {
					if (listParticules.get(i).vivant == true) {
						listParticules.get(i).miseAJour();
					}
					if (listParticules.get(i).vivant == false) {
						listParticules.remove(i);
					}
				}
			}
			
			for (int i = 0; i < iTerrain[1].length; i++) {
				if (iTerrain[carteActuelle][i] != null) {
					iTerrain[carteActuelle][i].miseAJour();
				}
			}
		}
		
		if (etatJeu == pause) {
			
		}
	}
	
	public void dessinerTempEcran() {
		
		long drawStart = 0;
		
		if (etatJeu == intro) {
			interfaceJoueur.afficher(graph2);
		}
		else {
			terrain.afficher(graph2);
			
			for (int i = 0; i < iTerrain[1].length; i++) {
				if (iTerrain[carteActuelle][i] != null) {
					iTerrain[carteActuelle][i].afficher(graph2);
				}
			}
			
			listEntite.add(joueur);
			
			for (int i = 0; i < mage[1].length; i++) {
				if (mage[carteActuelle][i] != null) {
					listEntite.add(mage[carteActuelle][i]);
				}
			}
			
			for (int i = 0; i < obj[1].length; i++) {
				if (obj[carteActuelle][i] != null) {
					listEntite.add(obj[carteActuelle][i]);
				}
			}
			
			for (int i = 0; i < monstre[1].length; i++) {
				if (monstre[carteActuelle][i] != null) {
					listEntite.add(monstre[carteActuelle][i]);
				}
			}
			
			for (int i = 0; i < listProjectiles.size(); i++) {
				if (listProjectiles.get(i) != null) {
					listEntite.add(listProjectiles.get(i));
				}
			}
			
			for (int i = 0; i < listParticules.size(); i++) {
				if (listParticules.get(i) != null) {
					listEntite.add(listParticules.get(i));
				}
			}
			
			Collections.sort(listEntite, new Comparator<Entite>() {
				
				@Override
				public int compare(Entite e1, Entite e2) {
					int resultat = Integer.compare(e1.carteX, e2.carteX);
					return resultat;
				}
			});
			
			for (int i = 0; i < listEntite.size(); i++) {
				listEntite.get(i).afficher(graph2);
			}
			
			listEntite.clear();
			
			joueur.afficher(graph2);
			
			interfaceJoueur.afficher(graph2);
		}
		
		if (action.debug == true) {
			long drawEnd = System.nanoTime();
			long passe = drawEnd - drawStart;
			
			graph2.setFont(new Font("Arial",Font.PLAIN, 20));
			graph2.setColor(Color.white);
			int x = 10;
			int y = 400;
			int ligne = 20;
			
			graph2.drawString("MondeX" + joueur.carteX, x, y);
			y += ligne;
			graph2.drawString("MondeY" + joueur.carteY, x, y);
			y += ligne;
			graph2.drawString("MondeColX" + (joueur.carteX+joueur.aireCollision.x)/tailleFinale, x, y);
			y += ligne;
			graph2.drawString("MondeColY" + (joueur.carteY+joueur.aireCollision.y)/tailleFinale, x, y);
			y += ligne;
			graph2.drawString("Draw time" + passe, x, y);

		}
	}
		
	public void dessinerEcran() {
		
		Graphics g = getGraphics();
		g.drawImage(tempEcran, 0, 0, ecranWidth, ecranHeight, null);
		g.dispose();
	}
	
	public void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		long drawStart = 0;
		Graphics2D graph2 = (Graphics2D) graph;
		
		
		if (etatJeu == intro) {
			interfaceJoueur.afficher(graph2);
		}
		else {
			terrain.afficher(graph2);
			
			for (int i = 0; i < iTerrain.length; i++) {
				if (iTerrain[carteActuelle][i] != null) {
					iTerrain[carteActuelle][i].afficher(graph2);
				}
			}
			
			listEntite.add(joueur);
			
			for (int i = 0; i < mage[1].length; i++) {
				if (mage[carteActuelle][i] != null) {
					listEntite.add(mage[carteActuelle][i]);
				}
			}
			
			for (int i = 0; i < obj[1].length; i++) {
				if (obj[carteActuelle][i] != null) {
					listEntite.add(obj[carteActuelle][i]);
				}
			}
			
			for (int i = 0; i < monstre[1].length; i++) {
				if (monstre[carteActuelle][i] != null) {
					listEntite.add(monstre[carteActuelle][i]);
				}
			}
			
			for (int i = 0; i < listProjectiles.size(); i++) {
				if (listProjectiles.get(i) != null) {
					listEntite.add(listProjectiles.get(i));
				}
			}
			
			for (int i = 0; i < listParticules.size(); i++) {
				if (listParticules.get(i) != null) {
					listEntite.add(listParticules.get(i));
				}
			}
			
			Collections.sort(listEntite, new Comparator<Entite>() {
				
				@Override
				public int compare(Entite e1, Entite e2) {
					int resultat = Integer.compare(e1.carteX, e2.carteX);
					return resultat;
				}
			});
			
			for (int i = 0; i < listEntite.size(); i++) {
				listEntite.get(i).afficher(graph2);
			}
			
			listEntite.clear();
			
			joueur.afficher(graph2);
			
			interfaceJoueur.afficher(graph2);
		}
		
		if (action.debug == true) {
			long drawEnd = System.nanoTime();
			long passe = drawEnd - drawStart;
			
			graph2.setFont(new Font("Arial",Font.PLAIN, 20));
			graph2.setColor(Color.white);
			int x = 10;
			int y = 400;
			int ligne = 20;
			
			graph2.drawString("MondeX" + joueur.carteX, x, y);
			y += ligne;
			graph2.drawString("MondeY" + joueur.carteY, x, y);
			y += ligne;
			graph2.drawString("MondeColX" + (joueur.carteX+joueur.aireCollision.x)/tailleFinale, x, y);
			y += ligne;
			graph2.drawString("MondeColY" + (joueur.carteY+joueur.aireCollision.y)/tailleFinale, x, y);
			y += ligne;
			graph2.drawString("Draw time" + passe, x, y);

		}
		
		graph2.dispose();
	}
	
	public void jouerMusique(int i) {
		musique.setFichier(i);
		musique.play();
		musique.loop();
	}
	
	public void stopperMusique() {
		musique.stop();
	}
	
	public void jouerSE(int i) {
		son.setFichier(i);
		son.play();
	}
}
