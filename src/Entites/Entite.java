package Entites;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Ecran;

public class Entite {
	
	public int carteX, carteY;
	public int vitesse;
	
	public BufferedImage avant, arriere, gauche, droite, avant1, arriere1, gauche1, droite1;
	public String direction;
	
	int marcher = 1;
	int compteur = 0;
	int attente = 0;

	public Rectangle aireCollision;
	public int aireSolideDefautX, aireSolideDefautY;
	public boolean collision = false;

	public int dureeVie;
    private int pv;
    private int food;
    private int age;
    private int degats;
    private String nom;
    private int fertilite;
    private Boolean actionHaut ,actionBas, actionGauche, actionDroite;
	private boolean estDeplace;
    private String sexe;
    private boolean alive = true;
	
	public Ecran ecran;

    public Entite(int pv, int degats, int food, int fertilite, int age, int vitesse, String nom, String sexe, Ecran ecran) {
        this.pv = pv;
        this.age = age;
        this.degats = degats;
        this.nom = nom;
        this.food = food;
        this.fertilite = fertilite;
        this.sexe = sexe;
		this.ecran = ecran;
		this.vitesse = vitesse;
    }

    public int getPv() {
        return pv;
    }

    public int getDegats() {
        return degats;
    }

    public String getNom() {
        return nom;
    }

    public String getSexe() {
        return sexe;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        if (this.food + food <= 150) {
            this.food = this.food + food;
        }
        if (this.food <= 0) {
            this.alive = false;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age, Entite animal) {
        String[] nvNom = this.nom.split(" ");
        if (this.age - age == 0) {
            this.alive = false;
        }
        this.age = this.age - age;
        if (this.age <= animal.dureeVie - animal.dureeVie/4 && nvNom.length > 1) {
            this.nom = "jeune " + nvNom[1];
        }
        if (this.age <= animal.dureeVie/2 && nvNom.length > 1) {
            this.nom = nvNom[1];
        }
    }

    public int getFertilite() {
        return fertilite;
    }

    public void setFertilite(int fertilite) {
        if (this.fertilite + fertilite < 121) {
            this.fertilite = this.fertilite + fertilite;
        }
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setPv(int pv) {
        this.pv = this.pv - pv;
        if (this.pv <= 0) {
            this.alive = false;
        }
    }

	public boolean isAlive() {
        return alive;
    }

    public Boolean estDeplace() {
        return estDeplace;
    }

    public void finDuTour() {
        estDeplace = false;
    }

	public void hasard() {
		actionBas = false;
		actionDroite = false;
		actionGauche = false;
		actionHaut = false;
		Random alea = new Random();
		int choix = alea.nextInt(4);
		switch(choix) {
		case 0:
			actionHaut = true;
			break;
		case 1:
			actionBas = true;
			break;
		case 2:
			actionGauche = true;
			break;
		case 3:
			actionDroite = true;
			break;
		}
	}

	public void Deplacer(Entite entite) {

		attente++;
		if (attente > 60) {		
			hasard();
			if (actionHaut == true) {
				direction = "haut";
			}
			if (actionBas == true) {
				direction = "bas";
			}
			if (actionGauche == true) {
				direction = "gauche";
			}
			if (actionDroite == true) {
				direction = "droite";
			}
			attente = 0;
		}
			collision = false;
			ecran.collisions.AnalyserTerrain(this);
			
			int index = ecran.collisions.analyserObjet(this, true);
			interactionObject(index);
			
			if (collision == false) {
				switch(direction) {
				case "haut":
					carteY -= entite.vitesse;
					break;
				case "bas":
					carteY += entite.vitesse;
					break;
				case "gauche":
					carteX -= entite.vitesse;
					break;
				case "droite":
					carteX += entite.vitesse;
					break;
				}
				
			}

			if (compteur > 15) {
				if (marcher == 1) {
					marcher = 2;
				}
				else if (marcher == 2) {
					marcher = 1;
				}
				compteur = 0;
	
			}
			compteur++;

	}
	
	public void interactionObject(int index) {
		
	}

	public void afficher(Graphics2D graph2) {
		//graph2.setColor(Color.black);
		//graph2.fillRect(x, y, ecran.tailleFinale, ecran.tailleFinale);
		
		BufferedImage image = null;

		int actuelX = carteX - ecran.joueur.carteX + ecran.joueur.ecranX;
		int actuelY = carteY - ecran.joueur.carteY + ecran.joueur.ecranY;
		
		switch(direction) {
		case "haut":
			if (marcher == 1) {
				image = arriere;
			}
			if (marcher == 2) {
				image = arriere1;
			}
			break;
		case "bas":
			if (marcher == 1) {
				image = avant;
			}
			if (marcher == 2) {
				image = avant1;
			}
			break;
		case "gauche":
			if (marcher == 1) {
				image = gauche;
			}
			if (marcher == 2) {
				image = gauche1;
			}
			break;
		case "droite":
			if (marcher == 1) {
				image = droite;
			}
			if (marcher == 2) {
				image = droite1;
			}
			break;
		}
		// System.out.println(carteX);
		// System.out.println(carteY);

		graph2.drawImage(image, actuelX, actuelY, null);
	}


}
