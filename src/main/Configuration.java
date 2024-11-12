package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configuration {

	Ecran ecran;
	
	public Configuration(Ecran ecran) {
		this.ecran = ecran;
	}
	
	public void sauverConfiguration() {
		try {
			BufferedWriter bi = new BufferedWriter(new FileWriter("configuration.txt"));
			
			if (ecran.pleinEcranOn) {
				bi.write("On");
			}
			if (!ecran.pleinEcranOn) {
				bi.write("Off");
			}
			bi.newLine();
			
			bi.write(String.valueOf(ecran.musique.echelleVolume));
			bi.newLine();
			
			bi.write(String.valueOf(ecran.son.echelleVolume));
			bi.newLine();
			
			bi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void chargerConfiguration() {
		
		try {
			BufferedReader bi = new BufferedReader(new FileReader("configuration.txt"));
			
			String lire = bi.readLine();
			
			if (lire.equals("On")) {
				ecran.pleinEcranOn = true;
			}
			if (lire.equals("Off")) {
				ecran.pleinEcranOn = false;
			}
			
			lire = bi.readLine();
			ecran.musique.echelleVolume = Integer.parseInt(lire);
			
			lire = bi.readLine();
			ecran.son.echelleVolume = Integer.parseInt(lire);
			
			bi.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
