package asserts;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;

public class Asserts {
	// Imagenes
	public static Image logoMario;
	public static Image spriteMario;
	public ImageIcon pause;
	public static Image background;
	
	// Sonidos
	public static AudioClip hereWeGo;
	public static AudioClip bye;
	public static AudioClip coin;
	public static AudioClip lost;
	public static AudioClip levelClear;
	public static AudioClip backgroundMusic;
	public AudioClip mainScreenMusic;
	public static AudioClip pauseSound;
	
	public void loadAsserts() {
		logoMario = (new ImageIcon("asserts/logoMario.png")).getImage();
		spriteMario = (new ImageIcon("asserts/SpriteMario.png")).getImage();
		pause = (new ImageIcon("asserts/pauseIcon.png"));
		background = (new ImageIcon("asserts.background.png")).getImage();
		try {
			hereWeGo = Applet.newAudioClip(new File("asserts/HereWeGoMario.wav").toURI().toURL());
			bye = Applet.newAudioClip(new File("asserts/byeByeMario.wav").toURI().toURL());
			coin = Applet.newAudioClip(new File("asserts/coinMario.wav").toURI().toURL());
			lost = Applet.newAudioClip(new File("asserts/lostGame.wav").toURI().toURL());
			levelClear = Applet.newAudioClip(new File("asserts/levelClear.wav").toURI().toURL());
			backgroundMusic = Applet.newAudioClip(new File("asserts/backgroundMusic.wav").toURI().toURL());
			mainScreenMusic = Applet.newAudioClip(new File("asserts/loadMusic.wav").toURI().toURL());
			pauseSound = Applet.newAudioClip(new File("asserts/pause.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
