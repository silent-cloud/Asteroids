package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Command class that turns the player ship
 * to the right.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class TurnPSRightCommand extends Command
{
	private GameWorld gw;

	/**
	 * Class Constructor
	 * 
	 * @param gw the game world of the game
	 */
	
	public TurnPSRightCommand(GameWorld gw)
	{
		super("PS Right");
		this.gw = gw;
	}

	/**
	 * Turns the player ship to the right when invoked.
	 */
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != -1)
		{
			if (evt.getKeyEvent() == -94)
				System.out.println("Right Arrow key was pressed.");
			else if (evt.getKeyEvent() == 'r')
				System.out.println("r key was pressed.");
			else
				System.out.println("PS Right button was clicked.");
			gw.turnPSRight();
		}
	}
}
