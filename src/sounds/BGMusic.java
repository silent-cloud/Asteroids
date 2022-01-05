package sounds;

import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;
import com.mycompany.a3.IGameWorld;

public class BGMusic implements Runnable, Observer
{
	private static BGMusic music;	//The single instance of the background music.
	private boolean soundOn;		//The flag determining whether the sound is on or off
	private boolean paused;			//The flag determining whether the game is paused or not
	private Media m;				//The media object that holds the background music file
	
	/**
	 * Class Constructor
	 */
	
	private BGMusic()
	{
		try 
		{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/background.wav");
			m = MediaManager.createMedia(is, "audio/wav", this);
			m.setVolume(50);
		} catch (Exception e) {
			e.printStackTrace();
		}
		soundOn = true;
	}
	
	/**
	 * Gets the single instance of the background
	 * music. If it hasn't been created, make one.
	 * 
	 * @return	the instance of the background music
	 */
	
	public static BGMusic getMusic()
	{
		 if (music == null)
			 music = new BGMusic();
		 return music;
	}
	
	/**
	 * Pauses the background music.
	 */
	
	public void pause()
	{
		m.pause();
	}
	
	/**
	 * Resumes the background music.
	 */
	
	public void play()
	{
		m.play();
	}
	
	/**
	 * Loops the background music continuously
	 */
	
	@Override
	public void run()
	{
		m.setTime(0);
		m.play();
	}
	
	/**
	 * Updates the sound value according to the sound
	 * checkbox on the side menu
	 */
	
	@Override
	public void update(Observable observable, Object data) 
	{
		IGameWorld gw = (IGameWorld) data;
		soundOn = gw.getSoundVal();
		paused = gw.isPaused();
		if (!soundOn && !paused)
		{
			pause();
		} else if (soundOn && !paused) {
			play();
		}
	}
}
