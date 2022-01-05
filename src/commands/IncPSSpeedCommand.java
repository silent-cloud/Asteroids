package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Command class that increases the player ship speed
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class IncPSSpeedCommand extends Command
{
	private GameWorld gw;

	/**
	 * Class Constructor
	 * 
	 * @param gw the game world of the game
	 */
	
	
	public IncPSSpeedCommand(GameWorld gw)
	{
		super("PS Speed (+)");
		this.gw = gw;
	}
	
	/**
	 * Increases the player ship object speed when invoked.
	 */

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != -1)
		{
			if (evt.getKeyEvent() == -91)
				System.out.println("Up Arrow key was pressed.");
			else if (evt.getKeyEvent() == 'i')
				System.out.println("i key was pressed.");
			else
				System.out.println("PS Speed (+) button was clicked.");
			gw.incPSSpeed();
		}
	}
}
