package sounds;

import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;
import com.mycompany.a3.IGameWorld;

public class PMLaunchSound implements Observer
{
	private static PMLaunchSound sound;	//The single instance of the player ship missile sound
	private boolean soundOn;			//The flag determining whether the sound is on or off
	private Media m;					//The media object that holds the player missile launch sound
	
	/**
	 * Class Constructor
	 */
	
	private PMLaunchSound()
	{
		try 
		{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/pmlaunch.wav");
			m = MediaManager.createMedia(is, "audio/wav");
		} catch (Exception e) {
			e.printStackTrace();
		}
		soundOn = true;
	}
	
	/**
	 * Gets the single instance of the player ship missile
	 * launch sound. If it hasn't been created, make one.
	 * 
	 * @return	the instance of player ship missile launch sound
	 */
	
	public static PMLaunchSound getPMLaunchSound()
	{
		if (sound == null)
			sound = new PMLaunchSound();
		return sound;
	}
	
	/**
	 * Plays the sound of when a player launches a missile.
	 */
	
	public void play()
	{
		if (soundOn)
		{
			m.setTime(0);
			m.play();
		}
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
	}
}
