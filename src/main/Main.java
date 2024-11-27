package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {

	public static JFrame affichage;
	public static void main(String[] args) {
		
		affichage = new JFrame();
		affichage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		affichage.setResizable(false);
		affichage.setTitle("Adventure Time");
		new Main().setIcon();
		
		Ecran ecran = new Ecran();
		affichage.add(ecran);
		
		ecran.configuration.chargerConfiguration();
		if (ecran.pleinEcranOn) {
			
		}
		
		affichage.pack();
		
		affichage.setLocationRelativeTo(null);
		affichage.setVisible(true);
		
		ecran.initialiserJeu();
		ecran.lancerFil();
	}

	public void setIcon() {
        java.net.URL imgURL = getClass().getClassLoader().getResource("joueur/paladin/Avant1.png");
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            affichage.setIconImage(icon.getImage());
        } else {
            System.err.println("Erreur : L'image /joueur/paladin/Avant1.png est introuvable");
        }
    }
}