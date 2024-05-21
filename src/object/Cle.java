package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.Ecran;

public class Cle extends JeuObject {
	
	Ecran ecran;

	public Cle(Ecran ecran) {
		this.ecran = ecran;
		nom = "Cle";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/Cle.png"));
			fct.miseAEchelle(image, ecran.tailleFinale, ecran.tailleFinale);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
