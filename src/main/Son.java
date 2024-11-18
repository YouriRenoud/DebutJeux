package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Son {
	Clip clip;
	URL sonURL[] = new URL[30];
	FloatControl fc;
	int echelleVolume = 3;
	float volume;
	
	public Son() {
		sonURL[0] = getClass().getResource("/sons/BlueBoyAdventure.wav");
		sonURL[1] = getClass().getResource("/sons/coin.wav");
		sonURL[2] = getClass().getResource("/sons/powerup.wav");
		sonURL[3] = getClass().getResource("/sons/unlock.wav");
		sonURL[4] = getClass().getResource("/sons/fanfare.wav");
		sonURL[5] = getClass().getResource("/sons/hitmonster.wav");
		sonURL[6] = getClass().getResource("/sons/receivedamage.wav");
		sonURL[7] = getClass().getResource("/sons/swingsword.wav");
		sonURL[8] = getClass().getResource("/sons/deathennemy.wav");		
		sonURL[9] = getClass().getResource("/sons/levelup.wav");
		sonURL[10] = getClass().getResource("/sons/cursor.wav");
		sonURL[11] = getClass().getResource("/sons/bouledefeu.wav");
		sonURL[12] = getClass().getResource("/sons/cuttree.wav");
		sonURL[13] = getClass().getResource("/sons/gameover.wav");
		sonURL[14] = getClass().getResource("/sons/porte.wav");
		sonURL[15] = getClass().getResource("/sons/mage.wav");
		sonURL[16] = getClass().getResource("/sons/dormir.wav");
		sonURL[17] = getClass().getResource("/sons/coupBloque.wav");
		sonURL[18] = getClass().getResource("/sons/parry.wav");
		sonURL[19] = getClass().getResource("/sons/ecrire.wav");



	}
	
	public void setFichier(int i) {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(sonURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			verifierVolume();
		} catch (Exception e) {
			
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	
	public void verifierVolume() {
		switch(echelleVolume) {
		case 0: volume = -80f; break;
		case 1: volume = -20f; break;
		case 2: volume = -12f; break;
		case 3: volume = -5f; break;
		case 4: volume = 1f; break;
		case 5: volume = 6f; break;
		}
		fc.setValue(volume);
	}
	
	
}
