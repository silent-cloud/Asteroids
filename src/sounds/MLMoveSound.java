package sounds;

import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;
import com.mycompany.a3.IGameWorld;

public class MLMoveSound implements Observer
{
	private static MLMoveSound sound;	//The single instance of the missile launcher move sound
	private boolean soundOn;			//The flag determining if the sound is on or off
	private Media m;					//The media object that holds the missile launcher move sound
	
	/**
	 * Class Constructor
	 */
	
	private MLMoveSound()
	{
		try 
		{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/mlmove.wav");
			m = MediaManager.createMedia(is, "audio/wav");
		} catch (Exception e) {
			e.printStackTrace();
		}
		soundOn = true;
	}
	
	/**
	 * Gets the single instance of the player missile launcher
	 * movement sound. If it hasn't been created, make one.
	 * 
	 * @return	the instance of the player missile launcher move sound
	 */
	
	public static MLMoveSound getMLMoveSound()
	{
		if (sound == null)
			sound = new MLMoveSound();
		return sound;
	}
	
	/**
	 * Plays the sound of when player missile launcher turns.
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
