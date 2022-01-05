package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Command class that decreases the player ship speed
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class DecPSSpeedCommand extends Command
{
	private GameWorld gw;

	/**
	 * Class Constructor
	 * 
	 * @param gw the game world of the game
	 */
	
	public DecPSSpeedCommand(GameWorld gw)
	{
		super("PS Speed (-)");
		this.gw = gw;
	}

	/**
	 * Decreases the player ship object speed when invoked.
	 */
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != -1)
		{
			if (evt.getKeyEvent() == -92)
				System.out.println("Down Arrow key was pressed.");
			else if (evt.getKeyEvent() == 'd')
				System.out.println("d key was pressed.");
			else
				System.out.println("PS Speed (-) button was clicked.");
			gw.decPSSpeed();
		}
	}
}
