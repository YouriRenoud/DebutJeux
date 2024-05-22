package main;

import Entites.Entite;
import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entites.Entite;
import Entites.GestionEntite;
import Entites.Joueur;
import Entites.Poule;
import object.JeuObject;
import terrain.GestionTerrain;
import java.util.concurrent.CopyOnWriteArrayList;


public class Ecran extends JPanel implements Runnable {
	final int tailleElement = 16;
	final int echelle = 3;

	public final int tailleFinale = tailleElement * echelle;

	public final int colonneMax = 16;
	public final int ligneMax = 12;
	public final int ecranLongueur = colonneMax * tailleFinale;
	public final int ecranLargeur = ligneMax * tailleFinale;

	// param map monde
	public final int mondeColMax = 50;
	public final int mondeLignMax = 50;
	public final int mondeLongueur = tailleFinale * mondeColMax;
	public final int mondeLargeur = tailleFinale * mondeLignMax;

	ActionClavier action = new ActionClavier(this);
	Thread filDuJeu;

	public GestionTerrain terrain = new GestionTerrain(this);
	public VerifierCollision collisions = new VerifierCollision(this);
	public GererObject gerer = new GererObject(this);
	public GestionEntite gerer2 = new GestionEntite(this);

	public Son son = new Son();
	public Son musique = new Son();

	public Joueur joueur = new Joueur(this, action);

	public JeuObject obj[] = new JeuObject[10];
	public List<Entite> ent = new CopyOnWriteArrayList<>();

	public int nbrEntite = 0;

	public UI interfaceJoueur = new UI(this);

	int FPS = 60;

	public int etatActuel;
	public final int enJeu = 1;
	public final int pauseJeu = 2;

	public Ecran() {

		this.setPreferredSize(new Dimension(ecranLongueur, ecranLargeur));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(action);
		this.setFocusable(true);
	}

	public void initialiserJeu() {
		gerer.setObjects();
		gerer2.setObjects();
		// jouerMusique(0);
		etatActuel = enJeu;
	}

	public void lancerFil() {

		filDuJeu = new Thread(this);
		filDuJeu.start();
	}

	@Override
	public void run() {

		while (filDuJeu != null) {

			double intervalle = 1000000000 / FPS;
			double prochainIntervalle = System.nanoTime() + intervalle;

			//ent.add(new Poule(6, 6, this.nbrEntite, "F", this));

			miseAJour();

			repaint();

			try {
				double tempsRestant = (prochainIntervalle - System.nanoTime()) / 1000000;

				if (tempsRestant < 0) {
					tempsRestant = 0;
				}

				Thread.sleep((long) tempsRestant);

				prochainIntervalle += intervalle;

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void miseAJour() {

		if (etatActuel == enJeu) {
			joueur.miseAJour();

			for (Entite e : ent) { // Iterate over the copy
				if (e != null) {
					e.Deplacer(e);
				}
			}
			gerer2.verifierMorts();
		}
		if (etatActuel == pauseJeu) {

		}
	}

	int compteur = 0;
	public void paintComponent(Graphics graph) {
		super.paintComponent(graph);

		Graphics2D graph2 = (Graphics2D) graph;

		terrain.afficher(graph2);

		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].afficher(graph2, this);
			}
		}

		for (Entite e : ent) {
			if (e != null) {
				e.afficher(graph2);
				//System.out.println(e.carteX + " " + e.carteY);
			}
		}

		joueur.afficher(graph2);

		interfaceJoueur.afficher(graph2);

		graph2.dispose();
	}

	public void jouerMusique(int i) {
		musique.setFichier(i);
		musique.play();
		musique.loop();
	}

	public void stopperMusique(int i) {
		musique.stop();
	}

	public void jouerSE(int i) {
		son.setFichier(i);
		son.play();
	}
}
