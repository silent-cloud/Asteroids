package sounds;

import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;
import com.mycompany.a3.IGameWorld;

public class LoseSound implements Observer
{
	private static LoseSound sound;	//The single instance of the lose sound
	private boolean soundOn;		//The flag determining whether the sound is on or off
	private Media m;				//The media object that holds the lose sound file
	
	/**
	 * Class Constructor
	 */
	
	private LoseSound()
	{
		try 
		{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/lose.wav");
			m = MediaManager.createMedia(is, "audio/wav");
		} catch (Exception e) {
			e.printStackTrace();
		}
		soundOn = true;
	}
	
	/**
	 * Gets the single instance of the lose
	 * sound. If it hasn't been created, make one.
	 * 
	 * @return	the instance of the lose sound
	 */
	
	public static LoseSound getLoseSound()
	{
		if (sound == null)
			sound = new LoseSound();
		return sound;
	}
	
	/**
	 * Plays the sound of when the player loses the game.
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
