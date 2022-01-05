package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Command class that creates a player missile
 * object from the player ship
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class FirePSMissileCommand extends Command
{
	private GameWorld gw;

	/**
	 * Class Constructor
	 * 
	 * @param gw the game world of the game
	 */
	
	public FirePSMissileCommand(GameWorld gw)
	{
		super("PS Fire");
		this.gw = gw;
	}
	
	/**
	 * Creates a player missile object when invoked.
	 */

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != -1)
		{
			if (evt.getKeyEvent() == -90)
				System.out.println("Spacebar key was pressed.");
			else
				System.out.println("PS Fire button was clicked.");
			gw.firePSMissile();
		}
	}
}
