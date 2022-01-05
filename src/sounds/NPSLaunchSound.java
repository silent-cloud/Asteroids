package sounds;

import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;
import com.mycompany.a3.IGameWorld;

public class NPSLaunchSound implements Observer
{
	private static NPSLaunchSound sound;	//The single instance of the non-player ship missile launch sound
	private boolean soundOn;				//The flag determining whether the sound is on or off
	private Media m;						//The media object that holds the non-player ship launch sound
	
	/**
	 * Class Constructor
	 */
	
	private NPSLaunchSound()
	{
		try 
		{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/npslaunch.wav");
			m = MediaManager.createMedia(is, "audio/wav");
		} catch (Exception e) {
			e.printStackTrace();
		}
		soundOn = true;
	}
	
	/**
	 * Gets the single instance of the non-player ship missile
	 * launch sound. If it hasn't been created, make one.
	 * 
	 * @return	the instance of the non-player ship missile launch sound
	 */
	
	public static NPSLaunchSound getNPSLaunchSound()
	{
		if (sound == null)
			sound = new NPSLaunchSound();
		return sound;
	}
	
	/**
	 * Plays the sound of when the non-player ship launches
	 * a missile.
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
