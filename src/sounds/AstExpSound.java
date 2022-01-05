package sounds;

import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;
import com.mycompany.a3.IGameWorld;

public class AstExpSound implements Observer
{
	private static AstExpSound sound;	//Single instance of the asteroid explosion sound class
	private boolean soundOn;			//Flag for determining whether sound is on or off
	private Media m;					//The media object containing the sound file
	
	/**
	 * Class Constructor
	 */
	
	private AstExpSound()
	{
		try 
		{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/explosion.wav");
			m = MediaManager.createMedia(is, "audio/wav");
		} catch (Exception e) {
			e.printStackTrace();
		}
		soundOn = true;
	}
	
	/**
	 * Gets the single instance of the asteroid explosion
	 * sound. If it hasn't been created, make one.
	 * 
	 * @return	the instance of the asteroid explosion sound
	 */
	
	public static AstExpSound getAstExpSound()
	{
		if (sound == null)
			sound = new AstExpSound();
		return sound;
	}
	
	/**
	 * Plays the sound of when an asteroid explodes.
	 */
	
	public void play()
	{
		if (soundOn) {
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
