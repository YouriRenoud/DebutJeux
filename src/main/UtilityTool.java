package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
	
	public BufferedImage scaleImage(BufferedImage originale, int longueur, int hauteur) {
		
		BufferedImage bonne = new BufferedImage(longueur, hauteur, originale.getType());
		Graphics2D graph = bonne.createGraphics();
		graph.drawImage(originale, 0, 0, longueur, hauteur, null);
		graph.dispose();
		
		return bonne;
	}
}
