package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionClavier implements KeyListener {


	Ecran ecran;
	public boolean haut, bas, gauche, droite;
	

	public ActionClavier(Ecran ecran) {
		this.ecran = ecran;
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int touche = e.getKeyCode();
		
		if (touche == KeyEvent.VK_RIGHT) {
			droite = true;
		}
		
		if (touche == KeyEvent.VK_LEFT) {
			gauche = true;
		}
		
		if (touche == KeyEvent.VK_UP) {
			haut = true;
		}
		
		if (touche == KeyEvent.VK_DOWN) {
			bas = true;
		}

		if (touche == KeyEvent.VK_P) {
			if (ecran.etatActuel == ecran.enJeu) {
				ecran.etatActuel = ecran.pauseJeu;
			}
			else if (ecran.etatActuel == ecran.pauseJeu) {
				ecran.etatActuel = ecran.enJeu;
			}
		}

		if (touche == KeyEvent.VK_E) {
				ecran.interfaceJoueur.finDuJeu = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int touche = e.getKeyCode();
		
		if (touche == KeyEvent.VK_RIGHT) {
			droite = false;
		}
		
		if (touche == KeyEvent.VK_LEFT) {
			gauche = false;
		}
		
		if (touche == KeyEvent.VK_UP) {
			haut = false;
		}
		
		if (touche == KeyEvent.VK_DOWN) {
			bas = false;
		}		
	}

}
