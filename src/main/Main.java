package main;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		
		JFrame affichage = new JFrame();
		affichage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		affichage.setResizable(false);
		affichage.setTitle("Adventure Time");
		
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
}